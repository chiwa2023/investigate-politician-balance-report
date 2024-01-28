import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto"

export default function createMockSurveyGroupOptions(): SelectOptionDto[] {
    const list: SelectOptionInterface[] = [];
    list.push(createDto(11, "調査側共通指標"));
    list.push(createDto(22, "オンブズマンAスペシャル"));
    list.push(createDto(33, "オンブズマンB特殊指標"));
    list.push(createDto(44, "シン・AI補助パターン"));
    return list;
}

function createDto(surveyGroupId: number, surveyName: string): SelectOptionDto {

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = surveyGroupId + "";
    dto.text = surveyName;

    return dto;
}