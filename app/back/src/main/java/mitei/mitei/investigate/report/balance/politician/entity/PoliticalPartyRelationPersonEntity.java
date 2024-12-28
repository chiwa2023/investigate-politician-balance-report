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
 * political_party_relation_person接続用Entity
 */
@Entity
@Table(name = "political_party_relation_person")
public class PoliticalPartyRelationPersonEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;
    
    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** タスク予定Id */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "political_party_relation_person_id")
    private Long politicalPartyRelationPersonId = INIT_Long;

    /**
     * タスク予定Idを取得する
     *
     * @return タスク予定Id
     */
    public Long getPoliticalPartyRelationPersonId() {
        return politicalPartyRelationPersonId;
    }

    /**
     * タスク予定Idを設定する
     *
     * @param politicalPartyRelationPersonId タスク予定Id
     */
    public void setPoliticalPartyRelationPersonId(final Long politicalPartyRelationPersonId) {
        this.politicalPartyRelationPersonId = politicalPartyRelationPersonId;
    }

    /** タスク予定同一識別コード */
    @Column(name = "political_party_relation_person_code")
    private Long politicalPartyRelationPersonCode = INIT_Long;

    /**
     * タスク予定同一識別コードを取得する
     *
     * @return タスク予定同一識別コード
     */
    public Long getPoliticalPartyRelationPersonCode() {
        return politicalPartyRelationPersonCode;
    }

    /**
     * タスク予定同一識別コードを設定する
     *
     * @param politicalPartyRelationPersonCode タスク予定同一識別コード
     */
    public void setPoliticalPartyRelationPersonCode(final Long politicalPartyRelationPersonCode) {
        this.politicalPartyRelationPersonCode = politicalPartyRelationPersonCode;
    }

    /** 最新区分 */
    @Column(name = "saishin_kbn")
    private Integer saishinKbn = INIT_Integer;

    /**
     * 最新区分を取得する
     *
     * @return 最新区分
     */
    public Integer getSaishinKbn() {
        return saishinKbn;
    }

    /**
     * 最新区分を設定する
     *
     * @param saishinKbn 最新区分
     */
    public void setSaishinKbn(final Integer saishinKbn) {
        this.saishinKbn = saishinKbn;
    }

    /** 政党Id */
    @Column(name = "political_party_id")
    private Long politicalPartyId = INIT_Long;

    /**
     * 政党Idを取得する
     *
     * @return 政党Id
     */
    public Long getPoliticalPartyId() {
        return politicalPartyId;
    }

    /**
     * 政党Idを設定する
     *
     * @param politicalPartyId 政党Id
     */
    public void setPoliticalPartyId(final Long politicalPartyId) {
        this.politicalPartyId = politicalPartyId;
    }

    /** 政党同一識別コード */
    @Column(name = "political_party_code")
    private Integer politicalPartyCode = INIT_Integer;

    /**
     * 政党同一識別コードを取得する
     *
     * @return 政党同一識別コード
     */
    public Integer getPoliticalPartyCode() {
        return politicalPartyCode;
    }

    /**
     * 政党同一識別コードを設定する
     *
     * @param politicalPartyCode 政党同一識別コード
     */
    public void setPoliticalPartyCode(final Integer politicalPartyCode) {
        this.politicalPartyCode = politicalPartyCode;
    }

    /** 政党名称 */
    @Column(name = "political_party_name")
    private String politicalPartyName = INIT_String;

    /**
     * 政党名称を取得する
     *
     * @return 政党名称
     */
    public String getPoliticalPartyName() {
        return politicalPartyName;
    }

    /**
     * 政党名称を設定する
     *
     * @param politicalPartyName 政党名称
     */
    public void setPoliticalPartyName(final String politicalPartyName) {
        this.politicalPartyName = politicalPartyName;
    }

    /** 関連者id */
    @Column(name = "relation_person_id")
    private Long relationPersonId = INIT_Long;

    /**
     * 関連者idを取得する
     *
     * @return 関連者id
     */
    public Long getRelationPersonId() {
        return relationPersonId;
    }

    /**
     * 関連者idを設定する
     *
     * @param relationPersonId 関連者id
     */
    public void setRelationPersonId(final Long relationPersonId) {
        this.relationPersonId = relationPersonId;
    }

    /** 政治関連者コード */
    @Column(name = "kanrensha_code")
    private String kanrenshaCode = INIT_String;

    /**
     * 政治関連者コードを取得する
     *
     * @return 政治関連者コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 政治関連者コードを設定する
     *
     * @param kanrenshaCode 政治関連者コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

    /** 関連者同一識別コード */
    @Column(name = "relation_person_code")
    private Integer relationPersonCode = INIT_Integer;

    /**
     * 関連者同一識別コードを取得する
     *
     * @return 関連者同一識別コード
     */
    public Integer getRelationPersonCode() {
        return relationPersonCode;
    }

    /**
     * 関連者同一識別コードを設定する
     *
     * @param relationPersonCode 関連者同一識別コード
     */
    public void setRelationPersonCode(final Integer relationPersonCode) {
        this.relationPersonCode = relationPersonCode;
    }

    /** 関連者名称 */
    @Column(name = "relation_person_name")
    private String relationPersonName = INIT_String;

    /**
     * 関連者名称を取得する
     *
     * @return 関連者名称
     */
    public String getRelationPersonName() {
        return relationPersonName;
    }

    /**
     * 関連者名称を設定する
     *
     * @param relationPersonName 関連者名称
     */
    public void setRelationPersonName(final String relationPersonName) {
        this.relationPersonName = relationPersonName;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Long insertUserId = INIT_Long;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    public Long getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
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
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザ同一識別コードを設定する
     *
     * @param insertUserCode 挿入ユーザ同一識別コード
     */
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
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ姓名を設定する
     *
     * @param insertUserName 挿入ユーザ姓名
     */
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
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入タイムスタンプを設定する
     *
     * @param insertTimestamp 挿入タイムスタンプ
     */
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
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新ユーザIdを設定する
     *
     * @param updateUserId 更新ユーザId
     */
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
    public Integer getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 更新ユーザ同一識別コードを設定する
     *
     * @param updateUserCode 更新ユーザ同一識別コード
     */
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
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 更新ユーザ姓名を設定する
     *
     * @param updateUserName 更新ユーザ姓名
     */
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
    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 更新タイムスタンプを設定する
     *
     * @param updateTimestamp 更新タイムスタンプ
     */
    public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

}
