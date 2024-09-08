package mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * offering_balancesheet_0719_real_estate_2025接続用Entity
 */
@Entity
@Table(name = "offering_balancesheet_0719_real_estate_2025")
public class OfferingBalancesheet0719RealEstate2025Entity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;
    
    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1980,1,1);

    /** 初期データ(Timestamp) */
    private static final Timestamp INIT_Timestamp = Timestamp.valueOf(INIT_LocalDate.atTime(0, 0, 0));

    /** 収支報告書様式7その19不動産明細Id */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offering_balancesheet_0719_real_estate_id")
    private Long offeringBalancesheet0719RealEstateId = INIT_Long;

    /**
     * 収支報告書様式7その19不動産明細Idを取得する
     *
     * @return 収支報告書様式7その19不動産明細Id
     */
    public Long getOfferingBalancesheet0719RealEstateId() {
        return offeringBalancesheet0719RealEstateId;
    }

    /**
     * 収支報告書様式7その19不動産明細Idを設定する
     *
     * @param offeringBalancesheet0719RealEstateId 収支報告書様式7その19不動産明細Id
     */
    public void setOfferingBalancesheet0719RealEstateId(final Long offeringBalancesheet0719RealEstateId) {
        this.offeringBalancesheet0719RealEstateId = offeringBalancesheet0719RealEstateId;
    }

    /** 収支報告書様式7その19同一識別コード */
    @Column(name = "offering_balancesheet_0719_real_estate_code")
    private Long offeringBalancesheet0719RealEstateCode = INIT_Long;

    /**
     * 収支報告書様式7その19同一識別コードを取得する
     *
     * @return 収支報告書様式7その19同一識別コード
     */
    public Long getOfferingBalancesheet0719RealEstateCode() {
        return offeringBalancesheet0719RealEstateCode;
    }

    /**
     * 収支報告書様式7その19同一識別コードを設定する
     *
     * @param offeringBalancesheet0719RealEstateCode 収支報告書様式7その19同一識別コード
     */
    public void setOfferingBalancesheet0719RealEstateCode(final Long offeringBalancesheet0719RealEstateCode) {
        this.offeringBalancesheet0719RealEstateCode = offeringBalancesheet0719RealEstateCode;
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

    /** 不動産区分 */
    @Column(name = "real_estate_kbn")
    private Integer realEstateKbn = INIT_Integer;

    /**
     * 不動産区分を取得する
     *
     * @return 不動産区分
     */
    public Integer getRealEstateKbn() {
        return realEstateKbn;
    }

    /**
     * 不動産区分を設定する
     *
     * @param realEstateKbn 不動産区分
     */
    public void setRealEstateKbn(final Integer realEstateKbn) {
        this.realEstateKbn = realEstateKbn;
    }

    /** 不動産区分名称 */
    @Column(name = "real_estate_kbn_name")
    private String realEstateKbnName = INIT_String;

    /**
     * 不動産区分名称を取得する
     *
     * @return 不動産区分名称
     */
    public String getRealEstateKbnName() {
        return realEstateKbnName;
    }

    /**
     * 不動産区分名称を設定する
     *
     * @param realEstateKbnName 不動産区分名称
     */
    public void setRealEstateKbnName(final String realEstateKbnName) {
        this.realEstateKbnName = realEstateKbnName;
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

    /** 摘要 */
    @Column(name = "tekiyou")
    private String tekiyou = INIT_String;

    /**
     * 摘要を取得する
     *
     * @return 摘要
     */
    public String getTekiyou() {
        return tekiyou;
    }

    /**
     * 摘要を設定する
     *
     * @param tekiyou 摘要
     */
    public void setTekiyou(final String tekiyou) {
        this.tekiyou = tekiyou;
    }

    /** 用途 */
    @Column(name = "youto")
    private String youto = INIT_String;

    /**
     * 用途を取得する
     *
     * @return 用途
     */
    public String getYouto() {
        return youto;
    }

    /**
     * 用途を設定する
     *
     * @param youto 用途
     */
    public void setYouto(final String youto) {
        this.youto = youto;
    }

    /** 使用者と代表者との関係 */
    @Column(name = "kankei_shiyousha")
    private String kankeiShiyousha = INIT_String;

    /**
     * 使用者と代表者との関係を取得する
     *
     * @return 使用者と代表者との関係
     */
    public String getKankeiShiyousha() {
        return kankeiShiyousha;
    }

    /**
     * 使用者と代表者との関係を設定する
     *
     * @param kankeiShiyousha 使用者と代表者との関係
     */
    public void setKankeiShiyousha(final String kankeiShiyousha) {
        this.kankeiShiyousha = kankeiShiyousha;
    }

    /** 使用の用途 */
    @Column(name = "shiyou_youto")
    private String shiyouYouto = INIT_String;

    /**
     * 使用の用途を取得する
     *
     * @return 使用の用途
     */
    public String getShiyouYouto() {
        return shiyouYouto;
    }

    /**
     * 使用の用途を設定する
     *
     * @param shiyouYouto 使用の用途
     */
    public void setShiyouYouto(final String shiyouYouto) {
        this.shiyouYouto = shiyouYouto;
    }

    /** 使用面積 */
    @Column(name = "shiyou_menseki")
    private String shiyouMenseki = INIT_String;

    /**
     * 使用面積を取得する
     *
     * @return 使用面積
     */
    public String getShiyouMenseki() {
        return shiyouMenseki;
    }

    /**
     * 使用面積を設定する
     *
     * @param shiyouMenseki 使用面積
     */
    public void setShiyouMenseki(final String shiyouMenseki) {
        this.shiyouMenseki = shiyouMenseki;
    }

    /** 使用価格 */
    @Column(name = "shiyou_kakaku")
    private Long shiyouKakaku = INIT_Long;

    /**
     * 使用価格を取得する
     *
     * @return 使用価格
     */
    public Long getShiyouKakaku() {
        return shiyouKakaku;
    }

    /**
     * 使用価格を設定する
     *
     * @param shiyouKakaku 使用価格
     */
    public void setShiyouKakaku(final Long shiyouKakaku) {
        this.shiyouKakaku = shiyouKakaku;
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
    private Timestamp insertTimestamp = INIT_Timestamp;

    /**
     * 挿入タイムスタンプを取得する
     *
     * @return 挿入タイムスタンプ
     */
    @Override
    public Timestamp getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入タイムスタンプを設定する
     *
     * @param insertTimestamp 挿入タイムスタンプ
     */
    @Override
    public void setInsertTimestamp(final Timestamp insertTimestamp) {
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
    private Timestamp updateTimestamp = INIT_Timestamp;

    /**
     * 更新タイムスタンプを取得する
     *
     * @return 更新タイムスタンプ
     */
    @Override
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 更新タイムスタンプを設定する
     *
     * @param updateTimestamp 更新タイムスタンプ
     */
    @Override
    public void setUpdateTimestamp(final Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

}
