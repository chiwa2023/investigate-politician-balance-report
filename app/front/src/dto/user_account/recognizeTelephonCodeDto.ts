export default class RecognizeTelephonCodeDto {

    /** 識別用コード */
    recoginzedCode: string;

    /** 送信コード1 */
    codeChar1: string;
    /** 送信コード2 */
    codeChar2: string;
    /** 送信コード3 */
    codeChar3: string;
    /** 送信コード4 */
    codeChar4: string;
    /** 送信コード5 */
    codeChar5: string;
    /** 送信コード6 */
    codeChar6: string;
    /** 送信コード7 */
    codeChar7: string;
    /** 送信コード8 */
    codeChar8: string;

    constructor() {

        const INIT_STRING: string = "";
        this.recoginzedCode = INIT_STRING;
        this.codeChar1 = INIT_STRING;
        this.codeChar2 = INIT_STRING;
        this.codeChar3 = INIT_STRING;
        this.codeChar4 = INIT_STRING;
        this.codeChar5 = INIT_STRING;
        this.codeChar6 = INIT_STRING;
        this.codeChar7 = INIT_STRING;
        this.codeChar8 = INIT_STRING;

    }
}