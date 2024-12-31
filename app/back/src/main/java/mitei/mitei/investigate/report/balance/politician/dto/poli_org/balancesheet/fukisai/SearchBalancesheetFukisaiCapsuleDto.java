package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 不記載検出検索条件Dto
 */
public class SearchBalancesheetFukisaiCapsuleDto extends AbstractCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 不記載検出検索条件 */
    private FukisaiSearchConditionDto fukisaiSearchConditionDto;

    /**
     * 不記載検出検索条件を取得する
     *
     * @return 不記載検出検索条件
     */
    public FukisaiSearchConditionDto getFukisaiSearchConditionDto() {
        return fukisaiSearchConditionDto;
    }

    /**
     * 不記載検出検索条件を設定する
     *
     * @param fukisaiSearchConditionDto 不記載検出検索条件
     */
    public void setFukisaiSearchConditionDto(final FukisaiSearchConditionDto fukisaiSearchConditionDto) {
        this.fukisaiSearchConditionDto = fukisaiSearchConditionDto;
    }

}
