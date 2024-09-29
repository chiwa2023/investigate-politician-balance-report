import TemplateFrameworkCapsuleDto from "../template/templateFrameworkCapsuleDto";

export default interface IncomeAndOutcomeNaturalSearchConditionCapsuleInterface {

}



export default class IncomeAndOutcomeNaturalSearchConditionCapsuleDto extends TemplateFrameworkCapsuleDto implements IncomeAndOutcomeNaturalSearchConditionCapsuleInterface {

    /** 検索語(ユーザ指定) */
    userKeyWords: string;

    /** 実際の検索語 */
    searchWords: string;

    /** 収支報告書収入検索フラグ */
    isSearchIncome: boolean;

    /** 収支報告書支出検索フラグ */
    isSearchOutcome: boolean;

    /** 収支報告書収入検索結果開始位置 */
    offsetIncome: number;

    /** 収支報告書支出検索結果開始位置 */
    offsetOutcome: number;

    /** 収支報告検索開始日付 */
    startDate: Date;

    /** 収支報告書終了日付 */
    endDate: Date;

    /** 収支報告検索開始日付 */
    startDateText: string;

    /** 収支報告書終了日付 */
    endDateText: string;



    constructor() {
        super();

        const INIT_STRING = "";

        // 基本は収入・支出とも検索
        const INIT_BOOLEAN = true;

        // 検索期間は適宜使いやすい値を設定
        const now: Date = new Date();
        const year = now.getFullYear() - 2;
        const start: Date = new Date(year, 1, 1);
        const end: Date = new Date(year, 12, 31);

        this.userKeyWords = INIT_STRING;
        this.searchWords = INIT_STRING;
        this.isSearchIncome = INIT_BOOLEAN;
        this.isSearchOutcome = INIT_BOOLEAN;
        this.offsetIncome = -1; // 未検索
        this.offsetOutcome = -1; // 未検索
        this.startDate = start;
        this.endDate = end;

        this.startDateText = year + "-01-01";
        this.endDateText = year + "-12-31";
    }

}