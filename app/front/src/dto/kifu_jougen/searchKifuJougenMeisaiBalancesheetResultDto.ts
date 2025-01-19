import KifuJougenTradingInfoInterface from "./kifuJougenTradingInfoDto";

export default interface SearchKifuJougenMeisaiBalancesheetResultInterface {

}

export default class SearchKifuJougenMeisaiBalancesheetResultDto {

    /** 取引相手数 */
    countAll: number;

    /** ページ数 */
    pageNumber: number;

    /** 取引グループリスト */
    listTradingGroup: KifuJougenTradingInfoInterface[];

    constructor() {

        // 初期データ
        const INIT_NUMBER: number = 0;

        this.countAll = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.listTradingGroup = [];

    }


}