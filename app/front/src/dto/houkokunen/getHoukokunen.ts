import SelectOptionInterface from "../selectOptionDto";
import SelectOptionDto from "../selectOptionDto";

/** 報告年を作成する */
export default function getHoukokunen(): SelectOptionInterface[] {
    const now: Date = new Date();
    const list: SelectOptionInterface[] = [];
    for (let year = 2010; year < now.getFullYear(); year++) {
        list.push(createOption(year));
    }
    return list;
}

function createOption(year: number) {
    const dto: SelectOptionInterface = new SelectOptionDto();
    const valueText: string = String(year);
    dto.value = valueText;
    dto.text = valueText;
    return dto;
}