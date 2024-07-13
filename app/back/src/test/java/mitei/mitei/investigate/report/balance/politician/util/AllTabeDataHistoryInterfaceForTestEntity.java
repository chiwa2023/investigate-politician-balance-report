package mitei.mitei.investigate.report.balance.politician.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * テーブル履歴Interfaceテスト専用Entity
 */
public class AllTabeDataHistoryInterfaceForTestEntity implements AllTabeDataHistoryInterface {
    
    /**　Long初期値 */
    private final Long INIT_LONG = 0L;

    /**　Integer初期値 */
    private final Integer INIT_INTEGER = 0;

    /**　String初期値 */
    private final String INIT_STRING = "";

    /**　Timestamp初期値 */
    private final Timestamp INIT_TIMESTAMP = Timestamp.valueOf(LocalDateTime.of(1980, 1, 1, 0, 0, 0));
    
    /**　データ挿入ユーザId */
    private Long insertUserId = INIT_LONG;
    /**　データ挿入ユーザ同一識別コード */
    private Integer insertUserCode = INIT_INTEGER;
    /**　データ挿入ユーザ名称 */
    private String insertUserName = INIT_STRING;
    /**　データ挿入時間 */
    private Timestamp insertTimestamp = INIT_TIMESTAMP;

    /**　データ挿入ユーザId */
    private Long updateUserId = INIT_LONG;
    /**　データ挿入ユーザ同一識別コード */
    private Integer updateUserCode = INIT_INTEGER;
    /**　データ挿入ユーザ名称 */
    private String updateUserName = INIT_STRING;
    /**　データ挿入時間 */
    private Timestamp updateTimestamp = INIT_TIMESTAMP;

    /** INSERT時ログインユーザIdを取得する */
    @Override
    public Long getInsertUserId() {
        return insertUserId;
    }

    /** INSERT時ログインユーザIdを設定する */
    @Override
    public void setInsertUserId(final Long insertUserId) {
        this.insertUserId = insertUserId;
    }
    
    /** INSERT時ログインユーザCodeを取得する */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /** INSERT時ログインユーザCodeを設定する */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** INSERT時ログインユーザNameを取得する */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /** INSERT時ログインユーザNameを設定する */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }
    
    /** INSERT時ログインユーザNameを取得する */
    @Override
    public Timestamp getInsertTimestamp() {
        return insertTimestamp;
    }
    
    /** INSERT時挿入時間を設定する */
    @Override
    public void setInsertTimestamp(final Timestamp insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** UPDATEt時ログインユーザIdを取得する */
    @Override
    public Long getUpdateUserId() {
        return updateUserId;
    }
    
    /** UPDATEt時ログインユーザIdを設定する */
    @Override
    public void setUpdateUserId(final Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /** UPDATE時ログインユーザCodeを取得する */
    @Override
    public Integer getUpdateUserCode() {
        return updateUserCode;
    }

    /** UPDATE時ログインユーザCodeを設定する */
    @Override
    public void setUpdateUserCode(final Integer updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    /** UPDATE時ログインユーザNameを取得する */
    @Override
    public String getUpdateUserName() {
        return updateUserName;
    }

    /** UPDATE時ログインユーザNameを設定する */
    @Override
    public void setUpdateUserName(final String updateUserName) {
        this.updateUserName = updateUserName;
    }
    
    /** UPDATE時更新時間を取得する */
    @Override
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }
    
    /** UPDATE時更新時間を設定する */
    @Override
    public void setUpdateTimestamp(final Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
    
    
    
    
    
}
