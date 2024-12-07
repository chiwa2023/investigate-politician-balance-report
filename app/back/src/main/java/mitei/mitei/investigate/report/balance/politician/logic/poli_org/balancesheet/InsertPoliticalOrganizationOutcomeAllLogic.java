package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganizationOutcomeAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganizationOutcomeAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganizationOutcomeAllY2025Logic;

/**
 * 政治資金収支報告書の支出を登録する
 */
@Component
public class InsertPoliticalOrganizationOutcomeAllLogic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPoliticalOrganizationOutcomeAllY2022Logic insertPoliticalOrganizationOutcomeAllY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganizationOutcomeAllY2024Logic insertPoliticalOrganizationOutcomeAllY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganizationOutcomeAllY2025Logic insertPoliticalOrganizationOutcomeAllY2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allBookDto          全文書Dto(使用するのは様式7その14と15のみ)
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode, // SUPPRESS CHECKSTYLE NOPMD
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final AllBookDto allBookDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getHoukokuNen()) {
            // 2022年
            case YEAR_2022:
                size = insertPoliticalOrganizationOutcomeAllY2022Logic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                size = insertPoliticalOrganizationOutcomeAllY2024Logic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPoliticalOrganizationOutcomeAllY2025Logic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);

                break;

            // NOTE:Logic実行追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + documentPropertyDto.getHoukokuNen());
        }

        return size;

    }
}
