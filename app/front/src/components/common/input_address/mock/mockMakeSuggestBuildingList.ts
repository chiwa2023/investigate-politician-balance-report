import type PostalCodeBuildingResultInterface from "../../../../dto/postal/postalCodeBuildingResultDto";
import PostalCodeBuildingResultDto from "../../../../dto/postal/postalCodeBuildingResultDto";
import type SelectOptionStringInterface from "../../../../dto/selectOptionStringDto";
import SelectOptionStringDto from "../../../../dto/selectOptionStringDto";

export default function mockMakeSuggestBuildingList():PostalCodeBuildingResultInterface{

    const list:SelectOptionStringInterface[] = [];

    const dto0:SelectOptionStringInterface = new SelectOptionStringDto();

    const dto1:SelectOptionStringInterface = new SelectOptionStringDto();
    dto1.value = "三角ビル1F";
    dto1.text= "三角ビル1F";

    const dto2:SelectOptionStringInterface = new SelectOptionStringDto();
    dto2.value = "三角ビル屋上";
    dto2.text= "三角ビル屋上";

    list.push(dto0);
    list.push(dto1);
    list.push(dto2);


    const resultDto:PostalCodeBuildingResultInterface = new PostalCodeBuildingResultDto();

    resultDto.listOptions = list;
    resultDto.lgCode = "123123";

    return resultDto;

}