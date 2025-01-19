export default interface SouryouKiseiRelationCodeInterface {

}

/**
 * 総量規制データDto
 */
export default class SouryouKiseiRelationCodeDto implements SouryouKiseiRelationCodeInterface {

    /** 合計 */
    sum: number;

    /** 関連者Id */
    relationId: number;

    /** 関連者同一識別コード */
    relationCode: number;

    /** 取引相手名称 */
    partnerName: string;

    /** 取引相手住所 */
    partnerJusho: string;

    /** 上限額 */
    limitAmount: number;

    /** 上限額超え該否 */
    isLimitOver: boolean;

    /** 団体区分 */
    dantaiKbn: string;

    /** 規制算出基準量 */
    kijunAmount: number;

    constructor() {

        // 初期データ
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.sum = INIT_NUMBER;
        this.relationId = INIT_NUMBER;
        this.relationCode = INIT_NUMBER;
        this.partnerName = INIT_STRING;
        this.partnerJusho = INIT_STRING;
        this.limitAmount = INIT_NUMBER;
        this.isLimitOver = INIT_BOOLEAN;
        this.dantaiKbn = INIT_STRING;
        this.kijunAmount = INIT_NUMBER;
    }

}