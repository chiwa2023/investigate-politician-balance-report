package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganizationSheet0701And0720Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganizationSheet0701And0720Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganizationSheet0701And0720Y2025Logic;

/**
 * 政治資金収支報告書の表紙、宣誓書、文書属性を保存する
 */
@Component
public class InsertPoliticalOrganizationSheet0701And0720Logic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPoliticalOrganizationSheet0701And0720Y2022Logic insertPoliticalOrganizationSheet0701And0720Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganizationSheet0701And0720Y2024Logic insertPoliticalOrganizationSheet0701And0720Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganizationSheet0701And0720Y2025Logic insertPoliticalOrganizationSheet0701And0720Y2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 登録作業を行う
     *
     * @param documentPropertyDto 政治団体収支報告書属性Dto
     * @param allBookDto          収支報告書全Dto
     * @param checkPrivilegeDto   権限チェックDto
     * @return 同一識別コード
     */
    public Long practice(final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllBookDto allBookDto, final CheckPrivilegeDto checkPrivilegeDto) {

        Long code = 0L;
        switch (documentPropertyDto.getHoukokuNen()) {
            // 2022年
            case YEAR_2022:
                code = insertPoliticalOrganizationSheet0701And0720Y2022Logic.practice(documentPropertyDto, allBookDto,
                        checkPrivilegeDto);
                break;
            // 2024年
            case YEAR_2024:
                code = insertPoliticalOrganizationSheet0701And0720Y2024Logic.practice(documentPropertyDto, allBookDto,
                        checkPrivilegeDto);
                break;
            // 2025年
            case YEAR_2025:
                code = insertPoliticalOrganizationSheet0701And0720Y2025Logic.practice(documentPropertyDto, allBookDto,
                        checkPrivilegeDto);
                break;

            // NOTE:Logic実行追加位置

            default:
                break;
        }

        return code;

    }

}
