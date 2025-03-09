import OfferingBalancesheet01And20SurfaceInterface from "../../entity/offeringBalancesheet01And20SurfaceEntity";
import OfferingBalancesheet01And20SurfaceEntity from "../../entity/offeringBalancesheet01And20SurfaceEntity";
import OfferingBalancesheet0702And0713SummaryInterface from "../../entity/offeringBalancesheet0702And0713SummaryEntity";
import OfferingBalancesheet0702And0713SummaryEntity from "../../entity/offeringBalancesheet0702And0713SummaryEntity";

export default interface KeinenHenkaSurfaceAndSummaryByYearInterface {


}

export default class KeinenHenkaSurfaceAndSummaryByYearDto implements KeinenHenkaSurfaceAndSummaryByYearInterface {

    /** 収支報告書表紙Entity */
    offeringBalancesheet01And20SurfaceEntity: OfferingBalancesheet01And20SurfaceInterface;

    /** 収支報告書集計Entity */
    offeringBalancesheet0702And0713SummaryEntity: OfferingBalancesheet0702And0713SummaryInterface;

    /** 検索開始報告年 */
    houkokuNen: number;


    constructor() {

        this.offeringBalancesheet01And20SurfaceEntity = new OfferingBalancesheet01And20SurfaceEntity();
        this.offeringBalancesheet0702And0713SummaryEntity = new OfferingBalancesheet0702And0713SummaryEntity();
        this.houkokuNen = 0;
    }

}