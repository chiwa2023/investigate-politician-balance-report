import KobetsuKiseiMeisaiInterface from "../../entity/kobetsuKiseiMeisaiEntity";

export default interface KifuJougenTradingInfoInterface {

}

/**
 * 寄付上限用取引項目Dto
 */
export default class KifuJougenTradingInfoDto implements KifuJougenTradingInfoInterface {

    /** 寄付額合計 */
    sumKifu: number;

    /** 取引相手(政治団体)名称 */
    partnerName: string;

    /** 調査対象政治団体住所 */
    partnerAddress: string;

    /** 取引相手関連者Id */
    relationId: number;

    /** 取引相手関連者同一識別コード */
    relationCode: number;

    /** 様式枝区分 */
    youshikiEdaKbn: number;

    /** 上限額超え該否 */
    isLimitOver: boolean;

    /** 明細リスト */
    listTradingMeisai: KobetsuKiseiMeisaiInterface[];

    constructor() {

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.sumKifu = INIT_NUMBER;
        this.partnerName = INIT_STRING;
        this.partnerAddress = INIT_STRING;
        this.relationId = INIT_NUMBER;
        this.relationCode = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.isLimitOver = INIT_BOOLEAN;
        this.listTradingMeisai = [];

    }

}