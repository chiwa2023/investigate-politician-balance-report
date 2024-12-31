import PoliOrgFeeInsuDto from "./poliOrgFeeInsuDto";

export default interface SearchPoliOrgBalancesheetMembershipFeeSummaryResultInterface{


}

export default class SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto implements SearchPoliOrgBalancesheetMembershipFeeSummaryResultInterface{

        /** 検索結果全件 */
        countAll:number;

        /** 検索結果を取得したページ数 */
        posPage:number;
    
        /** 個別の要約結果 */
        listSummary:PoliOrgFeeInsuDto[];
    
        constructor(){

        this.countAll=0;
        this.posPage=0;
        this.listSummary = [];

        }

}