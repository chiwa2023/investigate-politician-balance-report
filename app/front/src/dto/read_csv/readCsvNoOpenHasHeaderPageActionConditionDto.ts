export default class ReadCsvNoOpenHasHeaderPageActionConditionDto {

    // ヘッダ有無
    hasHeader: boolean;
    // 文字コード固定
    charsetValue: string;
    // 文字コード固定
    isFixShowCharset: boolean;

    constructor() {

        this.hasHeader = false;
        this.charsetValue = "";
        this.isFixShowCharset = false;

    }

}