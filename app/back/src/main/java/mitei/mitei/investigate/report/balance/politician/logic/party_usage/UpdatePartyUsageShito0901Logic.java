package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.UpdatePartyUsageShito0901Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.UpdatePartyUsageShito0901Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.UpdatePartyUsageShito0901Y2025Logic;

/**
 * 様式9(その1)の更新処理Logic
 */
@Component
public class UpdatePartyUsageShito0901Logic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private UpdatePartyUsageShito0901Y2022Logic updatePartyUsageShito0901Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private UpdatePartyUsageShito0901Y2024Logic updatePartyUsageShito0901Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private UpdatePartyUsageShito0901Y2025Logic updatePartyUsageShito0901Y2025Logic;

    /**
     * 最新データを履歴データにする
     *
     * @param nendo             報告年度
     * @param oldCode           更新すべき文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新件数
     */
    public int practice(final int nendo, final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        int result = 0;

        switch (nendo) {

            // 2022年
            case YEAR_2022:
                result = updatePartyUsageShito0901Y2022Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // 2022年
            case YEAR_2024:
                result = updatePartyUsageShito0901Y2024Logic.practice(oldCode, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                result = updatePartyUsageShito0901Y2025Logic.practice(oldCode, checkPrivilegeDto);
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + nendo);
        }

        return result;
    }

}
