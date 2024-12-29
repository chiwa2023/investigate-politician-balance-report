package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee;

/**
 * 政治団体会費指標格納Dto
 */
public class PoliOrgFeeInsuDto { // NOPMD DataClass

    /** 政治団体Id */
    private Long politicalOrgnaizationId;
    /** 政治団体同一識別コード */
    private Integer politicalOrgnaizationCode;
    /** 政治団体名称 */
    private String politicalOrgnaizationName;

    /** 政治団体代表者Id */
    private Long poliOrgDaihyoushaId;
    /** 政治団体代表者同一識別コード */
    private Integer poliOrgDaihyoushaCode;
    /** 政治団体代表者名称 */
    private String poliOrgDaihyoushaName;

    /** 評価値 */
    private String rating;

    /** 党費 */
    private Long fee;
    /** 員数 */
    private Integer insu;
    /** 頭割り */
    private String average;

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
     * 政治団体代表者Idを取得する
     *
     * @return 政治団体代表者Id
     */
    public Long getPoliOrgDaihyoushaId() {
        return poliOrgDaihyoushaId;
    }

    /**
     * 政治団体代表者Idを設定する
     *
     * @param poliOrgDaihyoushaId 政治団体代表者Id
     */
    public void setPoliOrgDaihyoushaId(final Long poliOrgDaihyoushaId) {
        this.poliOrgDaihyoushaId = poliOrgDaihyoushaId;
    }

    /**
     * 政治団体代表者同一識別コードを取得する
     *
     * @return 政治団体代表者同一識別コード
     */
    public Integer getPoliOrgDaihyoushaCode() {
        return poliOrgDaihyoushaCode;
    }

    /**
     * 政治団体代表者同一識別コードを設定する
     *
     * @param poliOrgDaihyoushaCode 政治団体代表者同一識別コード
     */
    public void setPoliOrgDaihyoushaCode(final Integer poliOrgDaihyoushaCode) {
        this.poliOrgDaihyoushaCode = poliOrgDaihyoushaCode;
    }

    /**
     * 政治団体代表者名称を取得する
     *
     * @return 政治団体代表者名称
     */
    public String getPoliOrgDaihyoushaName() {
        return poliOrgDaihyoushaName;
    }

    /**
     * 政治団体代表者名称を設定する
     *
     * @param poliOrgDaihyoushaName 政治団体代表者名称
     */
    public void setPoliOrgDaihyoushaName(final String poliOrgDaihyoushaName) {
        this.poliOrgDaihyoushaName = poliOrgDaihyoushaName;
    }

    /**
     * 評価値を取得する
     *
     * @return 評価値
     */
    public String getRating() {
        return rating;
    }

    /**
     * 評価値を設定する
     *
     * @param rating 評価値
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /**
     * 党費を取得する
     *
     * @return 党費
     */
    public Long getFee() {
        return fee;
    }

    /**
     * 党費を設定する
     *
     * @param fee 党費
     */
    public void setFee(final Long fee) {
        this.fee = fee;
    }

    /**
     * 員数を取得する
     *
     * @return 員数
     */
    public Integer getInsu() {
        return insu;
    }

    /**
     * 員数を設定する
     *
     * @param insu 員数
     */
    public void setInsu(final Integer insu) {
        this.insu = insu;
    }

    /**
     * 頭割りを取得する
     *
     * @return 頭割り
     */
    public String getAverage() {
        return average;
    }

    /**
     * 頭割りを設定する
     *
     * @param average 頭割り
     */
    public void setAverage(final String average) {
        this.average = average;
    }

}
