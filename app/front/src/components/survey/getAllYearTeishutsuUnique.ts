import SelectOptionInterface from "../../dto/selectOptionDto";
import SelectOptionDto from "../../dto/selectOptionDto";
import SurveySelecterInterface from "../../dto/survey/surveySelecterDto";

export default function getAllYearTeishutsuUnique(selectorList: SurveySelecterInterface[]): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];

    const storeId:number[] = []; 
    for (const dto of selectorList) {
        if(!storeId.includes(dto.yearTeishutsu)){
            list.push(createDto(dto.yearTeishutsu, dto.yearTeishutsuText));
            storeId.push(dto.yearTeishutsu);
        }
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