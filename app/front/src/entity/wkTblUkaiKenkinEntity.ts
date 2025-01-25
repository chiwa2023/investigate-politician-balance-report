export default interface WkTblUkaiKenkinInterface {

}

export default class WkTblUkaiKenkinEntity implements WkTblUkaiKenkinInterface {

    /** 迂回献金キャッチャー用ワークテーブルId  */
    wkTblUkaiKenkinEntityId: number;

    /** 迂回献金キャッチャー用ワークテーブル同一識別コード  */
    wkTblUkaiKenkinEntityCode: number;

    /** 収支報告書記載団体代表者Id */
    poliOrgDelegateId: number;

    /** 収支報告書記載団体代表者同一識別コード */
    poliOrgDelegateCode: number;

    /** 収支報告書記載団体代表者名称 */
    poliOrgDelegateName: string;

    /** 収支報告書記載団体会計責任者Id */
    poliOrgAccountManagerId: number;

    /** 収支報告書記載団体会計責任者同一識別コード */
    poliOrgAccountManagerCode: number;

    /** 収支報告書記載団体会計責任者名称 */
    poliOrgAccountManagerName: string;

    /** 収支報告書資金管理団体登録者Id */
    poliOrgShikinDantaiId: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    poliOrgShikinDantaiCode: number;

    /** 収支報告書資金管理団体登録者名称 */
    poliOrgShikinDantaiName: string;

    /** 収支報告書国会銀関連Id */
    poliOrgKokkaiGiin1Id: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    poliOrgKokkaiGiin1Code: number;

    /** 収支報告書資金管理団体登録者名称 */
    poliOrgKokkaiGiin1Name: string;

    /** 収支報告書国会銀関連Id */
    poliOrgKokkaiGiin2Id: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    poliOrgKokkaiGiin2Code: number;

    /** 収支報告書資金管理団体登録者名称 */
    poliOrgKokkaiGiin2Name: string;

    /** 収支報告書国会銀関連Id */
    poliOrgKokkaiGiin3Id: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    poliOrgKokkaiGiin3Code: number;

    /** 収支報告書資金管理団体登録者名称 */
    poliOrgKokkaiGiin3Name: string;

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

    /** 取引相手団体代表者Id */
    tradingPartnerDelegateId: number;

    /** 取引相手団体代表者同一識別コード */
    tradingPartnerDelegateCode: number;

    /** 取引相手団体代表者(保存はshokugyou) */
    tradingPartnerDelegateName: string;

    /** 収支報告書記載団体会計責任者Id */
    tradingOrgAccountManagerId: number;

    /** 収支報告書記載団体会計責任者同一識別コード */
    tradingOrgAccountManagerCode: number;

    /** 収支報告書記載団体会計責任者名称 */
    tradingOrgAccountManagerName: string;

    /** 収支報告書資金管理団体登録者Id */
    tradingOrgShikinDantaiId: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    tradingOrgShikinDantaiCode: number;

    /** 収支報告書資金管理団体登録者名称 */
    tradingOrgShikinDantaiName: string;

    /** 収支報告書国会銀関連Id */
    tradingOrgKokkaiGiin1Id: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    tradingOrgKokkaiGiin1Code: number;

    /** 収支報告書資金管理団体登録者名称 */
    tradingOrgKokkaiGiin1Name: string;

    /** 収支報告書国会銀関連Id */
    tradingOrgKokkaiGiin2Id: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    tradingOrgKokkaiGiin2Code: number;

    /** 収支報告書資金管理団体登録者名称 */
    tradingOrgKokkaiGiin2Name: string;

    /** 収支報告書国会銀関連Id */
    tradingOrgKokkaiGiin3Id: number;

    /** 収支報告書資金管理団体登録者同一識別コード */
    tradingOrgKokkaiGiin3Code: number;

    /** 収支報告書資金管理団体登録者名称 */
    tradingOrgKokkaiGiin3Name: string;



    constructor() {
        // 初期データ
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_DATE: Date = new Date(1947, 7, 28);

        this.wkTblUkaiKenkinEntityId = INIT_NUMBER;
        this.wkTblUkaiKenkinEntityCode = INIT_NUMBER;

        // 報告書記載団体代表者
        this.poliOrgDelegateId = INIT_NUMBER;
        this.poliOrgDelegateCode = INIT_NUMBER;
        this.poliOrgDelegateName = INIT_STRING;

        // 報告書記載団体会計責任者
        this.poliOrgAccountManagerId = INIT_NUMBER;
        this.poliOrgAccountManagerCode = INIT_NUMBER;
        this.poliOrgAccountManagerName = INIT_STRING;

        // 報告書記載団体資金管理団体責任者
        this.poliOrgShikinDantaiId = INIT_NUMBER;
        this.poliOrgShikinDantaiCode = INIT_NUMBER;
        this.poliOrgShikinDantaiName = INIT_STRING;

        // 報告書記載団体関連国会議員1
        this.poliOrgKokkaiGiin1Id = INIT_NUMBER;
        this.poliOrgKokkaiGiin1Code = INIT_NUMBER;
        this.poliOrgKokkaiGiin1Name = INIT_STRING;

        // 報告書記載団体関連国会議員2
        this.poliOrgKokkaiGiin2Id = INIT_NUMBER;
        this.poliOrgKokkaiGiin2Code = INIT_NUMBER;
        this.poliOrgKokkaiGiin2Name = INIT_STRING;

        // 報告書記載団体関連国会議員3
        this.poliOrgKokkaiGiin3Id = INIT_NUMBER;
        this.poliOrgKokkaiGiin3Code = INIT_NUMBER;
        this.poliOrgKokkaiGiin3Name = INIT_STRING;

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

        //取引相手団体代表者
        this.tradingPartnerDelegateId = INIT_NUMBER;
        this.tradingPartnerDelegateCode = INIT_NUMBER;
        this.tradingPartnerDelegateName = INIT_STRING;

        // 取引団体会計責任者
        this.tradingOrgAccountManagerId = INIT_NUMBER;
        this.tradingOrgAccountManagerCode = INIT_NUMBER;
        this.tradingOrgAccountManagerName = INIT_STRING;

        // 取引団体資金管理団体責任者
        this.tradingOrgShikinDantaiId = INIT_NUMBER;
        this.tradingOrgShikinDantaiCode = INIT_NUMBER;
        this.tradingOrgShikinDantaiName = INIT_STRING;

        // 取引団体団体関連国会議員1
        this.tradingOrgKokkaiGiin1Id = INIT_NUMBER;
        this.tradingOrgKokkaiGiin1Code = INIT_NUMBER;
        this.tradingOrgKokkaiGiin1Name = INIT_STRING;

        // 取引団体団体関連国会議員2
        this.tradingOrgKokkaiGiin2Id = INIT_NUMBER;
        this.tradingOrgKokkaiGiin2Code = INIT_NUMBER;
        this.tradingOrgKokkaiGiin2Name = INIT_STRING;

        // 取引団体団体関連国会議員3
        this.tradingOrgKokkaiGiin3Id = INIT_NUMBER;
        this.tradingOrgKokkaiGiin3Code = INIT_NUMBER;
        this.tradingOrgKokkaiGiin3Name = INIT_STRING;

    }

}