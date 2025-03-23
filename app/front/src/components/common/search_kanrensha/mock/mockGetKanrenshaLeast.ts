import KanrenshaLeastInterface from "../../../../dto/kanrensha/kanrenshaLeastDto";
import KanrenshaLeastDto from "../../../../dto/kanrensha/kanrenshaLeastDto";
import SearchKanrenshaLeastCapsuleDto from "../../../../dto/kanrensha/searchKanrenshaLeastCapsuleDto";

export default function mockGetKanrenshaLeast(searchDto: SearchKanrenshaLeastCapsuleDto): KanrenshaLeastInterface[] {

    const list: KanrenshaLeastInterface[] = [];
    if (searchDto.isSearchPerson) {
        list.push(createDto(1, "個人"+searchDto.searchWords + "花子", 1))
        list.push(createDto(2, "個人"+searchDto.searchWords + "花子", 1))
    }

    if (searchDto.isSearchCorp) {
        list.push(createDto(3, searchDto.searchWords + "会社", 2))
        list.push(createDto(4, searchDto.searchWords + "会社", 2))
    }

    if (searchDto.isSearchPoliOrg) {
        list.push(createDto(5, searchDto.searchWords + "団体", 3))
        list.push(createDto(6, searchDto.searchWords + "団体", 3))
    }
    return list;
}

function createDto(index: number, name: string, kbn: number): KanrenshaLeastInterface {

    const dto: KanrenshaLeastInterface = new KanrenshaLeastDto();

    dto.relationId = index * 10 + index * 2;
    dto.relationCode = index * 10 + index;
    dto.relationName = name + index;
    dto.relationKbn = kbn;

    return dto;
}