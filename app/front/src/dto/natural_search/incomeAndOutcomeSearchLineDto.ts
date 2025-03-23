export default interface IncomeAndOutcomeSearchLineInterface {

}

export default class IncomeAndOutcomeSearchLineDto implements IncomeAndOutcomeSearchLineInterface {

    /** 項目名称 */
    itemId: string;

    /** 項目名称 */
    itemName: string;

    /** 目的 */
    mokuteki: string;

    /** 金額 */
    kingaku: number;

    /** 発生日 */
    accrualDate: string;

    /** 発生日実値 */
    accrualDateValue: Date;

    /** 原文書政治団体代表者名 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
    daihyouName: string;

    /** 原文書政治団体名称 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
    dantaiName: string;

    /** 様式区分 */
    youshikiKbn: number;

    /** 様式枝区分項目 */
    youshikiEdaKbn: number;

    /** 支出した相手先名 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
    partnerName: string;

    /** 支出した相手先住所 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
    partnerJuusho: string;

    /** 金額表示テキスト収入 */
    kingakuIncomeText: string;

    /** 金額表示テキスト支出 */
    kingakuOutcomeText: string;

    /** 集計用金額 */
    kingakuShuukei: number;

    /** 挿入可能性 */
    availableAddText: string;

    constructor() {
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_DATE = new Date(1980, 1, 1);

        this.itemId = INIT_STRING;
        this.itemName = INIT_STRING;
        this.mokuteki = INIT_STRING;
        this.kingaku = INIT_NUMBER;
        this.accrualDate = INIT_STRING;
        this.accrualDateValue = INIT_DATE;
        this.daihyouName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.youshikiKbn = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.partnerName = INIT_STRING;
        this.partnerJuusho = INIT_STRING;
        this.kingakuIncomeText = INIT_STRING;
        this.kingakuOutcomeText = INIT_STRING;
        this.kingakuShuukei = INIT_NUMBER;
        this.availableAddText = INIT_STRING; 
    }
}