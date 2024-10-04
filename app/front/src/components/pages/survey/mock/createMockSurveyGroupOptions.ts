import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

/**
 * 調査グループ選択肢を生成する
 * @returns 調査グループ選択肢
 */
export default function createMockSurveyGroupOptions(): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    list.push(createDto(11, "調査側共通指標"));
    list.push(createDto(22, "オンブズマンAスペシャル"));
    list.push(createDto(33, "オンブズマンB特殊指標"));
    list.push(createDto(44, "シン・AI補助パターン"));
    return list;
}

/**
 * セレクトボックス選択肢Dtoを生成する
 * @param surveyGroupId 調査グループId 
 * @param surveyName 調査名称
 * @returns 選択肢Dto
 */
function createDto(surveyGroupId: number, surveyName: string): SelectOptionDto {

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = surveyGroupId + "";
    dto.text = surveyName;

    return dto;
}