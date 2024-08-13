import PoliticianAllLeastInterface from "../../../../dto/politician_all/politicianAllLeastDto";
import PoliticianAllLeastDto from "../../../../dto/politician_all/politicianAllLeastDto";

/**
 * 政治家mockリストを返却する
 * @returns リスト
 */
export default function mockGetPoliticianAll(isKokkaiGiin: boolean): PoliticianAllLeastInterface[] {
    const list: PoliticianAllLeastInterface[] = [];

    list.push(createDto(1,isKokkaiGiin));
    list.push(createDto(2,isKokkaiGiin));
    list.push(createDto(3,isKokkaiGiin));
    list.push(createDto(4,isKokkaiGiin));

    return list;
}

/**
 * 行を生成する
 * @param index 発生index
 * @returns Dto
 */
function createDto(index: number,isKokkaiGiin: boolean): PoliticianAllLeastInterface {

    const dto: PoliticianAllLeastInterface = new PoliticianAllLeastDto();

    dto.politicianAllCode = index;
    dto.politicianAllId = index * 5;
    dto.politicianAllName = "政治家　太郎" + index;
    dto.politicianAllNameKana = "せいじか　たろう" + index;
    dto.parliamentOrganizationCode = index;
    if (isKokkaiGiin) {
        dto.parliamentOrganizationId = index % 2 + 1;//仮で奇数…衆議院、偶数参議院
    } else {
        dto.parliamentOrganizationId = index * 5;
    }
    dto.parliamentOrganizationName = "議会" + index;
    dto.electionDistrictCode = index;
    dto.electionDistrictId = index * 5;
    dto.electionDistrictName = "議会選挙区" + index;

    return dto;
}