export default interface KobetsuKiseiMeisaiInterface {

}
export default class KobetsuKiseiMeisaiEntity implements KobetsuKiseiMeisaiInterface {

    /** 収支報告書収入(その3から12)Id */
    offeringBalancesheetIncomeId: number;

    /** 収支報告書収入(その3から12)同一識別コード */
    offeringBalancesheetIncomeCode: number;

    /** 文書同一識別コード */
    documentCode: number;

    /** 報告年 */
    houkokuNen: number;

    /** 提出日 */
    offeringDate: Date;

    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    /** 原文書政治団体代表者名 */
    daihyouName: string;

    /** 原文書政治団体名称 */
    dantaiName: string;

    /** 関連者区分 */
    relationKbn: number;

    /** 代表者関連者Id */
    relationPersonIdDelegate: number;

    /** 代表者関連者同一識別コード */
    relationPersonCodeDelegate: number;

    /** 代表者関連者名称 */
    relationPersonNameDelegate: string;

    /** 様式区分 */
    youshikiKbn: number;

    /** 様式枝区分項目 */
    youshikiEdaKbn: number;

    /** 項目名称 */
    itemName: string;

    /** 金額 */
    kingaku: number;

    /** 発生日 */
    accrualDate: string;

    /** 発生日実値 */
    accrualDateValue: Date;

    /** 備考 */
    bikou: string;

    /** 支出した相手先名 */
    partnerName: string;

    /** 支出した相手先住所 */
    partnerJuusho: string;

    /** 職業 */
    shokugyou: string;

    /** 支払者関連者Id */
    relationId: number;

    /** 支払者関連者同一識別コード */
    relationCode: number;

    /** 政治団体区分 */
    dantaiKbn: string;


    constructor() {

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_DATE: Date = new Date(1948, 7, 29);

        this.offeringBalancesheetIncomeId = INIT_NUMBER;
        this.offeringBalancesheetIncomeCode = INIT_NUMBER;
        this.documentCode = INIT_NUMBER;
        this.houkokuNen = INIT_NUMBER;
        this.offeringDate = INIT_DATE;
        this.politicalOrganizationId = INIT_NUMBER;
        this.politicalOrganizationCode = INIT_NUMBER;
        this.politicalOrganizationName = INIT_STRING;
        this.daihyouName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.relationKbn = INIT_NUMBER;
        this.relationPersonIdDelegate = INIT_NUMBER;
        this.relationPersonCodeDelegate = INIT_NUMBER;
        this.relationPersonNameDelegate = INIT_STRING;
        this.youshikiKbn = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.itemName = INIT_STRING;
        this.kingaku = INIT_NUMBER;
        this.accrualDate = INIT_STRING;
        this.accrualDateValue = INIT_DATE;
        this.bikou = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.partnerJuusho = INIT_STRING;
        this.shokugyou = INIT_STRING;
        this.relationId = INIT_NUMBER;
        this.relationCode = INIT_NUMBER;
        this.dantaiKbn = INIT_STRING;
    }

}