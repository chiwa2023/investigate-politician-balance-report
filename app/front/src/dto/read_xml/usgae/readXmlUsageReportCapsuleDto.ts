import AbstactCapsuleDto from "../../abstactCapsuleDto";

export default interface ReadXmlUsageReportCapsuleInterface {

}

export default class ReadXmlUsageReportCapsuleDto extends AbstactCapsuleDto implements ReadXmlUsageReportCapsuleInterface {

    /** ファイル内容(テキスト文字列) */
    fileContent: string;

    /** ファイル名 */
    fileName: string;

    /** 文字コード */
    charset: string;

    constructor() {
        super();
        const INIT_STRING = "";
        this.fileContent = INIT_STRING;
        this.fileName = INIT_STRING;
        this.charset = INIT_STRING;

    }
}