/**
 * 排他制御方法を格納する
 * TODO 仕様が決定次第修正する
 */
export default class CheckTransactionDto {

    /** 他機能確認用Mock強制例外発生フラグ */
    isRaiseExcception: boolean;

    /** 他機能確認用Mock判定結果 */
    isResult: boolean;

    /** 照会専用フラグ(排他制御チェック不要) */
    isSelectOnly: boolean;

    constructor() {
        //newしただけでは落ちる値を設定
        this.isRaiseExcception = true;
        this.isResult = false;
        this.isSelectOnly = false;
    }

}