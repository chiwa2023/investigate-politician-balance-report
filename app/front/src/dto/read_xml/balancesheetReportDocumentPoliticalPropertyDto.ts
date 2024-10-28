export default interface BalancesheetReportDocumentPoliticalPropertyInterface {

}

export default class BalancesheetReportDocumentPoliticalPropertyDto implements BalancesheetReportDocumentPoliticalPropertyInterface {

    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    /** 原文書団体名称 */
    dantaiName: string;

    /** 原文書代表者名 */
    daihyouName: string;

    /** 関連者区分 */
    relationKbn: number;

    /** 代表者関連者個人Id */
    relationPersonIdDelegate: number;

    /** 関連者同一識別コード */
    relationPersonCodeDelegate: number;

    /** 関連者名称 */
    relationPersonNameDelegate: string

    /** 報告年度 */
    houkokuNen: number;

    /** 提出日 */
    offeringDate: Date;

    /** 政治団体追加該否 */
    isAddOrganization: boolean;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.politicalOrganizationId = INIT_NUMBER;
        this.politicalOrganizationCode = INIT_NUMBER;
        this.politicalOrganizationName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.daihyouName = INIT_STRING;
        this.relationKbn = INIT_NUMBER;
        this.relationPersonIdDelegate = INIT_NUMBER;
        this.relationPersonCodeDelegate = INIT_NUMBER;
        this.relationPersonNameDelegate = INIT_STRING
        this.houkokuNen = INIT_NUMBER;
        this.offeringDate = new Date();
        this.isAddOrganization = false;
    }
}