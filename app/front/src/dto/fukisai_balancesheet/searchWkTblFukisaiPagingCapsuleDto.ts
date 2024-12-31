import AbstactCapsuleDto from "../abstactCapsuleDto";

export default interface SearchWkTblFukisaiPagingCapsuleInterface {

}

export default class SearchWkTblFukisaiPagingCapsuleDto extends AbstactCapsuleDto implements SearchWkTblFukisaiPagingCapsuleInterface {

    /** 政治団体同一識別コード */
    poliOrgCode: number;

    /** 取得全件件数 */
    countAll: number;

    /** ページ番号 */
    pageNumber: number;

    /** タスク計画同一識別コード */
    taskPlanCode: number;

    /** タスク登録年 */
    year: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.poliOrgCode = INIT_NUMBER;
        this.countAll = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.taskPlanCode = INIT_NUMBER
        this.year = INIT_NUMBER;
    }
}