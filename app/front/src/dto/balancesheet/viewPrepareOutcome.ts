import closeInputOutcomeForm from "./closeInputOutcomeForm";
import changeOutcomeYoushikiKbnState from "./changeOutcomeYoushikiKbnState";
import ReportKbnConstants from "./reportKbnConstants";
import AuditOpinionOutcomeInterface from "../audit_opinion/auditOpinionOutcomeDto";

/**
 * 収支報告書の表示nにかかわる値設定
 * @param outcomeDto 収支報告書(支出)データ
 * @returns 収支報告書(支出)データ
 */
export default function viewPrepareOutcome(outcomeDto: AuditOpinionOutcomeInterface): AuditOpinionOutcomeInterface {

    switch (outcomeDto.reportKbn) {
    //報告対象
    case ReportKbnConstants.PUBLISH_REPORT:
        //収支報告するのですべての入力フォームを開きます
        outcomeDto = changeOutcomeYoushikiKbnState(outcomeDto);
        break;
    //生活費
    case ReportKbnConstants.LIVING_COST:
        //収支報告しないのですべての入力フォームを閉じます
        closeInputOutcomeForm(outcomeDto);
        break;
        //政治活動
    case ReportKbnConstants.ABPLITION:
        //収支報告しないのですべての入力フォームを閉じます
        closeInputOutcomeForm(outcomeDto);
        break;
        //政治活動
    case ReportKbnConstants.PLAN_TASK:
        //後で入力したい、ということなので入力自体は可能です
        outcomeDto = changeOutcomeYoushikiKbnState(outcomeDto);
        break;
    }
    return outcomeDto;
}
