import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchVariousFinancialPayCapsuleDto extends AbstractCapsuleDto {

    /** 検索語 */
    searchWords: string;

    constructor() {
        super();
        this.searchWords = "";
    }

}
