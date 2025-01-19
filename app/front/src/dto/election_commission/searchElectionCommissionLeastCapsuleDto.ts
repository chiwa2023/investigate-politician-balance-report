import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchElectionCommissionLeastCapsuleDto extends AbstractCapsuleDto{

    /** 検索語 */    
    searchWords:string;

    constructor(){
        super();
        this.searchWords = "";
    }
}