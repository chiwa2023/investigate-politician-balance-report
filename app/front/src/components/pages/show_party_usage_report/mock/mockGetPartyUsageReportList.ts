import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockGetPartyUsageReportList(): SelectOptionInterface[]{

    const list: SelectOptionInterface[] = [];

    list.push(createDto(4));
    list.push(createDto(3));
    list.push(createDto(2));

    return list;
}


function createDto(index:number){

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = String(index);
    dto.text = "202"+index + "年:" + "202"+(index+1)+"年3月"+(index+10)+"日提出";
    
    return dto;
}