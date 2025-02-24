﻿export default class YoushikiEdaKbnIncomeConstants {

    /** 個人 */
    static PERSONAL: number = 1;
    static PERSONAL_TEXT: string = "個人";

    /** 法人 */
    static CORPORATION: number = 2
    static CORPORATION_TEXT: string = "法人";

    /** 政治団体 */
    static POLITIC_ORG: number = 3;
    static POLITIC_ORG_TEXT: string = "政治団体";

    static convertText(param: number) {

        switch (param) {
        case YoushikiEdaKbnIncomeConstants.PERSONAL:
            return YoushikiEdaKbnIncomeConstants.PERSONAL_TEXT;

        case YoushikiEdaKbnIncomeConstants.CORPORATION:
            return YoushikiEdaKbnIncomeConstants.CORPORATION_TEXT;

        case YoushikiEdaKbnIncomeConstants.POLITIC_ORG:
            return YoushikiEdaKbnIncomeConstants.POLITIC_ORG_TEXT;
            
        default:
            return "";
        }
        return "";
    }
}