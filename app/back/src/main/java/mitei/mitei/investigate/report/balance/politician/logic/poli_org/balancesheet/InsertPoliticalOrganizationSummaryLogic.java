package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0702SummaryTableIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0713ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0717SummaryTableOfAssetsDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganizationSummaryY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganizationSummaryY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganizationSummaryY2025Logic;

/**
 * 政治資金収支報告書の集計表(収入・支出・資産)を登録する
 */
@Component
public class InsertPoliticalOrganizationSummaryLogic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPoliticalOrganizationSummaryY2022Logic insertPoliticalOrganizationSummaryY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganizationSummaryY2024Logic insertPoliticalOrganizationSummaryY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganizationSummaryY2025Logic insertPoliticalOrganizationSummaryY2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allSheet0702dto     様式7その2収入の集計
     * @param allSheet0713dto     様式7その13支出の集計
     * @param allSheet0717dto     様式7その17資産の集計
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0702SummaryTableIncomeDto allSheet0702dto,
            final AllSheet0713ListOfExpenditureItemsDto allSheet0713dto,
            final AllSheet0717SummaryTableOfAssetsDto allSheet0717dto, final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getHoukokuNen()) {
            // 2022年
            case YEAR_2022:
                size = insertPoliticalOrganizationSummaryY2022Logic.practice(documentCode, documentPropertyDto,
                        allSheet0702dto, allSheet0713dto, allSheet0717dto, checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                size = insertPoliticalOrganizationSummaryY2024Logic.practice(documentCode, documentPropertyDto,
                        allSheet0702dto, allSheet0713dto, allSheet0717dto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPoliticalOrganizationSummaryY2025Logic.practice(documentCode, documentPropertyDto,
                        allSheet0702dto, allSheet0713dto, allSheet0717dto, checkPrivilegeDto);
                break;

            // NOTE:Logic実行追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + documentPropertyDto.getHoukokuNen());
        }

        return size;

    }
}
