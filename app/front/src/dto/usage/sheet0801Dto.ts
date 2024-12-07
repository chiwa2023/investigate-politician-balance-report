export default interface Sheet0801Interface {

}

export default class Sheet0801Dto implements Sheet0801Interface {

    /** 報告年度 */
    nendo: number;

    /** 政党名称 */
    partyName: string;

    /** 政党名称かな */
    partyNameKana: string;

    /** 主たる事務所住所 */
    officeAddress: string;

    /** 代表者姓名 */
    delegateName: string;

    /** 会計責任者姓名 */
    accountManagerName: string;

    /** 担当者1姓名 */
    worker1Name: string;

    /** 担当者1電話番号 */
    worker1Tel: string;

    /** 担当者2姓名 */
    worker2Name: string;

    /** 担当者2電話番号 */
    worker2Tel: string;

    /** 支部区分 */
    shibuKbn: string;

    /** 解散区分 */
    kaisanKbn: number;

    /** 解散報告日 */
    kaisanDate: string;

    /** 整理番号 */
    seiriNo: string;

    /** 受付番号 */
    uketsukeNo: string;

    constructor() {
        const INIT_NUMBER = 0;
        const INIT_STRING = "";

        this.nendo = INIT_NUMBER;
        this.partyName = INIT_STRING;
        this.partyNameKana = INIT_STRING;
        this.officeAddress = INIT_STRING;
        this.delegateName = INIT_STRING;
        this.accountManagerName = INIT_STRING;
        this.worker1Name = INIT_STRING;
        this.worker1Tel = INIT_STRING;
        this.worker2Name = INIT_STRING;
        this.worker2Tel = INIT_STRING;
        this.shibuKbn = INIT_STRING;
        this.kaisanKbn = INIT_NUMBER;
        this.kaisanDate = INIT_STRING;
        this.seiriNo = INIT_STRING;
        this.uketsukeNo = INIT_STRING;
   }

}