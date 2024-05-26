import SelectOptionInterface from "../selectOptionDto";
import SelectOptionDto from "../selectOptionDto";
import YoushikiEdaKbnOutcomeConstants from "./youshikiEdaKbnOutcomeConstants";

/**
 * 収支報告書支出データ仕訳枝項目(仕訳項目14)選択の選択肢を取得する
 * @returns 選択肢配列
 */
export default function getOrdinaryExpensesEdaKbn(): SelectOptionInterface[] {
    const list: SelectOptionDto[] = [];
    list.splice(0);
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.KOUNETSUHI, YoushikiEdaKbnOutcomeConstants.KOUNETSUHI_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.SHOUMOUHIN, YoushikiEdaKbnOutcomeConstants.SHOUMOUHIN_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.JIMUSHO, YoushikiEdaKbnOutcomeConstants.JIMUSHO_TEXT));

    return list;
}

/**
 * 選択肢Dtoを生成する
 * @param value 選択肢の値
 * @param text 選択肢の表示テキスト
 * @returns 選択肢Dto
 */
function createDto(value: string, text: string): SelectOptionDto {

    const dto: SelectOptionDto = new SelectOptionDto();
    dto.value = value;
    dto.text = text;

    return dto;
}