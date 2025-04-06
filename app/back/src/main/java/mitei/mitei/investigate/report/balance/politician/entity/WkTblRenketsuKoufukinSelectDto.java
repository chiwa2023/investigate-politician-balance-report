package mitei.mitei.investigate.report.balance.politician.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

/**
 * wk_tbl_renketsu_koufukin接続用Entity
 */
@Entity
public class WkTblRenketsuKoufukinSelectDto implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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


    /** 交付金連結ワークテーブル */
    @Column(name = "wk_tbl_renketsu_koufukin_id")
    private Long wkTblRenketsuKoufukinId = INIT_Long;

    /**
     * 交付金連結ワークテーブルを取得する
     *
     * @return 交付金連結ワークテーブル
     */
    public Long getWkTblRenketsuKoufukinId() {
        return wkTblRenketsuKoufukinId;
    }

    /**
     * 交付金連結ワークテーブルを設定する
     *
     * @param wkTblRenketsuKoufukinId 交付金連結ワークテーブル
     */
    public void setWkTblRenketsuKoufukinId(final Long wkTblRenketsuKoufukinId) {
        this.wkTblRenketsuKoufukinId = wkTblRenketsuKoufukinId;
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

    /** データ区分 */
    @Column(name = "data_kbn")
    private Integer dataKbn = INIT_Integer;

    /**
     * データ区分を取得する
     *
     * @return データ区分
     */
    public Integer getDataKbn() {
        return dataKbn;
    }

    /**
     * データ区分を設定する
     *
     * @param dataKbn データ区分
     */
    public void setDataKbn(final Integer dataKbn) {
        this.dataKbn = dataKbn;
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

    /** 収支報告書Id */
    @Id
    @Column(name = "balancesheet_id")
    private Long balancesheetId = INIT_Long;

    /**
     * 収支報告書Idを取得する
     *
     * @return 収支報告書Id
     */
    public Long getBalancesheetId() {
        return balancesheetId;
    }

    /**
     * 収支報告書Idを設定する
     *
     * @param balancesheetId 収支報告書Id
     */
    public void setBalancesheetId(final Long balancesheetId) {
        this.balancesheetId = balancesheetId;
    }

    /** 使途報告書Id */
    @Id
    @Column(name = "usage_report_id")
    private Long usageReportId = INIT_Long;

    /**
     * 使途報告書Idを取得する
     *
     * @return 使途報告書Id
     */
    public Long getUsageReportId() {
        return usageReportId;
    }

    /**
     * 使途報告書Idを設定する
     *
     * @param usageReportId 使途報告書Id
     */
    public void setUsageReportId(final Long usageReportId) {
        this.usageReportId = usageReportId;
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

    /** 収支報告書提出日 */
    @Column(name = "balancesheet_offering_date")
    private LocalDate balancesheetOfferingDate = INIT_LocalDate;

    /**
     * 収支報告書提出日を取得する
     *
     * @return 収支報告書提出日
     */
    public LocalDate getBalancesheetOfferingDate() {
        return balancesheetOfferingDate;
    }

    /**
     * 収支報告書提出日を設定する
     *
     * @param balancesheetOfferingDate 収支報告書提出日
     */
    public void setBalancesheetOfferingDate(final LocalDate balancesheetOfferingDate) {
        this.balancesheetOfferingDate = balancesheetOfferingDate;
    }

    /** 使途報告書提出日 */
    @Column(name = "usage_offering_date")
    private LocalDate usageOfferingDate = INIT_LocalDate;

    /**
     * 使途報告書提出日を取得する
     *
     * @return 使途報告書提出日
     */
    public LocalDate getUsageOfferingDate() {
        return usageOfferingDate;
    }

    /**
     * 使途報告書提出日を設定する
     *
     * @param usageOfferingDate 使途報告書提出日
     */
    public void setUsageOfferingDate(final LocalDate usageOfferingDate) {
        this.usageOfferingDate = usageOfferingDate;
    }

    /** 発生日 */
    @Column(name = "accrual_date_value")
    private LocalDate accrualDateValue = INIT_LocalDate;

    /**
     * 発生日を取得する
     *
     * @return 発生日
     */
    public LocalDate getAccrualDateValue() {
        return accrualDateValue;
    }

    /**
     * 発生日を設定する
     *
     * @param accrualDateValue 発生日
     */
    public void setAccrualDateValue(final LocalDate accrualDateValue) {
        this.accrualDateValue = accrualDateValue;
    }

    /** 収支報告書費目 */
    @Column(name = "balance_himoku")
    private String balanceHimoku = INIT_String;

    /**
     * 収支報告書費目を取得する
     *
     * @return 収支報告書費目
     */
    public String getBalanceHimoku() {
        return balanceHimoku;
    }

    /**
     * 収支報告書費目を設定する
     *
     * @param balanceHimoku 収支報告書費目
     */
    public void setBalanceHimoku(final String balanceHimoku) {
        this.balanceHimoku = balanceHimoku;
    }

    /** 収支報告書目的 */
    @Column(name = "balance_mokuteki")
    private String balanceMokuteki = INIT_String;

    /**
     * 収支報告書目的を取得する
     *
     * @return 収支報告書目的
     */
    public String getBalanceMokuteki() {
        return balanceMokuteki;
    }

    /**
     * 収支報告書目的を設定する
     *
     * @param balanceMokuteki 収支報告書目的
     */
    public void setBalanceMokuteki(final String balanceMokuteki) {
        this.balanceMokuteki = balanceMokuteki;
    }

    /** 交付金金額 */
    @Column(name = "amount_koufukin")
    private Long amountKoufukin = INIT_Long;

    /**
     * 交付金金額を取得する
     *
     * @return 交付金金額
     */
    public Long getAmountKoufukin() {
        return amountKoufukin;
    }

    /**
     * 交付金金額を設定する
     *
     * @param amountKoufukin 交付金金額
     */
    public void setAmountKoufukin(final Long amountKoufukin) {
        this.amountKoufukin = amountKoufukin;
    }

    /** 自団体金額 */
    @Column(name = "amount_my_funds")
    private Long amountMyFunds = INIT_Long;

    /**
     * 自団体金額を取得する
     *
     * @return 自団体金額
     */
    public Long getAmountMyFunds() {
        return amountMyFunds;
    }

    /**
     * 自団体金額を設定する
     *
     * @param amountMyFunds 自団体金額
     */
    public void setAmountMyFunds(final Long amountMyFunds) {
        this.amountMyFunds = amountMyFunds;
    }

    /** 全金額 */
    @Column(name = "amount_all")
    private Long amountAll = INIT_Long;

    /**
     * 全金額を取得する
     *
     * @return 全金額
     */
    public Long getAmountAll() {
        return amountAll;
    }

    /**
     * 全金額を設定する
     *
     * @param amountAll 全金額
     */
    public void setAmountAll(final Long amountAll) {
        this.amountAll = amountAll;
    }

    /** 使途報告書費目 */
    @Column(name = "usage_himoku")
    private String usageHimoku = INIT_String;

    /**
     * 使途報告書費目を取得する
     *
     * @return 使途報告書費目
     */
    public String getUsageHimoku() {
        return usageHimoku;
    }

    /**
     * 使途報告書費目を設定する
     *
     * @param usageHimoku 使途報告書費目
     */
    public void setUsageHimoku(final String usageHimoku) {
        this.usageHimoku = usageHimoku;
    }

    /** 使途報告書支出区分名称 */
    @Column(name = "usage_shishutsu_name")
    private String usageShishutsuName = INIT_String;

    /**
     * 使途報告書支出区分名称を取得する
     *
     * @return 使途報告書支出区分名称
     */
    public String getUsageShishutsuName() {
        return usageShishutsuName;
    }

    /**
     * 使途報告書支出区分名称を設定する
     *
     * @param usageShishutsuName 使途報告書支出区分名称
     */
    public void setUsageShishutsuName(final String usageShishutsuName) {
        this.usageShishutsuName = usageShishutsuName;
    }

    /** 使途報告書使用項目 */
    @Column(name = "usage_usage_item")
    private String usageUsageItem = INIT_String;

    /**
     * 使途報告書使用項目を取得する
     *
     * @return 使途報告書使用項目
     */
    public String getUsageUsageItem() {
        return usageUsageItem;
    }

    /**
     * 使途報告書使用項目を設定する
     *
     * @param usageUsageItem 使途報告書使用項目
     */
    public void setUsageUsageItem(final String usageUsageItem) {
        this.usageUsageItem = usageUsageItem;
    }

    /** 支払者(使途報告書) */
    @Column(name = "payee_name")
    private String payeeName = INIT_String;

    /**
     * 支払者(使途報告書)を取得する
     *
     * @return 支払者(使途報告書)
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 支払者(使途報告書)を設定する
     *
     * @param payeeName 支払者(使途報告書)
     */
    public void setPayeeName(final String payeeName) {
        this.payeeName = payeeName;
    }

    /** 支払者住所 */
    @Column(name = "payee_address")
    private String payeeAddress = INIT_String;

    /**
     * 支払者住所を取得する
     *
     * @return 支払者住所
     */
    public String getPayeeAddress() {
        return payeeAddress;
    }

    /**
     * 支払者住所を設定する
     *
     * @param payeeAddress 支払者住所
     */
    public void setPayeeAddress(final String payeeAddress) {
        this.payeeAddress = payeeAddress;
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
