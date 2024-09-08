package mitei.mitei.investigate.report.balance.politician.dto;

/**
 * 処理結果を返すフレームワークを実装するための抽象Dto
 */
public abstract class AbstractResultDto { // NOPMD DataClass

    /** 全処理終了フラグ */
    private Boolean isOk;

    /** 実施成功件数 */
    private Integer successCount;

    /** 実施失敗件数 */
    //基本transaction処理なので使用範囲はかなり限定される
    private Integer failureCount;


    /**
     * 全処理終了フラグを取得する
     *
     * @return 全処理終了フラグ
     */
    public Boolean getIsOk() {
        return isOk;
    }

    /**
     * 全処理終了フラグを設定する
     *
     * @param isOk 全処理終了フラグ
     */
    public void setIsOk(final Boolean isOk) {
        this.isOk = isOk;
    }

    /**
     * 実施成功件数を取得する
     *
     * @return 実施成功件数
     */
    public Integer getSuccessCount() {
        return successCount;
    }

    /**
     * 実施成功件数を設定する
     *
     * @param successCount 実施成功件数
     */
    public void setSuccessCount(final Integer successCount) {
        this.successCount = successCount;
    }

    /**
     * 実施失敗件数を取得する
     *
     * @return 実施失敗件数
     */
    public Integer getFailureCount() {
        return failureCount;
    }

    /**
     * 実施失敗件数を設定する
     *
     * @param failureCount 実施失敗件数
     */
    public void setFailureCount(final Integer failureCount) {
        this.failureCount = failureCount;
    }

    /** メッセージを取得する */
    abstract public String getMessage();
    
    /** メッセージを設定する */
    abstract public void setMessage(String message);

}
