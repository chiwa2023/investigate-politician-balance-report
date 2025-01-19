import AbstractCapsuleDto from "../abstractCapsuleDto";
import FukisaiSearchConditionInterface from "./fukisaiSearchConditionDto";
import FukisaiSearchConditionDto from "./fukisaiSearchConditionDto";

export default interface SearchBalancesheetFukisaiCapsuleInterface {

}

export default class SearchBalancesheetFukisaiCapsuleDto extends AbstractCapsuleDto implements SearchBalancesheetFukisaiCapsuleInterface {

    /** 不記載検出検索条件 */
    fukisaiSearchConditionDto: FukisaiSearchConditionInterface;

    constructor() {
        super();
        this.fukisaiSearchConditionDto = new FukisaiSearchConditionDto();

    }
}