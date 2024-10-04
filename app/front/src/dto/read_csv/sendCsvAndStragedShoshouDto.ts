import SaveStorageResultDto from "../storage/saveStorageResultDto";
import CsvCellInterface from "./csvCell";

/**
 * csv変換処理を行った結果を伝送するDto
 */
export default class SendCsvAndStragedShoshouDto {

    /** csvデータ */
    listAllCsv: [CsvCellInterface[]];

    /** csv保存処理結果 */
    saveStorageResultDto: SaveStorageResultDto;

    constructor() {
        this.listAllCsv = [[]];
        this.saveStorageResultDto = new SaveStorageResultDto();
    }
}