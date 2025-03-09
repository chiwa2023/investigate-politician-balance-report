import KeinenHenkaSurfaceAndSummaryByYearInterface from "../../../../dto/keinen_henka/keinenHenkaSurfaceAndSummaryByYearDto";
import KeinenHenkaSurfaceAndSummaryByYearDto from "../../../../dto/keinen_henka/keinenHenkaSurfaceAndSummaryByYearDto";
import OfferingBalancesheet01And20SurfaceInterface from "../../../../entity/offeringBalancesheet01And20SurfaceEntity";
import OfferingBalancesheet01And20SurfaceEntity from "../../../../entity/offeringBalancesheet01And20SurfaceEntity";
import OfferingBalancesheet0702And0713SummaryInterface from "../../../../entity/offeringBalancesheet0702And0713SummaryEntity";
import OfferingBalancesheet0702And0713SummaryEntity from "../../../../entity/offeringBalancesheet0702And0713SummaryEntity";

export default function mockGetKeinenHenkaData(startYear: number, endYear: number): KeinenHenkaSurfaceAndSummaryByYearInterface[] {

    const list: KeinenHenkaSurfaceAndSummaryByYearInterface[] = []

    for (let year = startYear; year <= endYear; year++) {
        list.push(creatDto(year));
    }

    return list;
}

function creatDto(year: number): KeinenHenkaSurfaceAndSummaryByYearInterface {

    const dto: KeinenHenkaSurfaceAndSummaryByYearInterface = new KeinenHenkaSurfaceAndSummaryByYearDto();
    dto.houkokuNen = year;

    const entitySurface: OfferingBalancesheet01And20SurfaceInterface = new OfferingBalancesheet01And20SurfaceEntity();
    entitySurface.houkokuNen = year;
    entitySurface.dantaiNameKana = "ちゃらんぽらんせいじだんたい" + year;
    entitySurface.dantaiName = "ちゃらんぽらん政治団体" + year;
    entitySurface.offeringDate = new Date(year,3,9);
    entitySurface.daihyoushaNameFirst = "代表";
    entitySurface.daihyoushaNameLast = "花子";
    entitySurface.kaikeiSekininshaNameFirst = "会計責任者";
    entitySurface.kaikeiSekininshaNameLast = "太郎";

    const entitySummary: OfferingBalancesheet0702And0713SummaryInterface = new OfferingBalancesheet0702And0713SummaryEntity();
    entitySummary.houkokuNen = year;

    dto.offeringBalancesheet01And20SurfaceEntity = entitySurface;
    dto.offeringBalancesheet0702And0713SummaryEntity = entitySummary;

    return dto;
}