export default class SearchMembershipFeeConditionDto {

    /** 報告年 */
    houkokunen: number;

    /** 政党Code */
    poliPartyCode: string;

    /** 政治団体Id */
    politicalOrgnaizationId: number;
    /** 政治団体同一識別コード */
    politicalOrgnaizationCode: number;
    /** 政治団体名称 */
    politicalOrgnaizationName: string;

    /** 党費月額 */
    feeMonth: number;

    /** 党費年額換算 */
    feeYear: number;

        /** 注意水準 */
        levelAttention: number;

        /** 警告水準 */
        levelWarning: number;
    
    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.houkokunen = INIT_NUMBER;
        this.poliPartyCode = INIT_STRING;
        this.politicalOrgnaizationId = INIT_NUMBER;
        this.politicalOrgnaizationCode = INIT_NUMBER;
        this.politicalOrgnaizationName = INIT_STRING;
        this.feeYear = 1; // 年額または月額どちらかは0でない必要がある
        this.feeMonth = INIT_NUMBER;

        this.levelAttention = INIT_NUMBER;
        this.levelWarning = INIT_NUMBER;

    }

}