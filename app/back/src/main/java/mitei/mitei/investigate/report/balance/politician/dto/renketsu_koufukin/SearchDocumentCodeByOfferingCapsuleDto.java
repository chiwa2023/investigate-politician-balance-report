package mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 文書コード検索条件Dto
 */
public class SearchDocumentCodeByOfferingCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 政治団体Id */
    private Long poliOrgId = INIT_Long;

    /** 政治団体コード */
    private Integer poliOrgCode = INIT_Integer;

    /** 政治団体Id */
    private String poliOrgName = INIT_String;

    /** 報告年 */
    private Integer houkokuNen = INIT_Integer;

    /**
     * 政治団体Idを設定する
     *
     * @return 政治団体Id
     */
    public Long getPoliOrgId() {
        return poliOrgId;
    }

    /**
     * 政治団体Idを設定する
     *
     * @param poliOrgId 政治団体Id
     */
    public void setPoliOrgId(final Long poliOrgId) {
        this.poliOrgId = poliOrgId;
    }

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Integer getPoliOrgCode() {
        return poliOrgCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param poliOrgCode 政治団体同一識別コード
     */
    public void setPoliOrgCode(final Integer poliOrgCode) {
        this.poliOrgCode = poliOrgCode;
    }

    /**
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getPoliOrgName() {
        return poliOrgName;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param poliOrgName 政治団体名称
     */
    public void setPoliOrgName(final String poliOrgName) {
        this.poliOrgName = poliOrgName;
    }

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

}
