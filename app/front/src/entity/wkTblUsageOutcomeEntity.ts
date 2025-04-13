export default interface WkTblUsageOutcomeInterface {

}
export default class WkTblUsageOutcomeEntity implements WkTblUsageOutcomeInterface {




    /** ワークテーブルId */
    worktableId: number;

    /** 使途報告書様式8その4Id */
    partyUsage0804ReportId: number;

    /** 使途報告書様式8その4同一識別コード */
    partyUsage0804ReportCode: number;

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

    /** 区分 */
    shishutsuKbn: number;

    /** 区分名称 */
    shishutsuKbnName: string;

    /** シート費目 */
    sheetHimoku: string;

    /** シート全合計 */
    sheetAmountAll: number;


    /** シート全合計交付金充当 */
    sheetAmountAllKoufukin: number;


    /** シート全合計自党基金充当 */
    sheetAmountAllMyFunds: number;


    /** シート全合計 */
    sheetAmountSonota: number;

    /** シート全合計交付金充当 */
    sheetAmountSonotaKoufukin: string;

    /** シート全合計自党基金充当 */
    sheetAmountSonotaMyFunds: string;

    /** 行番号 */
    rowNo: number;


    /** 使用項目 */
    usageItem: string;


    /** 支出金額 */
    amountAll: number;


    /** 交付金充当支出金額 */
    amountKoufukin: number;

    /** 交付金充当支出金額 */
    amountMyFunds: number;

    /** 発生日 */
    accrualDate: string;

    /** 発生日実値 */
    accrualDateValue: Date;

    /** 支払者 */
    payeeName: string;

    /** 支払者住所 */
    address: string;

    /** 備考 */
    bikou: string;

    /** 領収所徴収フラグ */
    flgCollectRecipt: number;

    /** 支払者関連者区分 */
    relationKbnPayee: number;

    /** 支払者関連者Id(個人) */
    relationPersonIdPayee: number;

    /** 支払者関連者同一識別コード(個人) */
    relationPersonCodePayee: number;

    /** 支払者関連者名称(個人) */
    relationPersonNamePayee: string;

    /** 支払者関連者Id(法人) */
    relationCorpIdPayee: number;

    /** 支払者関連者同一識別コード(法人) */
    relationCorpCodePayee: number;

    /** 支払者関連者Id(法人) */
    relationCorpNamePayee: string;

    /** 支払者関連者同一識別コード(政治団体) */
    relationPoliticalOrgIdPayee: number;

    /** 支払者関連者名称(政治団体) */
    relationPoliticalOrgCodePayee: number;

    /** 支払者関連者名称(政治団体) */
    relationPoliticalOrgNamePayee: string;

    /** 自然検索対象 */
    searchWords: string;

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
        this.partyUsage0804ReportId = INIT_NUMBER;
        this.partyUsage0804ReportCode = INIT_NUMBER;
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
        this.shishutsuKbn = INIT_NUMBER;
        this.shishutsuKbnName = INIT_STRING;
        this.sheetHimoku = INIT_STRING;
        this.sheetAmountAll = INIT_NUMBER;
        this.sheetAmountAllKoufukin = INIT_NUMBER;
        this.sheetAmountAllMyFunds = INIT_NUMBER;
        this.sheetAmountSonota = INIT_NUMBER;
        this.sheetAmountSonotaKoufukin = INIT_STRING;
        this.sheetAmountSonotaMyFunds = INIT_STRING;
        this.rowNo = INIT_NUMBER;
        this.usageItem = INIT_STRING;
        this.amountAll = INIT_NUMBER;
        this.amountKoufukin = INIT_NUMBER;
        this.amountMyFunds = INIT_NUMBER;
        this.accrualDate = INIT_STRING;
        this.accrualDateValue = INIT_DATE;
        this.payeeName = INIT_STRING;
        this.address = INIT_STRING;
        this.bikou = INIT_STRING;
        this.flgCollectRecipt = INIT_NUMBER;
        this.relationKbnPayee = INIT_NUMBER;
        this.relationPersonIdPayee = INIT_NUMBER;
        this.relationPersonCodePayee = INIT_NUMBER;
        this.relationPersonNamePayee = INIT_STRING;
        this.relationCorpIdPayee = INIT_NUMBER;
        this.relationCorpCodePayee = INIT_NUMBER;
        this.relationCorpNamePayee = INIT_STRING;
        this.relationPoliticalOrgIdPayee = INIT_NUMBER;
        this.relationPoliticalOrgCodePayee = INIT_NUMBER;
        this.relationPoliticalOrgNamePayee = INIT_STRING;
        this.searchWords = INIT_STRING;
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