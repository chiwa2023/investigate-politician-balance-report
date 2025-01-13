package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.KobetsuKiseiMeisaiEntity;

/**
 * 寄付上限取引全体Dto
 */
public class KifuJougenTradingInfoDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 寄付額合計 */
    private Long sumKifu = INIT_Long;

    /** 取引相手(政治団体)名称 */
    private String partnerName = INIT_String;

    /** 調査対象政治団体住所 */
    private String partnerAddress = INIT_String;

    /** 取引相手関連者Id */
    private Long relationId = INIT_Long;

    /** 取引相手関連者同一識別コード */
    private Integer relationCode = INIT_Integer;

    /** 様式枝区分 */
    private Integer youshikiEdaKbn = INIT_Integer;

    /** 上限額超え該否 */
    private Boolean isLimitOver = INIT_Boolean;

    /** 明細リスト */
    private List<KobetsuKiseiMeisaiEntity> listTradingMeisai = new ArrayList<>();

    /**
     * 寄付額合計を取得する
     *
     * @return 寄付額合計
     */
    public Long getSumKifu() {
        return sumKifu;
    }

    /**
     * 寄付額合計を設定する
     *
     * @param sumKifu 寄付額合計
     */
    public void setSumKifu(final Long sumKifu) {
        this.sumKifu = sumKifu;
    }

    /**
     * 取引相手(政治団体)名称を取得する
     *
     * @return 取引相手(政治団体)名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 取引相手(政治団体)名称を設定する
     *
     * @param partnerName 取引相手(政治団体)名称
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /**
     * 調査対象政治団体住所を取得する
     *
     * @return 調査対象政治団体住所
     */
    public String getPartnerAddress() {
        return partnerAddress;
    }

    /**
     * 調査対象政治団体住所を設定する
     *
     * @param partnerAddress 調査対象政治団体住所
     */
    public void setPartnerAddress(final String partnerAddress) {
        this.partnerAddress = partnerAddress;
    }

    /**
     * 取引相手関連者Idを取得する
     *
     * @return 取引相手関連者Id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 取引相手関連者Idを設定する
     *
     * @param relationId 取引相手関連者Id
     */
    public void setRelationId(final Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 取引相手関連者同一識別コードを取得する
     *
     * @return 取引相手関連者同一識別コード
     */
    public Integer getRelationCode() {
        return relationCode;
    }

    /**
     * 取引相手関連者同一識別コードを設定する
     *
     * @param relationCode 取引相手関連者同一識別コード
     */
    public void setRelationCode(final Integer relationCode) {
        this.relationCode = relationCode;
    }

    /**
     * 様式枝区分を取得する
     *
     * @return 様式枝区分
     */
    public Integer getYoushikiEdaKbn() {
        return youshikiEdaKbn;
    }

    /**
     * 様式枝区分を設定する
     *
     * @param youshikiEdaKbn 様式枝区分
     */
    public void setYoushikiEdaKbn(final Integer youshikiEdaKbn) {
        this.youshikiEdaKbn = youshikiEdaKbn;
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

    /**
     * 明細リストを取得する
     *
     * @return 明細リスト
     */
    public List<KobetsuKiseiMeisaiEntity> getListTradingMeisai() {
        return listTradingMeisai;
    }

    /**
     * 明細リストを設定する
     *
     * @param listTradingMeisai 明細リスト
     */
    public void setListTradingMeisai(final List<KobetsuKiseiMeisaiEntity> listTradingMeisai) {
        this.listTradingMeisai = listTradingMeisai;
    }

}
