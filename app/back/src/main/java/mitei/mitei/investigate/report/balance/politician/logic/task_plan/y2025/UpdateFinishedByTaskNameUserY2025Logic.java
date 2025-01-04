package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * タスク計画に作業終了フラグを立てる
 */
@Component
public class UpdateFinishedByTaskNameUserY2025Logic {

    /** タスク計画Repository */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

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

        List<TaskPlan2025Entity> list = taskPlan2025Repository
                .findByTaskPlanCodeAndInsertUserCodeAndTaskPlanNameAndSaishinKbn(taskPlanCode,
                        privilegeDto.getLoginUserCode(), taskName, DataHistoryStatusConstants.INSERT.value());

        // 正しくデータ更新できていない場合は処理中断
        final int correctSize = 1;
        if (list.size() != correctSize) {
            return 0;
        }

        // 元データを履歴に
        TaskPlan2025Entity entity = list.get(0);
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        taskPlan2025Repository.save(entity);

        // 処理完了にした新しいデータを積み上げ
        TaskPlan2025Entity entityNew = new TaskPlan2025Entity();
        BeanUtils.copyProperties(entity, entityNew);
        SetTableDataHistoryUtil.practice(privilegeDto, entityNew, DataHistoryStatusConstants.INSERT);
        entityNew.setIsFinished(true);
        entityNew.setTaskPlanId(0L); // auto_increment

        taskPlan2025Repository.save(entityNew);

        return 1;
    }

}
