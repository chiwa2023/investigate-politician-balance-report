import WkTblRenketsuKoufukinSelectInterface from "../../entity/wkTblRenketsuKoufukinSelectDto";

export default interface RenketsuKoufukinWkTblInterface{

}



export default class RenketsuKoufukinWkTblDto implements RenketsuKoufukinWkTblInterface {

    /** 取引相手数 */
    countAll: number;

    /** ページ数 */
    pageNumber: number;

    /** 1回取得件数 */
    offset: number;

    /** 紐づけ成功リスト */
    listSuccess: WkTblRenketsuKoufukinSelectInterface[];

    constructor() {
        const INIT_NUMBER: number = 0;

        this.countAll = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.offset = INIT_NUMBER;
        this.listSuccess = [];
    }
}