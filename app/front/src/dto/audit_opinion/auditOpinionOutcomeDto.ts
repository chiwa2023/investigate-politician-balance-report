import BalancesheetOutcomeInterface from "../balancesheet/balancesheetOutcomeDto";

export default interface AuditOpinionOutcomeInterface extends BalancesheetOutcomeInterface {

        /** 収入データ意見付記Id */
        auditOpinionId: number;

        /** データ作成が調査側フラグ */
        isCreatedItemByInvest: boolean;
    
        /** 調査側意見ユーザ人数 */
        researcherAmount: number;
    
        /**監査意見支持意見の値  */
        auditAgreeItemValue: string;
    
        /** 監査意見支持意見の表示テキスト */
        auditAgreeItemText: string;
    
        /** 操作ユーザ団体役割名称 */
        loginUserRole: string;
    
        /** 追加監査意見 */
        auditDetailOpinion: string;

    // eslint-disable-next-line semi
}

export default class AuditOpinionOutcomeDto extends BalancesheetOutcomeInterface implements AuditOpinionOutcomeInterface{

    /** 収入データ意見付記Id */
    auditOpinionId: number;

    /** データ作成が調査側フラグ */
    isCreatedItemByInvest: boolean;

    /** 調査側意見ユーザ人数 */
    researcherAmount: number;

    /**監査意見支持意見の値  */
    auditAgreeItemValue: string;

    /** 監査意見支持意見の表示テキスト */
    auditAgreeItemText: string;

    /** 操作ユーザ団体役割名称 */
    loginUserRole: string;

    /** 追加監査意見 */
    auditDetailOpinion: string;

    constructor() {
        super();
        this.auditOpinionId = 0;
        this.isCreatedItemByInvest = false;
        this.researcherAmount = 0;
        this.auditAgreeItemValue = "";
        this.auditAgreeItemText = "";
        this.loginUserRole = "";
        this.auditDetailOpinion = "";
    }

}
