export default interface TemplateWithTaskPlanInfoResultInterface {

}

export default class TemplateWithTaskPlanInfoResultDto implements TemplateWithTaskPlanInfoResultInterface {

    /** 表示メッセージ */
    message: string;

    /** 取得コード */
    taskPlanCode: number;

    /** 登録年 */
    year: number;

    constructor() {

        // 初期データ
        const INIT_STRING = "";
        const INIT_NUMBER = 0;

        this.message = INIT_STRING;
        this.taskPlanCode = INIT_NUMBER;
        this.year = INIT_NUMBER;

    }

}