package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 新規のタスク予定を追加する
 */
@Component
public class InsertTaskPlanY2025Logic {

    /** タスク計画Repository(2025年) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /**
     * 挿入作業を行う
     *
     * @param privilegeDto 権限確認Dto
     * @param listTask     タスクリスト
     * @param listUser     対象ユーザリスト
     * @return 挿入行数
     */
    public int practice(final CheckPrivilegeDto privilegeDto, final List<TaskInfoEntity> listTask,
            final List<UserWebAccessEntity> listUser) {

        List<TaskPlan2025Entity> list = new ArrayList<>();
        for (TaskInfoEntity taskEntity : listTask) {
            list.add(this.createEntity(privilegeDto, taskEntity, listUser));
        }

        // 同一識別コードの設定
        Integer code = 0;
        Optional<TaskPlan2025Entity> optinoal = taskPlan2025Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optinoal.isEmpty()) {
            code = optinoal.get().getTaskPlanCode();
        }
        for (TaskPlan2025Entity entity : list) {
            code++;
            entity.setTaskPlanCode(code);
        }

        return taskPlan2025Repository.saveAll(list).size();
    }

    private TaskPlan2025Entity createEntity(final CheckPrivilegeDto privilegeDto, final TaskInfoEntity taskInfoEntity,
            final List<UserWebAccessEntity> listUser) {

        TaskPlan2025Entity entity = new TaskPlan2025Entity();

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        // タスクの内容関連
        entity.setIsFinished(false); // タスクが未処理指定なのでfalse固定

        entity.setTaskPlanName(taskInfoEntity.getTaskInfoName());
        entity.setListUserCode(taskInfoEntity.getTaskLevelList());
        // 遷移するURLと指定するqueryを連結して格納
        entity.setTransferPass(taskInfoEntity.getTransferPass() + taskInfoEntity.getParamQuery());

        // 操作中の政治団体は権限確認Dtoからもらう
        entity.setPoliticalOrganizationId(privilegeDto.getPoliticalOrganizationId());
        entity.setPoliticalOrganizationCode(privilegeDto.getPoliticalOrganizationCode());
        entity.setPoliticalOrganizationName(privilegeDto.getPoliticalOrganizationName());

        entity.setTaskPlanId(0L); // autoincrement(明記)

        return entity;
    }

}
