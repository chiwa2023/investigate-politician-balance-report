package mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025;

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
 * offering_party_usage_0801_and_0807_report_2025接続用Entity
 */
@Entity
@Table(name = "offering_party_usage_0801_and_0807_report_2025")
public class OfferingPartyUsage0801And0807Report2025Entity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

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

    /** 使途報告書様式8その1と7Id */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_usage_0801_and_0807_report_id")
    private Long partyUsage0801And0807ReportId = INIT_Long;

    /**
     * 使途報告書様式8その1と7Idを取得する
     *
     * @return 使途報告書様式8その1と7Id
     */
    public Long getPartyUsage0801And0807ReportId() {
        return partyUsage0801And0807ReportId;
    }

    /**
     * 使途報告書様式8その1と7Idを設定する
     *
     * @param partyUsage0801And0807ReportId 使途報告書様式8その1と7Id
     */
    public void setPartyUsage0801And0807ReportId(final Long partyUsage0801And0807ReportId) {
        this.partyUsage0801And0807ReportId = partyUsage0801And0807ReportId;
    }

    /** 使途報告書様式8その1と7同一識別コード */
    @Column(name = "party_usage_0801_and_0807_report_code")
    private Long partyUsage0801And0807ReportCode = INIT_Long;

    /**
     * 使途報告書様式8その1と7同一識別コードを取得する
     *
     * @return 使途報告書様式8その1と7同一識別コード
     */
    public Long getPartyUsage0801And0807ReportCode() {
        return partyUsage0801And0807ReportCode;
    }

    /**
     * 使途報告書様式8その1と7同一識別コードを設定する
     *
     * @param partyUsage0801And0807ReportCode 使途報告書様式8その1と7同一識別コード
     */
    public void setPartyUsage0801And0807ReportCode(final Long partyUsage0801And0807ReportCode) {
        this.partyUsage0801And0807ReportCode = partyUsage0801And0807ReportCode;
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

    /** ヘッダのバージョン番号 */
    @Column(name = "version")
    private String version = INIT_String;

    /**
     * ヘッダのバージョン番号を取得する
     *
     * @return ヘッダのバージョン番号
     */
    public String getVersion() {
        return version;
    }

    /**
     * ヘッダのバージョン番号を設定する
     *
     * @param version ヘッダのバージョン番号
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /** ヘッダのアプリ名称 */
    @Column(name = "apli_name")
    private String apliName = INIT_String;

    /**
     * ヘッダのアプリ名称を取得する
     *
     * @return ヘッダのアプリ名称
     */
    public String getApliName() {
        return apliName;
    }

    /**
     * ヘッダのアプリ名称を設定する
     *
     * @param apliName ヘッダのアプリ名称
     */
    public void setApliName(final String apliName) {
        this.apliName = apliName;
    }

    /** ヘッダのアプリフラグ */
    @Column(name = "flg_apli")
    private String flgApli = INIT_String;

    /**
     * ヘッダのアプリフラグを取得する
     *
     * @return ヘッダのアプリフラグ
     */
    public String getFlgApli() {
        return flgApli;
    }

    /**
     * ヘッダのアプリフラグを設定する
     *
     * @param flgApli ヘッダのアプリフラグ
     */
    public void setFlgApli(final String flgApli) {
        this.flgApli = flgApli;
    }

    /** ヘッダの本部支部フラグ */
    @Column(name = "flg_honbu")
    private String flgHonbu = INIT_String;

    /**
     * ヘッダの本部支部フラグを取得する
     *
     * @return ヘッダの本部支部フラグ
     */
    public String getFlgHonbu() {
        return flgHonbu;
    }

    /**
     * ヘッダの本部支部フラグを設定する
     *
     * @param flgHonbu ヘッダの本部支部フラグ
     */
    public void setFlgHonbu(final String flgHonbu) {
        this.flgHonbu = flgHonbu;
    }

    /** 情報入力有無フラグテキスト */
    @Column(name = "joho_umu_text")
    private String johoUmuText = INIT_String;

    /**
     * 情報入力有無フラグテキストを取得する
     *
     * @return 情報入力有無フラグテキスト
     */
    public String getJohoUmuText() {
        return johoUmuText;
    }

    /**
     * 情報入力有無フラグテキストを設定する
     *
     * @param johoUmuText 情報入力有無フラグテキスト
     */
    public void setJohoUmuText(final String johoUmuText) {
        this.johoUmuText = johoUmuText;
    }

    /** 会計支出(表示)基準 */
    @Column(name = "kaikei_kijun_kingaku")
    private Long kaikeiKijunKingaku = INIT_Long;

    /**
     * 会計支出(表示)基準を取得する
     *
     * @return 会計支出(表示)基準
     */
    public Long getKaikeiKijunKingaku() {
        return kaikeiKijunKingaku;
    }

    /**
     * 会計支出(表示)基準を設定する
     *
     * @param kaikeiKijunKingaku 会計支出(表示)基準
     */
    public void setKaikeiKijunKingaku(final Long kaikeiKijunKingaku) {
        this.kaikeiKijunKingaku = kaikeiKijunKingaku;
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

    /** 様式8の1政党名称かな */
    @Column(name = "party_name_kana")
    private String partyNameKana = INIT_String;

    /**
     * 様式8の1政党名称かなを取得する
     *
     * @return 様式8の1政党名称かな
     */
    public String getPartyNameKana() {
        return partyNameKana;
    }

    /**
     * 様式8の1政党名称かなを設定する
     *
     * @param partyNameKana 様式8の1政党名称かな
     */
    public void setPartyNameKana(final String partyNameKana) {
        this.partyNameKana = partyNameKana;
    }

    /** 様式8の1政党名称 */
    @Column(name = "party_name")
    private String partyName = INIT_String;

    /**
     * 様式8の1政党名称を取得する
     *
     * @return 様式8の1政党名称
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * 様式8の1政党名称を設定する
     *
     * @param partyName 様式8の1政党名称
     */
    public void setPartyName(final String partyName) {
        this.partyName = partyName;
    }

    /** 様式8の1主たる事務所の住所 */
    @Column(name = "office_address")
    private String officeAddress = INIT_String;

    /**
     * 様式8の1主たる事務所の住所を取得する
     *
     * @return 様式8の1主たる事務所の住所
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * 様式8の1主たる事務所の住所を設定する
     *
     * @param officeAddress 様式8の1主たる事務所の住所
     */
    public void setOfficeAddress(final String officeAddress) {
        this.officeAddress = officeAddress;
    }

    /** 様式8の1代表者姓名 */
    @Column(name = "delegate_name")
    private String delegateName = INIT_String;

    /**
     * 様式8の1代表者姓名を取得する
     *
     * @return 様式8の1代表者姓名
     */
    public String getDelegateName() {
        return delegateName;
    }

    /**
     * 様式8の1代表者姓名を設定する
     *
     * @param delegateName 様式8の1代表者姓名
     */
    public void setDelegateName(final String delegateName) {
        this.delegateName = delegateName;
    }

    /** 様式8の1会計責任者姓名 */
    @Column(name = "account_manager_name")
    private String accountManagerName = INIT_String;

    /**
     * 様式8の1会計責任者姓名を取得する
     *
     * @return 様式8の1会計責任者姓名
     */
    public String getAccountManagerName() {
        return accountManagerName;
    }

    /**
     * 様式8の1会計責任者姓名を設定する
     *
     * @param accountManagerName 様式8の1会計責任者姓名
     */
    public void setAccountManagerName(final String accountManagerName) {
        this.accountManagerName = accountManagerName;
    }

    /** 様式8の1作業者1姓名 */
    @Column(name = "worker1_name")
    private String worker1Name = INIT_String;

    /**
     * 様式8の1作業者1姓名を取得する
     *
     * @return 様式8の1作業者1姓名
     */
    public String getWorker1Name() {
        return worker1Name;
    }

    /**
     * 様式8の1作業者1姓名を設定する
     *
     * @param worker1Name 様式8の1作業者1姓名
     */
    public void setWorker1Name(final String worker1Name) {
        this.worker1Name = worker1Name;
    }

    /** 様式8の1作業者1電話番号 */
    @Column(name = "worker1_tel")
    private String worker1Tel = INIT_String;

    /**
     * 様式8の1作業者1電話番号を取得する
     *
     * @return 様式8の1作業者1電話番号
     */
    public String getWorker1Tel() {
        return worker1Tel;
    }

    /**
     * 様式8の1作業者1電話番号を設定する
     *
     * @param worker1Tel 様式8の1作業者1電話番号
     */
    public void setWorker1Tel(final String worker1Tel) {
        this.worker1Tel = worker1Tel;
    }

    /** 様式8の1作業者2姓名 */
    @Column(name = "worker2_name")
    private String worker2Name = INIT_String;

    /**
     * 様式8の1作業者2姓名を取得する
     *
     * @return 様式8の1作業者2姓名
     */
    public String getWorker2Name() {
        return worker2Name;
    }

    /**
     * 様式8の1作業者2姓名を設定する
     *
     * @param worker2Name 様式8の1作業者2姓名
     */
    public void setWorker2Name(final String worker2Name) {
        this.worker2Name = worker2Name;
    }

    /** 様式8の1作業者2電話番号 */
    @Column(name = "worker2_tel")
    private String worker2Tel = INIT_String;

    /**
     * 様式8の1作業者2電話番号を取得する
     *
     * @return 様式8の1作業者2電話番号
     */
    public String getWorker2Tel() {
        return worker2Tel;
    }

    /**
     * 様式8の1作業者2電話番号を設定する
     *
     * @param worker2Tel 様式8の1作業者2電話番号
     */
    public void setWorker2Tel(final String worker2Tel) {
        this.worker2Tel = worker2Tel;
    }

    /** 様式8の1解散区分 */
    @Column(name = "kaisan_kbn")
    private Integer kaisanKbn = INIT_Integer;

    /**
     * 様式8の1解散区分を取得する
     *
     * @return 様式8の1解散区分
     */
    public Integer getKaisanKbn() {
        return kaisanKbn;
    }

    /**
     * 様式8の1解散区分を設定する
     *
     * @param kaisanKbn 様式8の1解散区分
     */
    public void setKaisanKbn(final Integer kaisanKbn) {
        this.kaisanKbn = kaisanKbn;
    }

    /** 様式8の1解散日 */
    @Column(name = "kaisan_date")
    private String kaisanDate = INIT_String;

    /**
     * 様式8の1解散日を取得する
     *
     * @return 様式8の1解散日
     */
    public String getKaisanDate() {
        return kaisanDate;
    }

    /**
     * 様式8の1解散日を設定する
     *
     * @param kaisanDate 様式8の1解散日
     */
    public void setKaisanDate(final String kaisanDate) {
        this.kaisanDate = kaisanDate;
    }

    /** 様式8の1支部区分 */
    @Column(name = "shibu_kbn")
    private Integer shibuKbn = INIT_Integer;

    /**
     * 様式8の1支部区分を取得する
     *
     * @return 様式8の1支部区分
     */
    public Integer getShibuKbn() {
        return shibuKbn;
    }

    /**
     * 様式8の1支部区分を設定する
     *
     * @param shibuKbn 様式8の1支部区分
     */
    public void setShibuKbn(final Integer shibuKbn) {
        this.shibuKbn = shibuKbn;
    }

    /** 様式8の1受付番号 */
    @Column(name = "uketsuke_no")
    private String uketsukeNo = INIT_String;

    /**
     * 様式8の1受付番号を取得する
     *
     * @return 様式8の1受付番号
     */
    public String getUketsukeNo() {
        return uketsukeNo;
    }

    /**
     * 様式8の1受付番号を設定する
     *
     * @param uketsukeNo 様式8の1受付番号
     */
    public void setUketsukeNo(final String uketsukeNo) {
        this.uketsukeNo = uketsukeNo;
    }

    /** 様式8の1整理番号 */
    @Column(name = "seiri_no")
    private String seiriNo = INIT_String;

    /**
     * 様式8の1整理番号を取得する
     *
     * @return 様式8の1整理番号
     */
    public String getSeiriNo() {
        return seiriNo;
    }

    /**
     * 様式8の1整理番号を設定する
     *
     * @param seiriNo 様式8の1整理番号
     */
    public void setSeiriNo(final String seiriNo) {
        this.seiriNo = seiriNo;
    }

    /** 領収書の写し提出(フラグ) */
    @Column(name = "copy_recipt")
    private Integer copyRecipt = INIT_Integer;

    /**
     * 領収書の写し提出(フラグ)を取得する
     *
     * @return 領収書の写し提出(フラグ)
     */
    public Integer getCopyRecipt() {
        return copyRecipt;
    }

    /**
     * 領収書の写し提出(フラグ)を設定する
     *
     * @param copyRecipt 領収書の写し提出(フラグ)
     */
    public void setCopyRecipt(final Integer copyRecipt) {
        this.copyRecipt = copyRecipt;
    }

    /** 監査意見書提出(フラグ) */
    @Column(name = "audit_option")
    private Integer auditOption = INIT_Integer;

    /**
     * 監査意見書提出(フラグ)を取得する
     *
     * @return 監査意見書提出(フラグ)
     */
    public Integer getAuditOption() {
        return auditOption;
    }

    /**
     * 監査意見書提出(フラグ)を設定する
     *
     * @param auditOption 監査意見書提出(フラグ)
     */
    public void setAuditOption(final Integer auditOption) {
        this.auditOption = auditOption;
    }

    /** 監査報告書提出(フラグ) */
    @Column(name = "audit_report")
    private Integer auditReport = INIT_Integer;

    /**
     * 監査報告書提出(フラグ)を取得する
     *
     * @return 監査報告書提出(フラグ)
     */
    public Integer getAuditReport() {
        return auditReport;
    }

    /**
     * 監査報告書提出(フラグ)を設定する
     *
     * @param auditReport 監査報告書提出(フラグ)
     */
    public void setAuditReport(final Integer auditReport) {
        this.auditReport = auditReport;
    }

    /** 支部から受領下書類提出(フラグ) */
    @Column(name = "shibu_document")
    private Integer shibuDocument = INIT_Integer;

    /**
     * 支部から受領下書類提出(フラグ)を取得する
     *
     * @return 支部から受領下書類提出(フラグ)
     */
    public Integer getShibuDocument() {
        return shibuDocument;
    }

    /**
     * 支部から受領下書類提出(フラグ)を設定する
     *
     * @param shibuDocument 支部から受領下書類提出(フラグ)
     */
    public void setShibuDocument(final Integer shibuDocument) {
        this.shibuDocument = shibuDocument;
    }

    /** 統括文書提出(フラグ) */
    @Column(name = "governing_document")
    private Integer governingDocument = INIT_Integer;

    /**
     * 統括文書提出(フラグ)を取得する
     *
     * @return 統括文書提出(フラグ)
     */
    public Integer getGoverningDocument() {
        return governingDocument;
    }

    /**
     * 統括文書提出(フラグ)を設定する
     *
     * @param governingDocument 統括文書提出(フラグ)
     */
    public void setGoverningDocument(final Integer governingDocument) {
        this.governingDocument = governingDocument;
    }

    /** 真実であることの宣誓フラグ */
    @Column(name = "flg_confirm")
    private Integer flgConfirm = INIT_Integer;

    /**
     * 真実であることの宣誓フラグを取得する
     *
     * @return 真実であることの宣誓フラグ
     */
    public Integer getFlgConfirm() {
        return flgConfirm;
    }

    /**
     * 真実であることの宣誓フラグを設定する
     *
     * @param flgConfirm 真実であることの宣誓フラグ
     */
    public void setFlgConfirm(final Integer flgConfirm) {
        this.flgConfirm = flgConfirm;
    }

    /** 提出日和暦 */
    @Column(name = "accrual_date")
    private String accrualDate = INIT_String;

    /**
     * 提出日和暦を取得する
     *
     * @return 提出日和暦
     */
    public String getAccrualDate() {
        return accrualDate;
    }

    /**
     * 提出日和暦を設定する
     *
     * @param accrualDate 提出日和暦
     */
    public void setAccrualDate(final String accrualDate) {
        this.accrualDate = accrualDate;
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
