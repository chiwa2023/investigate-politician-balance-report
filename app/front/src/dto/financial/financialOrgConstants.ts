class FinancialOrgConstants {

    /** 各種Pay */
    static VARIOUS_PAY: number = 1;

    /** 各種Pay利用申請中 */
    static VARIOUS_PAY_PROPOSE: number = 11;

    /** 全銀金融機関 */
    static ZENGIN_ORG: number = 2;

    /** 全銀金融機関申請中 */
    static ZENGIN_ORG_PROPOSE: number = 21;

}

function getFinancialKbnName(kbn: number): string {
    switch (kbn) {
        case FinancialOrgConstants.VARIOUS_PAY:
            return "各種Pay";

        case FinancialOrgConstants.ZENGIN_ORG:
            return "全銀金融機関";

        default:
            //基本的に実装ミス
            return "";
    }
}


export { FinancialOrgConstants, getFinancialKbnName }