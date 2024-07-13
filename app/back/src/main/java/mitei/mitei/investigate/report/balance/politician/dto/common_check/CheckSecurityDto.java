package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

/**
 * セキュリティチェック条件Dto
 * TODO 仕様が決定次第修正する
 */
public class CheckSecurityDto implements Serializable { // NOPMD DataClass

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 他機能確認用Mock強制例外発生フラグ */
    private Boolean isRaiseExcception;

    /** 他機能確認用Mock判定結果 */
    private Boolean isResult;

    
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
}
