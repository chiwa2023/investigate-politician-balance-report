import AbstactCapsuleDto from "../abstactCapsuleDto";

export default interface ReadXmlCapsuleInterface {

}

export default class ReadXmlCapsuleDto extends AbstactCapsuleDto implements ReadXmlCapsuleInterface {

    /** ファイル内容(テキスト文字列) */
    fileContent: string;

    /** ファイル名 */
    fileName: string;

    /** 文字コード */
    charset: string;

    /** 文書識別Key */
    documentKey: string;

    constructor() {
        super();
        const INIT_STRING = "";
        this.fileContent = INIT_STRING;
        this.fileName = INIT_STRING;
        this.charset = INIT_STRING;
        this.documentKey = INIT_STRING;

    }
}