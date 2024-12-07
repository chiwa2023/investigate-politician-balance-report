package mitei.mitei.investigate.report.balance.politician.logic.task_alert.mail.y2022;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.mail.SendAlertMail2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.mail.SendAlertMail2022Repository;
import mitei.mitei.investigate.report.balance.politician.logic.task_alert.mail.CallMailSendingInfoInterface;
import mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail.MailDataDto;
import mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail.SendMailCapsuleDto;

/**
 * メール送信予定を取得する(2022)
 */
@Component
public class CallMailSendingInfo2022Logic implements CallMailSendingInfoInterface {

    /** メール送信予定記録テーブル(2022) */
    @Autowired
    private SendAlertMail2022Repository sendAlertMail2022Repository;

    /**
     * 最新区分が最新・・・メール未送信データを取得する
     */
    @Override
    public SendMailCapsuleDto practice(final LocalDateTime localDateTime) {

        SendMailCapsuleDto capsuleDto = new SendMailCapsuleDto();

        // 最新区分かつ
        List<SendAlertMail2022Entity> listEntity = sendAlertMail2022Repository
                .findBySaishinKbnAndSendDatetimeBeforeOrderBySendAlertMailId(DataHistoryStatusConstants.INSERT.value(),
                        localDateTime);

        List<MailDataDto> list = new ArrayList<>();

        for (SendAlertMail2022Entity entity : listEntity) {
            list.add(this.createDto(entity));
        }

        capsuleDto.setListMailData(list);

        return capsuleDto;
    }

    /**
     * Entityから送信情報Dtoに変換する(BeanUtisがSQLの予約語とかなり重なっていて使いづらかった)
     *
     * @param entity 送信予定Entity
     * @return 送信予定Dto
     */
    private MailDataDto createDto(final SendAlertMail2022Entity entity) {

        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        privilegeDto.setLoginUserId(entity.getSendUserId());
        privilegeDto.setLoginUserCode(entity.getSendUserCode());
        privilegeDto.setLoginUserName(entity.getSendUserName());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(entity.getFromMail());
        message.setTo(entity.getToMail());
        message.setCc(entity.getCcMail());
        message.setBcc(entity.getBccMail());
        message.setReplyTo(entity.getReplyToMail());
        message.setSubject(entity.getSubjectMail());
        message.setText(entity.getBodyTextMail());

        MailDataDto dto = new MailDataDto();
        dto.setCheckPrivilegeDto(privilegeDto);
        dto.setSimpleMailMessage(message);

        dto.setSendAlertMailId(entity.getSendAlertMailId());
        dto.setSendAlertMailCode(entity.getSendAlertMailCode());
        dto.setIsRepeat(entity.getIsRepeat());

        return dto;
    }

}
