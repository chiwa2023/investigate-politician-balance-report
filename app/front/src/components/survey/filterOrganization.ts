import SelectOptionInterface from "../../dto/selectOptionDto";
import SelectOptionDto from "../../dto/selectOptionDto";
import SurveySelecterInterface from "../../dto/survey/surveySelecterDto";

export default function filterOrganization(selectorList: SurveySelecterInterface[],year:number): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    const baseList = selectorList.filter((dto) => dto.yearTeishutsu === year);

    for (const dto of baseList) {
        list.push(createDto(dto.organizaitionId, dto.organizaitionName));
    }
    return list;
}

function createDto(id: number, name: string): SelectOptionDto {
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = id + "";
    dto.text = name;
    return dto;
}