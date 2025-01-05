package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.UpdatePoliticalOrganizationIncomeAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.UpdatePoliticalOrganizationIncomeAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.UpdatePoliticalOrganizationIncomeAllY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.UpdatePoliticalOrganizationIncomeAllY2023Logic;
// importを追加

/**
 * 政治資金収支報告書収入の部の最新データを履歴データにする
 */
@Component
public class UpdatePoliticalOrganizationIncomeAllLogic {

    // フィールドテンプレート始まり
    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2022 = 2022;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired // 2022
    private UpdatePoliticalOrganizationIncomeAllY2022Logic updatePoliticalOrganizationIncomeAllY2022Logic;

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2024 = 2024;
    /** 最新を履歴に変換するLogic2024年 */
    @Autowired
    private UpdatePoliticalOrganizationIncomeAllY2024Logic updatePoliticalOrganizationIncomeAllY2024Logic;

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2025 = 2025;
    /** 最新を履歴に変換するLogic2025年 */
    @Autowired
    private UpdatePoliticalOrganizationIncomeAllY2025Logic updatePoliticalOrganizationIncomeAllY2025Logic;

    /** 固定文字2023年 */
    private static final int YEAR_2023 = 2023;
    /** 最新を履歴に変換するLogic2023年 */
    @Autowired
    private UpdatePoliticalOrganizationIncomeAllY2023Logic updatePoliticalOrganizationIncomeAllY2023Logic;

    // フィールドの追加位置

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

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                result = updatePoliticalOrganizationIncomeAllY2022Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2024:
                result = updatePoliticalOrganizationIncomeAllY2024Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2025:
                result = updatePoliticalOrganizationIncomeAllY2025Logic.practice(oldCode, checkPrivilegeDto);
                break;

            case YEAR_2023:
                result = updatePoliticalOrganizationIncomeAllY2023Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return result;
    }

}
