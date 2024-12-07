package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import java.util.Objects;

import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;

/**
 * メールとSNSダイレクトメッセージ送信本文作成Logic
 */
@Component
public class CreateSendMessageLogic {

    /** ユーザ名代替テキスト */
    public static final String REPLACE_USER_NAME = "【ユーザ名】";

    /** 遷移先代替テキスト */
    public static final String REPLACE_TRANSFER_PASS = "【遷移先】";

    /**
     * 本文作成処理を行う
     *
     * @param userName       ユーザ名
     * @param taskInfoEntity タスク情報
     * @return 本文
     */
    public String practice(final String userName, final TaskInfoEntity taskInfoEntity) throws IllegalArgumentException { // NOPMD

        if (Objects.isNull(taskInfoEntity)) {
            throw new IllegalArgumentException("引数のTaskInfoEntityがnullです");
        }

        // ユーザ名を置換
        String message = this.repairText(taskInfoEntity.getMessageTemplate());
        String user = this.repairText(userName);
        String body = message.replaceAll(REPLACE_USER_NAME, user);

        // 遷移先を置換
        String path = this.repairText(taskInfoEntity.getTransferPass())
                + this.repairText(taskInfoEntity.getParamQuery());
        return body.replaceAll(REPLACE_TRANSFER_PASS, path);
    }


    /** null対策(初期値を""で設定しているので、Entityを途中でnullにしていない限りほぼない) */
    private String repairText(final String data) {
        if (Objects.isNull(data)) {
            return "";
        } else {
            return data;
        }
    }

}
