import type SelectOptionStringInterface from "../selectOptionStringDto";

export default interface PostalCodeBlockResultInterface {

}

export default class PostalCodeBlockResultDto implements PostalCodeBlockResultInterface {

    /** 行政区検索 */
    isGyouseikuData: boolean;

    /** 地方自治体コード */
    lgCode: string;

    /** 住所番地まで選択肢 */
    listOptions: SelectOptionStringInterface[];

    constructor() {

        this.isGyouseikuData = false;
        this.lgCode = "";
        this.listOptions = [];

    }
}