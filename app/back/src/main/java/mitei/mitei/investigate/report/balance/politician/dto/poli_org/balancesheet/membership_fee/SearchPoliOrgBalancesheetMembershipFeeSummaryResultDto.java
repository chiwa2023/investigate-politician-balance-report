package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee;

import java.util.List;

/**
 * 党費要約検索結果Dto
 */
public class SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto { // NOPMD DataClass

    /** 検索結果全件 */
    private long countAll;

    /** 検索結果を取得したページ数 */
    private long posPage;

    /** 個別の要約結果 */
    private List<PoliOrgFeeInsuDto> listSummary;

    /**
     * 検索結果全件を取得する
     *
     * @return 検索結果全件
     */
    public long getCountAll() {
        return countAll;
    }

    /**
     * 検索結果全件を設定する
     *
     * @param countAll 検索結果全件
     */
    public void setCountAll(final long countAll) {
        this.countAll = countAll;
    }

    /**
     * 検索結果を取得したページ数を取得する
     *
     * @return 検索結果を取得したページ数
     */
    public long getPosPage() {
        return posPage;
    }

    /**
     * 検索結果を取得したページ数を設定する
     *
     * @param posPage 検索結果を取得したページ数
     */
    public void setPosPage(final long posPage) {
        this.posPage = posPage;
    }

    /**
     * 個別の要約結果を取得する
     *
     * @return 個別の要約結果
     */
    public List<PoliOrgFeeInsuDto> getListSummary() {
        return listSummary;
    }

    /**
     * 個別の要約結果を設定する
     *
     * @param listSummary 個別の要約結果
     */
    public void setListSummary(final List<PoliOrgFeeInsuDto> listSummary) {
        this.listSummary = listSummary;
    }

}
