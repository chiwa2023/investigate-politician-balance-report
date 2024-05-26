/**
 * 権限情報を格納する
 * TODO 仕様が決定次第修正する
 */
export default class CheckPrivilegeDto {

    /** 他機能確認用Mock強制例外発生フラグ */
    isRaiseExcception: boolean;

    /** 他機能確認用Mock判定結果 */
    isResult: boolean;

    /** ログインユーザId */
    loginUserId: number;

    /** ログインユーザ同一識別コード */
    loginUserCode: number;

    /** ログインユーザ氏名 */
    loginUserName: string;

    constructor() {
        //newしただけでは落ちる値を設定
        this.isRaiseExcception = true;
        this.isResult = false;

        this.loginUserId = 0;
        this.loginUserCode = 0;
        this.loginUserName = "";
    }

}