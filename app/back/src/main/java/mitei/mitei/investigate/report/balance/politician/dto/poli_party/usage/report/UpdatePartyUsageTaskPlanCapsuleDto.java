package mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;

/**
 * 政治資金収支報告書一括処理計画更新Dto
 */
public class UpdatePartyUsageTaskPlanCapsuleDto extends AbstractCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 政党交付金使途報告書タスク予定Entiy */
    private TaskPlanPartyUsageDetailEntity taskPlanPartyUsageDetailEntity = new TaskPlanPartyUsageDetailEntity();

    /**
     * 政党交付金使途報告書タスク予定Entiyを取得する
     *
     * @return 政党交付金使途報告書タスク予定Entiy
     */
    public TaskPlanPartyUsageDetailEntity getTaskPlanPartyUsageDetailEntity() {
        return taskPlanPartyUsageDetailEntity;
    }

    /**
     * 政党交付金使途報告書タスク予定Entiyを設定する
     *
     * @param taskPlanPartyUsageDetailEntity 政党交付金使途報告書タスク予定Entiy
     */
    public void setTaskPlanPartyUsageDetailEntity(final TaskPlanPartyUsageDetailEntity taskPlanPartyUsageDetailEntity) {
        this.taskPlanPartyUsageDetailEntity = taskPlanPartyUsageDetailEntity;
    }

}
