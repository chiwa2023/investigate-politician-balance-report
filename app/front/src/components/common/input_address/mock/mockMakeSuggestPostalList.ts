import type PostalCodePostalResultInterface from "../../../../dto/postal/postalCodePostalResultDto";
import PostalCodePostalResultDto from "../../../../dto/postal/postalCodePostalResultDto";
import type SelectOptionNumberInterface from "../../../../dto/selectOptionNumberDto";
import SelectOptionNumberDto from "../../../../dto/selectOptionNumberDto";

export default function mockMakeSuggestPostalList():PostalCodePostalResultInterface{

    const list:SelectOptionNumberInterface[] = [];

    const dto0:SelectOptionNumberInterface = new SelectOptionNumberDto();

    const dto1:SelectOptionNumberInterface = new SelectOptionNumberDto();
    dto1.value = 291;
    dto1.text= "山形県実在市岸辺町";

     const dto2:SelectOptionNumberInterface = new SelectOptionNumberDto();
     dto2.value = 845;
     dto2.text= "山形県実在市山麓町";

    const dto3:SelectOptionNumberInterface = new SelectOptionNumberDto();
    dto3.value = 348;
    dto3.text= "山形県実在市湖畔町";

    list.push(dto0);
    list.push(dto1);
    list.push(dto2);
    list.push(dto3);

    const resultDto:PostalCodePostalResultInterface = new PostalCodePostalResultDto();
    resultDto.listOptions = list;
    resultDto.isGyouseikuData = true;

    return resultDto;
}