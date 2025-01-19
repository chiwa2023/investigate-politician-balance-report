import AbstractCapsuleDto from "../abstractCapsuleDto";
import SearchMembershipFeeConditionDto from "./searchMembershipFeeConditionDto";

export default class MembershipFeeSearchCapsuleDto extends AbstractCapsuleDto{

    searchConditionDto:SearchMembershipFeeConditionDto;

    constructor(){
        super();
        this.searchConditionDto = new SearchMembershipFeeConditionDto();
    }
}