import SurveyIndicatorInterface from "../../../dto/surveyIndicatorDto";
import SurveyIndicatorDto from "../../../dto/surveyIndicatorDto";

export default function getMockSruveyView(groupId: number): SurveyIndicatorInterface[] {
    const list: SurveyIndicatorDto[] = [];

    switch (groupId) {
        case 11:
            list.push(createDto(9));
            break;
        case 22:
            list.push(createDto(1000));
            list.push(createDto(2000));
            break;
        case 33:
            list.push(createDto(11));
            list.push(createDto(12));
            list.push(createDto(13));
            break;
        case 44:
            list.push(createDto(101));
            list.push(createDto(202));
            list.push(createDto(303));
            list.push(createDto(404));
            break;
    }
    return list;
}

function createDto(id:number): SurveyIndicatorDto {

    const indicatorValue: SurveyIndicatorDto = new SurveyIndicatorDto();
    indicatorValue.indicatorExplain = "計算式の説明。aaa=4x+y";
    indicatorValue.indicatorTitle = "調査指標"+id;
    indicatorValue.indicatorAnswer = 99999;
    indicatorValue.indicatorAnswerText = "99,999";
    indicatorValue.indicatorUnit = "%";
    indicatorValue.indicatorResultDetail = "適当な説明"+id;

    return indicatorValue;
}