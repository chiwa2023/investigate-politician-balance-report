import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockMakeSuggestBlockList():SelectOptionInterface[]{

    const list:SelectOptionInterface[] = [];

    const dto0:SelectOptionInterface = new SelectOptionDto();

    const dto1:SelectOptionInterface = new SelectOptionDto();
    dto1.value = "1番地2";
    dto1.text= "1番地2";

    const dto2:SelectOptionInterface = new SelectOptionDto();
    dto2.value = "2番地3";
    dto2.text= "2番地3";

    const dto3:SelectOptionInterface = new SelectOptionDto();
    dto3.value = "1990番地";
    dto3.text= "1990番地";

    list.push(dto0);
    list.push(dto1);
    list.push(dto2);
    list.push(dto3);


    return list;

}