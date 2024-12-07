package mitei.mitei.investigate.report.balance.politician.dto.user;

/**
 * 公式に申請する役職k分
 */
public enum YakushokuKbnConstants {

    /** 代表者を表す定数 */
    DELEGATE(1),
    /** 会計責任者を表す定数 */
    ACCOUNT_MANAGER(2),
    /** 事務担当者を表す定数 */
    OFFICER(3),
    /** 一般作業員を表す定数 */
    WORKER(4);

    /** 定数区分値 */
    private final int stateValue;

    /** 代表者値 */
    private static final int DELEGATE_STATE = 1;

    /** 会計責任者値 */
    private static final int ACCOUNT_MANAGER_STATE = 2;

    /** 事務担当者値 */
    private static final int OFFICER_STATE = 3;

    /** 一般作業員値 */
    private static final int WORKER_STATE = 4;

    YakushokuKbnConstants(final int constant) {

        switch (constant) {
            case DELEGATE_STATE:
                stateValue = DELEGATE_STATE;
                break;

            case ACCOUNT_MANAGER_STATE:
                stateValue = ACCOUNT_MANAGER_STATE;
                break;

            case OFFICER_STATE:
                stateValue = OFFICER_STATE;
                break;

            case WORKER_STATE:
                stateValue = WORKER_STATE;
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + constant);
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
