package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.UpdatePoliticalOrganizationEstateAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.UpdatePoliticalOrganizationEstateAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.UpdatePoliticalOrganizationEstateAllY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.UpdatePoliticalOrganizationEstateAllY2023Logic;
// importを追加

/**
 * 政治資金収支報告書の資産明細の最新データを履歴データにする
 */
@Component
public class UpdatePoliticalOrganizationEstateAllLogic {

    // フィールドテンプレート始まり
    /** 2022年固定文字 */
    private static final int YEAR_2022 = 2022;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired // 2022
    private UpdatePoliticalOrganizationEstateAllY2022Logic updatePoliticalOrganizationEstateAllY2022Logic;

    /** 2024年固定文字 */
    private static final int YEAR_2024 = 2024;
    /** 最新を履歴に変換するLogic2024年 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2024Logic updatePoliticalOrganizationEstateAllY2024Logic;

    /** 2025年固定文字 */
    private static final int YEAR_2025 = 2025;
    /** 最新を履歴に変換するLogic2025年 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2025Logic updatePoliticalOrganizationEstateAllY2025Logic;

    /** 2023年固定文字 */
    private static final int YEAR_2023 = 2023;
    /** 最新を履歴に変換するLogic2023年 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2023Logic updatePoliticalOrganizationEstateAllY2023Logic;

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
                result = updatePoliticalOrganizationEstateAllY2022Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2024:
                result = updatePoliticalOrganizationEstateAllY2024Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2025:
                result = updatePoliticalOrganizationEstateAllY2025Logic.practice(oldCode, checkPrivilegeDto);
                break;

            case YEAR_2023:
                result = updatePoliticalOrganizationEstateAllY2023Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return result;
    }

}
