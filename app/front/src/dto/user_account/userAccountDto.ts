import RecognizeTelephonCodeDto from "./recognizeTelephonCodeDto";

export default class UserAccountDto {

    /**メールアドレス */
    loginUserMailAddress: string;

    /** パスワード */
    loginUserPassword: string;

    /** 電話番号1 */
    loginUserTel1: string;

    /** 電話番号2 */
    loginUserTel2: string;

    /** 電話番号3 */
    loginUserTel3: string;

    /** 電話認識コード */
    recognizeTelephonCodeDto: RecognizeTelephonCodeDto;

    constructor() {

        const INIT_STRING: string = "";
        this.loginUserMailAddress = INIT_STRING;
        this.loginUserPassword = INIT_STRING;
        this.loginUserTel1 = INIT_STRING;
        this.loginUserTel2 = INIT_STRING;
        this.loginUserTel3 = INIT_STRING;
        this.recognizeTelephonCodeDto = new RecognizeTelephonCodeDto();
    }
}