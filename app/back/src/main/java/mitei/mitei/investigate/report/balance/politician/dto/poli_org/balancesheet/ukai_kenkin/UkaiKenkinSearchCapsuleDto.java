package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateWithInTaskPlanCodeCapsuleDto;

/**
 * 迂回献金検索条件Dto
 */
public class UkaiKenkinSearchCapsuleDto extends TemplateWithInTaskPlanCodeCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 迂回献金検出条件Dto */
    private UkaiKenkinConditionCapsuleDto ukaiKenkinConditionCapsuleDto;

    /**
     * 迂回献金検索条件Dtoを取得する
     *
     * @return 迂回献金検索条件Dto
     */
    public UkaiKenkinConditionCapsuleDto getUkaiKenkinConditionCapsuleDto() {
        return ukaiKenkinConditionCapsuleDto;
    }

    /**
     * 迂回献金検索条件Dtoを設定する
     *
     * @param ukaiKenkinConditionCapsuleDto 迂回献金検索条件Dto
     */
    public void setUkaiKenkinConditionCapsuleDto(final UkaiKenkinConditionCapsuleDto ukaiKenkinConditionCapsuleDto) {
        this.ukaiKenkinConditionCapsuleDto = ukaiKenkinConditionCapsuleDto;
    }

    /** 表示件数 */
    private int countAll = INIT_Integer;

    /** 件数上限 */
    private int limit = INIT_Integer;

    /** 検索位置 */
    private int pageNumber = INIT_Integer;

    /**
     * 表示件数を取得する
     *
     * @return 表示件数
     */
    public int getCountAll() {
        return countAll;
    }

    /**
     * 表示件数を設定する
     *
     * @param countAll 表示件数
     */
    public void setCountAll(final int countAll) {
        this.countAll = countAll;
    }

    /**
     * 件数上限を取得する
     *
     * @return 件数上限
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 件数上限を設定する
     *
     * @param limit 件数上限
     */
    public void setLimit(final int limit) {
        this.limit = limit;
    }

    /**
     * 検索位置を取得する
     *
     * @return 検索位置
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 検索位置を設定する
     *
     * @param pageNumber 検索位置
     */
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

}
