package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 迂回献金キャッチャーワークテーブル作成条件Dto
 */
public class UkaiKenkinConditionCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 報告年 */
    private Integer houkokuNen = INIT_Integer;

    /** 政治団体id */
    private Long poliOrgId = INIT_Long;

    /** 政治団体同一識別コード */
    private Integer poliOrgCode = INIT_Integer;

    /** 政治団体名称 */
    private String poliOrgName = INIT_String;

    /** 検索基準原文書名該否 */
    private Boolean isNameSearch = INIT_Boolean;

    /** 抽出回数(想定階層) */
    private Integer pickupTimes = INIT_Integer;

    /** 交付金(様式5)検索有無 */
    private Boolean isKoufukin = INIT_Boolean;

    /** オフセット位置 */
    private Integer offset = INIT_Integer;

    /** ページ番号 */
    private Integer pageNum = INIT_Integer;

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
     * 政治団体idを取得する
     *
     * @return 政治団体id
     */
    public Long getPoliOrgId() {
        return poliOrgId;
    }

    /**
     * 政治団体idを設定する
     *
     * @param poliOrgId 政治団体id
     */
    public void setPoliOrgId(final Long poliOrgId) {
        this.poliOrgId = poliOrgId;
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
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getPoliOrgName() {
        return poliOrgName;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param poliOrgName 政治団体名称
     */
    public void setPoliOrgName(final String poliOrgName) {
        this.poliOrgName = poliOrgName;
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

    /**
     * 抽出回数(想定階層)を取得する
     *
     * @return 抽出回数(想定階層)
     */
    public Integer getPickupTimes() {
        return pickupTimes;
    }

    /**
     * 抽出回数(想定階層)を設定する
     *
     * @param pickupTimes 抽出回数(想定階層)
     */
    public void setPickupTimes(final Integer pickupTimes) {
        this.pickupTimes = pickupTimes;
    }

    /**
     * 交付金(様式5)検索有無を取得する
     *
     * @return 交付金(様式5)検索有無
     */
    public Boolean getIsKoufukin() {
        return isKoufukin;
    }

    /**
     * 交付金(様式5)検索有無を設定する
     *
     * @param isKoufukin 交付金(様式5)検索有無
     */
    public void setIsKoufukin(final Boolean isKoufukin) {
        this.isKoufukin = isKoufukin;
    }

    /**
     * オフセット位置を取得する
     *
     * @return オフセット位置
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * オフセット位置を設定する
     *
     * @param offset オフセット位置
     */
    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNum ページ番号
     */
    public void setPageNum(final Integer pageNum) {
        this.pageNum = pageNum;
    }

}
