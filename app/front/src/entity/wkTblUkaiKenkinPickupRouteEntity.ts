export default interface WkTblUkaiKenkinPickupRouteInterface {

}

export default class WkTblUkaiKenkinPickupRoutetEntity implements WkTblUkaiKenkinPickupRouteInterface {

    /** 迂回献金キャッチャー用結果抽出ワークテーブルId  */
    wkTblUkaiKenkinResultEntityId: number;

    /** 迂回献金キャッチャー用結果抽出ワークテーブル同一識別コード  */
    wkTblUkaiKenkinResultEntityCode: number;

    /** 収支報告書政治団体Id */
    politicalOrgId: number;

    /** 収支報告書政治団体同一識別コード */
    politicalOrgCode: number;

    /** 収支報告書政治団体名称 */
    politicalOrgName: string;

    /** 発生日 */
    accrualDate: string;

    /** 発生日実値 */
    accrualDateValue: Date;

    /** 様式区分 */
    youshikiKbn: number;

    /** 様式枝区分項目 */
    youshikiEdaKbn: number;

    /** 項目名 */
    itemName: string;

    /** 金額 */
    kingaku: number;

    /** 抽出階層 */
    pickupStage: number;

    /** (収支報告書行)連番 */
    renban: number;

    /** 取引相手Id */
    tradingPartnerId: number;

    /** 取引相手同一識別コード */
    tradingPartnerCode: number;

    /** 取引相手名称 */
    tradingPartnerName: string;

    /** 取引相手住所 */
    tradingPartnerAddress: string;


    /** 収支報告書記載団体関連者Id */
    poliOrgRelationPersonId: number;

    /** 収支報告書記載団体関連者同一識別コード */
    poliOrgRelationPersonCode: number;

    /** 収支報告書記載団体関連者名称 */
    poliOrgRelationPersonName: string;

    /** 取引相手関連者Id */
    tradingRelationPersonId: number;

    /** 取引相手関連者同一識別コード */
    tradingRelationPersonCode: number;

    /** 取引相手関連者名称 */
    tradingRelationPersonName: string;

    /** 取引相手関連者名称 */
    tradingRelationPersonAddress: string;

    /** 収支報告書記載団体関連者名称 */
    poliOrgRelationPersonYakuari: string;

    /** 取引相手関連者名称 */
    tradingRelationPersonYakuari: string;

    constructor() {
        // 初期データ
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_DATE: Date = new Date(1947, 7, 28);

        this.wkTblUkaiKenkinResultEntityId = INIT_NUMBER;
        this.wkTblUkaiKenkinResultEntityCode = INIT_NUMBER;


        // 報告書記載政治団体
        this.politicalOrgId = INIT_NUMBER;
        this.politicalOrgCode = INIT_NUMBER;
        this.politicalOrgName = INIT_STRING;

        // 項目
        this.accrualDate = INIT_STRING;
        this.accrualDateValue = INIT_DATE;
        this.itemName = INIT_STRING;
        this.youshikiKbn = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.kingaku = INIT_NUMBER;
        this.pickupStage = INIT_NUMBER;
        this.renban = INIT_NUMBER;

        // 取引相手
        this.tradingPartnerId = INIT_NUMBER;
        this.tradingPartnerCode = INIT_NUMBER;
        this.tradingPartnerName = INIT_STRING;
        this.tradingPartnerAddress = INIT_STRING;

        // 記載側該当者名
        this.poliOrgRelationPersonId = INIT_NUMBER;
        this.poliOrgRelationPersonCode = INIT_NUMBER;
        this.poliOrgRelationPersonName = INIT_STRING;

        // 取引相手該当者名
        this.tradingRelationPersonId = INIT_NUMBER;
        this.tradingRelationPersonCode = INIT_NUMBER;
        this.tradingRelationPersonName = INIT_STRING;
        this.tradingRelationPersonAddress = INIT_STRING;

        // 役割
        this.poliOrgRelationPersonYakuari = INIT_STRING;
        this.tradingRelationPersonYakuari = INIT_STRING;
    }

}