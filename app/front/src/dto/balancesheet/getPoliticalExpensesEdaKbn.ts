import SelectOptionInterface from "../selectOptionDto";
import SelectOptionDto from "../selectOptionDto";
import YoushikiEdaKbnOutcomeConstants from "./youshikiEdaKbnOutcomeConstants";

/**
 * 収支報告書収入データの収支報告書支出仕訳項目リストを取得する
 * @returns 収支報告書支出仕訳項目リスト
 */
export default function getPoliticalExpensesEdaKbn(): SelectOptionInterface[] {
    const list: SelectOptionDto[] = [];
    list.splice(0);

    list.push(createDto(YoushikiEdaKbnOutcomeConstants.ACTIVATION, YoushikiEdaKbnOutcomeConstants.ACTIVATION_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.ELECTION, YoushikiEdaKbnOutcomeConstants.ELECTION_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.PAPER, YoushikiEdaKbnOutcomeConstants.PAPER_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.COMERCIAL, YoushikiEdaKbnOutcomeConstants.COMERCIAL_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.PARTY, YoushikiEdaKbnOutcomeConstants.PARTY_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.BUISSINESS, YoushikiEdaKbnOutcomeConstants.BUISSINESS_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.RESEARCH, YoushikiEdaKbnOutcomeConstants.RESEARCH_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.DONATION, YoushikiEdaKbnOutcomeConstants.DONATION_TEXT));
    list.push(createDto(YoushikiEdaKbnOutcomeConstants.OTHER, YoushikiEdaKbnOutcomeConstants.OTHER_TEXT));

    return list;
}

/**
 * 選択肢を生成する
 * @param value オプション項目の値
 * @param text オプション項目の表示テキスト
 * @returns セレクトボックス選択肢Dto
 */
function createDto(value: string, text: string): SelectOptionDto {

    const dto: SelectOptionDto = new SelectOptionDto();
    dto.value = value;
    dto.text = text;

    return dto;
}