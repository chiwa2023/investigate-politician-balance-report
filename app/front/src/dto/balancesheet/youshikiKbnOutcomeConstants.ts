export default class YoushikiKbnOutcomeConstants {

    /** 14.経常経費の内訳 */
    static COSTS: string = "14";
    static COSTS_TEXT: string = "14.経常経費の内訳";

    /** 15.政治活動費 */
    static EXPENS: string = "6";
    static EXPENS_TEXT: string = "15.政治活動費";

    static convertText(youshikiKbn: number) {

        switch (String(youshikiKbn)) {
        case YoushikiKbnOutcomeConstants.COSTS:
            return YoushikiKbnOutcomeConstants.COSTS_TEXT;

        case YoushikiKbnOutcomeConstants.EXPENS:
            return YoushikiKbnOutcomeConstants.EXPENS_TEXT;

        default:
            return "";
        }
    }

}