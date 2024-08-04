export default class OneFileBlobDto {
    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;

    constructor() {

        this.fileName = "";
        this.fileContentBase64 = "";

    }
}