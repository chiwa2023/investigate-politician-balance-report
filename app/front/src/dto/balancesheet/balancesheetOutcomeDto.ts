import SelectOptionInterface from "../selectOptionDto";

/**
 *収支報告書支出データInterface
 */
export default interface BalancesheetOutcomeInterface {

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

    /** 領収書を徴しがたかったもの */
    notCollectReciptKbn: number;

    /** 発生日 */
    accrualDate: string;

    /** 支出項目同一識別コード */
    balancesheetOutcomeCode: number;

    /** 備考 */
    biko: string;

    /** 参照した摘要 */
    referDigest: string;

    /** ユーザ所属政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 継続事業Id */
    continueBuissinessId: number;

    /** 収支報告書支出項目Id */
    balancesheetOutcomeId: number;

    /** 書証識別コード */
    shoshouHistoryCode: number;

    /** 関連者個人Id */
    relationPersonId: number;

    /** 収支報告区分 */
    reportKbn: number;

    /** 氏名・団体名称 */
    professionOrgnizationName: string;

    /** 支出大項目 */
    categorizeGroup: string;

    /** 更新時間 */
    updateTime: Date;

    /** 様式仕訳枝項目区分 */
    youshikiEdaKbn: number;

    /** 関連者個人同一識別コード */
    relationPersonCode: number;

    /** 継続事業同一識別コード */
    continueBuissinessCode: number;

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
    storagedDocumentIdList: string[];

    /** 交付金に係る支出 */
    isExpendituresRelatedGrants: boolean;

    /** 取引金額 */
    amount: number;

    /** 一般項目同一識別コード */
    generalItemCode: number;

    /** 団体住所 */
    orgnizationAddress: string;

    /** 支出項目 */
    itemName: string;

    /** ユーザ所属政治団体Id */
    politicalOrganizationId: number;

    /** 関連者区分 */
    relationKbn: number;

    /** 最新区分 */
    saishinKbn: number;

    /** ユーザ所属政治団体Id */
    relationPoliticsOrganizationId: number;

    /** 関連者政治団体同一識別コード */
    relationPoliticsOrganizationCode: number;

    /** 関連者法人Id */
    relationCorporationId: number;

    /** 様式仕訳枝項目区分個別用選択リスト */
    youshikiEdaKbnOptions: SelectOptionInterface[];

    // eslint-disable-next-line semi
}

/**
 *収支報告書支出データDto
 */
export default class BalancesheetIncomeDto implements BalancesheetOutcomeInterface {


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

    /** 領収書を徴しがたかったもの */
    notCollectReciptKbn: number;

    /** 発生日 */
    accrualDate: string;

    /** 支出項目同一識別コード */
    balancesheetOutcomeCode: number;

    /** 備考 */
    biko: string;

    /** 参照した摘要 */
    referDigest: string;

    /** ユーザ所属政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 継続事業Id */
    continueBuissinessId: number;

    /** 収支報告書支出項目Id */
    balancesheetOutcomeId: number;

    /** 書証識別コード */
    shoshouHistoryCode: number;

    /** 関連者個人Id */
    relationPersonId: number;

    /** 収支報告区分 */
    reportKbn: number;

    /** 氏名・団体名称 */
    professionOrgnizationName: string;

    /** 支出大項目 */
    categorizeGroup: string;

    /** 更新時間 */
    updateTime: Date;

    /** 様式仕訳枝項目区分 */
    youshikiEdaKbn: number;

    /** 関連者個人同一識別コード */
    relationPersonCode: number;

    /** 継続事業同一識別コード */
    continueBuissinessCode: number;

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
    storagedDocumentIdList: string[];

    /** 交付金に係る支出 */
    isExpendituresRelatedGrants: boolean;

    /** 取引金額 */
    amount: number;

    /** 一般項目同一識別コード */
    generalItemCode: number;

    /** 団体住所 */
    orgnizationAddress: string;

    /** 支出項目 */
    itemName: string;

    /** ユーザ所属政治団体Id */
    politicalOrganizationId: number;

    /** 関連者区分 */
    relationKbn: number;

    /** 最新区分 */
    saishinKbn: number;

    /** ユーザ所属政治団体Id */
    relationPoliticsOrganizationId: number;

    /** 関連者政治団体同一識別コード */
    relationPoliticsOrganizationCode: number;

    /** 関連者法人Id */
    relationCorporationId: number;





    /** 様式仕訳枝項目区分個別用選択リスト */
    youshikiEdaKbnOptions: SelectOptionInterface[];

    /** 様式区分使用フラグ */
    isUseYoushikiKbn: boolean;
    /** 様式区分小項目使用フラグ */
    isUseYoushikiEdaKbn: boolean;
    /** 項目名称使用フラグ */
    isUseItemName: boolean;
    /** 組織名称使用フラグ */
    isUseOrgName: boolean;
    /** 団体住所使用フラグ */
    isUseAddress: boolean;
    /** カテゴリ分類使用フラグ */
    isUseCategorizedGroup: boolean;
    /** 備考使用フラグ */
    isUseBiko: boolean;
    /** 交付金に係る使用フラグ */
    isUseRelatedGrants: boolean;
    /** 領収書を徴しがたかったもの使用フラグ */
    isUseCollectRecipt: boolean;

    /**
     * Creates an instance of BalancesheetIncomeDto.
     */
    constructor() {
        /** 読み取り時の行 */
        this.readingLine = 0;
        this.shoshouId = "";
        this.shoshouHistoryCode = 0;
        this.shoshouKbn = 0;
        this.referDigest = "";
        this.isEditAutoInput = false;
        this.reportKbn = 0;
        this.youshikiKbn = 0;
        this.youshikiEdaKbn = 0;
        this.youshikiEdaKbnOptions = [];
        this.amount = 0;
        this.accrualDate = "1980-01-01";
        this.categorizeGroup = "";
        this.professionOrgnizationName = "";
        this.itemName = "";
        this.orgnizationAddress = "";
        this.biko = "";
        this.isExpendituresRelatedGrants = false;
        this.note = "";
        this.isDifferPrecedent = false;
        this.notCollectReciptKbn = 0;
        this.storagedDocumentIdList = [];
        this.itemIdKbn = 0;
        this.loginUserCode = 0;
        this.shoshouId = "";
        this.relationCorporationCode = 0;
        this.balancesheetOutcomeCode = 0;
        this.politicalOrganizationCode = 0;
        this.continueBuissinessId = 0;
        this.balancesheetOutcomeId = 0;
        this.shoshouHistoryCode = 0;
        this.relationPersonId = 0;
        this.updateTime = new Date();
        this.relationPersonCode = 0;
        this.continueBuissinessCode = 0;
        this.politicalOrganizationName = "";
        this.shoshouKbn = 0;
        this.generalItemId = 0;
        this.loginUserId = 0;
        this.loginUserName = "";
        this.generalItemCode = 0;
        this.politicalOrganizationId = 0;
        this.relationKbn = 0;
        this.saishinKbn = 0;
        this.relationPoliticsOrganizationId = 0;
        this.relationPoliticsOrganizationCode = 0;
        this.relationCorporationId = 0;
        this.isUseYoushikiKbn = true;
        this.isUseYoushikiEdaKbn = true;
        this.isUseOrgName = true;
        this.isUseItemName = true;
        this.isUseAddress = true;
        this.isUseCategorizedGroup = true;
        this.isUseBiko = true;
        this.isUseCollectRecipt = true;
        this.isUseRelatedGrants = true;
    }
}