import WkTblUkaiKenkinInterface from "../../entity/wkTblUkaiKenkinEntity";

export default interface UkaiKenkinDetaillResultInterface{

}

export default class UkaiKenkinDetaillResultDto implements UkaiKenkinDetaillResultInterface{

    /** 表示件数  */
    countAll: number;

    /** 件数上限  */
    limit: number;

    /** 検索位置  */
    offset: number;

    /** 検索明細リスト  */
    listDetailEntity:WkTblUkaiKenkinInterface[];

    constructor() {
        // 初期データ
        const INIT_NUMBER = 0;

        this.countAll = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.offset = INIT_NUMBER;
        this.listDetailEntity = [];
    }

}