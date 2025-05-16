import type PostalCodeBlockResultInterface from "../../../../dto/postal/postalCodeBlockResultDto";
import PostalCodeBlockResultDto from "../../../../dto/postal/postalCodeBlockResultDto";
import SelectOptionStringInterface from "../../../../dto/selectOptionStringDto";
import SelectOptionStringDto from "../../../../dto/selectOptionStringDto";

export default function mockMakeSuggestBlockList():PostalCodeBlockResultInterface{


    const list:SelectOptionStringInterface[] = [];

    const dto0:SelectOptionStringInterface = new SelectOptionStringDto();

    const dto1:SelectOptionStringInterface = new SelectOptionStringDto();
    dto1.value = "架空市湖畔町1番地2";
    dto1.text= "1番地2";

    const dto2:SelectOptionStringInterface = new SelectOptionStringDto();
    dto2.value = "架空市湖畔町2番地3";
    dto2.text= "2番地3";

    const dto3:SelectOptionStringInterface = new SelectOptionStringDto();
    dto3.value = "架空市湖畔町1990番地";
    dto3.text= "1990番地";

    list.push(dto0);
    list.push(dto1);
    list.push(dto2);
    list.push(dto3);

    const resultDto:PostalCodeBlockResultInterface = new PostalCodeBlockResultDto();
    resultDto.isGyouseikuData = false;
    resultDto.listOptions = list;
    resultDto.lgCode = "123123";
    
    return resultDto;

}