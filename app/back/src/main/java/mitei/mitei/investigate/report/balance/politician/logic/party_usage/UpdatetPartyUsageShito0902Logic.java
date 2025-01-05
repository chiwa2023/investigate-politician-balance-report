package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.UpdatePartyUsageShito0902Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.UpdatePartyUsageShito0902Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.UpdatePartyUsageShito0902Y2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2023.UpdatePartyUsageShito0902Y2023Logic;
// importを追加

/**
 * 様式9その2更新Logic
 */
@Component
public class UpdatetPartyUsageShito0902Logic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private UpdatePartyUsageShito0902Y2022Logic updatePartyUsageShito0902Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private UpdatePartyUsageShito0902Y2024Logic updatePartyUsageShito0902Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private UpdatePartyUsageShito0902Y2025Logic updatePartyUsageShito0902Y2025Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    private UpdatePartyUsageShito0902Y2023Logic updatePartyUsageShito0902Y2023Logic;

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
                result = updatePartyUsageShito0902Y2022Logic.practice(oldCode, checkPrivilegeDto);
                break; // 2022

            // 2024年
            case YEAR_2024:
                result = updatePartyUsageShito0902Y2024Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                result = updatePartyUsageShito0902Y2025Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // 2023年
            case YEAR_2023:
                result = updatePartyUsageShito0902Y2023Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return result;
    }

}
