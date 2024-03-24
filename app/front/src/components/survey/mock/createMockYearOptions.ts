import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";

/**
 * 提出年選択肢を生成する
 * @returns 提出年リスト 
 */
export default function createMockYearOptions(): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    list.push(createDto(2022));
    list.push(createDto(2022));
    list.push(createDto(2021));
    return list;
}

/**
 *  セレクトボックス選択肢Dtoを生成する
 * @param year 提出年
 * @returns 選択肢Dto
 */
function createDto(year: number): SelectOptionDto {

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = year + "";
    dto.text = year + "年";

    return dto;
}