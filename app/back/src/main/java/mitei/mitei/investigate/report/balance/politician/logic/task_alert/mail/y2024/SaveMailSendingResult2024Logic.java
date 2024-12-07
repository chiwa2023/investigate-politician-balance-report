package mitei.mitei.investigate.report.balance.politician.logic.task_alert.mail.y2024;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.mail.SendAlertMail2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.mail.SendAlertMail2024Repository;
import mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail.MailDataDto;
import mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail.SendMailAllPlanResultDto;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * メール送信処理結果を保存するLogic
 */
@Component
public class SaveMailSendingResult2024Logic {

    /** メール送信予定テーブルRepository(2024年) */
    @Autowired
    private SendAlertMail2024Repository sendAlertMail2024Repository;

    /**
     * 保存処理を行う
     *
     * @param datetTimeShori 処理日時
     * @param listPickup     抽出リスト
     * @param listFailure    送信失敗リスト
     * @return メール送信最終結果Dto
     */
    public SendMailAllPlanResultDto practice(final LocalDateTime datetTimeShori, final List<MailDataDto> listPickup,
            final List<MailDataDto> listFailure) {

        SendAlertMail2024Entity entity;
        Optional<SendAlertMail2024Entity> optional;

        List<SendAlertMail2024Entity> listUpdate = new ArrayList<>();
        // 送信試行リストはすべて更新=処理済に変更
        for (MailDataDto dto : listPickup) {
            optional = sendAlertMail2024Repository.findById(dto.getSendAlertMailId());
            if (optional.isPresent()) {
                entity = optional.get();
                SetTableDataHistoryUtil.practice(dto.getCheckPrivilegeDto(), entity, DataHistoryStatusConstants.UPDATE);
                listUpdate.add(entity);
            }
        }

        LocalDateTime datetimeRetry = LocalDateTime.of(datetTimeShori.toLocalDate(), datetTimeShori.toLocalTime());
        datetimeRetry = datetimeRetry.plusHours(12L); // SUPPRESS CHECKSTYLE MagicNumber 12時間を超えたら再トライ

        // メールが送信できなかった件のうち、初回のデータは再トライする
        List<SendAlertMail2024Entity> listInsert = new ArrayList<>();
        for (MailDataDto dto : listFailure) {
            optional = sendAlertMail2024Repository.findById(dto.getSendAlertMailId());
            if (optional.isPresent()) {
                entity = optional.get();
                // 初回試行でないもののみを再試行の対象とするので再試行フラグをたてて追加リストに置く
                if (Boolean.FALSE.equals(entity.getIsRepeat())) {
                    entity.setSendAlertMailId(0L); // 新規追加
                    SetTableDataHistoryUtil.practice(dto.getCheckPrivilegeDto(), entity,
                            DataHistoryStatusConstants.INSERT);
                    entity.setIsRepeat(true);
                    entity.setSendDatetime(datetimeRetry);
                    listInsert.add(entity);
                }
            }
        }

        sendAlertMail2024Repository.saveAll(listUpdate);
        sendAlertMail2024Repository.saveAll(listInsert);

        // TODO 再試行予定が立てらなかった場合の対応は別途検討する(例外で中断するなら例外をキャッチしてしまうことも視野)

        // 最終結果を送信する
        SendMailAllPlanResultDto resultDto = new SendMailAllPlanResultDto();
        int countFailure = listFailure.size();
        resultDto.setFailureCount(countFailure);
        int countSucccess = listPickup.size() - countFailure;
        resultDto.setSuccessCount(countSucccess);
        if (0 == countFailure) {
            resultDto.setIsOk(true);
        } else {
            resultDto.setIsOk(false);
        }

        return resultDto;
    }
}
