export default interface FukisaiSearchConditionInterface {

}

export default class FukisaiSearchConditionDto implements FukisaiSearchConditionInterface {

    /** 報告年 */
    houkokuNen: number;

    /** 政治団体Id */
    poliOrgId: number;

    /** 政治団体同一識別コード */
    poliOrgCode: number;

    /** 政治団体名称 */
    poliOrgName: string;

    /** 政治団体原文書名 */
    dantaiName: string;

    /** 検索方式コード該否 */
    isSearchCode: boolean;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.houkokuNen = INIT_NUMBER;
        this.poliOrgId = INIT_NUMBER;
        this.poliOrgCode = INIT_NUMBER;
        this.poliOrgName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.isSearchCode = INIT_BOOLEAN;

    }

}