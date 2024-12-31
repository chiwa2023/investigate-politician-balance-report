package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai;

import java.io.Serializable;

/**
 * 不記載検出ワークテーブルDto
 */
public class FukisaiSearchConditionDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 報告年 */
    private Integer houkokuNen;

    /** 政治団体同一識別コード */
    private Integer poliOrgCode;

    /** 政治団体原文書名 */
    private String dantaiName;

    /** 検索方式コード該否 */
    private Boolean isSearchCode;

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Integer getPoliOrgCode() {
        return poliOrgCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param poliOrgCode 政治団体同一識別コード
     */
    public void setPoliOrgCode(final Integer poliOrgCode) {
        this.poliOrgCode = poliOrgCode;
    }

    /**
     * 政治団体原文書名を取得する
     *
     * @return 政治団体原文書名
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 政治団体原文書名を設定する
     *
     * @param dantaiName 政治団体原文書名
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /**
     * 検索方式コード該否を取得する
     *
     * @return 検索方式コード該否
     */
    public Boolean getIsSearchCode() {
        return isSearchCode;
    }

    /**
     * 検索方式コード該否を設定する
     *
     * @param isSearchCode 検索方式コード該否
     */
    public void setIsSearchCode(final Boolean isSearchCode) {
        this.isSearchCode = isSearchCode;
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

}
