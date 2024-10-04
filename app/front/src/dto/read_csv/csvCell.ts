/**
 *csvのセルを表すInterface
 */
export default interface CsvCellInterface {
    /**
     *cellのデータ。
     */
    data: string;
// eslint-disable-next-line semi
}

/**
 *csvのセルを表すDto
 */
export default class CsvCellDto implements CsvCellInterface{

    /**
     *cellのデータ。
     */
    data: string;

    /**
     * Creates an instance of CsvCellDto.
     */
    constructor(){
        this.data = "";
    }
}
