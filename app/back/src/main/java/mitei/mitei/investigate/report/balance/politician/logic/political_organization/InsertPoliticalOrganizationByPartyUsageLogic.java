package mitei.mitei.investigate.report.balance.politician.logic.political_organization;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政党交付金使途報告書表紙データから政治団体新規追加処理をする
 */
@Component
public class InsertPoliticalOrganizationByPartyUsageLogic {

    /** 政治団体Repository */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

    /** 全文検索用成形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 挿入処理を行う
     *
     * @param coverDto 使途報告書様式8その1表紙
     * @param privilegeDto 権限確認Dto
     * @return 登録された政治団体Entity
     */
    public PoliticalOrganizationEntity practice(final Sheet0801Dto coverDto, final CheckPrivilegeDto privilegeDto) {

        PoliticalOrganizationEntity entity = new PoliticalOrganizationEntity();
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        entity.setPoliticalOrganizationId(0L); // auto increnment 明記
        entity.setPoliticalOrganizationName(coverDto.getPartyName());
        entity.setPoliticalOrganizationNameKana(coverDto.getPartyNameKana());
        entity.setDantaiKbn("1"); // 政治団体団体区分政党 TODO 定数実装次第修正する

        // 住所
        entity.setAddressAll(coverDto.getOfficeAddress());

        final String BLANK = "　"; // 公式で項目連結時には全角スペース

        // 代表者
        String nameDaihyou = coverDto.getDelegateName();
        entity.setDaihyoshaName(nameDaihyou);
        String[] cell = nameDaihyou.split(BLANK);
        final int shimeiSplitLength = 2;
        if (shimeiSplitLength == cell.length) {
            entity.setDaihyoshaLastName(cell[0]);
            entity.setDaihyoshaFirstName(cell[1]);
        }

        // 会計責任者
        String nameKaikeiSekinin = coverDto.getAccountManagerName();
        entity.setKaikeiSekininshaName(nameKaikeiSekinin);
        cell = nameKaikeiSekinin.split(BLANK);
        if (shimeiSplitLength == cell.length) {
            entity.setKaikeiSekininshaLastName(cell[0]);
            entity.setKaikeiSekininshaFirstName(cell[1]);
        }

        // 会計責任者の職務代行者
        String nameWorker1 = coverDto.getWorker1Name();
        entity.setKaikeiDaikoName(nameWorker1);
        cell = nameWorker1.split(BLANK);
        if (shimeiSplitLength == cell.length) {
            entity.setKaikeiDaikoLastName(cell[0]);
            entity.setKaikeiDaikoFirstName(cell[1]);
        }

        // 全文検索用カラム
        StringBuilder builder = new StringBuilder();
        builder.append(entity.getPoliticalOrganizationName()).append(entity.getDaihyoshaName()).append(entity.getAddressAll());
        entity.setSearchText(formatNaturalSearchTextUtil.practice(builder.toString()));

        // 最後に最新コード+1
        Integer code = 1;
        Optional<PoliticalOrganizationEntity> optional = politicalOrganizationRepository
                .findFirstByOrderByPoliticalOrganizationCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getPoliticalOrganizationCode();
        }
        entity.setPoliticalOrganizationCode(code);

        return politicalOrganizationRepository.save(entity);
    }
}
