package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckAllreadyRegistDataPoliticalOrganizationY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckAllreadyRegistDataY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckAllreadyRegistDataY2025Logic;

/**
 * すでに登録済の場合は表紙、誓約書の文書同一識別コードのリストを返す 政治団体コード、提出日が同じものが同じ文書
 */
@Component
public class CheckAllreadyRegistDataPoliticalOrganizationLogic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 検証対応年(2022)Logic */
    @Autowired
    private CheckAllreadyRegistDataPoliticalOrganizationY2022Logic checkAllreadyRegistDataPoliticalOrganizationY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 検証対応年(2024)Logic */
    @Autowired
    private CheckAllreadyRegistDataY2024Logic checkAllreadyRegistDataY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 検証対応年(2025)Logic */
    @Autowired
    private CheckAllreadyRegistDataY2025Logic checkAllreadyRegistDataY2025Logic;

    /**
     * 確認作業を行う
     *
     * @param documentPropertyDto 文書属性Dto
     * @return 文書同一識別コードのリスト
     */
    public List<Long> practice(final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto) {

        List<Long> list = new ArrayList<>();

        switch (documentPropertyDto.getHoukokuNen()) {
            // 2022年
            case YEAR_2022:
                list.addAll(checkAllreadyRegistDataPoliticalOrganizationY2022Logic.practice(documentPropertyDto));
                break;

            // 2024年
            case YEAR_2024:
                list.addAll(checkAllreadyRegistDataY2024Logic.practice(documentPropertyDto));
                break;

            // 2025年
            case YEAR_2025:
                list.addAll(checkAllreadyRegistDataY2025Logic.practice(documentPropertyDto));
                break;

            // NOTE:Logic実行追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + documentPropertyDto.getHoukokuNen());
        }

        return list;
    }

}
