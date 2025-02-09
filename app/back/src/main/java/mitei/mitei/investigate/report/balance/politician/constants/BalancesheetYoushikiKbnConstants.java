package mitei.mitei.investigate.report.balance.politician.constants;

/**
 * 政治資金収支報告書様式区分定数
 */
public final class BalancesheetYoushikiKbnConstants {

    /**
     * 様式枝区分を定数
     */
    public class YoushikiKbn { // NOPMD DataClass
        
        /** 団体基礎情報(表紙) */
        public static final int HYOUSHI = 1;

        /** 収入項目集計表 */
        public static final int SHUUKEI_SHUUNYU = 2;
        
        /** 機関誌の発行その他 */
        public static final int KIKANSHI = 3;
        
        /** 借入金 */
        public static final int SHAKUNYUKIN = 4;
        
        /** 本部または支部からの交付金 */
        public static final int KOUFUKIN = 5;
        
        /** その他の収入 */
        public static final int SHUUNYU_SONOTA = 6;
        
        /** 寄付 */
        public static final int DONATE = 7;
        
        /** 寄付のうちあっせんによるもの */
        public static final int DONATE_ASSEN = 8;

        /** 政党匿名寄付 */
        public static final int DONATE_TOKUMEI = 9;
        
        /** 特定パーティー */
        public static final int TOKUTEI_PARTY = 10;
        
        /** 政治資金パーティー */
        public static final int PARTY = 11;
        
        /** 政治資金パーティーのうちあっせんによるもの */
        public static final int PARTY_ASSEN = 12;

        /** 支出項目集計表 */
        public static final int SHUUKEI_SHISHUTSU = 13;
        
        /** 経常経費の内訳 */
        public static final int KEIJO_KEIHI = 14;

        /** 政治活動費 */
        public static final int SEIJIKATSUDOUHI = 15;
        
        /** 本部または支部に供与した交付金 */
        public static final int KOUFUKIN_SHISHUTSU = 16;
        
        /** 資産項目記載一覧 */
        public static final int SHUUKEI_SHISAN = 17;
        
        /** 資産の内訳 */
        public static final int ESTATE = 18;
        
        /** 不動産詳細の内訳 */
        public static final int REALESTATE = 19;

        /** 宣誓書 */
        public static final int SENSEISHO = 20;

    }

    /**
     * 様式枝区分を示す定数
     */
    public final class YoushikiEdaKbn { // NOPMD DataClass

        /** 未設定 */
        public static final int NO_SET = 0;

        /** 個人 */
        public static final int KOJIN = 1;

        /** 企業・団体 */
        public static final int KIGYOU_DANTAI = 2;

        /** 政治団体 */
        public static final int SEIJI_DANTAI = 3;

    }

}
