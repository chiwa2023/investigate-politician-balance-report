package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei;

import java.io.Serializable;

/**
 * 総量規制(関連者コード紐づけ)Dto
 */
public class SouryouKiseiRelationCodeDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 合計 */
    private Long sum = INIT_Long;

    /**
     * 合計を取得する
     *
     * @return 合計
     */
    public Long getSum() {
        return sum;
    }

    /**
     * 合計を設定する
     *
     * @param sum 合計
     */
    public void setSum(final Long sum) {
        this.sum = sum;
    }

    /** 関連者Id */
    private Long relationId = INIT_Long;

    /** 関連者同一識別コード */
    private Integer relationCode = INIT_Integer;

    /**
     * 関連者Idを取得する
     *
     * @return 関連者Id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 関連者Idを設定する
     *
     * @param relationId 関連者Id
     */
    public void setRelationId(final Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 関連者同一識別コードを取得する
     *
     * @return 関連者同一識別コード
     */
    public Integer getRelationCode() {
        return relationCode;
    }

    /**
     * 関連者同一識別コードを設定する
     *
     * @param relationCode 関連者同一識別コード
     */
    public void setRelationCode(final Integer relationCode) {
        this.relationCode = relationCode;
    }

    /** 取引相手名称 */
    private String partnerName = INIT_String;

    /**
     * 取引相手名称を取得する
     *
     * @return 取引相手名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 取引相手名称を設定する
     *
     * @param partnerName 取引相手名称
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 取引相手住所 */
    private String partnerJusho = INIT_String;

    /**
     * 取引相手住所を取得する
     *
     * @return 取引相手住所
     */
    public String getPartnerJusho() {
        return partnerJusho;
    }

    /**
     * 取引相手住所を設定する
     *
     * @param partnerJusho 取引相手住所
     */
    public void setPartnerJusho(final String partnerJusho) {
        this.partnerJusho = partnerJusho;
    }

    /** 上限額 */
    private Long limitAmount = INIT_Long;

    /** 上限額超え該否 */
    private Boolean isLimitOver = INIT_Boolean;

    /**
     * 上限額を取得する
     *
     * @return 上限額
     */
    public Long getLimitAmount() {
        return limitAmount;
    }

    /**
     * 上限額を設定する
     *
     * @param limitAmount 上限額
     */
    public void setLimitAmount(final Long limitAmount) {
        this.limitAmount = limitAmount;
    }

    /**
     * 上限額超え該否を取得する
     *
     * @return 上限額超え該否
     */
    public Boolean getIsLimitOver() {
        return isLimitOver;
    }

    /**
     * 上限額超え該否を設定する
     *
     * @param isLimitOver 上限額超え該否
     */
    public void setIsLimitOver(final Boolean isLimitOver) {
        this.isLimitOver = isLimitOver;
    }

}
