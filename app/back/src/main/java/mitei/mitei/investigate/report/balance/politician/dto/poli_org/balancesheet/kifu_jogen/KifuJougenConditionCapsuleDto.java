package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 寄付上限検索条件Dto
 */
public class KifuJougenConditionCapsuleDto extends AbstractCapsuleDto implements Serializable { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Boolean INIT_Boolean = false;

    /** 報告年 */
    private Integer houkokuNen = INIT_Integer;

    /** 政治団体同一識別コード */
    private Integer poliOrgCode = INIT_Integer;

    /** 検索基準原文書名該否 */
    private Boolean isNameSearch = INIT_Boolean;

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
     * 検索基準原文書名該否を取得する
     *
     * @return 検索基準原文書名該否
     */
    public Boolean getIsNameSearch() {
        return isNameSearch;
    }

    /**
     * 検索基準原文書名該否を設定する
     *
     * @param isNameSearch 検索基準原文書名該否
     */
    public void setIsNameSearch(final Boolean isNameSearch) {
        this.isNameSearch = isNameSearch;
    }

    /** 検索団体区分 */
    private Integer seachKifuKbn = INIT_Integer;

    /** 様式枝区分 */
    private Integer youshikiEdaKbn = INIT_Integer;

    /** オフセット件数 */
    private Integer offset = INIT_Integer;

    /** ページ番号 */
    private Integer pageNum = INIT_Integer;

    /**
     * 検索団体区分
     *
     * @return 検索団体区分
     */
    public Integer getSeachKifuKbn() {
        return seachKifuKbn;
    }

    /**
     * 検索団体区分
     *
     * @param seachKifuKbn 検索団体区分
     */
    public void setSeachKifuKbn(final Integer seachKifuKbn) {
        this.seachKifuKbn = seachKifuKbn;
    }

    /**
     * 様式枝区分
     *
     * @return 様式枝区分
     */
    public Integer getYoushikiEdaKbn() {
        return youshikiEdaKbn;
    }

    /**
     * 様式枝区分
     *
     * @param youshikiEdaKbn 様式枝区分
     */
    public void setYoushikiEdaKbn(final Integer youshikiEdaKbn) {
        this.youshikiEdaKbn = youshikiEdaKbn;
    }

    /**
     * オフセット件数
     *
     * @return オフセット件数
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * オフセット件数
     *
     * @param offset オフセット件数
     */
    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    /**
     * ページ番号
     *
     * @return ページ番号
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * ページ番号
     *
     * @param pageNum ページ番号
     */
    public void setPageNum(final Integer pageNum) {
        this.pageNum = pageNum;
    }

}
