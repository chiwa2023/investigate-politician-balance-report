import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

/**
 * 組織セレクトボックスを生成する
 * @returns 組織セレクトボックス選択肢
 */
export default function createMockOrganizationOptions(): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    list.push(createDto(111, "所属政党支部資金管理団体報告"));
    list.push(createDto(222, "政治太郎さんを応援する会"));
    list.push(createDto(333, "政治太郎本人団体"));
    return list;
}

//export default function createMockOrganizationOptions2(selectorList:SurveySelecterInterface[],year:number):SelectOptionDto[]{
//    const list: SelectOptionInterface[] = [];
//
//    const baseList: SurveySelecterInterface[] = selectorList.filter((dto) => dto.yearTeishutsu === year);
//    for(const dto of baseList){
//        list.push(createDto(dto.organizaitionId,dto.organizaitionName));
//    }
//    return list;
//}

/**
 * セレクトボックス選択肢Dtoを生成する
 * @param id 値
 * @param name 表示テキスト
 * @returns 選択肢Dto
 */
function createDto(id: number, name: string): SelectOptionDto {

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = id + "";
    dto.text = name;

    return dto;
}