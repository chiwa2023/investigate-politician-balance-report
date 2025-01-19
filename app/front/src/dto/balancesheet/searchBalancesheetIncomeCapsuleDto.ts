import AbstractCapsuleDto from "../abstractCapsuleDto";

export default class SearchBalancesheetIncomeCapsuleDto extends AbstractCapsuleDto{

    /** 検索語 */    
    searchWords:string;

    constructor(){
        super();
        this.searchWords = "";
    }
}