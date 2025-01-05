package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0718AssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0719RealEstateDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.InsertPoliticalOrganizationEstateAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.InsertPoliticalOrganizationEstateAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.InsertPoliticalOrganizationEstateAllY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.InsertPoliticalOrganizationEstateAllY2023Logic;
// importを追加

/**
 * 政治資金収支報告書の資産明細を登録する
 * 悲観テーブルロックの時間をできうる限り短くするため、単純ロジック数多く繰り返しているだけで複雑度上がっていることを加味して、
 * すべての資産データ登録をこのLogicに集めた
 */
@Component
public class InsertPoliticalOrganizationEstateAllLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private InsertPoliticalOrganizationEstateAllY2022Logic insertPoliticalOrganizationEstateAllY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPoliticalOrganizationEstateAllY2024Logic insertPoliticalOrganizationEstateAllY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPoliticalOrganizationEstateAllY2025Logic insertPoliticalOrganizationEstateAllY2025Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    private InsertPoliticalOrganizationEstateAllY2023Logic insertPoliticalOrganizationEstateAllY2023Logic;

    // フィールドの追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode              文書同一識別コード
     * @param documentPropertyDto       文書属性Dto
     * @param allSheet0718AssetsDto     様式7その18資産Dto
     * @param allSheet0719RealEstateDto 様式7その19不動産Dto
     * @param checkPrivilegeDto         権限Dto
     * @return このロジックで登録された行数
     */
    public int practice(final Long documentCode, // SUPPRESS CHECKSTYLE NOPMD
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0718AssetsDto allSheet0718AssetsDto,
            final AllSheet0719RealEstateDto allSheet0719RealEstateDto, final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getHoukokuNen()) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                size = insertPoliticalOrganizationEstateAllY2022Logic.practice(documentCode, documentPropertyDto,
                        allSheet0718AssetsDto, allSheet0719RealEstateDto, checkPrivilegeDto);
                break;
            // 2024年
            case YEAR_2024:
                size = insertPoliticalOrganizationEstateAllY2024Logic.practice(documentCode, documentPropertyDto,
                        allSheet0718AssetsDto, allSheet0719RealEstateDto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPoliticalOrganizationEstateAllY2025Logic.practice(documentCode, documentPropertyDto,
                        allSheet0718AssetsDto, allSheet0719RealEstateDto, checkPrivilegeDto);
                break;

            // 2023年
            case YEAR_2023:
                size = insertPoliticalOrganizationEstateAllY2023Logic.practice(documentCode, documentPropertyDto,
                        allSheet0718AssetsDto, allSheet0719RealEstateDto, checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return size;
    }

}
