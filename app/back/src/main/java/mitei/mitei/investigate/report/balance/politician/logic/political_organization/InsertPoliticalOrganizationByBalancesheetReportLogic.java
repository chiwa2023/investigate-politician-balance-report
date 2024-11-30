package mitei.mitei.investigate.report.balance.politician.logic.political_organization;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書から政治団体を新規登録する
 */
@Component
public class InsertPoliticalOrganizationByBalancesheetReportLogic {

    /** 政治団体Repository */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

    /** 全文検索用成形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 登録処理を行う
     *
     * @param coverDto     政治資金収支報告書表紙Dto
     * @param privilegeDto 権限確認Dto
     * @return 登録されたEntity
     */
    public PoliticalOrganizationEntity practice(final Sheet070100CoverAndOrganizationDetailsDto coverDto,
            final CheckPrivilegeDto privilegeDto) {

        PoliticalOrganizationEntity entity = new PoliticalOrganizationEntity();
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        entity.setPoliticalOrganizationId(0L); // auto increnment 明記
        entity.setPoliticalOrganizationName(coverDto.getDantaiName01());
        entity.setPoliticalOrganizationNameKana(coverDto.getDantaiNameKana());
        entity.setDantaiKbn(coverDto.getDantaiKbn());

        final String BLANK = "　"; // 公式で項目連結時には全角スペース
        // 住所
        entity.setAddressAll(coverDto.getJimushoJusho() + BLANK + coverDto.getJimushoJushoTatemono());

        // 代表者
        entity.setDaihyoshaLastName(coverDto.getDaihyoushaNameLast());
        entity.setDaihyoshaFirstName(coverDto.getDaihyoushaNameFirst());
        entity.setDaihyoshaName(entity.getDaihyoshaLastName() + BLANK + entity.getDaihyoshaFirstName());

        // 会計責任者
        entity.setKaikeiSekininshaLastName(coverDto.getKaikeiSekinnshaNameLast());
        entity.setKaikeiSekininshaFirstName(coverDto.getKaikeiSekinnshaNameFirst());
        entity.setKaikeiSekininshaName(
                entity.getKaikeiSekininshaLastName() + BLANK + entity.getKaikeiSekininshaFirstName());

        // 会計責任者の職務代行者
        entity.setKaikeiDaikoLastName(coverDto.getJimuTantousha1NameLast());
        entity.setKaikeiDaikoFirstName(coverDto.getJimuTantousha1NameFirst());
        entity.setKaikeiDaikoName(entity.getKaikeiDaikoLastName() + BLANK + entity.getKaikeiDaikoFirstName());

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
