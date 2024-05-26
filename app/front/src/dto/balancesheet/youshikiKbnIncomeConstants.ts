export default class YoushikiKbnIncomeConstants {

    //TODO 定数化する

    /** 3.機関誌の発行その他 */
    static JOURNAL: string = "3";
    static JOURNAL_TEXT: string = "3.機関誌の発行その他";

    /** 4.借入金 */
    static BORROWED: string = "4";
    static BORROWED_TEXT: string = "4.借入金";

    /** 5.本部または支部からの交付金 */
    static RELATEDLGRANTS: string = "5";
    static RELATEDLGRANTS_TEXT: string = "5.本部または支部からの交付金";

    /** 6.その他の収入 */
    static OTHER: string = "6";
    static OTHER_TEXT: string = "6.その他の収入";

    /** 7.寄付 */
    static DONATE: string = "7";
    static DONATE_TEXT: string = "7.寄付";

    /** 8.寄付のうちあっせんによるもの */
    static DONATE_MEDIATE: string = "8";
    static DONATE_MEDIATE_TEXT: string = "8.寄付のうちあっせんによるもの";

    /** 9.政党匿名寄付 */
    static ANONYMUS: string = "9";
    static ANONYMUS_TEXT: string = "9.政党匿名寄付";

    /** 10.特定パーティー */
    static SPECIFIC_PARTY: string = "10";
    static SPECIFIC_PARTY_TEXT: string = "10.特定パーティー";

    /** 11.政治資金パーティー */
    static PARTY: string = "11";
    static PARTY_TEXT: string = "11.政治資金パーティー";

    /** 12.政治資金パーティーのうちあっせんによるもの */
    static PARTY_MEDIATE: string = "12";
    static PARTY_MEDIATE_TEXT: string = "12.政治資金パーティーのうちあっせんによるもの";


    static convertText(youshikiKbn: number) {

        switch (String(youshikiKbn)) {
        case YoushikiKbnIncomeConstants.JOURNAL:
            return YoushikiKbnIncomeConstants.JOURNAL_TEXT;

        case YoushikiKbnIncomeConstants.BORROWED:
            return YoushikiKbnIncomeConstants.BORROWED_TEXT;

        case YoushikiKbnIncomeConstants.RELATEDLGRANTS:
            return YoushikiKbnIncomeConstants.RELATEDLGRANTS_TEXT;

        case YoushikiKbnIncomeConstants.OTHER:
            return YoushikiKbnIncomeConstants.OTHER_TEXT;

        case YoushikiKbnIncomeConstants.DONATE:
            return YoushikiKbnIncomeConstants.DONATE_TEXT;

        case YoushikiKbnIncomeConstants.DONATE_MEDIATE:
            return YoushikiKbnIncomeConstants.DONATE_MEDIATE_TEXT;

        case YoushikiKbnIncomeConstants.ANONYMUS:
            return YoushikiKbnIncomeConstants.ANONYMUS_TEXT;

        case YoushikiKbnIncomeConstants.SPECIFIC_PARTY:
            return YoushikiKbnIncomeConstants.SPECIFIC_PARTY_TEXT;

        case YoushikiKbnIncomeConstants.PARTY:
            return YoushikiKbnIncomeConstants.PARTY_TEXT;

        case YoushikiKbnIncomeConstants.PARTY_MEDIATE:
            return YoushikiKbnIncomeConstants.PARTY_MEDIATE_TEXT;

        default:
            return "";
        }

        return "";
    }

}