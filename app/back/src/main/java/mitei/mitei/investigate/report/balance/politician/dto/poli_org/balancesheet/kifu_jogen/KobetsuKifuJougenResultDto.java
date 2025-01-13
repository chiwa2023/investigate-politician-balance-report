package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen;

import java.io.Serializable;

/**
 * 寄付上限個別制限検索結果Dto
 */
public class KobetsuKifuJougenResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 政治団体解析結果 */
    private SearchKifuJougenMeisaiBalancesheetResultDto resultOrgDto;

    /** 個人解析結果 */
    private SearchKifuJougenMeisaiBalancesheetResultDto resultPersonalDto;

    /**
     * 政治団体解析結果を取得する
     *
     * @return 政治団体解析結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto getResultOrgDto() {
        return resultOrgDto;
    }

    /**
     * 政治団体解析結果を設定する
     *
     * @param resultOrgDto 政治団体解析結果
     */
    public void setResultOrgDto(final SearchKifuJougenMeisaiBalancesheetResultDto resultOrgDto) {
        this.resultOrgDto = resultOrgDto;
    }

    /**
     * 個人解析結果を取得する
     *
     * @return 個人解析結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto getResultPersonalDto() {
        return resultPersonalDto;
    }

    /**
     * 個人解析結果を設定する
     *
     * @param resultPersonalDto 個人解析結果
     */
    public void setResultPersonalDto(final SearchKifuJougenMeisaiBalancesheetResultDto resultPersonalDto) {
        this.resultPersonalDto = resultPersonalDto;
    }

}
