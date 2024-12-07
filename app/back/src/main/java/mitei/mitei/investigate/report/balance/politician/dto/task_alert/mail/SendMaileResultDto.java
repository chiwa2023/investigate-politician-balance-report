package mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * メール送信結果を格納する
 */
public class SendMaileResultDto extends AbstractResultDto implements Serializable { // NOPMD DataClass

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

    /** 送信失敗者 */
    private List<MailDataDto> listFailure = new ArrayList<>();

    /**
     * 送信失敗者リストを取得する
     *
     * @return 送信失敗者リスト
     */
    public List<MailDataDto> getListFailure() {
        return listFailure;
    }

    /**
     * 送信失敗者リストを設定する
     *
     * @param listFailure 送信失敗者リスト
     */
    public void setListFailure(final List<MailDataDto> listFailure) {
        this.listFailure = listFailure;
    }

}
