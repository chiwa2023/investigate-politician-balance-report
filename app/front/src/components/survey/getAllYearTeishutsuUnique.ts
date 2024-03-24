import SelectOptionInterface from "../../dto/selectOptionDto";
import SelectOptionDto from "../../dto/selectOptionDto";
import SurveySelecterInterface from "../../dto/survey/surveySelecterDto";

/**
 *  重複のない提出年リストを作成する
 * @param selectorList 全調査リスト
 * @returns セレクトボックス選択肢
 */
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

/**
 * セレクトボックス選択肢Dtoを生成する
 * @param id 値
 * @param name 表示テキスト
 * @returns Dto
 */
function createDto(id: number, name: string): SelectOptionDto {
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = id + "";
    dto.text = name;
    return dto;
}