package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * 収支報告書自然検索結果格納Dto
 */
public class IncomeAndOutcomeNaturalSearchResultDto extends AbstractResultDto implements Serializable { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(String) */
    private static final String INIT_String = "";

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

    /** 処理メッセージ */
    private String message = INIT_String;

    /**
     * 処理メッセージを取得する
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 処理メッセージを設定する
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

}
