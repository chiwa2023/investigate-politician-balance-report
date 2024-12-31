package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.CallTaskPlanByCodeUserY2024Logic;

/**
 * 同一識別コードと名称からタスク計画を取得する
 */
@Component
public class CallTaskPlanByCodeUserLogic {

    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private CallTaskPlanByCodeUserY2024Logic callTaskPlanByCodeUserY2024Logic;

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
            // 2024年
            case YEAR_2024:
                return callTaskPlanByCodeUserY2024Logic.practice(taskPlanCode, userCode, taskName, year);
            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }
}
