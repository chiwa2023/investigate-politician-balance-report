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
 * offering_balancesheet_0702_and_0713_and_0717_summary_2025接続用Entity
 */
@Entity
@Table(name = "offering_balancesheet_0702_and_0713_and_0717_summary_2025")
public class OfferingBalancesheet0702And0713And0717Summary2025Entity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

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

    /** 収支報告書様式7その2と13と17Id */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offering_balancesheet_0702_and_0713_and_0717_summary_id")
    private Long offeringBalancesheet0702And0713And0717SummaryId = INIT_Long;

    /**
     * 収支報告書様式7その2と13と17Idを取得する
     *
     * @return 収支報告書様式7その2と13と17Id
     */
    public Long getOfferingBalancesheet0702And0713And0717SummaryId() {
        return offeringBalancesheet0702And0713And0717SummaryId;
    }

    /**
     * 収支報告書様式7その2と13と17Idを設定する
     *
     * @param offeringBalancesheet0702And0713And0717SummaryId 収支報告書様式7その2と13と17Id
     */
    public void setOfferingBalancesheet0702And0713And0717SummaryId(final Long offeringBalancesheet0702And0713And0717SummaryId) {
        this.offeringBalancesheet0702And0713And0717SummaryId = offeringBalancesheet0702And0713And0717SummaryId;
    }

    /** 収支報告書様式7その2と13と17同一識別コード */
    @Column(name = "offering_balancesheet_0702_and_0713_and_0717_summary_code")
    private Long offeringBalancesheet0702And0713And0717SummaryCode = INIT_Long;

    /**
     * 収支報告書様式7その2と13と17同一識別コードを取得する
     *
     * @return 収支報告書様式7その2と13と17同一識別コード
     */
    public Long getOfferingBalancesheet0702And0713And0717SummaryCode() {
        return offeringBalancesheet0702And0713And0717SummaryCode;
    }

    /**
     * 収支報告書様式7その2と13と17同一識別コードを設定する
     *
     * @param offeringBalancesheet0702And0713And0717SummaryCode 収支報告書様式7その2と13と17同一識別コード
     */
    public void setOfferingBalancesheet0702And0713And0717SummaryCode(final Long offeringBalancesheet0702And0713And0717SummaryCode) {
        this.offeringBalancesheet0702And0713And0717SummaryCode = offeringBalancesheet0702And0713And0717SummaryCode;
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

    /** 収入総合計 */
    @Column(name = "shunyu_gokei")
    private Long shunyuGokei = INIT_Long;

    /**
     * 収入総合計を取得する
     *
     * @return 収入総合計
     */
    public Long getShunyuGokei() {
        return shunyuGokei;
    }

    /**
     * 収入総合計を設定する
     *
     * @param shunyuGokei 収入総合計
     */
    public void setShunyuGokei(final Long shunyuGokei) {
        this.shunyuGokei = shunyuGokei;
    }

    /** 前年繰り越し合計 */
    @Column(name = "zennen_kurikoshi")
    private Long zennenKurikoshi = INIT_Long;

    /**
     * 前年繰り越し合計を取得する
     *
     * @return 前年繰り越し合計
     */
    public Long getZennenKurikoshi() {
        return zennenKurikoshi;
    }

    /**
     * 前年繰り越し合計を設定する
     *
     * @param zennenKurikoshi 前年繰り越し合計
     */
    public void setZennenKurikoshi(final Long zennenKurikoshi) {
        this.zennenKurikoshi = zennenKurikoshi;
    }

    /** 本年収入合計 */
    @Column(name = "honnen_shunyu")
    private Long honnenShunyu = INIT_Long;

    /**
     * 本年収入合計を取得する
     *
     * @return 本年収入合計
     */
    public Long getHonnenShunyu() {
        return honnenShunyu;
    }

    /**
     * 本年収入合計を設定する
     *
     * @param honnenShunyu 本年収入合計
     */
    public void setHonnenShunyu(final Long honnenShunyu) {
        this.honnenShunyu = honnenShunyu;
    }

    /** 支出総合計 */
    @Column(name = "shishutsu_goukei")
    private Long shishutsuGoukei = INIT_Long;

    /**
     * 支出総合計を取得する
     *
     * @return 支出総合計
     */
    public Long getShishutsuGoukei() {
        return shishutsuGoukei;
    }

    /**
     * 支出総合計を設定する
     *
     * @param shishutsuGoukei 支出総合計
     */
    public void setShishutsuGoukei(final Long shishutsuGoukei) {
        this.shishutsuGoukei = shishutsuGoukei;
    }

    /** 翌年繰り越し合計 */
    @Column(name = "yokunen_kurikoshi")
    private Long yokunenKurikoshi = INIT_Long;

    /**
     * 翌年繰り越し合計を取得する
     *
     * @return 翌年繰り越し合計
     */
    public Long getYokunenKurikoshi() {
        return yokunenKurikoshi;
    }

    /**
     * 翌年繰り越し合計を設定する
     *
     * @param yokunenKurikoshi 翌年繰り越し合計
     */
    public void setYokunenKurikoshi(final Long yokunenKurikoshi) {
        this.yokunenKurikoshi = yokunenKurikoshi;
    }

    /** 個人の党費または会費を納入金額 */
    @Column(name = "koji_futan_goukei")
    private String kojiFutanGoukei = INIT_String;

    /**
     * 個人の党費または会費を納入金額を取得する
     *
     * @return 個人の党費または会費を納入金額
     */
    public String getKojiFutanGoukei() {
        return kojiFutanGoukei;
    }

    /**
     * 個人の党費または会費を納入金額を設定する
     *
     * @param kojiFutanGoukei 個人の党費または会費を納入金額
     */
    public void setKojiFutanGoukei(final String kojiFutanGoukei) {
        this.kojiFutanGoukei = kojiFutanGoukei;
    }

    /** 党費または会費を納入した員数 */
    @Column(name = "koji_futan_suu")
    private String kojiFutanSuu = INIT_String;

    /**
     * 党費または会費を納入した員数を取得する
     *
     * @return 党費または会費を納入した員数
     */
    public String getKojiFutanSuu() {
        return kojiFutanSuu;
    }

    /**
     * 党費または会費を納入した員数を設定する
     *
     * @param kojiFutanSuu 党費または会費を納入した員数
     */
    public void setKojiFutanSuu(final String kojiFutanSuu) {
        this.kojiFutanSuu = kojiFutanSuu;
    }

    /** 個人寄付の合計 */
    @Column(name = "kojin_kifu_goukei")
    private Long kojinKifuGoukei = INIT_Long;

    /**
     * 個人寄付の合計を取得する
     *
     * @return 個人寄付の合計
     */
    public Long getKojinKifuGoukei() {
        return kojinKifuGoukei;
    }

    /**
     * 個人寄付の合計を設定する
     *
     * @param kojinKifuGoukei 個人寄付の合計
     */
    public void setKojinKifuGoukei(final Long kojinKifuGoukei) {
        this.kojinKifuGoukei = kojinKifuGoukei;
    }

    /** 個人寄付備考 */
    @Column(name = "kojin_kifu_bikou")
    private String kojinKifuBikou = INIT_String;

    /**
     * 個人寄付備考を取得する
     *
     * @return 個人寄付備考
     */
    public String getKojinKifuBikou() {
        return kojinKifuBikou;
    }

    /**
     * 個人寄付備考を設定する
     *
     * @param kojinKifuBikou 個人寄付備考
     */
    public void setKojinKifuBikou(final String kojinKifuBikou) {
        this.kojinKifuBikou = kojinKifuBikou;
    }

    /** 特定寄付合計 */
    @Column(name = "tokutei_kifu_goukei")
    private Long tokuteiKifuGoukei = INIT_Long;

    /**
     * 特定寄付合計を取得する
     *
     * @return 特定寄付合計
     */
    public Long getTokuteiKifuGoukei() {
        return tokuteiKifuGoukei;
    }

    /**
     * 特定寄付合計を設定する
     *
     * @param tokuteiKifuGoukei 特定寄付合計
     */
    public void setTokuteiKifuGoukei(final Long tokuteiKifuGoukei) {
        this.tokuteiKifuGoukei = tokuteiKifuGoukei;
    }

    /** 特定寄付備考 */
    @Column(name = "tokutei_kifu_bikou")
    private String tokuteiKifuBikou = INIT_String;

    /**
     * 特定寄付備考を取得する
     *
     * @return 特定寄付備考
     */
    public String getTokuteiKifuBikou() {
        return tokuteiKifuBikou;
    }

    /**
     * 特定寄付備考を設定する
     *
     * @param tokuteiKifuBikou 特定寄付備考
     */
    public void setTokuteiKifuBikou(final String tokuteiKifuBikou) {
        this.tokuteiKifuBikou = tokuteiKifuBikou;
    }

    /** 法人寄付合計 */
    @Column(name = "houjin_kifu_goukei")
    private Long houjinKifuGoukei = INIT_Long;

    /**
     * 法人寄付合計を取得する
     *
     * @return 法人寄付合計
     */
    public Long getHoujinKifuGoukei() {
        return houjinKifuGoukei;
    }

    /**
     * 法人寄付合計を設定する
     *
     * @param houjinKifuGoukei 法人寄付合計
     */
    public void setHoujinKifuGoukei(final Long houjinKifuGoukei) {
        this.houjinKifuGoukei = houjinKifuGoukei;
    }

    /** 法人寄付備考 */
    @Column(name = "houjin_kifu_biko")
    private String houjinKifuBiko = INIT_String;

    /**
     * 法人寄付備考を取得する
     *
     * @return 法人寄付備考
     */
    public String getHoujinKifuBiko() {
        return houjinKifuBiko;
    }

    /**
     * 法人寄付備考を設定する
     *
     * @param houjinKifuBiko 法人寄付備考
     */
    public void setHoujinKifuBiko(final String houjinKifuBiko) {
        this.houjinKifuBiko = houjinKifuBiko;
    }

    /** 政治団体寄付合計 */
    @Column(name = "seiji_dantai_kifu_goukei")
    private Long seijiDantaiKifuGoukei = INIT_Long;

    /**
     * 政治団体寄付合計を取得する
     *
     * @return 政治団体寄付合計
     */
    public Long getSeijiDantaiKifuGoukei() {
        return seijiDantaiKifuGoukei;
    }

    /**
     * 政治団体寄付合計を設定する
     *
     * @param seijiDantaiKifuGoukei 政治団体寄付合計
     */
    public void setSeijiDantaiKifuGoukei(final Long seijiDantaiKifuGoukei) {
        this.seijiDantaiKifuGoukei = seijiDantaiKifuGoukei;
    }

    /** 政治団体寄付備考 */
    @Column(name = "seiji_dantai_kifu_bikou")
    private String seijiDantaiKifuBikou = INIT_String;

    /**
     * 政治団体寄付備考を取得する
     *
     * @return 政治団体寄付備考
     */
    public String getSeijiDantaiKifuBikou() {
        return seijiDantaiKifuBikou;
    }

    /**
     * 政治団体寄付備考を設定する
     *
     * @param seijiDantaiKifuBikou 政治団体寄付備考
     */
    public void setSeijiDantaiKifuBikou(final String seijiDantaiKifuBikou) {
        this.seijiDantaiKifuBikou = seijiDantaiKifuBikou;
    }

    /** 寄付小計合計 */
    @Column(name = "kifu_shoukei_goukei")
    private Long kifuShoukeiGoukei = INIT_Long;

    /**
     * 寄付小計合計を取得する
     *
     * @return 寄付小計合計
     */
    public Long getKifuShoukeiGoukei() {
        return kifuShoukeiGoukei;
    }

    /**
     * 寄付小計合計を設定する
     *
     * @param kifuShoukeiGoukei 寄付小計合計
     */
    public void setKifuShoukeiGoukei(final Long kifuShoukeiGoukei) {
        this.kifuShoukeiGoukei = kifuShoukeiGoukei;
    }

    /** 寄付小計備考 */
    @Column(name = "kifu_shoukei_bikou")
    private String kifuShoukeiBikou = INIT_String;

    /**
     * 寄付小計備考を取得する
     *
     * @return 寄付小計備考
     */
    public String getKifuShoukeiBikou() {
        return kifuShoukeiBikou;
    }

    /**
     * 寄付小計備考を設定する
     *
     * @param kifuShoukeiBikou 寄付小計備考
     */
    public void setKifuShoukeiBikou(final String kifuShoukeiBikou) {
        this.kifuShoukeiBikou = kifuShoukeiBikou;
    }

    /** あっせん合計 */
    @Column(name = "assen_goukei")
    private Long assenGoukei = INIT_Long;

    /**
     * あっせん合計を取得する
     *
     * @return あっせん合計
     */
    public Long getAssenGoukei() {
        return assenGoukei;
    }

    /**
     * あっせん合計を設定する
     *
     * @param assenGoukei あっせん合計
     */
    public void setAssenGoukei(final Long assenGoukei) {
        this.assenGoukei = assenGoukei;
    }

    /** あっせん備考 */
    @Column(name = "assen_bikou")
    private String assenBikou = INIT_String;

    /**
     * あっせん備考を取得する
     *
     * @return あっせん備考
     */
    public String getAssenBikou() {
        return assenBikou;
    }

    /**
     * あっせん備考を設定する
     *
     * @param assenBikou あっせん備考
     */
    public void setAssenBikou(final String assenBikou) {
        this.assenBikou = assenBikou;
    }

    /** 匿名寄附合計 */
    @Column(name = "tokumei_kifu_goukei")
    private Long tokumeiKifuGoukei = INIT_Long;

    /**
     * 匿名寄附合計を取得する
     *
     * @return 匿名寄附合計
     */
    public Long getTokumeiKifuGoukei() {
        return tokumeiKifuGoukei;
    }

    /**
     * 匿名寄附合計を設定する
     *
     * @param tokumeiKifuGoukei 匿名寄附合計
     */
    public void setTokumeiKifuGoukei(final Long tokumeiKifuGoukei) {
        this.tokumeiKifuGoukei = tokumeiKifuGoukei;
    }

    /** 匿名寄付備考 */
    @Column(name = "tokumei_kifu_bikou")
    private String tokumeiKifuBikou = INIT_String;

    /**
     * 匿名寄付備考を取得する
     *
     * @return 匿名寄付備考
     */
    public String getTokumeiKifuBikou() {
        return tokumeiKifuBikou;
    }

    /**
     * 匿名寄付備考を設定する
     *
     * @param tokumeiKifuBikou 匿名寄付備考
     */
    public void setTokumeiKifuBikou(final String tokumeiKifuBikou) {
        this.tokumeiKifuBikou = tokumeiKifuBikou;
    }

    /** 寄付総合計 */
    @Column(name = "kifu_so_goukei")
    private Long kifuSoGoukei = INIT_Long;

    /**
     * 寄付総合計を取得する
     *
     * @return 寄付総合計
     */
    public Long getKifuSoGoukei() {
        return kifuSoGoukei;
    }

    /**
     * 寄付総合計を設定する
     *
     * @param kifuSoGoukei 寄付総合計
     */
    public void setKifuSoGoukei(final Long kifuSoGoukei) {
        this.kifuSoGoukei = kifuSoGoukei;
    }

    /** 寄付総合計備考 */
    @Column(name = "kifu_so_goukei_bikou")
    private String kifuSoGoukeiBikou = INIT_String;

    /**
     * 寄付総合計備考を取得する
     *
     * @return 寄付総合計備考
     */
    public String getKifuSoGoukeiBikou() {
        return kifuSoGoukeiBikou;
    }

    /**
     * 寄付総合計備考を設定する
     *
     * @param kifuSoGoukeiBikou 寄付総合計備考
     */
    public void setKifuSoGoukeiBikou(final String kifuSoGoukeiBikou) {
        this.kifuSoGoukeiBikou = kifuSoGoukeiBikou;
    }

    /** 人件費合計 */
    @Column(name = "goukei_jinkenhi")
    private String goukeiJinkenhi = INIT_String;

    /**
     * 人件費合計を取得する
     *
     * @return 人件費合計
     */
    public String getGoukeiJinkenhi() {
        return goukeiJinkenhi;
    }

    /**
     * 人件費合計を設定する
     *
     * @param goukeiJinkenhi 人件費合計
     */
    public void setGoukeiJinkenhi(final String goukeiJinkenhi) {
        this.goukeiJinkenhi = goukeiJinkenhi;
    }

    /** 人件費のうち交付金に係る支出 */
    @Column(name = "kohfu_jinkenhi")
    private String kohfuJinkenhi = INIT_String;

    /**
     * 人件費のうち交付金に係る支出を取得する
     *
     * @return 人件費のうち交付金に係る支出
     */
    public String getKohfuJinkenhi() {
        return kohfuJinkenhi;
    }

    /**
     * 人件費のうち交付金に係る支出を設定する
     *
     * @param kohfuJinkenhi 人件費のうち交付金に係る支出
     */
    public void setKohfuJinkenhi(final String kohfuJinkenhi) {
        this.kohfuJinkenhi = kohfuJinkenhi;
    }

    /** 人件費備考 */
    @Column(name = "bikou_jinkenhi")
    private String bikouJinkenhi = INIT_String;

    /**
     * 人件費備考を取得する
     *
     * @return 人件費備考
     */
    public String getBikouJinkenhi() {
        return bikouJinkenhi;
    }

    /**
     * 人件費備考を設定する
     *
     * @param bikouJinkenhi 人件費備考
     */
    public void setBikouJinkenhi(final String bikouJinkenhi) {
        this.bikouJinkenhi = bikouJinkenhi;
    }

    /** 光熱費合計 */
    @Column(name = "goukei_kohnetsuhi")
    private String goukeiKohnetsuhi = INIT_String;

    /**
     * 光熱費合計を取得する
     *
     * @return 光熱費合計
     */
    public String getGoukeiKohnetsuhi() {
        return goukeiKohnetsuhi;
    }

    /**
     * 光熱費合計を設定する
     *
     * @param goukeiKohnetsuhi 光熱費合計
     */
    public void setGoukeiKohnetsuhi(final String goukeiKohnetsuhi) {
        this.goukeiKohnetsuhi = goukeiKohnetsuhi;
    }

    /** 光熱費のうち交付金に係る支出 */
    @Column(name = "kohfu_kohnetsuhi")
    private String kohfuKohnetsuhi = INIT_String;

    /**
     * 光熱費のうち交付金に係る支出を取得する
     *
     * @return 光熱費のうち交付金に係る支出
     */
    public String getKohfuKohnetsuhi() {
        return kohfuKohnetsuhi;
    }

    /**
     * 光熱費のうち交付金に係る支出を設定する
     *
     * @param kohfuKohnetsuhi 光熱費のうち交付金に係る支出
     */
    public void setKohfuKohnetsuhi(final String kohfuKohnetsuhi) {
        this.kohfuKohnetsuhi = kohfuKohnetsuhi;
    }

    /** 光熱費備考 */
    @Column(name = "bikou_kohnetsuhi")
    private String bikouKohnetsuhi = INIT_String;

    /**
     * 光熱費備考を取得する
     *
     * @return 光熱費備考
     */
    public String getBikouKohnetsuhi() {
        return bikouKohnetsuhi;
    }

    /**
     * 光熱費備考を設定する
     *
     * @param bikouKohnetsuhi 光熱費備考
     */
    public void setBikouKohnetsuhi(final String bikouKohnetsuhi) {
        this.bikouKohnetsuhi = bikouKohnetsuhi;
    }

    /** 備品合計 */
    @Column(name = "goukei_bihinhi")
    private String goukeiBihinhi = INIT_String;

    /**
     * 備品合計を取得する
     *
     * @return 備品合計
     */
    public String getGoukeiBihinhi() {
        return goukeiBihinhi;
    }

    /**
     * 備品合計を設定する
     *
     * @param goukeiBihinhi 備品合計
     */
    public void setGoukeiBihinhi(final String goukeiBihinhi) {
        this.goukeiBihinhi = goukeiBihinhi;
    }

    /** 備品のうち交付金に係る支出 */
    @Column(name = "kohfu_bihinhi")
    private String kohfuBihinhi = INIT_String;

    /**
     * 備品のうち交付金に係る支出を取得する
     *
     * @return 備品のうち交付金に係る支出
     */
    public String getKohfuBihinhi() {
        return kohfuBihinhi;
    }

    /**
     * 備品のうち交付金に係る支出を設定する
     *
     * @param kohfuBihinhi 備品のうち交付金に係る支出
     */
    public void setKohfuBihinhi(final String kohfuBihinhi) {
        this.kohfuBihinhi = kohfuBihinhi;
    }

    /** 備品備考 */
    @Column(name = "bikou_bihinhi")
    private String bikouBihinhi = INIT_String;

    /**
     * 備品備考を取得する
     *
     * @return 備品備考
     */
    public String getBikouBihinhi() {
        return bikouBihinhi;
    }

    /**
     * 備品備考を設定する
     *
     * @param bikouBihinhi 備品備考
     */
    public void setBikouBihinhi(final String bikouBihinhi) {
        this.bikouBihinhi = bikouBihinhi;
    }

    /** 事務所費合計 */
    @Column(name = "goukei_jimushohi")
    private String goukeiJimushohi = INIT_String;

    /**
     * 事務所費合計を取得する
     *
     * @return 事務所費合計
     */
    public String getGoukeiJimushohi() {
        return goukeiJimushohi;
    }

    /**
     * 事務所費合計を設定する
     *
     * @param goukeiJimushohi 事務所費合計
     */
    public void setGoukeiJimushohi(final String goukeiJimushohi) {
        this.goukeiJimushohi = goukeiJimushohi;
    }

    /** 事務所費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_jimushohi")
    private String kohfuJimushohi = INIT_String;

    /**
     * 事務所費合計のうち交付金に係る支出を取得する
     *
     * @return 事務所費合計のうち交付金に係る支出
     */
    public String getKohfuJimushohi() {
        return kohfuJimushohi;
    }

    /**
     * 事務所費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuJimushohi 事務所費合計のうち交付金に係る支出
     */
    public void setKohfuJimushohi(final String kohfuJimushohi) {
        this.kohfuJimushohi = kohfuJimushohi;
    }

    /** 事務所費備考 */
    @Column(name = "bikou_jimushohi")
    private String bikouJimushohi = INIT_String;

    /**
     * 事務所費備考を取得する
     *
     * @return 事務所費備考
     */
    public String getBikouJimushohi() {
        return bikouJimushohi;
    }

    /**
     * 事務所費備考を設定する
     *
     * @param bikouJimushohi 事務所費備考
     */
    public void setBikouJimushohi(final String bikouJimushohi) {
        this.bikouJimushohi = bikouJimushohi;
    }

    /** 経費項目の合計 */
    @Column(name = "goukei_keihi_shoukei")
    private Long goukeiKeihiShoukei = INIT_Long;

    /**
     * 経費項目の合計を取得する
     *
     * @return 経費項目の合計
     */
    public Long getGoukeiKeihiShoukei() {
        return goukeiKeihiShoukei;
    }

    /**
     * 経費項目の合計を設定する
     *
     * @param goukeiKeihiShoukei 経費項目の合計
     */
    public void setGoukeiKeihiShoukei(final Long goukeiKeihiShoukei) {
        this.goukeiKeihiShoukei = goukeiKeihiShoukei;
    }

    /** 経費の供与した交付金に係る支出 */
    @Column(name = "kohfu_keihi_shoukei")
    private Long kohfuKeihiShoukei = INIT_Long;

    /**
     * 経費の供与した交付金に係る支出を取得する
     *
     * @return 経費の供与した交付金に係る支出
     */
    public Long getKohfuKeihiShoukei() {
        return kohfuKeihiShoukei;
    }

    /**
     * 経費の供与した交付金に係る支出を設定する
     *
     * @param kohfuKeihiShoukei 経費の供与した交付金に係る支出
     */
    public void setKohfuKeihiShoukei(final Long kohfuKeihiShoukei) {
        this.kohfuKeihiShoukei = kohfuKeihiShoukei;
    }

    /** 経費の備考 */
    @Column(name = "bikou_keihi_shoukei")
    private String bikouKeihiShoukei = INIT_String;

    /**
     * 経費の備考を取得する
     *
     * @return 経費の備考
     */
    public String getBikouKeihiShoukei() {
        return bikouKeihiShoukei;
    }

    /**
     * 経費の備考を設定する
     *
     * @param bikouKeihiShoukei 経費の備考
     */
    public void setBikouKeihiShoukei(final String bikouKeihiShoukei) {
        this.bikouKeihiShoukei = bikouKeihiShoukei;
    }

    /** 経費項目の合計 */
    @Column(name = "goukei_sonota_keihi")
    private Long goukeiSonotaKeihi = INIT_Long;

    /**
     * 経費項目の合計を取得する
     *
     * @return 経費項目の合計
     */
    public Long getGoukeiSonotaKeihi() {
        return goukeiSonotaKeihi;
    }

    /**
     * 経費項目の合計を設定する
     *
     * @param goukeiSonotaKeihi 経費項目の合計
     */
    public void setGoukeiSonotaKeihi(final Long goukeiSonotaKeihi) {
        this.goukeiSonotaKeihi = goukeiSonotaKeihi;
    }

    /** 経費項目の合計のうち交付金に係る支出 */
    @Column(name = "kohfu_sonota_keihi")
    private String kohfuSonotaKeihi = INIT_String;

    /**
     * 経費項目の合計のうち交付金に係る支出を取得する
     *
     * @return 経費項目の合計のうち交付金に係る支出
     */
    public String getKohfuSonotaKeihi() {
        return kohfuSonotaKeihi;
    }

    /**
     * 経費項目の合計のうち交付金に係る支出を設定する
     *
     * @param kohfuSonotaKeihi 経費項目の合計のうち交付金に係る支出
     */
    public void setKohfuSonotaKeihi(final String kohfuSonotaKeihi) {
        this.kohfuSonotaKeihi = kohfuSonotaKeihi;
    }

    /** 経費項目の合計備考 */
    @Column(name = "bikou_sonota_keihi")
    private String bikouSonotaKeihi = INIT_String;

    /**
     * 経費項目の合計備考を取得する
     *
     * @return 経費項目の合計備考
     */
    public String getBikouSonotaKeihi() {
        return bikouSonotaKeihi;
    }

    /**
     * 経費項目の合計備考を設定する
     *
     * @param bikouSonotaKeihi 経費項目の合計備考
     */
    public void setBikouSonotaKeihi(final String bikouSonotaKeihi) {
        this.bikouSonotaKeihi = bikouSonotaKeihi;
    }

    /** 組織費合計 */
    @Column(name = "goukei_soshiki_katsudouhi")
    private Long goukeiSoshikiKatsudouhi = INIT_Long;

    /**
     * 組織費合計を取得する
     *
     * @return 組織費合計
     */
    public Long getGoukeiSoshikiKatsudouhi() {
        return goukeiSoshikiKatsudouhi;
    }

    /**
     * 組織費合計を設定する
     *
     * @param goukeiSoshikiKatsudouhi 組織費合計
     */
    public void setGoukeiSoshikiKatsudouhi(final Long goukeiSoshikiKatsudouhi) {
        this.goukeiSoshikiKatsudouhi = goukeiSoshikiKatsudouhi;
    }

    /** 組織費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_soshiki_katsudouhi")
    private String kohfuSoshikiKatsudouhi = INIT_String;

    /**
     * 組織費合計のうち交付金に係る支出を取得する
     *
     * @return 組織費合計のうち交付金に係る支出
     */
    public String getKohfuSoshikiKatsudouhi() {
        return kohfuSoshikiKatsudouhi;
    }

    /**
     * 組織費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuSoshikiKatsudouhi 組織費合計のうち交付金に係る支出
     */
    public void setKohfuSoshikiKatsudouhi(final String kohfuSoshikiKatsudouhi) {
        this.kohfuSoshikiKatsudouhi = kohfuSoshikiKatsudouhi;
    }

    /** 組織費合計備考 */
    @Column(name = "bikou_soshiki_katsudouhi")
    private String bikouSoshikiKatsudouhi = INIT_String;

    /**
     * 組織費合計備考を取得する
     *
     * @return 組織費合計備考
     */
    public String getBikouSoshikiKatsudouhi() {
        return bikouSoshikiKatsudouhi;
    }

    /**
     * 組織費合計備考を設定する
     *
     * @param bikouSoshikiKatsudouhi 組織費合計備考
     */
    public void setBikouSoshikiKatsudouhi(final String bikouSoshikiKatsudouhi) {
        this.bikouSoshikiKatsudouhi = bikouSoshikiKatsudouhi;
    }

    /** 選挙費合計 */
    @Column(name = "goukei_senkyo_katsudou")
    private Long goukeiSenkyoKatsudou = INIT_Long;

    /**
     * 選挙費合計を取得する
     *
     * @return 選挙費合計
     */
    public Long getGoukeiSenkyoKatsudou() {
        return goukeiSenkyoKatsudou;
    }

    /**
     * 選挙費合計を設定する
     *
     * @param goukeiSenkyoKatsudou 選挙費合計
     */
    public void setGoukeiSenkyoKatsudou(final Long goukeiSenkyoKatsudou) {
        this.goukeiSenkyoKatsudou = goukeiSenkyoKatsudou;
    }

    /** 選挙費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_senkyo_katsudou")
    private String kohfuSenkyoKatsudou = INIT_String;

    /**
     * 選挙費合計のうち交付金に係る支出を取得する
     *
     * @return 選挙費合計のうち交付金に係る支出
     */
    public String getKohfuSenkyoKatsudou() {
        return kohfuSenkyoKatsudou;
    }

    /**
     * 選挙費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuSenkyoKatsudou 選挙費合計のうち交付金に係る支出
     */
    public void setKohfuSenkyoKatsudou(final String kohfuSenkyoKatsudou) {
        this.kohfuSenkyoKatsudou = kohfuSenkyoKatsudou;
    }

    /** 選挙費合計備考 */
    @Column(name = "bikou_senkyo_katsudou")
    private String bikouSenkyoKatsudou = INIT_String;

    /**
     * 選挙費合計備考を取得する
     *
     * @return 選挙費合計備考
     */
    public String getBikouSenkyoKatsudou() {
        return bikouSenkyoKatsudou;
    }

    /**
     * 選挙費合計備考を設定する
     *
     * @param bikouSenkyoKatsudou 選挙費合計備考
     */
    public void setBikouSenkyoKatsudou(final String bikouSenkyoKatsudou) {
        this.bikouSenkyoKatsudou = bikouSenkyoKatsudou;
    }

    /** その他事業費合計 */
    @Column(name = "goukei_sonota_jigyou")
    private Long goukeiSonotaJigyou = INIT_Long;

    /**
     * その他事業費合計を取得する
     *
     * @return その他事業費合計
     */
    public Long getGoukeiSonotaJigyou() {
        return goukeiSonotaJigyou;
    }

    /**
     * その他事業費合計を設定する
     *
     * @param goukeiSonotaJigyou その他事業費合計
     */
    public void setGoukeiSonotaJigyou(final Long goukeiSonotaJigyou) {
        this.goukeiSonotaJigyou = goukeiSonotaJigyou;
    }

    /** その他事業費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_sonota_jigyou")
    private String kohfuSonotaJigyou = INIT_String;

    /**
     * その他事業費合計のうち交付金に係る支出を取得する
     *
     * @return その他事業費合計のうち交付金に係る支出
     */
    public String getKohfuSonotaJigyou() {
        return kohfuSonotaJigyou;
    }

    /**
     * その他事業費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuSonotaJigyou その他事業費合計のうち交付金に係る支出
     */
    public void setKohfuSonotaJigyou(final String kohfuSonotaJigyou) {
        this.kohfuSonotaJigyou = kohfuSonotaJigyou;
    }

    /** その他事業費合計備考 */
    @Column(name = "bikou_sonota_jigyou")
    private String bikouSonotaJigyou = INIT_String;

    /**
     * その他事業費合計備考を取得する
     *
     * @return その他事業費合計備考
     */
    public String getBikouSonotaJigyou() {
        return bikouSonotaJigyou;
    }

    /**
     * その他事業費合計備考を設定する
     *
     * @param bikouSonotaJigyou その他事業費合計備考
     */
    public void setBikouSonotaJigyou(final String bikouSonotaJigyou) {
        this.bikouSonotaJigyou = bikouSonotaJigyou;
    }

    /** 機関誌発行合計 */
    @Column(name = "goukei_hakkou")
    private Long goukeiHakkou = INIT_Long;

    /**
     * 機関誌発行合計を取得する
     *
     * @return 機関誌発行合計
     */
    public Long getGoukeiHakkou() {
        return goukeiHakkou;
    }

    /**
     * 機関誌発行合計を設定する
     *
     * @param goukeiHakkou 機関誌発行合計
     */
    public void setGoukeiHakkou(final Long goukeiHakkou) {
        this.goukeiHakkou = goukeiHakkou;
    }

    /** 機関誌発行合計のうち交付金に係る支出 */
    @Column(name = "kohfu_hakkou")
    private String kohfuHakkou = INIT_String;

    /**
     * 機関誌発行合計のうち交付金に係る支出を取得する
     *
     * @return 機関誌発行合計のうち交付金に係る支出
     */
    public String getKohfuHakkou() {
        return kohfuHakkou;
    }

    /**
     * 機関誌発行合計のうち交付金に係る支出を設定する
     *
     * @param kohfuHakkou 機関誌発行合計のうち交付金に係る支出
     */
    public void setKohfuHakkou(final String kohfuHakkou) {
        this.kohfuHakkou = kohfuHakkou;
    }

    /** 機関誌発行合計備考 */
    @Column(name = "bikou_hakkou")
    private String bikouHakkou = INIT_String;

    /**
     * 機関誌発行合計備考を取得する
     *
     * @return 機関誌発行合計備考
     */
    public String getBikouHakkou() {
        return bikouHakkou;
    }

    /**
     * 機関誌発行合計備考を設定する
     *
     * @param bikouHakkou 機関誌発行合計備考
     */
    public void setBikouHakkou(final String bikouHakkou) {
        this.bikouHakkou = bikouHakkou;
    }

    /** 宣伝広告費合計 */
    @Column(name = "goukei_senden")
    private Long goukeiSenden = INIT_Long;

    /**
     * 宣伝広告費合計を取得する
     *
     * @return 宣伝広告費合計
     */
    public Long getGoukeiSenden() {
        return goukeiSenden;
    }

    /**
     * 宣伝広告費合計を設定する
     *
     * @param goukeiSenden 宣伝広告費合計
     */
    public void setGoukeiSenden(final Long goukeiSenden) {
        this.goukeiSenden = goukeiSenden;
    }

    /** 宣伝広告費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_senden")
    private String kohfuSenden = INIT_String;

    /**
     * 宣伝広告費合計のうち交付金に係る支出を取得する
     *
     * @return 宣伝広告費合計のうち交付金に係る支出
     */
    public String getKohfuSenden() {
        return kohfuSenden;
    }

    /**
     * 宣伝広告費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuSenden 宣伝広告費合計のうち交付金に係る支出
     */
    public void setKohfuSenden(final String kohfuSenden) {
        this.kohfuSenden = kohfuSenden;
    }

    /** 宣伝広告費合計備考 */
    @Column(name = "bikou_senden")
    private String bikouSenden = INIT_String;

    /**
     * 宣伝広告費合計備考を取得する
     *
     * @return 宣伝広告費合計備考
     */
    public String getBikouSenden() {
        return bikouSenden;
    }

    /**
     * 宣伝広告費合計備考を設定する
     *
     * @param bikouSenden 宣伝広告費合計備考
     */
    public void setBikouSenden(final String bikouSenden) {
        this.bikouSenden = bikouSenden;
    }

    /** パーティ開催費合計 */
    @Column(name = "goukei_kaisai_pty")
    private Long goukeiKaisaiPty = INIT_Long;

    /**
     * パーティ開催費合計を取得する
     *
     * @return パーティ開催費合計
     */
    public Long getGoukeiKaisaiPty() {
        return goukeiKaisaiPty;
    }

    /**
     * パーティ開催費合計を設定する
     *
     * @param goukeiKaisaiPty パーティ開催費合計
     */
    public void setGoukeiKaisaiPty(final Long goukeiKaisaiPty) {
        this.goukeiKaisaiPty = goukeiKaisaiPty;
    }

    /** パーティ開催費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_kaisai_pty")
    private String kohfuKaisaiPty = INIT_String;

    /**
     * パーティ開催費合計のうち交付金に係る支出を取得する
     *
     * @return パーティ開催費合計のうち交付金に係る支出
     */
    public String getKohfuKaisaiPty() {
        return kohfuKaisaiPty;
    }

    /**
     * パーティ開催費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuKaisaiPty パーティ開催費合計のうち交付金に係る支出
     */
    public void setKohfuKaisaiPty(final String kohfuKaisaiPty) {
        this.kohfuKaisaiPty = kohfuKaisaiPty;
    }

    /** パーティ開催費合計備考 */
    @Column(name = "bikou_kaisai_pty")
    private String bikouKaisaiPty = INIT_String;

    /**
     * パーティ開催費合計備考を取得する
     *
     * @return パーティ開催費合計備考
     */
    public String getBikouKaisaiPty() {
        return bikouKaisaiPty;
    }

    /**
     * パーティ開催費合計備考を設定する
     *
     * @param bikouKaisaiPty パーティ開催費合計備考
     */
    public void setBikouKaisaiPty(final String bikouKaisaiPty) {
        this.bikouKaisaiPty = bikouKaisaiPty;
    }

    /** その他合計 */
    @Column(name = "goukei_sonota")
    private Long goukeiSonota = INIT_Long;

    /**
     * その他合計を取得する
     *
     * @return その他合計
     */
    public Long getGoukeiSonota() {
        return goukeiSonota;
    }

    /**
     * その他合計を設定する
     *
     * @param goukeiSonota その他合計
     */
    public void setGoukeiSonota(final Long goukeiSonota) {
        this.goukeiSonota = goukeiSonota;
    }

    /** その他合計のうち交付金に係る支出 */
    @Column(name = "kohfu_sonota")
    private Long kohfuSonota = INIT_Long;

    /**
     * その他合計のうち交付金に係る支出を取得する
     *
     * @return その他合計のうち交付金に係る支出
     */
    public Long getKohfuSonota() {
        return kohfuSonota;
    }

    /**
     * その他合計のうち交付金に係る支出を設定する
     *
     * @param kohfuSonota その他合計のうち交付金に係る支出
     */
    public void setKohfuSonota(final Long kohfuSonota) {
        this.kohfuSonota = kohfuSonota;
    }

    /** その他合計備考 */
    @Column(name = "bikou_sonota")
    private String bikouSonota = INIT_String;

    /**
     * その他合計備考を取得する
     *
     * @return その他合計備考
     */
    public String getBikouSonota() {
        return bikouSonota;
    }

    /**
     * その他合計備考を設定する
     *
     * @param bikouSonota その他合計備考
     */
    public void setBikouSonota(final String bikouSonota) {
        this.bikouSonota = bikouSonota;
    }

    /** 調査研究費合計 */
    @Column(name = "goukei_chousa_kenkyu")
    private Long goukeiChousaKenkyu = INIT_Long;

    /**
     * 調査研究費合計を取得する
     *
     * @return 調査研究費合計
     */
    public Long getGoukeiChousaKenkyu() {
        return goukeiChousaKenkyu;
    }

    /**
     * 調査研究費合計を設定する
     *
     * @param goukeiChousaKenkyu 調査研究費合計
     */
    public void setGoukeiChousaKenkyu(final Long goukeiChousaKenkyu) {
        this.goukeiChousaKenkyu = goukeiChousaKenkyu;
    }

    /** 調査研究費合計のうち交付金に係る支出 */
    @Column(name = "kohfu_chousa_kenkyu")
    private String kohfuChousaKenkyu = INIT_String;

    /**
     * 調査研究費合計のうち交付金に係る支出を取得する
     *
     * @return 調査研究費合計のうち交付金に係る支出
     */
    public String getKohfuChousaKenkyu() {
        return kohfuChousaKenkyu;
    }

    /**
     * 調査研究費合計のうち交付金に係る支出を設定する
     *
     * @param kohfuChousaKenkyu 調査研究費合計のうち交付金に係る支出
     */
    public void setKohfuChousaKenkyu(final String kohfuChousaKenkyu) {
        this.kohfuChousaKenkyu = kohfuChousaKenkyu;
    }

    /** 調査研究費合計備考 */
    @Column(name = "bikou_chousa_kenkyu")
    private String bikouChousaKenkyu = INIT_String;

    /**
     * 調査研究費合計備考を取得する
     *
     * @return 調査研究費合計備考
     */
    public String getBikouChousaKenkyu() {
        return bikouChousaKenkyu;
    }

    /**
     * 調査研究費合計備考を設定する
     *
     * @param bikouChousaKenkyu 調査研究費合計備考
     */
    public void setBikouChousaKenkyu(final String bikouChousaKenkyu) {
        this.bikouChousaKenkyu = bikouChousaKenkyu;
    }

    /** 寄附合計 */
    @Column(name = "goukei_kifukin")
    private Long goukeiKifukin = INIT_Long;

    /**
     * 寄附合計を取得する
     *
     * @return 寄附合計
     */
    public Long getGoukeiKifukin() {
        return goukeiKifukin;
    }

    /**
     * 寄附合計を設定する
     *
     * @param goukeiKifukin 寄附合計
     */
    public void setGoukeiKifukin(final Long goukeiKifukin) {
        this.goukeiKifukin = goukeiKifukin;
    }

    /** 寄附合計のうち交付金に係る支出 */
    @Column(name = "kohfu_kifukin")
    private String kohfuKifukin = INIT_String;

    /**
     * 寄附合計のうち交付金に係る支出を取得する
     *
     * @return 寄附合計のうち交付金に係る支出
     */
    public String getKohfuKifukin() {
        return kohfuKifukin;
    }

    /**
     * 寄附合計のうち交付金に係る支出を設定する
     *
     * @param kohfuKifukin 寄附合計のうち交付金に係る支出
     */
    public void setKohfuKifukin(final String kohfuKifukin) {
        this.kohfuKifukin = kohfuKifukin;
    }

    /** 寄附合計備考 */
    @Column(name = "bikou_kifukin")
    private String bikouKifukin = INIT_String;

    /**
     * 寄附合計備考を取得する
     *
     * @return 寄附合計備考
     */
    public String getBikouKifukin() {
        return bikouKifukin;
    }

    /**
     * 寄附合計備考を設定する
     *
     * @param bikouKifukin 寄附合計備考
     */
    public void setBikouKifukin(final String bikouKifukin) {
        this.bikouKifukin = bikouKifukin;
    }

    /** 活動費小計 */
    @Column(name = "goukei_katsudouhi")
    private Long goukeiKatsudouhi = INIT_Long;

    /**
     * 活動費小計を取得する
     *
     * @return 活動費小計
     */
    public Long getGoukeiKatsudouhi() {
        return goukeiKatsudouhi;
    }

    /**
     * 活動費小計を設定する
     *
     * @param goukeiKatsudouhi 活動費小計
     */
    public void setGoukeiKatsudouhi(final Long goukeiKatsudouhi) {
        this.goukeiKatsudouhi = goukeiKatsudouhi;
    }

    /** 活動費小計のうち交付金に係る支出 */
    @Column(name = "kohfu_katsudouhi")
    private Long kohfuKatsudouhi = INIT_Long;

    /**
     * 活動費小計のうち交付金に係る支出を取得する
     *
     * @return 活動費小計のうち交付金に係る支出
     */
    public Long getKohfuKatsudouhi() {
        return kohfuKatsudouhi;
    }

    /**
     * 活動費小計のうち交付金に係る支出を設定する
     *
     * @param kohfuKatsudouhi 活動費小計のうち交付金に係る支出
     */
    public void setKohfuKatsudouhi(final Long kohfuKatsudouhi) {
        this.kohfuKatsudouhi = kohfuKatsudouhi;
    }

    /** 活動費小計備考 */
    @Column(name = "bikou_katsudouhi")
    private String bikouKatsudouhi = INIT_String;

    /**
     * 活動費小計備考を取得する
     *
     * @return 活動費小計備考
     */
    public String getBikouKatsudouhi() {
        return bikouKatsudouhi;
    }

    /**
     * 活動費小計備考を設定する
     *
     * @param bikouKatsudouhi 活動費小計備考
     */
    public void setBikouKatsudouhi(final String bikouKatsudouhi) {
        this.bikouKatsudouhi = bikouKatsudouhi;
    }

    /** 総合計 */
    @Column(name = "goukei_zen_gohkei")
    private Long goukeiZenGohkei = INIT_Long;

    /**
     * 総合計を取得する
     *
     * @return 総合計
     */
    public Long getGoukeiZenGohkei() {
        return goukeiZenGohkei;
    }

    /**
     * 総合計を設定する
     *
     * @param goukeiZenGohkei 総合計
     */
    public void setGoukeiZenGohkei(final Long goukeiZenGohkei) {
        this.goukeiZenGohkei = goukeiZenGohkei;
    }

    /** 土地有無フラグ */
    @Column(name = "flg_tochi")
    private Integer flgTochi = INIT_Integer;

    /**
     * 土地有無フラグを取得する
     *
     * @return 土地有無フラグ
     */
    public Integer getFlgTochi() {
        return flgTochi;
    }

    /**
     * 土地有無フラグを設定する
     *
     * @param flgTochi 土地有無フラグ
     */
    public void setFlgTochi(final Integer flgTochi) {
        this.flgTochi = flgTochi;
    }

    /** 土地備考 */
    @Column(name = "bikou_tochi")
    private String bikouTochi = INIT_String;

    /**
     * 土地備考を取得する
     *
     * @return 土地備考
     */
    public String getBikouTochi() {
        return bikouTochi;
    }

    /**
     * 土地備考を設定する
     *
     * @param bikouTochi 土地備考
     */
    public void setBikouTochi(final String bikouTochi) {
        this.bikouTochi = bikouTochi;
    }

    /** 建物有無フラグ */
    @Column(name = "flg_tatemono")
    private Integer flgTatemono = INIT_Integer;

    /**
     * 建物有無フラグを取得する
     *
     * @return 建物有無フラグ
     */
    public Integer getFlgTatemono() {
        return flgTatemono;
    }

    /**
     * 建物有無フラグを設定する
     *
     * @param flgTatemono 建物有無フラグ
     */
    public void setFlgTatemono(final Integer flgTatemono) {
        this.flgTatemono = flgTatemono;
    }

    /** 建物備考 */
    @Column(name = "bikou_tatemono")
    private String bikouTatemono = INIT_String;

    /**
     * 建物備考を取得する
     *
     * @return 建物備考
     */
    public String getBikouTatemono() {
        return bikouTatemono;
    }

    /**
     * 建物備考を設定する
     *
     * @param bikouTatemono 建物備考
     */
    public void setBikouTatemono(final String bikouTatemono) {
        this.bikouTatemono = bikouTatemono;
    }

    /** 借地権有無フラグ */
    @Column(name = "flg_shakuchiken")
    private Integer flgShakuchiken = INIT_Integer;

    /**
     * 借地権有無フラグを取得する
     *
     * @return 借地権有無フラグ
     */
    public Integer getFlgShakuchiken() {
        return flgShakuchiken;
    }

    /**
     * 借地権有無フラグを設定する
     *
     * @param flgShakuchiken 借地権有無フラグ
     */
    public void setFlgShakuchiken(final Integer flgShakuchiken) {
        this.flgShakuchiken = flgShakuchiken;
    }

    /** 借地権備考 */
    @Column(name = "bikou_shakuchiken")
    private String bikouShakuchiken = INIT_String;

    /**
     * 借地権備考を取得する
     *
     * @return 借地権備考
     */
    public String getBikouShakuchiken() {
        return bikouShakuchiken;
    }

    /**
     * 借地権備考を設定する
     *
     * @param bikouShakuchiken 借地権備考
     */
    public void setBikouShakuchiken(final String bikouShakuchiken) {
        this.bikouShakuchiken = bikouShakuchiken;
    }

    /** 動産有無フラグ */
    @Column(name = "flg_dohsan")
    private Integer flgDohsan = INIT_Integer;

    /**
     * 動産有無フラグを取得する
     *
     * @return 動産有無フラグ
     */
    public Integer getFlgDohsan() {
        return flgDohsan;
    }

    /**
     * 動産有無フラグを設定する
     *
     * @param flgDohsan 動産有無フラグ
     */
    public void setFlgDohsan(final Integer flgDohsan) {
        this.flgDohsan = flgDohsan;
    }

    /** 動産備考 */
    @Column(name = "bikou_dohsan")
    private String bikouDohsan = INIT_String;

    /**
     * 動産備考を取得する
     *
     * @return 動産備考
     */
    public String getBikouDohsan() {
        return bikouDohsan;
    }

    /**
     * 動産備考を設定する
     *
     * @param bikouDohsan 動産備考
     */
    public void setBikouDohsan(final String bikouDohsan) {
        this.bikouDohsan = bikouDohsan;
    }

    /** 預金有無フラグ */
    @Column(name = "flg_yokin")
    private Integer flgYokin = INIT_Integer;

    /**
     * 預金有無フラグを取得する
     *
     * @return 預金有無フラグ
     */
    public Integer getFlgYokin() {
        return flgYokin;
    }

    /**
     * 預金有無フラグを設定する
     *
     * @param flgYokin 預金有無フラグ
     */
    public void setFlgYokin(final Integer flgYokin) {
        this.flgYokin = flgYokin;
    }

    /** 預金備考 */
    @Column(name = "bikou_yokin")
    private String bikouYokin = INIT_String;

    /**
     * 預金備考を取得する
     *
     * @return 預金備考
     */
    public String getBikouYokin() {
        return bikouYokin;
    }

    /**
     * 預金備考を設定する
     *
     * @param bikouYokin 預金備考
     */
    public void setBikouYokin(final String bikouYokin) {
        this.bikouYokin = bikouYokin;
    }

    /** 信託有無フラグ */
    @Column(name = "flg_shintaku")
    private Integer flgShintaku = INIT_Integer;

    /**
     * 信託有無フラグを取得する
     *
     * @return 信託有無フラグ
     */
    public Integer getFlgShintaku() {
        return flgShintaku;
    }

    /**
     * 信託有無フラグを設定する
     *
     * @param flgShintaku 信託有無フラグ
     */
    public void setFlgShintaku(final Integer flgShintaku) {
        this.flgShintaku = flgShintaku;
    }

    /** 信託備考 */
    @Column(name = "bikou_shintaku")
    private String bikouShintaku = INIT_String;

    /**
     * 信託備考を取得する
     *
     * @return 信託備考
     */
    public String getBikouShintaku() {
        return bikouShintaku;
    }

    /**
     * 信託備考を設定する
     *
     * @param bikouShintaku 信託備考
     */
    public void setBikouShintaku(final String bikouShintaku) {
        this.bikouShintaku = bikouShintaku;
    }

    /** 証券有無フラグ */
    @Column(name = "flg_shouken")
    private Integer flgShouken = INIT_Integer;

    /**
     * 証券有無フラグを取得する
     *
     * @return 証券有無フラグ
     */
    public Integer getFlgShouken() {
        return flgShouken;
    }

    /**
     * 証券有無フラグを設定する
     *
     * @param flgShouken 証券有無フラグ
     */
    public void setFlgShouken(final Integer flgShouken) {
        this.flgShouken = flgShouken;
    }

    /** 証券備考 */
    @Column(name = "bikou_shouken")
    private String bikouShouken = INIT_String;

    /**
     * 証券備考を取得する
     *
     * @return 証券備考
     */
    public String getBikouShouken() {
        return bikouShouken;
    }

    /**
     * 証券備考を設定する
     *
     * @param bikouShouken 証券備考
     */
    public void setBikouShouken(final String bikouShouken) {
        this.bikouShouken = bikouShouken;
    }

    /** 出資有無フラグ */
    @Column(name = "flg_shusshi")
    private Integer flgShusshi = INIT_Integer;

    /**
     * 出資有無フラグを取得する
     *
     * @return 出資有無フラグ
     */
    public Integer getFlgShusshi() {
        return flgShusshi;
    }

    /**
     * 出資有無フラグを設定する
     *
     * @param flgShusshi 出資有無フラグ
     */
    public void setFlgShusshi(final Integer flgShusshi) {
        this.flgShusshi = flgShusshi;
    }

    /** 出資備考 */
    @Column(name = "bikou_shusshi")
    private String bikouShusshi = INIT_String;

    /**
     * 出資備考を取得する
     *
     * @return 出資備考
     */
    public String getBikouShusshi() {
        return bikouShusshi;
    }

    /**
     * 出資備考を設定する
     *
     * @param bikouShusshi 出資備考
     */
    public void setBikouShusshi(final String bikouShusshi) {
        this.bikouShusshi = bikouShusshi;
    }

    /** 貸付有無フラグ */
    @Column(name = "flg_kashitsuke")
    private Integer flgKashitsuke = INIT_Integer;

    /**
     * 貸付有無フラグを取得する
     *
     * @return 貸付有無フラグ
     */
    public Integer getFlgKashitsuke() {
        return flgKashitsuke;
    }

    /**
     * 貸付有無フラグを設定する
     *
     * @param flgKashitsuke 貸付有無フラグ
     */
    public void setFlgKashitsuke(final Integer flgKashitsuke) {
        this.flgKashitsuke = flgKashitsuke;
    }

    /** 貸付備考 */
    @Column(name = "bikou_kashitsuke")
    private String bikouKashitsuke = INIT_String;

    /**
     * 貸付備考を取得する
     *
     * @return 貸付備考
     */
    public String getBikouKashitsuke() {
        return bikouKashitsuke;
    }

    /**
     * 貸付備考を設定する
     *
     * @param bikouKashitsuke 貸付備考
     */
    public void setBikouKashitsuke(final String bikouKashitsuke) {
        this.bikouKashitsuke = bikouKashitsuke;
    }

    /** 敷金有無フラグ */
    @Column(name = "flg_shikikin")
    private Integer flgShikikin = INIT_Integer;

    /**
     * 敷金有無フラグを取得する
     *
     * @return 敷金有無フラグ
     */
    public Integer getFlgShikikin() {
        return flgShikikin;
    }

    /**
     * 敷金有無フラグを設定する
     *
     * @param flgShikikin 敷金有無フラグ
     */
    public void setFlgShikikin(final Integer flgShikikin) {
        this.flgShikikin = flgShikikin;
    }

    /** 敷金備考 */
    @Column(name = "bikou_shikikin")
    private String bikouShikikin = INIT_String;

    /**
     * 敷金備考を取得する
     *
     * @return 敷金備考
     */
    public String getBikouShikikin() {
        return bikouShikikin;
    }

    /**
     * 敷金備考を設定する
     *
     * @param bikouShikikin 敷金備考
     */
    public void setBikouShikikin(final String bikouShikikin) {
        this.bikouShikikin = bikouShikikin;
    }

    /** 施設利用権有無フラグ */
    @Column(name = "flg_shisetsu_riyou")
    private Integer flgShisetsuRiyou = INIT_Integer;

    /**
     * 施設利用権有無フラグを取得する
     *
     * @return 施設利用権有無フラグ
     */
    public Integer getFlgShisetsuRiyou() {
        return flgShisetsuRiyou;
    }

    /**
     * 施設利用権有無フラグを設定する
     *
     * @param flgShisetsuRiyou 施設利用権有無フラグ
     */
    public void setFlgShisetsuRiyou(final Integer flgShisetsuRiyou) {
        this.flgShisetsuRiyou = flgShisetsuRiyou;
    }

    /** 施設利用権備考 */
    @Column(name = "bikou_shisetsu_riyou")
    private String bikouShisetsuRiyou = INIT_String;

    /**
     * 施設利用権備考を取得する
     *
     * @return 施設利用権備考
     */
    public String getBikouShisetsuRiyou() {
        return bikouShisetsuRiyou;
    }

    /**
     * 施設利用権備考を設定する
     *
     * @param bikouShisetsuRiyou 施設利用権備考
     */
    public void setBikouShisetsuRiyou(final String bikouShisetsuRiyou) {
        this.bikouShisetsuRiyou = bikouShisetsuRiyou;
    }

    /** 借入有無フラグ */
    @Column(name = "flg_kairiire")
    private Integer flgKairiire = INIT_Integer;

    /**
     * 借入有無フラグを取得する
     *
     * @return 借入有無フラグ
     */
    public Integer getFlgKairiire() {
        return flgKairiire;
    }

    /**
     * 借入有無フラグを設定する
     *
     * @param flgKairiire 借入有無フラグ
     */
    public void setFlgKairiire(final Integer flgKairiire) {
        this.flgKairiire = flgKairiire;
    }

    /** 借入備考 */
    @Column(name = "bikou_kariire")
    private String bikouKariire = INIT_String;

    /**
     * 借入備考を取得する
     *
     * @return 借入備考
     */
    public String getBikouKariire() {
        return bikouKariire;
    }

    /**
     * 借入備考を設定する
     *
     * @param bikouKariire 借入備考
     */
    public void setBikouKariire(final String bikouKariire) {
        this.bikouKariire = bikouKariire;
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
