export default class FeeSummaryDto {

    /** 会費合計  */
    sumFee: number;

    /** 員数合計  */
    sumInsu: number;

    constructor() {
        this.sumFee = 0;
        this.sumInsu = 0;
    }
}