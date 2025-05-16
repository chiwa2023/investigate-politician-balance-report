import type SelectOptionNumberInterface from "../selectOptionNumberDto";

export default interface PostalCodePostalResultInterface {

}

export default class PostalCodePostalResultDto implements PostalCodePostalResultInterface {

    /** 行政区検索 */
    isGyouseikuData: boolean;

    /** 郵便番号住所までリスト */
    listOptions: SelectOptionNumberInterface[];

    constructor() {

        this.isGyouseikuData = false;
        this.listOptions = [];

    }
}