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
 * political_organization_property接続用Entity
 */
@Entity
@Table(name = "political_organization_property")
public class PoliticalOrganizationPropertyEntity implements Serializable, // NOPMD DataClass
        AllTabeDataHistoryInterface {

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

    /** 政治団体属性Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "political_organization_property_id")
    private Long politicalOrganizationPropertyId = INIT_Long;

    /**
     * 政治団体属性Idを取得する
     *
     * @return 政治団体属性Id
     */
    public Long getPoliticalOrganizationPropertyId() {
        return politicalOrganizationPropertyId;
    }

    /**
     * 政治団体属性Idを設定する
     *
     * @param politicalOrganizationPropertyId 政治団体属性Id
     */
    public void setPoliticalOrganizationPropertyId(final Long politicalOrganizationPropertyId) {
        this.politicalOrganizationPropertyId = politicalOrganizationPropertyId;
    }

    /** 政治団体属性同一識別コード */
    @Column(name = "political_organization_property_code")
    private Integer politicalOrganizationPropertyCode = INIT_Integer;

    /**
     * 政治団体属性同一識別コードを取得する
     *
     * @return 政治団体属性同一識別コード
     */
    public Integer getPoliticalOrganizationPropertyCode() {
        return politicalOrganizationPropertyCode;
    }

    /**
     * 政治団体属性同一識別コードを設定する
     *
     * @param politicalOrganizationPropertyCode 政治団体属性同一識別コード
     */
    public void setPoliticalOrganizationPropertyCode(final Integer politicalOrganizationPropertyCode) {
        this.politicalOrganizationPropertyCode = politicalOrganizationPropertyCode;
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

    /** 団体代表者関連者個人id */
    @Column(name = "delegate_relation_person_id")
    private Long delegateRelationPersonId = INIT_Long;

    /**
     * 団体代表者関連者個人idを取得する
     *
     * @return 団体代表者関連者個人id
     */
    public Long getDelegateRelationPersonId() {
        return delegateRelationPersonId;
    }

    /**
     * 団体代表者関連者個人idを設定する
     *
     * @param delegateRelationPersonId 団体代表者関連者個人id
     */
    public void setDelegateRelationPersonId(final Long delegateRelationPersonId) {
        this.delegateRelationPersonId = delegateRelationPersonId;
    }

    /** 団体代表者関連者個人同一識別コード */
    @Column(name = "delegate_relation_person_code")
    private Integer delegateRelationPersonCode = INIT_Integer;

    /**
     * 団体代表者関連者個人同一識別コードを取得する
     *
     * @return 団体代表者関連者個人同一識別コード
     */
    public Integer getDelegateRelationPersonCode() {
        return delegateRelationPersonCode;
    }

    /**
     * 団体代表者関連者個人同一識別コードを設定する
     *
     * @param delegateRelationPersonCode 団体代表者関連者個人同一識別コード
     */
    public void setDelegateRelationPersonCode(final Integer delegateRelationPersonCode) {
        this.delegateRelationPersonCode = delegateRelationPersonCode;
    }

    /** 団体代表者姓名 */
    @Column(name = "delegate_koushoku_name")
    private String delegateKoushokuName = INIT_String;

    /**
     * 団体代表者姓名を取得する
     *
     * @return 団体代表者姓名
     */
    public String getDelegateKoushokuName() {
        return delegateKoushokuName;
    }

    /**
     * 団体代表者姓名を設定する
     *
     * @param delegateKoushokuName 団体代表者姓名
     */
    public void setDelegateKoushokuName(final String delegateKoushokuName) {
        this.delegateKoushokuName = delegateKoushokuName;
    }

    /** 会計責任者関連者個人id */
    @Column(name = "account_manager_relation_person_id")
    private Long accountManagerRelationPersonId = INIT_Long;

    /**
     * 会計責任者関連者個人idを取得する
     *
     * @return 会計責任者関連者個人id
     */
    public Long getAccountManagerRelationPersonId() {
        return accountManagerRelationPersonId;
    }

    /**
     * 会計責任者関連者個人idを設定する
     *
     * @param accountManagerRelationPersonId 会計責任者関連者個人id
     */
    public void setAccountManagerRelationPersonId(final Long accountManagerRelationPersonId) {
        this.accountManagerRelationPersonId = accountManagerRelationPersonId;
    }

    /** 会計責任者関連者個人同一識別コード */
    @Column(name = "account_manager_relation_person_code")
    private Integer accountManagerRelationPersonCode = INIT_Integer;

    /**
     * 会計責任者関連者個人同一識別コードを取得する
     *
     * @return 会計責任者関連者個人同一識別コード
     */
    public Integer getAccountManagerRelationPersonCode() {
        return accountManagerRelationPersonCode;
    }

    /**
     * 会計責任者関連者個人同一識別コードを設定する
     *
     * @param accountManagerRelationPersonCode 会計責任者関連者個人同一識別コード
     */
    public void setAccountManagerRelationPersonCode(final Integer accountManagerRelationPersonCode) {
        this.accountManagerRelationPersonCode = accountManagerRelationPersonCode;
    }

    /** 会計責任者姓名 */
    @Column(name = "account_manager_koushoku_name")
    private String accountManagerKoushokuName = INIT_String;

    /**
     * 会計責任者姓名を取得する
     *
     * @return 会計責任者姓名
     */
    public String getAccountManagerKoushokuName() {
        return accountManagerKoushokuName;
    }

    /**
     * 会計責任者姓名を設定する
     *
     * @param accountManagerKoushokuName 会計責任者姓名
     */
    public void setAccountManagerKoushokuName(final String accountManagerKoushokuName) {
        this.accountManagerKoushokuName = accountManagerKoushokuName;
    }

    /** 会計責任者代行者関連者個人id */
    @Column(name = "kaikei_daiko_relation_person_id")
    private Long kaikeiDaikoRelationPersonId = INIT_Long;

    /**
     * 会計責任者代行者関連者個人idを取得する
     *
     * @return 会計責任者代行者関連者個人id
     */
    public Long getKaikeiDaikoRelationPersonId() {
        return kaikeiDaikoRelationPersonId;
    }

    /**
     * 会計責任者代行者関連者個人idを設定する
     *
     * @param kaikeiDaikoRelationPersonId 会計責任者代行者関連者個人id
     */
    public void setKaikeiDaikoRelationPersonId(final Long kaikeiDaikoRelationPersonId) {
        this.kaikeiDaikoRelationPersonId = kaikeiDaikoRelationPersonId;
    }

    /** 会計責任者代行者関連者個人同一識別コード */
    @Column(name = "kaikei_daiko_relation_person_code")
    private Integer kaikeiDaikoRelationPersonCode = INIT_Integer;

    /**
     * 会計責任者代行者関連者個人同一識別コードを取得する
     *
     * @return 会計責任者代行者関連者個人同一識別コード
     */
    public Integer getKaikeiDaikoRelationPersonCode() {
        return kaikeiDaikoRelationPersonCode;
    }

    /**
     * 会計責任者代行者関連者個人同一識別コードを設定する
     *
     * @param kaikeiDaikoRelationPersonCode 会計責任者代行者関連者個人同一識別コード
     */
    public void setKaikeiDaikoRelationPersonCode(final Integer kaikeiDaikoRelationPersonCode) {
        this.kaikeiDaikoRelationPersonCode = kaikeiDaikoRelationPersonCode;
    }

    /** 会計責任者代行者者姓名 */
    @Column(name = "kaikei_daiko_koushoku_name")
    private String kaikeiDaikoKoushokuName = INIT_String;

    /**
     * 会計責任者代行者者姓名を取得する
     *
     * @return 会計責任者代行者者姓名
     */
    public String getKaikeiDaikoKoushokuName() {
        return kaikeiDaikoKoushokuName;
    }

    /**
     * 会計責任者代行者者姓名を設定する
     *
     * @param kaikeiDaikoKoushokuName 会計責任者代行者者姓名
     */
    public void setKaikeiDaikoKoushokuName(final String kaikeiDaikoKoushokuName) {
        this.kaikeiDaikoKoushokuName = kaikeiDaikoKoushokuName;
    }

    /** 資金管理団体責任者関連者個人id */
    @Column(name = "shikin_daihyou_relation_person_id")
    private Long shikinDaihyouRelationPersonId = INIT_Long;

    /**
     * 資金管理団体責任者関連者個人idを取得する
     *
     * @return 資金管理団体責任者関連者個人id
     */
    public Long getShikinDaihyouRelationPersonId() {
        return shikinDaihyouRelationPersonId;
    }

    /**
     * 資金管理団体責任者関連者個人idを設定する
     *
     * @param shikinDaihyouRelationPersonId 資金管理団体責任者関連者個人id
     */
    public void setShikinDaihyouRelationPersonId(final Long shikinDaihyouRelationPersonId) {
        this.shikinDaihyouRelationPersonId = shikinDaihyouRelationPersonId;
    }

    /** 資金管理団体責任者関連者個人同一識別コード */
    @Column(name = "shikin_daihyou_relation_person_code")
    private Integer shikinDaihyouRelationPersonCode = INIT_Integer;

    /**
     * 資金管理団体責任者関連者個人同一識別コードを取得する
     *
     * @return 資金管理団体責任者関連者個人同一識別コード
     */
    public Integer getShikinDaihyouRelationPersonCode() {
        return shikinDaihyouRelationPersonCode;
    }

    /**
     * 資金管理団体責任者関連者個人同一識別コードを設定する
     *
     * @param shikinDaihyouRelationPersonCode 資金管理団体責任者関連者個人同一識別コード
     */
    public void setShikinDaihyouRelationPersonCode(final Integer shikinDaihyouRelationPersonCode) {
        this.shikinDaihyouRelationPersonCode = shikinDaihyouRelationPersonCode;
    }

    /** 資金管理団体責任者姓名 */
    @Column(name = "shikin_daihyou_koushoku_name")
    private String shikinDaihyouKoushokuName = INIT_String;

    /**
     * 資金管理団体責任者姓名を取得する
     *
     * @return 資金管理団体責任者姓名
     */
    public String getShikinDaihyouKoushokuName() {
        return shikinDaihyouKoushokuName;
    }

    /**
     * 資金管理団体責任者姓名を設定する
     *
     * @param shikinDaihyouKoushokuName 資金管理団体責任者姓名
     */
    public void setShikinDaihyouKoushokuName(final String shikinDaihyouKoushokuName) {
        this.shikinDaihyouKoushokuName = shikinDaihyouKoushokuName;
    }

    /** 国会議員1関連者個人id */
    @Column(name = "giin1_relation_person_id")
    private Long giin1RelationPersonId = INIT_Long;

    /**
     * 国会議員1関連者個人idを取得する
     *
     * @return 国会議員1関連者個人id
     */
    public Long getGiin1RelationPersonId() {
        return giin1RelationPersonId;
    }

    /**
     * 国会議員1関連者個人idを設定する
     *
     * @param giin1RelationPersonId 国会議員1関連者個人id
     */
    public void setGiin1RelationPersonId(final Long giin1RelationPersonId) {
        this.giin1RelationPersonId = giin1RelationPersonId;
    }

    /** 国会議員1関連者個人同一識別コード */
    @Column(name = "giin1_relation_person_code")
    private Integer giin1RelationPersonCode = INIT_Integer;

    /**
     * 国会議員1関連者個人同一識別コードを取得する
     *
     * @return 国会議員1関連者個人同一識別コード
     */
    public Integer getGiin1RelationPersonCode() {
        return giin1RelationPersonCode;
    }

    /**
     * 国会議員1関連者個人同一識別コードを設定する
     *
     * @param giin1RelationPersonCode 国会議員1関連者個人同一識別コード
     */
    public void setGiin1RelationPersonCode(final Integer giin1RelationPersonCode) {
        this.giin1RelationPersonCode = giin1RelationPersonCode;
    }

    /** 国会議員1姓名 */
    @Column(name = "giin1_koushoku_name")
    private String giin1KoushokuName = INIT_String;

    /**
     * 国会議員1姓名を取得する
     *
     * @return 国会議員1姓名
     */
    public String getGiin1KoushokuName() {
        return giin1KoushokuName;
    }

    /**
     * 国会議員1姓名を設定する
     *
     * @param giin1KoushokuName 国会議員1姓名
     */
    public void setGiin1KoushokuName(final String giin1KoushokuName) {
        this.giin1KoushokuName = giin1KoushokuName;
    }

    /** 国会議員2関連者個人id */
    @Column(name = "giin2_relation_person_id")
    private Long giin2RelationPersonId = INIT_Long;

    /**
     * 国会議員2関連者個人idを取得する
     *
     * @return 国会議員2関連者個人id
     */
    public Long getGiin2RelationPersonId() {
        return giin2RelationPersonId;
    }

    /**
     * 国会議員2関連者個人idを設定する
     *
     * @param giin2RelationPersonId 国会議員2関連者個人id
     */
    public void setGiin2RelationPersonId(final Long giin2RelationPersonId) {
        this.giin2RelationPersonId = giin2RelationPersonId;
    }

    /** 国会議員2関連者個人同一識別コード */
    @Column(name = "giin2_relation_person_code")
    private Integer giin2RelationPersonCode = INIT_Integer;

    /**
     * 国会議員2関連者個人同一識別コードを取得する
     *
     * @return 国会議員2関連者個人同一識別コード
     */
    public Integer getGiin2RelationPersonCode() {
        return giin2RelationPersonCode;
    }

    /**
     * 国会議員2関連者個人同一識別コードを設定する
     *
     * @param giin2RelationPersonCode 国会議員2関連者個人同一識別コード
     */
    public void setGiin2RelationPersonCode(final Integer giin2RelationPersonCode) {
        this.giin2RelationPersonCode = giin2RelationPersonCode;
    }

    /** 国会議員2姓名 */
    @Column(name = "giin2__koushoku_name")
    private String giin2KoushokuName = INIT_String;

    /**
     * 国会議員2姓名を取得する
     *
     * @return 国会議員2姓名
     */
    public String getGiin2KoushokuName() {
        return giin2KoushokuName;
    }

    /**
     * 国会議員2姓名を設定する
     *
     * @param giin2KoushokuName 国会議員2姓名
     */
    public void setGiin2KoushokuName(final String giin2KoushokuName) {
        this.giin2KoushokuName = giin2KoushokuName;
    }

    /** 国会議員3関連者個人id */
    @Column(name = "giin3_relation_person_id")
    private Long giin3RelationPersonId = INIT_Long;

    /**
     * 国会議員3関連者個人idを取得する
     *
     * @return 国会議員3関連者個人id
     */
    public Long getGiin3RelationPersonId() {
        return giin3RelationPersonId;
    }

    /**
     * 国会議員3関連者個人idを設定する
     *
     * @param giin3RelationPersonId 国会議員3関連者個人id
     */
    public void setGiin3RelationPersonId(final Long giin3RelationPersonId) {
        this.giin3RelationPersonId = giin3RelationPersonId;
    }

    /** 国会議員3関連者個人同一識別コード */
    @Column(name = "giin3_relation_person_code")
    private Integer giin3RelationPersonCode = INIT_Integer;

    /**
     * 国会議員3関連者個人同一識別コードを取得する
     *
     * @return 国会議員3関連者個人同一識別コード
     */
    public Integer getGiin3RelationPersonCode() {
        return giin3RelationPersonCode;
    }

    /**
     * 国会議員3関連者個人同一識別コードを設定する
     *
     * @param giin3RelationPersonCode 国会議員3関連者個人同一識別コード
     */
    public void setGiin3RelationPersonCode(final Integer giin3RelationPersonCode) {
        this.giin3RelationPersonCode = giin3RelationPersonCode;
    }

    /** 国会議員3姓名 */
    @Column(name = "giin3__koushoku_name")
    private String giin3KoushokuName = INIT_String;

    /**
     * 国会議員3姓名を取得する
     *
     * @return 国会議員3姓名
     */
    public String getGiin3KoushokuName() {
        return giin3KoushokuName;
    }

    /**
     * 国会議員3姓名を設定する
     *
     * @param giin3KoushokuName 国会議員3姓名
     */
    public void setGiin3KoushokuName(final String giin3KoushokuName) {
        this.giin3KoushokuName = giin3KoushokuName;
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
