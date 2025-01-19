import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface KifuJougenConditionCapsuleInterface {

}
/**
 * 寄付条件検索条件Dto
 */
export default class KifuJougenConditionCapsuleDto extends AbstractCapsuleDto implements KifuJougenConditionCapsuleInterface {

    /** 報告年 */
    houkokuNen: number;

    /** 政治団体同一識別コード */
    poliOrgCode: number;

    /** 検索基準原文書名該否 */
    isNameSearch: boolean;

    /** 検索団体区分 */
    seachKifuKbn: number;

    /** 様式枝区分 */
    youshikiEdaKbn: number;

    /** オフセット件数 */
    offset: number;

    /** ページ番号 */
    pageNum: number;

    constructor() {

        super();

        // 初期データ
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.houkokuNen = INIT_NUMBER;
        this.poliOrgCode = INIT_NUMBER;
        this.isNameSearch = INIT_BOOLEAN;
        this.seachKifuKbn = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.offset = INIT_NUMBER;
        this.pageNum = INIT_NUMBER;
    }

}