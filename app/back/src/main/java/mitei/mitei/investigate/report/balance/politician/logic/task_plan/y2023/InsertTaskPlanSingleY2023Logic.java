package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 1行だけタスク計画を登録する(2023年)
 */
@Component
public class InsertTaskPlanSingleY2023Logic {

    /** タスク計画Repository(2023) */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

    /**
     * 処理を行う
     *
     * @param taskInfoEntity タスク情報Entity
     * @param privilegeDto   権限確認Dto
     * @return 登録タスク計画同一識別コード
     */
    public int practice(final TaskInfoEntity taskInfoEntity, final CheckPrivilegeDto privilegeDto) {

        TaskPlan2023Entity entity = new TaskPlan2023Entity();

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        // タスクの内容関連
        entity.setIsFinished(false); // タスクが未処理指定なのでfalse固定

        entity.setTaskPlanName(taskInfoEntity.getTaskInfoName());
        entity.setTaskLevelList(taskInfoEntity.getTaskLevelList());

        // 遷移するURLと指定するqueryを連結して格納
        entity.setTransferPass(taskInfoEntity.getTransferPass() + taskInfoEntity.getParamQuery());

        // 操作中の政治団体は権限確認Dtoからもらう
        entity.setPoliticalOrganizationId(privilegeDto.getPoliticalOrganizationId());
        entity.setPoliticalOrganizationCode(privilegeDto.getPoliticalOrganizationCode());
        entity.setPoliticalOrganizationName(privilegeDto.getPoliticalOrganizationName());

        entity.setTaskPlanId(0L); // autoincrement(明記)

        Integer code = 1;
        Optional<TaskPlan2023Entity> optional = taskPlan2023Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getTaskPlanCode();
        }
        entity.setTaskPlanCode(code);

        return taskPlan2023Repository.save(entity).getTaskPlanCode();
    }

}
