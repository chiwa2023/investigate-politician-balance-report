import SelectOptionInterface from "../../dto/selectOptionDto";
import SelectOptionDto from "../../dto/selectOptionDto";
import SurveySelecterInterface from "../../dto/survey/surveySelecterDto";

export default function filterYearTeishutsu(selectorList: SurveySelecterInterface[],id:number): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    const baseList = selectorList.filter((dto) => dto.organizaitionId === id);

    for (const dto of baseList) {
        list.push(createDto(dto.yearTeishutsu, dto.yearTeishutsuText));
    }

    list.sort((dtoA,dtoB) => { 
        if(dtoA.value > dtoB.value){
            return -1;
        }
        if(dtoA.value < dtoB.value){
            return 1;
        }
        return 0;
    });

    return list;
}

function createDto(id: number, name: string): SelectOptionDto {
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = id + "";
    dto.text = name;
    return dto;
}