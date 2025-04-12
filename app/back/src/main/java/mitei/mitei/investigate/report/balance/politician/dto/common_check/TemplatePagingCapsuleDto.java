package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * ページング機能のみ検索条件格納Dto
 */
public class TemplatePagingCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final Integer INIT_Integer = 0;

    /** ページング件数 */
    private Integer offset = INIT_Integer;

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

    /**
     * ページング件数を取得する
     *
     * @return ページング件数
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * ページング件数を設定する
     *
     * @param offset ページング件数
     */
    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数
     */
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}
