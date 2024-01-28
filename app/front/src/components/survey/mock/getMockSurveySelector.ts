import SurveySelecterInterface from "../../../dto/survey/surveySelecterDto";
import SurveySelecterDto from "../../../dto/survey/surveySelecterDto";

export default function createMockOrganizationOptions():SurveySelecterInterface[]{
    const list: SurveySelecterInterface[] = [];
    list.push(createDto(2022,111,"所属政党支部資金管理団体報告"));
    list.push(createDto(2021,111,"所属政党支部資金管理団体報告"));
    list.push(createDto(2019,111,"所属政党支部資金管理団体報告"));
    list.push(createDto(2018,111,"所属政党支部資金管理団体報告"));

    list.push(createDto(2022,222,"政治太郎さんを応援する会"));
    list.push(createDto(2021,222,"政治太郎さんを応援する会"));

    list.push(createDto(2022,333,"政治太郎本人団体"));
    list.push(createDto(2020,333,"政治太郎本人団体"));
    list.push(createDto(2019,333,"政治太郎本人団体"));
    return list;
}

function createDto(year:number,orgId:number,orgName:string):SurveySelecterDto{

    const dto: SurveySelecterDto = new SurveySelecterDto();
    dto.yearTeishutsu = year;
    dto.yearTeishutsuText = year + "年";
    dto.organizaitionId = orgId;
    dto.organizaitionName = orgName;

    return dto;
}