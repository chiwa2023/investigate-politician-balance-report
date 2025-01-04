package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.EraseWkTblPersonalTaskY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025.EraseWkTblPersonalTaskY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023.EraseWkTblPersonalTaskY2023Logic;
// importを追加

/**
 * 指定したタスクを履歴にする
 */
@Component
public class EraseWkTblPersonalTaskLogic {

    // フィールドテンプレート始まり
    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private EraseWkTblPersonalTaskY2024Logic eraseWkTblPersonalTaskY2024Logic;

    /** 2025年 */
    private static final int YEAR_2025 = 2025;
    /** 2025年Loigc */
    @Autowired // 2025
    private EraseWkTblPersonalTaskY2025Logic eraseWkTblPersonalTaskY2025Logic;

    /** 2023年 */
    private static final int YEAR_2023 = 2023;
    /** 2023年Loigc */
    @Autowired // 2023
    private EraseWkTblPersonalTaskY2023Logic eraseWkTblPersonalTaskY2023Logic;

    // フィールドの追加位置

    /**
     * 処理を行う
     *
     * @param listTaskName タスク名
     * @param privilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final int year, final List<String> listTaskName, final CheckPrivilegeDto privilegeDto) {

        switch (year) {

            // caseテンプレート始まり
            // 2024年
            case YEAR_2024:
                return eraseWkTblPersonalTaskY2024Logic.practice(listTaskName, privilegeDto);

            // 2025年
            case YEAR_2025:
                return eraseWkTblPersonalTaskY2025Logic.practice(listTaskName, privilegeDto);

            // 2023年
            case YEAR_2023:
                return eraseWkTblPersonalTaskY2023Logic.practice(listTaskName, privilegeDto);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
