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
 * wk_tbl_ukai_kenkin_pickup_route接続用Entity
 */
@Entity
@Table(name = "wk_tbl_ukai_kenkin_pickup_route")
public class WkTblUkaiKenkinPickupRouteEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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

    /** 迂回献金キャッチャールート抽出ワークテーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_ukai_kenkin_pickup_route_id")
    private Integer wkTblUkaiKenkinPickupRouteId = INIT_Integer;

    /**
     * 迂回献金キャッチャールート抽出ワークテーブルIdを取得する
     *
     * @return 迂回献金キャッチャールート抽出ワークテーブルId
     */
    public Integer getWkTblUkaiKenkinPickupRouteId() {
        return wkTblUkaiKenkinPickupRouteId;
    }

    /**
     * 迂回献金キャッチャールート抽出ワークテーブルIdを設定する
     *
     * @param wkTblUkaiKenkinPickupRouteId 迂回献金キャッチャールート抽出ワークテーブルId
     */
    public void setWkTblUkaiKenkinPickupRouteId(final Integer wkTblUkaiKenkinPickupRouteId) {
        this.wkTblUkaiKenkinPickupRouteId = wkTblUkaiKenkinPickupRouteId;
    }

    /** 迂回献金キャッチャールート抽出ワークテーブル予定同一識別コード */
    @Column(name = "wk_tbl_ukai_kenkin_pickup_route_code")
    private Integer wkTblUkaiKenkinPickupRouteCode = INIT_Integer;

    /**
     * 迂回献金キャッチャールート抽出ワークテーブル予定同一識別コードを取得する
     *
     * @return 迂回献金キャッチャールート抽出ワークテーブル予定同一識別コード
     */
    public Integer getWkTblUkaiKenkinPickupRouteCode() {
        return wkTblUkaiKenkinPickupRouteCode;
    }

    /**
     * 迂回献金キャッチャールート抽出ワークテーブル予定同一識別コードを設定する
     *
     * @param wkTblUkaiKenkinPickupRouteCode 迂回献金キャッチャールート抽出ワークテーブル予定同一識別コード
     */
    public void setWkTblUkaiKenkinPickupRouteCode(final Integer wkTblUkaiKenkinPickupRouteCode) {
        this.wkTblUkaiKenkinPickupRouteCode = wkTblUkaiKenkinPickupRouteCode;
    }

    /** 複写元テーブルId */
    @Column(name = "table_id")
    private Long tablleId = INIT_Long;

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

    /** itemName */
    @Column(name = "kingaku")
    private Long kingaku = INIT_Long;

    /**
     * itemNameを取得する
     *
     * @return itemName
     */
    public Long getKingaku() {
        return kingaku;
    }

    /**
     * itemNameを設定する
     *
     * @param kingaku itemName
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

    /** (収支報告書行)連番 */
    @Column(name = "renban")
    private Integer renban = INIT_Integer;

    /**
     * (収支報告書行)連番を取得する
     *
     * @return (収支報告書行)連番
     */
    public Integer getRenban() {
        return renban;
    }

    /**
     * (収支報告書行)連番を設定する
     *
     * @param renban (収支報告書行)連番
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

    /** 収支報告書記載団体関連者Id */
    @Column(name = "poli_org_relation_person_id")
    private Long poliOrgRelationPersonId = INIT_Long;

    /**
     * 収支報告書記載団体関連者Idを取得する
     *
     * @return 収支報告書記載団体関連者Id
     */
    public Long getPoliOrgRelationPersonId() {
        return poliOrgRelationPersonId;
    }

    /**
     * 収支報告書記載団体関連者Idを設定する
     *
     * @param poliOrgRelationPersonId 収支報告書記載団体関連者Id
     */
    public void setPoliOrgRelationPersonId(final Long poliOrgRelationPersonId) {
        this.poliOrgRelationPersonId = poliOrgRelationPersonId;
    }

    /** 収支報告書記載団体関連者同一識別コード */
    @Column(name = "poli_org_relation_person_code")
    private Integer poliOrgRelationPersonCode = INIT_Integer;

    /**
     * 収支報告書記載団体関連者同一識別コードを取得する
     *
     * @return 収支報告書記載団体関連者同一識別コード
     */
    public Integer getPoliOrgRelationPersonCode() {
        return poliOrgRelationPersonCode;
    }

    /**
     * 収支報告書記載団体関連者同一識別コードを設定する
     *
     * @param poliOrgRelationPersonCode 収支報告書記載団体関連者同一識別コード
     */
    public void setPoliOrgRelationPersonCode(final Integer poliOrgRelationPersonCode) {
        this.poliOrgRelationPersonCode = poliOrgRelationPersonCode;
    }

    /** 収支報告書記載団体関連者名称 */
    @Column(name = "poli_org_relation_person_name")
    private String poliOrgRelationPersonName = INIT_String;

    /**
     * 収支報告書記載団体関連者名称を取得する
     *
     * @return 収支報告書記載団体関連者名称
     */
    public String getPoliOrgRelationPersonName() {
        return poliOrgRelationPersonName;
    }

    /**
     * 収支報告書記載団体関連者名称を設定する
     *
     * @param poliOrgRelationPersonName 収支報告書記載団体関連者名称
     */
    public void setPoliOrgRelationPersonName(final String poliOrgRelationPersonName) {
        this.poliOrgRelationPersonName = poliOrgRelationPersonName;
    }

    /** 取引相手関連者Id */
    @Column(name = "trading_relation_person_id")
    private Long tradingRelationPersonId = INIT_Long;

    /**
     * 取引相手関連者Idを取得する
     *
     * @return 取引相手関連者Id
     */
    public Long getTradingRelationPersonId() {
        return tradingRelationPersonId;
    }

    /**
     * 取引相手関連者Idを設定する
     *
     * @param tradingRelationPersonId 取引相手関連者Id
     */
    public void setTradingRelationPersonId(final Long tradingRelationPersonId) {
        this.tradingRelationPersonId = tradingRelationPersonId;
    }

    /** 取引相手関連者同一識別コード */
    @Column(name = "trading_relation_person_code")
    private Integer tradingRelationPersonCode = INIT_Integer;

    /**
     * 取引相手関連者同一識別コードを取得する
     *
     * @return 取引相手関連者同一識別コード
     */
    public Integer getTradingRelationPersonCode() {
        return tradingRelationPersonCode;
    }

    /**
     * 取引相手関連者同一識別コードを設定する
     *
     * @param tradingRelationPersonCode 取引相手関連者同一識別コード
     */
    public void setTradingRelationPersonCode(final Integer tradingRelationPersonCode) {
        this.tradingRelationPersonCode = tradingRelationPersonCode;
    }

    /** 取引相手関連者名称 */
    @Column(name = "trading_relation_person_name")
    private String tradingRelationPersonName = INIT_String;

    /**
     * 取引相手関連者名称を取得する
     *
     * @return 取引相手関連者名称
     */
    public String getTradingRelationPersonName() {
        return tradingRelationPersonName;
    }

    /**
     * 取引相手関連者名称を設定する
     *
     * @param tradingRelationPersonName 取引相手関連者名称
     */
    public void setTradingRelationPersonName(final String tradingRelationPersonName) {
        this.tradingRelationPersonName = tradingRelationPersonName;
    }

    /** 取引相手関連者名称 */
    @Column(name = "trading_relation_person_address")
    private String tradingRelationPersonAddress = INIT_String;

    /**
     * 取引相手関連者名称を取得する
     *
     * @return 取引相手関連者名称
     */
    public String getTradingRelationPersonAddress() {
        return tradingRelationPersonAddress;
    }

    /**
     * 取引相手関連者名称を設定する
     *
     * @param tradingRelationPersonAddress 取引相手関連者名称
     */
    public void setTradingRelationPersonAddress(final String tradingRelationPersonAddress) {
        this.tradingRelationPersonAddress = tradingRelationPersonAddress;
    }

    /** 収支報告書記載団体関連者役割 */
    @Column(name = "poli_org_relation_person_yakuari")
    private String poliOrgRelationPersonYakuari = INIT_String;

    /**
     * 収支報告書記載団体関連者役割を取得する
     *
     * @return 収支報告書記載団体関連者役割
     */
    public String getPoliOrgRelationPersonYakuari() {
        return poliOrgRelationPersonYakuari;
    }

    /**
     * 収支報告書記載団体関連者役割を設定する
     *
     * @param poliOrgRelationPersonYakuari 収支報告書記載団体関連者役割
     */
    public void setPoliOrgRelationPersonYakuari(final String poliOrgRelationPersonYakuari) {
        this.poliOrgRelationPersonYakuari = poliOrgRelationPersonYakuari;
    }

    /** 取引相手関連者役割 */
    @Column(name = "trading_relation_person_yakuari")
    private String tradingRelationPersonYakuari = INIT_String;

    /**
     * 取引相手関連者役割を取得する
     *
     * @return 取引相手関連者役割
     */
    public String getTradingRelationPersonYakuari() {
        return tradingRelationPersonYakuari;
    }

    /**
     * 取引相手関連者役割を設定する
     *
     * @param tradingRelationPersonYakuari 取引相手関連者役割
     */
    public void setTradingRelationPersonYakuari(final String tradingRelationPersonYakuari) {
        this.tradingRelationPersonYakuari = tradingRelationPersonYakuari;
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
