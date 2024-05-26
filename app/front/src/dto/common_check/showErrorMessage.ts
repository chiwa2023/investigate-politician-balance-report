/** 
 * サーバステータスに合わせたメッセージを表示する
 * @param status サーバステータス
 */
export default function showErrorMessage(status: number) {

    //TODO 例外表示方法の使用が決定したら修正する
    //特に現在はわかりやすいようにstatus表示もしている部分
    
    //権限不足 HttpStatus.FORBIDDEN=403
    const FORBIDDEN = 403;
    
    //セキュリティ事故 HttpStatus.BAD_GATEWAY=502
    const BAD_GATEWAY = 502;

    //排他の対象HttpStatus.CONFLICT=409
    const CONFLICT = 409;

    //実装上の問題無含めた内部エラー
    const INTERNAL_ERROR = 500;

    switch (status) {
    //権限不足
    case FORBIDDEN:
        alert("利用権限がありません。あなたの団体の事務担当責任者またはシステム運営者にお問い合わせください"+status);
        break;
    //排他制御
    case CONFLICT:
        alert("今利用しているデータを他のユーザさんが編集の可能性を予告しています。15分程度後に再度アクセスしてみてください"+status);
        break;
    //セキュリティ事故
    case BAD_GATEWAY:
        alert("システム運営者にお問い合わせください。ネットワーク上の問題が発生していると思われます"+status);
        break;
    //内部エラー
    case INTERNAL_ERROR:
        alert("予測できない例外が発生しています。システム運営者にお問い合わせください"+status);
        break;
    //その他
    default:
        alert("予測できない例外が発生しています。システム運営者にお問い合わせください"+status);
        break;
    }
}