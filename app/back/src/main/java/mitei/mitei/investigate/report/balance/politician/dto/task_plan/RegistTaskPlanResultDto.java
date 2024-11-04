package mitei.mitei.investigate.report.balance.politician.dto.task_plan;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * タスク計画登録結果Dto
 */
public class RegistTaskPlanResultDto extends AbstractResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** メッセージ */
    private String message;

    /**
     * メッセージを取得する
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * メッセージを設定する
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

}
