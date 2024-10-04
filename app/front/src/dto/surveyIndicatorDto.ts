/**
 * 調査指標を表示するInterface
 *
 * @export
 * @interface SurveyIndicatorInterface
 */
export default interface SurveyIndicatorInterface {

    /** 計算式       */
    indicatorExplain: string;

    /** 指標タイトル */
    indicatorTitle: string;

    /** 計算結果     */
    indicatorAnswer: number;

    /** 計算結果     */
    indicatorAnswerText: string;

    /** 計算結果単位 */
    indicatorUnit: string;

    /** 判定詳細     */
    indicatorResultDetail: String;
}

/**
 * 調査指標を表示するクラス
 *
 * @export
 * @class SurveyIndicatorDto 調査指標Dto
 * @implements {SurveyIndicatorInterface}
 */
export default class SurveyIndicatorDto implements SurveyIndicatorInterface {

    /** 計算式       */
    indicatorExplain: string;

    /** 指標タイトル */
    indicatorTitle: string;

    /** 計算結果     */
    indicatorAnswer: number;

    /** 計算結果     */
    indicatorAnswerText: string;

    /** 計算結果単位 */
    indicatorUnit: string;

    /** 判定詳細     */
    indicatorResultDetail: String;

    constructor(surveyIndicatorInterface?: SurveyIndicatorInterface) {

        if (surveyIndicatorInterface !== undefined) {
            this.indicatorExplain = surveyIndicatorInterface.indicatorExplain;
            this.indicatorTitle = surveyIndicatorInterface.indicatorTitle;
            this.indicatorAnswer = surveyIndicatorInterface.indicatorAnswer;
            this.indicatorAnswerText = surveyIndicatorInterface.indicatorAnswerText;
            this.indicatorUnit = surveyIndicatorInterface.indicatorUnit;
            this.indicatorResultDetail = surveyIndicatorInterface.indicatorResultDetail;
        } else {
            this.indicatorExplain = "";
            this.indicatorTitle = "";
            this.indicatorAnswer = 0;
            this.indicatorAnswerText = "";
            this.indicatorUnit = "";
            this.indicatorResultDetail = "";

        }
    }
}