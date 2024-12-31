package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;

/**
 * 不記載検出明細グループ格納Dto
 */
public class FukisaiTradingInfoDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 収入合計 */
    private Long sumIncome;

    /** 支出合計 */
    private Long sumOutcome;

    /** 調査対象政治団体名称 */
    private String politicalOrgName;

    /** 取引相手(政治団体)名称 */
    private String partnerName;

    /** 取引明細リスト */
    private List<WkTblFukisaiBalancesheetEntity> listMeisai = new ArrayList<>();

    /** 金額差有無 */
    private Boolean isKingakuDiffer;

    /**
     * 収入合計を取得する
     *
     * @return 収入合計
     */
    public Long getSumIncome() {
        return sumIncome;
    }

    /**
     * 収入合計を設定する
     *
     * @param sumIncome 収入合計
     */
    public void setSumIncome(final Long sumIncome) {
        this.sumIncome = sumIncome;
    }

    /**
     * 支出合計を取得する
     *
     * @return 支出合計
     */
    public Long getSumOutcome() {
        return sumOutcome;
    }

    /**
     * 支出合計を設定する
     *
     * @param sumOutcome 支出合計
     */
    public void setSumOutcome(final Long sumOutcome) {
        this.sumOutcome = sumOutcome;
    }

    /**
     * 調査対象政治団体名称を取得する
     *
     * @return 調査対象政治団体名称
     */
    public String getPoliticalOrgName() {
        return politicalOrgName;
    }

    /**
     * 調査対象政治団体名称を設定する
     *
     * @param politicalOrgName 調査対象政治団体名称
     */
    public void setPoliticalOrgName(final String politicalOrgName) {
        this.politicalOrgName = politicalOrgName;
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
     * 取引明細リストを取得する
     *
     * @return 取引明細リスト
     */
    public List<WkTblFukisaiBalancesheetEntity> getListMeisai() {
        return listMeisai;
    }

    /**
     * 取引明細リストを設定する
     *
     * @param listMeisai 取引明細リスト
     */
    public void setListMeisai(final List<WkTblFukisaiBalancesheetEntity> listMeisai) {
        this.listMeisai = listMeisai;
    }

    /**
     * 金額差有無を取得する
     *
     * @return 金額差有無
     */
    public Boolean getIsKingakuDiffer() {
        return isKingakuDiffer;
    }

    /**
     * 金額差有無を設定する
     *
     * @param isKingakuDiffer 金額差有無
     */
    public void setIsKingakuDiffer(final Boolean isKingakuDiffer) {
        this.isKingakuDiffer = isKingakuDiffer;
    }

}
