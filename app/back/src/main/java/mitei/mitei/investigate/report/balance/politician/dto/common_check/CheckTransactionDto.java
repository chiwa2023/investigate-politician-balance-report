package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

/**
 * 排他制御チェック条件Dto
 * TODO 仕様が決定次第修正する
 */
public class CheckTransactionDto implements Serializable { // NOPMD DataClass

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 他機能確認用Mock強制例外発生フラグ */
    private Boolean isRaiseExcception;

    /** 他機能確認用Mock判定結果 */
    private Boolean isResult;

    /** 照会専用フラグ(排他制御チェック不要) */
    private Boolean isSelectOnly;

    /**
     * 他機能確認用Mock強制例外発生フラグを取得する
     *
     * @return 他機能確認用Mock強制例外発生フラグ
     */
    public Boolean getIsRaiseExcception() {
        return isRaiseExcception;
    }

    /**
     * 他機能確認用Mock強制例外発生フラグを設定する
     *
     * @param isRaiseExcception 他機能確認用Mock強制例外発生フラグ
     */
    public void setIsRaiseExcception(final Boolean isRaiseExcception) {
        this.isRaiseExcception = isRaiseExcception;
    }

    /**
     * 他機能確認用Mock判定結果を取得する
     *
     * @return 他機能確認用Mock判定結果
     */
    public Boolean getIsResult() {
        return isResult;
    }

    /**
     * 他機能確認用Mock判定結果を設定する
     *
     * @param isResult 他機能確認用Mock判定結果
     */
    public void setIsResult(final Boolean isResult) {
        this.isResult = isResult;
    }

    /**
     * 照会専用フラグを取得する
     *
     * @return 照会専用フラグ
     */
    public Boolean getIsSelectOnly() {
        return isSelectOnly;
    }

    /**
     * 照会専用フラグを設定する
     *
     * @param isSelectOnly 照会専用フラグ
     */
    public void setIsSelectOnly(final Boolean isSelectOnly) {
        this.isSelectOnly = isSelectOnly;
    }


}
