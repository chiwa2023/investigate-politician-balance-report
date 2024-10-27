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
 * political_organization_access接続用Entity
 */
@Entity
@Table(name = "political_organization_access")
public class PoliticalOrganizationAccessEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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

    /** 政治団体連絡先Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "political_organization_access_id")
    private Long politicalOrganizationAccessId = INIT_Long;

    /**
     * 政治団体連絡先Idを取得する
     *
     * @return 政治団体連絡先Id
     */
    public Long getPoliticalOrganizationAccessId() {
        return politicalOrganizationAccessId;
    }

    /**
     * 政治団体連絡先Idを設定する
     *
     * @param politicalOrganizationAccessId 政治団体連絡先Id
     */
    public void setPoliticalOrganizationAccessId(final Long politicalOrganizationAccessId) {
        this.politicalOrganizationAccessId = politicalOrganizationAccessId;
    }

    /** 政治団体連絡先同一識別コード */
    @Column(name = "spolitical_organization_access_code")
    private Integer spoliticalOrganizationAccessCode = INIT_Integer;

    /**
     * 政治団体連絡先同一識別コードを取得する
     *
     * @return 政治団体連絡先同一識別コード
     */
    public Integer getSpoliticalOrganizationAccessCode() {
        return spoliticalOrganizationAccessCode;
    }

    /**
     * 政治団体連絡先同一識別コードを設定する
     *
     * @param spoliticalOrganizationAccessCode 政治団体連絡先同一識別コード
     */
    public void setSpoliticalOrganizationAccessCode(final Integer spoliticalOrganizationAccessCode) {
        this.spoliticalOrganizationAccessCode = spoliticalOrganizationAccessCode;
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
    private Long politicalOrganizationCode = INIT_Long;

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Long getPoliticalOrganizationCode() {
        return politicalOrganizationCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param politicalOrganizationCode 政治団体同一識別コード
     */
    public void setPoliticalOrganizationCode(final Long politicalOrganizationCode) {
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

    /** 郵便番号1 */
    @Column(name = "postalcode1")
    private String postalcode1 = INIT_String;

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostalcode1() {
        return postalcode1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postalcode1 郵便番号1
     */
    public void setPostalcode1(final String postalcode1) {
        this.postalcode1 = postalcode1;
    }

    /** 郵便番号2 */
    @Column(name = "postalcode2")
    private String postalcode2 = INIT_String;

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostalcode2() {
        return postalcode2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postalcode2 郵便番号2
     */
    public void setPostalcode2(final String postalcode2) {
        this.postalcode2 = postalcode2;
    }

    /** 全住所 */
    @Column(name = "address_all")
    private String addressAll = INIT_String;

    /**
     * 全住所を取得する
     *
     * @return 全住所
     */
    public String getAddressAll() {
        return addressAll;
    }

    /**
     * 全住所を設定する
     *
     * @param addressAll 全住所
     */
    public void setAddressAll(final String addressAll) {
        this.addressAll = addressAll;
    }

    /** 住所(郵便番号呼び出し) */
    @Column(name = "address_postal")
    private String addressPostal = INIT_String;

    /**
     * 住所(郵便番号呼び出し)を取得する
     *
     * @return 住所(郵便番号呼び出し)
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所(郵便番号呼び出し)を設定する
     *
     * @param addressPostal 住所(郵便番号呼び出し)
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /** 住所番地 */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressBlock 住所番地
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /** 住所建物 */
    @Column(name = "address_building")
    private String addressBuilding = INIT_String;

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressBuilding() {
        return addressBuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressBuilding 住所建物
     */
    public void setAddressBuilding(final String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    /** 電話番号全体 */
    @Column(name = "phon_all")
    private String phonAll = INIT_String;

    /**
     * 電話番号全体を取得する
     *
     * @return 電話番号全体
     */
    public String getPhonAll() {
        return phonAll;
    }

    /**
     * 電話番号全体を設定する
     *
     * @param phonAll 電話番号全体
     */
    public void setPhonAll(final String phonAll) {
        this.phonAll = phonAll;
    }

    /** 電話番号1 */
    @Column(name = "phon_no1")
    private String phonNo1 = INIT_String;

    /**
     * 電話番号1を取得する
     *
     * @return 電話番号1
     */
    public String getPhonNo1() {
        return phonNo1;
    }

    /**
     * 電話番号1を設定する
     *
     * @param phonNo1 電話番号1
     */
    public void setPhonNo1(final String phonNo1) {
        this.phonNo1 = phonNo1;
    }

    /** 電話番号2 */
    @Column(name = "phon_no2")
    private String phonNo2 = INIT_String;

    /**
     * 電話番号2を取得する
     *
     * @return 電話番号2
     */
    public String getPhonNo2() {
        return phonNo2;
    }

    /**
     * 電話番号2を設定する
     *
     * @param phonNo2 電話番号2
     */
    public void setPhonNo2(final String phonNo2) {
        this.phonNo2 = phonNo2;
    }

    /** 電話番号3 */
    @Column(name = "phon_no3")
    private String phonNo3 = INIT_String;

    /**
     * 電話番号3を取得する
     *
     * @return 電話番号3
     */
    public String getPhonNo3() {
        return phonNo3;
    }

    /**
     * 電話番号3を設定する
     *
     * @param phonNo3 電話番号3
     */
    public void setPhonNo3(final String phonNo3) {
        this.phonNo3 = phonNo3;
    }

    /** メールアドレス */
    @Column(name = "mail_addless")
    private String mailAddless = INIT_String;

    /**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
    public String getMailAddless() {
        return mailAddless;
    }

    /**
     * メールアドレスを設定する
     *
     * @param mailAddless メールアドレス
     */
    public void setMailAddless(final String mailAddless) {
        this.mailAddless = mailAddless;
    }

    /** 自団体ホームページURL */
    @Column(name = "owned_media_url")
    private String ownedMediaUrl = INIT_String;

    /**
     * 自団体ホームページURLを取得する
     *
     * @return 自団体ホームページURL
     */
    public String getOwnedMediaUrl() {
        return ownedMediaUrl;
    }

    /**
     * 自団体ホームページURLを設定する
     *
     * @param ownedMediaUrl 自団体ホームページURL
     */
    public void setOwnedMediaUrl(final String ownedMediaUrl) {
        this.ownedMediaUrl = ownedMediaUrl;
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
