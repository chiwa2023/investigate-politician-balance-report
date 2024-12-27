import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockGetPoliParty():SelectOptionInterface[]{

    const list:SelectOptionInterface[] = [];

    list.push(createOption(0,"指定なし"));
    list.push(createOption(123,"動物園軍団"));
    list.push(createOption(987,"チーム植物園"));

    return list;
}

function createOption(value: number,text:string) {
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = String(value);
    dto.text = text;
    return dto;
}