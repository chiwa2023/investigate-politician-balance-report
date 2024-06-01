import ElectionCommissionLeastInterface from "../../../../dto/election_commission/electionCommissionDto";
import ElectionCommissionLeastDto from "../../../../dto/election_commission/electionCommissionDto";

/**
 * 選挙管理委員会mockリストを返却する
 * @returns リスト
 */
export default function mockGetElectionCommissionLeast():ElectionCommissionLeastInterface[]{
    const list:ElectionCommissionLeastInterface[] = [];

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
function createDto(index:number):ElectionCommissionLeastInterface{
    
    const dto:ElectionCommissionLeastInterface = new ElectionCommissionLeastDto();
    
    dto.electionCommissionCode = index;
    dto.electionCommissionId = index*5;
    dto.electionCommissionName = "選挙管理委員会"+index;

    return dto;
}