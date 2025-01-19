import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchPoliticianAllLeastCapsuleDto extends AbstractCapsuleDto {

    /** 検索語 */
    searchWords: string;

    /** 国会議員該否 */
    isKokkaiGiin: boolean;

    constructor() {
        super();
        this.searchWords = "";
        this.isKokkaiGiin = false;
    }
}