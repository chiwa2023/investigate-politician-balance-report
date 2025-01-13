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
 * relation_corp接続用Entity
 */
@Entity
@Table(name = "relation_corp")
public class RelationCorpEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

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

    /** 関連者企業(団体)Id */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_corp_id")
    private Long relationCorpId = INIT_Long;

    /**
     * 関連者企業(団体)Idを取得する
     *
     * @return 関連者企業(団体)Id
     */
    public Long getRelationCorpId() {
        return relationCorpId;
    }

    /**
     * 関連者企業(団体)Idを設定する
     *
     * @param relationCorpId 関連者企業(団体)Id
     */
    public void setRelationCorpId(final Long relationCorpId) {
        this.relationCorpId = relationCorpId;
    }

    /** 関連者企業(団体)同一識別コード */
    @Column(name = "relation_corp_code")
    private Integer relationCorpCode = INIT_Integer;

    /**
     * 関連者企業(団体)同一識別コードを取得する
     *
     * @return 関連者企業(団体)同一識別コード
     */
    public Integer getRelationCorpCode() {
        return relationCorpCode;
    }

    /**
     * 関連者企業(団体)同一識別コードを設定する
     *
     * @param relationCorpCode 関連者企業(団体)同一識別コード
     */
    public void setRelationCorpCode(final Integer relationCorpCode) {
        this.relationCorpCode = relationCorpCode;
    }

    /** 関連者企業(団体)名 */
    @Column(name = "relation_corp_name")
    private String relationCorpName = INIT_String;

    /**
     * 関連者企業(団体)名を取得する
     *
     * @return 関連者企業(団体)名
     */
    public String getRelationCorpName() {
        return relationCorpName;
    }

    /**
     * 関連者企業(団体)名を設定する
     *
     * @param relationCorpName 関連者企業(団体)名
     */
    public void setRelationCorpName(final String relationCorpName) {
        this.relationCorpName = relationCorpName;
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

    /** 関連者住所Id */
    @Column(name = "address_id")
    private Long addressId = INIT_Long;

    /**
     * 関連者住所Idを取得する
     *
     * @return 関連者住所Id
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * 関連者住所Idを設定する
     *
     * @param addressId 関連者住所Id
     */
    public void setAddressId(final Long addressId) {
        this.addressId = addressId;
    }

    /** 関連者住所同一識別コード */
    @Column(name = "address_code")
    private Integer addressCode = INIT_Integer;

    /**
     * 関連者住所同一識別コードを取得する
     *
     * @return 関連者住所同一識別コード
     */
    public Integer getAddressCode() {
        return addressCode;
    }

    /**
     * 関連者住所同一識別コードを設定する
     *
     * @param addressCode 関連者住所同一識別コード
     */
    public void setAddressCode(final Integer addressCode) {
        this.addressCode = addressCode;
    }

    /** 関連者住所名称 */
    @Column(name = "address_name")
    private String addressName = INIT_String;

    /**
     * 関連者住所名称を取得する
     *
     * @return 関連者住所名称
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 関連者住所名称を設定する
     *
     * @param addressName 関連者住所名称
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
    }

    /** 全文検索テキスト */
    @Column(name = "table_all_search_text")
    private String tableAllSearchText = INIT_String;

    /**
     * 全文検索テキストを取得する
     *
     * @return 全文検索テキスト
     */
    public String getTableAllSearchText() {
        return tableAllSearchText;
    }

    /**
     * 全文検索テキストを設定する
     *
     * @param tableAllSearchText 全文検索テキスト
     */
    public void setTableAllSearchText(final String tableAllSearchText) {
        this.tableAllSearchText = tableAllSearchText;
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
