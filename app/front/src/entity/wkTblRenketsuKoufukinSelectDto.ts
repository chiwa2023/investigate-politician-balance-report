export default interface WkTblRenketsuKoufukinSelectInterface {

}

export default class WkTblRenketsuKoufukinSelectDto implements WkTblRenketsuKoufukinSelectInterface {

    /** 交付金連結ワークテーブル */
    wkTblRenketsuKoufukinId: number;

    /** 最新区分 */
    saishinKbn: number;

    /** データ区分 */
    dataKbn: number;

    /** 報告年 */
    houkokuNen: number;

    /** 収支報告書Id */
    balancesheetId: number;

    /** 使途報告書Id */
    usageReportId: number;

    /** 原文書政治団体代表者名 */
    daihyouName: string;

    /** 原文書政治団体名称 */
    dantaiName: string;

    /** 収支報告書提出日 */
    balancesheetOfferingDate: Date;

    /** 使途報告書提出日 */
    usageOfferingDate: Date;

    /** 発生日 */
    accrualDateValue: Date;

    /** 収支報告書費目 */
    balanceHimoku: string;

    /** 収支報告書目的 */
    balanceMokuteki: string;

    /** 交付金金額 */
    amountKoufukin: number;

    /** 自団体金額 */
    amountMyFunds: number;

    /** 全金額 */
    amountAll: number;

    /** 使途報告書費目 */
    usageHimoku: string;

    /** 使途報告書支出区分名称 */
    usageShishutsuName: string;

    /** 使途報告書使用項目 */
    usageUsageItem: string;

    /** 支払者(使途報告書) */
    payeeName: string;

    /** 支払者住所 */
    payeeAddress: string;

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

        this.wkTblRenketsuKoufukinId = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.dataKbn = INIT_NUMBER;
        this.houkokuNen = INIT_NUMBER;
        this.balancesheetId = INIT_NUMBER;
        this.usageReportId = INIT_NUMBER;
        this.daihyouName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.balancesheetOfferingDate = INIT_DATE;
        this.usageOfferingDate = INIT_DATE;
        this.accrualDateValue = INIT_DATE;
        this.balanceHimoku = INIT_STRING;
        this.balanceMokuteki = INIT_STRING;
        this.amountKoufukin = INIT_NUMBER;
        this.amountMyFunds = INIT_NUMBER;
        this.amountAll = INIT_NUMBER;
        this.usageHimoku = INIT_STRING;
        this.usageShishutsuName = INIT_STRING;
        this.usageUsageItem = INIT_STRING;
        this.payeeName = INIT_STRING;
        this.payeeAddress = INIT_STRING;
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