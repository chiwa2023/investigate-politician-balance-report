package mitei.mitei.investigate.report.balance.politician.dto.common_check;

/**
 * データ履歴(新規・更新)を区別する定数
 */
public enum DataHistoryStatusConstants {
    
    /** 新規を表す定数 */
    INSERT(1),
    /** 更新を表す定数 */
    UPDATE(0);
    
    /** 定数区分値 */
    private final int stateValue;

    /** 最新状態値 */
    private static final int INSERT_STATE = 1;

    /** 履歴状態値 */
    private static final int UPDATE_STATE = 0;

    DataHistoryStatusConstants(final int constant) {
        
        if (INSERT_STATE == constant) { // 新規挿入=1
            stateValue = INSERT_STATE;
        } else {
            stateValue = UPDATE_STATE;
        }
    }

    /**
     * 最新状態か履歴状態をフラグ(int)で表す。主にDB登録用
     *
     * @return 状態値
     */
    public int value() {
        return stateValue;
    }

}
