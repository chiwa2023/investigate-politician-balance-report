import BalancesheetIncomeInterface from "../../../../dto/balancesheet/balancesheetIncomeDto";
import BalancesheetIncomeDto from "../../../../dto/balancesheet/balancesheetIncomeDto";
import ReportKbnConstants from "../../../../dto/balancesheet/reportKbnConstants";
import YoushikiKbnIncomeConstants from "../../../../dto/balancesheet/youshikiKbnIncomeConstants";

/**
 * 収支報告書収入項目Mockデータを取得
 * @param code 識別コード
 * @returns 収支報告書収入項目リスト
 */
export default function mockGetBalancesheetIncome(code: number): BalancesheetIncomeInterface[] {

    const list: BalancesheetIncomeInterface[] = [];
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
function createDto(index: number): BalancesheetIncomeInterface {
    const dto: BalancesheetIncomeInterface = new BalancesheetIncomeDto;

    dto.balancesheetIncomeId = index * 5;
    dto.balancesheetIncomeCode = index;
    dto.itemName = "項目" + index;

    //とりあえず仮の値を入れてみる
    if(index % 2 === 1){
        dto.reportKbn = ReportKbnConstants.PUBLISH_REPORT;
        dto.youshikiKbn = parseInt(YoushikiKbnIncomeConstants.DONATE);
    }else{
        dto.reportKbn = ReportKbnConstants.PLAN_TASK;
        dto.youshikiKbn = parseInt(YoushikiKbnIncomeConstants.PARTY);
    }


    return dto;
}