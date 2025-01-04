package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 指定したタスクを履歴にする
 */
@Component
public class EraseWkTblPersonalTaskY2023Logic {

    /** タスク計画Repository(2023) */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

    /**
     * 処理を行う
     *
     * @param listTaskName タスク名
     * @param privilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final List<String> listTaskName, final CheckPrivilegeDto privilegeDto) {

        // 基本的には1件または0件
        List<TaskPlan2023Entity> listPre = taskPlan2023Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), listTaskName, DataHistoryStatusConstants.INSERT.value());

        // 最新データを最新でなくする
        for (TaskPlan2023Entity preEntity : listPre) {
            SetTableDataHistoryUtil.practice(privilegeDto, preEntity, DataHistoryStatusConstants.UPDATE);
        }

        // 最新データをを挿入する
        return taskPlan2023Repository.saveAll(listPre).size();
    }

}
