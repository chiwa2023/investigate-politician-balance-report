import TemplateFrameworkResultDto from "../template/templateFrameworkResultDto";
import IncomeAndOutcomeSearchLineDto from "./incomeAndOutcomeSearchLineDto";

export default interface IncomeAndOutcomeNaturalSearchResultInterface {

}

export default class IncomeAndOutcomeNaturalSearchResultDto extends TemplateFrameworkResultDto implements IncomeAndOutcomeNaturalSearchResultInterface {

    /** 実際の検索語 */
    searchWords: string;

    /** 支出検索結果リスト */
    listOutcome: IncomeAndOutcomeSearchLineDto[];

    /** 支出検索結果リスト */
    listIncome: IncomeAndOutcomeSearchLineDto[];

    /** 収入検索全体件数 */
    countIncome: number;

    /** 支出検索全体件数 */
    countOutcome: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.searchWords = INIT_STRING;
        this.listOutcome = [];
        this.listIncome = [];
        this.countIncome = INIT_NUMBER;
        this.countOutcome = INIT_NUMBER;
    }

}