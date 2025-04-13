import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface KanrenshaBalancesheetConditionCapsuleInterface {

}
/**
 * 寄付条件検索条件Dto
 */
export default class KanrenshaBalancesheetConditionCapsuleDto extends AbstractCapsuleDto implements KanrenshaBalancesheetConditionCapsuleInterface {

    /** 報告年 */
    houkokuNen: number;

    /** 関連者区分 */
    relationKbn: number;

    /** 関連者Id */
    relationId: number;

    /** 関連者同一識別コード */
    relationCode: number;

    /** 関連者名称 */
    relationName: string;

    /** 検索基準原文書名該否 */
    isNameSearch: boolean;

    /** 収入機関誌発行該否 */
    isSearchJournal: boolean;

    /** 収入借入金該否 */
    isSearchBorrowed: boolean;

    /** 収入交付金該否 */
    isSearchRelatedlgrants: boolean;

    /** 収入その他該否 */
    isSearchOtherIncome: boolean;

    /** 収入寄付個人該否 */
    isSearchDonatePerson: boolean;

    /** 収入寄付企業団体該否 */
    isSearchDonateCorp: boolean;

    /** 収入寄付政治団体該否 */
    isSearchDonatePoliOrg: boolean;

    /** 収入寄付あっせん該否 */
    isSearchDonateMediate: boolean;

    /** 収入匿名政党寄付該否 */
    isSearchAnonymus: boolean;

    /** 収入特定パーティ該否 */
    isSearchSpecificParty: boolean;

    /** 収入パーティ個人該否 */
    isSearchPartyPerson: boolean;

    /** 収入パーティ企業団体該否 */
    isSearchPartyCorp: boolean;

    /** 収入パーティ政治団体該否 */
    isSearchPartyPoliOrg: boolean;

    /** 収入パーティあっせん該否 */
    isSearchPartyMediate: boolean;

    /** 支出光熱費該否 */
    isSearchKounetsuhi: boolean;

    /** 支出消耗品該否 */
    isSearchShoumouhin: boolean;

    /** 支出事務所費該否 */
    isSearchJimusho: boolean;

    /** 支出活動費該否 */
    isSearchActivation: boolean;

    /** 支出選挙運動費該否 */
    isSearchElection: boolean;

    /** 支出機関誌発行費該否 */
    isSearchPaper: boolean;

    /** 支出宣伝広告費該否 */
    isSearchComercial: boolean;

    /** 支出パーティ費該否 */
    isSearchPartyOutcome: boolean;

    /** 支出その他事業費該否 */
    isSearchBuissiness: boolean;

    /** 支出調査研究費該否 */
    isSearchResearch: boolean;

    /** 支出寄付費該否 */
    isSearchDonation: boolean;

    /** 支出その他費用該否 */
    isSearchOtherOutcome: boolean;



    /** オフセット件数 */
    offset: number;

    /** ページ番号収入 */
    pageNumIncome: number;

    /** ページ番号支出 */
    pageNumOutcome: number;

    constructor() {

        super();

        // 初期データ
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;
        const INIT_STRING: string = "";

        this.houkokuNen = INIT_NUMBER;
        this.relationKbn = INIT_NUMBER;
        this.relationId = INIT_NUMBER;
        this.relationCode = INIT_NUMBER;
        this.relationName = INIT_STRING;
        this.isNameSearch = INIT_BOOLEAN;

        this.isSearchJournal = INIT_BOOLEAN;
        this.isSearchBorrowed = INIT_BOOLEAN;
        this.isSearchRelatedlgrants = INIT_BOOLEAN;
        this.isSearchOtherIncome = INIT_BOOLEAN;
        this.isSearchDonatePerson = INIT_BOOLEAN;
        this.isSearchDonateCorp = INIT_BOOLEAN;
        this.isSearchDonatePoliOrg = INIT_BOOLEAN;
        this.isSearchDonateMediate = INIT_BOOLEAN;
        this.isSearchAnonymus = INIT_BOOLEAN;
        this.isSearchSpecificParty = INIT_BOOLEAN;
        this.isSearchPartyPerson = INIT_BOOLEAN;
        this.isSearchPartyCorp = INIT_BOOLEAN;
        this.isSearchPartyPoliOrg = INIT_BOOLEAN;
        this.isSearchPartyMediate = INIT_BOOLEAN;
        this.isSearchKounetsuhi = INIT_BOOLEAN;
        this.isSearchShoumouhin = INIT_BOOLEAN;
        this.isSearchJimusho = INIT_BOOLEAN;
        this.isSearchActivation = INIT_BOOLEAN;
        this.isSearchElection = INIT_BOOLEAN;
        this.isSearchPaper = INIT_BOOLEAN;
        this.isSearchComercial = INIT_BOOLEAN;
        this.isSearchPartyOutcome = INIT_BOOLEAN;
        this.isSearchBuissiness = INIT_BOOLEAN;
        this.isSearchResearch = INIT_BOOLEAN;
        this.isSearchDonation = INIT_BOOLEAN;
        this.isSearchOtherOutcome = INIT_BOOLEAN;

        this.offset = INIT_NUMBER;
        this.pageNumIncome = INIT_NUMBER;
        this.pageNumOutcome = INIT_NUMBER;
    }

}