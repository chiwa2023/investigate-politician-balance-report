export default interface TaskPlanBalancesheetDetailInterface {

}

export default class TaskPlanBalancesheetDetailEntity implements TaskPlanBalancesheetDetailInterface{

    /** 政治資金収支報告書準備登録タスクId */
    taskPlanBalancesheetDetailId: number;

    /** 政治資金収支報告書準備登録タスク定同一識別コード */
    taskPlanBalancesheetDetailCode: number;

    /** 最新区分 */
    saishinKbn: number;

    /** 作業終了フラグ */
    isFinished: boolean;

    /** 読み取り文字コード */
    charset: string;

    /** 子ディレクトリ */
    childDir: string;

    /** ファイル名 */
    fileName: string;

    /** ファイルフルパス */
    fullPath: string;

    /** 書証保存Id */
    shoshouId: number;

    /** 書証保存同一識別コード */
    shoshouCode: number;

    /** 書証区分 */
    shoshouKbn: number;

    /** 文書識別Key */
    documentKey: string;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザ同一識別コード */
    insertUserCode: number;

    /** 挿入ユーザ姓名 */
    insertUserName: string;

    /** 挿入タイムスタンプ */
    insertTimestamp: Date;

    /** 更新ユーザId */
    updateUserId: number;

    /** 更新ユーザ同一識別コード */
    updateUserCode: number;

    /** 更新ユーザ姓名 */
    updateUserName: string;

    /** 更新タイムスタンプ */
    updateTimestamp: Date;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING = "0";
        const INIT_Boolean: boolean = false;
        const INIT_LocalDate: Date = new Date(1948, 7, 29);

        this.taskPlanBalancesheetDetailId = INIT_NUMBER;
        this.taskPlanBalancesheetDetailCode = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.isFinished = INIT_Boolean;
        this.charset = INIT_STRING;
        this.childDir = INIT_STRING;
        this.fileName = INIT_STRING;
        this.fullPath = INIT_STRING;
        this.documentKey = INIT_STRING;
        this.shoshouId = INIT_NUMBER;
        this.shoshouCode = INIT_NUMBER;
        this.shoshouKbn = INIT_NUMBER;
        this.insertUserId = INIT_NUMBER;
        this.insertUserCode = INIT_NUMBER;
        this.insertUserName = INIT_STRING;
        this.insertTimestamp = INIT_LocalDate;
        this.updateUserId = INIT_NUMBER;
        this.updateUserCode = INIT_NUMBER;
        this.updateUserName = INIT_STRING;
        this.updateTimestamp = INIT_LocalDate;
    }
}