package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.UpdatePoliticalOrganizationOutcomeAllY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.UpdatePoliticalOrganizationOutcomeAllY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.UpdatePoliticalOrganizationOutcomeAllY2025Logic;

/**
 * 政治資金収支報告書の支出の最新データを履歴データにする
 */
@Component
public class UpdatePoliticalOrganizationOutcomeAllLogic {

    
    
    /** 最新を履歴に変換するLogic2022年 */
    private static final int YEAR_2022 = 2022;
    /** 最新を履歴に変換するLogic2022年 */
    @Autowired
    private UpdatePoliticalOrganizationOutcomeAllY2022Logic updatePoliticalOrganizationOutcomeAllY2022Logic;
    

    /** 最新を履歴に変換するLogic2024年 */
    private static final int YEAR_2024 = 2024;
    /** 最新を履歴に変換するLogic2024年 */
    @Autowired
    private UpdatePoliticalOrganizationOutcomeAllY2024Logic updatePoliticalOrganizationOutcomeAllY2024Logic;

    /** 最新を履歴に変換するLogic2025年 */
    private static final int YEAR_2025 = 2025;
    /** 最新を履歴に変換するLogic2025年 */
    @Autowired
    private UpdatePoliticalOrganizationOutcomeAllY2025Logic updatePoliticalOrganizationOutcomeAllY2025Logic;

    
    
    /**
     * 最新データを履歴データにする
     *
     * @param houkokuNen 報告年
     * @param oldCode 更新すべき文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新件数
     */
    public int practice(final int houkokuNen, final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        int result = 0;
        
        switch (houkokuNen) {
            case YEAR_2022:
                result = updatePoliticalOrganizationOutcomeAllY2022Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2024:
                result = updatePoliticalOrganizationOutcomeAllY2024Logic.practice(oldCode, checkPrivilegeDto);
                break;
            case YEAR_2025:
                result = updatePoliticalOrganizationOutcomeAllY2025Logic.practice(oldCode, checkPrivilegeDto);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

        return result;
    }

}
