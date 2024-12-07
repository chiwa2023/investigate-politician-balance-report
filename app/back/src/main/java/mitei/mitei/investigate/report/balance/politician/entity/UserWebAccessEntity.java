package mitei.mitei.investigate.report.balance.politician.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * user_web_access接続用Entity
 */
@Entity
@Table(name = "user_web_access")
public class UserWebAccessEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** ユーザWeb連絡先Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_web_access_id")
    private Long userWebAccessId = INIT_Long;

    /**
     * ユーザWeb連絡先Idを取得する
     *
     * @return ユーザWeb連絡先Id
     */
    public Long getUserWebAccessId() {
        return userWebAccessId;
    }

    /**
     * ユーザWeb連絡先Idを設定する
     *
     * @param userWebAccessId ユーザWeb連絡先Id
     */
    public void setUserWebAccessId(final Long userWebAccessId) {
        this.userWebAccessId = userWebAccessId;
    }

    /** ユーザWeb連絡先同一識別コード */
    @Column(name = "user_web_access_code")
    private Integer userWebAccessCode = INIT_Integer;

    /**
     * ユーザWeb連絡先同一識別コードを取得する
     *
     * @return ユーザWeb連絡先同一識別コード
     */
    public Integer getUserWebAccessCode() {
        return userWebAccessCode;
    }

    /**
     * ユーザWeb連絡先同一識別コードを設定する
     *
     * @param userWebAccessCode ユーザWeb連絡先同一識別コード
     */
    public void setUserWebAccessCode(final Integer userWebAccessCode) {
        this.userWebAccessCode = userWebAccessCode;
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

    /** 登録ユーザId */
    @Column(name = "user_id")
    private Long userId = INIT_Long;

    /**
     * 登録ユーザIdを取得する
     *
     * @return 登録ユーザId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 登録ユーザIdを設定する
     *
     * @param userId 登録ユーザId
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /** 登録ユーザ同一識別コード */
    @Column(name = "user_code")
    private Integer userCode = INIT_Integer;

    /**
     * 登録ユーザ同一識別コードを取得する
     *
     * @return 登録ユーザ同一識別コード
     */
    public Integer getUserCode() {
        return userCode;
    }

    /**
     * 登録ユーザ同一識別コードを設定する
     *
     * @param userCode 登録ユーザ同一識別コード
     */
    public void setUserCode(final Integer userCode) {
        this.userCode = userCode;
    }

    /** 登録ユーザ姓名 */
    @Column(name = "user_name")
    private String userName = INIT_String;

    /**
     * 登録ユーザ姓名を取得する
     *
     * @return 登録ユーザ姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 登録ユーザ姓名を設定する
     *
     * @param userName 登録ユーザ姓名
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /** 権限区分 */
    @Column(name = "task_level")
    private Integer taskLevel = INIT_Integer;

    /**
     * 権限区分を取得する
     *
     * @return 権限区分
     */
    public Integer getTaskLevel() {
        return taskLevel;
    }

    /**
     * 権限区分を設定する
     *
     * @param taskLevel 権限区分
     */
    public void setTaskLevel(final Integer taskLevel) {
        this.taskLevel = taskLevel;
    }

    /** メールアドレス */
    @Column(name = "mail_address")
    private String mailAddress = INIT_String;

    /**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレスを設定する
     *
     * @param mailAddress メールアドレス
     */
    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /** SNSId */
    @Column(name = "sns_id")
    private Long snsId = INIT_Long;

    /**
     * SNSIdを取得する
     *
     * @return SNSId
     */
    public Long getSnsId() {
        return snsId;
    }

    /**
     * SNSIdを設定する
     *
     * @param snsId SNSId
     */
    public void setSnsId(final Long snsId) {
        this.snsId = snsId;
    }

    /** SNS同一識別コード */
    @Column(name = "sns_code")
    private Integer snsCode = INIT_Integer;

    /**
     * SNS同一識別コードを取得する
     *
     * @return SNS同一識別コード
     */
    public Integer getSnsCode() {
        return snsCode;
    }

    /**
     * SNS同一識別コードを設定する
     *
     * @param snsCode SNS同一識別コード
     */
    public void setSnsCode(final Integer snsCode) {
        this.snsCode = snsCode;
    }

    /** SNS名称 */
    @Column(name = "sns_name")
    private String snsName = INIT_String;

    /**
     * SNS名称を取得する
     *
     * @return SNS名称
     */
    public String getSnsName() {
        return snsName;
    }

    /**
     * SNS名称を設定する
     *
     * @param snsName SNS名称
     */
    public void setSnsName(final String snsName) {
        this.snsName = snsName;
    }

    /** 送信LogicId */
    @Column(name = "send_sns_logic_id")
    private Integer sendSnsLogicId = INIT_Integer;

    /**
     * 送信LogicIdを取得する
     *
     * @return 送信LogicId
     */
    public Integer getSendSnsLogicId() {
        return sendSnsLogicId;
    }

    /**
     * 送信LogicIdを設定する
     *
     * @param sendSnsLogicId 送信LogicId
     */
    public void setSendSnsLogicId(final Integer sendSnsLogicId) {
        this.sendSnsLogicId = sendSnsLogicId;
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
    private LocalDateTime insertTimestamp = INIT_Timestamp;

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
    private LocalDateTime updateTimestamp = INIT_Timestamp;

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
