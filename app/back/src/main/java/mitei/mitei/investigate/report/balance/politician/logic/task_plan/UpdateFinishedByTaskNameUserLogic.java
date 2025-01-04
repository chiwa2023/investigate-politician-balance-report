package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.UpdateFinishedByTaskNameUserY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025.UpdateFinishedByTaskNameUserY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023.UpdateFinishedByTaskNameUserY2023Logic;
// importを追加

/**
 * タスク計画に作業終了フラグを立てる
 */
@Component
public class UpdateFinishedByTaskNameUserLogic {

    // フィールドテンプレート始まり
    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private UpdateFinishedByTaskNameUserY2024Logic updateFinishedByTaskNameUserY2024Logic;

    /** 2025年 */
    private static final int YEAR_2025 = 2025;
    /** 2025年Loigc */
    @Autowired // 2025
    private UpdateFinishedByTaskNameUserY2025Logic updateFinishedByTaskNameUserY2025Logic;

    /** 2023年 */
    private static final int YEAR_2023 = 2023;
    /** 2023年Loigc */
    @Autowired // 2023
    private UpdateFinishedByTaskNameUserY2023Logic updateFinishedByTaskNameUserY2023Logic;

    // フィールドの追加位置

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画同一識別コード
     * @param privilegeDto 権限確認Dto
     * @param taskName     タスク名
     * @param year         タスク処理年
     * @return 処理完了該否
     */
    public int practice(final Integer taskPlanCode, final CheckPrivilegeDto privilegeDto, final String taskName,
            final Integer year) {

        switch (year) {
            // caseテンプレート始まり
            // 2024年
            case YEAR_2024:
                return updateFinishedByTaskNameUserY2024Logic.practice(taskPlanCode, privilegeDto, taskName, year);

            // 2025年
            case YEAR_2025:
                return updateFinishedByTaskNameUserY2025Logic.practice(taskPlanCode, privilegeDto, taskName, year);

            // 2023年
            case YEAR_2023:
                return updateFinishedByTaskNameUserY2023Logic.practice(taskPlanCode, privilegeDto, taskName, year);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
