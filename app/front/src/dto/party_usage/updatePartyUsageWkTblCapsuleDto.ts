import AbstactCapsuleDto from "../abstactCapsuleDto";
import WkTblPoliPartyUsageReportEntity from "../../entity/wkTblPoliPartyUsageReportEntity"

export default interface UpdatePartyUsageWkTblCapsuleInterface {

}

export default class UpdatePartyUsageWkTblCapsuleDto extends AbstactCapsuleDto implements UpdatePartyUsageWkTblCapsuleInterface {

    /** 政治資金収支報告書一括処理ワークテーブルEntity  */
    wkTblPoliPartyUsageReportEntity: WkTblPoliPartyUsageReportEntity;

    constructor() {
        super();
        this.wkTblPoliPartyUsageReportEntity = new WkTblPoliPartyUsageReportEntity();

    }

}