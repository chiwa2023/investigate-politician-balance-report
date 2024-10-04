/**
 * 収支報告書収入情報Interface
 */
export default interface BalancesheetIncomeInterface {

    /** 項目区分 */
    itemIdKbn: number;

    /** ログインユーザ同一識別コード */
    loginUserCode: number;

    /** ログインユーザ名称 */
    loginUserName: string;

    /** 書証Id */
    shoshouId: string;

    /** 意見付記 */
    note: string;

    /** 関連者法人同一識別コード */
    relationCorporationCode: number;

    /** 収支報告書収入Id */
    balancesheetIncomeId: number;

    /** 発生日 */
    accrualDate: string;

    /** 備考 */
    biko: string;

    /** 参照した摘要 */
    referDigest: string;

    /** 関連者政治団体同一識別コード */
    politicalOrganizationCodee: number;

    /** 継続事業Id */
    continueBuissinessId: number;

    /** 寄付法人が上場または外資50%超え会社有無フラグ */
    isPrimeListedOrForeign: boolean;

    /** 書証識別コード */
    shoshouHistoryCode: number;

    /** 関連者個人Id */
    relationPersonId: number;

    /** 収支報告区分 */
    reportKbn: number;

    /** 氏名・団体名称 */
    professionOrgnizationName: string;

    /** 税額控除有無 */
    isCreditTax: boolean;

    /** 様式仕訳枝項目区分 */
    youshikiEdaKbn: number;

    /** 関連者個人同一識別コード */
    relationPersonCode: number;

    /** 継続事業同一識別コード */
    continueBuissinessCode: number;

    /** 政治資金パーティ名称 */
    partyName: string;

    /** 様式仕訳区分 */
    youshikiKbn: number;

    /** ユーザ所属政治団体名称 */
    politicalOrganizationName: string;

    /** 書証区分 */
    shoshouKbn: number;

    /** 入力済項目編集区分 */
    isEditAutoInput: boolean;

    /** 一般項目Id */
    generalItemId: number

    /** 読み取り時の行 */
    readingLine: number;

    /** ログインユーザId */
    loginUserId: number;

    /** 前例と異なる処理フラグ */
    isDifferPrecedent: boolean;

    /** 保全証票リスト */
    storagedDocumentIdList: string;

    /** 取引金額 */
    amount: number;

    /** 政治名称 */
    updateTime: Date;

    /** 一般項目同一識別コード */
    generalItemCode: number;

    /** 集めた期間終了日 */
    mediationEndDate: string;

    /** 個人・団体住所 */
    orgnizationAddress: string;

    /** 項目名称 */
    itemName: string;

    /** 収支報告書収入項目同一識別コード */
    balancesheetIncomeCode: number;

    /** パーティ開催日 */
    partyDate: string;

    /** ユーザ所属政治団体Id */
    politicalOrganizationId: number;

    /** 関連者区分 */
    relationKbn: number;

    /** 寄付が遺贈有無フラグ */
    isBequest: boolean;

    /** 最新区分 */
    saishinKbn: number;

    /** 政治名称 */
    relationPoliticsOrganizationId: number;

    /**関連者政治団体同一識別コード */
    relationPoliticsOrganizationCode: number;

    /** 関連者法人Id */
    relationCorporationId: number;

    /** 集めた期間開始日 */
    mediationStartDate: string;

    // eslint-disable-next-line semi
}

/**
 * 収支報告書収入情報Dto
 * BalancesheetIncomeDto 収支報告書収入データ
 */
export default class BalancesheetIncomeDto implements BalancesheetIncomeInterface {

    /** 項目区分 */
    itemIdKbn: number;

    /** ログインユーザ同一識別コード */
    loginUserCode: number;

    /** ログインユーザ名称 */
    loginUserName: string;

    /** 書証Id */
    shoshouId: string;

    /** 意見付記 */
    note: string;

    /** 関連者法人同一識別コード */
    relationCorporationCode: number;

    /** 収支報告書収入Id */
    balancesheetIncomeId: number;

    /** 発生日 */
    accrualDate: string;

    /** 備考 */
    biko: string;

    /** 参照した摘要 */
    referDigest: string;

    /** 関連者政治団体同一識別コード */
    politicalOrganizationCodee: number;

    /** 継続事業Id */
    continueBuissinessId: number;

    /** 寄付法人が上場または外資50%超え会社有無フラグ */
    isPrimeListedOrForeign: boolean;

    /** 書証識別コード */
    shoshouHistoryCode: number;

    /** 関連者個人Id */
    relationPersonId: number;

    /** 収支報告区分 */
    reportKbn: number;

    /** 氏名・団体名称 */
    professionOrgnizationName: string;

    /** 税額控除有無 */
    isCreditTax: boolean;

    /** 様式仕訳枝項目区分 */
    youshikiEdaKbn: number;

    /** 関連者個人同一識別コード */
    relationPersonCode: number;

    /** 継続事業同一識別コード */
    continueBuissinessCode: number;

    /** 政治資金パーティ名称 */
    partyName: string;

    /** 様式仕訳区分 */
    youshikiKbn: number;

    /** ユーザ所属政治団体名称 */
    politicalOrganizationName: string;

    /** 書証区分 */
    shoshouKbn: number;

    /** 入力済項目編集区分 */
    isEditAutoInput: boolean;

    /** 一般項目Id */
    generalItemId: number;

    /** 読み取り時の行 */
    readingLine: number;

    /** ログインユーザId */
    loginUserId: number;

    /** 前例と異なる処理フラグ */
    isDifferPrecedent: boolean;

    /** 保全証票リスト */
    storagedDocumentIdList: string;

    /** 取引金額 */
    amount: number;

    /** 更新時間 */
    updateTime: Date;

    /** 一般項目同一識別コード */
    generalItemCode: number;

    /** 集めた期間終了日 */
    mediationEndDate: string;

    /** 個人・団体住所 */
    orgnizationAddress: string;

    /** 項目名称 */
    itemName: string;

    /** 収支報告書収入項目同一識別コード */
    balancesheetIncomeCode: number;

    /** パーティ開催日 */
    partyDate: string;

    /** ユーザ所属政治団体Id */
    politicalOrganizationId: number;

    /** 関連者区分 */
    relationKbn: number;

    /** 寄付が遺贈有無フラグ */
    isBequest: boolean;

    /** 最新区分 */
    saishinKbn: number;

    /** 政治名称 */
    relationPoliticsOrganizationId: number;

    /**関連者政治団体同一識別コード */
    relationPoliticsOrganizationCode: number;

    /** 関連者法人Id */
    relationCorporationId: number;

    /** 集めた期間開始日 */
    mediationStartDate: string;

    /** 様式仕訳区分使用フラグ */
    isUseYoushikiKbn: boolean;
    /** 様式仕訳枝項目区分使用フラグ */
    isUseYoushikiEdaKbn: boolean;
    /** あっせん(期間)使用フラグ */
    isUseMediation: boolean;
    /** 項目名使用フラグ */
    isUseItemName: boolean;
    /** 組織・個人名使用フラグ */
    isUseOrgName: boolean;
    /** 個人・組織住所使用フラグ */
    isUseAddress: boolean;
    /** 政治資金パーティ開催日使用フラグ */
    isUsePartyDate: boolean;
    /** 備考使用フラグ */
    isUseBiko: boolean;
    /** 税額控除使用フラグ */
    isUseCreditTax: boolean;
    /** パーティ名称使用フラグ */
    isUsePartyName: boolean;
    /** 寄付遺贈使用フラグ */
    isUseBequest: boolean;
    /** 上場会社または50%外資会社使用フラグ */
    isUsePrimeListedOrForeign: boolean;

    /** 項目名称入力内容示唆*/
    attentionItemName: string;
    /** 個人・組織名称入力内容示唆 */
    attentionOrgName: string;
    /** 個人・組織住所入力内容示唆 */
    attentionAddress: string;
    /** 期間入力内容示唆 */
    attentionMediation: string;
    /** パーティ日付入力内容示唆 */
    attentionPartyDate: string;
    /** 税額控除内容示唆 */
    attentionCreditTax: string;
    /** パーティ名称名称入力内容示唆 */
    attentionPartyName: string;

    constructor() {
        this.readingLine = 0;
        this.shoshouId = "";
        this.shoshouHistoryCode = 0;
        this.shoshouKbn = 0;
        this.referDigest = "";
        this.isEditAutoInput = false;
        this.reportKbn = 0;
        this.youshikiKbn = 0;
        this.youshikiEdaKbn = 0;
        this.amount = 0;
        this.accrualDate = "1980-01-01";
        this.mediationStartDate = "1980-01-01";
        this.mediationEndDate = "1980-01-01";
        this.professionOrgnizationName = "";
        this.itemName = "";
        this.orgnizationAddress = "";
        this.partyDate = "1980-01-01";
        this.biko = "";
        this.isCreditTax = false;
        this.partyName = "";
        this.isBequest = false;
        this.isPrimeListedOrForeign = false;
        this.note = "";
        this.isDifferPrecedent = false;
        this.storagedDocumentIdList = "";
        this.itemIdKbn = 0;
        this.loginUserCode = 0;
        this.shoshouId = "";
        this.relationCorporationCode = 0;
        this.balancesheetIncomeId = 0;
        this.politicalOrganizationCodee = 0;
        this.continueBuissinessId = 0;
        this.shoshouHistoryCode = 0;
        this.relationPersonId = 0;
        this.relationPersonCode = 0;
        this.continueBuissinessCode = 0;
        this.politicalOrganizationName = "";
        this.shoshouKbn = 0;
        this.generalItemId = 0;
        this.loginUserId = 0;
        this.loginUserName = "";
        this.updateTime = new Date();
        this.generalItemCode = 0;
        this.balancesheetIncomeCode = 0;
        this.politicalOrganizationId = 0;
        this.relationKbn = 0;
        this.saishinKbn = 0;
        this.relationPoliticsOrganizationId = 0;
        this.relationPoliticsOrganizationCode = 0;
        this.relationCorporationId = 0;

        /* ここからは画面のみで使用する変数 */
        this.isUseYoushikiKbn = false;
        this.isUseYoushikiEdaKbn = false;
        this.isUseMediation = false;
        this.isUseOrgName = false;
        this.isUseItemName = false;
        this.isUseAddress = false;
        this.isUsePartyDate = false;
        this.isUseBiko = false;
        this.isUseCreditTax = false;
        this.isUsePartyName = false;
        this.isUseBequest = false;
        this.isUsePrimeListedOrForeign = false;
        this.attentionItemName = "";
        this.attentionOrgName = "";
        this.attentionAddress = "";
        this.attentionMediation = "";
        this.attentionPartyDate = "";
        this.attentionCreditTax = "";
        this.attentionPartyName = "";
    }
}