import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface UkaiKenkinConditionCapsuleInterface {

}
export default class UkaiKenkinConditionCapsuleDto extends AbstractCapsuleDto implements UkaiKenkinConditionCapsuleInterface {

    /** 報告年 */
    houkokuNen: number;

    /** 政治団体id */
    poliOrgId: number;

    /** 政治団体同一識別コード */
    poliOrgCode: number;

    /** 政治団体名称 */
    poliOrgName: string;

    /** 検索基準原文書名該否 */
    isNameSearch: boolean;

    /** 抽出回数(想定階層) */
    pickupTimes: number;

    /** 交付金(様式5)検索有無 */
    isKoufukin: boolean;

    /** オフセット件数 */
    offset: number;

    /** ページ番号 */
    pageNum: number;

    constructor() {

        super();

        // 初期データ
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;
        const INIT_STRING: string = "";

        this.houkokuNen = INIT_NUMBER;
        this.poliOrgId = INIT_NUMBER;
        this.poliOrgCode = INIT_NUMBER;
        this.poliOrgName = INIT_STRING;
        this.isNameSearch = INIT_BOOLEAN;
        this.offset = INIT_NUMBER;
        this.pageNum = INIT_NUMBER;
        this.pickupTimes = INIT_NUMBER;
        this.isKoufukin = INIT_BOOLEAN;

    }

}