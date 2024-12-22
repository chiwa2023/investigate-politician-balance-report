package mitei.mitei.investigate.report.balance.politician.dto.zengin_org_change;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 全銀金融機関支店検索条件Dto
 */
public class SearchZenginChangeConditionCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 未処理フラグ */
    private boolean isMishoriOnly = INIT_Boolean; // NOPMD

    /** 検索期間開始 */
    private LocalDate startDate = INIT_LocalDate;

    /** 検索期間終了 */
    private LocalDate endDate = INIT_LocalDate;

    /** 金融機関コード */
    private String financialCode = INIT_String;

    /** 該当変更区分リスト */
    private List<Integer> listChangeKbn = new ArrayList<>();

    /**
     * 未処理フラグを取得する
     *
     * @return 未処理フラグ
     */
    public boolean isMishoriOnly() {
        return isMishoriOnly;
    }

    /**
     * 未処理フラグを設定する
     *
     * @param isMishoriOnly 未処理フラグ
     */
    public void setMishoriOnly(final boolean isMishoriOnly) {
        this.isMishoriOnly = isMishoriOnly;
    }

    /**
     * 検索期間開始を取得する
     *
     * @return 検索期間開始
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * 検索期間開始を設定する
     *
     * @param startDate 検索期間開始
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 検索期間終了を取得する
     *
     * @return 検索期間終了
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * 検索期間終了を設定する
     *
     * @param endDate 検索期間終了
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * 金融機関コードを取得する
     *
     * @return 金融機関コード
     */
    public String getFinancialCode() {
        return financialCode;
    }

    /**
     * 金融機関コードを設定する
     *
     * @param financialCode 金融機関コード
     */
    public void setFinancialCode(final String financialCode) {
        this.financialCode = financialCode;
    }

    /**
     * 該当変更区分リストを取得する
     *
     * @return 該当変更区分リスト
     */
    public List<Integer> getListChangeKbn() {
        return listChangeKbn;
    }

    /**
     * 該当変更区分リストを設定する
     *
     * @param listChangeKbn 該当変更区分リスト
     */
    public void setListChangeKbn(final List<Integer> listChangeKbn) {
        this.listChangeKbn = listChangeKbn;
    }

}
