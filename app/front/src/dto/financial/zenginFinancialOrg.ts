/**
 * 全銀金融機関Interface
 */
export default interface ZenginFinancialOrgInterface {

    /** 全銀金融機関Id */
    zenginFinancialOrgId: number;
    /** 全銀金融機関同一識別コード*/
    zenginFinancialOrgCode: number;
    /** 全銀金融機関名称 */
    zenginFinancialOrgName: string;
    /** 最新区分 */
    saishinKbn: number;
// eslint-disable-next-line semi
}

/**
 * 全銀金融機関Dto
 */
export default class ZenginFinancialOrgDto implements ZenginFinancialOrgInterface {
    /** 全銀金融機関Id */
    zenginFinancialOrgId: number;
    /** 全銀金融機関同一識別コード*/
    zenginFinancialOrgCode: number;
    /** 全銀金融機関名称 */
    zenginFinancialOrgName: string;
    /** 最新区分 */
    saishinKbn: number;

    constructor() {

        this.zenginFinancialOrgId = 0;
        this.zenginFinancialOrgCode = 0;
        this.zenginFinancialOrgName = "";
        this.saishinKbn = 0;

    }

}