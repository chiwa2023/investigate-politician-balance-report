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
 * task_info接続用Entity
 */
@Entity
@Table(name = "task_info")
public class TaskInfoEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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

    /** タスク情報Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_info_id")
    private Long taskInfoId = INIT_Long;

    /**
     * タスク情報Idを取得する
     *
     * @return タスク情報Id
     */
    public Long getTaskInfoId() {
        return taskInfoId;
    }

    /**
     * タスク情報Idを設定する
     *
     * @param taskInfoId タスク情報Id
     */
    public void setTaskInfoId(final Long taskInfoId) {
        this.taskInfoId = taskInfoId;
    }

    /** タスク情報同一識別コード */
    @Column(name = "task_info_code")
    private Integer taskInfoCode = INIT_Integer;

    /**
     * タスク情報同一識別コードを取得する
     *
     * @return タスク情報同一識別コード
     */
    public Integer getTaskInfoCode() {
        return taskInfoCode;
    }

    /**
     * タスク情報同一識別コードを設定する
     *
     * @param taskInfoCode タスク情報同一識別コード
     */
    public void setTaskInfoCode(final Integer taskInfoCode) {
        this.taskInfoCode = taskInfoCode;
    }

    /** タスク情報名称 */
    @Column(name = "task_info_name")
    private String taskInfoName = INIT_String;

    /**
     * タスク情報名称を取得する
     *
     * @return タスク情報名称
     */
    public String getTaskInfoName() {
        return taskInfoName;
    }

    /**
     * タスク情報名称を設定する
     *
     * @param taskInfoName タスク情報名称
     */
    public void setTaskInfoName(final String taskInfoName) {
        this.taskInfoName = taskInfoName;
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

    /** タスク水準リスト */
    @Column(name = "task_level_list")
    private String taskLevelList = INIT_String;

    /**
     * タスク水準リストを取得する
     *
     * @return タスク水準リスト
     */
    public String getTaskLevelList() {
        return taskLevelList;
    }

    /**
     * タスク水準リストを設定する
     *
     * @param taskLevelList タスク水準リスト
     */
    public void setTaskLevelList(final String taskLevelList) {
        this.taskLevelList = taskLevelList;
    }

    /** メッセージテンプレート */
    @Column(name = "message_template")
    private String messageTemplate = INIT_String;

    /**
     * メッセージテンプレートを取得する
     *
     * @return メッセージテンプレート
     */
    public String getMessageTemplate() {
        return messageTemplate;
    }

    /**
     * メッセージテンプレートを設定する
     *
     * @param messageTemplate メッセージテンプレート
     */
    public void setMessageTemplate(final String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    /** 遷移パス(URL) */
    @Column(name = "transfer_pass")
    private String transferPass = INIT_String;

    /**
     * 遷移パス(URL)を取得する
     *
     * @return 遷移パス(URL)
     */
    public String getTransferPass() {
        return transferPass;
    }

    /**
     * 遷移パス(URL)を設定する
     *
     * @param transferPass 遷移パス(URL)
     */
    public void setTransferPass(final String transferPass) {
        this.transferPass = transferPass;
    }

    /** SNS同一識別コード */
    @Column(name = "param_query")
    private String paramQuery = INIT_String;

    /**
     * SNS同一識別コードを取得する
     *
     * @return SNS同一識別コード
     */
    public String getParamQuery() {
        return paramQuery;
    }

    /**
     * SNS同一識別コードを設定する
     *
     * @param paramQuery SNS同一識別コード
     */
    public void setParamQuery(final String paramQuery) {
        this.paramQuery = paramQuery;
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
