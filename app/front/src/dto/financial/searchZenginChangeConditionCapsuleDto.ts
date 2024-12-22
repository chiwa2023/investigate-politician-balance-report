import AbstactCapsuleDto from "../abstactCapsuleDto";



export default class SearchZenginChangeConditionCapsuleDto extends AbstactCapsuleDto {

    /** 未処理フラグ */
    isMishoriOnly: boolean;

    /** 検索期間開始 */
    startDate: string;

    /** 検索期間終了 */
    endDate: string;

    /** 検索期間開始 */
    financialCode: string;

    /** 該当変更区分リスト */
    listChangeKbn: number[];

    constructor() {
        super();
        
        this.isMishoriOnly = true;
        this.startDate = "";
        this.endDate = "";
        this.financialCode = "";
        this.listChangeKbn = [];

    }
}