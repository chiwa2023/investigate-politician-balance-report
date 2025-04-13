import WkTblUsageOutcomeInterface from "../../entity/wkTblUsageOutcomeEntity";

export default interface RenketsuKoufukinFailureInterface {

}


export default class RenketsuKoufukinFailureDto implements RenketsuKoufukinFailureInterface {

    /** 取引相手数 */
    countAll: number;

    /** ページ数 */
    pageNumber: number;

    /** 1回取得件数 */
    offset: number;

    /** 紐づけ失敗リスト */
    listFailure: WkTblUsageOutcomeInterface[];

    constructor() {
        const INIT_NUMBER: number = 0;

        this.countAll = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.offset = INIT_NUMBER;
        this.listFailure = [];
    }

}