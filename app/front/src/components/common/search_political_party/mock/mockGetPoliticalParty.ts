import PoliticalPartyLeastInterface from "../../../../dto/political_party/politicalPartyLeastDto";
import PoliticalPartyLeastDto from "../../../../dto/political_party/politicalPartyLeastDto";

/**
 * 選挙管理委員会mockリストを返却する
 * @returns リスト
 */
export default function mockGetPoliticalParty():PoliticalPartyLeastInterface[]{
    const list:PoliticalPartyLeastInterface[] = [];

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
function createDto(index:number):PoliticalPartyLeastInterface{
    
    const dto:PoliticalPartyLeastInterface = new PoliticalPartyLeastDto();
    
    dto.politicalPartyCode = index;
    dto.politicalPartyId = index*5;
    dto.politicalPartyName = "政党"+index;

    return dto;
}