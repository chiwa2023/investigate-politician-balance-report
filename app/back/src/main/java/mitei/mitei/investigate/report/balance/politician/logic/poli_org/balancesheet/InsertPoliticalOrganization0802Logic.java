package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0802WithdrawalItemsByTransferDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganization0802Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganization0802Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganization0802Y2025Logic;

/**
 * 様式08その2 支出項目別金額の内訳
 */
@Component
public class InsertPoliticalOrganization0802Logic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPoliticalOrganization0802Y2022Logic insertPoliticalOrganization0802Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganization0802Y2024Logic insertPoliticalOrganization0802Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganization0802Y2025Logic insertPoliticalOrganization0802Y2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param itemTransfer0802Dto 様式8その2Dto
     * @param checkPrivilegeDto   権限Dto
     * @return 登録行数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0802WithdrawalItemsByTransferDto itemTransfer0802Dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getHoukokuNen()) {
            // 2022年
            case YEAR_2022:
                size = insertPoliticalOrganization0802Y2022Logic.practice(documentCode, documentPropertyDto,
                        itemTransfer0802Dto, checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                size = insertPoliticalOrganization0802Y2024Logic.practice(documentCode, documentPropertyDto,
                        itemTransfer0802Dto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPoliticalOrganization0802Y2025Logic.practice(documentCode, documentPropertyDto,
                        itemTransfer0802Dto, checkPrivilegeDto);
                break;

            // NOTE:Logic実行追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + documentPropertyDto.getHoukokuNen());
        }

        return size;

    }

}
