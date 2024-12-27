import AbstactCapsuleDto from "../abstactCapsuleDto";
import SearchMembershipFeeConditionDto from "./searchMembershipFeeConditionDto";

export default class MembershipFeeSearchCapsuleDto extends AbstactCapsuleDto{

    searchConditionDto:SearchMembershipFeeConditionDto;

    constructor(){
        super();
        this.searchConditionDto = new SearchMembershipFeeConditionDto();
    }
}