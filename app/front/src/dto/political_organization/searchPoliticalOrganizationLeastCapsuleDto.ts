import AbstactCapsuleDto from "../abstactCapsuleDto";

export default class SearchPoliticalOrganizationLeastCapsuleDto extends AbstactCapsuleDto {

    /** 検索語 */
    searchWords: string;

    /** 結果に履歴データも含める該否 */
    isHisory: boolean;

    constructor() {
        super();
        this.searchWords = "";
        this.isHisory = false;
    }
}