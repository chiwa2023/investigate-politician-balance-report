package mitei.mitei.investigate.report.balance.politician.logic.political_organization;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0807Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * コード標準化されていない読みとりデータからコード化された政治団体を推定する
 */
@Component
public class GuesshPoliticalOrganizationByPartyUsageLogic {

    /** 政治団体Repository */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_TIMESTAMP = INIT_LocalDate.atTime(0, 0, 0);

    /**
     * 登録済の政治団体情報を推定する
     *
     * @param cover0801Dto 政党交付金使途報告書表紙(様式8の1)
     * @param oath0807dto  政党交付金使途報告書宣誓書(様式8の7)
     * @return 政治資金収支報告書用政治団体Dto
     */
    public PartyUsageDocumentPoliticalPropertyDto practice( // SUPPRESS CHECKSTYLE ReturnCountCheck
            final Sheet0801Dto cover0801Dto, final Sheet0807Dto oath0807dto) {

        // 完全一致していないデータがない場合は処理を抜ける
        PartyUsageDocumentPoliticalPropertyDto dto = new PartyUsageDocumentPoliticalPropertyDto();
        // 団体目名と代表者名はデータからの読みとりそのままも残す(報告年は読みとりデータを参照)
        dto.setDantaiName(cover0801Dto.getPartyName());
        dto.setDaihyouName(cover0801Dto.getDelegateName());
        dto.setNendo(cover0801Dto.getNendo());
        LocalDate oathDate = dateConvertUtil.practiceWarekiToLocalDate(oath0807dto.getAccrualDate());
        dto.setOfferingDate(oathDate);

        // 団体名などから一致データを探す
        List<PoliticalOrganizationEntity> list = politicalOrganizationRepository
                .findByPoliticalOrganizationNameAndDantaiKbnAndDaihyoshaName(cover0801Dto.getPartyName(), "1",
                        cover0801Dto.getDelegateName());
        if (list.isEmpty()) {
            return dto;
        }

        // 1件の場合はチェックして作成作業
        final int size1 = 1;
        if (size1 == list.size()) {
            return this.convertDto(dto, list.get(0), cover0801Dto);
        }

        // 複数ある場合は特定
        // 制度上の集計末日(時間)
        int year = cover0801Dto.getNendo();
        LocalDateTime datetimeKijun = LocalDateTime.of(year, 12, 31, 23, 59, 59, 59); // SUPPRESS CHECKSTYLE MagicNumber

        // 複数ある場合は報告年年末日がが更新期間にあるものとする
        List<PoliticalOrganizationEntity> listFilter = list.stream().filter(entityCheck -> {
            if (INIT_TIMESTAMP.equals(entityCheck.getUpdateTimestamp())) {
                // 最新データは挿入時データより前であることを確認
                return entityCheck.getInsertTimestamp().isAfter(datetimeKijun);
            } else {
                // 履歴データは挿入時と更新時の間にあることを確認
                return entityCheck.getInsertTimestamp().isBefore(datetimeKijun)
                        && entityCheck.getUpdateTimestamp().isAfter(datetimeKijun);
            }
        }).toList();

        PoliticalOrganizationEntity entity;
        if (listFilter.isEmpty()) {
            // 2.更新期間に該当がない場合最新データ
            entity = list.stream().filter(entitySaishin -> entitySaishin.getSaishinKbn().equals(1)).toList().get(0);
        } else {
            // 範囲内の最新データを取得する
            List<PoliticalOrganizationEntity> listSort = new ArrayList<>(listFilter);
            listSort.sort((e1, e2) -> {
                return e1.getInsertTimestamp().compareTo(e2.getInsertTimestamp());
            });
            entity = listSort.get(0);
        }

        return this.convertDto(dto, entity, cover0801Dto);
    }

    private PartyUsageDocumentPoliticalPropertyDto convertDto(final PartyUsageDocumentPoliticalPropertyDto dto,
            final PoliticalOrganizationEntity entity, final Sheet0801Dto cover0801Dto) {

        if (!entity.getAddressAll().equals(cover0801Dto.getOfficeAddress())) {
            // 最後のチェックとして住所が異なっていた場合は空Dto
            return dto;
        }

        // dto.setRelationKbn(null); // TODO 廃止予定

        // 内容としては読みとりデータと同一であるはずだが、コード標準化するデータには標準化データを採用
        dto.setPoliticalOrganizationId(entity.getPoliticalOrganizationId());
        dto.setPoliticalOrganizationCode(entity.getPoliticalOrganizationCode());
        dto.setPoliticalOrganizationName(entity.getPoliticalOrganizationName());
        dto.setRelationPersonIdDelegate(entity.getDaihyoshaUserId());
        dto.setRelationPersonCodeDelegate(entity.getDaihyoshaUserCode());
        dto.setRelationPersonNameDelegate(entity.getDaihyoshaName());

        return dto;
    }
}
