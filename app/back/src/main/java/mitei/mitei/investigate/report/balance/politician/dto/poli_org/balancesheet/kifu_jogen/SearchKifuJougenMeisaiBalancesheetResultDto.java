package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 寄付上限明細検索結果Dto
 */
public class SearchKifuJougenMeisaiBalancesheetResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 取引相手数 */
    private Integer countAll;

    /** ページ数 */
    private Integer pageNumber;

    /** 取引グループリスト */
    private List<KifuJougenTradingInfoDto> listTradingGroup = new ArrayList<>();

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
     * @param pageNumber ページ数を取得する
     */
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 取引グループリストを取得する
     *
     * @return 取引グループリストを取得する
     */
    public List<KifuJougenTradingInfoDto> getListTradingGroup() {
        return listTradingGroup;
    }

    /**
     * 取引グループリストを設定する
     *
     * @param listTradingGroup 取引グループリスト
     */
    public void setListTradingGroup(final List<KifuJougenTradingInfoDto> listTradingGroup) {
        this.listTradingGroup = listTradingGroup;
    }

}
