import OfferingPartyUsage0801And0807ReportInterface from "../../entity/offeringPartyUsage0801And0807ReportEntity";
import OfferingPartyUsage0801And0807ReportDto from "../../entity/offeringPartyUsage0801And0807ReportEntity";
import OfferingPartyUsage0802And0803ReportInterface from "../../entity/offeringPartyUsage0802And0803ReportEntity";
import OfferingPartyUsage0802And0803ReportDto from "../../entity/offeringPartyUsage0802And0803ReportEntity";

export default interface keinenUsageSurface02103SummaryByYearInterface{

}

export default class keinenUsageSurface02103SummaryByYearDto implements keinenUsageSurface02103SummaryByYearInterface{

    /** 政党交付金使途報告書表紙Entity  */
    surfaceEntity:OfferingPartyUsage0801And0807ReportInterface;

    /** 政党交付金使途報告書集計Entity  */
    summaryEntity:OfferingPartyUsage0802And0803ReportInterface;

    /** 検索開始報告年 */
    houkokuNen: number;

    constructor(){

        this.surfaceEntity =new OfferingPartyUsage0801And0807ReportDto();
        this.summaryEntity = new OfferingPartyUsage0802And0803ReportDto();
        this.houkokuNen = 0;

    }

}