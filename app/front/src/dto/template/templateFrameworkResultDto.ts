export default interface TemplateFrameworkResultInterface {

    /** 全処理終了フラグ */
    isOk: boolean;

    /** 実施成功件数 */
    successCount: number;

    /** 実施失敗件数 */
    failureCount: number;

    /** 表示メッセージ */
    message: string;

}

export default class TemplateFrameworkResultDto implements TemplateFrameworkResultInterface {

    /** 全処理終了フラグ */
    isOk: boolean;

    /** 実施成功件数 */
    successCount: number;

    /** 実施失敗件数 */
    //基本transaction処理なので使用範囲はかなり限定される
    failureCount: number;

    /** 表示メッセージ */
    message: string;

    constructor() {

        this.isOk = false;
        this.successCount = 0;
        this.failureCount = 0;
        this.message = "";

    }
}
