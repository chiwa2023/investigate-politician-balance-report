import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchPoliticalPartyLeastCapsuleDto extends AbstractCapsuleDto{

    /** 検索語 */    
    searchWords:string;

    constructor(){
        super();
        this.searchWords = "";
    }
}