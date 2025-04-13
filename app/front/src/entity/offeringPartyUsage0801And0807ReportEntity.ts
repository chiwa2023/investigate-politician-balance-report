export default interface OfferingPartyUsage0801And0807ReportInterface {

}

export default class OfferingPartyUsage0801And0807ReportEntity implements OfferingPartyUsage0801And0807ReportInterface {


    /** 使途報告書様式8その1と7Id */
    partyUsage0801And0807ReportId: number;

    /** 使途報告書様式8その1と7同一識別コード */
    partyUsage0801And0807ReportCode: number;

    /** 最新区分 */
    saishinKbn: number;

    /** ヘッダのバージョン番号 */
    version: string;

    /** ヘッダのアプリ名称 */
    apliName: string;

    /** ヘッダのアプリフラグ */
    flgApli: string;

    /** ヘッダの本部支部フラグ */
    flgHonbu: string;

    /** 情報入力有無フラグテキスト */
    johoUmuText: string;

    /** 会計支出(表示)基準 */
    kaikeiKijunKingaku: number;

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

    /** 様式8の1政党名称かな */
    partyNameKana: string;

    /** 様式8の1政党名称 */
    partyName: string;

    /** 様式8の1主たる事務所の住所 */
    officeAddress: string;

    /** 様式8の1代表者姓名 */
    delegateName: string;

    /** 様式8の1会計責任者姓名 */
    accountManagerName: string;

    /** 様式8の1作業者1姓名 */
    worker1Name: string;

    /** 様式8の1作業者1電話番号 */
    worker1Tel: string;

    /** 様式8の1作業者2姓名 */
    worker2Name: string;

    /** 様式8の1作業者2電話番号 */
    worker2Tel: string;

    /** 様式8の1解散区分 */
    kaisanKbn: number;

    /** 様式8の1解散日 */
    kaisanDate: string;

    /** 様式8の1支部区分 */
    shibuKbn: number;

    /** 様式8の1受付番号 */
    uketsukeNo: string;

    /** 様式8の1整理番号 */
    seiriNo: string;

    /** 領収書の写し提出(フラグ) */
    copyRecipt: number;

    /** 監査意見書提出(フラグ) */
    auditOption: number;

    /** 監査報告書提出(フラグ) */
    auditReport: number;

    /** 支部から受領下書類提出(フラグ) */
    shibuDocument: number;

    /** 統括文書提出(フラグ) */
    governingDocument: number;

    /** 真実であることの宣誓フラグ */
    flgConfirm: number;

    /** 提出日和暦 */
    accrualDate: string;

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
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_DATE: Date = new Date(1947, 7, 28);

        this.partyUsage0801And0807ReportId = INIT_NUMBER;
        this.partyUsage0801And0807ReportCode = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.version = INIT_STRING;
        this.apliName = INIT_STRING;
        this.flgApli = INIT_STRING;
        this.flgHonbu = INIT_STRING;
        this.johoUmuText = INIT_STRING;
        this.kaikeiKijunKingaku = INIT_NUMBER;
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
        this.partyNameKana = INIT_STRING;
        this.partyName = INIT_STRING;
        this.officeAddress = INIT_STRING;
        this.delegateName = INIT_STRING;
        this.accountManagerName = INIT_STRING;
        this.worker1Name = INIT_STRING;
        this.worker1Tel = INIT_STRING;
        this.worker2Name = INIT_STRING;
        this.worker2Tel = INIT_STRING;
        this.kaisanKbn = INIT_NUMBER;
        this.kaisanDate = INIT_STRING;
        this.shibuKbn = INIT_NUMBER;
        this.uketsukeNo = INIT_STRING;
        this.seiriNo = INIT_STRING;
        this.copyRecipt = INIT_NUMBER;
        this.auditOption = INIT_NUMBER;
        this.auditReport = INIT_NUMBER;
        this.shibuDocument = INIT_NUMBER;
        this.governingDocument = INIT_NUMBER;
        this.flgConfirm = INIT_NUMBER;
        this.accrualDate = INIT_STRING;
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