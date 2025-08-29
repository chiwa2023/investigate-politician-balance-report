package mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner;

import java.io.Serializable;

/**
 * 履歴検索条件Dto
 */
public class SearchPartnerHistoryCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Short) */
    private static final Short INIT_Short = 0;

    /** 紐づけ関連者区分 */
    private Short kanrenshaKbn = INIT_Short;

    /**
     * 紐づけ関連者区分を取得する
     *
     * @return 紐づけ関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 紐づけ関連者区分を設定する
     *
     * @param kanrenshaKbn 紐づけ関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 個人名 */
    private String partnerName = INIT_String;

    /**
     * 個人名を取得する
     *
     * @return 個人名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 個人名を設定する
     *
     * @param partnerName 個人名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 個人全住所 */
    private String allAddress = INIT_String;

    /**
     * 個人全住所を取得する
     *
     * @return 個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 個人全住所を設定する
     *
     * @param allAddress 個人全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 個人職業または団体代表者 */
    private String recognizedKey = INIT_String;

    /**
     * 個人職業または団体代表者を取得する
     *
     * @return 個人職業または団体代表者
     */
    public String getRecognizedKey() {
        return recognizedKey;
    }

    /**
     * 個人職業または団体代表者を設定する
     *
     * @param recognizedKey 個人職業または団体代表者
     */
    public void setRecognizedKey(final String recognizedKey) {
        this.recognizedKey = recognizedKey;
    }

}
