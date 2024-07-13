package mitei.mitei.investigate.report.balance.politician.entity;

import java.sql.Timestamp;

/**
 * テーブル履歴を管理するためのInterface
 */
public interface AllTabeDataHistoryInterface {
    
    /** INSERT時ログインユーザIdを取得する */
    Long getInsertUserId();
    /** INSERT時ログインユーザIdを設定する */
    void setInsertUserId(Long insertUserId);
    
    /** INSERT時ログインユーザCodeを取得する */
    Integer getInsertUserCode();
    /** INSERT時ログインユーザCodeを設定する */
    void setInsertUserCode(Integer insertUserCode);
    
    /** INSERT時ログインユーザNameを取得する */
    String getInsertUserName();
    /** INSERT時ログインユーザNameを設定する */
    void setInsertUserName(String insertUserName);
    
    /** INSERT時挿入時間を取得する */
    Timestamp getInsertTimestamp();
    /** INSERT時挿入時間を設定する */
    void setInsertTimestamp(Timestamp insertTimetamp);
    
    /** UPDATEt時ログインユーザIdを取得する */
    Long getUpdateUserId();
    /** UPDATEt時ログインユーザIdを設定する */
    void setUpdateUserId(Long updatetUserId);
    
    /** UPDATE時ログインユーザCodeを取得する */
    Integer getUpdateUserCode();
    /** UPDATE時ログインユーザCodeを設定する */
    void setUpdateUserCode(Integer updateUserCode);
    
    /** UPDATE時ログインユーザNameを取得する */
    String getUpdateUserName();
    /** UPDATE時ログインユーザNameを設定する */
    void setUpdateUserName(String updateUserName);
    
    /** UPDATE時更新時間を取得する */
    Timestamp getUpdateTimestamp();
    /** UPDATE時更新時間を設定する */
    void setUpdateTimestamp(Timestamp insertTimetamp);
    
}
