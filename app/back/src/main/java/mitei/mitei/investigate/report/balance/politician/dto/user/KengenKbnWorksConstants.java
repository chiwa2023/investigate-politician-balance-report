package mitei.mitei.investigate.report.balance.politician.dto.user;

/**
 * ユーザの作業権限を表す定数値
 */
public class KengenKbnWorksConstants { // NOPMD DataClass

    /** 代表者値 */
    public static final int DELEGATE_ONLY = 1;

    /** 会計責任者値 */
    public static final int ACCOUNT_MANAGER_ONLY = 2;

    /** 事務担当者値 */
    public static final int OFFICER_ONLY = 3;

    /** 一般作業員値 */
    public static final int WORKER_ONLY = 4;

    /** 会計責任者以下 */
    public static final int MANAGER_UNDER = 5;

    /** 事務担当者以下 */
    public static final int OFFICER_UNDER = 6;

    /** 会計責任者以上 */
    public static final int MANAGER_OVER = 7;

}
