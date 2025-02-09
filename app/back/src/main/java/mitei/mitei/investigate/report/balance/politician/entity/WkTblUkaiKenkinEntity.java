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
 * wk_tbl_ukai_kenkin接続用Entity
 */
@Entity
@Table(name = "wk_tbl_ukai_kenkin")
public class WkTblUkaiKenkinEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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

    /** 迂回献金キャッチャーワークテーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_ukai_kenkin_id")
    private Integer wkTblUkaiKenkinId = INIT_Integer;

    /**
     * 迂回献金キャッチャーワークテーブルIdを取得する
     *
     * @return 迂回献金キャッチャーワークテーブルId
     */
    public Integer getWkTblUkaiKenkinId() {
        return wkTblUkaiKenkinId;
    }

    /**
     * 迂回献金キャッチャーワークテーブルIdを設定する
     *
     * @param wkTblUkaiKenkinId 迂回献金キャッチャーワークテーブルId
     */
    public void setWkTblUkaiKenkinId(final Integer wkTblUkaiKenkinId) {
        this.wkTblUkaiKenkinId = wkTblUkaiKenkinId;
    }

    /** 迂回献金キャッチャーワークテーブル予定同一識別コード */
    @Column(name = "wk_tbl_ukai_kenkin_code")
    private Integer wkTblUkaiKenkinCode = INIT_Integer;

    /**
     * 迂回献金キャッチャーワークテーブル予定同一識別コードを取得する
     *
     * @return 迂回献金キャッチャーワークテーブル予定同一識別コード
     */
    public Integer getWkTblUkaiKenkinCode() {
        return wkTblUkaiKenkinCode;
    }

    /**
     * 迂回献金キャッチャーワークテーブル予定同一識別コードを設定する
     *
     * @param wkTblUkaiKenkinCode 迂回献金キャッチャーワークテーブル予定同一識別コード
     */
    public void setWkTblUkaiKenkinCode(final Integer wkTblUkaiKenkinCode) {
        this.wkTblUkaiKenkinCode = wkTblUkaiKenkinCode;
    }

    /** 複写元テーブルId */
    @Column(name = "table_id")
    private Long tablleId = INIT_Long;

    /** 報告年 */
    @Column(name = "houkoku_nen")
    private Integer houkokuNen = INIT_Integer;

    /**
     * 複写元テーブルIdを取得する
     *
     * @return 複写元テーブルId
     */
    public Long getTablleId() {
        return tablleId;
    }

    /**
     * 複写元テーブルIdを設定する
     *
     * @param tablleId 複写元テーブルId
     */
    public void setTablleId(final Long tablleId) {
        this.tablleId = tablleId;
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

    /** 収支報告書記載団体代表者Id */
    @Column(name = "poli_org_delegate_id")
    private Long poliOrgDelegateId = INIT_Long;

    /**
     * 収支報告書記載団体代表者Idを取得する
     *
     * @return 収支報告書記載団体代表者Id
     */
    public Long getPoliOrgDelegateId() {
        return poliOrgDelegateId;
    }

    /**
     * 収支報告書記載団体代表者Idを設定する
     *
     * @param poliOrgDelegateId 収支報告書記載団体代表者Id
     */
    public void setPoliOrgDelegateId(final Long poliOrgDelegateId) {
        this.poliOrgDelegateId = poliOrgDelegateId;
    }

    /** 収支報告書記載団体代表者同一識別コード */
    @Column(name = "poli_org_delegate_code")
    private Integer poliOrgDelegateCode = INIT_Integer;

    /**
     * 収支報告書記載団体代表者同一識別コードを取得する
     *
     * @return 収支報告書記載団体代表者同一識別コード
     */
    public Integer getPoliOrgDelegateCode() {
        return poliOrgDelegateCode;
    }

    /**
     * 収支報告書記載団体代表者同一識別コードを設定する
     *
     * @param poliOrgDelegateCode 収支報告書記載団体代表者同一識別コード
     */
    public void setPoliOrgDelegateCode(final Integer poliOrgDelegateCode) {
        this.poliOrgDelegateCode = poliOrgDelegateCode;
    }

    /** 収支報告書記載団体代表者名称 */
    @Column(name = "poli_org_delegate_name")
    private String poliOrgDelegateName = INIT_String;

    /**
     * 収支報告書記載団体代表者名称を取得する
     *
     * @return 収支報告書記載団体代表者名称
     */
    public String getPoliOrgDelegateName() {
        return poliOrgDelegateName;
    }

    /**
     * 収支報告書記載団体代表者名称を設定する
     *
     * @param poliOrgDelegateName 収支報告書記載団体代表者名称
     */
    public void setPoliOrgDelegateName(final String poliOrgDelegateName) {
        this.poliOrgDelegateName = poliOrgDelegateName;
    }

    /** 収支報告書記載団体会計責任者Id */
    @Column(name = "poli_org_account_manager_id")
    private Long poliOrgAccountManagerId = INIT_Long;

    /**
     * 収支報告書記載団体会計責任者Idを取得する
     *
     * @return 収支報告書記載団体会計責任者Id
     */
    public Long getPoliOrgAccountManagerId() {
        return poliOrgAccountManagerId;
    }

    /**
     * 収支報告書記載団体会計責任者Idを設定する
     *
     * @param poliOrgAccountManagerId 収支報告書記載団体会計責任者Id
     */
    public void setPoliOrgAccountManagerId(final Long poliOrgAccountManagerId) {
        this.poliOrgAccountManagerId = poliOrgAccountManagerId;
    }

    /** 収支報告書記載団体会計責任者同一識別コード */
    @Column(name = "poli_org_account_manager_code")
    private Integer poliOrgAccountManagerCode = INIT_Integer;

    /**
     * 収支報告書記載団体会計責任者同一識別コードを取得する
     *
     * @return 収支報告書記載団体会計責任者同一識別コード
     */
    public Integer getPoliOrgAccountManagerCode() {
        return poliOrgAccountManagerCode;
    }

    /**
     * 収支報告書記載団体会計責任者同一識別コードを設定する
     *
     * @param poliOrgAccountManagerCode 収支報告書記載団体会計責任者同一識別コード
     */
    public void setPoliOrgAccountManagerCode(final Integer poliOrgAccountManagerCode) {
        this.poliOrgAccountManagerCode = poliOrgAccountManagerCode;
    }

    /** 収支報告書記載団体会計責任者名称 */
    @Column(name = "poli_org_account_manager_name")
    private String poliOrgAccountManagerName = INIT_String;

    /**
     * 収支報告書記載団体会計責任者名称を取得する
     *
     * @return 収支報告書記載団体会計責任者名称
     */
    public String getPoliOrgAccountManagerName() {
        return poliOrgAccountManagerName;
    }

    /**
     * 収支報告書記載団体会計責任者名称を設定する
     *
     * @param poliOrgAccountManagerName 収支報告書記載団体会計責任者名称
     */
    public void setPoliOrgAccountManagerName(final String poliOrgAccountManagerName) {
        this.poliOrgAccountManagerName = poliOrgAccountManagerName;
    }

    /** 収支報告書資金管理団体登録者Id */
    @Column(name = "poli_org_shikin_dantai_id")
    private Long poliOrgShikinDantaiId = INIT_Long;

    /**
     * 収支報告書資金管理団体登録者Idを取得する
     *
     * @return 収支報告書資金管理団体登録者Id
     */
    public Long getPoliOrgShikinDantaiId() {
        return poliOrgShikinDantaiId;
    }

    /**
     * 収支報告書資金管理団体登録者Idを設定する
     *
     * @param poliOrgShikinDantaiId 収支報告書資金管理団体登録者Id
     */
    public void setPoliOrgShikinDantaiId(final Long poliOrgShikinDantaiId) {
        this.poliOrgShikinDantaiId = poliOrgShikinDantaiId;
    }

    /** 収支報告書資金管理団体登録者同一識別コード */
    @Column(name = "poli_org_shikin_dantai_code")
    private Integer poliOrgShikinDantaiCode = INIT_Integer;

    /**
     * 収支報告書資金管理団体登録者同一識別コードを取得する
     *
     * @return 収支報告書資金管理団体登録者同一識別コード
     */
    public Integer getPoliOrgShikinDantaiCode() {
        return poliOrgShikinDantaiCode;
    }

    /**
     * 収支報告書資金管理団体登録者同一識別コードを設定する
     *
     * @param poliOrgShikinDantaiCode 収支報告書資金管理団体登録者同一識別コード
     */
    public void setPoliOrgShikinDantaiCode(final Integer poliOrgShikinDantaiCode) {
        this.poliOrgShikinDantaiCode = poliOrgShikinDantaiCode;
    }

    /** 収支報告書資金管理団体登録者名称 */
    @Column(name = "poli_org_shikin_dantai_name")
    private String poliOrgShikinDantaiName = INIT_String;

    /**
     * 収支報告書資金管理団体登録者名称を取得する
     *
     * @return 収支報告書資金管理団体登録者名称
     */
    public String getPoliOrgShikinDantaiName() {
        return poliOrgShikinDantaiName;
    }

    /**
     * 収支報告書資金管理団体登録者名称を設定する
     *
     * @param poliOrgShikinDantaiName 収支報告書資金管理団体登録者名称
     */
    public void setPoliOrgShikinDantaiName(final String poliOrgShikinDantaiName) {
        this.poliOrgShikinDantaiName = poliOrgShikinDantaiName;
    }

    /** 収支報告書国会議員1関連者 */
    @Column(name = "poli_org_kokkai_giin1_id")
    private Long poliOrgKokkaiGiin1Id = INIT_Long;

    /**
     * 収支報告書国会議員1関連者を取得する
     *
     * @return 収支報告書国会議員1関連者
     */
    public Long getPoliOrgKokkaiGiin1Id() {
        return poliOrgKokkaiGiin1Id;
    }

    /**
     * 収支報告書国会議員1関連者を設定する
     *
     * @param poliOrgKokkaiGiin1Id 収支報告書国会議員1関連者
     */
    public void setPoliOrgKokkaiGiin1Id(final Long poliOrgKokkaiGiin1Id) {
        this.poliOrgKokkaiGiin1Id = poliOrgKokkaiGiin1Id;
    }

    /** 収支報告書国会議員1関連者同一識別コード */
    @Column(name = "poli_org_kokkai_giin1_code")
    private Integer poliOrgKokkaiGiin1Code = INIT_Integer;

    /**
     * 収支報告書国会議員1関連者同一識別コードを取得する
     *
     * @return 収支報告書国会議員1関連者同一識別コード
     */
    public Integer getPoliOrgKokkaiGiin1Code() {
        return poliOrgKokkaiGiin1Code;
    }

    /**
     * 収支報告書国会議員1関連者同一識別コードを設定する
     *
     * @param poliOrgKokkaiGiin1Code 収支報告書国会議員1関連者同一識別コード
     */
    public void setPoliOrgKokkaiGiin1Code(final Integer poliOrgKokkaiGiin1Code) {
        this.poliOrgKokkaiGiin1Code = poliOrgKokkaiGiin1Code;
    }

    /** 収支報告書国会議員1関連者名称 */
    @Column(name = "poli_org_kokkai_giin1_name")
    private String poliOrgKokkaiGiin1Name = INIT_String;

    /**
     * 収支報告書国会議員1関連者名称を取得する
     *
     * @return 収支報告書国会議員1関連者名称
     */
    public String getPoliOrgKokkaiGiin1Name() {
        return poliOrgKokkaiGiin1Name;
    }

    /**
     * 収支報告書国会議員1関連者名称を設定する
     *
     * @param poliOrgKokkaiGiin1Name 収支報告書国会議員1関連者名称
     */
    public void setPoliOrgKokkaiGiin1Name(final String poliOrgKokkaiGiin1Name) {
        this.poliOrgKokkaiGiin1Name = poliOrgKokkaiGiin1Name;
    }

    /** 収支報告書国会議員2関連者Id */
    @Column(name = "poli_org_kokkai_giin2_id")
    private Long poliOrgKokkaiGiin2Id = INIT_Long;

    /**
     * 収支報告書国会議員2関連者Idを取得する
     *
     * @return 収支報告書国会議員2関連者Id
     */
    public Long getPoliOrgKokkaiGiin2Id() {
        return poliOrgKokkaiGiin2Id;
    }

    /**
     * 収支報告書国会議員2関連者Idを設定する
     *
     * @param poliOrgKokkaiGiin2Id 収支報告書国会議員2関連者Id
     */
    public void setPoliOrgKokkaiGiin2Id(final Long poliOrgKokkaiGiin2Id) {
        this.poliOrgKokkaiGiin2Id = poliOrgKokkaiGiin2Id;
    }

    /** 収支報告書国会議員2関連者同一識別コード */
    @Column(name = "poli_org_kokkai_giin2_code")
    private Integer poliOrgKokkaiGiin2Code = INIT_Integer;

    /**
     * 収支報告書国会議員2関連者同一識別コードを取得する
     *
     * @return 収支報告書国会議員2関連者同一識別コード
     */
    public Integer getPoliOrgKokkaiGiin2Code() {
        return poliOrgKokkaiGiin2Code;
    }

    /**
     * 収支報告書国会議員2関連者同一識別コードを設定する
     *
     * @param poliOrgKokkaiGiin2Code 収支報告書国会議員2関連者同一識別コード
     */
    public void setPoliOrgKokkaiGiin2Code(final Integer poliOrgKokkaiGiin2Code) {
        this.poliOrgKokkaiGiin2Code = poliOrgKokkaiGiin2Code;
    }

    /** 収支報告書国会議員2関連者名称 */
    @Column(name = "poli_org_kokkai_giin2_name")
    private String poliOrgKokkaiGiin2Name = INIT_String;

    /**
     * 収支報告書国会議員2関連者名称を取得する
     *
     * @return 収支報告書国会議員2関連者名称
     */
    public String getPoliOrgKokkaiGiin2Name() {
        return poliOrgKokkaiGiin2Name;
    }

    /**
     * 収支報告書国会議員2関連者名称を設定する
     *
     * @param poliOrgKokkaiGiin2Name 収支報告書国会議員2関連者名称
     */
    public void setPoliOrgKokkaiGiin2Name(final String poliOrgKokkaiGiin2Name) {
        this.poliOrgKokkaiGiin2Name = poliOrgKokkaiGiin2Name;
    }

    /** 収支報告書国会議員3関連者Id */
    @Column(name = "poli_org_kokkai_giin3_id")
    private Long poliOrgKokkaiGiin3Id = INIT_Long;

    /**
     * 収支報告書国会議員3関連者Idを取得する
     *
     * @return 収支報告書国会議員3関連者Id
     */
    public Long getPoliOrgKokkaiGiin3Id() {
        return poliOrgKokkaiGiin3Id;
    }

    /**
     * 収支報告書国会議員3関連者Idを設定する
     *
     * @param poliOrgKokkaiGiin3Id 収支報告書国会議員3関連者Id
     */
    public void setPoliOrgKokkaiGiin3Id(final Long poliOrgKokkaiGiin3Id) {
        this.poliOrgKokkaiGiin3Id = poliOrgKokkaiGiin3Id;
    }

    /** 収支報告書国会議員3関連者同一識別コード */
    @Column(name = "poli_org_kokkai_giin3_code")
    private Integer poliOrgKokkaiGiin3Code = INIT_Integer;

    /**
     * 収支報告書国会議員3関連者同一識別コードを取得する
     *
     * @return 収支報告書国会議員3関連者同一識別コード
     */
    public Integer getPoliOrgKokkaiGiin3Code() {
        return poliOrgKokkaiGiin3Code;
    }

    /**
     * 収支報告書国会議員3関連者同一識別コードを設定する
     *
     * @param poliOrgKokkaiGiin3Code 収支報告書国会議員3関連者同一識別コード
     */
    public void setPoliOrgKokkaiGiin3Code(final Integer poliOrgKokkaiGiin3Code) {
        this.poliOrgKokkaiGiin3Code = poliOrgKokkaiGiin3Code;
    }

    /** 収支報告書国会議員3関連者名称 */
    @Column(name = "poli_org_kokkai_giin3_name")
    private String poliOrgKokkaiGiin3Name = INIT_String;

    /**
     * 収支報告書国会議員3関連者名称を取得する
     *
     * @return 収支報告書国会議員3関連者名称
     */
    public String getPoliOrgKokkaiGiin3Name() {
        return poliOrgKokkaiGiin3Name;
    }

    /**
     * 収支報告書国会議員3関連者名称を設定する
     *
     * @param poliOrgKokkaiGiin3Name 収支報告書国会議員3関連者名称
     */
    public void setPoliOrgKokkaiGiin3Name(final String poliOrgKokkaiGiin3Name) {
        this.poliOrgKokkaiGiin3Name = poliOrgKokkaiGiin3Name;
    }

    /** 収支報告書政治団体Id */
    @Column(name = "political_org_id")
    private Long politicalOrgId = INIT_Long;

    /**
     * 収支報告書政治団体Idを取得する
     *
     * @return 収支報告書政治団体Id
     */
    public Long getPoliticalOrgId() {
        return politicalOrgId;
    }

    /**
     * 収支報告書政治団体Idを設定する
     *
     * @param politicalOrgId 収支報告書政治団体Id
     */
    public void setPoliticalOrgId(final Long politicalOrgId) {
        this.politicalOrgId = politicalOrgId;
    }

    /** 収支報告書政治団体同一識別コード */
    @Column(name = "political_org_code")
    private Integer politicalOrgCode = INIT_Integer;

    /**
     * 収支報告書政治団体同一識別コードを取得する
     *
     * @return 収支報告書政治団体同一識別コード
     */
    public Integer getPoliticalOrgCode() {
        return politicalOrgCode;
    }

    /**
     * 収支報告書政治団体同一識別コードを設定する
     *
     * @param politicalOrgCode 収支報告書政治団体同一識別コード
     */
    public void setPoliticalOrgCode(final Integer politicalOrgCode) {
        this.politicalOrgCode = politicalOrgCode;
    }

    /** 収支報告書政治団体名称 */
    @Column(name = "political_org_name")
    private String politicalOrgName = INIT_String;

    /**
     * 収支報告書政治団体名称を取得する
     *
     * @return 収支報告書政治団体名称
     */
    public String getPoliticalOrgName() {
        return politicalOrgName;
    }

    /**
     * 収支報告書政治団体名称を設定する
     *
     * @param politicalOrgName 収支報告書政治団体名称
     */
    public void setPoliticalOrgName(final String politicalOrgName) {
        this.politicalOrgName = politicalOrgName;
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

    /** 項目名 */
    @Column(name = "item_name")
    private String itemName = INIT_String;

    /**
     * 項目名を取得する
     *
     * @return 項目名
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 項目名を設定する
     *
     * @param itemName 項目名
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
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

    /** 抽出階層 */
    @Column(name = "pickup_stage")
    private Integer pickupStage = INIT_Integer;

    /**
     * 抽出階層を取得する
     *
     * @return 抽出階層
     */
    public Integer getPickupStage() {
        return pickupStage;
    }

    /**
     * 抽出階層を設定する
     *
     * @param pickupStage 抽出階層
     */
    public void setPickupStage(final Integer pickupStage) {
        this.pickupStage = pickupStage;
    }

    /** 行連番 */
    @Column(name = "renban")
    private Integer renban = INIT_Integer;

    /**
     * 行連番を取得する
     *
     * @return 行連番
     */
    public Integer getRenban() {
        return renban;
    }

    /**
     * 行連番を設定する
     *
     * @param renban 行連番
     */
    public void setRenban(final Integer renban) {
        this.renban = renban;
    }

    /** 取引相手Id */
    @Column(name = "trading_partner_id")
    private Long tradingPartnerId = INIT_Long;

    /**
     * 取引相手Idを取得する
     *
     * @return 取引相手Id
     */
    public Long getTradingPartnerId() {
        return tradingPartnerId;
    }

    /**
     * 取引相手Idを設定する
     *
     * @param tradingPartnerId 取引相手Id
     */
    public void setTradingPartnerId(final Long tradingPartnerId) {
        this.tradingPartnerId = tradingPartnerId;
    }

    /** 取引相手同一識別コード */
    @Column(name = "trading_partner_code")
    private Integer tradingPartnerCode = INIT_Integer;

    /**
     * 取引相手同一識別コードを取得する
     *
     * @return 取引相手同一識別コード
     */
    public Integer getTradingPartnerCode() {
        return tradingPartnerCode;
    }

    /**
     * 取引相手同一識別コードを設定する
     *
     * @param tradingPartnerCode 取引相手同一識別コード
     */
    public void setTradingPartnerCode(final Integer tradingPartnerCode) {
        this.tradingPartnerCode = tradingPartnerCode;
    }

    /** 取引相手名称 */
    @Column(name = "trading_partner_name")
    private String tradingPartnerName = INIT_String;

    /**
     * 取引相手名称を取得する
     *
     * @return 取引相手名称
     */
    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * 取引相手名称を設定する
     *
     * @param tradingPartnerName 取引相手名称
     */
    public void setTradingPartnerName(final String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    /** 取引相手住所 */
    @Column(name = "trading_partner_address")
    private String tradingPartnerAddress = INIT_String;

    /**
     * 取引相手住所を取得する
     *
     * @return 取引相手住所
     */
    public String getTradingPartnerAddress() {
        return tradingPartnerAddress;
    }

    /**
     * 取引相手住所を設定する
     *
     * @param tradingPartnerAddress 取引相手住所
     */
    public void setTradingPartnerAddress(final String tradingPartnerAddress) {
        this.tradingPartnerAddress = tradingPartnerAddress;
    }

    /** 取引相手団体代表者Id */
    @Column(name = "trading_partner_delegate_id")
    private Long tradingPartnerDelegateId = INIT_Long;

    /**
     * 取引相手団体代表者Idを取得する
     *
     * @return 取引相手団体代表者Id
     */
    public Long getTradingPartnerDelegateId() {
        return tradingPartnerDelegateId;
    }

    /**
     * 取引相手団体代表者Idを設定する
     *
     * @param tradingPartnerDelegateId 取引相手団体代表者Id
     */
    public void setTradingPartnerDelegateId(final Long tradingPartnerDelegateId) {
        this.tradingPartnerDelegateId = tradingPartnerDelegateId;
    }

    /** 取引相手団体代表者同一識別コード */
    @Column(name = "trading_partner_delegate_code")
    private Integer tradingPartnerDelegateCode = INIT_Integer;

    /**
     * 取引相手団体代表者同一識別コードを取得する
     *
     * @return 取引相手団体代表者同一識別コード
     */
    public Integer getTradingPartnerDelegateCode() {
        return tradingPartnerDelegateCode;
    }

    /**
     * 取引相手団体代表者同一識別コードを設定する
     *
     * @param tradingPartnerDelegateCode 取引相手団体代表者同一識別コード
     */
    public void setTradingPartnerDelegateCode(final Integer tradingPartnerDelegateCode) {
        this.tradingPartnerDelegateCode = tradingPartnerDelegateCode;
    }

    /** 取引相手団体代表者 */
    @Column(name = "trading_partner_delegate_name")
    private String tradingPartnerDelegateName = INIT_String;

    /**
     * 取引相手団体代表者を取得する
     *
     * @return 取引相手団体代表者
     */
    public String getTradingPartnerDelegateName() {
        return tradingPartnerDelegateName;
    }

    /**
     * 取引相手団体代表者を設定する
     *
     * @param tradingPartnerDelegateName 取引相手団体代表者
     */
    public void setTradingPartnerDelegateName(final String tradingPartnerDelegateName) {
        this.tradingPartnerDelegateName = tradingPartnerDelegateName;
    }

    /** 取引相手団体会計責任者Id */
    @Column(name = "trading_org_account_manager_id")
    private Long tradingOrgAccountManagerId = INIT_Long;

    /**
     * 取引相手団体会計責任者Idを取得する
     *
     * @return 取引相手団体会計責任者Id
     */
    public Long getTradingOrgAccountManagerId() {
        return tradingOrgAccountManagerId;
    }

    /**
     * 取引相手団体会計責任者Idを設定する
     *
     * @param tradingOrgAccountManagerId 取引相手団体会計責任者Id
     */
    public void setTradingOrgAccountManagerId(final Long tradingOrgAccountManagerId) {
        this.tradingOrgAccountManagerId = tradingOrgAccountManagerId;
    }

    /** 取引相手団体会計責任者同一識別コード */
    @Column(name = "trading_org_account_manager_code")
    private Integer tradingOrgAccountManagerCode = INIT_Integer;

    /**
     * 取引相手団体会計責任者同一識別コードを取得する
     *
     * @return 取引相手団体会計責任者同一識別コード
     */
    public Integer getTradingOrgAccountManagerCode() {
        return tradingOrgAccountManagerCode;
    }

    /**
     * 取引相手団体会計責任者同一識別コードを設定する
     *
     * @param tradingOrgAccountManagerCode 取引相手団体会計責任者同一識別コード
     */
    public void setTradingOrgAccountManagerCode(final Integer tradingOrgAccountManagerCode) {
        this.tradingOrgAccountManagerCode = tradingOrgAccountManagerCode;
    }

    /** 取引相手団体会計責任者名称 */
    @Column(name = "trading_org_account_manager_name")
    private String tradingOrgAccountManagerName = INIT_String;

    /**
     * 取引相手団体会計責任者名称を取得する
     *
     * @return 取引相手団体会計責任者名称
     */
    public String getTradingOrgAccountManagerName() {
        return tradingOrgAccountManagerName;
    }

    /**
     * 取引相手団体会計責任者名称を設定する
     *
     * @param tradingOrgAccountManagerName 取引相手団体会計責任者名称
     */
    public void setTradingOrgAccountManagerName(final String tradingOrgAccountManagerName) {
        this.tradingOrgAccountManagerName = tradingOrgAccountManagerName;
    }

    /** 取引相手資金管理団体登録者Id */
    @Column(name = "trading_org_shikin_dantai_id")
    private Long tradingOrgShikinDantaiId = INIT_Long;

    /**
     * 取引相手資金管理団体登録者Idを取得する
     *
     * @return 取引相手資金管理団体登録者Id
     */
    public Long getTradingOrgShikinDantaiId() {
        return tradingOrgShikinDantaiId;
    }

    /**
     * 取引相手資金管理団体登録者Idを設定する
     *
     * @param tradingOrgShikinDantaiId 取引相手資金管理団体登録者Id
     */
    public void setTradingOrgShikinDantaiId(final Long tradingOrgShikinDantaiId) {
        this.tradingOrgShikinDantaiId = tradingOrgShikinDantaiId;
    }

    /** 取引相手資金管理団体登録者同一識別コード */
    @Column(name = "trading_org_shikin_dantai_code")
    private Integer tradingOrgShikinDantaiCode = INIT_Integer;

    /**
     * 取引相手資金管理団体登録者同一識別コードを取得する
     *
     * @return 取引相手資金管理団体登録者同一識別コード
     */
    public Integer getTradingOrgShikinDantaiCode() {
        return tradingOrgShikinDantaiCode;
    }

    /**
     * 取引相手資金管理団体登録者同一識別コードを設定する
     *
     * @param tradingOrgShikinDantaiCode 取引相手資金管理団体登録者同一識別コード
     */
    public void setTradingOrgShikinDantaiCode(final Integer tradingOrgShikinDantaiCode) {
        this.tradingOrgShikinDantaiCode = tradingOrgShikinDantaiCode;
    }

    /** 取引相手書資金管理団体登録者名称 */
    @Column(name = "trading_org_shikin_dantai_name")
    private String tradingOrgShikinDantaiName = INIT_String;

    /**
     * 取引相手書資金管理団体登録者名称を取得する
     *
     * @return 取引相手書資金管理団体登録者名称
     */
    public String getTradingOrgShikinDantaiName() {
        return tradingOrgShikinDantaiName;
    }

    /**
     * 取引相手書資金管理団体登録者名称を設定する
     *
     * @param tradingOrgShikinDantaiName 取引相手書資金管理団体登録者名称
     */
    public void setTradingOrgShikinDantaiName(final String tradingOrgShikinDantaiName) {
        this.tradingOrgShikinDantaiName = tradingOrgShikinDantaiName;
    }

    /** 取引相手国会議員1関連者Id */
    @Column(name = "trading_org_kokkai_giin1_id")
    private Long tradingOrgKokkaiGiin1Id = INIT_Long;

    /**
     * 取引相手国会議員1関連者Idを取得する
     *
     * @return 取引相手国会議員1関連者Id
     */
    public Long getTradingOrgKokkaiGiin1Id() {
        return tradingOrgKokkaiGiin1Id;
    }

    /**
     * 取引相手国会議員1関連者Idを設定する
     *
     * @param tradingOrgKokkaiGiin1Id 取引相手国会議員1関連者Id
     */
    public void setTradingOrgKokkaiGiin1Id(final Long tradingOrgKokkaiGiin1Id) {
        this.tradingOrgKokkaiGiin1Id = tradingOrgKokkaiGiin1Id;
    }

    /** 取引相手国会議員1関連者同一識別コード */
    @Column(name = "trading_org_kokkai_giin1_code")
    private Integer tradingOrgKokkaiGiin1Code = INIT_Integer;

    /**
     * 取引相手国会議員1関連者同一識別コードを取得する
     *
     * @return 取引相手国会議員1関連者同一識別コード
     */
    public Integer getTradingOrgKokkaiGiin1Code() {
        return tradingOrgKokkaiGiin1Code;
    }

    /**
     * 取引相手国会議員1関連者同一識別コードを設定する
     *
     * @param tradingOrgKokkaiGiin1Code 取引相手国会議員1関連者同一識別コード
     */
    public void setTradingOrgKokkaiGiin1Code(final Integer tradingOrgKokkaiGiin1Code) {
        this.tradingOrgKokkaiGiin1Code = tradingOrgKokkaiGiin1Code;
    }

    /** 取引相手国会議員1関連者名称 */
    @Column(name = "trading_org_kokkai_giin1_name")
    private String tradingOrgKokkaiGiin1Name = INIT_String;

    /**
     * 取引相手国会議員1関連者名称を取得する
     *
     * @return 取引相手国会議員1関連者名称
     */
    public String getTradingOrgKokkaiGiin1Name() {
        return tradingOrgKokkaiGiin1Name;
    }

    /**
     * 取引相手国会議員1関連者名称を設定する
     *
     * @param tradingOrgKokkaiGiin1Name 取引相手国会議員1関連者名称
     */
    public void setTradingOrgKokkaiGiin1Name(final String tradingOrgKokkaiGiin1Name) {
        this.tradingOrgKokkaiGiin1Name = tradingOrgKokkaiGiin1Name;
    }

    /** 取引相手国会議員2関連者Id */
    @Column(name = "trading_org_kokkai_giin2_id")
    private Long tradingOrgKokkaiGiin2Id = INIT_Long;

    /**
     * 取引相手国会議員2関連者Idを取得する
     *
     * @return 取引相手国会議員2関連者Id
     */
    public Long getTradingOrgKokkaiGiin2Id() {
        return tradingOrgKokkaiGiin2Id;
    }

    /**
     * 取引相手国会議員2関連者Idを設定する
     *
     * @param tradingOrgKokkaiGiin2Id 取引相手国会議員2関連者Id
     */
    public void setTradingOrgKokkaiGiin2Id(final Long tradingOrgKokkaiGiin2Id) {
        this.tradingOrgKokkaiGiin2Id = tradingOrgKokkaiGiin2Id;
    }

    /** 取引相手国会議員2関連者同一識別コード */
    @Column(name = "trading_org_kokkai_giin2_code")
    private Integer tradingOrgKokkaiGiin2Code = INIT_Integer;

    /**
     * 取引相手国会議員2関連者同一識別コードを取得する
     *
     * @return 取引相手国会議員2関連者同一識別コード
     */
    public Integer getTradingOrgKokkaiGiin2Code() {
        return tradingOrgKokkaiGiin2Code;
    }

    /**
     * 取引相手国会議員2関連者同一識別コードを設定する
     *
     * @param tradingOrgKokkaiGiin2Code 取引相手国会議員2関連者同一識別コード
     */
    public void setTradingOrgKokkaiGiin2Code(final Integer tradingOrgKokkaiGiin2Code) {
        this.tradingOrgKokkaiGiin2Code = tradingOrgKokkaiGiin2Code;
    }

    /** 取引相手国会議員2関連者名称 */
    @Column(name = "trading_org_kokkai_giin2_name")
    private String tradingOrgKokkaiGiin2Name = INIT_String;

    /**
     * 取引相手国会議員2関連者名称を取得する
     *
     * @return 取引相手国会議員2関連者名称
     */
    public String getTradingOrgKokkaiGiin2Name() {
        return tradingOrgKokkaiGiin2Name;
    }

    /**
     * 取引相手国会議員2関連者名称を設定する
     *
     * @param tradingOrgKokkaiGiin2Name 取引相手国会議員2関連者名称
     */
    public void setTradingOrgKokkaiGiin2Name(final String tradingOrgKokkaiGiin2Name) {
        this.tradingOrgKokkaiGiin2Name = tradingOrgKokkaiGiin2Name;
    }

    /** 取引相手国会議員3関連者Id */
    @Column(name = "trading_org_kokkai_giin3_id")
    private Long tradingOrgKokkaiGiin3Id = INIT_Long;

    /**
     * 取引相手国会議員3関連者Idを取得する
     *
     * @return 取引相手国会議員3関連者Id
     */
    public Long getTradingOrgKokkaiGiin3Id() {
        return tradingOrgKokkaiGiin3Id;
    }

    /**
     * 取引相手国会議員3関連者Idを設定する
     *
     * @param tradingOrgKokkaiGiin3Id 取引相手国会議員3関連者Id
     */
    public void setTradingOrgKokkaiGiin3Id(final Long tradingOrgKokkaiGiin3Id) {
        this.tradingOrgKokkaiGiin3Id = tradingOrgKokkaiGiin3Id;
    }

    /** 取引相手国会議員3関連者同一識別コード */
    @Column(name = "trading_org_kokkai_giin3_code")
    private Integer tradingOrgKokkaiGiin3Code = INIT_Integer;

    /**
     * 取引相手国会議員3関連者同一識別コードを取得する
     *
     * @return 取引相手国会議員3関連者同一識別コード
     */
    public Integer getTradingOrgKokkaiGiin3Code() {
        return tradingOrgKokkaiGiin3Code;
    }

    /**
     * 取引相手国会議員3関連者同一識別コードを設定する
     *
     * @param tradingOrgKokkaiGiin3Code 取引相手国会議員3関連者同一識別コード
     */
    public void setTradingOrgKokkaiGiin3Code(final Integer tradingOrgKokkaiGiin3Code) {
        this.tradingOrgKokkaiGiin3Code = tradingOrgKokkaiGiin3Code;
    }

    /** 取引相手国会議員3関連者名称 */
    @Column(name = "trading_org_kokkai_giin3_name")
    private String tradingOrgKokkaiGiin3Name = INIT_String;

    /**
     * 取引相手国会議員3関連者名称を取得する
     *
     * @return 取引相手国会議員3関連者名称
     */
    public String getTradingOrgKokkaiGiin3Name() {
        return tradingOrgKokkaiGiin3Name;
    }

    /**
     * 取引相手国会議員3関連者名称を設定する
     *
     * @param tradingOrgKokkaiGiin3Name 取引相手国会議員3関連者名称
     */
    public void setTradingOrgKokkaiGiin3Name(final String tradingOrgKokkaiGiin3Name) {
        this.tradingOrgKokkaiGiin3Name = tradingOrgKokkaiGiin3Name;
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
