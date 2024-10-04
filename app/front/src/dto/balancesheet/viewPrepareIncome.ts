import closeInputIncomeForm from "./closeInputIncomeForm";
import changeIncomeYoushikiKbnState from "./changeIncomeYoushikiKbnState";
import ReportKbnConstants from "./reportKbnConstants";
import AuditOpinionIncomeInterface from "../audit_opinion/auditOpinionIncomeDto";

/**
 * 収支報告書収入データの報告対象が変更した時に値を変更するLogic
 * @param incomeDto 収支報告書収入データ
 * @returns 収支報告書収入データ
 */
export default function viewPrepareIncome(incomeDto:AuditOpinionIncomeInterface):AuditOpinionIncomeInterface{
    switch (incomeDto.reportKbn) {
    //報告対象
    case ReportKbnConstants.PUBLISH_REPORT:
        //収支報告するのですべての入力フォームを開きます
        incomeDto = changeIncomeYoushikiKbnState(incomeDto);
        break;
        //生活費
    case ReportKbnConstants.LIVING_COST:
        //収支報告しないのですべての入力フォームを閉じます
        closeInputIncomeForm(incomeDto);
        break;
        //政治活動
    case ReportKbnConstants.ABPLITION:
        //収支報告しないのですべての入力フォームを閉じます
        closeInputIncomeForm(incomeDto);
        break;
        //政治活動
    case ReportKbnConstants.PLAN_TASK:
        //後で入力したい、ということなので入力自体は可能です
        incomeDto = changeIncomeYoushikiKbnState(incomeDto);
        break;

    default:
        //初期データの時は該当する
        break;
    }

    return incomeDto;
}
