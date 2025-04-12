package mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin;

import java.io.Serializable;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageOutcomeEntity;

/**
 * 交付金連結支出紐づけ失敗Dto
 */
public class RenketsuKoufukinFailureDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 取引相手数 */
    private Integer countAll;

    /** ページ数 */
    private Integer pageNumber;

    /**
     * 取引相手数を取得する
     *
     * @return 取引相手数
     */
    public Integer getCountAll() {
        return countAll;
    }

    /**
     * 取引相手数を設定する
     *
     * @param countAll 取引相手数
     */
    public void setCountAll(final Integer countAll) {
        this.countAll = countAll;
    }

    /**
     * ページ数を取得する
     *
     * @return ページ数
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ数を設定する
     *
     * @param pageNumber ページ数
     */
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** 1回取得件数 */
    private Integer offset;

    /**
     * 1回取得件数を取得する
     *
     * @return 1回取得件数
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 1回取得件数を設定する
     *
     * @param offset 1回取得件数
     */
    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    /** 紐づけ失敗リスト */
    private List<WkTblUsageOutcomeEntity> listFailure;

    /**
     * 紐づけ失敗リストを取得する
     *
     * @return 紐づけ失敗リスト
     */
    public List<WkTblUsageOutcomeEntity> getListFailure() {
        return listFailure;
    }

    /**
     * 紐づけ失敗リストを設定する
     *
     * @param listFailure 紐づけ失敗リスト
     */
    public void setListFailure(final List<WkTblUsageOutcomeEntity> listFailure) {
        this.listFailure = listFailure;
    }
}
