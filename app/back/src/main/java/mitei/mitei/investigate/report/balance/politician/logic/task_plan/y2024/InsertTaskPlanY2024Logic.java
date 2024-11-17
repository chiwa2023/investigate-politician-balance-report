package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 新規のタスク予定を追加する
 */
@Component
public class InsertTaskPlanY2024Logic {

    /** タスク計画Repository(2024年) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    /**
     * 挿入作業を行う
     *
     * @param privilegeDto 権限確認Dto
     * @param listTask     タスクリスト
     * @return 挿入行数
     */
    public int practice(final CheckPrivilegeDto privilegeDto, final List<TaskInfoEntity> listTask) {

        final String INIT_STRING = "";

        List<TaskPlan2024Entity> list = new ArrayList<>();
        boolean isAdd;
        for (TaskInfoEntity taskEntity : listTask) {
            isAdd = true;
            // ページへの接続クエリがない場合、全ユーザ統一ページである
            // その場合、すでにタスク予定が存在している場合は、2重にタスクを追加する必要がない
            if (INIT_STRING.equals(taskEntity.getParamQuery())) {
                isAdd = taskPlan2024Repository.findBySaishinKbnAndTaskPlanName(
                        DataHistoryStatusConstants.INSERT.value(), taskEntity.getTaskInfoName()).isEmpty();
            }

            if (isAdd) {
                list.add(this.createEntity(privilegeDto, taskEntity));
            }
        }

        // 同一識別コードの設定
        Integer code = 0;
        Optional<TaskPlan2024Entity> optinoal = taskPlan2024Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optinoal.isEmpty()) {
            code = optinoal.get().getTaskPlanCode();
        }
        for (TaskPlan2024Entity entity : list) {
            code++;
            entity.setTaskPlanCode(code);
        }

        return taskPlan2024Repository.saveAll(list).size();
    }

    private TaskPlan2024Entity createEntity(final CheckPrivilegeDto privilegeDto, final TaskInfoEntity taskInfoEntity) {

        TaskPlan2024Entity entity = new TaskPlan2024Entity();

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

        return entity;
    }

}
