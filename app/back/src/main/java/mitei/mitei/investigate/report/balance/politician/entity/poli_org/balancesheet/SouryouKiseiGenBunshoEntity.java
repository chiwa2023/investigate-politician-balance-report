package mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 原文書取引相手名と取引相手住所から単純な合計を取得するためのEntity
 */
@Entity
public class SouryouKiseiGenBunshoEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 合計 */
    @Column(name = "sum")
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

    /** 取引相手名称 */
    @Id
    @Column(name = "partner_name")
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
    @Id
    @Column(name = "partner_juusho")
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

}
