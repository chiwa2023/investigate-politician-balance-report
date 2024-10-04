import PoliticalOrganizationLeastInterface from "../political_organization/politicalOrganizationLeastDto";
import LinkBalancesheetVersionDto from "./linkBalancesheetVersionDto";

/**
 * 新しい版管理Dtoを生成する
 * @param id データId(back側で取得しなおす仮の値)
 * @param code データ同一識別コード(back側で取得しなおす仮の値)
 * @param politicalDto 政治団体最低限情報
 * @param year 提出年
 * @param times 提出回数
 * @param offeringDate 提出日付
 * @returns 版Dto
 */
export default function createNewLinkversionDto(id: number, code: number, politicalDto: PoliticalOrganizationLeastInterface, year: number, times: number, offeringDate: string): LinkBalancesheetVersionDto {

    const addDto: LinkBalancesheetVersionDto = new LinkBalancesheetVersionDto();

    const initDate: string = "1980-01-01";
    const initString: string = "";
    const initInt: number = 0;

    addDto.linkBalancesheetVersionId = id;
    addDto.linkBalancesheetVersionCode = code;
    addDto.politicalOrgnaizationId = politicalDto.politicalOrganizationId;
    addDto.politicalOrgnaizationCode = politicalDto.politicalOrganizationCode;
    addDto.politicalOrgnaizationName = politicalDto.politicalOrganizationName;
    addDto.offeringYear = year;
    addDto.offeringTimes = times;
    addDto.offeringDate = offeringDate;
    addDto.publicationDate = initDate;
    addDto.isDataSetFormal = false;
    addDto.isDataSetInformal = false;
    addDto.electionCommitionId = initInt;
    addDto.electionCommitionCode = initInt;
    addDto.electionCommitionName = initString;
    addDto.publicationLinkUrl = initString;
    addDto.politicalOrgnaizationId = initInt;
    addDto.politicalOrgnaizationCode = initInt;

    return addDto;
}