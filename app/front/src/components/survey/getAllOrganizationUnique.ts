import SelectOptionInterface from "../../dto/selectOptionDto";
import SelectOptionDto from "../../dto/selectOptionDto";
import SurveySelecterInterface from "../../dto/survey/surveySelecterDto";

export default function getAllOrganizationUnique(selectorList: SurveySelecterInterface[]): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    const storeId:number[] =[];
    for (const dto of selectorList) {
        if(!storeId.includes(dto.organizaitionId)){
            list.push(createDto(dto.organizaitionId, dto.organizaitionName));
            storeId.push(dto.organizaitionId);
        }
    }
    return list;
}
function createDto(id: number, name: string): SelectOptionDto {
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = id + "";
    dto.text = name;
    return dto;
}