package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganizationIncomeAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganizationIncomeAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganizationIncomeAllY2025Logic;

/**
 * 政治資金収支報告書収入の部を登録する
 */
@Component
public class InsertPoliticalOrganizationIncomeAllLogic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPoliticalOrganizationIncomeAllY2022Logic insertPoliticalOrganizationIncomeAllY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganizationIncomeAllY2024Logic insertPoliticalOrganizationIncomeAllY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganizationIncomeAllY2025Logic insertPoliticalOrganizationIncomeAllY2025Logic;

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
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final AllBookDto allBookDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getHoukokuNen()) {
            // 2022年
            case YEAR_2022:
                size = insertPoliticalOrganizationIncomeAllY2022Logic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                size = insertPoliticalOrganizationIncomeAllY2024Logic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPoliticalOrganizationIncomeAllY2025Logic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);
                break;

                // NOTE:Logic実行追加位置

            default:
                break;
        }

        return size;
    }

}
