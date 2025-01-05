package mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * offering_balancesheet_outcome_2025接続用Entity
 */
@Entity
@Table(name = "offering_balancesheet_outcome_2025")
public class OfferingBalancesheetOutcome2025Entity implements Serializable, AllTabeDataHistoryInterface { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** 収支報告書支出(その14と15)Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offering_balancesheet_outcome_id")
    private Long offeringBalancesheetOutcomeId = INIT_Long;

    /**
     * 収支報告書支出(その14と15)Idを取得する
     *
     * @return 収支報告書支出(その14と15)Id
     */
    public Long getOfferingBalancesheetOutcomeId() {
        return offeringBalancesheetOutcomeId;
    }

    /**
     * 収支報告書支出(その14と15)Idを設定する
     *
     * @param offeringBalancesheetOutcomeId 収支報告書支出(その14と15)Id
     */
    public void setOfferingBalancesheetOutcomeId(final Long offeringBalancesheetOutcomeId) {
        this.offeringBalancesheetOutcomeId = offeringBalancesheetOutcomeId;
    }

    /** 収支報告書支出(その14と15)同一識別コード */
    @Column(name = "offering_balancesheet_outcome_code")
    private Long offeringBalancesheetOutcomeCode = INIT_Long;

    /**
     * 収支報告書支出(その14と15)同一識別コードを取得する
     *
     * @return 収支報告書支出(その14と15)同一識別コード
     */
    public Long getOfferingBalancesheetOutcomeCode() {
        return offeringBalancesheetOutcomeCode;
    }

    /**
     * 収支報告書支出(その14と15)同一識別コードを設定する
     *
     * @param offeringBalancesheetOutcomeCode 収支報告書支出(その14と15)同一識別コード
     */
    public void setOfferingBalancesheetOutcomeCode(final Long offeringBalancesheetOutcomeCode) {
        this.offeringBalancesheetOutcomeCode = offeringBalancesheetOutcomeCode;
    }

    /** 最新区分 */
    @Column(name = "saishin_kbn")
    private Integer saishinKbn = INIT_Integer;

    /**
     * 最新区分を取得する
     *
     * @return 最新区分
     */
    @Override
    public Integer getSaishinKbn() {
        return saishinKbn;
    }

    /**
     * 最新区分を設定する
     *
     * @param saishinKbn 最新区分
     */
    @Override
    public void setSaishinKbn(final Integer saishinKbn) {
        this.saishinKbn = saishinKbn;
    }

    /** 文書同一識別コード */
    @Column(name = "document_code")
    private Long documentCode = INIT_Long;

    /**
     * 文書同一識別コードを取得する
     *
     * @return 文書同一識別コード
     */
    public Long getDocumentCode() {
        return documentCode;
    }

    /**
     * 文書同一識別コードを設定する
     *
     * @param documentCode 文書同一識別コード
     */
    public void setDocumentCode(final Long documentCode) {
        this.documentCode = documentCode;
    }

    /** 報告年 */
    @Column(name = "houkoku_nen")
    private Integer houkokuNen = INIT_Integer;

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

    /** 提出日 */
    @Column(name = "offering_date")
    private LocalDate offeringDate = INIT_LocalDate;

    /**
     * 提出日を取得する
     *
     * @return 提出日
     */
    public LocalDate getOfferingDate() {
        return offeringDate;
    }

    /**
     * 提出日を設定する
     *
     * @param offeringDate 提出日
     */
    public void setOfferingDate(final LocalDate offeringDate) {
        this.offeringDate = offeringDate;
    }

    /** 政治団体Id */
    @Column(name = "political_organization_id")
    private Long politicalOrganizationId = INIT_Long;

    /**
     * 政治団体Idを取得する
     *
     * @return 政治団体Id
     */
    public Long getPoliticalOrganizationId() {
        return politicalOrganizationId;
    }

    /**
     * 政治団体Idを設定する
     *
     * @param politicalOrganizationId 政治団体Id
     */
    public void setPoliticalOrganizationId(final Long politicalOrganizationId) {
        this.politicalOrganizationId = politicalOrganizationId;
    }

    /** 政治団体同一識別コード */
    @Column(name = "political_organization_code")
    private Integer politicalOrganizationCode = INIT_Integer;

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Integer getPoliticalOrganizationCode() {
        return politicalOrganizationCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param politicalOrganizationCode 政治団体同一識別コード
     */
    public void setPoliticalOrganizationCode(final Integer politicalOrganizationCode) {
        this.politicalOrganizationCode = politicalOrganizationCode;
    }

    /** 政治団体名称 */
    @Column(name = "political_organization_name")
    private String politicalOrganizationName = INIT_String;

    /**
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getPoliticalOrganizationName() {
        return politicalOrganizationName;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param politicalOrganizationName 政治団体名称
     */
    public void setPoliticalOrganizationName(final String politicalOrganizationName) {
        this.politicalOrganizationName = politicalOrganizationName;
    }

    /** 原文書政治団体代表者名 */
    @Column(name = "daihyou_name")
    private String daihyouName = INIT_String;

    /**
     * 原文書政治団体代表者名を取得する
     *
     * @return 原文書政治団体代表者名
     */
    public String getDaihyouName() {
        return daihyouName;
    }

    /**
     * 原文書政治団体代表者名を設定する
     *
     * @param daihyouName 原文書政治団体代表者名
     */
    public void setDaihyouName(final String daihyouName) {
        this.daihyouName = daihyouName;
    }

    /** 原文書政治団体名称 */
    @Column(name = "dantai_name")
    private String dantaiName = INIT_String;

    /**
     * 原文書政治団体名称を取得する
     *
     * @return 原文書政治団体名称
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 原文書政治団体名称を設定する
     *
     * @param dantaiName 原文書政治団体名称
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /** 関連者区分 */
    @Column(name = "relation_kbn")
    private Integer relationKbn = INIT_Integer;

    /**
     * 関連者区分を取得する
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

    /** 代表者関連者Id */
    @Column(name = "relation_person_id_delegate")
    private Long relationPersonIdDelegate = INIT_Long;

    /**
     * 代表者関連者Idを取得する
     *
     * @return 代表者関連者Id
     */
    public Long getRelationPersonIdDelegate() {
        return relationPersonIdDelegate;
    }

    /**
     * 代表者関連者Idを設定する
     *
     * @param relationPersonIdDelegate 代表者関連者Id
     */
    public void setRelationPersonIdDelegate(final Long relationPersonIdDelegate) {
        this.relationPersonIdDelegate = relationPersonIdDelegate;
    }

    /** 代表者関連者同一識別コード */
    @Column(name = "relation_person_code_delegate")
    private Integer relationPersonCodeDelegate = INIT_Integer;

    /**
     * 代表者関連者同一識別コードを取得する
     *
     * @return 代表者関連者同一識別コード
     */
    public Integer getRelationPersonCodeDelegate() {
        return relationPersonCodeDelegate;
    }

    /**
     * 代表者関連者同一識別コードを設定する
     *
     * @param relationPersonCodeDelegate 代表者関連者同一識別コード
     */
    public void setRelationPersonCodeDelegate(final Integer relationPersonCodeDelegate) {
        this.relationPersonCodeDelegate = relationPersonCodeDelegate;
    }

    /** 代表者関連者名称 */
    @Column(name = "relation_person_name_delegate")
    private String relationPersonNameDelegate = INIT_String;

    /**
     * 代表者関連者名称を取得する
     *
     * @return 代表者関連者名称
     */
    public String getRelationPersonNameDelegate() {
        return relationPersonNameDelegate;
    }

    /**
     * 代表者関連者名称を設定する
     *
     * @param relationPersonNameDelegate 代表者関連者名称
     */
    public void setRelationPersonNameDelegate(final String relationPersonNameDelegate) {
        this.relationPersonNameDelegate = relationPersonNameDelegate;
    }

    /** 様式区分 */
    @Column(name = "youshiki_kbn")
    private Integer youshikiKbn = INIT_Integer;

    /**
     * 様式区分を取得する
     *
     * @return 様式区分
     */
    public Integer getYoushikiKbn() {
        return youshikiKbn;
    }

    /**
     * 様式区分を設定する
     *
     * @param youshikiKbn 様式区分
     */
    public void setYoushikiKbn(final Integer youshikiKbn) {
        this.youshikiKbn = youshikiKbn;
    }

    /** 様式枝区分項目 */
    @Column(name = "youshiki_eda_kbn")
    private Integer youshikiEdaKbn = INIT_Integer;

    /**
     * 様式枝区分項目を取得する
     *
     * @return 様式枝区分項目
     */
    public Integer getYoushikiEdaKbn() {
        return youshikiEdaKbn;
    }

    /**
     * 様式枝区分項目を設定する
     *
     * @param youshikiEdaKbn 様式枝区分項目
     */
    public void setYoushikiEdaKbn(final Integer youshikiEdaKbn) {
        this.youshikiEdaKbn = youshikiEdaKbn;
    }

    /** 様式枝区分項目 */
    @Column(name = "page_total")
    private Long pageTotal = INIT_Long;

    /**
     * 様式枝区分項目を取得する
     *
     * @return 様式枝区分項目
     */
    public Long getPageTotal() {
        return pageTotal;
    }

    /**
     * 様式枝区分項目を設定する
     *
     * @param pageTotal 様式枝区分項目
     */
    public void setPageTotal(final Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    /** 様式枝区分項目 */
    @Column(name = "sonota_total")
    private String sonotaTotal = INIT_String;

    /**
     * 様式枝区分項目を取得する
     *
     * @return 様式枝区分項目
     */
    public String getSonotaTotal() {
        return sonotaTotal;
    }

    /**
     * 様式枝区分項目を設定する
     *
     * @param sonotaTotal 様式枝区分項目
     */
    public void setSonotaTotal(final String sonotaTotal) {
        this.sonotaTotal = sonotaTotal;
    }

    /** 様式枝区分項目 */
    @Column(name = "himoku")
    private String himoku = INIT_String;

    /**
     * 様式枝区分項目を取得する
     *
     * @return 様式枝区分項目
     */
    public String getHimoku() {
        return himoku;
    }

    /**
     * 様式枝区分項目を設定する
     *
     * @param himoku 様式枝区分項目
     */
    public void setHimoku(final String himoku) {
        this.himoku = himoku;
    }

    /** 連番 */
    @Column(name = "ichiren_no")
    private Integer ichirenNo = INIT_Integer;

    /**
     * 連番を取得する
     *
     * @return 連番
     */
    public Integer getIchirenNo() {
        return ichirenNo;
    }

    /**
     * 連番を設定する
     *
     * @param ichirenNo 連番
     */
    public void setIchirenNo(final Integer ichirenNo) {
        this.ichirenNo = ichirenNo;
    }

    /** 目的 */
    @Column(name = "mokuteki")
    private String mokuteki = INIT_String;

    /**
     * 目的を取得する
     *
     * @return 目的
     */
    public String getMokuteki() {
        return mokuteki;
    }

    /**
     * 目的を設定する
     *
     * @param mokuteki 目的
     */
    public void setMokuteki(final String mokuteki) {
        this.mokuteki = mokuteki;
    }

    /** 金額 */
    @Column(name = "kingaku")
    private Long kingaku = INIT_Long;

    /**
     * 金額を取得する
     *
     * @return 金額
     */
    public Long getKingaku() {
        return kingaku;
    }

    /**
     * 金額を設定する
     *
     * @param kingaku 金額
     */
    public void setKingaku(final Long kingaku) {
        this.kingaku = kingaku;
    }

    /** 発生日 */
    @Column(name = "accrual_date")
    private String accrualDate = INIT_String;

    /**
     * 発生日を取得する
     *
     * @return 発生日
     */
    public String getAccrualDate() {
        return accrualDate;
    }

    /**
     * 発生日を設定する
     *
     * @param accrualDate 発生日
     */
    public void setAccrualDate(final String accrualDate) {
        this.accrualDate = accrualDate;
    }

    /** 発生日実値 */
    @Column(name = "accrual_date_value")
    private LocalDate accrualDateValue = INIT_LocalDate;

    /**
     * 発生日実値を取得する
     *
     * @return 発生日実値
     */
    public LocalDate getAccrualDateValue() {
        return accrualDateValue;
    }

    /**
     * 発生日実値を設定する
     *
     * @param accrualDateValue 発生日実値
     */
    public void setAccrualDateValue(final LocalDate accrualDateValue) {
        this.accrualDateValue = accrualDateValue;
    }

    /** 取引相手先名 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 取引相手先名を取得する
     *
     * @return 取引相手先名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 取引相手先名を設定する
     *
     * @param partnerName 取引相手先名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 取引相手先住所 */
    // TODO カラム名タイプミス修正する
    @Column(name = "partner_jusho")
    private String partnerJuusho = INIT_String;

    /**
     * 取引相手先住所を取得する
     *
     * @return 取引相手先住所
     */
    public String getPartnerJuusho() {
        return partnerJuusho;
    }

    /**
     * 取引相手先住所を設定する
     *
     * @param partnerJuusho 取引相手先住所
     */
    public void setPartnerJusho(final String partnerJuusho) {
        this.partnerJuusho = partnerJuusho;
    }

    /** 摘要 */
    @Column(name = "bikou")
    private String bikou = INIT_String;

    /**
     * 摘要を取得する
     *
     * @return 摘要
     */
    public String getBikou() {
        return bikou;
    }

    /**
     * 摘要を設定する
     *
     * @param bikou 摘要
     */
    public void setBikou(final String bikou) {
        this.bikou = bikou;
    }

    /** 代表者関連者名称 */
    @Column(name = "flg_ryoushuusho")
    private Integer flgRyoushuusho = INIT_Integer;

    /**
     * 代表者関連者名称を取得する
     *
     * @return 代表者関連者名称
     */
    public Integer getFlgRyoushuusho() {
        return flgRyoushuusho;
    }

    /**
     * 代表者関連者名称を設定する
     *
     * @param flgRyoushuusho 代表者関連者名称
     */
    public void setFlgRyoushuusho(final Integer flgRyoushuusho) {
        this.flgRyoushuusho = flgRyoushuusho;
    }

    /** 代表者関連者名称 */
    @Column(name = "flg_kouufukin")
    private Integer flgKouufukin = INIT_Integer;

    /**
     * 代表者関連者名称を取得する
     *
     * @return 代表者関連者名称
     */
    public Integer getFlgKouufukin() {
        return flgKouufukin;
    }

    /**
     * 代表者関連者名称を設定する
     *
     * @param flgKouufukin 代表者関連者名称
     */
    public void setFlgKouufukin(final Integer flgKouufukin) {
        this.flgKouufukin = flgKouufukin;
    }

    /** 支払者関連者Id(個人) */
    @Column(name = "relation_person_id_outcome")
    private Long relationPersonIdOutcome = INIT_Long;

    /**
     * 支払者関連者Id(個人)を取得する
     *
     * @return 支払者関連者Id(個人)
     */
    public Long getRelationPersonIdOutcome() {
        return relationPersonIdOutcome;
    }

    /**
     * 支払者関連者Id(個人)を設定する
     *
     * @param relationPersonIdOutcome 支払者関連者Id(個人)
     */
    public void setRelationPersonIdOutcome(final Long relationPersonIdOutcome) {
        this.relationPersonIdOutcome = relationPersonIdOutcome;
    }

    /** 支払者関連者同一識別コード(個人) */
    @Column(name = "relation_person_code_outcome")
    private Integer relationPersonCodeOutcome = INIT_Integer;

    /**
     * 支払者関連者同一識別コード(個人)を取得する
     *
     * @return 支払者関連者同一識別コード(個人)
     */
    public Integer getRelationPersonCodeOutcome() {
        return relationPersonCodeOutcome;
    }

    /**
     * 支払者関連者同一識別コード(個人)を設定する
     *
     * @param relationPersonCodeOutcome 支払者関連者同一識別コード(個人)
     */
    public void setRelationPersonCodeOutcome(final Integer relationPersonCodeOutcome) {
        this.relationPersonCodeOutcome = relationPersonCodeOutcome;
    }

    /** 支払者関連者名称(個人) */
    @Column(name = "relation_person_name_outcome")
    private String relationPersonNameOutcome = INIT_String;

    /**
     * 支払者関連者名称(個人)を取得する
     *
     * @return 支払者関連者名称(個人)
     */
    public String getRelationPersonNameOutcome() {
        return relationPersonNameOutcome;
    }

    /**
     * 支払者関連者名称(個人)を設定する
     *
     * @param relationPersonNameOutcome 支払者関連者名称(個人)
     */
    public void setRelationPersonNameOutcome(final String relationPersonNameOutcome) {
        this.relationPersonNameOutcome = relationPersonNameOutcome;
    }

    /** 支払者関連者Id(法人) */
    @Column(name = "relation_corp_id_outcome")
    private Long relationCorpIdOutcome = INIT_Long;

    /**
     * 支払者関連者Id(法人)を取得する
     *
     * @return 支払者関連者Id(法人)
     */
    public Long getRelationCorpIdOutcome() {
        return relationCorpIdOutcome;
    }

    /**
     * 支払者関連者Id(法人)を設定する
     *
     * @param relationCorpIdOutcome 支払者関連者Id(法人)
     */
    public void setRelationCorpIdOutcome(final Long relationCorpIdOutcome) {
        this.relationCorpIdOutcome = relationCorpIdOutcome;
    }

    /** 支払者関連者同一識別コード(法人) */
    @Column(name = "relation_corp_code_outcome")
    private Integer relationCorpCodeOutcome = INIT_Integer;

    /**
     * 支払者関連者同一識別コード(法人)を取得する
     *
     * @return 支払者関連者同一識別コード(法人)
     */
    public Integer getRelationCorpCodeOutcome() {
        return relationCorpCodeOutcome;
    }

    /**
     * 支払者関連者同一識別コード(法人)を設定する
     *
     * @param relationCorpCodeOutcome 支払者関連者同一識別コード(法人)
     */
    public void setRelationCorpCodeOutcome(final Integer relationCorpCodeOutcome) {
        this.relationCorpCodeOutcome = relationCorpCodeOutcome;
    }

    /** 支払者関連者Id(法人) */
    @Column(name = "relation_corp_name_outcome")
    private String relationCorpNameOutcome = INIT_String;

    /**
     * 支払者関連者Id(法人)を取得する
     *
     * @return 支払者関連者Id(法人)
     */
    public String getRelationCorpNameOutcome() {
        return relationCorpNameOutcome;
    }

    /**
     * 支払者関連者Id(法人)を設定する
     *
     * @param relationCorpNameOutcome 支払者関連者Id(法人)
     */
    public void setRelationCorpNameOutcome(final String relationCorpNameOutcome) {
        this.relationCorpNameOutcome = relationCorpNameOutcome;
    }

    /** 支払者関連者同一識別コード(政治団体) */
    @Column(name = "relation_political_org_id_outcome")
    private Long relationPoliticalOrgIdOutcome = INIT_Long;

    /**
     * 支払者関連者同一識別コード(政治団体)を取得する
     *
     * @return 支払者関連者同一識別コード(政治団体)
     */
    public Long getRelationPoliticalOrgIdOutcome() {
        return relationPoliticalOrgIdOutcome;
    }

    /**
     * 支払者関連者同一識別コード(政治団体)を設定する
     *
     * @param relationPoliticalOrgIdOutcome 支払者関連者同一識別コード(政治団体)
     */
    public void setRelationPoliticalOrgIdOutcome(final Long relationPoliticalOrgIdOutcome) {
        this.relationPoliticalOrgIdOutcome = relationPoliticalOrgIdOutcome;
    }

    /** 支払者関連者名称(政治団体) */
    @Column(name = "relation_political_org_code_outcome")
    private Integer relationPoliticalOrgCodeOutcome = INIT_Integer;

    /**
     * 支払者関連者名称(政治団体)を取得する
     *
     * @return 支払者関連者名称(政治団体)
     */
    public Integer getRelationPoliticalOrgCodeOutcome() {
        return relationPoliticalOrgCodeOutcome;
    }

    /**
     * 支払者関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgCodeOutcome 支払者関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgCodeOutcome(final Integer relationPoliticalOrgCodeOutcome) {
        this.relationPoliticalOrgCodeOutcome = relationPoliticalOrgCodeOutcome;
    }

    /** 支払者関連者名称(政治団体) */
    @Column(name = "relation_political_org_name_outcome")
    private String relationPoliticalOrgNameOutcome = INIT_String;

    /**
     * 支払者関連者名称(政治団体)を取得する
     *
     * @return 支払者関連者名称(政治団体)
     */
    public String getRelationPoliticalOrgNameOutcome() {
        return relationPoliticalOrgNameOutcome;
    }

    /**
     * 支払者関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgNameOutcome 支払者関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgNameOutcome(final String relationPoliticalOrgNameOutcome) {
        this.relationPoliticalOrgNameOutcome = relationPoliticalOrgNameOutcome;
    }

    /** 自由検索用カラム */
    @Column(name = "search_words")
    private String searchWords = INIT_String;

    /**
     * 自由検索用カラムを取得する
     *
     * @return 自由検索用カラム
     */
    public String getSearchWords() {
        return searchWords;
    }

    /**
     * 自由検索用カラムを設定する
     *
     * @param searchWords 自由検索用カラム
     */
    public void setSearchWords(final String searchWords) {
        this.searchWords = searchWords;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Long insertUserId = INIT_Long;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Long getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Long insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザ同一識別コード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_Integer;

    /**
     * 挿入ユーザ同一識別コードを取得する
     *
     * @return 挿入ユーザ同一識別コード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザ同一識別コードを設定する
     *
     * @param insertUserCode 挿入ユーザ同一識別コード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ姓名 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_String;

    /**
     * 挿入ユーザ姓名を取得する
     *
     * @return 挿入ユーザ姓名
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ姓名を設定する
     *
     * @param insertUserName 挿入ユーザ姓名
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入タイムスタンプ */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_Timestamp;

    /**
     * 挿入タイムスタンプを取得する
     *
     * @return 挿入タイムスタンプ
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入タイムスタンプを設定する
     *
     * @param insertTimestamp 挿入タイムスタンプ
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 更新ユーザId */
    @Column(name = "update_user_id")
    private Long updateUserId = INIT_Long;

    /**
     * 更新ユーザIdを取得する
     *
     * @return 更新ユーザId
     */
    @Override
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新ユーザIdを設定する
     *
     * @param updateUserId 更新ユーザId
     */
    @Override
    public void setUpdateUserId(final Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /** 更新ユーザ同一識別コード */
    @Column(name = "update_user_code")
    private Integer updateUserCode = INIT_Integer;

    /**
     * 更新ユーザ同一識別コードを取得する
     *
     * @return 更新ユーザ同一識別コード
     */
    @Override
    public Integer getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 更新ユーザ同一識別コードを設定する
     *
     * @param updateUserCode 更新ユーザ同一識別コード
     */
    @Override
    public void setUpdateUserCode(final Integer updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    /** 更新ユーザ姓名 */
    @Column(name = "update_user_name")
    private String updateUserName = INIT_String;

    /**
     * 更新ユーザ姓名を取得する
     *
     * @return 更新ユーザ姓名
     */
    @Override
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 更新ユーザ姓名を設定する
     *
     * @param updateUserName 更新ユーザ姓名
     */
    @Override
    public void setUpdateUserName(final String updateUserName) {
        this.updateUserName = updateUserName;
    }

    /** 更新タイムスタンプ */
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp = INIT_Timestamp;

    /**
     * 更新タイムスタンプを取得する
     *
     * @return 更新タイムスタンプ
     */
    @Override
    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 更新タイムスタンプを設定する
     *
     * @param updateTimestamp 更新タイムスタンプ
     */
    @Override
    public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

}
