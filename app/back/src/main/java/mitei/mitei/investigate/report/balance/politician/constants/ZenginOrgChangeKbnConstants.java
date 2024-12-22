package mitei.mitei.investigate.report.balance.politician.constants;

/**
 * 全銀金融機関・支店変更テーブル定数
 */
public final class ZenginOrgChangeKbnConstants {

    /** 追加区分 */
    public static final int ADD = 1;
    /** 追加区分テキスト */
    public static final String ADD_TEXT = "追加";

    /** 廃止区分 */
    public static final int DELETE = 2;
    /** 廃止区分テキスト */
    public static final String DELETE_TEXT = "廃止";

    /** 移転区分 */
    public static final int MOVE = 3;
    /** 移転区分テキスト */
    public static final String MOVE_TEXT = "移転";

    /**
     * インスタンス生成除け
     */
    private ZenginOrgChangeKbnConstants() {

    }

    /**
     * 変更区分名称を取得する
     *
     * @param changeKbn 変更区分
     * @return 変更区分テキスト
     */
    public static String getText(final int changeKbn) {
        switch (changeKbn) {
            // 追加
            case ADD:
                return ADD_TEXT;
            // 廃止
            case DELETE:
                return DELETE_TEXT;
            // 移転
            case MOVE:
                return MOVE_TEXT;
            default:
                throw new IllegalArgumentException("Unexpected value: " + changeKbn);
        }
    }

}
