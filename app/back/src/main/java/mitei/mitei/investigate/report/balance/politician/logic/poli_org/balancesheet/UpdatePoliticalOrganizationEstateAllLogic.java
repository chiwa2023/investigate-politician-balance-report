package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.UpdatePoliticalOrganizationEstateAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.UpdatePoliticalOrganizationEstateAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.UpdatePoliticalOrganizationEstateAllY2025Logic;

/**
 * 政治資金収支報告書の資産明細の最新データを履歴データにする
 */
@Component
public class UpdatePoliticalOrganizationEstateAllLogic {

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2022 = 2022;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2022Logic updatePoliticalOrganizationEstateAllY2022Logic;

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2024 = 2024;
    /** 最新を履歴に変換するLogic2024年 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2024Logic updatePoliticalOrganizationEstateAllY2024Logic;

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2025 = 2025;
    /** 最新を履歴に変換するLogic2025年 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2025Logic updatePoliticalOrganizationEstateAllY2025Logic;

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
                result = updatePoliticalOrganizationEstateAllY2022Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2024:
                result = updatePoliticalOrganizationEstateAllY2024Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2025:
                result = updatePoliticalOrganizationEstateAllY2025Logic.practice(oldCode, checkPrivilegeDto);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

        return result;
    }

}
