import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockGetUkaikenkinRoutePageList():SelectOptionInterface[]{

    const list:SelectOptionInterface[] = [];

    list.push(createDto(   0,"経路なし関係者寄付"));
    list.push(createDto(   1,"経路(1)ABCD団体-->関連団体A"));
    list.push(createDto(   2,"経路(2)ABCD団体-->関連団体B"));
    list.push(createDto(   3,"経路(3)ABCD団体-->関連団体C"));
    list.push(createDto(   4,"経路(4)ABCD団体-->関連団体D"));

    return list;
}

function createDto(value:number,text:string):SelectOptionInterface{
    const dto:SelectOptionInterface = new SelectOptionDto();

    dto.value = String(value);
    dto.text = text;
    return dto;
}