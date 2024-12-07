package mitei.mitei.investigate.report.balance.politician.entity.mail;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * send_alert_mail_2024接続用Entity
 */
@Entity
@Table(name = "send_alert_mail_2024")
public class SendAlertMail2024Entity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_LocalDateTime = INIT_LocalDate.atTime(0, 0, 0);

    /** 項目名称取得Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "send_alert_mail_id")
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
    @Column(name = "send_alert_mail_code")
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

    /** 最新区分 */
    @Column(name = "saishin_kbn")
    private Integer saishinKbn = INIT_Integer;

    /**
     * 最新区分を取得する
     *
     * @return 最新区分
     */
    @Override
    public Integer getSaishinKbn() {
        return saishinKbn;
    }

    /**
     * 最新区分を設定する
     *
     * @param saishinKbn 最新区分
     */
    @Override
    public void setSaishinKbn(final Integer saishinKbn) {
        this.saishinKbn = saishinKbn;
    }

    /** 送信先ユーザId */
    @Column(name = "send_user_id")
    private Long sendUserId = INIT_Long;

    /**
     * 送信先ユーザIdを取得する
     *
     * @return 送信先ユーザId
     */
    public Long getSendUserId() {
        return sendUserId;
    }

    /**
     * 送信先ユーザIdを設定する
     *
     * @param sendUserId 送信先ユーザId
     */
    public void setSendUserId(final Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    /** 送信先ユーザ同一識別コード */
    @Column(name = "send_user_code")
    private Integer sendUserCode = INIT_Integer;

    /**
     * 送信先ユーザ同一識別コードを取得する
     *
     * @return 送信先ユーザ同一識別コード
     */
    public Integer getSendUserCode() {
        return sendUserCode;
    }

    /**
     * 送信先ユーザ同一識別コードを設定する
     *
     * @param sendUserCode 送信先ユーザ同一識別コード
     */
    public void setSendUserCode(final Integer sendUserCode) {
        this.sendUserCode = sendUserCode;
    }

    /** 送信先ユーザ名称 */
    @Column(name = "send_user_name")
    private String sendUserName = INIT_String;

    /**
     * 送信先ユーザ名称を取得する
     *
     * @return 送信先ユーザ名称
     */
    public String getSendUserName() {
        return sendUserName;
    }

    /**
     * 送信先ユーザ名称を設定する
     *
     * @param sendUserName 送信先ユーザ名称
     */
    public void setSendUserName(final String sendUserName) {
        this.sendUserName = sendUserName;
    }

    /** 送信予定時間 */
    @Column(name = "send_datetime")
    private LocalDateTime sendDatetime = INIT_LocalDateTime;

    /**
     * 送信予定時間を取得する
     *
     * @return 送信予定時間
     */
    public LocalDateTime getSendDatetime() {
        return sendDatetime;
    }

    /**
     * 送信予定時間を設定する
     *
     * @param sendDatetime 送信予定時間
     */
    public void setSendDatetime(final LocalDateTime sendDatetime) {
        this.sendDatetime = sendDatetime;
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

    /** 送信元メールアドレス */
    @Column(name = "from_mail")
    private String fromMail = INIT_String;

    /**
     * 送信元メールアドレスを取得する
     *
     * @return 送信元メールアドレス
     */
    public String getFromMail() {
        return fromMail;
    }

    /**
     * 送信元メールアドレスを設定する
     *
     * @param fromMail 送信元メールアドレス
     */
    public void setFromMail(final String fromMail) {
        this.fromMail = fromMail;
    }

    /** 宛先メールアドレス */
    @Column(name = "to_mail")
    private String toMail = INIT_String;

    /**
     * 宛先メールアドレスを取得する
     *
     * @return 宛先メールアドレス
     */
    public String getToMail() {
        return toMail;
    }

    /**
     * 宛先メールアドレスを設定する
     *
     * @param toMail 宛先メールアドレス
     */
    public void setToMail(final String toMail) {
        this.toMail = toMail;
    }

    /** ccメールアドレス */
    @Column(name = "cc_mail")
    private String ccMail = INIT_String;

    /**
     * ccメールアドレスを取得する
     *
     * @return ccメールアドレス
     */
    public String getCcMail() {
        return ccMail;
    }

    /**
     * ccメールアドレスを設定する
     *
     * @param ccMail ccメールアドレス
     */
    public void setCcMail(final String ccMail) {
        this.ccMail = ccMail;
    }

    /** bccメールアドレス */
    @Column(name = "bcc_mail")
    private String bccMail = INIT_String;

    /**
     * bccメールアドレスを取得する
     *
     * @return bccメールアドレス
     */
    public String getBccMail() {
        return bccMail;
    }

    /**
     * bccメールアドレスを設定する
     *
     * @param bccMail bccメールアドレス
     */
    public void setBccMail(final String bccMail) {
        this.bccMail = bccMail;
    }

    /** 返信先メールアドレス */
    @Column(name = "reply__to_mail")
    private String replyToMail = INIT_String;

    /**
     * 返信先メールアドレスを取得する
     *
     * @return 返信先メールアドレス
     */
    public String getReplyToMail() {
        return replyToMail;
    }

    /**
     * 返信先メールアドレスを設定する
     *
     * @param replyToMail 返信先メールアドレス
     */
    public void setReplyToMail(final String replyToMail) {
        this.replyToMail = replyToMail;
    }

    /** メールタイトル */
    @Column(name = "subject_mail")
    private String subjectMail = INIT_String;

    /**
     * メールタイトルを取得する
     *
     * @return メールタイトル
     */
    public String getSubjectMail() {
        return subjectMail;
    }

    /**
     * メールタイトルを設定する
     *
     * @param subjectMail メールタイトル
     */
    public void setSubjectMail(final String subjectMail) {
        this.subjectMail = subjectMail;
    }

    /** メール本文 */
    @Column(name = "body_text_mail")
    private String bodyTextMail = INIT_String;

    /**
     * メール本文を取得する
     *
     * @return メール本文
     */
    public String getBodyTextMail() {
        return bodyTextMail;
    }

    /**
     * メール本文を設定する
     *
     * @param bodyTextMail メール本文
     */
    public void setBodyTextMail(final String bodyTextMail) {
        this.bodyTextMail = bodyTextMail;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Long insertUserId = INIT_Long;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Long getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Long insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザ同一識別コード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_Integer;

    /**
     * 挿入ユーザ同一識別コードを取得する
     *
     * @return 挿入ユーザ同一識別コード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザ同一識別コードを設定する
     *
     * @param insertUserCode 挿入ユーザ同一識別コード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ姓名 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_String;

    /**
     * 挿入ユーザ姓名を取得する
     *
     * @return 挿入ユーザ姓名
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ姓名を設定する
     *
     * @param insertUserName 挿入ユーザ姓名
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入タイムスタンプ */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_LocalDateTime;

    /**
     * 挿入タイムスタンプを取得する
     *
     * @return 挿入タイムスタンプ
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入タイムスタンプを設定する
     *
     * @param insertTimestamp 挿入タイムスタンプ
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 更新ユーザId */
    @Column(name = "update_user_id")
    private Long updateUserId = INIT_Long;

    /**
     * 更新ユーザIdを取得する
     *
     * @return 更新ユーザId
     */
    @Override
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新ユーザIdを設定する
     *
     * @param updateUserId 更新ユーザId
     */
    @Override
    public void setUpdateUserId(final Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /** 更新ユーザ同一識別コード */
    @Column(name = "update_user_code")
    private Integer updateUserCode = INIT_Integer;

    /**
     * 更新ユーザ同一識別コードを取得する
     *
     * @return 更新ユーザ同一識別コード
     */
    @Override
    public Integer getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 更新ユーザ同一識別コードを設定する
     *
     * @param updateUserCode 更新ユーザ同一識別コード
     */
    @Override
    public void setUpdateUserCode(final Integer updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    /** 更新ユーザ姓名 */
    @Column(name = "update_user_name")
    private String updateUserName = INIT_String;

    /**
     * 更新ユーザ姓名を取得する
     *
     * @return 更新ユーザ姓名
     */
    @Override
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 更新ユーザ姓名を設定する
     *
     * @param updateUserName 更新ユーザ姓名
     */
    @Override
    public void setUpdateUserName(final String updateUserName) {
        this.updateUserName = updateUserName;
    }

    /** 更新タイムスタンプ */
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp = INIT_LocalDateTime;

    /**
     * 更新タイムスタンプを取得する
     *
     * @return 更新タイムスタンプ
     */
    @Override
    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 更新タイムスタンプを設定する
     *
     * @param updateTimestamp 更新タイムスタンプ
     */
    @Override
    public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    
    

    
}
