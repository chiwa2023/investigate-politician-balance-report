package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckAllreadyRegistDataPoliticalOrganizationY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckAllreadyRegistDataPoliticalOrganizationY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckAllreadyRegistDataPoliticalOrganizationY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckAllreadyRegistDataPoliticalOrganizationY2023Logic;
// importを追加

/**
 * すでに登録済の場合は表紙、誓約書の文書同一識別コードのリストを返す 政治団体コード、提出日が同じものが同じ文書
 */
@Component
public class CheckAllreadyRegistDataPoliticalOrganizationLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 検証対応年(2022)Logic */
    @Autowired // 2022
    private CheckAllreadyRegistDataPoliticalOrganizationY2022Logic checkAllreadyRegistDataPoliticalOrganizationY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 検証対応年(2024)Logic */
    @Autowired
    private CheckAllreadyRegistDataPoliticalOrganizationY2024Logic checkAllreadyRegistDataPoliticalOrganizationY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 検証対応年(2025)Logic */
    @Autowired
    private CheckAllreadyRegistDataPoliticalOrganizationY2025Logic checkAllreadyRegistDataPoliticalOrganizationY2025Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 検証対応年(2023)Logic */
    private CheckAllreadyRegistDataPoliticalOrganizationY2023Logic checkAllreadyRegistDataPoliticalOrganizationY2023Logic;

    // フィールドの追加位置

    /**
     * 確認作業を行う
     *
     * @param documentPropertyDto 文書属性Dto
     * @return 文書同一識別コードのリスト
     */
    public List<Long> practice(final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto) {

        List<Long> list = new ArrayList<>();

        switch (documentPropertyDto.getHoukokuNen()) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                list.addAll(checkAllreadyRegistDataPoliticalOrganizationY2022Logic.practice(documentPropertyDto));
                break; // 2022

            // 2024年
            case YEAR_2024:
                list.addAll(checkAllreadyRegistDataPoliticalOrganizationY2024Logic.practice(documentPropertyDto));
                break;

            // 2025年
            case YEAR_2025:
                list.addAll(checkAllreadyRegistDataPoliticalOrganizationY2025Logic.practice(documentPropertyDto));
                break;

            // 2023年
            case YEAR_2023:
                list.addAll(checkAllreadyRegistDataPoliticalOrganizationY2023Logic.practice(documentPropertyDto));
                break; // 2023

            // caseの追加位置

            default:
                break;
        }

        return list;
    }

}
