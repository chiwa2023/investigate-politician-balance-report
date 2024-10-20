import SelectOptionInterface from "../selectOptionDto";
import SelectOptionDto from "../selectOptionDto";

export default function createPagingSelectBoxOption(countAll: number, limit: number): SelectOptionInterface[] {
    const list: SelectOptionInterface[] = [];

    // 一件も取得できない場合は未取得のみで処理終了
    if(countAll === 0){
        const page1: SelectOptionInterface = new SelectOptionDto();
        page1.value = "-1";
        page1.text = "未検索またはヒットなし";
        list.push(page1);
        return list;
    }

    // 全件数に合わせてループ
    const loopCount = Math.trunc(countAll / limit);
    for (let index = 1; index <= loopCount; index++) {
        const page: SelectOptionInterface = new SelectOptionDto();
        page.value = String((index - 1) * limit);
        page.text = ((index - 1) * limit + 1) + "から" + (index * limit) + "まで";
        list.push(page);
    }

    // 余りを追加
    if(0 !== countAll % limit){
        const pageLast: SelectOptionInterface = new SelectOptionDto();
        pageLast.value = String(loopCount * limit);
        pageLast.text = (loopCount * limit + 1) + "から" + countAll + "まで";
        list.push(pageLast);
    }

    return list;
}