import TemplateFrameworkResultDto from "../template/templateFrameworkResultDto";
import FukisaiTradingInfoInterface from "./fukisaiTradingInfoDto";

export default interface SearchFukisaiBalancesheetResultInterface {

}

export default class SearchFukisaiBalancesheetResultDto extends TemplateFrameworkResultDto implements SearchFukisaiBalancesheetResultInterface {

    /** 取引相手数 */
    countAll: number;

    /** ページ数 */
    pageNumber: number;

    /** 取引グループリスト */
    listTradingGroup: FukisaiTradingInfoInterface[];

    /** 表示メッセージ */
    message: string;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.countAll = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.listTradingGroup = [];
        this.message = INIT_STRING;

    }

}