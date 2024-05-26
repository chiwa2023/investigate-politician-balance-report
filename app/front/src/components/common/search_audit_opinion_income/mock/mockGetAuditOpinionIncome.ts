import AuditOpinionIncomeInterface from "../../../../dto/audit_opinion/auditOpinionIncomeDto";
import AuditOpinionIncomeDto from "../../../../dto/audit_opinion/auditOpinionIncomeDto";
import ReportKbnConstants from "../../../../dto/balancesheet/reportKbnConstants";
import YoushikiEdaKbnIncomeConstants from "../../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import YoushikiKbnIncomeConstants from "../../../../dto/balancesheet/youshikiKbnIncomeConstants";

/**
 * 収支報告書収入項目Mockデータを取得
 * @param code 識別コード
 * @returns 収支報告書収入項目リスト
 */
export default function mockGetAuditOpinionIncome(code: number): AuditOpinionIncomeInterface[] {

    const list: AuditOpinionIncomeInterface[] = [];
    list.push(createDto(code * 100 + 1));
    list.push(createDto(code * 100 + 2));
    list.push(createDto(code * 100 + 3));
    list.push(createDto(code * 100 + 4));

    return list;
}

/**
 * Mock項目を生成
 * @param index 区別用Index
 * @returns 収支報告書収入項目Dto
 */
function createDto(index: number): AuditOpinionIncomeInterface {
    const dto: AuditOpinionIncomeInterface = new AuditOpinionIncomeDto;

    dto.balancesheetIncomeId = index * 5;
    dto.balancesheetIncomeCode = index;
    dto.itemName = "項目" + index;

 
    //とりあえず仮の値を入れてみる
    if(index % 2 === 1){
        dto.reportKbn = ReportKbnConstants.PUBLISH_REPORT;
        dto.youshikiKbn = parseInt(YoushikiKbnIncomeConstants.DONATE);
        dto.youshikiEdaKbn = parseInt(YoushikiEdaKbnIncomeConstants.PERSONAL);
        dto.loginUserRole = "会計責任者";
    }else{
        dto.reportKbn = ReportKbnConstants.PLAN_TASK;
        dto.youshikiKbn = parseInt(YoushikiKbnIncomeConstants.PARTY);
        dto.youshikiEdaKbn = parseInt(YoushikiEdaKbnIncomeConstants.CORPORATION);
        dto.loginUserRole = "事務担当者1";
    }


    return dto;
}