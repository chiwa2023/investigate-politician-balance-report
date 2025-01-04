package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2023Repository;

/**
 * 同一識別コードと名称からタスク計画を取得する
 */
@Component
public class CallTaskPlanByCodeUserY2023Logic {

    /** タスク計画Repository */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

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

        List<TaskPlan2023Entity> list = taskPlan2023Repository
                .findByTaskPlanCodeAndInsertUserCodeAndTaskPlanNameAndSaishinKbn(taskPlanCode, userCode, taskName,
                        DataHistoryStatusConstants.INSERT.value());

        // 正しくデータ更新できていない場合はfalse
        final int correctSize = 1;
        if (correctSize != list.size()) { //NOPMD
            return false;
        }

        return list.get(0).getIsFinished();
    }

}
