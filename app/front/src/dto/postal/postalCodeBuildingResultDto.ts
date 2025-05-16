import type SelectOptionStringInterface from "../selectOptionStringDto";

export default interface PostalCodeBuildingResultInterface {

}

export default class PostalCodeBuildingResultDto implements PostalCodeBuildingResultInterface {

    /** 地方自治体コード */
    lgCode: string;

    /** 建物選択肢リスト */
    listOptions: SelectOptionStringInterface[];

    constructor() {
        this.lgCode = "";
        this.listOptions = [];
    }

}