export default interface OneFileBlobInterface {
    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;

}
export default class OneFileBlobDto implements OneFileBlobInterface {
    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;

    constructor() {

        this.fileName = "";
        this.fileContentBase64 = "";

    }
}