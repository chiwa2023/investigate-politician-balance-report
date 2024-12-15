package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.io.Serializable;

/**
 * 全銀協発行金融機関・店舗情報csvDto
 */
public class FinancialOrgCsvDto implements Serializable { // NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;
    
    /** 金融機関コード */
    private String orgCode;

    /** 店舗コード */
    private String branchCode;

    /** 金融機関名(カナ) */
    private String orgNameKana;

    /** 金融機関名(漢字) */
    private String orgName;

    /** 店舗名(カナ) */
    private String branchNameKana;

    /** 店舗名(漢字) */
    private String branchName;

    /** 郵便番号 */
    private String postalCode;

    /** 店舗所在地(漢字) */
    private String branchAddress;

    /** 電話番号 */
    private String phonNumber;

    /** 手形交換所番号 */
    private String billExchangeNumber;

    /** 並びコード */
    private String orderCode;

    /** 内国為替制度加盟 */
    private String flgNaikokuKawase;

    /**
     * 金融機関コードを取得する
     *
     * @return 金融機関コード
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 金融機関コードを設定する
     *
     * @param orgCode 金融機関コード
     */
    public void setOrgCode(final String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 店舗コードを取得する
     *
     * @return 店舗コード
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * 店舗コードを設定する
     *
     * @param branchCode 店舗コード
     */
    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * 金融機関名(カナ)を取得する
     *
     * @return 金融機関名(カナ)
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 金融機関名(カナ)を設定する
     *
     * @param orgNameKana 金融機関名(カナ)
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
    }

    /**
     * 金融機関名(漢字)を取得する
     *
     * @return 金融機関名(漢字)
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 金融機関名(漢字)を設定する
     *
     * @param orgName 金融機関名(漢字)
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /**
     * 店舗名(カナ)を取得する
     *
     * @return 店舗名(カナ)
     */
    public String getBranchNameKana() {
        return branchNameKana;
    }

    /**
     * 店舗名(カナ)を設定する
     *
     * @param branchNameKana 店舗名(カナ)
     */
    public void setBranchNameKana(final String branchNameKana) {
        this.branchNameKana = branchNameKana;
    }

    /**
     * 店舗名(漢字)を取得する
     *
     * @return 店舗名(漢字)
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 店舗名(漢字)を設定する
     *
     * @param branchName 店舗名(漢字)
     */
    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    /**
     * 郵便番号を取得する
     *
     * @return 郵便番号
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 郵便番号を設定する
     *
     * @param postalCode 郵便番号
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 店舗所在地(漢字)を取得する
     *
     * @return 店舗所在地(漢字)
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * 店舗所在地(漢字)を設定する
     *
     * @param branchAddress 店舗所在地(漢字)
     */
    public void setBranchAddress(final String branchAddress) {
        this.branchAddress = branchAddress;
    }

    /**
     * 電話番号を取得する
     *
     * @return 電話番号
     */
    public String getPhonNumber() {
        return phonNumber;
    }

    /**
     * 電話番号を設定する
     *
     * @param phonNumber 電話番号
     */
    public void setPhonNumber(final String phonNumber) {
        this.phonNumber = phonNumber;
    }

    /**
     * 手形交換所番号を取得する
     *
     * @return 手形交換所番号
     */
    public String getBillExchangeNumber() {
        return billExchangeNumber;
    }

    /**
     * 手形交換所番号を設定する
     *
     * @param billExchangeNumber 手形交換所番号
     */
    public void setBillExchangeNumber(final String billExchangeNumber) {
        this.billExchangeNumber = billExchangeNumber;
    }

    /**
     * 並びコードを取得する
     *
     * @return 並びコード
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 並びコードを設定する
     *
     * @param orderCode 並びコード
     */
    public void setOrderCode(final String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 内国為替制度加盟を取得する
     *
     * @return 内国為替制度加盟
     */
    public String getFlgNaikokuKawase() {
        return flgNaikokuKawase;
    }

    /**
     * 内国為替制度加盟を設定する
     *
     * @param flgNaikokuKawase 内国為替制度加盟
     */
    public void setFlgNaikokuKawase(final String flgNaikokuKawase) {
        this.flgNaikokuKawase = flgNaikokuKawase;
    }

}
