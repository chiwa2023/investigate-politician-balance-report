/**
 * 各種PayInterface
 */
export default interface VariousFinancialPayInterface {

    /** 各種PayId */
    variousFinancialPayId: number;
    /** 各種Pay同一識別コード*/
    variousFinancialPayCode: number;
    /** 各種Pay名称 */
    variousFinancialPayName: string;
    /** 最新区分 */
    saishinKbn: number;
// eslint-disable-next-line semi
}

/**
 * 各種PayDto
 */
export default class VariousFinancialPayDto implements VariousFinancialPayInterface {

    /** 各種PayId */
    variousFinancialPayId: number;
    /** 各種Pay同一識別コード*/
    variousFinancialPayCode: number;
    /** 各種Pay名称 */
    variousFinancialPayName: string;
    /** 最新区分 */
    saishinKbn: number;

    constructor() {

        this.variousFinancialPayId = 0;
        this.variousFinancialPayCode = 0;
        this.variousFinancialPayName = "";
        this.saishinKbn = 0;

    }

}