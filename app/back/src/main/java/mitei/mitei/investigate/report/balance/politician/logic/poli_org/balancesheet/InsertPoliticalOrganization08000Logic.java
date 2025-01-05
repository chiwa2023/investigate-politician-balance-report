package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0800DifficultCollectReceiptDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganization08000Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganization08000Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganization08000Y2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.InsertPoliticalOrganization08000Y2023Logic;
// importを追加

/**
 * 様式8 領収書を徴しがたかった支出項目一覧表登録Logic
 */
@Component
public class InsertPoliticalOrganization08000Logic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private InsertPoliticalOrganization08000Y2022Logic insertPoliticalOrganization08000y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganization08000Y2024Logic insertPoliticalOrganization08000y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganization08000Y2025Logic insertPoliticalOrganization08000Y2025Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    private InsertPoliticalOrganization08000Y2023Logic insertPoliticalOrganization08000Y2023Logic;

    // フィールドの追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 収支報告書文書属性Dto
     * @param difficultReciptDto  様式8 領収書を徴しがたかった支出項目
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0800DifficultCollectReceiptDto difficultReciptDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getHoukokuNen()) {
            
            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                size = insertPoliticalOrganization08000y2022Logic.practice(documentCode, documentPropertyDto,
                        difficultReciptDto, checkPrivilegeDto);
                break;
            // 2024年
            case YEAR_2024:
                size = insertPoliticalOrganization08000y2024Logic.practice(documentCode, documentPropertyDto,
                        difficultReciptDto, checkPrivilegeDto);
                break;
            // 2025年
            case YEAR_2025:
                size = insertPoliticalOrganization08000Y2025Logic.practice(documentCode, documentPropertyDto,
                        difficultReciptDto, checkPrivilegeDto);
                break;

            // 2023年
            case YEAR_2023:
                size = insertPoliticalOrganization08000Y2023Logic.practice(documentCode, documentPropertyDto,
                        difficultReciptDto, checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return size;
    }
}
