import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto"

export default function createMockYearOptions():SelectOptionDto[]{
    const list: SelectOptionInterface[] = [];
    list.push(createDto(2022));
    list.push(createDto(2022));
    list.push(createDto(2021));
    return list;
}

function createDto(year:number):SelectOptionDto{

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = year+"";
    dto.text = year + "年";

    return dto;
}