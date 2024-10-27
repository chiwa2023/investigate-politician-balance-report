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
 * political_organization接続用Entity
 */
@Entity
@Table(name = "political_organization")
public class PoliticalOrganizationEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

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

    /** 政治団体Id */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /** 政治団体名称かな */
    @Column(name = "political_organization_name_kana")
    private String politicalOrganizationNameKana = INIT_String;

    /**
     * 政治団体名称かなを取得する
     *
     * @return 政治団体名称かな
     */
    public String getPoliticalOrganizationNameKana() {
        return politicalOrganizationNameKana;
    }

    /**
     * 政治団体名称かなを設定する
     *
     * @param politicalOrganizationNameKana 政治団体名称かな
     */
    public void setPoliticalOrganizationNameKana(final String politicalOrganizationNameKana) {
        this.politicalOrganizationNameKana = politicalOrganizationNameKana;
    }

    /** 政治団体区分 */
    @Column(name = "dantai_kbn")
    private String dantaiKbn = INIT_String;

    /**
     * 政治団体区分を取得する
     *
     * @return 政治団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 政治団体区分を設定する
     *
     * @param dantaiKbn 政治団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
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

    /** 代表者のユーザId */
    @Column(name = "daihyosha_user_id")
    private Long daihyoshaUserId = INIT_Long;

    /**
     * 代表者のユーザIdを取得する
     *
     * @return 代表者のユーザId
     */
    public Long getDaihyoshaUserId() {
        return daihyoshaUserId;
    }

    /**
     * 代表者のユーザIdを設定する
     *
     * @param daihyoshaUserId 代表者のユーザId
     */
    public void setDaihyoshaUserId(final Long daihyoshaUserId) {
        this.daihyoshaUserId = daihyoshaUserId;
    }

    /** 代表者のユーザ同一識別コード */
    @Column(name = "daihyosha_user_code")
    private Integer daihyoshaUserCode = INIT_Integer;

    /**
     * 代表者のユーザ同一識別コードを取得する
     *
     * @return 代表者のユーザ同一識別コード
     */
    public Integer getDaihyoshaUserCode() {
        return daihyoshaUserCode;
    }

    /**
     * 代表者のユーザ同一識別コードを設定する
     *
     * @param daihyoshaUserCode 代表者のユーザ同一識別コード
     */
    public void setDaihyoshaUserCode(final Integer daihyoshaUserCode) {
        this.daihyoshaUserCode = daihyoshaUserCode;
    }

    /** 代表者の姓名 */
    @Column(name = "daihyosha_name")
    private String daihyoshaName = INIT_String;

    /**
     * 代表者の姓名を取得する
     *
     * @return 代表者の姓名
     */
    public String getDaihyoshaName() {
        return daihyoshaName;
    }

    /**
     * 代表者の姓名を設定する
     *
     * @param daihyoshaName 代表者の姓名
     */
    public void setDaihyoshaName(final String daihyoshaName) {
        this.daihyoshaName = daihyoshaName;
    }

    /** 代表者の姓 */
    @Column(name = "daihyosha_first_name")
    private String daihyoshaFirstName = INIT_String;

    /**
     * 代表者の姓を取得する
     *
     * @return 代表者の姓
     */
    public String getDaihyoshaFirstName() {
        return daihyoshaFirstName;
    }

    /**
     * 代表者の姓を設定する
     *
     * @param daihyoshaFirstName 代表者の姓
     */
    public void setDaihyoshaFirstName(final String daihyoshaFirstName) {
        this.daihyoshaFirstName = daihyoshaFirstName;
    }

    /** 代表者の名 */
    @Column(name = "daihyosha_last_name")
    private String daihyoshaLastName = INIT_String;

    /**
     * 代表者の名を取得する
     *
     * @return 代表者の名
     */
    public String getDaihyoshaLastName() {
        return daihyoshaLastName;
    }

    /**
     * 代表者の名を設定する
     *
     * @param daihyoshaLastName 代表者の名
     */
    public void setDaihyoshaLastName(final String daihyoshaLastName) {
        this.daihyoshaLastName = daihyoshaLastName;
    }

    /** 代表者ミドルネーム */
    @Column(name = "daihyosha_middle_name")
    private String daihyoshaMiddleName = INIT_String;

    /**
     * 代表者ミドルネームを取得する
     *
     * @return 代表者ミドルネーム
     */
    public String getDaihyoshaMiddleName() {
        return daihyoshaMiddleName;
    }

    /**
     * 代表者ミドルネームを設定する
     *
     * @param daihyoshaMiddleName 代表者ミドルネーム
     */
    public void setDaihyoshaMiddleName(final String daihyoshaMiddleName) {
        this.daihyoshaMiddleName = daihyoshaMiddleName;
    }

    /** 代表者姓かな */
    @Column(name = "daihyosha_first_name_kana")
    private String daihyoshaFirstNameKana = INIT_String;

    /**
     * 代表者姓かなを取得する
     *
     * @return 代表者姓かな
     */
    public String getDaihyoshaFirstNameKana() {
        return daihyoshaFirstNameKana;
    }

    /**
     * 代表者姓かなを設定する
     *
     * @param daihyoshaFirstNameKana 代表者姓かな
     */
    public void setDaihyoshaFirstNameKana(final String daihyoshaFirstNameKana) {
        this.daihyoshaFirstNameKana = daihyoshaFirstNameKana;
    }

    /** 代表者名かな */
    @Column(name = "daihyosha_last_name_kana")
    private String daihyoshaLastNameKana = INIT_String;

    /**
     * 代表者名かなを取得する
     *
     * @return 代表者名かな
     */
    public String getDaihyoshaLastNameKana() {
        return daihyoshaLastNameKana;
    }

    /**
     * 代表者名かなを設定する
     *
     * @param daihyoshaLastNameKana 代表者名かな
     */
    public void setDaihyoshaLastNameKana(final String daihyoshaLastNameKana) {
        this.daihyoshaLastNameKana = daihyoshaLastNameKana;
    }

    /** 会計責任者のユーザId */
    @Column(name = "kaikei_sekininsha_user_id")
    private Long kaikeiSekininshaUserId = INIT_Long;

    /**
     * 会計責任者のユーザIdを取得する
     *
     * @return 会計責任者のユーザId
     */
    public Long getKaikeiSekininshaUserId() {
        return kaikeiSekininshaUserId;
    }

    /**
     * 会計責任者のユーザIdを設定する
     *
     * @param kaikeiSekininshaUserId 会計責任者のユーザId
     */
    public void setKaikeiSekininshaUserId(final Long kaikeiSekininshaUserId) {
        this.kaikeiSekininshaUserId = kaikeiSekininshaUserId;
    }

    /** 会計責任者のユーザ同一識別コード */
    @Column(name = "kaikei_sekininsha_user_code")
    private Integer kaikeiSekininshaUserCode = INIT_Integer;

    /**
     * 会計責任者のユーザ同一識別コードを取得する
     *
     * @return 会計責任者のユーザ同一識別コード
     */
    public Integer getKaikeiSekininshaUserCode() {
        return kaikeiSekininshaUserCode;
    }

    /**
     * 会計責任者のユーザ同一識別コードを設定する
     *
     * @param kaikeiSekininshaUserCode 会計責任者のユーザ同一識別コード
     */
    public void setKaikeiSekininshaUserCode(final Integer kaikeiSekininshaUserCode) {
        this.kaikeiSekininshaUserCode = kaikeiSekininshaUserCode;
    }

    /** 会計責任者の姓名 */
    @Column(name = "kaikei_sekininsha_name")
    private String kaikeiSekininshaName = INIT_String;

    /**
     * 会計責任者の姓名を取得する
     *
     * @return 会計責任者の姓名
     */
    public String getKaikeiSekininshaName() {
        return kaikeiSekininshaName;
    }

    /**
     * 会計責任者の姓名を設定する
     *
     * @param kaikeiSekininshaName 会計責任者の姓名
     */
    public void setKaikeiSekininshaName(final String kaikeiSekininshaName) {
        this.kaikeiSekininshaName = kaikeiSekininshaName;
    }

    /** 会計責任者の姓 */
    @Column(name = "kaikei_sekininsha_first_name")
    private String kaikeiSekininshaFirstName = INIT_String;

    /**
     * 会計責任者の姓を取得する
     *
     * @return 会計責任者の姓
     */
    public String getKaikeiSekininshaFirstName() {
        return kaikeiSekininshaFirstName;
    }

    /**
     * 会計責任者の姓を設定する
     *
     * @param kaikeiSekininshaFirstName 会計責任者の姓
     */
    public void setKaikeiSekininshaFirstName(final String kaikeiSekininshaFirstName) {
        this.kaikeiSekininshaFirstName = kaikeiSekininshaFirstName;
    }

    /** 会計責任者の名 */
    @Column(name = "kaikei_sekininsha_last_name")
    private String kaikeiSekininshaLastName = INIT_String;

    /**
     * 会計責任者の名を取得する
     *
     * @return 会計責任者の名
     */
    public String getKaikeiSekininshaLastName() {
        return kaikeiSekininshaLastName;
    }

    /**
     * 会計責任者の名を設定する
     *
     * @param kaikeiSekininshaLastName 会計責任者の名
     */
    public void setKaikeiSekininshaLastName(final String kaikeiSekininshaLastName) {
        this.kaikeiSekininshaLastName = kaikeiSekininshaLastName;
    }

    /** 会計責任者ミドルネーム */
    @Column(name = "kaikei_sekininsha_middle_name")
    private String kaikeiSekininshaMiddleName = INIT_String;

    /**
     * 会計責任者ミドルネームを取得する
     *
     * @return 会計責任者ミドルネーム
     */
    public String getKaikeiSekininshaMiddleName() {
        return kaikeiSekininshaMiddleName;
    }

    /**
     * 会計責任者ミドルネームを設定する
     *
     * @param kaikeiSekininshaMiddleName 会計責任者ミドルネーム
     */
    public void setKaikeiSekininshaMiddleName(final String kaikeiSekininshaMiddleName) {
        this.kaikeiSekininshaMiddleName = kaikeiSekininshaMiddleName;
    }

    /** 会計責任者姓かな */
    @Column(name = "kaikei_sekininsha_first_name_kana")
    private String kaikeiSekininshaFirstNameKana = INIT_String;

    /**
     * 会計責任者姓かなを取得する
     *
     * @return 会計責任者姓かな
     */
    public String getKaikeiSekininshaFirstNameKana() {
        return kaikeiSekininshaFirstNameKana;
    }

    /**
     * 会計責任者姓かなを設定する
     *
     * @param kaikeiSekininshaFirstNameKana 会計責任者姓かな
     */
    public void setKaikeiSekininshaFirstNameKana(final String kaikeiSekininshaFirstNameKana) {
        this.kaikeiSekininshaFirstNameKana = kaikeiSekininshaFirstNameKana;
    }

    /** 会計責任者名かな */
    @Column(name = "kaikei_sekininsha_last_name_kana")
    private String kaikeiSekininshaLastNameKana = INIT_String;

    /**
     * 会計責任者名かなを取得する
     *
     * @return 会計責任者名かな
     */
    public String getKaikeiSekininshaLastNameKana() {
        return kaikeiSekininshaLastNameKana;
    }

    /**
     * 会計責任者名かなを設定する
     *
     * @param kaikeiSekininshaLastNameKana 会計責任者名かな
     */
    public void setKaikeiSekininshaLastNameKana(final String kaikeiSekininshaLastNameKana) {
        this.kaikeiSekininshaLastNameKana = kaikeiSekininshaLastNameKana;
    }

    /** 会計責任者の代行者ユーザId */
    @Column(name = "kaikei_daiko_user_id")
    private Long kaikeiDaikoUserId = INIT_Long;

    /**
     * 会計責任者の代行者ユーザIdを取得する
     *
     * @return 会計責任者の代行者ユーザId
     */
    public Long getKaikeiDaikoUserId() {
        return kaikeiDaikoUserId;
    }

    /**
     * 会計責任者の代行者ユーザIdを設定する
     *
     * @param kaikeiDaikoUserId 会計責任者の代行者ユーザId
     */
    public void setKaikeiDaikoUserId(final Long kaikeiDaikoUserId) {
        this.kaikeiDaikoUserId = kaikeiDaikoUserId;
    }

    /** 会計責任者の代行者ユーザ同一識別コード */
    @Column(name = "kaikei_daiko_user_code")
    private Integer kaikeiDaikoUserCode = INIT_Integer;

    /**
     * 会計責任者の代行者ユーザ同一識別コードを取得する
     *
     * @return 会計責任者の代行者ユーザ同一識別コード
     */
    public Integer getKaikeiDaikoUserCode() {
        return kaikeiDaikoUserCode;
    }

    /**
     * 会計責任者の代行者ユーザ同一識別コードを設定する
     *
     * @param kaikeiDaikoUserCode 会計責任者の代行者ユーザ同一識別コード
     */
    public void setKaikeiDaikoUserCode(final Integer kaikeiDaikoUserCode) {
        this.kaikeiDaikoUserCode = kaikeiDaikoUserCode;
    }

    /** 会計責任者の代行者姓名 */
    @Column(name = "kaikei_daiko_name")
    private String kaikeiDaikoName = INIT_String;

    /**
     * 会計責任者の代行者姓名を取得する
     *
     * @return 会計責任者の代行者姓名
     */
    public String getKaikeiDaikoName() {
        return kaikeiDaikoName;
    }

    /**
     * 会計責任者の代行者姓名を設定する
     *
     * @param kaikeiDaikoName 会計責任者の代行者姓名
     */
    public void setKaikeiDaikoName(final String kaikeiDaikoName) {
        this.kaikeiDaikoName = kaikeiDaikoName;
    }

    /** 会計責任者代行者の姓 */
    @Column(name = "kaikei_daiko_first_name")
    private String kaikeiDaikoFirstName = INIT_String;

    /**
     * 会計責任者代行者の姓を取得する
     *
     * @return 会計責任者代行者の姓
     */
    public String getKaikeiDaikoFirstName() {
        return kaikeiDaikoFirstName;
    }

    /**
     * 会計責任者代行者の姓を設定する
     *
     * @param kaikeiDaikoFirstName 会計責任者代行者の姓
     */
    public void setKaikeiDaikoFirstName(final String kaikeiDaikoFirstName) {
        this.kaikeiDaikoFirstName = kaikeiDaikoFirstName;
    }

    /** 会計責任者代行者の名 */
    @Column(name = "kaikei_daiko_last_name")
    private String kaikeiDaikoLastName = INIT_String;

    /**
     * 会計責任者代行者の名を取得する
     *
     * @return 会計責任者代行者の名
     */
    public String getKaikeiDaikoLastName() {
        return kaikeiDaikoLastName;
    }

    /**
     * 会計責任者代行者の名を設定する
     *
     * @param kaikeiDaikoLastName 会計責任者代行者の名
     */
    public void setKaikeiDaikoLastName(final String kaikeiDaikoLastName) {
        this.kaikeiDaikoLastName = kaikeiDaikoLastName;
    }

    /** 会計責任者代行者ミドルネーム */
    @Column(name = "kaikei_daiko_middle_name")
    private String kaikeiDaikoMiddleName = INIT_String;

    /**
     * 会計責任者代行者ミドルネームを取得する
     *
     * @return 会計責任者代行者ミドルネーム
     */
    public String getKaikeiDaikoMiddleName() {
        return kaikeiDaikoMiddleName;
    }

    /**
     * 会計責任者代行者ミドルネームを設定する
     *
     * @param kaikeiDaikoMiddleName 会計責任者代行者ミドルネーム
     */
    public void setKaikeiDaikoMiddleName(final String kaikeiDaikoMiddleName) {
        this.kaikeiDaikoMiddleName = kaikeiDaikoMiddleName;
    }

    /** 会計責任者代行者姓かな */
    @Column(name = "kaikei_daiko_first_name_kana")
    private String kaikeiDaikoFirstNameKana = INIT_String;

    /**
     * 会計責任者代行者姓かなを取得する
     *
     * @return 会計責任者代行者姓かな
     */
    public String getKaikeiDaikoFirstNameKana() {
        return kaikeiDaikoFirstNameKana;
    }

    /**
     * 会計責任者代行者姓かなを設定する
     *
     * @param kaikeiDaikoFirstNameKana 会計責任者代行者姓かな
     */
    public void setKaikeiDaikoFirstNameKana(final String kaikeiDaikoFirstNameKana) {
        this.kaikeiDaikoFirstNameKana = kaikeiDaikoFirstNameKana;
    }

    /** 会計責任者代行者名かな */
    @Column(name = "kaikei_daiko_last_name_kana")
    private String kaikeiDaikoLastNameKana = INIT_String;

    /**
     * 会計責任者代行者名かなを取得する
     *
     * @return 会計責任者代行者名かな
     */
    public String getKaikeiDaikoLastNameKana() {
        return kaikeiDaikoLastNameKana;
    }

    /**
     * 会計責任者代行者名かなを設定する
     *
     * @param kaikeiDaikoLastNameKana 会計責任者代行者名かな
     */
    public void setKaikeiDaikoLastNameKana(final String kaikeiDaikoLastNameKana) {
        this.kaikeiDaikoLastNameKana = kaikeiDaikoLastNameKana;
    }

    /** 自由検索用カラム */
    @Column(name = "search_text")
    private String searchText = INIT_String;

    /**
     * 自由検索用カラムを取得する
     *
     * @return 自由検索用カラム
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 自由検索用カラムを設定する
     *
     * @param searchText 自由検索用カラム
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
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

}
