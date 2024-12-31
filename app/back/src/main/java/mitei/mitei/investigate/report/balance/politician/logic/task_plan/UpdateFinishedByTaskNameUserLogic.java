package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.UpdateFinishedByTaskNameUserY2024Logic;

/**
 * タスク計画に作業終了フラグを立てる
 */
@Component
public class UpdateFinishedByTaskNameUserLogic {

    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private UpdateFinishedByTaskNameUserY2024Logic updateFinishedByTaskNameUserY2024Logic;

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
            case YEAR_2024:
                return updateFinishedByTaskNameUserY2024Logic.practice(taskPlanCode, privilegeDto, taskName, year);
            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
