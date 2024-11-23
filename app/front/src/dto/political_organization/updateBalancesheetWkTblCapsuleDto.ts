import AbstactCapsuleDto from "../abstactCapsuleDto";
import WkTblPoliOrgBalancesheetReportEntity from "../../entity/wkTblPoliOrgBalancesheetReportEntity"

export default interface UpdateBalancesheetWkTblCapsuleInterface {

}

export default class UpdateBalancesheetWkTblCapsuleDto extends AbstactCapsuleDto implements UpdateBalancesheetWkTblCapsuleInterface {

    /** 政治資金収支報告書一括処理ワークテーブルEntity  */
    wkTblPoliOrgBalancesheetReportEntity: WkTblPoliOrgBalancesheetReportEntity;

    constructor() {
        super();
        this.wkTblPoliOrgBalancesheetReportEntity = new WkTblPoliOrgBalancesheetReportEntity();

    }

}