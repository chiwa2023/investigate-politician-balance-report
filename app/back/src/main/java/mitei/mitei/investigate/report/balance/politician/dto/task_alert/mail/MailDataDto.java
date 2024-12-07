package mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail;

import java.io.Serializable;

import org.springframework.mail.SimpleMailMessage;

import jakarta.persistence.Column;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;

/**
 * 各ユーザに送信するデータを格納するDto
 */
public class MailDataDto implements Serializable { // NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 権限確認Dto */
    private CheckPrivilegeDto checkPrivilegeDto;

    /** メールメッセージDto */
    private SimpleMailMessage simpleMailMessage;

    /**
     * 権限確認Dtoを取得する
     *
     * @return 権限確認Dto
     */
    public CheckPrivilegeDto getCheckPrivilegeDto() {
        return checkPrivilegeDto;
    }

    /**
     * 権限確認Dtoを設定する
     *
     * @param checkPrivilegeDto 権限確認Dto
     */
    public void setCheckPrivilegeDto(final CheckPrivilegeDto checkPrivilegeDto) {
        this.checkPrivilegeDto = checkPrivilegeDto;
    }

    /**
     * メール送信メッセージを取得する
     *
     * @return メール送信メッセージ
     */
    public SimpleMailMessage getSimpleMailMessage() {
        return simpleMailMessage;
    }

    /**
     * メール送信メッセージを設定する
     *
     * @param simpleMailMessage メール送信メッセージ
     */
    public void setSimpleMailMessage(final SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /** 項目名称取得Id */
    private Long sendAlertMailId = INIT_Long;

    /**
     * 項目名称取得Idを取得する
     *
     * @return 項目名称取得Id
     */
    public Long getSendAlertMailId() {
        return sendAlertMailId;
    }

    /**
     * 項目名称取得Idを設定する
     *
     * @param sendAlertMailId 項目名称取得Id
     */
    public void setSendAlertMailId(final Long sendAlertMailId) {
        this.sendAlertMailId = sendAlertMailId;
    }

    /** 項目名称取得同一識別コード */
    private Integer sendAlertMailCode = INIT_Integer;

    /**
     * 項目名称取得同一識別コードを取得する
     *
     * @return 項目名称取得同一識別コード
     */
    public Integer getSendAlertMailCode() {
        return sendAlertMailCode;
    }

    /**
     * 項目名称取得同一識別コードを設定する
     *
     * @param sendAlertMailCode 項目名称取得同一識別コード
     */
    public void setSendAlertMailCode(final Integer sendAlertMailCode) {
        this.sendAlertMailCode = sendAlertMailCode;
    }

    /** 再送信該否 */
    @Column(name = "is_repeat")
    private Boolean isRepeat = INIT_Boolean;

    /**
     * 再送信該否を取得する
     *
     * @return 再送信該否
     */
    public Boolean getIsRepeat() {
        return isRepeat;
    }

    /**
     * 再送信該否を設定する
     *
     * @param isRepeat 再送信該否
     */
    public void setIsRepeat(final Boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

}
