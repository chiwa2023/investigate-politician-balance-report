package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * メッセージとコードを伝達するResultDto
 */
public class TemplateWithTaskPlanInfoResultDto extends AbstractResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

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

    /** 取得コード */
    private Integer taskPlanCode = INIT_Integer;

    /** 登録年 */
    private Integer year = INIT_Integer;

    /**
     * タスク計画同一識別コード
     *
     * @return タスク計画同一識別コード
     */
    public Integer getTaskPlanCode() {
        return taskPlanCode;
    }

    /**
     * タスク計画同一識別コード
     *
     * @param taskPlanCode タスク計画同一識別コード
     */
    public void setTaskPlanCode(final Integer taskPlanCode) {
        this.taskPlanCode = taskPlanCode;
    }

    /**
     * 登録年
     *
     * @return 登録年
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 登録年
     *
     * @param year 登録年
     */
    public void setYear(final Integer year) {
        this.year = year;
    }

}
