import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchKanrenshaLeastCapsuleDto extends AbstractCapsuleDto {

    /** 検索語 */
    searchWords: string;

    /** 個人検索有無 */
    isSearchPerson: boolean;

    /** 企業団体検索有無 */
    isSearchCorp: boolean;

    /** 政治団体検索有無 */
    isSearchPoliOrg: boolean;


    constructor() {
        super();
        const INIT_BOOLEAN = true;

        this.searchWords = "";
        this.isSearchPerson = INIT_BOOLEAN;
        this.isSearchCorp = INIT_BOOLEAN;
        this.isSearchPoliOrg = INIT_BOOLEAN;
    }
}