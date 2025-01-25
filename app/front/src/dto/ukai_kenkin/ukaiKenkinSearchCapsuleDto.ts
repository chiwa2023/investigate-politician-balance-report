import TemplateWithTaskPlanInfoResultInterface from "../common_check/templateWithTaskPlanInfoResultDto";
import UkaiKenkinConditionCapsuleInterface from "./ukaiKenkinConditionCapsuleDto";
import UkaiKenkinConditionCapsuleDto from "./ukaiKenkinConditionCapsuleDto";

export default interface UkaiKenkinSearchCapsuleInterface {


}

export default class UkaiKenkinSearchCapsuleDto extends TemplateWithTaskPlanInfoResultInterface implements UkaiKenkinSearchCapsuleInterface {

    /** ワークテーブル作成条件 */
    createCapsuleDto: UkaiKenkinConditionCapsuleInterface;

    constructor() {

        super();
        // 初期データ
       this.createCapsuleDto = new UkaiKenkinConditionCapsuleDto();
    }
}