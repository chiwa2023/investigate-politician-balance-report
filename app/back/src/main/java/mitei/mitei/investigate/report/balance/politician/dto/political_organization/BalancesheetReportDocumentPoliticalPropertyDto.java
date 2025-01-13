package mitei.mitei.investigate.report.balance.politician.dto.political_organization;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 政治団体最小限Dto
 */
public class BalancesheetReportDocumentPoliticalPropertyDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** Long型の初期値 */
    private static final Long INIT_Long = 0L;

    /** Long型の初期値 */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** Boolean型の初期値 */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 政治団体Id */
    private Long politicalOrganizationId = INIT_Long;

    /** 政治団体同一識別コード */
    private Integer politicalOrganizationCode = INIT_Integer;

    /** 政治団体名称 */
    private String politicalOrganizationName = INIT_String;

    /** 原文書団体名称 */
    private String dantaiName = INIT_String;

    /** 原文書代表者名 */
    private String daihyouName = INIT_String;

    /** 関連者区分 */
    private Integer relationKbn = INIT_Integer;

    /** 代表者関連者個人Id */
    private Long relationPersonIdDelegate = INIT_Long;

    /** 関連者同一識別コード */
    private Integer relationPersonCodeDelegate = INIT_Integer;

    /** 関連者名称 */
    private String relationPersonNameDelegate = INIT_String;

    /** 報告年度 */
    private Integer houkokuNen = INIT_Integer;

    /** 提出日 */
    private LocalDate offeringDate = INIT_LocalDate;

    /** 政治団体追加該否 */
    private Boolean isAddOrganization = INIT_Boolean;

    /** 政治団体区分 */
    private String dantaiKbn = INIT_String;

    /** 資金管理団体該否 */
    private Integer umuShikinKnariDanti = INIT_Integer;

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

    /**
     * 原文書団体名称を取得する
     *
     * @return 原文書団体名称
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 原文書団体名称を設定する
     *
     * @param dantaiName 原文書団体名称
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /**
     * 原文書代表者名を取得する
     *
     * @return 原文書代表者名
     */
    public String getDaihyouName() {
        return daihyouName;
    }

    /**
     * 原文書代表者名を設定する
     *
     * @param daihyouName 原文書代表者名
     */
    public void setDaihyouName(final String daihyouName) {
        this.daihyouName = daihyouName;
    }

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

    /**
     * 代表者関連者個人Idを取得する
     *
     * @return 代表者関連者個人Id
     */
    public Long getRelationPersonIdDelegate() {
        return relationPersonIdDelegate;
    }

    /**
     * 代表者関連者個人Idを設定する
     *
     * @param relationPersonIdDelegate 代表者関連者個人Id
     */
    public void setRelationPersonIdDelegate(final Long relationPersonIdDelegate) {
        this.relationPersonIdDelegate = relationPersonIdDelegate;
    }

    /**
     * 関連者同一識別コードを取得する
     *
     * @return 関連者同一識別コード
     */
    public Integer getRelationPersonCodeDelegate() {
        return relationPersonCodeDelegate;
    }

    /**
     * 関連者同一識別コードを設定する
     *
     * @param relationPersonCodeDelegate 関連者同一識別コード
     */
    public void setRelationPersonCodeDelegate(final Integer relationPersonCodeDelegate) {
        this.relationPersonCodeDelegate = relationPersonCodeDelegate;
    }

    /**
     * 関連者名称を取得する
     *
     * @return 関連者名称
     */
    public String getRelationPersonNameDelegate() {
        return relationPersonNameDelegate;
    }

    /**
     * 関連者名称を設定する
     *
     * @param relationPersonNameDelegate 関連者名称
     */
    public void setRelationPersonNameDelegate(final String relationPersonNameDelegate) {
        this.relationPersonNameDelegate = relationPersonNameDelegate;
    }

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

    /**
     * 政治団体追加該否を取得する
     *
     * @return 政治団体追加該否
     */
    public Boolean getIsAddOrganization() {
        return isAddOrganization;
    }

    /**
     * 政治団体追加該否を設定する
     *
     * @param isAddOrganization 政治団体追加該否
     */
    public void setIsAddOrganization(final Boolean isAddOrganization) {
        this.isAddOrganization = isAddOrganization;
    }

    /**
     * 政治団体区分
     *
     * @return 政治団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 政治団体区分
     *
     * @param dantaiKbn 政治団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

    /**
     * 資金管理団体の有無
     *
     * @return 資金管理団体の有無
     */
    public Integer getUmuShikinKnariDanti() {
        return umuShikinKnariDanti;
    }

    /**
     * 資金管理団体の有無
     *
     * @param umuShikinKnariDanti 資金管理団体の有無
     */
    public void setUmuShikinKnariDanti(final Integer umuShikinKnariDanti) {
        this.umuShikinKnariDanti = umuShikinKnariDanti;
    }

}
