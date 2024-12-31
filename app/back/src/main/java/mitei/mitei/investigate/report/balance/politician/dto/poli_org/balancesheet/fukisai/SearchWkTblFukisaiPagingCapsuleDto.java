package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 不記載検出ワークテーブル検索ページングDto
 */
public class SearchWkTblFukisaiPagingCapsuleDto extends AbstractCapsuleDto implements Serializable { //NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 政治団体同一識別コード */
    private Integer poliOrgCode;

    /** 取得全件件数 */
    private Integer countAll;

    /** ページ番号 */
    private Integer pageNumber;

    /** タスク計画同一識別コード */
    private Integer taskPlanCode;

    /** タスク登録年 */
    private Integer year;

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
     * 取得可能件数を取得する
     *
     * @return 取得可能件数
     */
    public Integer getCountAll() {
        return countAll;
    }

    /**
     * 取得可能件数を設定する
     *
     * @param countAll 取得可能件数
     */
    public void setCountAll(final Integer countAll) {
        this.countAll = countAll;
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

    /**
     * タスク計画同一識別コードを取得する
     *
     * @return タスク計画同一識別コード
     */
    public Integer getTaskPlanCode() {
        return taskPlanCode;
    }

    /**
     * タスク計画同一識別コードを設定する
     *
     * @param taskPlanCode タスク計画同一識別コード
     */
    public void setTaskPlanCode(final Integer taskPlanCode) {
        this.taskPlanCode = taskPlanCode;
    }

    /**
     * 登録年を取得する
     *
     * @return 登録年
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 登録年を設定する
     *
     * @param year 登録年
     */
    public void setYear(final Integer year) {
        this.year = year;
    }
}
