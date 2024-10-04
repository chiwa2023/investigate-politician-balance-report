package mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * offering_party_usage_0802_and_0803_report_2022接続用Entity
 */
@Entity
@Table(name = "offering_party_usage_0802_and_0803_report_2022")
public class OfferingPartyUsage0802And0803Report2022Entity implements Serializable, AllTabeDataHistoryInterface { // NOPMD

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

    /** 使途報告書様式8その2と3Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_usage_0802_and_0803_report_id")
    private Long partyUsage0802And0803ReportId = INIT_Long;

    /**
     * 使途報告書様式8その2と3Idを取得する
     *
     * @return 使途報告書様式8その2と3Id
     */
    public Long getPartyUsage0802And0803ReportId() {
        return partyUsage0802And0803ReportId;
    }

    /**
     * 使途報告書様式8その2と3Idを設定する
     *
     * @param partyUsage0802And0803ReportId 使途報告書様式8その2と3Id
     */
    public void setPartyUsage0802And0803ReportId(final Long partyUsage0802And0803ReportId) {
        this.partyUsage0802And0803ReportId = partyUsage0802And0803ReportId;
    }

    /** 使途報告書様式8その2と3同一識別コード */
    @Column(name = "party_usage_0802_and_0803_report_code")
    private Long partyUsage0802And0803ReportCode = INIT_Long;

    /**
     * 使途報告書様式8その2と3同一識別コードを取得する
     *
     * @return 使途報告書様式8その2と3同一識別コード
     */
    public Long getPartyUsage0802And0803ReportCode() {
        return partyUsage0802And0803ReportCode;
    }

    /**
     * 使途報告書様式8その2と3同一識別コードを設定する
     *
     * @param partyUsage0802And0803ReportCode 使途報告書様式8その2と3同一識別コード
     */
    public void setPartyUsage0802And0803ReportCode(final Long partyUsage0802And0803ReportCode) {
        this.partyUsage0802And0803ReportCode = partyUsage0802And0803ReportCode;
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

    /** 全政治活動全合計 */
    @Column(name = "total_all_action_all")
    private Long totalAllActionAll = INIT_Long;

    /**
     * 全政治活動全合計を取得する
     *
     * @return 全政治活動全合計
     */
    public Long getTotalAllActionAll() {
        return totalAllActionAll;
    }

    /**
     * 全政治活動全合計を設定する
     *
     * @param totalAllActionAll 全政治活動全合計
     */
    public void setTotalAllActionAll(final Long totalAllActionAll) {
        this.totalAllActionAll = totalAllActionAll;
    }

    /** 全政治活動交付金充当 */
    @Column(name = "total_all_action_juto_koufukin")
    private Long totalAllActionJutoKoufukin = INIT_Long;

    /**
     * 全政治活動交付金充当を取得する
     *
     * @return 全政治活動交付金充当
     */
    public Long getTotalAllActionJutoKoufukin() {
        return totalAllActionJutoKoufukin;
    }

    /**
     * 全政治活動交付金充当を設定する
     *
     * @param totalAllActionJutoKoufukin 全政治活動交付金充当
     */
    public void setTotalAllActionJutoKoufukin(final Long totalAllActionJutoKoufukin) {
        this.totalAllActionJutoKoufukin = totalAllActionJutoKoufukin;
    }

    /** 全政治活動自党基金充当 */
    @Column(name = "total_all_action_juto_my_funds")
    private Long totalAllActionJutoMyFunds = INIT_Long;

    /**
     * 全政治活動自党基金充当を取得する
     *
     * @return 全政治活動自党基金充当
     */
    public Long getTotalAllActionJutoMyFunds() {
        return totalAllActionJutoMyFunds;
    }

    /**
     * 全政治活動自党基金充当を設定する
     *
     * @param totalAllActionJutoMyFunds 全政治活動自党基金充当
     */
    public void setTotalAllActionJutoMyFunds(final Long totalAllActionJutoMyFunds) {
        this.totalAllActionJutoMyFunds = totalAllActionJutoMyFunds;
    }

    /** 全政治活動備考 */
    @Column(name = "total_all_action_bikou")
    private String totalAllActionBikou = INIT_String;

    /**
     * 全政治活動備考を取得する
     *
     * @return 全政治活動備考
     */
    public String getTotalAllActionBikou() {
        return totalAllActionBikou;
    }

    /**
     * 全政治活動備考を設定する
     *
     * @param totalAllActionBikou 全政治活動備考
     */
    public void setTotalAllActionBikou(final String totalAllActionBikou) {
        this.totalAllActionBikou = totalAllActionBikou;
    }

    /** 全総計全合計 */
    @Column(name = "total_all_amount_all")
    private Long totalAllAmountAll = INIT_Long;

    /**
     * 全総計全合計を取得する
     *
     * @return 全総計全合計
     */
    public Long getTotalAllAmountAll() {
        return totalAllAmountAll;
    }

    /**
     * 全総計全合計を設定する
     *
     * @param totalAllAmountAll 全総計全合計
     */
    public void setTotalAllAmountAll(final Long totalAllAmountAll) {
        this.totalAllAmountAll = totalAllAmountAll;
    }

    /** 全総計交付金充当 */
    @Column(name = "total_all_amount_juto_koufukin")
    private Long totalAllAmountJutoKoufukin = INIT_Long;

    /**
     * 全総計交付金充当を取得する
     *
     * @return 全総計交付金充当
     */
    public Long getTotalAllAmountJutoKoufukin() {
        return totalAllAmountJutoKoufukin;
    }

    /**
     * 全総計交付金充当を設定する
     *
     * @param totalAllAmountJutoKoufukin 全総計交付金充当
     */
    public void setTotalAllAmountJutoKoufukin(final Long totalAllAmountJutoKoufukin) {
        this.totalAllAmountJutoKoufukin = totalAllAmountJutoKoufukin;
    }

    /** 総計自党基金充当 */
    @Column(name = "total_all_amount_juto_my_funds")
    private Long totalAllAmountJutoMyFunds = INIT_Long;

    /**
     * 総計自党基金充当を取得する
     *
     * @return 総計自党基金充当
     */
    public Long getTotalAllAmountJutoMyFunds() {
        return totalAllAmountJutoMyFunds;
    }

    /**
     * 総計自党基金充当を設定する
     *
     * @param totalAllAmountJutoMyFunds 総計自党基金充当
     */
    public void setTotalAllAmountJutoMyFunds(final Long totalAllAmountJutoMyFunds) {
        this.totalAllAmountJutoMyFunds = totalAllAmountJutoMyFunds;
    }

    /** 全総計備考 */
    @Column(name = "total_all_amount_bikou")
    private String totalAllAmountBikou = INIT_String;

    /**
     * 全総計備考を取得する
     *
     * @return 全総計備考
     */
    public String getTotalAllAmountBikou() {
        return totalAllAmountBikou;
    }

    /**
     * 全総計備考を設定する
     *
     * @param totalAllAmountBikou 全総計備考
     */
    public void setTotalAllAmountBikou(final String totalAllAmountBikou) {
        this.totalAllAmountBikou = totalAllAmountBikou;
    }

    /** 全事業総計全合計 */
    @Column(name = "total_all_jigyou_all")
    private Long totalAllJigyouAll = INIT_Long;

    /**
     * 全事業総計全合計を取得する
     *
     * @return 全事業総計全合計
     */
    public Long getTotalAllJigyouAll() {
        return totalAllJigyouAll;
    }

    /**
     * 全事業総計全合計を設定する
     *
     * @param totalAllJigyouAll 全事業総計全合計
     */
    public void setTotalAllJigyouAll(final Long totalAllJigyouAll) {
        this.totalAllJigyouAll = totalAllJigyouAll;
    }

    /** 全事業総計交付金充当 */
    @Column(name = "total_all_jigyou_juto_koufukin")
    private Long totalAllJigyouJutoKoufukin = INIT_Long;

    /**
     * 全事業総計交付金充当を取得する
     *
     * @return 全事業総計交付金充当
     */
    public Long getTotalAllJigyouJutoKoufukin() {
        return totalAllJigyouJutoKoufukin;
    }

    /**
     * 全事業総計交付金充当を設定する
     *
     * @param totalAllJigyouJutoKoufukin 全事業総計交付金充当
     */
    public void setTotalAllJigyouJutoKoufukin(final Long totalAllJigyouJutoKoufukin) {
        this.totalAllJigyouJutoKoufukin = totalAllJigyouJutoKoufukin;
    }

    /** 全事業自党基金充当 */
    @Column(name = "total_all_jigyou_juto_my_funds")
    private Long totalAllJigyouJutoMyFunds = INIT_Long;

    /**
     * 全事業自党基金充当を取得する
     *
     * @return 全事業自党基金充当
     */
    public Long getTotalAllJigyouJutoMyFunds() {
        return totalAllJigyouJutoMyFunds;
    }

    /**
     * 全事業自党基金充当を設定する
     *
     * @param totalAllJigyouJutoMyFunds 全事業自党基金充当
     */
    public void setTotalAllJigyouJutoMyFunds(final Long totalAllJigyouJutoMyFunds) {
        this.totalAllJigyouJutoMyFunds = totalAllJigyouJutoMyFunds;
    }

    /** 全事業備考 */
    @Column(name = "total_all_jigyou_bikou")
    private String totalAllJigyouBikou = INIT_String;

    /**
     * 全事業備考を取得する
     *
     * @return 全事業備考
     */
    public String getTotalAllJigyouBikou() {
        return totalAllJigyouBikou;
    }

    /**
     * 全事業備考を設定する
     *
     * @param totalAllJigyouBikou 全事業備考
     */
    public void setTotalAllJigyouBikou(final String totalAllJigyouBikou) {
        this.totalAllJigyouBikou = totalAllJigyouBikou;
    }

    /** 備品消耗品費全合計 */
    @Column(name = "total_bihin_all")
    private Long totalBihinAll = INIT_Long;

    /**
     * 備品消耗品費全合計を取得する
     *
     * @return 備品消耗品費全合計
     */
    public Long getTotalBihinAll() {
        return totalBihinAll;
    }

    /**
     * 備品消耗品費全合計を設定する
     *
     * @param totalBihinAll 備品消耗品費全合計
     */
    public void setTotalBihinAll(final Long totalBihinAll) {
        this.totalBihinAll = totalBihinAll;
    }

    /** 備品消耗品費交付金充当 */
    @Column(name = "total_bihin_juto_koufukin")
    private Long totalBihinJutoKoufukin = INIT_Long;

    /**
     * 備品消耗品費交付金充当を取得する
     *
     * @return 備品消耗品費交付金充当
     */
    public Long getTotalBihinJutoKoufukin() {
        return totalBihinJutoKoufukin;
    }

    /**
     * 備品消耗品費交付金充当を設定する
     *
     * @param totalBihinJutoKoufukin 備品消耗品費交付金充当
     */
    public void setTotalBihinJutoKoufukin(final Long totalBihinJutoKoufukin) {
        this.totalBihinJutoKoufukin = totalBihinJutoKoufukin;
    }

    /** 備品消耗品費自党基金充当 */
    @Column(name = "total_bihin_juto_my_funds")
    private Long totalBihinJutoMyFunds = INIT_Long;

    /**
     * 備品消耗品費自党基金充当を取得する
     *
     * @return 備品消耗品費自党基金充当
     */
    public Long getTotalBihinJutoMyFunds() {
        return totalBihinJutoMyFunds;
    }

    /**
     * 備品消耗品費自党基金充当を設定する
     *
     * @param totalBihinJutoMyFunds 備品消耗品費自党基金充当
     */
    public void setTotalBihinJutoMyFunds(final Long totalBihinJutoMyFunds) {
        this.totalBihinJutoMyFunds = totalBihinJutoMyFunds;
    }

    /** 備品消耗品費備考 */
    @Column(name = "total_bihin_bikou")
    private String totalBihinBikou = INIT_String;

    /**
     * 備品消耗品費備考を取得する
     *
     * @return 備品消耗品費備考
     */
    public String getTotalBihinBikou() {
        return totalBihinBikou;
    }

    /**
     * 備品消耗品費備考を設定する
     *
     * @param totalBihinBikou 備品消耗品費備考
     */
    public void setTotalBihinBikou(final String totalBihinBikou) {
        this.totalBihinBikou = totalBihinBikou;
    }

    /** 調査研究費全合計 */
    @Column(name = "total_chousa_all")
    private Long totalChousaAll = INIT_Long;

    /**
     * 調査研究費全合計を取得する
     *
     * @return 調査研究費全合計
     */
    public Long getTotalChousaAll() {
        return totalChousaAll;
    }

    /**
     * 調査研究費全合計を設定する
     *
     * @param totalChousaAll 調査研究費全合計
     */
    public void setTotalChousaAll(final Long totalChousaAll) {
        this.totalChousaAll = totalChousaAll;
    }

    /** 調査研究費交付金充当 */
    @Column(name = "total_chousa_juto_koufukin")
    private Long totalChousaJutoKoufukin = INIT_Long;

    /**
     * 調査研究費交付金充当を取得する
     *
     * @return 調査研究費交付金充当
     */
    public Long getTotalChousaJutoKoufukin() {
        return totalChousaJutoKoufukin;
    }

    /**
     * 調査研究費交付金充当を設定する
     *
     * @param totalChousaJutoKoufukin 調査研究費交付金充当
     */
    public void setTotalChousaJutoKoufukin(final Long totalChousaJutoKoufukin) {
        this.totalChousaJutoKoufukin = totalChousaJutoKoufukin;
    }

    /** 調査研究費自党基金充当 */
    @Column(name = "total_chousa_juto_my_funds")
    private Long totalChousaJutoMyFunds = INIT_Long;

    /**
     * 調査研究費自党基金充当を取得する
     *
     * @return 調査研究費自党基金充当
     */
    public Long getTotalChousaJutoMyFunds() {
        return totalChousaJutoMyFunds;
    }

    /**
     * 調査研究費自党基金充当を設定する
     *
     * @param totalChousaJutoMyFunds 調査研究費自党基金充当
     */
    public void setTotalChousaJutoMyFunds(final Long totalChousaJutoMyFunds) {
        this.totalChousaJutoMyFunds = totalChousaJutoMyFunds;
    }

    /** 調査研究費備考 */
    @Column(name = "total_chousa_bikou")
    private String totalChousaBikou = INIT_String;

    /**
     * 調査研究費備考を取得する
     *
     * @return 調査研究費備考
     */
    public String getTotalChousaBikou() {
        return totalChousaBikou;
    }

    /**
     * 調査研究費備考を設定する
     *
     * @param totalChousaBikou 調査研究費備考
     */
    public void setTotalChousaBikou(final String totalChousaBikou) {
        this.totalChousaBikou = totalChousaBikou;
    }

    /** 事務所費全合計 */
    @Column(name = "total_jimusho_all")
    private Long totalJimushoAll = INIT_Long;

    /**
     * 事務所費全合計を取得する
     *
     * @return 事務所費全合計
     */
    public Long getTotalJimushoAll() {
        return totalJimushoAll;
    }

    /**
     * 事務所費全合計を設定する
     *
     * @param totalJimushoAll 事務所費全合計
     */
    public void setTotalJimushoAll(final Long totalJimushoAll) {
        this.totalJimushoAll = totalJimushoAll;
    }

    /** 事務所費交付金充当 */
    @Column(name = "total_jimusho_juto_koufukin")
    private Long totalJimushoJutoKoufukin = INIT_Long;

    /**
     * 事務所費交付金充当を取得する
     *
     * @return 事務所費交付金充当
     */
    public Long getTotalJimushoJutoKoufukin() {
        return totalJimushoJutoKoufukin;
    }

    /**
     * 事務所費交付金充当を設定する
     *
     * @param totalJimushoJutoKoufukin 事務所費交付金充当
     */
    public void setTotalJimushoJutoKoufukin(final Long totalJimushoJutoKoufukin) {
        this.totalJimushoJutoKoufukin = totalJimushoJutoKoufukin;
    }

    /** 事務所費自党基金充当 */
    @Column(name = "total_jimusho_juto_my_funds")
    private Long totalJimushoJutoMyFunds = INIT_Long;

    /**
     * 事務所費自党基金充当を取得する
     *
     * @return 事務所費自党基金充当
     */
    public Long getTotalJimushoJutoMyFunds() {
        return totalJimushoJutoMyFunds;
    }

    /**
     * 事務所費自党基金充当を設定する
     *
     * @param totalJimushoJutoMyFunds 事務所費自党基金充当
     */
    public void setTotalJimushoJutoMyFunds(final Long totalJimushoJutoMyFunds) {
        this.totalJimushoJutoMyFunds = totalJimushoJutoMyFunds;
    }

    /** 事務所費備考 */
    @Column(name = "total_jimusho_bikou")
    private String totalJimushoBikou = INIT_String;

    /**
     * 事務所費備考を取得する
     *
     * @return 事務所費備考
     */
    public String getTotalJimushoBikou() {
        return totalJimushoBikou;
    }

    /**
     * 事務所費備考を設定する
     *
     * @param totalJimushoBikou 事務所費備考
     */
    public void setTotalJimushoBikou(final String totalJimushoBikou) {
        this.totalJimushoBikou = totalJimushoBikou;
    }

    /** 人件費全合計 */
    @Column(name = "total_jinkenhi_all")
    private Long totalJinkenhiAll = INIT_Long;

    /**
     * 人件費全合計を取得する
     *
     * @return 人件費全合計
     */
    public Long getTotalJinkenhiAll() {
        return totalJinkenhiAll;
    }

    /**
     * 人件費全合計を設定する
     *
     * @param totalJinkenhiAll 人件費全合計
     */
    public void setTotalJinkenhiAll(final Long totalJinkenhiAll) {
        this.totalJinkenhiAll = totalJinkenhiAll;
    }

    /** 人件費交付金充当 */
    @Column(name = "total_jinkenhi_juto_koufukin")
    private Long totalJinkenhiJutoKoufukin = INIT_Long;

    /**
     * 人件費交付金充当を取得する
     *
     * @return 人件費交付金充当
     */
    public Long getTotalJinkenhiJutoKoufukin() {
        return totalJinkenhiJutoKoufukin;
    }

    /**
     * 人件費交付金充当を設定する
     *
     * @param totalJinkenhiJutoKoufukin 人件費交付金充当
     */
    public void setTotalJinkenhiJutoKoufukin(final Long totalJinkenhiJutoKoufukin) {
        this.totalJinkenhiJutoKoufukin = totalJinkenhiJutoKoufukin;
    }

    /** 人件費自党基金充当 */
    @Column(name = "total_jinkenhi_juto_my_funds")
    private Long totalJinkenhiJutoMyFunds = INIT_Long;

    /**
     * 人件費自党基金充当を取得する
     *
     * @return 人件費自党基金充当
     */
    public Long getTotalJinkenhiJutoMyFunds() {
        return totalJinkenhiJutoMyFunds;
    }

    /**
     * 人件費自党基金充当を設定する
     *
     * @param totalJinkenhiJutoMyFunds 人件費自党基金充当
     */
    public void setTotalJinkenhiJutoMyFunds(final Long totalJinkenhiJutoMyFunds) {
        this.totalJinkenhiJutoMyFunds = totalJinkenhiJutoMyFunds;
    }

    /** 人件費備考 */
    @Column(name = "total_jinkenhi_bikou")
    private String totalJinkenhiBikou = INIT_String;

    /**
     * 人件費備考を取得する
     *
     * @return 人件費備考
     */
    public String getTotalJinkenhiBikou() {
        return totalJinkenhiBikou;
    }

    /**
     * 人件費備考を設定する
     *
     * @param totalJinkenhiBikou 人件費備考
     */
    public void setTotalJinkenhiBikou(final String totalJinkenhiBikou) {
        this.totalJinkenhiBikou = totalJinkenhiBikou;
    }

    /** 経費全合計 */
    @Column(name = "total_keihi_all")
    private Long totalKeihiAll = INIT_Long;

    /**
     * 経費全合計を取得する
     *
     * @return 経費全合計
     */
    public Long getTotalKeihiAll() {
        return totalKeihiAll;
    }

    /**
     * 経費全合計を設定する
     *
     * @param totalKeihiAll 経費全合計
     */
    public void setTotalKeihiAll(final Long totalKeihiAll) {
        this.totalKeihiAll = totalKeihiAll;
    }

    /** 経費交付金充当 */
    @Column(name = "total_keihi_juto_koufukin")
    private Long totalKeihiJutoKoufukin = INIT_Long;

    /**
     * 経費交付金充当を取得する
     *
     * @return 経費交付金充当
     */
    public Long getTotalKeihiJutoKoufukin() {
        return totalKeihiJutoKoufukin;
    }

    /**
     * 経費交付金充当を設定する
     *
     * @param totalKeihiJutoKoufukin 経費交付金充当
     */
    public void setTotalKeihiJutoKoufukin(final Long totalKeihiJutoKoufukin) {
        this.totalKeihiJutoKoufukin = totalKeihiJutoKoufukin;
    }

    /** 経費自党基金充当 */
    @Column(name = "total_keihi_juto_my_funds")
    private Long totalKeihiJutoMyFunds = INIT_Long;

    /**
     * 経費自党基金充当を取得する
     *
     * @return 経費自党基金充当
     */
    public Long getTotalKeihiJutoMyFunds() {
        return totalKeihiJutoMyFunds;
    }

    /**
     * 経費自党基金充当を設定する
     *
     * @param totalKeihiJutoMyFunds 経費自党基金充当
     */
    public void setTotalKeihiJutoMyFunds(final Long totalKeihiJutoMyFunds) {
        this.totalKeihiJutoMyFunds = totalKeihiJutoMyFunds;
    }

    /** 経費備考 */
    @Column(name = "total_keihi_bikou")
    private String totalKeihiBikou = INIT_String;

    /**
     * 経費備考を取得する
     *
     * @return 経費備考
     */
    public String getTotalKeihiBikou() {
        return totalKeihiBikou;
    }

    /**
     * 経費備考を設定する
     *
     * @param totalKeihiBikou 経費備考
     */
    public void setTotalKeihiBikou(final String totalKeihiBikou) {
        this.totalKeihiBikou = totalKeihiBikou;
    }

    /** 寄附全合計 */
    @Column(name = "total_kifu_all")
    private Long totalKifuAll = INIT_Long;

    /**
     * 寄附全合計を取得する
     *
     * @return 寄附全合計
     */
    public Long getTotalKifuAll() {
        return totalKifuAll;
    }

    /**
     * 寄附全合計を設定する
     *
     * @param totalKifuAll 寄附全合計
     */
    public void setTotalKifuAll(final Long totalKifuAll) {
        this.totalKifuAll = totalKifuAll;
    }

    /** 寄附交付金充当 */
    @Column(name = "total_kifu_juto_koufukin")
    private Long totalKifuJutoKoufukin = INIT_Long;

    /**
     * 寄附交付金充当を取得する
     *
     * @return 寄附交付金充当
     */
    public Long getTotalKifuJutoKoufukin() {
        return totalKifuJutoKoufukin;
    }

    /**
     * 寄附交付金充当を設定する
     *
     * @param totalKifuJutoKoufukin 寄附交付金充当
     */
    public void setTotalKifuJutoKoufukin(final Long totalKifuJutoKoufukin) {
        this.totalKifuJutoKoufukin = totalKifuJutoKoufukin;
    }

    /** 寄附自党基金充当 */
    @Column(name = "total_kifu_juto_my_funds")
    private Long totalKifuJutoMyFunds = INIT_Long;

    /**
     * 寄附自党基金充当を取得する
     *
     * @return 寄附自党基金充当
     */
    public Long getTotalKifuJutoMyFunds() {
        return totalKifuJutoMyFunds;
    }

    /**
     * 寄附自党基金充当を設定する
     *
     * @param totalKifuJutoMyFunds 寄附自党基金充当
     */
    public void setTotalKifuJutoMyFunds(final Long totalKifuJutoMyFunds) {
        this.totalKifuJutoMyFunds = totalKifuJutoMyFunds;
    }

    /** 寄附備考 */
    @Column(name = "total_kifu_bikou")
    private String totalKifuBikou = INIT_String;

    /**
     * 寄附備考を取得する
     *
     * @return 寄附備考
     */
    public String getTotalKifuBikou() {
        return totalKifuBikou;
    }

    /**
     * 寄附備考を設定する
     *
     * @param totalKifuBikou 寄附備考
     */
    public void setTotalKifuBikou(final String totalKifuBikou) {
        this.totalKifuBikou = totalKifuBikou;
    }

    /** 機関誌発行全合計 */
    @Column(name = "total_kikanshi_all")
    private Long totalKikanshiAll = INIT_Long;

    /**
     * 機関誌発行全合計を取得する
     *
     * @return 機関誌発行全合計
     */
    public Long getTotalKikanshiAll() {
        return totalKikanshiAll;
    }

    /**
     * 機関誌発行全合計を設定する
     *
     * @param totalKikanshiAll 機関誌発行全合計
     */
    public void setTotalKikanshiAll(final Long totalKikanshiAll) {
        this.totalKikanshiAll = totalKikanshiAll;
    }

    /** 機関誌発行交付金充当 */
    @Column(name = "total_kikanshi_juto_koufukin")
    private Long totalKikanshiJutoKoufukin = INIT_Long;

    /**
     * 機関誌発行交付金充当を取得する
     *
     * @return 機関誌発行交付金充当
     */
    public Long getTotalKikanshiJutoKoufukin() {
        return totalKikanshiJutoKoufukin;
    }

    /**
     * 機関誌発行交付金充当を設定する
     *
     * @param totalKikanshiJutoKoufukin 機関誌発行交付金充当
     */
    public void setTotalKikanshiJutoKoufukin(final Long totalKikanshiJutoKoufukin) {
        this.totalKikanshiJutoKoufukin = totalKikanshiJutoKoufukin;
    }

    /** 機関誌発行自党基金充当 */
    @Column(name = "total_kikanshi_juto_my_funds")
    private Long totalKikanshiJutoMyFunds = INIT_Long;

    /**
     * 機関誌発行自党基金充当を取得する
     *
     * @return 機関誌発行自党基金充当
     */
    public Long getTotalKikanshiJutoMyFunds() {
        return totalKikanshiJutoMyFunds;
    }

    /**
     * 機関誌発行自党基金充当を設定する
     *
     * @param totalKikanshiJutoMyFunds 機関誌発行自党基金充当
     */
    public void setTotalKikanshiJutoMyFunds(final Long totalKikanshiJutoMyFunds) {
        this.totalKikanshiJutoMyFunds = totalKikanshiJutoMyFunds;
    }

    /** 機関誌発行備考 */
    @Column(name = "total_kikanshi_bikou")
    private String totalKikanshiBikou = INIT_String;

    /**
     * 機関誌発行備考を取得する
     *
     * @return 機関誌発行備考
     */
    public String getTotalKikanshiBikou() {
        return totalKikanshiBikou;
    }

    /**
     * 機関誌発行備考を設定する
     *
     * @param totalKikanshiBikou 機関誌発行備考
     */
    public void setTotalKikanshiBikou(final String totalKikanshiBikou) {
        this.totalKikanshiBikou = totalKikanshiBikou;
    }

    /** 光熱費全合計 */
    @Column(name = "total_kounetsuhi_all")
    private Long totalKounetsuhiAll = INIT_Long;

    /**
     * 光熱費全合計を取得する
     *
     * @return 光熱費全合計
     */
    public Long getTotalKounetsuhiAll() {
        return totalKounetsuhiAll;
    }

    /**
     * 光熱費全合計を設定する
     *
     * @param totalKounetsuhiAll 光熱費全合計
     */
    public void setTotalKounetsuhiAll(final Long totalKounetsuhiAll) {
        this.totalKounetsuhiAll = totalKounetsuhiAll;
    }

    /** 光熱費交付金充当 */
    @Column(name = "total_kounetsuhi_juto_koufukin")
    private Long totalKounetsuhiJutoKoufukin = INIT_Long;

    /**
     * 光熱費交付金充当を取得する
     *
     * @return 光熱費交付金充当
     */
    public Long getTotalKounetsuhiJutoKoufukin() {
        return totalKounetsuhiJutoKoufukin;
    }

    /**
     * 光熱費交付金充当を設定する
     *
     * @param totalKounetsuhiJutoKoufukin 光熱費交付金充当
     */
    public void setTotalKounetsuhiJutoKoufukin(final Long totalKounetsuhiJutoKoufukin) {
        this.totalKounetsuhiJutoKoufukin = totalKounetsuhiJutoKoufukin;
    }

    /** 光熱費自党基金充当 */
    @Column(name = "total_kounetsuhi_juto_my_funds")
    private Long totalKounetsuhiJutoMyFunds = INIT_Long;

    /**
     * 光熱費自党基金充当を取得する
     *
     * @return 光熱費自党基金充当
     */
    public Long getTotalKounetsuhiJutoMyFunds() {
        return totalKounetsuhiJutoMyFunds;
    }

    /**
     * 光熱費自党基金充当を設定する
     *
     * @param totalKounetsuhiJutoMyFunds 光熱費自党基金充当
     */
    public void setTotalKounetsuhiJutoMyFunds(final Long totalKounetsuhiJutoMyFunds) {
        this.totalKounetsuhiJutoMyFunds = totalKounetsuhiJutoMyFunds;
    }

    /** 光熱費備考 */
    @Column(name = "total_kounetsuhi_bikou")
    private String totalKounetsuhiBikou = INIT_String;

    /**
     * 光熱費備考を取得する
     *
     * @return 光熱費備考
     */
    public String getTotalKounetsuhiBikou() {
        return totalKounetsuhiBikou;
    }

    /**
     * 光熱費備考を設定する
     *
     * @param totalKounetsuhiBikou 光熱費備考
     */
    public void setTotalKounetsuhiBikou(final String totalKounetsuhiBikou) {
        this.totalKounetsuhiBikou = totalKounetsuhiBikou;
    }

    /** パーティ費全合計 */
    @Column(name = "total_party_all")
    private Long totalPartyAll = INIT_Long;

    /**
     * パーティ費全合計を取得する
     *
     * @return パーティ費全合計
     */
    public Long getTotalPartyAll() {
        return totalPartyAll;
    }

    /**
     * パーティ費全合計を設定する
     *
     * @param totalPartyAll パーティ費全合計
     */
    public void setTotalPartyAll(final Long totalPartyAll) {
        this.totalPartyAll = totalPartyAll;
    }

    /** パーティ費交付金充当 */
    @Column(name = "total_party_juto_koufukin")
    private Long totalPartyJutoKoufukin = INIT_Long;

    /**
     * パーティ費交付金充当を取得する
     *
     * @return パーティ費交付金充当
     */
    public Long getTotalPartyJutoKoufukin() {
        return totalPartyJutoKoufukin;
    }

    /**
     * パーティ費交付金充当を設定する
     *
     * @param totalPartyJutoKoufukin パーティ費交付金充当
     */
    public void setTotalPartyJutoKoufukin(final Long totalPartyJutoKoufukin) {
        this.totalPartyJutoKoufukin = totalPartyJutoKoufukin;
    }

    /** パーティ費自党基金充当 */
    @Column(name = "total_party_juto_my_funds")
    private Long totalPartyJutoMyFunds = INIT_Long;

    /**
     * パーティ費自党基金充当を取得する
     *
     * @return パーティ費自党基金充当
     */
    public Long getTotalPartyJutoMyFunds() {
        return totalPartyJutoMyFunds;
    }

    /**
     * パーティ費自党基金充当を設定する
     *
     * @param totalPartyJutoMyFunds パーティ費自党基金充当
     */
    public void setTotalPartyJutoMyFunds(final Long totalPartyJutoMyFunds) {
        this.totalPartyJutoMyFunds = totalPartyJutoMyFunds;
    }

    /** パーティ費備考 */
    @Column(name = "total_party_bikou")
    private String totalPartyBikou = INIT_String;

    /**
     * パーティ費備考を取得する
     *
     * @return パーティ費備考
     */
    public String getTotalPartyBikou() {
        return totalPartyBikou;
    }

    /**
     * パーティ費備考を設定する
     *
     * @param totalPartyBikou パーティ費備考
     */
    public void setTotalPartyBikou(final String totalPartyBikou) {
        this.totalPartyBikou = totalPartyBikou;
    }

    /** 宣伝費全合計 */
    @Column(name = "total_senden_all")
    private Long totalSendenAll = INIT_Long;

    /**
     * 宣伝費全合計を取得する
     *
     * @return 宣伝費全合計
     */
    public Long getTotalSendenAll() {
        return totalSendenAll;
    }

    /**
     * 宣伝費全合計を設定する
     *
     * @param totalSendenAll 宣伝費全合計
     */
    public void setTotalSendenAll(final Long totalSendenAll) {
        this.totalSendenAll = totalSendenAll;
    }

    /** 宣伝費交付金充当 */
    @Column(name = "total_senden_juto_koufukin")
    private Long totalSendenJutoKoufukin = INIT_Long;

    /**
     * 宣伝費交付金充当を取得する
     *
     * @return 宣伝費交付金充当
     */
    public Long getTotalSendenJutoKoufukin() {
        return totalSendenJutoKoufukin;
    }

    /**
     * 宣伝費交付金充当を設定する
     *
     * @param totalSendenJutoKoufukin 宣伝費交付金充当
     */
    public void setTotalSendenJutoKoufukin(final Long totalSendenJutoKoufukin) {
        this.totalSendenJutoKoufukin = totalSendenJutoKoufukin;
    }

    /** 宣伝費自党基金充当 */
    @Column(name = "total_senden_juto_my_funds")
    private Long totalSendenJutoMyFunds = INIT_Long;

    /**
     * 宣伝費自党基金充当を取得する
     *
     * @return 宣伝費自党基金充当
     */
    public Long getTotalSendenJutoMyFunds() {
        return totalSendenJutoMyFunds;
    }

    /**
     * 宣伝費自党基金充当を設定する
     *
     * @param totalSendenJutoMyFunds 宣伝費自党基金充当
     */
    public void setTotalSendenJutoMyFunds(final Long totalSendenJutoMyFunds) {
        this.totalSendenJutoMyFunds = totalSendenJutoMyFunds;
    }

    /** 宣伝費備考 */
    @Column(name = "total_senden_bikou")
    private String totalSendenBikou = INIT_String;

    /**
     * 宣伝費備考を取得する
     *
     * @return 宣伝費備考
     */
    public String getTotalSendenBikou() {
        return totalSendenBikou;
    }

    /**
     * 宣伝費備考を設定する
     *
     * @param totalSendenBikou 宣伝費備考
     */
    public void setTotalSendenBikou(final String totalSendenBikou) {
        this.totalSendenBikou = totalSendenBikou;
    }

    /** 選挙費全合計 */
    @Column(name = "total_senkyo_all")
    private Long totalSenkyoAll = INIT_Long;

    /**
     * 選挙費全合計を取得する
     *
     * @return 選挙費全合計
     */
    public Long getTotalSenkyoAll() {
        return totalSenkyoAll;
    }

    /**
     * 選挙費全合計を設定する
     *
     * @param totalSenkyoAll 選挙費全合計
     */
    public void setTotalSenkyoAll(final Long totalSenkyoAll) {
        this.totalSenkyoAll = totalSenkyoAll;
    }

    /** 選挙費交付金充当 */
    @Column(name = "total_senkyo_juto_koufukin")
    private Long totalSenkyoJutoKoufukin = INIT_Long;

    /**
     * 選挙費交付金充当を取得する
     *
     * @return 選挙費交付金充当
     */
    public Long getTotalSenkyoJutoKoufukin() {
        return totalSenkyoJutoKoufukin;
    }

    /**
     * 選挙費交付金充当を設定する
     *
     * @param totalSenkyoJutoKoufukin 選挙費交付金充当
     */
    public void setTotalSenkyoJutoKoufukin(final Long totalSenkyoJutoKoufukin) {
        this.totalSenkyoJutoKoufukin = totalSenkyoJutoKoufukin;
    }

    /** 選挙費自党基金充当 */
    @Column(name = "total_senkyo_juto_my_funds")
    private Long totalSenkyoJutoMyFunds = INIT_Long;

    /**
     * 選挙費自党基金充当を取得する
     *
     * @return 選挙費自党基金充当
     */
    public Long getTotalSenkyoJutoMyFunds() {
        return totalSenkyoJutoMyFunds;
    }

    /**
     * 選挙費自党基金充当を設定する
     *
     * @param totalSenkyoJutoMyFunds 選挙費自党基金充当
     */
    public void setTotalSenkyoJutoMyFunds(final Long totalSenkyoJutoMyFunds) {
        this.totalSenkyoJutoMyFunds = totalSenkyoJutoMyFunds;
    }

    /** 選挙費備考 */
    @Column(name = "total_senkyo_bikou")
    private String totalSenkyoBikou = INIT_String;

    /**
     * 選挙費備考を取得する
     *
     * @return 選挙費備考
     */
    public String getTotalSenkyoBikou() {
        return totalSenkyoBikou;
    }

    /**
     * 選挙費備考を設定する
     *
     * @param totalSenkyoBikou 選挙費備考
     */
    public void setTotalSenkyoBikou(final String totalSenkyoBikou) {
        this.totalSenkyoBikou = totalSenkyoBikou;
    }

    /** 支部交付金全合計 */
    @Column(name = "total_shibu_koufu_all")
    private Long totalShibuKoufuAll = INIT_Long;

    /**
     * 支部交付金全合計を取得する
     *
     * @return 支部交付金全合計
     */
    public Long getTotalShibuKoufuAll() {
        return totalShibuKoufuAll;
    }

    /**
     * 支部交付金全合計を設定する
     *
     * @param totalShibuKoufuAll 支部交付金全合計
     */
    public void setTotalShibuKoufuAll(final Long totalShibuKoufuAll) {
        this.totalShibuKoufuAll = totalShibuKoufuAll;
    }

    /** 支部交付金交付金充当 */
    @Column(name = "total_shibu_koufu_juto_koufukin")
    private Long totalShibuKoufuJutoKoufukin = INIT_Long;

    /**
     * 支部交付金交付金充当を取得する
     *
     * @return 支部交付金交付金充当
     */
    public Long getTotalShibuKoufuJutoKoufukin() {
        return totalShibuKoufuJutoKoufukin;
    }

    /**
     * 支部交付金交付金充当を設定する
     *
     * @param totalShibuKoufuJutoKoufukin 支部交付金交付金充当
     */
    public void setTotalShibuKoufuJutoKoufukin(final Long totalShibuKoufuJutoKoufukin) {
        this.totalShibuKoufuJutoKoufukin = totalShibuKoufuJutoKoufukin;
    }

    /** 支部交付金自党基金充当 */
    @Column(name = "total_shibu_koufu_juto_my_funds")
    private Long totalShibuKoufuJutoMyFunds = INIT_Long;

    /**
     * 支部交付金自党基金充当を取得する
     *
     * @return 支部交付金自党基金充当
     */
    public Long getTotalShibuKoufuJutoMyFunds() {
        return totalShibuKoufuJutoMyFunds;
    }

    /**
     * 支部交付金自党基金充当を設定する
     *
     * @param totalShibuKoufuJutoMyFunds 支部交付金自党基金充当
     */
    public void setTotalShibuKoufuJutoMyFunds(final Long totalShibuKoufuJutoMyFunds) {
        this.totalShibuKoufuJutoMyFunds = totalShibuKoufuJutoMyFunds;
    }

    /** 支部交付金備考 */
    @Column(name = "total_shibu_koufu_bikou")
    private String totalShibuKoufuBikou = INIT_String;

    /**
     * 支部交付金備考を取得する
     *
     * @return 支部交付金備考
     */
    public String getTotalShibuKoufuBikou() {
        return totalShibuKoufuBikou;
    }

    /**
     * 支部交付金備考を設定する
     *
     * @param totalShibuKoufuBikou 支部交付金備考
     */
    public void setTotalShibuKoufuBikou(final String totalShibuKoufuBikou) {
        this.totalShibuKoufuBikou = totalShibuKoufuBikou;
    }

    /** その他事業費全合計 */
    @Column(name = "total_sonota_jigyou_all")
    private Long totalSonotaJigyouAll = INIT_Long;

    /**
     * その他事業費全合計を取得する
     *
     * @return その他事業費全合計
     */
    public Long getTotalSonotaJigyouAll() {
        return totalSonotaJigyouAll;
    }

    /**
     * その他事業費全合計を設定する
     *
     * @param totalSonotaJigyouAll その他事業費全合計
     */
    public void setTotalSonotaJigyouAll(final Long totalSonotaJigyouAll) {
        this.totalSonotaJigyouAll = totalSonotaJigyouAll;
    }

    /** その他事業費交付金充当 */
    @Column(name = "total_sonota_jigyou_juto_koufukin")
    private Long totalSonotaJigyouJutoKoufukin = INIT_Long;

    /**
     * その他事業費交付金充当を取得する
     *
     * @return その他事業費交付金充当
     */
    public Long getTotalSonotaJigyouJutoKoufukin() {
        return totalSonotaJigyouJutoKoufukin;
    }

    /**
     * その他事業費交付金充当を設定する
     *
     * @param totalSonotaJigyouJutoKoufukin その他事業費交付金充当
     */
    public void setTotalSonotaJigyouJutoKoufukin(final Long totalSonotaJigyouJutoKoufukin) {
        this.totalSonotaJigyouJutoKoufukin = totalSonotaJigyouJutoKoufukin;
    }

    /** その他事業費自党基金充当 */
    @Column(name = "total_sonota_jigyou_juto_my_funds")
    private Long totalSonotaJigyouJutoMyFunds = INIT_Long;

    /**
     * その他事業費自党基金充当を取得する
     *
     * @return その他事業費自党基金充当
     */
    public Long getTotalSonotaJigyouJutoMyFunds() {
        return totalSonotaJigyouJutoMyFunds;
    }

    /**
     * その他事業費自党基金充当を設定する
     *
     * @param totalSonotaJigyouJutoMyFunds その他事業費自党基金充当
     */
    public void setTotalSonotaJigyouJutoMyFunds(final Long totalSonotaJigyouJutoMyFunds) {
        this.totalSonotaJigyouJutoMyFunds = totalSonotaJigyouJutoMyFunds;
    }

    /** その他事業費備考 */
    @Column(name = "total_sonota_jigyou_bikou")
    private String totalSonotaJigyouBikou = INIT_String;

    /**
     * その他事業費備考を取得する
     *
     * @return その他事業費備考
     */
    public String getTotalSonotaJigyouBikou() {
        return totalSonotaJigyouBikou;
    }

    /**
     * その他事業費備考を設定する
     *
     * @param totalSonotaJigyouBikou その他事業費備考
     */
    public void setTotalSonotaJigyouBikou(final String totalSonotaJigyouBikou) {
        this.totalSonotaJigyouBikou = totalSonotaJigyouBikou;
    }

    /** その他経費全合計 */
    @Column(name = "total_sonota_keihi_all")
    private Long totalSonotaKeihiAll = INIT_Long;

    /**
     * その他経費全合計を取得する
     *
     * @return その他経費全合計
     */
    public Long getTotalSonotaKeihiAll() {
        return totalSonotaKeihiAll;
    }

    /**
     * その他経費全合計を設定する
     *
     * @param totalSonotaKeihiAll その他経費全合計
     */
    public void setTotalSonotaKeihiAll(final Long totalSonotaKeihiAll) {
        this.totalSonotaKeihiAll = totalSonotaKeihiAll;
    }

    /** その他経費交付金充当 */
    @Column(name = "total_sonota_keihi_juto_koufukin")
    private Long totalSonotaKeihiJutoKoufukin = INIT_Long;

    /**
     * その他経費交付金充当を取得する
     *
     * @return その他経費交付金充当
     */
    public Long getTotalSonotaKeihiJutoKoufukin() {
        return totalSonotaKeihiJutoKoufukin;
    }

    /**
     * その他経費交付金充当を設定する
     *
     * @param totalSonotaKeihiJutoKoufukin その他経費交付金充当
     */
    public void setTotalSonotaKeihiJutoKoufukin(final Long totalSonotaKeihiJutoKoufukin) {
        this.totalSonotaKeihiJutoKoufukin = totalSonotaKeihiJutoKoufukin;
    }

    /** その他経費自党基金充当 */
    @Column(name = "total_sonota_keihi_juto_my_funds")
    private Long totalSonotaKeihiJutoMyFunds = INIT_Long;

    /**
     * その他経費自党基金充当を取得する
     *
     * @return その他経費自党基金充当
     */
    public Long getTotalSonotaKeihiJutoMyFunds() {
        return totalSonotaKeihiJutoMyFunds;
    }

    /**
     * その他経費自党基金充当を設定する
     *
     * @param totalSonotaKeihiJutoMyFunds その他経費自党基金充当
     */
    public void setTotalSonotaKeihiJutoMyFunds(final Long totalSonotaKeihiJutoMyFunds) {
        this.totalSonotaKeihiJutoMyFunds = totalSonotaKeihiJutoMyFunds;
    }

    /** その他経費備考 */
    @Column(name = "total_sonota_keihi_bikou")
    private String totalSonotaKeihiBikou = INIT_String;

    /**
     * その他経費備考を取得する
     *
     * @return その他経費備考
     */
    public String getTotalSonotaKeihiBikou() {
        return totalSonotaKeihiBikou;
    }

    /**
     * その他経費備考を設定する
     *
     * @param totalSonotaKeihiBikou その他経費備考
     */
    public void setTotalSonotaKeihiBikou(final String totalSonotaKeihiBikou) {
        this.totalSonotaKeihiBikou = totalSonotaKeihiBikou;
    }

    /** 組織費全合計 */
    @Column(name = "total_soshiki_all")
    private Long totalSoshikiAll = INIT_Long;

    /**
     * 組織費全合計を取得する
     *
     * @return 組織費全合計
     */
    public Long getTotalSoshikiAll() {
        return totalSoshikiAll;
    }

    /**
     * 組織費全合計を設定する
     *
     * @param totalSoshikiAll 組織費全合計
     */
    public void setTotalSoshikiAll(final Long totalSoshikiAll) {
        this.totalSoshikiAll = totalSoshikiAll;
    }

    /** 組織費交付金充当 */
    @Column(name = "total_soshiki_juto_koufukin")
    private Long totalSoshikiJutoKoufukin = INIT_Long;

    /**
     * 組織費交付金充当を取得する
     *
     * @return 組織費交付金充当
     */
    public Long getTotalSoshikiJutoKoufukin() {
        return totalSoshikiJutoKoufukin;
    }

    /**
     * 組織費交付金充当を設定する
     *
     * @param totalSoshikiJutoKoufukin 組織費交付金充当
     */
    public void setTotalSoshikiJutoKoufukin(final Long totalSoshikiJutoKoufukin) {
        this.totalSoshikiJutoKoufukin = totalSoshikiJutoKoufukin;
    }

    /** 組織費自党基金充当 */
    @Column(name = "total_soshiki_juto_my_funds")
    private Long totalSoshikiJutoMyFunds = INIT_Long;

    /**
     * 組織費自党基金充当を取得する
     *
     * @return 組織費自党基金充当
     */
    public Long getTotalSoshikiJutoMyFunds() {
        return totalSoshikiJutoMyFunds;
    }

    /**
     * 組織費自党基金充当を設定する
     *
     * @param totalSoshikiJutoMyFunds 組織費自党基金充当
     */
    public void setTotalSoshikiJutoMyFunds(final Long totalSoshikiJutoMyFunds) {
        this.totalSoshikiJutoMyFunds = totalSoshikiJutoMyFunds;
    }

    /** 組織費備考 */
    @Column(name = "total_soshiki_bikou")
    private String totalSoshikiBikou = INIT_String;

    /**
     * 組織費備考を取得する
     *
     * @return 組織費備考
     */
    public String getTotalSoshikiBikou() {
        return totalSoshikiBikou;
    }

    /**
     * 組織費備考を設定する
     *
     * @param totalSoshikiBikou 組織費備考
     */
    public void setTotalSoshikiBikou(final String totalSoshikiBikou) {
        this.totalSoshikiBikou = totalSoshikiBikou;
    }

    /** 政党交付金（支部政党交付金）の総額 */
    @Column(name = "party_all_koufukin")
    private Long partyAllKoufukin = INIT_Long;

    /**
     * 政党交付金（支部政党交付金）の総額を取得する
     *
     * @return 政党交付金（支部政党交付金）の総額
     */
    public Long getPartyAllKoufukin() {
        return partyAllKoufukin;
    }

    /**
     * 政党交付金（支部政党交付金）の総額を設定する
     *
     * @param partyAllKoufukin 政党交付金（支部政党交付金）の総額
     */
    public void setPartyAllKoufukin(final Long partyAllKoufukin) {
        this.partyAllKoufukin = partyAllKoufukin;
    }

    /** 前年末政党基金（支部基金）残高 */
    @Column(name = "last_year_rest")
    private Long lastYearRest = INIT_Long;

    /**
     * 前年末政党基金（支部基金）残高を取得する
     *
     * @return 前年末政党基金（支部基金）残高
     */
    public Long getLastYearRest() {
        return lastYearRest;
    }

    /**
     * 前年末政党基金（支部基金）残高を設定する
     *
     * @param lastYearRest 前年末政党基金（支部基金）残高
     */
    public void setLastYearRest(final Long lastYearRest) {
        this.lastYearRest = lastYearRest;
    }

    /** 政党交付金（支部政党交付金）による支出総額 */
    @Column(name = "outcome_koufukin_all")
    private Long outcomeKoufukinAll = INIT_Long;

    /**
     * 政党交付金（支部政党交付金）による支出総額を取得する
     *
     * @return 政党交付金（支部政党交付金）による支出総額
     */
    public Long getOutcomeKoufukinAll() {
        return outcomeKoufukinAll;
    }

    /**
     * 政党交付金（支部政党交付金）による支出総額を設定する
     *
     * @param outcomeKoufukinAll 政党交付金（支部政党交付金）による支出総額
     */
    public void setOutcomeKoufukinAll(final Long outcomeKoufukinAll) {
        this.outcomeKoufukinAll = outcomeKoufukinAll;
    }

    /** 政党交付金（支部政党交付金）支出充当額総額 */
    @Column(name = "outcome_koufukin")
    private Long outcomeKoufukin = INIT_Long;

    /**
     * 政党交付金（支部政党交付金）支出充当額総額を取得する
     *
     * @return 政党交付金（支部政党交付金）支出充当額総額
     */
    public Long getOutcomeKoufukin() {
        return outcomeKoufukin;
    }

    /**
     * 政党交付金（支部政党交付金）支出充当額総額を設定する
     *
     * @param outcomeKoufukin 政党交付金（支部政党交付金）支出充当額総額
     */
    public void setOutcomeKoufukin(final Long outcomeKoufukin) {
        this.outcomeKoufukin = outcomeKoufukin;
    }

    /** 政党基金（支部基金）支出充当額総額 */
    @Column(name = "outcome_shibu_kikin")
    private Long outcomeShibuKikin = INIT_Long;

    /**
     * 政党基金（支部基金）支出充当額総額を取得する
     *
     * @return 政党基金（支部基金）支出充当額総額
     */
    public Long getOutcomeShibuKikin() {
        return outcomeShibuKikin;
    }

    /**
     * 政党基金（支部基金）支出充当額総額を設定する
     *
     * @param outcomeShibuKikin 政党基金（支部基金）支出充当額総額
     */
    public void setOutcomeShibuKikin(final Long outcomeShibuKikin) {
        this.outcomeShibuKikin = outcomeShibuKikin;
    }

    /** 基金取り崩し */
    @Column(name = "withdrawal_funds")
    private Long withdrawalFunds = INIT_Long;

    /**
     * 基金取り崩しを取得する
     *
     * @return 基金取り崩し
     */
    public Long getWithdrawalFunds() {
        return withdrawalFunds;
    }

    /**
     * 基金取り崩しを設定する
     *
     * @param withdrawalFunds 基金取り崩し
     */
    public void setWithdrawalFunds(final Long withdrawalFunds) {
        this.withdrawalFunds = withdrawalFunds;
    }

    /** 政党基金（支部基金）積立総額 */
    @Column(name = "accumulate_funds")
    private Long accumulateFunds = INIT_Long;

    /**
     * 政党基金（支部基金）積立総額を取得する
     *
     * @return 政党基金（支部基金）積立総額
     */
    public Long getAccumulateFunds() {
        return accumulateFunds;
    }

    /**
     * 政党基金（支部基金）積立総額を設定する
     *
     * @param accumulateFunds 政党基金（支部基金）積立総額
     */
    public void setAccumulateFunds(final Long accumulateFunds) {
        this.accumulateFunds = accumulateFunds;
    }

    /** 政党基金（支部基金）の運用により収受した果実の総額 */
    @Column(name = "funds_sum_gain")
    private Long fundsSumGain = INIT_Long;

    /**
     * 政党基金（支部基金）の運用により収受した果実の総額を取得する
     *
     * @return 政党基金（支部基金）の運用により収受した果実の総額
     */
    public Long getFundsSumGain() {
        return fundsSumGain;
    }

    /**
     * 政党基金（支部基金）の運用により収受した果実の総額を設定する
     *
     * @param fundsSumGain 政党基金（支部基金）の運用により収受した果実の総額
     */
    public void setFundsSumGain(final Long fundsSumGain) {
        this.fundsSumGain = fundsSumGain;
    }

    /** 本年末等政党基金（支部基金）残高 */
    @Column(name = "this_year_rest")
    private Long thisYearRest = INIT_Long;

    /**
     * 本年末等政党基金（支部基金）残高を取得する
     *
     * @return 本年末等政党基金（支部基金）残高
     */
    public Long getThisYearRest() {
        return thisYearRest;
    }

    /**
     * 本年末等政党基金（支部基金）残高を設定する
     *
     * @param thisYearRest 本年末等政党基金（支部基金）残高
     */
    public void setThisYearRest(final Long thisYearRest) {
        this.thisYearRest = thisYearRest;
    }

    /** 備 考 */
    @Column(name = "bikou_differ")
    private Long bikouDiffer = INIT_Long;

    /**
     * 備 考を取得する
     *
     * @return 備 考
     */
    public Long getBikouDiffer() {
        return bikouDiffer;
    }

    /**
     * 備 考を設定する
     *
     * @param bikouDiffer 備 考
     */
    public void setBikouDiffer(final Long bikouDiffer) {
        this.bikouDiffer = bikouDiffer;
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
