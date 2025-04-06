package mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 交付金連絡ワークテーブル作成条件Dto
 */
public class CreateRenketsuKoufukinCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 報告年 */
    private Integer houkokuNen = INIT_Integer;

    /** 文書コード使途報告書 */
    private Long documentCodeUsage = INIT_Long;

    /** 文書コード収支報告書 */
    private Long documentCodeBalance = INIT_Long;

    /**
     * 報告年を取得する
     *
     * @return 報告年
     */
    public Integer getHoukokuNen() {
        return houkokuNen;
    }

    /**
     * 報告年を設定する
     *
     * @param houkokuNen 報告年
     */
    public void setHoukokuNen(final Integer houkokuNen) {
        this.houkokuNen = houkokuNen;
    }

    /**
     * 文書コード使途報告書を取得する
     *
     * @return 文書コード使途報告書
     */
    public Long getDocumentCodeUsage() {
        return documentCodeUsage;
    }

    /**
     * 文書コード使途報告書を設定する
     *
     * @param documentCodeUsage 文書コード使途報告書
     */
    public void setDocumentCodeUsage(final Long documentCodeUsage) {
        this.documentCodeUsage = documentCodeUsage;
    }

    /**
     * 文書コード収支報告書を取得する
     *
     * @return 文書コード収支報告書
     */
    public Long getDocumentCodeBalance() {
        return documentCodeBalance;
    }

    /**
     * 文書コード収支報告書を設定する
     *
     * @param documentCodeBalance 文書コード収支報告書
     */
    public void setDocumentCodeBalance(final Long documentCodeBalance) {
        this.documentCodeBalance = documentCodeBalance;
    }

}
