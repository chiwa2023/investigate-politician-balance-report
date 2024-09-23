package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 支出・収入の行データDto
 */
public class IncomeAndOutcomeSearchLineDto implements Serializable { // NOPMD DataClass

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

    // TODO 最初は最小限ではじめ、順次増やしていく

    /** 項目名称 */
    private String itemName = INIT_String;

    /**
     * 項目名称を取得する
     *
     * @return 項目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 項目名称を設定する
     *
     * @param itemName 項目名称
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /** 目的 */
    private String mokuteki = INIT_String;

    /**
     * 目的を取得する
     *
     * @return 目的
     */
    public String getMokuteki() {
        return mokuteki;
    }

    /**
     * 目的を設定する
     *
     * @param mokuteki 目的
     */
    public void setMokuteki(final String mokuteki) {
        this.mokuteki = mokuteki;
    }

    /** 金額 */
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

    /** 発生日 */
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

    /** 原文書政治団体代表者名 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
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

    /** 原文書政治団体名称 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
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

    /** 様式区分 */
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

    /** 支出した相手先名 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
    private String partnerName = INIT_String;

    /**
     * 支出した相手先名を取得する
     *
     * @return 支出した相手先名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 支出した相手先名を設定する
     *
     * @param partnerName 支出した相手先名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 支出した相手先住所 TODO コードを標準化ができ次第、文書由来でなくシステム由来のデータに切り替える */
    private String partnerJuusho = INIT_String;

    /**
     * 支出した相手先住所を取得する
     *
     * @return 支出した相手先住所
     */
    public String getPartnerJuusho() {
        return partnerJuusho;
    }

    /**
     * 支出した相手先住所を設定する
     *
     * @param partnerJuusho 支出した相手先住所
     */
    public void setPartnerJuusho(final String partnerJuusho) {
        this.partnerJuusho = partnerJuusho;
    }

    /** 金額表示テキスト収入 */
    private String kingakuIncomeText = INIT_String;

    /** 金額表示テキスト支出 */
    private String kingakuOutcomeText = INIT_String;

    /** 集計用金額 */
    private Long kingakuShuukei = INIT_Long;

    /**
     * 金額表示テキスト収入を取得する
     *
     * @return 金額表示テキスト収入
     */
    public String getKingakuIncomeText() {
        return kingakuIncomeText;
    }

    /**
     * 金額表示テキスト収入を設定する
     *
     * @param kingakuIncomeText 金額表示テキスト収入
     */
    public void setKingakuIncomeText(final String kingakuIncomeText) {
        this.kingakuIncomeText = kingakuIncomeText;
    }

    /**
     * 金額表示テキスト支出を取得する
     *
     * @return 金額表示テキスト支出
     */
    public String getKingakuOutcomeText() {
        return kingakuOutcomeText;
    }

    /**
     * 金額表示テキスト支出を設定する
     *
     * @param kingakuOutcomeText 金額表示テキスト支出
     */
    public void setKingakuOutcomeText(final String kingakuOutcomeText) {
        this.kingakuOutcomeText = kingakuOutcomeText;
    }

    /**
     * 金額集計用を取得する
     *
     * @return 金額集計用
     */
    public Long getKingakuShuukei() {
        return kingakuShuukei;
    }

    /**
     * 金額集計を用設定する
     *
     * @param kingakuShuukei 金額集計用
     */
    public void setKingakuShuukei(final Long kingakuShuukei) {
        this.kingakuShuukei = kingakuShuukei;
    }

}
