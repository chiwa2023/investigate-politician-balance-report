package mitei.mitei.investigate.report.balance.politician.entity.storage;

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
 * save_file_storage_2023接続用Entity
 */
@Entity
@Table(name = "save_file_storage_2023")
public class SaveFileStorage2023Entity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;
    
    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_LocalDateTime = INIT_LocalDate.atTime(0, 0, 0);

    /** ファイル保存ストレージId */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_file_storage_id")
    private Long saveFileStorageId = INIT_Long;

    /**
     * ファイル保存ストレージIdを取得する
     *
     * @return ファイル保存ストレージId
     */
    public Long getSaveFileStorageId() {
        return saveFileStorageId;
    }

    /**
     * ファイル保存ストレージIdを設定する
     *
     * @param saveFileStorageId ファイル保存ストレージId
     */
    public void setSaveFileStorageId(final Long saveFileStorageId) {
        this.saveFileStorageId = saveFileStorageId;
    }

    /** ファイル保存ストレージ同一識別コード */
    @Column(name = "save_file_storage_code")
    private Long saveFileStorageCode = INIT_Long;

    /**
     * ファイル保存ストレージ同一識別コードを取得する
     *
     * @return ファイル保存ストレージ同一識別コード
     */
    public Long getSaveFileStorageCode() {
        return saveFileStorageCode;
    }

    /**
     * ファイル保存ストレージ同一識別コードを設定する
     *
     * @param saveFileStorageCode ファイル保存ストレージ同一識別コード
     */
    public void setSaveFileStorageCode(final Long saveFileStorageCode) {
        this.saveFileStorageCode = saveFileStorageCode;
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

    /** 格納子ディレクトリ */
    @Column(name = "child_dir")
    private String childDir = INIT_String;
    
    /**
     * 格納子ディレクトリを取得する
     *
     * @return 格納子ディレクトリ
     */
    public String getChildDir() {
        return childDir;
    }

    /**
     * 格納子ディレクトリを設定する
     *
     * @param childDir 格納子ディレクトリ
     */
    public void setChildDir(final String childDir) {
        this.childDir = childDir;
    }

    /** ファイル名 */
    @Column(name = "file_name")
    private String fileName = INIT_String;

    /**
     * ファイル名を取得する
     *
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名を設定する
     *
     * @param fileName ファイル名
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    
    /** 登録時間 */
    @Column(name = "regist_time_text")
    private String registTimeText = INIT_String;

    /**
     * 登録時間取得する
     *
     * @return 登録時間
     */
    public String getRegistTimeText() {
        return registTimeText;
    }

    /**
     * 登録時間設定する
     *
     * @param registTimeText 登録時間
     */
    public void setRegistTimeText(final String registTimeText) {
        this.registTimeText = registTimeText;
    }

    
    
    
    
    
    
    /** 書証区分 */
    @Column(name = "shosho_kbn")
    private Integer shoshoKbn = INIT_Integer;

    /**
     * 書証区分を取得する
     *
     * @return 書証区分
     */
    public Integer getShoshoKbn() {
        return shoshoKbn;
    }

    /**
     * 書証区分を設定する
     *
     * @param shoshoKbn 書証区分
     */
    public void setShoshoKbn(final Integer shoshoKbn) {
        this.shoshoKbn = shoshoKbn;
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
