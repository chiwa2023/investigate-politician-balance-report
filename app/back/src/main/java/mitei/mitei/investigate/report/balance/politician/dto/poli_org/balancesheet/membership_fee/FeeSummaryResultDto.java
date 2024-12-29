package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * 合計会費と合計員数格納Dto
 */
public class FeeSummaryResultDto implements Serializable { //NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;
    
    /** 会費合計 */
    @Column(name = "sum_fee")
    private Long sumFee = 0L;

    /** 員数合計 */
    @Column(name = "sum_insu")
    private Integer sumInsu = 0;

    /**
     * 会費合計を取得する
     *
     * @return 会費合計
     */
    public Long getSumFee() {
        return sumFee;
    }

    /**
     * 会費合計を設定する
     *
     * @param sumFee 会費合計
     */
    public void setSumFee(final Long sumFee) {
        this.sumFee = sumFee;
    }

    /**
     * 員数合計を取得する
     *
     * @return 員数合計
     */
    public Integer getSumInsu() {
        return sumInsu;
    }

    /**
     * 員数合計を設定する
     *
     * @param sumInsu 員数合計
     */
    public void setSumInsu(final Integer sumInsu) {
        this.sumInsu = sumInsu;
    }

}
