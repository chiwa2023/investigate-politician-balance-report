/**
 * 収支報告書を指定するための要素Interface
 *
 * @export
 * @interface SurveySelecterInterface 収支報告書指定条件
 */
export default interface SurveySelecterInterface {
    /** データ年 */
    yearTeishutsu: number;
    /** データ年表示 */
    yearTeishutsuText: string;
    /** 資金管理団体Id */
    organizaitionId: number;
    /** 資金管理団体名称  */
    organizaitionName: string;
}

/**
 * 収支報告書を指定するための要素Dto
 *
 * @export
 * @class SurveySelecterDto 収支報告書指定条件Dto
 * @implements {SurveySelecterInterface}
 */
export default class SurveySelecterDto implements SurveySelecterInterface {
    /** データ年 */
    yearTeishutsu: number;
    /** データ年表示 */
    yearTeishutsuText: string;
    /** 資金管理団体Id */
    organizaitionId: number;
    /** 資金管理団体名称  */
    organizaitionName: string;

    constructor(impl?: SurveySelecterInterface) {
        if (impl !== undefined) {
            this.yearTeishutsu = impl?.yearTeishutsu;
            this.yearTeishutsuText = impl?.yearTeishutsuText;
            this.organizaitionId = impl?.organizaitionId;
            this.organizaitionName = impl?.organizaitionName;
        }
        else {
            this.yearTeishutsu = -1;
            this.yearTeishutsuText = "";
            this.organizaitionId = -1;
            this.organizaitionName = "";
        }
    }
}