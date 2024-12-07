package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.UpdatetPoliticalOrganizationSheet0701And0720Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.UpdatetPoliticalOrganizationSheet0701And0720Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.UpdatetPoliticalOrganizationSheet0701And0720Y2025Logic;

/**
 * 政治資金収支報告書の表紙、宣誓書、文書属性の最新データを履歴データにする
 */
@Component
public class UpdatePoliticalOrganizationSheet0701And0720Logic {

    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2022 = 2022;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired
    private UpdatetPoliticalOrganizationSheet0701And0720Y2022Logic updatetPoliticalOrganizationSheet0701And0720Y2022Logic;

    /** 最新を履歴に変換するLogic2024年 */
    private static final int YEAR_2024 = 2024;
    /** 最新を履歴に変換するLogic2024年 */
    @Autowired
    private UpdatetPoliticalOrganizationSheet0701And0720Y2024Logic updatetPoliticalOrganizationSheet0701And0720Y2024Logic;

    /** 最新を履歴に変換するLogic2025年 */
    private static final int YEAR_2025 = 2025;
    /** 最新を履歴に変換するLogic2025年 */
    @Autowired
    private UpdatetPoliticalOrganizationSheet0701And0720Y2025Logic updatetPoliticalOrganizationSheet0701And0720Y2025Logic;

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
                result = updatetPoliticalOrganizationSheet0701And0720Y2022Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2024:
                result = updatetPoliticalOrganizationSheet0701And0720Y2024Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2025:
                result = updatetPoliticalOrganizationSheet0701And0720Y2025Logic.practice(oldCode, checkPrivilegeDto);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

        return result;
    }

}
