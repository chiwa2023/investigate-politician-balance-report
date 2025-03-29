package mitei.mitei.investigate.report.balance.politician.dto.kanrensha;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 政治資金収支報告書を関連者基準で検索する場合の検索条件格納Dto
 */
public class KanrenshaBalancesheetConditionCapsuleDto extends AbstractCapsuleDto  implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 報告年 */
    private Integer houkokuNen = INIT_Integer;

    /** 関連者区分 */
    private Integer relationKbn = INIT_Integer;

    /** 関連者Id */
    private Long relationId = INIT_Long;

    /** 関連者同一識別コード */
    private Integer relationCode = INIT_Integer;

    /** 関連者名称 */
    private String relationName = INIT_String;

    /** 検索基準原文書名該否 */
    private Boolean isNameSearch = INIT_Boolean;

    /** 収入機関誌発行該否 */
    private Boolean isSearchJournal = INIT_Boolean;

    /** 収入借入金該否 */
    private Boolean isSearchBorrowed = INIT_Boolean;

    /** 収入交付金該否 */
    private Boolean isSearchRelatedlgrants = INIT_Boolean;

    /** 収入その他該否 */
    private Boolean isSearchOtherIncome = INIT_Boolean;

    /** 収入寄付個人該否 */
    private Boolean isSearchDonatePerson = INIT_Boolean;

    /** 収入寄付企業団体該否 */
    private Boolean isSearchDonateCorp = INIT_Boolean;

    /** 収入寄付政治団体該否 */
    private Boolean isSearchDonatePoliOrg = INIT_Boolean;

    /** 収入寄付あっせん該否 */
    private Boolean isSearchDonateMediate = INIT_Boolean;

    /** 収入匿名政党寄付該否 */
    private Boolean isSearchAnonymus = INIT_Boolean;

    /** 収入特定パーティ該否 */
    private Boolean isSearchSpecificParty = INIT_Boolean;

    /** 収入パーティ個人該否 */
    private Boolean isSearchPartyPerson = INIT_Boolean;

    /** 収入パーティ企業団体該否 */
    private Boolean isSearchPartyCorp = INIT_Boolean;

    /** 収入パーティ政治団体該否 */
    private Boolean isSearchPartyPoliOrg = INIT_Boolean;

    /** 収入パーティあっせん該否 */
    private Boolean isSearchPartyMediate = INIT_Boolean;

    /** 支出光熱費該否 */
    private Boolean isSearchKounetsuhi = INIT_Boolean;

    /** 支出消耗品該否 */
    private Boolean isSearchShoumouhin = INIT_Boolean;

    /** 支出事務所費該否 */
    private Boolean isSearchJimusho = INIT_Boolean;

    /** 支出活動費該否 */
    private Boolean isSearchActivation = INIT_Boolean;

    /** 支出選挙運動費該否 */
    private Boolean isSearchElection = INIT_Boolean;

    /** 支出機関誌発行費該否 */
    private Boolean isSearchPaper = INIT_Boolean;

    /** 支出宣伝広告費該否 */
    private Boolean isSearchComercial = INIT_Boolean;

    /** 支出パーティ費該否 */
    private Boolean isSearchPartyOutcome = INIT_Boolean;

    /** 支出その他事業費該否 */
    private Boolean isSearchBuissiness = INIT_Boolean;

    /** 支出調査研究費該否 */
    private Boolean isSearchResearch = INIT_Boolean;

    /** 支出寄付費該否 */
    private Boolean isSearchDonation = INIT_Boolean;

    /** 支出その他費用該否 */
    private Boolean isSearchOtherOutcome = INIT_Boolean;

    /** オフセット件数収入 */
    private Integer offset = INIT_Integer;

    /** ページ番号収入 */
    private Integer pageNumIncome = INIT_Integer;

    /** ページ番号支出 */
    private Integer pageNumOutcome = INIT_Integer;

    /**
     * 報告年を取得する
     *
     * @return 報告年
     */
    public Integer getHoukokuNen() {
        return houkokuNen;
    }

    /**
     * 報告年を設定する
     *
     * @param houkokuNen 報告年
     */
    public void setHoukokuNen(final Integer houkokuNen) {
        this.houkokuNen = houkokuNen;
    }

    /**
     * 関連者区分取得する
     *
     * @return 関連者区分
     */
    public Integer getRelationKbn() {
        return relationKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param relationKbn 関連者区分
     */
    public void setRelationKbn(final Integer relationKbn) {
        this.relationKbn = relationKbn;
    }

    /**
     * 関連者Idを取得する
     *
     * @return 関連者Id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 関連者Idを設定する
     *
     * @param relationId 関連者Id
     */
    public void setRelationId(final Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 関連者同一識別コードを取得する
     *
     * @return 関連者同一識別コード
     */
    public Integer getRelationCode() {
        return relationCode;
    }

    /**
     * 関連者同一識別コードを設定する
     *
     * @param relationCode 関連者同一識別コード
     */
    public void setRelationCode(final Integer relationCode) {
        this.relationCode = relationCode;
    }

    /**
     * 関連者名称を取得する
     *
     * @return 関連者名称
     */
    public String getRelationName() {
        return relationName;
    }

    /**
     * 関連者名称を設定する
     *
     * @param relationName 関連者名称
     */
    public void setRelationName(final String relationName) {
        this.relationName = relationName;
    }

    /**
     * 検索基準原文書名該否を取得する
     *
     * @return 検索基準原文書名該否
     */
    public Boolean getIsNameSearch() {
        return isNameSearch;
    }

    /**
     * 検索基準原文書名該否を設定する
     *
     * @param isNameSearch 検索基準原文書名該否
     */
    public void setIsNameSearch(final Boolean isNameSearch) {
        this.isNameSearch = isNameSearch;
    }

    /**
     * 収入機関誌発行該否を取得する
     *
     * @return 収入機関誌発行該否
     */
    public Boolean getIsSearchJournal() {
        return isSearchJournal;
    }

    /**
     * 収入機関誌発行該否を設定する
     *
     * @param isSearchJournal 収入機関誌発行該否
     */
    public void setIsSearchJournal(final Boolean isSearchJournal) {
        this.isSearchJournal = isSearchJournal;
    }

    /**
     * 収入借入金該否を取得する
     *
     * @return 収入借入金該否
     */
    public Boolean getIsSearchBorrowed() {
        return isSearchBorrowed;
    }

    /**
     * 収入借入金該否を設定する
     *
     * @param isSearchBorrowed 収入借入金該否
     */
    public void setIsSearchBorrowed(final Boolean isSearchBorrowed) {
        this.isSearchBorrowed = isSearchBorrowed;
    }

    /**
     * 収入交付金該否を取得する
     *
     * @return 収入交付金該否
     */
    public Boolean getIsSearchRelatedlgrants() {
        return isSearchRelatedlgrants;
    }

    /**
     * 収入交付金該否を設定する
     *
     * @param isSearchRelatedlgrants 収入交付金該否
     */
    public void setIsSearchRelatedlgrants(final Boolean isSearchRelatedlgrants) {
        this.isSearchRelatedlgrants = isSearchRelatedlgrants;
    }

    /**
     * 収入その他該否を取得する
     *
     * @return 収入その他該否
     */
    public Boolean getIsSearchOtherIncome() {
        return isSearchOtherIncome;
    }

    /**
     * 収入その他該否を設定する
     *
     * @param isSearchOtherIncome 収入その他該否
     */
    public void setIsSearchOtherIncome(final Boolean isSearchOtherIncome) {
        this.isSearchOtherIncome = isSearchOtherIncome;
    }

    /**
     * 収入寄付個人該否を取得する
     *
     * @return 収入寄付個人該否
     */
    public Boolean getIsSearchDonatePerson() {
        return isSearchDonatePerson;
    }

    /**
     * 収入寄付個人該否を設定する
     *
     * @param isSearchDonatePerson 収入寄付個人該否
     */
    public void setIsSearchDonatePerson(final Boolean isSearchDonatePerson) {
        this.isSearchDonatePerson = isSearchDonatePerson;
    }

    /**
     * 収入寄付企業団体該否を取得する
     *
     * @return 収入寄付企業団体該否
     */
    public Boolean getIsSearchDonateCorp() {
        return isSearchDonateCorp;
    }

    /**
     * 収入寄付企業団体該否を設定する
     *
     * @param isSearchDonateCorp 収入寄付企業団体該否
     */
    public void setIsSearchDonateCorp(final Boolean isSearchDonateCorp) {
        this.isSearchDonateCorp = isSearchDonateCorp;
    }

    /**
     * 収入寄付政治団体該否を取得する
     *
     * @return 収入寄付政治団体該否
     */
    public Boolean getIsSearchDonatePoliOrg() {
        return isSearchDonatePoliOrg;
    }

    /**
     * 収入寄付政治団体該否を設定する
     *
     * @param isSearchDonatePoliOrg 収入寄付政治団体該否
     */
    public void setIsSearchDonatePoliOrg(final Boolean isSearchDonatePoliOrg) {
        this.isSearchDonatePoliOrg = isSearchDonatePoliOrg;
    }

    /**
     * 収入寄付あっせん該否を取得する
     *
     * @return 収入寄付あっせん該否
     */
    public Boolean getIsSearchDonateMediate() {
        return isSearchDonateMediate;
    }

    /**
     * 収入寄付あっせん該否を設定する
     *
     * @param isSearchDonateMediate 収入寄付あっせん該否
     */
    public void setIsSearchDonateMediate(final Boolean isSearchDonateMediate) {
        this.isSearchDonateMediate = isSearchDonateMediate;
    }

    /**
     * 収入匿名政党寄付該否を取得する
     *
     * @return 収入匿名政党寄付該否
     */
    public Boolean getIsSearchAnonymus() {
        return isSearchAnonymus;
    }

    /**
     * 収入匿名政党寄付該否を設定する
     *
     * @param isSearchAnonymus 収入匿名政党寄付該否
     */
    public void setIsSearchAnonymus(final Boolean isSearchAnonymus) {
        this.isSearchAnonymus = isSearchAnonymus;
    }

    /**
     * 収入特定パーティ該否を取得する
     *
     * @return 収入特定パーティ該否
     */
    public Boolean getIsSearchSpecificParty() {
        return isSearchSpecificParty;
    }

    /**
     * 収入特定パーティ該否を設定する
     *
     * @param isSearchSpecificParty 収入特定パーティ該否
     */
    public void setIsSearchSpecificParty(final Boolean isSearchSpecificParty) {
        this.isSearchSpecificParty = isSearchSpecificParty;
    }

    /**
     * 収入パーティ個人該否を取得する
     *
     * @return 収入パーティ個人該否
     */
    public Boolean getIsSearchPartyPerson() {
        return isSearchPartyPerson;
    }

    /**
     * 収入パーティ個人該否を設定する
     *
     * @param isSearchPartyPerson 収入パーティ個人該否
     */
    public void setIsSearchPartyPerson(final Boolean isSearchPartyPerson) {
        this.isSearchPartyPerson = isSearchPartyPerson;
    }

    /**
     * 収入パーティ企業団体該否を取得する
     *
     * @return 収入パーティ企業団体該否
     */
    public Boolean getIsSearchPartyCorp() {
        return isSearchPartyCorp;
    }

    /**
     * 収入パーティ企業団体該否を設定する
     *
     * @param isSearchPartyCorp 収入パーティ企業団体該否
     */
    public void setIsSearchPartyCorp(final Boolean isSearchPartyCorp) {
        this.isSearchPartyCorp = isSearchPartyCorp;
    }

    /**
     * 収入パーティ政治団体該否を取得する
     *
     * @return 収入パーティ政治団体該否
     */
    public Boolean getIsSearchPartyPoliOrg() {
        return isSearchPartyPoliOrg;
    }

    /**
     * 収入パーティ政治団体該否を設定する
     *
     * @param isSearchPartyPoliOrg 収入パーティ政治団体該否
     */
    public void setIsSearchPartyPoliOrg(final Boolean isSearchPartyPoliOrg) {
        this.isSearchPartyPoliOrg = isSearchPartyPoliOrg;
    }

    /**
     * 収入パーティあっせん該否を取得する
     *
     * @return 収入パーティあっせん該否
     */
    public Boolean getIsSearchPartyMediate() {
        return isSearchPartyMediate;
    }

    /**
     * 収入パーティあっせん該否を設定する
     *
     * @param isSearchPartyMediate 収入パーティあっせん該否
     */
    public void setIsSearchPartyMediate(final Boolean isSearchPartyMediate) {
        this.isSearchPartyMediate = isSearchPartyMediate;
    }

    /**
     * 支出光熱費該否を取得する
     *
     * @return 支出光熱費該否
     */
    public Boolean getIsSearchKounetsuhi() {
        return isSearchKounetsuhi;
    }

    /**
     * 支出光熱費該否を設定する
     *
     * @param isSearchKounetsuhi 支出光熱費該否
     */
    public void setIsSearchKounetsuhi(final Boolean isSearchKounetsuhi) {
        this.isSearchKounetsuhi = isSearchKounetsuhi;
    }

    /**
     * 支出消耗品該否を取得する
     *
     * @return 支出消耗品該否
     */
    public Boolean getIsSearchShoumouhin() {
        return isSearchShoumouhin;
    }

    /**
     * 支出消耗品該否を設定する
     *
     * @param isSearchShoumouhin 支出消耗品該否
     */
    public void setIsSearchShoumouhin(final Boolean isSearchShoumouhin) {
        this.isSearchShoumouhin = isSearchShoumouhin;
    }

    /**
     * 支出事務所費該否を取得する
     *
     * @return 支出事務所費該否
     */
    public Boolean getIsSearchJimusho() {
        return isSearchJimusho;
    }

    /**
     * 支出事務所費該否を設定する
     *
     * @param isSearchJimusho 支出事務所費該否
     */
    public void setIsSearchJimusho(final Boolean isSearchJimusho) {
        this.isSearchJimusho = isSearchJimusho;
    }

    /**
     * 支出活動費該否を取得する
     *
     * @return 支出活動費該否
     */
    public Boolean getIsSearchActivation() {
        return isSearchActivation;
    }

    /**
     * 支出活動費該否を設定する
     *
     * @param isSearchActivation 支出活動費該否
     */
    public void setIsSearchActivation(final Boolean isSearchActivation) {
        this.isSearchActivation = isSearchActivation;
    }

    /**
     * 支出選挙運動費該否を取得する
     *
     * @return 支出選挙運動費該否
     */
    public Boolean getIsSearchElection() {
        return isSearchElection;
    }

    /**
     * 支出選挙運動費該否を設定する
     *
     * @param isSearchElection 支出選挙運動費該否
     */
    public void setIsSearchElection(final Boolean isSearchElection) {
        this.isSearchElection = isSearchElection;
    }

    /**
     * 支出機関誌発行費該否を取得する
     *
     * @return 支出機関誌発行費該否
     */
    public Boolean getIsSearchPaper() {
        return isSearchPaper;
    }

    /**
     * 支出機関誌発行費該否を設定する
     *
     * @param isSearchPaper 支出機関誌発行費該否
     */
    public void setIsSearchPaper(final Boolean isSearchPaper) {
        this.isSearchPaper = isSearchPaper;
    }

    /**
     * 支出宣伝広告費該否を取得する
     *
     * @return 支出宣伝広告費該否
     */
    public Boolean getIsSearchComercial() {
        return isSearchComercial;
    }

    /**
     * 支出宣伝広告費該否を設定する
     *
     * @param isSearchComercial 支出宣伝広告費該否
     */
    public void setIsSearchComercial(final Boolean isSearchComercial) {
        this.isSearchComercial = isSearchComercial;
    }

    /**
     * 支出パーティ費該否を取得する
     *
     * @return 支出パーティ費該否
     */
    public Boolean getIsSearchPartyOutcome() {
        return isSearchPartyOutcome;
    }

    /**
     * 支出パーティ費該否を設定する
     *
     * @param isSearchPartyOutcome 支出パーティ費該否
     */
    public void setIsSearchPartyOutcome(final Boolean isSearchPartyOutcome) {
        this.isSearchPartyOutcome = isSearchPartyOutcome;
    }

    /**
     * 支出その他事業費該否を取得する
     *
     * @return 支出その他事業費該否
     */
    public Boolean getIsSearchBuissiness() {
        return isSearchBuissiness;
    }

    /**
     * 支出その他事業費該否を設定する
     *
     * @param isSearchBuissiness 支出その他事業費該否
     */
    public void setIsSearchBuissiness(final Boolean isSearchBuissiness) {
        this.isSearchBuissiness = isSearchBuissiness;
    }

    /**
     * 支出調査研究費該否を取得する
     *
     * @return 支出調査研究費該否
     */
    public Boolean getIsSearchResearch() {
        return isSearchResearch;
    }

    /**
     * 支出調査研究費該否を設定する
     *
     * @param isSearchResearch 支出調査研究費該否
     */
    public void setIsSearchResearch(final Boolean isSearchResearch) {
        this.isSearchResearch = isSearchResearch;
    }

    /**
     * 支出寄付費該否を取得する
     *
     * @return 支出寄付費該否
     */
    public Boolean getIsSearchDonation() {
        return isSearchDonation;
    }

    /**
     * 支出寄付費該否を設定する
     *
     * @param isSearchDonation 支出寄付費該否
     */
    public void setIsSearchDonation(final Boolean isSearchDonation) {
        this.isSearchDonation = isSearchDonation;
    }

    /**
     * 支出その他費用該否を取得する
     *
     * @return 支出その他費用該否
     */
    public Boolean getIsSearchOtherOutcome() {
        return isSearchOtherOutcome;
    }

    /**
     * 支出その他費用該否を設定する
     *
     * @param isSearchOtherOutcome 支出その他費用該否
     */
    public void setIsSearchOtherOutcome(final Boolean isSearchOtherOutcome) {
        this.isSearchOtherOutcome = isSearchOtherOutcome;
    }

    /**
     * ページ番号収入を取得する
     *
     * @return ページ番号収入
     */
    public Integer getPageNumIncome() {
        return pageNumIncome;
    }

    /**
     * ページ番号収入を設定する
     *
     * @param pageNumIncome ページ番号収入
     */
    public void setPageNumIncome(final Integer pageNumIncome) {
        this.pageNumIncome = pageNumIncome;
    }

    /**
     * ページ番号支出を取得する
     *
     * @return ページ番号支出
     */
    public Integer getPageNumOutcome() {
        return pageNumOutcome;
    }

    /**
     * ページ番号支出を設定する
     *
     * @param pageNumOutcome ページ番号支出
     */
    public void setPageNumOutcome(final Integer pageNumOutcome) {
        this.pageNumOutcome = pageNumOutcome;
    }

    /**
     * オフセット件数収入を取得する
     *
     * @return オフセット件数収入
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * オフセット件数収入を設定する
     *
     * @param offset オフセット件数収入
     */
    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    /** 全件数(収入) */
    private Integer allCountIncome = INIT_Integer;

    /** 全件数(支出) */
    private Integer allCountOutcome = INIT_Integer;

    /**
     * 全件数(収入)を取得する
     *
     * @return 全件数(収入)
     */
    public Integer getAllCountIncome() {
        return allCountIncome;
    }

    /**
     * 全件数(収入)を設定する
     *
     * @param allCountIncome 全件数(収入)
     */
    public void setAllCountIncome(final Integer allCountIncome) {
        this.allCountIncome = allCountIncome;
    }

    /**
     * 全件数(支出)を取得する
     *
     * @return 全件数(支出)
     */
    public Integer getAllCountOutcome() {
        return allCountOutcome;
    }

    /**
     * 全件数(支出)を設定する
     *
     * @param allCountOutcome 全件数(支出)
     */
    public void setAllCountOutcome(final Integer allCountOutcome) {
        this.allCountOutcome = allCountOutcome;
    }

}
