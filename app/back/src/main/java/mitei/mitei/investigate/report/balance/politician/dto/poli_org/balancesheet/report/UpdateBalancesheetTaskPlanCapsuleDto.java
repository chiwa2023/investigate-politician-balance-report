package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;

/**
 * 政治資金収支報告書一括処理計画更新Dto
 */
public class UpdateBalancesheetTaskPlanCapsuleDto extends AbstractCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 政治資金収支報告書タスク予定Entiy */
    private TaskPlanBalancesheetDetailEntity taskPlanBalancesheetDetailEntity = new TaskPlanBalancesheetDetailEntity();

    /**
     * 政治資金収支報告書タスク予定Entiyを取得する
     *
     * @return 政治資金収支報告書タスク予定Entiy
     */
    public TaskPlanBalancesheetDetailEntity getTaskPlanBalancesheetDetailEntity() {
        return taskPlanBalancesheetDetailEntity;
    }

    /**
     * 政治資金収支報告書タスク予定Entiyを設定する
     *
     * @param taskPlanBalancesheetDetailEntity 政治資金収支報告書タスク予定Entiy
     */
    public void setTaskPlanBalancesheetDetailEntity(
            final TaskPlanBalancesheetDetailEntity taskPlanBalancesheetDetailEntity) {
        this.taskPlanBalancesheetDetailEntity = taskPlanBalancesheetDetailEntity;
    }

}
