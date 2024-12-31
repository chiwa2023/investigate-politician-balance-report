package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * 不記載検出検索結果統合Dto
 */
public class SearchFukisaiBalancesheetResultDto extends AbstractResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 取引相手数 */
    private Integer countAll;

    /** ページ数 */
    private Integer pageNumber;

    /** 取引グループリスト */
    private List<FukisaiTradingInfoDto> listTradingGroup = new ArrayList<>();

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

    /**
     * 取引グループリストを取得する
     *
     * @return 取引グループリスト
     */
    public List<FukisaiTradingInfoDto> getListTradingGroup() {
        return listTradingGroup;
    }

    /**
     * 取引グループリストを設定する
     *
     * @param listTradingGroup 取引グループリスト
     */
    public void setListTradingGroup(final List<FukisaiTradingInfoDto> listTradingGroup) {
        this.listTradingGroup = listTradingGroup;
    }

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 表示メッセージ */
    private String message = INIT_String;

    /**
     * 表示メッセージを取得する
     *
     * @return 表示メッセージ
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 表示メッセージを設定する
     *
     * @param message 表示メッセージ
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }
}
