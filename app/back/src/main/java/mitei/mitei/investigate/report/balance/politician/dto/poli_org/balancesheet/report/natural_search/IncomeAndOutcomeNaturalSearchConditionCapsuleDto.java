package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search;

import java.io.Serializable;
import java.time.LocalDate;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 収支報告書自然検索条件格納Dto
 */
public class IncomeAndOutcomeNaturalSearchConditionCapsuleDto extends AbstractCapsuleDto implements Serializable { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 検索語(ユーザ指定) */
    private String userKeyWords = INIT_String;

    /** 実際にRepositoryに与える検索語 */
    private String searchWords = INIT_String;

    /** 収支報告書収入検索フラグ */
    private Boolean isSearchIncome = INIT_Boolean;

    /** 収支報告書支出検索フラグ */
    private Boolean isSearchOutcome = INIT_Boolean;

    /** 収支報告書収入検索結果開始位置 */
    private Integer offsetIncome = INIT_Integer;

    /** 収支報告書支出検索結果開始位置 */
    private Integer offsetOutcome = INIT_Integer;

    /** 収支報告検索開始日付 */
    private LocalDate startDate = INIT_LocalDate;

    /** 収支報告書終了日付 */
    private LocalDate endDate = INIT_LocalDate;

    /**
     * ユーザ指定検索語を取得する
     *
     * @return ユーザ指定検索語
     */
    public String getUserKeyWords() {
        return userKeyWords;
    }

    /**
     * ユーザ指定検索語を設定する
     *
     * @param userKeyWords ユーザ指定検索語
     */
    public void setUserKeyWords(final String userKeyWords) {
        this.userKeyWords = userKeyWords;
    }

    /**
     * 収支報告書収入検索フラグを取得する
     *
     * @return 収支報告書収入検索フラグ
     */
    public Boolean getIsSearchIncome() {
        return isSearchIncome;
    }

    /**
     * 収支報告書収入検索フラグを設定する
     *
     * @param isSearchIncome 収支報告書収入検索フラグ
     */
    public void setIsSearchIncome(final Boolean isSearchIncome) {
        this.isSearchIncome = isSearchIncome;
    }

    /**
     * 収支報告書支出検索フラグを取得する
     *
     * @return 収支報告書支出検索フラグ
     */
    public Boolean getIsSearchOutcome() {
        return isSearchOutcome;
    }

    /**
     * 収支報告書支出検索フラグを設定する
     *
     * @param isSearchOutcome 収支報告書支出検索フラグ
     */
    public void setIsSearchOutcome(final Boolean isSearchOutcome) {
        this.isSearchOutcome = isSearchOutcome;
    }

    /**
     * 収支報告書収入検索結果開始位置を取得する
     *
     * @return 収支報告書収入検索結果開始位置
     */
    public Integer getOffsetIncome() {
        return offsetIncome;
    }

    /**
     * 収支報告書収入検索結果開始位置を設定する
     *
     * @param offsetIncome 収支報告書収入検索結果開始位置
     */
    public void setOffsetIncome(final Integer offsetIncome) {
        this.offsetIncome = offsetIncome;
    }

    /**
     * 収支報告書支出検索結果開始位置を取得する
     *
     * @return 収支報告書支出検索結果開始位置
     */
    public Integer getOffsetOutcome() {
        return offsetOutcome;
    }

    /**
     * 収支報告書支出検索結果開始位置を設定する
     *
     * @param offsetOutcome 収支報告書支出検索結果開始位置
     */
    public void setOffsetOutcome(final Integer offsetOutcome) {
        this.offsetOutcome = offsetOutcome;
    }

    /**
     * 収支報告検索開始日付を取得する
     *
     * @return 収支報告検索開始日付
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * 収支報告検索開始日付を設定する
     *
     * @param startDate 収支報告検索開始日付
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 収支報告書終了日付を取得する
     *
     * @return 収支報告書終了日付
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * 収支報告書終了日付を設定する
     *
     * @param endDate 収支報告書終了日付
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * 検索語(実検索用)を取得する
     *
     * @return 検索語(実検索用)
     */
    public String getSearchWords() {
        return searchWords;
    }

    /**
     * 検索語(実検索用)を設定する
     *
     * @param searchWords 検索語(実検索用)
     */
    public void setSearchWords(final String searchWords) {
        this.searchWords = searchWords;
    }

}
