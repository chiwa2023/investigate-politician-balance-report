export default class YoushikiEdaKbnOutcomeConstants {

    /** 2.光熱水費 */
    static KOUNETSUHI: string = "2";
    static KOUNETSUHI_TEXT: string = "2.光熱水費";

    /** 3.備品・消耗品費 */
    static SHOUMOUHIN: string = "3";
    static SHOUMOUHIN_TEXT: string = "3.備品・消耗品費";

    /** 4.事務所費 */
    static JIMUSHO: string = "4";
    static JIMUSHO_TEXT: string = "4.事務所費";

    /** 1.組織活動費 */
    static ACTIVATION: string = "1";
    static ACTIVATION_TEXT: string = "1.組織活動費";

    /** 2.選挙関係費 */
    static ELECTION: string = "2";
    static ELECTION_TEXT: string = "2.選挙関係費";

    /** 3.機関紙発行事業費 */
    static PAPER: string = "3";
    static PAPER_TEXT: string = "3.機関紙発行事業費";

    /** 4.宣伝事業費 */
    static COMERCIAL: string = "4";
    static COMERCIAL_TEXT: string = "4.宣伝事業費";

    /** 5.政治資金パーティー開催事業費 */
    static PARTY: string = "5";
    static PARTY_TEXT: string = "5.政治資金パーティー開催事業費";

    /** 6.その他の事業費 */
    static BUISSINESS: string = "6";
    static BUISSINESS_TEXT: string = "6.その他の事業費";

    /** 7.調査研究費 */
    static RESEARCH: string = "7";
    static RESEARCH_TEXT: string = "7.調査研究費";

    /** 8.寄付交付金 */
    static DONATION: string = "8";
    static DONATION_TEXT: string = "8.寄付交付金";

    /** 9.その他の経費 */
    static OTHER: string = "9";
    static OTHER_TEXT: string = "9.その他の経費";

    static convertText(youshikiKbn: number, youshikiEdaKbn: string) {
        if (14 === youshikiKbn) {
            switch (youshikiEdaKbn) {
            case YoushikiEdaKbnOutcomeConstants.KOUNETSUHI:
                return YoushikiEdaKbnOutcomeConstants.KOUNETSUHI_TEXT;

            case YoushikiEdaKbnOutcomeConstants.SHOUMOUHIN:
                return YoushikiEdaKbnOutcomeConstants.SHOUMOUHIN_TEXT;

            case YoushikiEdaKbnOutcomeConstants.JIMUSHO:
                return YoushikiEdaKbnOutcomeConstants.JIMUSHO_TEXT;

            default:
                return "";
            }
        }

        if (15 === youshikiKbn) {
            switch (youshikiEdaKbn) {
            case YoushikiEdaKbnOutcomeConstants.ACTIVATION:
                return YoushikiEdaKbnOutcomeConstants.ACTIVATION_TEXT;

            case YoushikiEdaKbnOutcomeConstants.ELECTION:
                return YoushikiEdaKbnOutcomeConstants.ELECTION_TEXT;

            case YoushikiEdaKbnOutcomeConstants.PAPER:
                return YoushikiEdaKbnOutcomeConstants.PAPER_TEXT;

            case YoushikiEdaKbnOutcomeConstants.COMERCIAL:
                return YoushikiEdaKbnOutcomeConstants.COMERCIAL_TEXT;

            case YoushikiEdaKbnOutcomeConstants.PARTY:
                return YoushikiEdaKbnOutcomeConstants.PARTY_TEXT;

            case YoushikiEdaKbnOutcomeConstants.BUISSINESS:
                return YoushikiEdaKbnOutcomeConstants.BUISSINESS_TEXT;

            case YoushikiEdaKbnOutcomeConstants.RESEARCH:
                return YoushikiEdaKbnOutcomeConstants.RESEARCH_TEXT;

            case YoushikiEdaKbnOutcomeConstants.DONATION:
                return YoushikiEdaKbnOutcomeConstants.DONATION_TEXT;

            case YoushikiEdaKbnOutcomeConstants.OTHER:
                return YoushikiEdaKbnOutcomeConstants.OTHER_TEXT;

            default:
                return "";
            }
        }

        return "";
    }

}