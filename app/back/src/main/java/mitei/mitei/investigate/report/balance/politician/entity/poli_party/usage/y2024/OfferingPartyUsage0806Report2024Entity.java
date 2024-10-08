package mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024;

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
 * offering_party_usage_0806_report_2024接続用Entity
 */
@Entity
@Table(name = "offering_party_usage_0806_report_2024")
public class OfferingPartyUsage0806Report2024Entity implements Serializable, AllTabeDataHistoryInterface { // NOPMD

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

    /** 使途報告書様式8(その5)Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_usage_0806_report_id")
    private Long partyUsage0806ReportId = INIT_Long;

    /**
     * 使途報告書様式8(その5)Idを取得する
     *
     * @return 使途報告書様式8(その5)Id
     */
    public Long getPartyUsage0806ReportId() {
        return partyUsage0806ReportId;
    }

    /**
     * 使途報告書様式8(その5)Idを設定する
     *
     * @param partyUsage0806ReportId 使途報告書様式8(その5)Id
     */
    public void setPartyUsage0806ReportId(final Long partyUsage0806ReportId) {
        this.partyUsage0806ReportId = partyUsage0806ReportId;
    }

    /** 使途報告書様式8(その5)同一識別コード */
    @Column(name = "party_usage_0806_report_code")
    private Long partyUsage0806ReportCode = INIT_Long;

    /**
     * 使途報告書様式8(その5)同一識別コードを取得する
     *
     * @return 使途報告書様式8(その5)同一識別コード
     */
    public Long getPartyUsage0806ReportCode() {
        return partyUsage0806ReportCode;
    }

    /**
     * 使途報告書様式8(その5)同一識別コードを設定する
     *
     * @param partyUsage0806ReportCode 使途報告書様式8(その5)同一識別コード
     */
    public void setPartyUsage0806ReportCode(final Long partyUsage0806ReportCode) {
        this.partyUsage0806ReportCode = partyUsage0806ReportCode;
    }

    /** 最新区分 */
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

    /** 報告年度 */
    @Column(name = "nendo")
    private Integer nendo = INIT_Integer;

    /**
     * 報告年度を取得する
     *
     * @return 報告年度
     */
    public Integer getNendo() {
        return nendo;
    }

    /**
     * 報告年度を設定する
     *
     * @param nendo 報告年度
     */
    public void setNendo(final Integer nendo) {
        this.nendo = nendo;
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

    /** 基金名称 */
    @Column(name = "funds_name")
    private String fundsName = INIT_String;

    /**
     * 基金名称を取得する
     *
     * @return 基金名称
     */
    public String getFundsName() {
        return fundsName;
    }

    /**
     * 基金名称を設定する
     *
     * @param fundsName 基金名称
     */
    public void setFundsName(final String fundsName) {
        this.fundsName = fundsName;
    }

    /** 基金の目的 */
    @Column(name = "funds_purpose")
    private String fundsPurpose = INIT_String;

    /**
     * 基金の目的を取得する
     *
     * @return 基金の目的
     */
    public String getFundsPurpose() {
        return fundsPurpose;
    }

    /**
     * 基金の目的を設定する
     *
     * @param fundsPurpose 基金の目的
     */
    public void setFundsPurpose(final String fundsPurpose) {
        this.fundsPurpose = fundsPurpose;
    }

    /** 基金前年高 */
    @Column(name = "total_last_year")
    private Long totalLastYear = INIT_Long;

    /**
     * 基金前年高を取得する
     *
     * @return 基金前年高
     */
    public Long getTotalLastYear() {
        return totalLastYear;
    }

    /**
     * 基金前年高を設定する
     *
     * @param totalLastYear 基金前年高
     */
    public void setTotalLastYear(final Long totalLastYear) {
        this.totalLastYear = totalLastYear;
    }

    /** 総計 */
    @Column(name = "total")
    private Long total = INIT_Long;

    /**
     * 総計を取得する
     *
     * @return 総計
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 総計を設定する
     *
     * @param total 総計
     */
    public void setTotal(final Long total) {
        this.total = total;
    }

    /** 総計備考 */
    @Column(name = "total_bikou")
    private String totalBikou = INIT_String;

    /**
     * 総計備考を取得する
     *
     * @return 総計備考
     */
    public String getTotalBikou() {
        return totalBikou;
    }

    /**
     * 総計備考を設定する
     *
     * @param totalBikou 総計備考
     */
    public void setTotalBikou(final String totalBikou) {
        this.totalBikou = totalBikou;
    }

    /** 増減集計 */
    @Column(name = "total_increase")
    private Long totalIncrease = INIT_Long;

    /**
     * 増減集計を取得する
     *
     * @return 増減集計
     */
    public Long getTotalIncrease() {
        return totalIncrease;
    }

    /**
     * 増減集計を設定する
     *
     * @param totalIncrease 増減集計
     */
    public void setTotalIncrease(final Long totalIncrease) {
        this.totalIncrease = totalIncrease;
    }

    /** 増減集計備考 */
    @Column(name = "total_increase_bikou")
    private String totalIncreaseBikou = INIT_String;

    /**
     * 増減集計備考を取得する
     *
     * @return 増減集計備考
     */
    public String getTotalIncreaseBikou() {
        return totalIncreaseBikou;
    }

    /**
     * 増減集計備考を設定する
     *
     * @param totalIncreaseBikou 増減集計備考
     */
    public void setTotalIncreaseBikou(final String totalIncreaseBikou) {
        this.totalIncreaseBikou = totalIncreaseBikou;
    }

    /** 本年集計 */
    @Column(name = "total_this_year")
    private Long totalThisYear = INIT_Long;

    /**
     * 本年集計を取得する
     *
     * @return 本年集計
     */
    public Long getTotalThisYear() {
        return totalThisYear;
    }

    /**
     * 本年集計を設定する
     *
     * @param totalThisYear 本年集計
     */
    public void setTotalThisYear(final Long totalThisYear) {
        this.totalThisYear = totalThisYear;
    }

    /** 本年集計備考 */
    @Column(name = "total_this_year_bikou")
    private String totalThisYearBikou = INIT_String;

    /**
     * 本年集計備考を取得する
     *
     * @return 本年集計備考
     */
    public String getTotalThisYearBikou() {
        return totalThisYearBikou;
    }

    /**
     * 本年集計備考を設定する
     *
     * @param totalThisYearBikou 本年集計備考
     */
    public void setTotalThisYearBikou(final String totalThisYearBikou) {
        this.totalThisYearBikou = totalThisYearBikou;
    }

    /** 区分小計 */
    @Column(name = "sub_total")
    private Long subTotal = INIT_Long;

    /**
     * 区分小計を取得する
     *
     * @return 区分小計
     */
    public Long getSubTotal() {
        return subTotal;
    }

    /**
     * 区分小計を設定する
     *
     * @param subTotal 区分小計
     */
    public void setSubTotal(final Long subTotal) {
        this.subTotal = subTotal;
    }

    /** 区分小計備考 */
    @Column(name = "sub_total_bikou")
    private String subTotalBikou = INIT_String;

    /**
     * 区分小計備考を取得する
     *
     * @return 区分小計備考
     */
    public String getSubTotalBikou() {
        return subTotalBikou;
    }

    /**
     * 区分小計備考を設定する
     *
     * @param subTotalBikou 区分小計備考
     */
    public void setSubTotalBikou(final String subTotalBikou) {
        this.subTotalBikou = subTotalBikou;
    }

    /** 区分番号 */
    @Column(name = "kubun_num")
    private Integer kubunNum = INIT_Integer;

    /**
     * 区分番号を取得する
     *
     * @return 区分番号
     */
    public Integer getKubunNum() {
        return kubunNum;
    }

    /**
     * 区分番号を設定する
     *
     * @param kubunNum 区分番号
     */
    public void setKubunNum(final Integer kubunNum) {
        this.kubunNum = kubunNum;
    }

    /** 行番号 */
    @Column(name = "row_no")
    private Integer rowNo = INIT_Integer;

    /**
     * 行番号を取得する
     *
     * @return 行番号
     */
    public Integer getRowNo() {
        return rowNo;
    }

    /**
     * 行番号を設定する
     *
     * @param rowNo 行番号
     */
    public void setRowNo(final Integer rowNo) {
        this.rowNo = rowNo;
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

    /** 金額 */
    @Column(name = "amount")
    private Long amount = INIT_Long;

    /**
     * 金額を取得する
     *
     * @return 金額
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 金額を設定する
     *
     * @param amount 金額
     */
    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    /** 備考 */
    @Column(name = "bikou")
    private String bikou = INIT_String;

    /**
     * 備考を取得する
     *
     * @return 備考
     */
    public String getBikou() {
        return bikou;
    }

    /**
     * 備考を設定する
     *
     * @param bikou 備考
     */
    public void setBikou(final String bikou) {
        this.bikou = bikou;
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
