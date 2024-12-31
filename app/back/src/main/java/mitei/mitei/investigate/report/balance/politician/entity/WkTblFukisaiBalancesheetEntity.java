package mitei.mitei.investigate.report.balance.politician.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * wk_tbl_fukisai_balancesheet接続用Entity
 */
@Entity
@Table(name = "wk_tbl_fukisai_balancesheet")
public class WkTblFukisaiBalancesheetEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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

    /** 収支報告書不記載ワークテーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_fukisai_balancesheet_id")
    private Long wkTblFukisaiBalancesheetId = INIT_Long;

    /**
     * 収支報告書不記載ワークテーブルIdを取得する
     *
     * @return 収支報告書不記載ワークテーブルId
     */
    public Long getWkTblFukisaiBalancesheetId() {
        return wkTblFukisaiBalancesheetId;
    }

    /**
     * 収支報告書不記載ワークテーブルIdを設定する
     *
     * @param wkTblFukisaiBalancesheetId 収支報告書不記載ワークテーブルId
     */
    public void setWkTblFukisaiBalancesheetId(final Long wkTblFukisaiBalancesheetId) {
        this.wkTblFukisaiBalancesheetId = wkTblFukisaiBalancesheetId;
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

    /** 検索順キー */
    @Column(name = "search_order_key")
    private String searchOrderKey = INIT_String;

    /**
     * 検索順キーを取得する
     *
     * @return 検索順キー
     */
    public String getSearchOrderKey() {
        return searchOrderKey;
    }

    /**
     * 検索順キーを設定する
     *
     * @param searchOrderKey 検索順キー
     */
    public void setSearchOrderKey(final String searchOrderKey) {
        this.searchOrderKey = searchOrderKey;
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

    /** 文書同一識別コード収入 */
    @Column(name = "document_code_input")
    private Long documentCodeInput = INIT_Long;

    /**
     * 文書同一識別コード収入を取得する
     *
     * @return 文書同一識別コード収入
     */
    public Long getDocumentCodeInput() {
        return documentCodeInput;
    }

    /**
     * 文書同一識別コード収入を設定する
     *
     * @param documentCodeInput 文書同一識別コード収入
     */
    public void setDocumentCodeInput(final Long documentCodeInput) {
        this.documentCodeInput = documentCodeInput;
    }

    /** 文書同一識別コード支出 */
    @Column(name = "document_code_output")
    private Long documentCodeOutput = INIT_Long;

    /**
     * 文書同一識別コード支出を取得する
     *
     * @return 文書同一識別コード支出
     */
    public Long getDocumentCodeOutput() {
        return documentCodeOutput;
    }

    /**
     * 文書同一識別コード支出を設定する
     *
     * @param documentCodeOutput 文書同一識別コード支出
     */
    public void setDocumentCodeOutput(final Long documentCodeOutput) {
        this.documentCodeOutput = documentCodeOutput;
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

    /** 項目名称 */
    @Column(name = "item_name")
    private String itemName = INIT_String;

    /**
     * 項目名称を取得する
     *
     * @return 項目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 項目名称を設定する
     *
     * @param itemName 項目名称
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /** 金額収入 */
    @Column(name = "kingaku_input")
    private Long kingakuInput = INIT_Long;

    /**
     * 金額収入を取得する
     *
     * @return 金額収入
     */
    public Long getKingakuInput() {
        return kingakuInput;
    }

    /**
     * 金額収入を設定する
     *
     * @param kingakuInput 金額収入
     */
    public void setKingakuInput(final Long kingakuInput) {
        this.kingakuInput = kingakuInput;
    }

    /** 金額支出 */
    @Column(name = "kingaku_output")
    private Long kingakuOutput = INIT_Long;

    /**
     * 金額支出を取得する
     *
     * @return 金額支出
     */
    public Long getKingakuOutput() {
        return kingakuOutput;
    }

    /**
     * 金額支出を設定する
     *
     * @param kingakuOutput 金額支出
     */
    public void setKingakuOutput(final Long kingakuOutput) {
        this.kingakuOutput = kingakuOutput;
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

    /** 支出した相手先名 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 支出した相手先名を取得する
     *
     * @return 支出した相手先名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 支出した相手先名を設定する
     *
     * @param partnerName 支出した相手先名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 支出した相手先住所 */
    @Column(name = "partner_juusho")
    private String partnerJuusho = INIT_String;

    /**
     * 支出した相手先住所を取得する
     *
     * @return 支出した相手先住所
     */
    public String getPartnerJuusho() {
        return partnerJuusho;
    }

    /**
     * 支出した相手先住所を設定する
     *
     * @param partnerJuusho 支出した相手先住所
     */
    public void setPartnerJuusho(final String partnerJuusho) {
        this.partnerJuusho = partnerJuusho;
    }

    /** 職業 */
    @Column(name = "shokugyou")
    private String shokugyou = INIT_String;

    /**
     * 職業を取得する
     *
     * @return 職業
     */
    public String getShokugyou() {
        return shokugyou;
    }

    /**
     * 職業を設定する
     *
     * @param shokugyou 職業
     */
    public void setShokugyou(final String shokugyou) {
        this.shokugyou = shokugyou;
    }

    /** 取引相手関連者同一識別コード(政治団体) */
    @Column(name = "relation_political_org_id")
    private Long relationPoliticalOrgId = INIT_Long;

    /**
     * 取引相手関連者同一識別コード(政治団体)を取得する
     *
     * @return 取引相手関連者同一識別コード(政治団体)
     */
    public Long getRelationPoliticalOrgId() {
        return relationPoliticalOrgId;
    }

    /**
     * 取引相手関連者同一識別コード(政治団体)を設定する
     *
     * @param relationPoliticalOrgId 取引相手関連者同一識別コード(政治団体)
     */
    public void setRelationPoliticalOrgId(final Long relationPoliticalOrgId) {
        this.relationPoliticalOrgId = relationPoliticalOrgId;
    }

    /** 取引相手関連者名称(政治団体) */
    @Column(name = "relation_political_org_code")
    private Integer relationPoliticalOrgCode = INIT_Integer;

    /**
     * 取引相手関連者名称(政治団体)を取得する
     *
     * @return 取引相手関連者名称(政治団体)
     */
    public Integer getRelationPoliticalOrgCode() {
        return relationPoliticalOrgCode;
    }

    /**
     * 取引相手関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgCode 取引相手関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgCode(final Integer relationPoliticalOrgCode) {
        this.relationPoliticalOrgCode = relationPoliticalOrgCode;
    }

    /** 取引相手関連者名称(政治団体) */
    @Column(name = "relation_political_org_name")
    private String relationPoliticalOrgName = INIT_String;

    /**
     * 取引相手関連者名称(政治団体)を取得する
     *
     * @return 取引相手関連者名称(政治団体)
     */
    public String getRelationPoliticalOrgName() {
        return relationPoliticalOrgName;
    }

    /**
     * 取引相手関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgName 取引相手関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgName(final String relationPoliticalOrgName) {
        this.relationPoliticalOrgName = relationPoliticalOrgName;
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
