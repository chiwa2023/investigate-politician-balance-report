package mitei.mitei.investigate.report.balance.politician.logic.task_alert.mail.y2022;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;
import mitei.mitei.investigate.report.balance.politician.entity.mail.SendAlertMail2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.mail.SendAlertMail2022Repository;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.CreateSendMessageLogic;
import mitei.mitei.investigate.report.balance.politician.logic.user_web_access.CreateTaskLevelListLogic;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * メール送信予約データを挿入する
 */
@Component
public class InsertMailInfo2022Logic {

    /** メッセージ本文作成Logic */
    @Autowired
    private CreateSendMessageLogic createSendMessageLogic;

    /** メール送信予定Repository(2022年) */
    @Autowired
    private SendAlertMail2022Repository sendAlertMail2022Repository;

    /** タスク水準からユーザリスト生成Logic */
    @Autowired
    private CreateTaskLevelListLogic createTaskLevelListLogic;

    /**
     * タスクとユーザ別でメール通知計画を登録する
     *
     * @param privilegeDto  権限確認Dto
     * @param datetimeShori 処理日時
     * @param userEntity    操作ユーザ情報
     * @param listTask      タスクリスト
     */
    public int practice(final CheckPrivilegeDto privilegeDto, final LocalDateTime datetimeShori,
            final UserWebAccessEntity userEntity, final List<TaskInfoEntity> listTask) {

        List<SendAlertMail2022Entity> list = new ArrayList<>();

        // タスクでループする
        for (TaskInfoEntity taskEntity : listTask) {
            // タスク水準リストを生成する
            list.add(this.createMailEntity(privilegeDto, userEntity, datetimeShori,
                    this.createBccList(createTaskLevelListLogic.practice(taskEntity.getTaskLevelList())), taskEntity));
        }

        // テーブルの同一識別コード最大値を取得
        Optional<SendAlertMail2022Entity> optional = sendAlertMail2022Repository
                .findFirstByOrderBySendAlertMailCodeDesc();
        Integer code = 0;
        if (!optional.isEmpty()) {
            code = code + optional.get().getSendAlertMailCode();
        }
        for (SendAlertMail2022Entity entity : list) {
            code++;
            entity.setSendAlertMailCode(code); // 同一識別コード取得
        }

        return sendAlertMail2022Repository.saveAll(list).size();
    }

    /* 登録行のデータを作成 */
    private SendAlertMail2022Entity createMailEntity(final CheckPrivilegeDto privilegeDto,
            final UserWebAccessEntity userEntity, final LocalDateTime datetimeShori, final String sendUserText,
            final TaskInfoEntity taskInfoEntity) {

        SendAlertMail2022Entity entity = new SendAlertMail2022Entity();

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        // 送信基礎情報
        entity.setIsRepeat(false); // 再送信はしていない
        entity.setSendDatetime(datetimeShori); // 処理日時

        // 送信相手情報は操作者宛メール(作業完了メールのような扱いになる)と対象ユーザをBCCで送信
        entity.setSendUserId(userEntity.getUserId());
        entity.setSendUserCode(userEntity.getUserCode());
        entity.setSendUserName(userEntity.getUserName());
        entity.setBccMail(sendUserText);
        entity.setToMail(userEntity.getMailAddress()); // 相手先メールアドレス

        // 送付者情報
        entity.setFromMail(userEntity.getMailAddress()); // 送信元メールアドレス・・・操作者メールアドレス
        entity.setReplyToMail(userEntity.getMailAddress()); // 返信先・・・操作者メールアドレス

        // 入力の必要がないので空文字=初期値のまま
        // entity.setCcMail("");

        // メッセージ内容
        entity.setSubjectMail(taskInfoEntity.getTaskInfoName()); // タイトル
        entity.setBodyTextMail(createSendMessageLogic.practice(userEntity.getUserName(), taskInfoEntity));

        // auto incrementのため0(初期値0ではあるが明記)
        entity.setSendAlertMailId(0L);

        return entity;
    }

    /* Bcc送信リストを作成 */
    private String createBccList(final List<UserWebAccessEntity> list) {
        final String blank = "";

        String listText = blank;
        // 操作ユーザ以外に同レベルの権限を持ったユーザがいないことは、運営上ありえないが実装上は存在するので対処
        if (list.isEmpty()) {
            return listText;
        }

        StringBuilder stringBuilder = new StringBuilder();
        String mail;
        for (UserWebAccessEntity accessEntity : list) {
            mail = accessEntity.getMailAddress();
            if (!blank.equals(mail)) {
                stringBuilder.append(accessEntity.getMailAddress()).append(',');
            }
        }

        // 最後の不要のカンマを除去
        listText = stringBuilder.toString();
        return listText.substring(0, listText.length() - 1);
    }

}
