import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";

/**
 * CSV指定項目を取得する
 * @returns 選択肢オプション項目
 */
export default function getCsvReadItem(): SelectOptionInterface[] {

    // TODO backと共通の定数値なのでbackから取得することも検討する
    const list: SelectOptionInterface[] = [];

    //| 指定なし`0`
    const dtoNoSelect: SelectOptionDto = new SelectOptionDto();
    dtoNoSelect.value = "0";
    dtoNoSelect.text = "指定なし";

    //| 取引金額支出`1`
    const dtoOutcomeAmount: SelectOptionDto = new SelectOptionDto();
    dtoOutcomeAmount.value = "1";
    dtoOutcomeAmount.text = "取引金額支出";

    //| 取引金額収入`2`
    const dtoIncomeAmount: SelectOptionDto = new SelectOptionDto();
    dtoIncomeAmount.value = "2";
    dtoIncomeAmount.text = "取引金額収入";

    //| 取引金額増減兼用`3`
    const dtoAmount: SelectOptionDto = new SelectOptionDto();
    dtoAmount.value = "3";
    dtoAmount.text = "取引金額増減兼用";

    //| 発生日`15`
    const dtoDate: SelectOptionDto = new SelectOptionDto();
    dtoDate.value = "15";
    dtoDate.text = "発生日";

    //| 摘要`16`
    const dtoDigest: SelectOptionDto = new SelectOptionDto();
    dtoDigest.value = "16";
    dtoDigest.text = "摘要";

    //| 取引相手名称`17`
    const dtoDealer: SelectOptionDto = new SelectOptionDto();
    dtoDealer.value = "17";
    dtoDealer.text = "取引相手名称";

    //作成した項目を追加
    list.push(dtoNoSelect);
    list.push(dtoOutcomeAmount);
    list.push(dtoIncomeAmount);
    list.push(dtoAmount);
    list.push(dtoDate);
    list.push(dtoDigest);
    list.push(dtoDealer);

    return list;
}
