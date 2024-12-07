package mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * 次回の予定も含めたメール送信結果の登録結果を記録します
 */
public class SendMailAllPlanResultDto extends AbstractResultDto implements Serializable {

    /** SerialId */
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
