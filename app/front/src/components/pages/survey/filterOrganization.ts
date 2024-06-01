import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";
import SurveySelecterInterface from "../../../dto/survey/surveySelecterDto";

/**
 * 重複のないセレクトボックス用組織リストを作成する
 * @param selectorList 調査リスト
 * @param year 提出年
 * @returns セレクトボックス選択肢リスト
 */
export default function filterOrganization(selectorList: SurveySelecterInterface[],year:number): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    const baseList = selectorList.filter((dto) => dto.yearTeishutsu === year);

    for (const dto of baseList) {
        list.push(createDto(dto.organizaitionId, dto.organizaitionName));
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