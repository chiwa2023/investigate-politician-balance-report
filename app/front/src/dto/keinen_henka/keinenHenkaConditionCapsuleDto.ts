import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface KeinenHenkaConditionCapsuleInterface {

}

export default class KeinenHenkaConditionCapsuleDto extends AbstractCapsuleDto implements KeinenHenkaConditionCapsuleInterface {

    /** 政治団体同一識別Id */
    poliOrgId: number;

    /** 政治団体同一識別コード */
    poliOrgCode: number;

    /** 政治団体同一識別名称 */
    poliOrgName: string;

    /** 検索開始報告年 */
    houkokuNenStart: number;

    /** 検索終了報告年 */
    houkokuNenEnd: number;


    constructor() {
        
        super();

        // 初期データ
        const INIT_NUMBER = 0;
        const INIT_STRING = "";

        this.poliOrgId = INIT_NUMBER;
        this.poliOrgCode = INIT_NUMBER;
        this.poliOrgName = INIT_STRING;
        this.houkokuNenStart = INIT_NUMBER;
        this.houkokuNenEnd = INIT_NUMBER;
    }

}