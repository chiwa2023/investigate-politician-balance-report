import WkTblUkaiKenkinPickupRouteInterface from "../../entity/wkTblUkaiKenkinPickupRouteEntity";

export default interface UkaiKenkinPickupRouteResultInterface {
}


export default class UkaiKenkinPickupRouteResultDto implements UkaiKenkinPickupRouteResultInterface {

    /** 表示件数  */
    countAll: number;

    /** 件数上限  */
    limit: number;

    /** 検索位置  */
    offset: number;

    /** 抽出リスト(初階層)  */
    listFirstRouteEntity: WkTblUkaiKenkinPickupRouteInterface[];

    /** 抽出リスト(最終階層)  */
    listLastRouteEntity: WkTblUkaiKenkinPickupRouteInterface[];

    /** 抽出ルートリスト(中間ルート候補)  */
    listPickupRouteEntity: WkTblUkaiKenkinPickupRouteInterface[];

    constructor() {
        // 初期データ
        const INIT_NUMBER = 0;

        this.countAll = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.offset = INIT_NUMBER;
        this.listPickupRouteEntity = [];
        this.listFirstRouteEntity = [];
        this.listLastRouteEntity = [];

    }

}