export default interface FrameworkResultInterface {
    /** 処理メッセージ */
    message: string;

}


export default class FrameworkResultDto implements FrameworkResultInterface {
    /** 処理メッセージ */
    message: string;

    constructor() {
        const INIT_STRING: string = "";
        this.message = INIT_STRING;
    }
}
