package mitei.mitei.investigate.report.balance.politician.logic.political_organization;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet072000OathDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * コード標準化されていない読みとりデータからコード化された政治団体を推定する
 */
@Component
public class GuesshPoliticalOrganizationLogic {

    /** 政治団体Repository */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 全角スペース */
    private static final String ZENKAKU_SPACE = "　";

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_TIMESTAMP = INIT_LocalDate.atTime(0, 0, 0);

    /**
     * 登録済の政治団体情報を推定する
     *
     * @param cover0701Dto 政治資金収支報告書表紙(様式7の1)
     * @param oath0720dto  政治資金収支報告書宣誓書(様式7の20)
     * @return 政治資金収支報告書用政治団体Dto
     */
    public BalancesheetReportDocumentPoliticalPropertyDto practice( // SUPPRESS CHECKSTYLE ReturnCountCheck
            final Sheet070100CoverAndOrganizationDetailsDto cover0701Dto, final Sheet072000OathDto oath0720dto) {

        // 完全一致していないデータがない場合は処理を抜ける
        BalancesheetReportDocumentPoliticalPropertyDto dto = new BalancesheetReportDocumentPoliticalPropertyDto();
        // 団体目名と代表者名はデータからの読みとりそのままも残す(報告年は読みとりデータを参照)
        dto.setDantaiName(cover0701Dto.getDantaiName01());
        dto.setDaihyouName(
                this.convertFullName(cover0701Dto.getDaihyoushaNameLast(), cover0701Dto.getDaihyoushaNameFirst()));
        dto.setHoukokuNen(cover0701Dto.getHoukokuNen());
        LocalDate oathDate = dateConvertUtil.practiceWarekiToLocalDate(oath0720dto.getDateOath());
        dto.setOfferingDate(oathDate);

        // 団体名などから一致データを探す
        List<PoliticalOrganizationEntity> list = politicalOrganizationRepository
                .findByPoliticalOrganizationNameAndDantaiKbnAndDaihyoshaName(cover0701Dto.getDantaiName01(),
                        cover0701Dto.getDantaiKbn(), this.convertFullName(cover0701Dto.getDaihyoushaNameLast(),
                                cover0701Dto.getDaihyoushaNameFirst()));
        if (list.isEmpty()) {
            return dto;
        }

        // 1件の場合はチェックして作成作業
        final int size1 = 1;
        if (size1 == list.size()) {
            return this.convertDto(dto,list.get(0), cover0701Dto);
        }

        // 複数ある場合は特定
        // 制度上の集計末日(時間)
        int year = cover0701Dto.getHoukokuNen();
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

        return this.convertDto(dto,entity, cover0701Dto);
    }

    private BalancesheetReportDocumentPoliticalPropertyDto convertDto(final BalancesheetReportDocumentPoliticalPropertyDto dto, final PoliticalOrganizationEntity entity,
            final Sheet070100CoverAndOrganizationDetailsDto cover0701Dto) {

        if (!entity.getAddressAll()
                .equals(this.convertFullName(cover0701Dto.getJimushoJusho(), cover0701Dto.getJimushoJushoTatemono()))) {
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

    private String convertFullName(final String name1, final String name2) {

        String first = "";
        String last = "";

        if (!Objects.isNull(name1)) {
            first = name1;
        }

        if (!Objects.isNull(name2)) {
            last = name2;
        }

        return first + ZENKAKU_SPACE + last;
    }
}
