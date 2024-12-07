import AbstactCapsuleDto from "../abstactCapsuleDto";
import TaskPlanPartyUsageDetailEntity from "../../entity/taskPlanPartyUsageDetailEntity"

export default interface UpdatePartyUsageTaskPlanCapsuleInterface {

}


export default class UpdatePartyUsageTaskPlanCapsuleDto extends AbstactCapsuleDto implements UpdatePartyUsageTaskPlanCapsuleInterface {

    /** 政治資金収支報告書一括処理実行予定Entity  */
    taskPlanPartyUsageDetailEntity: TaskPlanPartyUsageDetailEntity;

    constructor() {
        super();
        this.taskPlanPartyUsageDetailEntity = new TaskPlanPartyUsageDetailEntity();

    }
}