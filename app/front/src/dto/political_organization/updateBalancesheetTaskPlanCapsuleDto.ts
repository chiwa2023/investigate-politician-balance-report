import AbstactCapsuleDto from "../abstactCapsuleDto";
import TaskPlanBalancesheetDetailEntity from "../../entity/taskPlanBalancesheetDetailEntity"

export default interface UpdateBalancesheetTaskPlanCapsuleInterface {

}


export default class UpdateBalancesheetTaskPlanCapsuleDto extends AbstactCapsuleDto implements UpdateBalancesheetTaskPlanCapsuleInterface {

    /** 政治資金収支報告書一括処理実行予定Entity  */
    taskPlanBalancesheetDetailEntity: TaskPlanBalancesheetDetailEntity;

    constructor() {
        super();
        this.taskPlanBalancesheetDetailEntity = new TaskPlanBalancesheetDetailEntity();

    }
}