export default class ProposeCsvReadTemplateEntity {

    /** CSV読取り仕様同一識別コード */
    proposeCsvReadTemplateCode: number;

    /** CSV読取り仕様名称 */
    proposeCsvReadTemplateName: string;

    /** 指定配列内容 */
    arrayText: string;

    /** 金融機関名称 */
    financialOrgName: string;

    /** 配列桁数 */
    arrayNumber: number;

    /** 金融機関区分 */
    financialOrgKbn: number;

    /** 金融機関同一識別コード */
    financialOrgCode: number;

    /** 更新時間 */
    updateTime: Date;

    /** 最新区分 */
    saishinKbn: number;

    /** テーブル全文検索カラム */
    tableAllSearchText: string;

    /** CSV読取り仕様Id */
    proposeCsvReadTemplateId: number;

    /** 金融機関Id */
    financialOrgId: number;

    /** ヘッダの有無 */
    hasHeader: boolean;

    /** 書証Id */
    shoshouId: string;

    /** 書証同一識別コード */
    shoshouCode: number;

    /** 書証区分 */
    shoshouKbn: number;

    /** 申請過程該否 */
    inProsessing: boolean;

    /** 申請諾否 */
    isAccepted: boolean;

    /** 編集元Id */
    editId: number;

    /** 判定理由 */
    judgeReason: string;

    /** ログインユーザId */
    loginUserId: number;

    /** ログインユーザ同一識別コード */
    loginUserCode: number;

    /** ログインユーザ氏名 */
    loginUserName: string;


    constructor() {
        this.proposeCsvReadTemplateCode = 0;
        this.proposeCsvReadTemplateName = "";
        this.arrayText = "";
        this.financialOrgName = "";
        this.arrayNumber = 0;
        this.financialOrgKbn = 0;
        this.financialOrgCode = 0;
        this.updateTime = new Date();
        this.saishinKbn = 0;
        this.tableAllSearchText = "";
        this.proposeCsvReadTemplateId = 0;
        this.financialOrgId = 0;
        this.hasHeader = false;
        this.loginUserId = 0;
        this.loginUserCode = 0;
        this.loginUserName = "";
        this.shoshouId = "";
        this.shoshouCode = 0;
        this.shoshouKbn = 0;
        this.inProsessing = false;
        this.isAccepted = false;
        this.editId = 0;
        this.judgeReason = "";
    }

}