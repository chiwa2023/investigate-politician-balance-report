import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockMakeSuggestBuildingList():SelectOptionInterface[]{

    const list:SelectOptionInterface[] = [];

    const dto0:SelectOptionInterface = new SelectOptionDto();

    const dto1:SelectOptionInterface = new SelectOptionDto();
    dto1.value = "三角ビル1F";
    dto1.text= "三角ビル1F";

    const dto2:SelectOptionInterface = new SelectOptionDto();
    dto2.value = "三角ビル屋上";
    dto2.text= "三角ビル屋上";

    list.push(dto0);
    list.push(dto1);
    list.push(dto2);

    return list;

}