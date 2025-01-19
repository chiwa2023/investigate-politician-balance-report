import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchPoliticalOrganizationLeastCapsuleDto extends AbstractCapsuleDto{

    /** 検索語 */    
    searchWords:string;

    constructor(){
        super();
        this.searchWords = "";
    }
}