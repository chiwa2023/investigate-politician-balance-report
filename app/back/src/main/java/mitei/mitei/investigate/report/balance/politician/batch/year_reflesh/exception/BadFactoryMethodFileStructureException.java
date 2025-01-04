package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.exception;

/**
 * 年次を束ねるファクトリメソッドロジックで自動年次追加処理を行うのに必要な条件(Memo)が不足している例外
 */
public class BadFactoryMethodFileStructureException extends Exception {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /**
     * 例外処理を行う
     *
     * @param message 例外メッセージ
     */
    public BadFactoryMethodFileStructureException(final String message) {
        super(message);
    }
}
