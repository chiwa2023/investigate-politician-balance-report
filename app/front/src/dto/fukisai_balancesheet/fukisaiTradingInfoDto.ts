import WkTblFukisaiBalancesheetEntity from '../../entity/wkTblFukisaiBalancesheetEntity';

export default interface FukisaiTradingInfoInterface {

}

export default class FukisaiTradingInfoDto implements FukisaiTradingInfoInterface {

    /** 収入合計 */
    sumIncome: number;

    /** 支出合計 */
    sumOutcome: number;

    /** 調査対象政治団体名称 */
    politicalOrgName: string;

    /** 取引相手(政治団体)名称 */
    partnerName: string;

    /** 取引明細リスト */
    listMeisai: WkTblFukisaiBalancesheetEntity[];

    /** 金額差有無 */
    isKingakuDiffer: boolean;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.sumIncome = INIT_NUMBER;
        this.sumOutcome = INIT_NUMBER;
        this.politicalOrgName = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.listMeisai = [];
        this.isKingakuDiffer = INIT_BOOLEAN;

    }

}