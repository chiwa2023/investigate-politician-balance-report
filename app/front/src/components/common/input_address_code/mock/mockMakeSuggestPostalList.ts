import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockMakeSuggestPostalList():SelectOptionInterface[]{

    const list:SelectOptionInterface[] = [];

    const dto0:SelectOptionInterface = new SelectOptionDto();

    const dto1:SelectOptionInterface = new SelectOptionDto();
    dto1.value = "山形県実在市岸辺町";
    dto1.text= "山形県実在市岸辺町";

    const dto2:SelectOptionInterface = new SelectOptionDto();
    dto2.value = "山形県実在市山麓町";
    dto2.text= "山形県実在市山麓町";

    const dto3:SelectOptionInterface = new SelectOptionDto();
    dto3.value = "山形県実在市湖畔町";
    dto3.text= "山形県実在市湖畔町";

    list.push(dto0);
    list.push(dto1);
    list.push(dto2);
    list.push(dto3);

    return list;
}