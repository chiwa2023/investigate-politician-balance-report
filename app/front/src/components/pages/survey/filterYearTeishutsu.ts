import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";
import SurveySelecterInterface from "../../../dto/survey/surveySelecterDto";

/**
 * 提出年にフィルタを行う
 * @param selectorList 調査可能リスト
 * @param id 選択肢の値
 * @returns 選択肢のDto 
 */
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
/**
 * 選択肢Dtoを生成する
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