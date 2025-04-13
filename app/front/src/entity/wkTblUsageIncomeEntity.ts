export default interface WkTblUsageIncomeInterface {

}

export default class WkTblUsageIncomeEntity implements WkTblUsageIncomeInterface {

    /** ワークテーブルId */
    worktableId: number;

    /** 使途報告書様式6(その2)区分2Id */
    partyUsage0802Kbn02ReportId: number;

    /** 使途報告書様式8(その2)区分2同一識別コード */
    partyUsage0802Kbn02ReportCode: number;

    /** 最新区分 */
    saishinKbn: number;

    /** 文書同一識別コード */
    documentCode: number;

    /** 報告年度 */
    nendo: number;

    /** 提出日 */
    offeringDate: Date;

    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    /** 原文書政治団体名称 */
    dantaiName: string;

    /** 原文書政治団体代表者名 */
    daihyouName: string;

    /** 関連者区分 */
    relationKbn: number;

    /** 代表者関連者Id */
    relationPersonIdDelegate: number;

    /** 代表者関連者同一識別コード */
    relationPersonCodeDelegate: number;

    /** 代表者関連者名称 */
    relationPersonNameDelegate: string;

    /** 合計 */
    totalAmount: number;

    /** 行番号 */
    rowNo: number;

    /** 項目名称 */
    itemName: string;

    /** 金額 */
    amount: number;

    /** 発生日 */
    accrualDate: string;

    /** 発生日実値 */
    accrualDateValue: Date;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザ同一識別コード */
    insertUserCode: number;

    /** 挿入ユーザ姓名 */
    insertUserName: string;

    /** 挿入タイムスタンプ */
    insertTimestamp: Date;

    /** 更新ユーザId */
    updateUserId: number;

    /** 更新ユーザ同一識別コード */
    updateUserCode: number;

    /** 更新ユーザ姓名 */
    updateUserName: string;

    /** 更新タイムスタンプ */
    updateTimestamp: Date;


    constructor() {

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_DATE = new Date(1948, 7, 29);

        this.worktableId = INIT_NUMBER;
        this.partyUsage0802Kbn02ReportId = INIT_NUMBER;
        this.partyUsage0802Kbn02ReportCode = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.documentCode = INIT_NUMBER;
        this.nendo = INIT_NUMBER;
        this.offeringDate = INIT_DATE;
        this.politicalOrganizationId = INIT_NUMBER;
        this.politicalOrganizationCode = INIT_NUMBER;
        this.politicalOrganizationName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.daihyouName = INIT_STRING;
        this.relationKbn = INIT_NUMBER;
        this.relationPersonIdDelegate = INIT_NUMBER;
        this.relationPersonCodeDelegate = INIT_NUMBER;
        this.relationPersonNameDelegate = INIT_STRING;
        this.totalAmount = INIT_NUMBER;
        this.rowNo = INIT_NUMBER;
        this.itemName = INIT_STRING;
        this.amount = INIT_NUMBER;
        this.accrualDate = INIT_STRING;
        this.accrualDateValue = INIT_DATE;
        this.insertUserId = INIT_NUMBER;
        this.insertUserCode = INIT_NUMBER;
        this.insertUserName = INIT_STRING;
        this.insertTimestamp = INIT_DATE;
        this.updateUserId = INIT_NUMBER;
        this.updateUserCode = INIT_NUMBER;
        this.updateUserName = INIT_STRING;
        this.updateTimestamp = INIT_DATE;

    }

}