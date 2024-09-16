package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 収支報告書自然検索結果格納Dto
 */
public class IncomeAndOutcomeNaturalSearchResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;
    
    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;


    /** 支出検索結果リスト */
    private List<IncomeAndOutcomeSearchLineDto> listOutcome = new ArrayList<>();

    /** 支出検索結果リスト */
    private List<IncomeAndOutcomeSearchLineDto> listIncome = new ArrayList<>();

    /**
     * 支出検索結果リストを取得する
     *
     * @return 支出検索結果リスト
     */
    public List<IncomeAndOutcomeSearchLineDto> getListOutcome() {
        return listOutcome;
    }

    /**
     * 支出検索結果リストを設定する
     *
     * @param listOutcome 支出検索結果リスト
     */
    public void setListOutcome(final List<IncomeAndOutcomeSearchLineDto> listOutcome) {
        this.listOutcome = listOutcome;
    }

    /**
     * 支出検索結果リストを取得する
     *
     * @return 支出検索結果リスト
     */
    public List<IncomeAndOutcomeSearchLineDto> getListIncome() {
        return listIncome;
    }

    /**
     * 支出検索結果リストを設定する
     *
     * @param listIncome 支出検索結果リスト
     */
    public void setListIncome(final List<IncomeAndOutcomeSearchLineDto> listIncome) {
        this.listIncome = listIncome;
    }

    /** 収入検索全体件数 */
    private int countIncome = INIT_Integer;

    /** 支出検索全体件数 */
    private int countOutcome = INIT_Integer;

    /**
     * 収入検索全体件数を取得する
     *
     * @return 収入検索全体件数
     */
    public int getCountIncome() {
        return countIncome;
    }

    /**
     * 収入検索全体件数を設定する
     *
     * @param countIncome 収入検索全体件数
     */
    public void setCountIncome(final int countIncome) {
        this.countIncome = countIncome;
    }

    /**
     * 支出検索全体件数を取得する
     *
     * @return 支出検索全体件数
     */
    public int getCountOutcome() {
        return countOutcome;
    }

    /**
     * 支出検索全体件数を設定する
     *
     * @param countOutcome 支出検索全体件数
     */
    public void setCountOutcome(final int countOutcome) {
        this.countOutcome = countOutcome;
    }

    
    
}
