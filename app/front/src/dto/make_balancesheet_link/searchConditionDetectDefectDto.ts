/**
 * 収支報告書／使途報告書欠損検出検索条件Interface
 */
export default interface SearchConditionDetectDefectInterface {

    /** 団体Id */
    orgnaizationId: number;

    /** 団体同一識別コード */
    orgnaizationCode: number;

    /** 団体名称 */
    orgnaizationName: string;

    /** 検索条件開始年 */
    conditionStartYear: number;

    /** 検索条件終了年 */
    conditionEndYear: number;

}

/**
 * 収支報告書／使途報告書欠損検出検索条件Dto
 */
export default class SearchConditionDetectDefectDto {

    /** 団体Id */
    orgnaizationId: number;

    /** 団体同一識別コード */
    orgnaizationCode: number;

    /** 団体名称 */
    orgnaizationName: string;

    /** 検索条件開始年 */
    conditionStartYear: number;

    /** 検索条件終了年 */
    conditionEndYear: number;

    /**
     * コンストラクタ
     * @param impl インターフェイス実装
     */
    constructor(impl?: SearchConditionDetectDefectInterface) {
        //初期値
        const initInt: number = 0;
        const initString: string = "";

        if (impl !== undefined) {
            this.orgnaizationId = impl.orgnaizationId;
            this.orgnaizationCode = impl.orgnaizationCode;
            this.orgnaizationName = impl.orgnaizationName;
            this.conditionStartYear = impl.conditionStartYear;
            this.conditionEndYear = impl.conditionEndYear;
        } else {
            this.orgnaizationId = initInt;
            this.orgnaizationCode = initInt;
            this.orgnaizationName = initString;
            this.conditionStartYear = initInt;
            this.conditionEndYear = initInt;

        }


    }

}