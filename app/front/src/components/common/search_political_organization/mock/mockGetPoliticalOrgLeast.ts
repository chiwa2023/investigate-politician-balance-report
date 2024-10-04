import PoliticalOrganizationLeastInterface from "../../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../../dto/political_organization/politicalOrganizationLeastDto";

/**
 * 政治団体mockリストを返却する
 * @returns リスト
 */
export default function mockGetPoliticalOrgLeast():PoliticalOrganizationLeastInterface[]{
    const list:PoliticalOrganizationLeastInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));

    return list;
}

/**
 * 行を生成する
 * @param index 発生index
 * @returns Dto
 */
function createDto(index:number):PoliticalOrganizationLeastInterface{
    
    const dto:PoliticalOrganizationLeastInterface = new PoliticalOrganizationLeastDto();
    
    dto.politicalOrganizationCode = index;
    dto.politicalOrganizationId = index*5;
    dto.politicalOrganizationName = index+"政治団体";

    return dto;
}