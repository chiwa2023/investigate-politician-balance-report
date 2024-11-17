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

    /**
     * タスクとユーザ別でメール通知計画を登録する
     *
     * @param privilegeDto  権限確認Dto
     * @param datetimeShori 処理日時
     * @param userEntity    操作ユーザ情報
     * @param listSend      送信ユーザリスト
     * @param listTask      タスクリスト
     */
    public int practice(final CheckPrivilegeDto privilegeDto, final LocalDateTime datetimeShori,
            final UserWebAccessEntity userEntity, final List<UserWebAccessEntity> listSend,
            final List<TaskInfoEntity> listTask) {

        List<SendAlertMail2022Entity> list = new ArrayList<>();

        // タスクとユーザでループする
        for (TaskInfoEntity taskEntity : listTask) {
            List<UserWebAccessEntity> listPickup = null;
            
            for (UserWebAccessEntity sendEntity : listPickup) {
                list.add(this.createMailEntity(privilegeDto, userEntity, datetimeShori, sendEntity, taskEntity));
            }
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

    private SendAlertMail2022Entity createMailEntity(final CheckPrivilegeDto privilegeDto,
            final UserWebAccessEntity userEntity, final LocalDateTime datetimeShori,
            final UserWebAccessEntity sendEntity, final TaskInfoEntity taskInfoEntity) {

        SendAlertMail2022Entity entity = new SendAlertMail2022Entity();

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        // 送信基礎情報
        entity.setIsRepeat(false); // 再送信はしていない
        entity.setSendDatetime(datetimeShori); // 処理日時

        // 送信相手情報
        entity.setSendUserId(sendEntity.getUserId());
        entity.setSendUserCode(sendEntity.getUserCode());
        entity.setSendUserName(sendEntity.getUserName());
        entity.setToMail(sendEntity.getMailAddress()); // 相手先メールアドレス

        // 送付者情報
        entity.setFromMail(userEntity.getMailAddress()); // 送信元メールアドレス・・・操作者メールアドレス
        entity.setReplyToMail(userEntity.getMailAddress()); // 返信先・・・操作者メールアドレス

        // 入力の必要がないので空文字=初期値のまま
        // entity.setBccMail("");
        // entity.setCcMail("");

        // メッセージ内容
        entity.setSubjectMail(taskInfoEntity.getTaskInfoName()); // タイトル
        entity.setBodyTextMail(createSendMessageLogic.practice(sendEntity.getUserName(), taskInfoEntity));

        // auto incrementのため0(初期値0ではあるが明記)
        entity.setSendAlertMailId(0L);

        return entity;
    }

}
