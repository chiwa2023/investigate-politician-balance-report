package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 経年変化検索条件Dto
 */
public class KeinenHenkaConditionCapsuleDto extends AbstractCapsuleDto implements Serializable { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(String) */
    private static final Long INIT_Long = 0L;

    /** 政治団体同一識別Id */
    private Long poliOrgId = INIT_Long;

    /** 政治団体同一識別コード */
    private Integer poliOrgCode = INIT_Integer;

    /** 政治団体同一識別名称 */
    private String poliOrgName = INIT_String;

    /** 検索開始報告年 */
    private Integer houkokuNenStart;

    /** 検索終了報告年 */
    private Integer houkokuNenEnd;

    /**
     * 政治団体Idを取得する
     *
     * @return 政治団体同一識別Id
     */
    public Long getPoliOrgId() {
        return poliOrgId;
    }

    /**
     * 政治団体Idを設定する
     *
     * @param poliOrgId 政治団体同一識別Id
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
     * 検索開始報告年を取得する
     *
     * @return 検索開始報告年
     */
    public Integer getHoukokuNenStart() {
        return houkokuNenStart;
    }

    /**
     * 検索開始報告年を設定する
     *
     * @param houkokuNenStart 検索開始報告年
     */
    public void setHoukokuNenStart(final Integer houkokuNenStart) {
        this.houkokuNenStart = houkokuNenStart;
    }

    /**
     * 検索終了報告年を取得する
     *
     * @return 検索終了報告年
     */
    public Integer getHoukokuNenEnd() {
        return houkokuNenEnd;
    }

    /**
     * 検索終了報告年を設定する
     *
     * @param houkokuNenEnd 検索終了報告年
     */
    public void setHoukokuNenEnd(final Integer houkokuNenEnd) {
        this.houkokuNenEnd = houkokuNenEnd;
    }

}
