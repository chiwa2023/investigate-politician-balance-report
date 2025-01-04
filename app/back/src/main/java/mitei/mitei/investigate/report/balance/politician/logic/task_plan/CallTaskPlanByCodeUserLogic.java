package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.CallTaskPlanByCodeUserY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025.CallTaskPlanByCodeUserY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023.CallTaskPlanByCodeUserY2023Logic;
// importを追加

/**
 * 同一識別コードと名称からタスク計画を取得する
 */
@Component
public class CallTaskPlanByCodeUserLogic {

    // フィールドテンプレート始まり
    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private CallTaskPlanByCodeUserY2024Logic callTaskPlanByCodeUserY2024Logic;

    /** 2025年 */
    private static final int YEAR_2025 = 2025;
    /** 2025年Loigc */
    @Autowired // 2025
    private CallTaskPlanByCodeUserY2025Logic callTaskPlanByCodeUserY2025Logic;

    /** 2023年 */
    private static final int YEAR_2023 = 2023;
    /** 2023年Loigc */
    @Autowired // 2023
    private CallTaskPlanByCodeUserY2023Logic callTaskPlanByCodeUserY2023Logic;

    // フィールドの追加位置

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画同一識別コード
     * @param userCode     ユーザ同一識別コード
     * @param taskName     タスク名
     * @param year         タスク処理年
     * @return 処理完了該否
     */
    public boolean practice(final Integer taskPlanCode, final Integer userCode, final String taskName,
            final Integer year) {

        switch (year) {

            // caseテンプレート始まり
            // 2024年
            case YEAR_2024:
                return callTaskPlanByCodeUserY2024Logic.practice(taskPlanCode, userCode, taskName, year);

            // 2025年
            case YEAR_2025:
                return callTaskPlanByCodeUserY2025Logic.practice(taskPlanCode, userCode, taskName, year);

            // 2023年
            case YEAR_2023:
                return callTaskPlanByCodeUserY2023Logic.practice(taskPlanCode, userCode, taskName, year);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }
}
