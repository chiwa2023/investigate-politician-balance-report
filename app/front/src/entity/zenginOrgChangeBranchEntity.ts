export default interface ZenginOrgChangeBranchInterface {

}

export default class ZenginOrgChangeBranchEntity implements ZenginOrgChangeBranchInterface {

    /** 金融機関異動Id */
    zenginOrgChangeBranchId: number;

    /** 金融機関異動同一識別コード */
    zenginOrgChangeBranchCode: number;

    /** 最新区分 */
    saishinKbn: number;

    /** 完了フラグ */
    isFinished: boolean;

    /** 異動区分 */
    changeKbn: number;

    /** 異動区分表示 */
    changeKbnText: string;

    /** 編集金融機関マスタId */
    zenginOrgTempoMasterId: number;

    /** 編集全銀協金融機関マスタ同一識別コード */
    zenginOrgTempoMasterCode: number;

    /** 編集全銀協金融機関名称 */
    zenginOrgTempoMasterName: string;

    /** 移転先金融機関支店Id */
    zenginOrgMoveId: number;

    /** 移転先金融機関支店同一識別コード */
    zenginOrgMoveCode: number;

    /** 移転先金融機関支店名称 */
    zenginOrgMoveName: String;

    /** 全銀協金融機関コード */
    orgCode: string;

    /** 全銀協金融機関支店コード */
    branchCode: string;

    /** 金融機関かな */
    orgNameKana: string;

    /** 金融機関名称 */
    orgName: string;

    /** 支店名かな */
    branchNameKana: string;

    /** 支店名 */
    branchName: string;

    /** 支店郵便番号 */
    postalCode: string;

    /** 支店住所 */
    branchAddress: string;

    /** 支店電話番号 */
    phonNumber: string;

    /** 手形交換所番号 */
    billExchangeNumber: string;

    /** 並び順 */
    orderCode: string;

    /** 内国為替制度加盟該否 */
    flgNaikokuKawase: string;

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

        /** 初期データ */
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_BOOLEAN = false;
        const INIT_DATE = new Date(1948, 7, 29);


        this.zenginOrgChangeBranchId = INIT_NUMBER;
        this.zenginOrgChangeBranchCode = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.isFinished = INIT_BOOLEAN;
        this.changeKbn = INIT_NUMBER;
        this.changeKbnText = INIT_STRING;
        this.zenginOrgTempoMasterId = INIT_NUMBER;
        this.zenginOrgTempoMasterCode = INIT_NUMBER;
        this.zenginOrgTempoMasterName = INIT_STRING;
        this.zenginOrgMoveId = INIT_NUMBER;
        this.zenginOrgMoveCode = INIT_NUMBER;
        this.zenginOrgMoveName = INIT_STRING;
        this.orgCode = INIT_STRING;
        this.branchCode = INIT_STRING;
        this.orgNameKana = INIT_STRING;
        this.orgName = INIT_STRING;
        this.branchNameKana = INIT_STRING;
        this.branchName = INIT_STRING;
        this.postalCode = INIT_STRING;
        this.branchAddress = INIT_STRING;
        this.phonNumber = INIT_STRING;
        this.billExchangeNumber = INIT_STRING;
        this.orderCode = INIT_STRING;
        this.flgNaikokuKawase = INIT_STRING;
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