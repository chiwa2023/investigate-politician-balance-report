/**
 * 政治資金収支報告書シート様式7の1Interface
 *
 * @export
 * @interface Sheet070100CoverAndOrganizationDetailsInterface
 */
export default interface Sheet070100CoverAndOrganizationDetailsInterface {
    /** 報告年 */
    houkokuNen: number;

    /** 開催年月日 */
    dateKaisai: string;

    /** 政治団体名称 */
    dantaiName: string;

    /** 政治団体名称かな */
    dantaiNameKana: string;

    /** 事務所の住所 */
    jimushoJusho: string;

    /** 事務所の住所建物 */
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

    /** 国家議員3の姓 */
    kokkaiGiin3NameFirst: string;

    /** 国家議員3の公職(衆参) */
    kokkaiGiin3ShuuSan: string;

    /** 国家議員3現職と候補者の別 */
    kokkaiGiin3GenKouho: string;

}

/**
 * 政治資金収支報告書シート様式7の1Dto
 *
 * @export
 * @class Sheet070100CoverAndOrganizationDetailsDto
 * @implements {Sheet070100CoverAndOrganizationDetailsInterface}
 */
export default class Sheet070100CoverAndOrganizationDetailsDto implements Sheet070100CoverAndOrganizationDetailsInterface {

    /** 報告年 */
    houkokuNen: number;

    /** 開催年月日 */
    dateKaisai: string;

    /** 政治団体名称 */
    dantaiName: string;

    /** 政治団体名称かな */
    dantaiNameKana: string;

    /** 事務所の住所 */
    jimushoJusho: string;

    /** 事務所の住所建物 */
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

    /** 国家議員3の姓 */
    kokkaiGiin3NameFirst: string;

    /** 国家議員3の公職(衆参) */
    kokkaiGiin3ShuuSan: string;

    /** 国家議員3現職と候補者の別 */
    kokkaiGiin3GenKouho: string;

    /**
     * Creates an instance of Sheet070100CoverAndOrganizationDetailsDto.
     * @param {Sheet070100CoverAndOrganizationDetailsInterface} [sheet070100CoverAndOrganizationDetailsInterface]
     * @memberof Sheet070100CoverAndOrganizationDetailsDto
     */
    constructor(sheet070100CoverAndOrganizationDetailsInterface?: Sheet070100CoverAndOrganizationDetailsInterface) {

        /* 報告年 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.houkokuNen !== undefined) {
            this.houkokuNen = sheet070100CoverAndOrganizationDetailsInterface?.houkokuNen;
        } else {
            this.houkokuNen = 0;
        }
        /* 開催年月日 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.dateKaisai !== undefined) {
            this.dateKaisai = sheet070100CoverAndOrganizationDetailsInterface?.dateKaisai;
        } else {
            this.dateKaisai = "";
        }
        /* 政治団体名称 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.dantaiName !== undefined) {
            this.dantaiName = sheet070100CoverAndOrganizationDetailsInterface?.dantaiName;
        } else {
            this.dantaiName = "";
        }
        /* 政治団体名称かな */
        if (sheet070100CoverAndOrganizationDetailsInterface?.dantaiNameKana !== undefined) {
            this.dantaiNameKana = sheet070100CoverAndOrganizationDetailsInterface?.dantaiNameKana;
        } else {
            this.dantaiNameKana = "";
        }
        /* 事務所の住所 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimushoJusho !== undefined) {
            this.jimushoJusho = sheet070100CoverAndOrganizationDetailsInterface?.jimushoJusho;
        } else {
            this.jimushoJusho = "";
        }
        /* 事務所の住所建物 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimushoJushoTatemono !== undefined) {
            this.jimushoJushoTatemono = sheet070100CoverAndOrganizationDetailsInterface?.jimushoJushoTatemono;
        } else {
            this.jimushoJushoTatemono = "";
        }
        /* 代表者の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.daihyoushaNameLast !== undefined) {
            this.daihyoushaNameLast = sheet070100CoverAndOrganizationDetailsInterface?.daihyoushaNameLast;
        } else {
            this.daihyoushaNameLast = "";
        }
        /* 代表者の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.daihyoushaNameFirst !== undefined) {
            this.daihyoushaNameFirst = sheet070100CoverAndOrganizationDetailsInterface?.daihyoushaNameFirst;
        } else {
            this.daihyoushaNameFirst = "";
        }
        /* 会計責任者の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kaikeiSekinnshaNameLast !== undefined) {
            this.kaikeiSekinnshaNameLast = sheet070100CoverAndOrganizationDetailsInterface?.kaikeiSekinnshaNameLast;
        } else {
            this.kaikeiSekinnshaNameLast = "";
        }
        /* 会計責任者の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kaikeiSekinnshaNameFirst !== undefined) {
            this.kaikeiSekinnshaNameFirst = sheet070100CoverAndOrganizationDetailsInterface?.kaikeiSekinnshaNameFirst;
        } else {
            this.kaikeiSekinnshaNameFirst = "";
        }
        /* 事務担当者1の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha1NameLast !== undefined) {
            this.jimuTantousha1NameLast = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha1NameLast;
        } else {
            this.jimuTantousha1NameLast = "";
        }
        /* 事務担当者1の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha1NameFirst !== undefined) {
            this.jimuTantousha1NameFirst = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha1NameFirst;
        } else {
            this.jimuTantousha1NameFirst = "";
        }
        /* 事務担当者1の電話番号 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha1Tel !== undefined) {
            this.jimuTantousha1Tel = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha1Tel;
        } else {
            this.jimuTantousha1Tel = "";
        }
        /* 事務担当者2の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha2NameLast !== undefined) {
            this.jimuTantousha2NameLast = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha2NameLast;
        } else {
            this.jimuTantousha2NameLast = "";
        }
        /* 事務担当者2の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha2NameFirst !== undefined) {
            this.jimuTantousha2NameFirst = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha2NameFirst;
        } else {
            this.jimuTantousha2NameFirst = "";
        }
        /* 事務担当者2の電話番号 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha2Tel !== undefined) {
            this.jimuTantousha2Tel = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha2Tel;
        } else {
            this.jimuTantousha2Tel = "";
        }
        /* 事務担当者3の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha3NameLast !== undefined) {
            this.jimuTantousha3NameLast = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha3NameLast;
        } else {
            this.jimuTantousha3NameLast = "";
        }
        /* 事務担当者3の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha3NameFirst !== undefined) {
            this.jimuTantousha3NameFirst = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha3NameFirst;
        } else {
            this.jimuTantousha3NameFirst = "";
        }
        /* 事務担当者3の電話番号 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha3Tel !== undefined) {
            this.jimuTantousha3Tel = sheet070100CoverAndOrganizationDetailsInterface?.jimuTantousha3Tel;
        } else {
            this.jimuTantousha3Tel = "";
        }
        /* 団体区分 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.dantaiKbn !== undefined) {
            this.dantaiKbn = sheet070100CoverAndOrganizationDetailsInterface?.dantaiKbn;
        } else {
            this.dantaiKbn = "";
        }
        /* 活動区域区分 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.katsudouKuikiKbn !== undefined) {
            this.katsudouKuikiKbn = sheet070100CoverAndOrganizationDetailsInterface?.katsudouKuikiKbn;
        } else {
            this.katsudouKuikiKbn = 0;
        }
        /* 資金管理団体の有無 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.umuShikinKanrenDantai !== undefined) {
            this.umuShikinKanrenDantai = sheet070100CoverAndOrganizationDetailsInterface?.umuShikinKanrenDantai;
        } else {
            this.umuShikinKanrenDantai = 0;
        }
        /* 公職の名称 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.koushokuName !== undefined) {
            this.koushokuName = sheet070100CoverAndOrganizationDetailsInterface?.koushokuName;
        } else {
            this.koushokuName = "";
        }
        /* 現職候補者の別 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.koushokuGenKouho !== undefined) {
            this.koushokuGenKouho = sheet070100CoverAndOrganizationDetailsInterface?.koushokuGenKouho;
        } else {
            this.koushokuGenKouho = "";
        }
        /* 資金管理団体の設立者の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.shikinDaihyouName1 !== undefined) {
            this.shikinDaihyouName1 = sheet070100CoverAndOrganizationDetailsInterface?.shikinDaihyouName1;
        } else {
            this.shikinDaihyouName1 = "";
        }
        /* 資金管理団体の設立者の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.shikinDaihyouName2 !== undefined) {
            this.shikinDaihyouName2 = sheet070100CoverAndOrganizationDetailsInterface?.shikinDaihyouName2;
        } else {
            this.shikinDaihyouName2 = "";
        }
        /* 資金管理団体の指定期間(開始) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kanriDantaiPeriodStart !== undefined) {
            this.kanriDantaiPeriodStart = sheet070100CoverAndOrganizationDetailsInterface?.kanriDantaiPeriodStart;
        } else {
            this.kanriDantaiPeriodStart = "";
        }
        /* 資金管理団体の指定期間(終了) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kanriDantaiPeriodEnd !== undefined) {
            this.kanriDantaiPeriodEnd = sheet070100CoverAndOrganizationDetailsInterface?.kanriDantaiPeriodEnd;
        } else {
            this.kanriDantaiPeriodEnd = "";
        }
        /* 資金管理団体の複数指定期間 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kanriDantaiPeriodRest !== undefined) {
            this.kanriDantaiPeriodRest = sheet070100CoverAndOrganizationDetailsInterface?.kanriDantaiPeriodRest;
        } else {
            this.kanriDantaiPeriodRest = "";
        }
        /* 国会議員関連団体区分 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiinDantaiKbn !== undefined) {
            this.kokkaiGiinDantaiKbn = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiinDantaiKbn;
        } else {
            this.kokkaiGiinDantaiKbn = 0;
        }
        /* 国家議員1の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1NameLast !== undefined) {
            this.kokkaiGiin1NameLast = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1NameLast;
        } else {
            this.kokkaiGiin1NameLast = "";
        }
        /* 国家議員1の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1NameFirst !== undefined) {
            this.kokkaiGiin1NameFirst = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1NameFirst;
        } else {
            this.kokkaiGiin1NameFirst = "";
        }
        /* 国家議員1の公職(衆参) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1ShuuSan !== undefined) {
            this.kokkaiGiin1ShuuSan = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1ShuuSan;
        } else {
            this.kokkaiGiin1ShuuSan = "";
        }
        /* 国家議員1現職と候補者の別 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1GenKouho !== undefined) {
            this.kokkaiGiin1GenKouho = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin1GenKouho;
        } else {
            this.kokkaiGiin1GenKouho = "";
        }
        /* 国会議員関係団体の特例適用期間(開始) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.giinDantantaiTokureiPeriodStart !== undefined) {
            this.giinDantantaiTokureiPeriodStart = sheet070100CoverAndOrganizationDetailsInterface?.giinDantantaiTokureiPeriodStart;
        } else {
            this.giinDantantaiTokureiPeriodStart = "";
        }
        /* 国会議員関係団体の特例適用期間(終了) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.giinDantantaiTokureiPeriodEnd !== undefined) {
            this.giinDantantaiTokureiPeriodEnd = sheet070100CoverAndOrganizationDetailsInterface?.giinDantantaiTokureiPeriodEnd;
        } else {
            this.giinDantantaiTokureiPeriodEnd = "";
        }
        /* 国会議員関係団体の複数特例適用期間 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.giinDantantaiTokureiPeriodRest !== undefined) {
            this.giinDantantaiTokureiPeriodRest = sheet070100CoverAndOrganizationDetailsInterface?.giinDantantaiTokureiPeriodRest;
        } else {
            this.giinDantantaiTokureiPeriodRest = "";
        }
        /* 国家議員2の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2NameLast !== undefined) {
            this.kokkaiGiin2NameLast = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2NameLast;
        } else {
            this.kokkaiGiin2NameLast = "";
        }
        /* 国家議員2の名 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2NameFirst !== undefined) {
            this.kokkaiGiin2NameFirst = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2NameFirst;
        } else {
            this.kokkaiGiin2NameFirst = "";
        }
        /* 国家議員2の公職(衆参) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2ShuuSan !== undefined) {
            this.kokkaiGiin2ShuuSan = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2ShuuSan;
        } else {
            this.kokkaiGiin2ShuuSan = "";
        }
        /* 国家議員2現職と候補者の別 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2GenKouho !== undefined) {
            this.kokkaiGiin2GenKouho = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin2GenKouho;
        } else {
            this.kokkaiGiin2GenKouho = "";
        }
        /* 国家議員3の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3NameLast !== undefined) {
            this.kokkaiGiin3NameLast = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3NameLast;
        } else {
            this.kokkaiGiin3NameLast = "";
        }
        /* 国家議員3の姓 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3NameFirst !== undefined) {
            this.kokkaiGiin3NameFirst = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3NameFirst;
        } else {
            this.kokkaiGiin3NameFirst = "";
        }
        /* 国家議員3の公職(衆参) */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3ShuuSan !== undefined) {
            this.kokkaiGiin3ShuuSan = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3ShuuSan;
        } else {
            this.kokkaiGiin3ShuuSan = "";
        }
        /* 国家議員3現職と候補者の別 */
        if (sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3GenKouho !== undefined) {
            this.kokkaiGiin3GenKouho = sheet070100CoverAndOrganizationDetailsInterface?.kokkaiGiin3GenKouho;
        } else {
            this.kokkaiGiin3GenKouho = "";
        }
    }

}