package mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022;

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
 * offering_party_usage_0804_report_2022接続用Entity
 */
@Entity
@Table(name = "offering_party_usage_0804_report_2022")
public class OfferingPartyUsage0804Report2022Entity implements Serializable, AllTabeDataHistoryInterface { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1980, 1, 1);

    /** 初期データ(Timestamp) */
    private static final Timestamp INIT_Timestamp = Timestamp.valueOf(INIT_LocalDate.atTime(0, 0, 0));

    /** 使途報告書様式8その4Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_usage_0804_report_id")
    private Long partyUsage0804ReportId = INIT_Long;

    /**
     * 使途報告書様式8その4Idを取得する
     *
     * @return 使途報告書様式8その4Id
     */
    public Long getPartyUsage0804ReportId() {
        return partyUsage0804ReportId;
    }

    /**
     * 使途報告書様式8その4Idを設定する
     *
     * @param partyUsage0804ReportId 使途報告書様式8その4Id
     */
    public void setPartyUsage0804ReportId(final Long partyUsage0804ReportId) {
        this.partyUsage0804ReportId = partyUsage0804ReportId;
    }

    /** 使途報告書様式8その4同一識別コード */
    @Column(name = "party_usage_0804_report_code")
    private Long partyUsage0804ReportCode = INIT_Long;

    /**
     * 使途報告書様式8その4同一識別コードを取得する
     *
     * @return 使途報告書様式8その4同一識別コード
     */
    public Long getPartyUsage0804ReportCode() {
        return partyUsage0804ReportCode;
    }

    /**
     * 使途報告書様式8その4同一識別コードを設定する
     *
     * @param partyUsage0804ReportCode 使途報告書様式8その4同一識別コード
     */
    public void setPartyUsage0804ReportCode(final Long partyUsage0804ReportCode) {
        this.partyUsage0804ReportCode = partyUsage0804ReportCode;
    }

    /** 最新区分 */
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

    /** 報告年度 */
    @Column(name = "nendo")
    private Integer nendo = INIT_Integer;

    /**
     * 報告年度を取得する
     *
     * @return 報告年度
     */
    public Integer getNendo() {
        return nendo;
    }

    /**
     * 報告年度を設定する
     *
     * @param nendo 報告年度
     */
    public void setNendo(final Integer nendo) {
        this.nendo = nendo;
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

    /** 区分 */
    @Column(name = "shishutsu_kbn")
    private Integer shishutsuKbn = INIT_Integer;

    /**
     * 区分を取得する
     *
     * @return 区分
     */
    public Integer getShishutsuKbn() {
        return shishutsuKbn;
    }

    /**
     * 区分を設定する
     *
     * @param shishutsuKbn 区分
     */
    public void setShishutsuKbn(final Integer shishutsuKbn) {
        this.shishutsuKbn = shishutsuKbn;
    }

    /** 区分名称 */
    @Column(name = "shishutsu_kbn_name")
    private String shishutsuKbnName = INIT_String;

    /**
     * 区分名称を取得する
     *
     * @return 区分名称
     */
    public String getShishutsuKbnName() {
        return shishutsuKbnName;
    }

    /**
     * 区分名称を設定する
     *
     * @param shishutsuKbnName 区分名称
     */
    public void setShishutsuKbnName(final String shishutsuKbnName) {
        this.shishutsuKbnName = shishutsuKbnName;
    }

    /** シート費目 */
    @Column(name = "sheet_himoku")
    private String sheetHimoku = INIT_String;

    /**
     * シート費目を取得する
     *
     * @return シート費目
     */
    public String getSheetHimoku() {
        return sheetHimoku;
    }

    /**
     * シート費目を設定する
     *
     * @param sheetHimoku シート費目
     */
    public void setSheetHimoku(final String sheetHimoku) {
        this.sheetHimoku = sheetHimoku;
    }

    /** シート全合計 */
    @Column(name = "sheet_amount_all")
    private Long sheetAmountAll = INIT_Long;

    /**
     * シート全合計を取得する
     *
     * @return シート全合計
     */
    public Long getSheetAmountAll() {
        return sheetAmountAll;
    }

    /**
     * シート全合計を設定する
     *
     * @param sheetAmountAll シート全合計
     */
    public void setSheetAmountAll(final Long sheetAmountAll) {
        this.sheetAmountAll = sheetAmountAll;
    }

    /** シート全合計交付金充当 */
    @Column(name = "sheet_amount_all_koufukin")
    private Long sheetAmountAllKoufukin = INIT_Long;

    /**
     * シート全合計交付金充当を取得する
     *
     * @return シート全合計交付金充当
     */
    public Long getSheetAmountAllKoufukin() {
        return sheetAmountAllKoufukin;
    }

    /**
     * シート全合計交付金充当を設定する
     *
     * @param sheetAmountAllKoufukin シート全合計交付金充当
     */
    public void setSheetAmountAllKoufukin(final Long sheetAmountAllKoufukin) {
        this.sheetAmountAllKoufukin = sheetAmountAllKoufukin;
    }

    /** シート全合計自党基金充当 */
    @Column(name = "sheet_amount_all_my_funds")
    private Long sheetAmountAllMyFunds = INIT_Long;

    /**
     * シート全合計自党基金充当を取得する
     *
     * @return シート全合計自党基金充当
     */
    public Long getSheetAmountAllMyFunds() {
        return sheetAmountAllMyFunds;
    }

    /**
     * シート全合計自党基金充当を設定する
     *
     * @param sheetAmountAllMyFunds シート全合計自党基金充当
     */
    public void setSheetAmountAllMyFunds(final Long sheetAmountAllMyFunds) {
        this.sheetAmountAllMyFunds = sheetAmountAllMyFunds;
    }

    /** シート全合計 */
    @Column(name = "sheet_amount_sonota")
    private Long sheetAmountSonota = INIT_Long;

    /**
     * シート全合計を取得する
     *
     * @return シート全合計
     */
    public Long getSheetAmountSonota() {
        return sheetAmountSonota;
    }

    /**
     * シート全合計を設定する
     *
     * @param sheetAmountSonota シート全合計
     */
    public void setSheetAmountSonota(final Long sheetAmountSonota) {
        this.sheetAmountSonota = sheetAmountSonota;
    }

    /** シート全合計交付金充当 */
    @Column(name = "sheet_amount_sonota_koufukin")
    private String sheetAmountSonotaKoufukin = INIT_String;

    /**
     * シート全合計交付金充当を取得する
     *
     * @return シート全合計交付金充当
     */
    public String getSheetAmountSonotaKoufukin() {
        return sheetAmountSonotaKoufukin;
    }

    /**
     * シート全合計交付金充当を設定する
     *
     * @param sheetAmountSonotaKoufukin シート全合計交付金充当
     */
    public void setSheetAmountSonotaKoufukin(final String sheetAmountSonotaKoufukin) {
        this.sheetAmountSonotaKoufukin = sheetAmountSonotaKoufukin;
    }

    /** シート全合計自党基金充当 */
    @Column(name = "sheet_amount_sonota_my_funds")
    private String sheetAmountSonotaMyFunds = INIT_String;

    /**
     * シート全合計自党基金充当を取得する
     *
     * @return シート全合計自党基金充当
     */
    public String getSheetAmountSonotaMyFunds() {
        return sheetAmountSonotaMyFunds;
    }

    /**
     * シート全合計自党基金充当を設定する
     *
     * @param sheetAmountSonotaMyFunds シート全合計自党基金充当
     */
    public void setSheetAmountSonotaMyFunds(final String sheetAmountSonotaMyFunds) {
        this.sheetAmountSonotaMyFunds = sheetAmountSonotaMyFunds;
    }

    /** 行番号 */
    @Column(name = "row_no")
    private Integer rowNo = INIT_Integer;

    /**
     * 行番号を取得する
     *
     * @return 行番号
     */
    public Integer getRowNo() {
        return rowNo;
    }

    /**
     * 行番号を設定する
     *
     * @param rowNo 行番号
     */
    public void setRowNo(final Integer rowNo) {
        this.rowNo = rowNo;
    }

    /** 使用項目 */
    @Column(name = "usage_item")
    private String usageItem = INIT_String;

    /**
     * 使用項目を取得する
     *
     * @return 使用項目
     */
    public String getUsageItem() {
        return usageItem;
    }

    /**
     * 使用項目を設定する
     *
     * @param usageItem 使用項目
     */
    public void setUsageItem(final String usageItem) {
        this.usageItem = usageItem;
    }

    /** 支出金額 */
    @Column(name = "amount_all")
    private Long amountAll = INIT_Long;

    /**
     * 支出金額を取得する
     *
     * @return 支出金額
     */
    public Long getAmountAll() {
        return amountAll;
    }

    /**
     * 支出金額を設定する
     *
     * @param amountAll 支出金額
     */
    public void setAmountAll(final Long amountAll) {
        this.amountAll = amountAll;
    }

    /** 交付金充当支出金額 */
    @Column(name = "amount_koufukin")
    private Long amountKoufukin = INIT_Long;

    /**
     * 交付金充当支出金額を取得する
     *
     * @return 交付金充当支出金額
     */
    public Long getAmountKoufukin() {
        return amountKoufukin;
    }

    /**
     * 交付金充当支出金額を設定する
     *
     * @param amountKoufukin 交付金充当支出金額
     */
    public void setAmountKoufukin(final Long amountKoufukin) {
        this.amountKoufukin = amountKoufukin;
    }

    /** 交付金充当支出金額 */
    @Column(name = "amount_my_funds")
    private Long amountMyFunds = INIT_Long;

    /**
     * 交付金充当支出金額を取得する
     *
     * @return 交付金充当支出金額
     */
    public Long getAmountMyFunds() {
        return amountMyFunds;
    }

    /**
     * 交付金充当支出金額を設定する
     *
     * @param amountMyFunds 交付金充当支出金額
     */
    public void setAmountMyFunds(final Long amountMyFunds) {
        this.amountMyFunds = amountMyFunds;
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

    /** 支払者 */
    @Column(name = "payee_name")
    private String payeeName = INIT_String;

    /**
     * 支払者を取得する
     *
     * @return 支払者
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 支払者を設定する
     *
     * @param payeeName 支払者
     */
    public void setPayeeName(final String payeeName) {
        this.payeeName = payeeName;
    }

    /** 支払者住所 */
    @Column(name = "address")
    private String address = INIT_String;

    /**
     * 支払者住所を取得する
     *
     * @return 支払者住所
     */
    public String getAddress() {
        return address;
    }

    /**
     * 支払者住所を設定する
     *
     * @param address 支払者住所
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /** 備考 */
    @Column(name = "bikou")
    private String bikou = INIT_String;

    /**
     * 備考を取得する
     *
     * @return 備考
     */
    public String getBikou() {
        return bikou;
    }

    /**
     * 備考を設定する
     *
     * @param bikou 備考
     */
    public void setBikou(final String bikou) {
        this.bikou = bikou;
    }

    /** 領収所徴収フラグ */
    @Column(name = "flg_collect_recipt")
    private Integer flgCollectRecipt = INIT_Integer;

    /**
     * 領収所徴収フラグを取得する
     *
     * @return 領収所徴収フラグ
     */
    public Integer getFlgCollectRecipt() {
        return flgCollectRecipt;
    }

    /**
     * 領収所徴収フラグを設定する
     *
     * @param flgCollectRecipt 領収所徴収フラグ
     */
    public void setFlgCollectRecipt(final Integer flgCollectRecipt) {
        this.flgCollectRecipt = flgCollectRecipt;
    }

    /** 支払者関連者区分 */
    @Column(name = "relation_kbn_payee")
    private Integer relationKbnPayee = INIT_Integer;

    /**
     * 支払者関連者区分を取得する
     *
     * @return 支払者関連者区分
     */
    public Integer getRelationKbnPayee() {
        return relationKbnPayee;
    }

    /**
     * 支払者関連者区分を設定する
     *
     * @param relationKbnPayee 支払者関連者区分
     */
    public void setRelationKbnPayee(final Integer relationKbnPayee) {
        this.relationKbnPayee = relationKbnPayee;
    }

    /** 支払者関連者Id(個人) */
    @Column(name = "relation_person_id_payee")
    private Long relationPersonIdPayee = INIT_Long;

    /**
     * 支払者関連者Id(個人)を取得する
     *
     * @return 支払者関連者Id(個人)
     */
    public Long getRelationPersonIdPayee() {
        return relationPersonIdPayee;
    }

    /**
     * 支払者関連者Id(個人)を設定する
     *
     * @param relationPersonIdPayee 支払者関連者Id(個人)
     */
    public void setRelationPersonIdPayee(final Long relationPersonIdPayee) {
        this.relationPersonIdPayee = relationPersonIdPayee;
    }

    /** 支払者関連者同一識別コード(個人) */
    @Column(name = "relation_person_code_payee")
    private Integer relationPersonCodePayee = INIT_Integer;

    /**
     * 支払者関連者同一識別コード(個人)を取得する
     *
     * @return 支払者関連者同一識別コード(個人)
     */
    public Integer getRelationPersonCodePayee() {
        return relationPersonCodePayee;
    }

    /**
     * 支払者関連者同一識別コード(個人)を設定する
     *
     * @param relationPersonCodePayee 支払者関連者同一識別コード(個人)
     */
    public void setRelationPersonCodePayee(final Integer relationPersonCodePayee) {
        this.relationPersonCodePayee = relationPersonCodePayee;
    }

    /** 支払者関連者名称(個人) */
    @Column(name = "relation_person_name_payee")
    private String relationPersonNamePayee = INIT_String;

    /**
     * 支払者関連者名称(個人)を取得する
     *
     * @return 支払者関連者名称(個人)
     */
    public String getRelationPersonNamePayee() {
        return relationPersonNamePayee;
    }

    /**
     * 支払者関連者名称(個人)を設定する
     *
     * @param relationPersonNamePayee 支払者関連者名称(個人)
     */
    public void setRelationPersonNamePayee(final String relationPersonNamePayee) {
        this.relationPersonNamePayee = relationPersonNamePayee;
    }

    /** 支払者関連者Id(法人) */
    @Column(name = "relation_corp_id_payee")
    private Long relationCorpIdPayee = INIT_Long;

    /**
     * 支払者関連者Id(法人)を取得する
     *
     * @return 支払者関連者Id(法人)
     */
    public Long getRelationCorpIdPayee() {
        return relationCorpIdPayee;
    }

    /**
     * 支払者関連者Id(法人)を設定する
     *
     * @param relationCorpIdPayee 支払者関連者Id(法人)
     */
    public void setRelationCorpIdPayee(final Long relationCorpIdPayee) {
        this.relationCorpIdPayee = relationCorpIdPayee;
    }

    /** 支払者関連者同一識別コード(法人) */
    @Column(name = "relation_corp_code_payee")
    private Integer relationCorpCodePayee = INIT_Integer;

    /**
     * 支払者関連者同一識別コード(法人)を取得する
     *
     * @return 支払者関連者同一識別コード(法人)
     */
    public Integer getRelationCorpCodePayee() {
        return relationCorpCodePayee;
    }

    /**
     * 支払者関連者同一識別コード(法人)を設定する
     *
     * @param relationCorpCodePayee 支払者関連者同一識別コード(法人)
     */
    public void setRelationCorpCodePayee(final Integer relationCorpCodePayee) {
        this.relationCorpCodePayee = relationCorpCodePayee;
    }

    /** 支払者関連者Id(法人) */
    @Column(name = "relation_corp_name_payee")
    private String relationCorpNamePayee = INIT_String;

    /**
     * 支払者関連者Id(法人)を取得する
     *
     * @return 支払者関連者Id(法人)
     */
    public String getRelationCorpNamePayee() {
        return relationCorpNamePayee;
    }

    /**
     * 支払者関連者Id(法人)を設定する
     *
     * @param relationCorpNamePayee 支払者関連者Id(法人)
     */
    public void setRelationCorpNamePayee(final String relationCorpNamePayee) {
        this.relationCorpNamePayee = relationCorpNamePayee;
    }

    /** 支払者関連者同一識別コード(政治団体) */
    @Column(name = "relation_political_org_id_payee")
    private Long relationPoliticalOrgIdPayee = INIT_Long;

    /**
     * 支払者関連者同一識別コード(政治団体)を取得する
     *
     * @return 支払者関連者同一識別コード(政治団体)
     */
    public Long getRelationPoliticalOrgIdPayee() {
        return relationPoliticalOrgIdPayee;
    }

    /**
     * 支払者関連者同一識別コード(政治団体)を設定する
     *
     * @param relationPoliticalOrgIdPayee 支払者関連者同一識別コード(政治団体)
     */
    public void setRelationPoliticalOrgIdPayee(final Long relationPoliticalOrgIdPayee) {
        this.relationPoliticalOrgIdPayee = relationPoliticalOrgIdPayee;
    }

    /** 支払者関連者名称(政治団体) */
    @Column(name = "relation_political_org_code_payee")
    private Integer relationPoliticalOrgCodePayee = INIT_Integer;

    /**
     * 支払者関連者名称(政治団体)を取得する
     *
     * @return 支払者関連者名称(政治団体)
     */
    public Integer getRelationPoliticalOrgCodePayee() {
        return relationPoliticalOrgCodePayee;
    }

    /**
     * 支払者関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgCodePayee 支払者関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgCodePayee(final Integer relationPoliticalOrgCodePayee) {
        this.relationPoliticalOrgCodePayee = relationPoliticalOrgCodePayee;
    }

    /** 支払者関連者名称(政治団体) */
    @Column(name = "relation_political_org_name_payee")
    private String relationPoliticalOrgNamePayee = INIT_String;

    /**
     * 支払者関連者名称(政治団体)を取得する
     *
     * @return 支払者関連者名称(政治団体)
     */
    public String getRelationPoliticalOrgNamePayee() {
        return relationPoliticalOrgNamePayee;
    }

    /**
     * 支払者関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgNamePayee 支払者関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgNamePayee(final String relationPoliticalOrgNamePayee) {
        this.relationPoliticalOrgNamePayee = relationPoliticalOrgNamePayee;
    }

    /** 自由検索用カラム */
    @Column(name = "search_words")
    private String searchWords = INIT_String;

    /**
     * 自由検索用カラムを取得する
     *
     * @return 自由検索用カラム
     */
    public String getSearchWords() {
        return searchWords;
    }

    /**
     * 自由検索用カラムを設定する
     *
     * @param searchWords 自由検索用カラム
     */
    public void setSearchWords(final String searchWords) {
        this.searchWords = searchWords;
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
