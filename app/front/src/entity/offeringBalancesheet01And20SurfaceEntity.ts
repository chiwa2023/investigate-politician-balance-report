export default interface OfferingBalancesheet01And20SurfaceInterface {

}

export default class OfferingBalancesheet01And20SurfaceEntity implements OfferingBalancesheet01And20SurfaceInterface {

    /** 収支報告書様式7その1と20Id */
    offeringBalancesheet0701And0720SurfaceId: number;

    /** 収支報告書様式7その1と20同一識別コード */
    offeringBalancesheet0701And0720SurfaceCode: number;

    /** 最新区分 */
    saishinKbn: number;

    /** ヘッダのバージョン番号 */
    version: string;

    /** ヘッダのアプリ名称 */
    appName: string;

    /** ファイルのフォーマット形式 */
    fileFormatNo: string;

    /** 告示フラグ */
    flgKokuji: string;

    /** 帳簿アプリ番号 */
    chouboAppVer: string;

    /** 情報入力有無テキスト */
    inputBitText: string;

    /** 報告年 */
    houkokuNen: number;

    /** 提出日 */
    offeringDate: Date;

    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    /** 原文書政治団体代表者名 */
    daihyouName: string;

    /** 原文書政治団体名称 */
    dantaiName: string;

    /** 関連者区分 */
    relationKbn: number;

    /** 代表者関連者Id */
    relationPersonIdDelegate: number;

    /** 代表者関連者同一識別コード */
    relationPersonCodeDelegate: number;

    /** 代表者関連者名称 */
    relationPersonNameDelegate: string;

    /** 開催年月日 */
    dateKaisai: string;

    /** 政治団体名称かな */
    dantaiNameKana: string;

    /** 様式7の1主たる事務所の住所 */
    jimushoJusho: string;

    /** 様式7の1主たる事務所の住所建物 */
    jimushoJushoTatemono: string;

    /** 代表者の姓 */
    daihyoushaNameLast: string;

    /** 代表者の名 */
    daihyoushaNameFirst: string;

    /** 会計責任者の姓 */
    kaikeiSekinnshaNameLast: string;

    /** 会計責任者の名 */
    kaikeiSekinnshaNameFirst: string;

    /** 事務担当者1の姓 */
    jimuTantousha1NameLast: string;

    /** 事務担当者1の名 */
    jimuTantousha1NameFirst: string;

    /** 事務担当者1の電話番号 */
    jimuTantousha1Tel: string;

    /** 事務担当者2の姓 */
    jimuTantousha2NameLast: string;

    /** 事務担当者2の名 */
    jimuTantousha2NameFirst: string;

    /** 事務担当者2の電話番号 */
    jimuTantousha2Tel: string;

    /** 事務担当者3の姓 */
    jimuTantousha3NameLast: string;

    /** 事務担当者3の名 */
    jimuTantousha3NameFirst: string;

    /** 事務担当者3の電話番号 */
    jimuTantousha3Tel: string;

    /** 団体区分 */
    dantaiKbn: string;

    /** 活動区域区分 */
    katsudouKuikiKbn: number;

    /** 資金管理団体の有無 */
    umuShikinKanrenDantai: number;

    /** 公職の名称 */
    koushokuName: string;

    /** 現職候補者の別 */
    koushokuGenKouho: string;

    /** 資金管理団体の設立者の姓 */
    shikinDaihyouName1: string;

    /** 資金管理団体の設立者の名 */
    shikinDaihyouName2: string;

    /** 資金管理団体の指定期間(開始) */
    kanriDantaiPeriodStart: string;

    /** 資金管理団体の指定期間(終了) */
    kanriDantaiPeriodEnd: string;

    /** 資金管理団体の複数指定期間 */
    kanriDantaiPeriodRest: string;

    /** 国会議員関連団体区分 */
    kokkaiGiinDantaiKbn: number;

    /** 国家議員1の姓 */
    kokkaiGiin1NameLast: string;

    /** 国家議員1の名 */
    kokkaiGiin1NameFirst: string;

    /** 国家議員1の公職(衆参) */
    kokkaiGiin1ShuuSan: string;

    /** 国家議員1現職と候補者の別 */
    kokkaiGiin1GenKouho: string;

    /** 国会議員関係団体の特例適用期間(開始) */
    giinDantantaiTokureiPeriodStart: string;

    /** 国会議員関係団体の特例適用期間(終了) */
    giinDantantaiTokureiPeriodEnd: string;

    /** 国会議員関係団体の複数特例適用期間 */
    giinDantantaiTokureiPeriodRest: string;

    /** 国家議員2の姓 */
    kokkaiGiin2NameLast: string;

    /** 国家議員2の名 */
    kokkaiGiin2NameFirst: string;

    /** 国家議員2の公職(衆参) */
    kokkaiGiin2ShuuSan: string;

    /** 国家議員2現職と候補者の別 */
    kokkaiGiin2GenKouho: string;

    /** 国家議員3の姓 */
    kokkaiGiin3NameLast: string;

    /** 国家議員3の名 */
    kokkaiGiin3NameFirst: string;

    /** 国家議員3の公職(衆参) */
    kokkaiGiin3ShuuSan: string;

    /** 国家議員3現職と候補者の別 */
    kokkaiGiin3GenKouho: string;

    /** 領収書の写しの有無 */
    flgReciptCopy: number;

    /** 監査意見書の有無 */
    flgKansaIkensho: number;

    /** 政治資金監査報告書 */
    flgSeijishikinHohkokusho: number;

    /** 宣誓日 */
    dateOath: string;

    /** 会計責任者姓名の姓 */
    kaikeiSekininshaNameLast: string;

    /** 会計責任者姓名の名 */
    kaikeiSekininshaNameFirst: string;

    constructor() {

        // 初期データ
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_DATE = new Date(1948, 7, 29);

        this.offeringBalancesheet0701And0720SurfaceId = INIT_NUMBER;
        this.offeringBalancesheet0701And0720SurfaceCode = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.version = INIT_STRING;
        this.appName = INIT_STRING;
        this.fileFormatNo = INIT_STRING;
        this.flgKokuji = INIT_STRING;
        this.chouboAppVer = INIT_STRING;
        this.inputBitText = INIT_STRING;
        this.houkokuNen = INIT_NUMBER;
        this.offeringDate = INIT_DATE;
        this.politicalOrganizationId = INIT_NUMBER;
        this.politicalOrganizationCode = INIT_NUMBER;
        this.politicalOrganizationName = INIT_STRING;
        this.daihyouName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.relationKbn = INIT_NUMBER;
        this.relationPersonIdDelegate = INIT_NUMBER;
        this.relationPersonCodeDelegate = INIT_NUMBER;
        this.relationPersonNameDelegate = INIT_STRING;
        this.dateKaisai = INIT_STRING;
        this.dantaiNameKana = INIT_STRING;
        this.jimushoJusho = INIT_STRING;
        this.jimushoJushoTatemono = INIT_STRING;
        this.daihyoushaNameLast = INIT_STRING;
        this.daihyoushaNameFirst = INIT_STRING;
        this.kaikeiSekinnshaNameLast = INIT_STRING;
        this.kaikeiSekinnshaNameFirst = INIT_STRING;
        this.jimuTantousha1NameLast = INIT_STRING;
        this.jimuTantousha1NameFirst = INIT_STRING;
        this.jimuTantousha1Tel = INIT_STRING;
        this.jimuTantousha2NameLast = INIT_STRING;
        this.jimuTantousha2NameFirst = INIT_STRING;
        this.jimuTantousha2Tel = INIT_STRING;
        this.jimuTantousha3NameLast = INIT_STRING;
        this.jimuTantousha3NameFirst = INIT_STRING;
        this.jimuTantousha3Tel = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.katsudouKuikiKbn = INIT_NUMBER;
        this.umuShikinKanrenDantai = INIT_NUMBER;
        this.koushokuName = INIT_STRING;
        this.koushokuGenKouho = INIT_STRING;
        this.shikinDaihyouName1 = INIT_STRING;
        this.shikinDaihyouName2 = INIT_STRING;
        this.kanriDantaiPeriodStart = INIT_STRING;
        this.kanriDantaiPeriodEnd = INIT_STRING;
        this.kanriDantaiPeriodRest = INIT_STRING;
        this.kokkaiGiinDantaiKbn = INIT_NUMBER;
        this.kokkaiGiin1NameLast = INIT_STRING;
        this.kokkaiGiin1NameFirst = INIT_STRING;
        this.kokkaiGiin1ShuuSan = INIT_STRING;
        this.kokkaiGiin1GenKouho = INIT_STRING;
        this.giinDantantaiTokureiPeriodStart = INIT_STRING;
        this.giinDantantaiTokureiPeriodEnd = INIT_STRING;
        this.giinDantantaiTokureiPeriodRest = INIT_STRING;
        this.kokkaiGiin2NameLast = INIT_STRING;
        this.kokkaiGiin2NameFirst = INIT_STRING;
        this.kokkaiGiin2ShuuSan = INIT_STRING;
        this.kokkaiGiin2GenKouho = INIT_STRING;
        this.kokkaiGiin3NameLast = INIT_STRING;
        this.kokkaiGiin3NameFirst = INIT_STRING;
        this.kokkaiGiin3ShuuSan = INIT_STRING;
        this.kokkaiGiin3GenKouho = INIT_STRING;
        this.flgReciptCopy = INIT_NUMBER;
        this.flgKansaIkensho = INIT_NUMBER;
        this.flgSeijishikinHohkokusho = INIT_NUMBER;
        this.dateOath = INIT_STRING;
        this.kaikeiSekininshaNameLast = INIT_STRING;
        this.kaikeiSekininshaNameFirst = INIT_STRING;

    }
}