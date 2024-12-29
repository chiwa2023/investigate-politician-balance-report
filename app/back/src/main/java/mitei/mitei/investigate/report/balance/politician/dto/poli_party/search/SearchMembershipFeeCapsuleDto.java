package mitei.mitei.investigate.report.balance.politician.dto.poli_party.search;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 会費要約検索条件格納Dto
 */
public class SearchMembershipFeeCapsuleDto extends AbstractCapsuleDto { // NOPMD DataClass

    /** 報告年 */
    private Integer houkokunen;

    /** 政党Code */
    private Integer poliPartyCode;

    /** 政治団体Id */
    private Long politicalOrgnaizationId;
    /** 政治団体同一識別コード */
    private Integer politicalOrgnaizationCode;
    /** 政治団体名称 */
    private String politicalOrgnaizationName;

    /** 党費月額 */
    private Integer feeMonth;

    /** 党費年額換算 */
    private Integer feeYear;

    /** 注意水準 */
    private Integer levelAttention;

    /** 警告水準 */
    private Integer levelWarning;

    /**
     * 報告年を取得する
     *
     * @return 報告年
     */
    public Integer getHoukokunen() {
        return houkokunen;
    }

    /**
     * 報告年を設定する
     *
     * @param houkokunen 報告年
     */
    public void setHoukokunen(final Integer houkokunen) {
        this.houkokunen = houkokunen;
    }

    /**
     * 政党Codeを取得する
     *
     * @return 政党Code
     */
    public Integer getPoliPartyCode() {
        return poliPartyCode;
    }

    /**
     * 政党Codeを設定する
     *
     * @param poliPartyCode 政党Code
     */
    public void setPoliPartyCode(final Integer poliPartyCode) {
        this.poliPartyCode = poliPartyCode;
    }

    /**
     * 政治団体Idを取得する
     *
     * @return 政治団体Id
     */
    public Long getPoliticalOrgnaizationId() {
        return politicalOrgnaizationId;
    }

    /**
     * 政治団体Idを設定する
     *
     * @param politicalOrgnaizationId 政治団体Id
     */
    public void setPoliticalOrgnaizationId(final Long politicalOrgnaizationId) {
        this.politicalOrgnaizationId = politicalOrgnaizationId;
    }

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Integer getPoliticalOrgnaizationCode() {
        return politicalOrgnaizationCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param politicalOrgnaizationCode 政治団体同一識別コード
     */
    public void setPoliticalOrgnaizationCode(final Integer politicalOrgnaizationCode) {
        this.politicalOrgnaizationCode = politicalOrgnaizationCode;
    }

    /**
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getPoliticalOrgnaizationName() {
        return politicalOrgnaizationName;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param politicalOrgnaizationName 政治団体名称
     */
    public void setPoliticalOrgnaizationName(final String politicalOrgnaizationName) {
        this.politicalOrgnaizationName = politicalOrgnaizationName;
    }

    /**
     * 党費月額を取得する
     *
     * @return 党費月額
     */
    public Integer getFeeMonth() {
        return feeMonth;
    }

    /**
     * 党費月額を設定する
     *
     * @param feeMonth 党費月額
     */
    public void setFeeMonth(final Integer feeMonth) {
        this.feeMonth = feeMonth;
    }

    /**
     * 党費年額換算を取得する
     *
     * @return 党費年額換算
     */
    public Integer getFeeYear() {
        return feeYear;
    }

    /**
     * 党費年額換算を設定する
     *
     * @param feeYear 党費年額換算
     */
    public void setFeeYear(final Integer feeYear) {
        this.feeYear = feeYear;
    }

    /**
     * 注意水準を取得する
     *
     * @return 注意水準
     */
    public Integer getLevelAttention() {
        return levelAttention;
    }

    /**
     * 注意水準を設定する
     *
     * @param levelAttention 注意水準
     */
    public void setLevelAttention(final Integer levelAttention) {
        this.levelAttention = levelAttention;
    }

    /**
     * 警告水準を取得する
     *
     * @return 警告水準
     */
    public Integer getLevelWarning() {
        return levelWarning;
    }

    /**
     * 警告水準を設定する
     *
     * @param levelWarning 警告水準
     */
    public void setLevelWarning(final Integer levelWarning) {
        this.levelWarning = levelWarning;
    }

    /** 検索結果全件 */
    private long countAll;

    /** 検索結果を取得したページ数 */
    private int posPage;

    /**
     * 検索結果全件を取得する
     *
     * @return 検索結果全件
     */
    public long getCountAll() {
        return countAll;
    }

    /**
     * 検索結果全件を設定する
     *
     * @param countAll 検索結果全件
     */
    public void setCountAll(final long countAll) {
        this.countAll = countAll;
    }

    /**
     * 検索結果を取得したページ数を取得する
     *
     * @return 検索結果を取得したページ数
     */
    public int getPosPage() {
        return posPage;
    }

    /**
     * 検索結果を取得したページ数を設定する
     *
     * @param posPage 検索結果を取得したページ数
     */
    public void setPosPage(final int posPage) {
        this.posPage = posPage;
    }

    
}
