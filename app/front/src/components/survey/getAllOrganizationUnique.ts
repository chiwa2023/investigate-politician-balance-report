import SelectOptionInterface from "../../dto/selectOptionDto";
import SelectOptionDto from "../../dto/selectOptionDto";
import SurveySelecterInterface from "../../dto/survey/surveySelecterDto";

/**
 * 組織名で重複のないリストを生成すつ
 * @param selectorList 全調査リスト
 * @returns セレクトボックス選択肢
 */
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