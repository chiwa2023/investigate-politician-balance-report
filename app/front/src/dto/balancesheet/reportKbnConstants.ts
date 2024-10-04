/**
 * 収支報告区分
 */
export default class ReportKbnConstants {

    /** 報告書に掲載 */
    static PUBLISH_REPORT: number = 1;

    /** 生活費 */
    static LIVING_COST: number = 20;

    /** 政治活動(廃止予定) */
    static ABPLITION: number = 1;

    /** タスクに計上 */
    static PLAN_TASK: number = 50;

    /** 報告書に掲載 */
    static PUBLISH_REPORT_TEXT: string = "収支報告";

    /** 生活費 */
    static LIVING_COST_TEXT: string = "生活費";

    /** 政治活動(廃止予定) */
    static ABPLITION_TEXT: string = "政策活動費";

    /** タスクに計上 */
    static PLAN_TASK_TEXT: string = "タスク計上";


    static convertText(reportKbn: number) {

        switch (reportKbn) {

        case ReportKbnConstants.PUBLISH_REPORT:
            return ReportKbnConstants.PUBLISH_REPORT_TEXT;

        case ReportKbnConstants.LIVING_COST:
            return ReportKbnConstants.LIVING_COST_TEXT;

        case ReportKbnConstants.ABPLITION:
            return ReportKbnConstants.ABPLITION_TEXT;

        case ReportKbnConstants.PLAN_TASK:
            return ReportKbnConstants.PLAN_TASK_TEXT;

        default:
            return "";
        }

        return "";
    }
}