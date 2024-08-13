import AbstactCapsuleDto from "../abstactCapsuleDto";

export default class SearchPoliticianAllLeastCapsuleDto extends AbstactCapsuleDto {

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