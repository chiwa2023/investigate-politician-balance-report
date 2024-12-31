import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";

export default function getPagingOption(count: number, limit: number): SelectOptionInterface[] {

    const list: SelectOptionInterface[] = [];

    const loop: number = count / limit;

    for (let page = 0; page < loop; page++) {

        list.push(createDto(page, limit, count));
    }

    return list;
}

function createDto(page: number, limit: number, count: number) {

    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = String(page);

    const textEnd: number = (page + 1) * limit;
    let lastNumber = "";
    if (textEnd < count) {
        lastNumber = String(textEnd);
    } else {
        lastNumber = String(count);
    }

    dto.text = page * limit + 1 + "から" + lastNumber;

    return dto;
}