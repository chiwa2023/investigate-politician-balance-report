package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.UpdatePoliticalOrganizationSummaryY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.UpdatePoliticalOrganizationSummaryY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.UpdatePoliticalOrganizationSummaryY2025Logic;

/**
 * 政治資金収支報告書の集計表(収入・支出・資産)のうち最新データを履歴データする
 */
@Component
public class UpdatePoliticalOrganizationSummaryLogic {

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2022 = 2022;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired
    private UpdatePoliticalOrganizationSummaryY2022Logic updatePoliticalOrganizationSummaryY2022Logic;

    /** 最新を履歴に変換するLogic2024年 */
    private static final int YEAR_2024 = 2024;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired
    private UpdatePoliticalOrganizationSummaryY2024Logic updatePoliticalOrganizationSummaryY2024Logic;

    /** 最新を履歴に変換するLogic2025年 */
    private static final int YEAR_2025 = 2025;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired
    private UpdatePoliticalOrganizationSummaryY2025Logic updatePoliticalOrganizationSummaryY2025Logic;

    /**
     * 最新データを履歴データにする
     *
     * @param houkokuNen        報告年
     * @param oldCode           更新すべき文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新件数
     */
    public int practice(final int houkokuNen, final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        int result = 0;

        switch (houkokuNen) {
            case YEAR_2022:
                result = updatePoliticalOrganizationSummaryY2022Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2024:
                result = updatePoliticalOrganizationSummaryY2024Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2025:
                result = updatePoliticalOrganizationSummaryY2025Logic.practice(oldCode, checkPrivilegeDto);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

        return result;
    }

}
