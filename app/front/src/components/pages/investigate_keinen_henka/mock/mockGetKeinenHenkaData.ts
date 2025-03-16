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
    entitySurface.offeringDate = new Date(year, 3, 9);
    entitySurface.daihyoushaNameFirst = "代表";
    entitySurface.daihyoushaNameLast = "花子";
    entitySurface.kaikeiSekininshaNameFirst = "会計責任者";
    entitySurface.kaikeiSekininshaNameLast = "太郎";

    const entitySummary: OfferingBalancesheet0702And0713SummaryInterface = new OfferingBalancesheet0702And0713SummaryEntity();

    entitySummary.houkokuNen = year;

    const plus = year % 3;

    // 収入テーブル
    entitySummary.shunyuGokei = year * 100 + plus * 3 + 110;
    entitySummary.zennenKurikoshi = year * 100 + plus * 3 + 120;
    entitySummary.honnenShunyu = year * 100 + plus * 3 + 130;
    entitySummary.shishutsuGoukei = year * 100 + plus * 3 + 140;
    entitySummary.yokunenKurikoshi = year * 100 + plus * 3 + 150;
    entitySummary.kojiFutanGoukei = (year * 100 + plus * 3 + 160) + "";
    entitySummary.kojiFutanSuu = year + plus * 3 + 11 + "";
    entitySummary.kojinKifuGoukei = year * 100 + plus * 3 + 170;
    entitySummary.kojinKifuBikou = year + "";
    entitySummary.tokuteiKifuGoukei = year * 100 + plus * 3 + 180;
    entitySummary.tokuteiKifuBikou = year + "";
    entitySummary.houjinKifuGoukei = year * 100 + plus * 3 + 190;
    entitySummary.houjinKifuBiko = year + "";
    entitySummary.seijiDantaiKifuGoukei = year * 1000 + plus * 3 + 200;
    entitySummary.seijiDantaiKifuBikou = year + "";
    entitySummary.kifuShoukeiGoukei = year * 100 + plus * 3 + 210;
    entitySummary.kifuShoukeiBikou = year + "";
    entitySummary.assenGoukei = year * 100 + plus * 3 + 220;
    entitySummary.assenBikou = year + "";
    entitySummary.tokumeiKifuGoukei = year * 100 + plus * 3 + 230;
    entitySummary.tokumeiKifuBikou = year + "";
    entitySummary.kifuSoGoukei = year * 100 + plus * 3 + 240;
    entitySummary.kifuSoGoukeiBikou = year + "";

    // 支出テーブル
    entitySummary.goukeiJinkenhi = (year * 100 + plus * 3 + 110) + "";
    entitySummary.kohfuJinkenhi = (year * 42 + plus * 3 + 120) + "";
    entitySummary.bikouJinkenhi = year + "";
    entitySummary.goukeiKohnetsuhi = (year * 100 + plus * 3 + 130) + "";
    entitySummary.kohfuKohnetsuhi = (year * 42 + plus * 3 + 140) + "";
    entitySummary.bikouKohnetsuhi = year + "";
    entitySummary.goukeiBihinhi = (year * 100 + plus * 3 + 150) + "";
    entitySummary.kohfuBihinhi = (year * 42 + plus * 3 + 160) + "";
    entitySummary.bikouBihinhi = year + "";
    entitySummary.goukeiJimushohi = (year * 100 + plus * 3 + 170) + "";
    entitySummary.kohfuJimushohi = (year * 42 + plus * 3 + 180) + "";
    entitySummary.bikouJimushohi = year + "";
    entitySummary.goukeiKeihiShoukei = (year * 100 + plus * 3 + 190);
    entitySummary.kohfuKeihiShoukei = (year * 42 + plus * 3 + 200);
    entitySummary.bikouKeihiShoukei = year + "";
    entitySummary.goukeiSoshikiKatsudouhi = (year * 100 + plus * 3 + 210);
    entitySummary.kohfuSoshikiKatsudouhi = (year * 42 + plus * 3 + 220) + "";
    entitySummary.bikouSoshikiKatsudouhi = year + "";
    entitySummary.goukeiSenkyoKatsudou = (year * 100 + plus * 3 + 230);
    entitySummary.kohfuSenkyoKatsudou = (year * 42 + plus * 3 + 240) + "";
    entitySummary.bikouSenkyoKatsudou = year + "";
    entitySummary.goukeiSonota = (year * 100 + plus * 3 + 250);
    entitySummary.kohfuSonota = (year * 42 + plus * 3 + 260);
    entitySummary.bikouSonota = year + "";
    entitySummary.goukeiHakkou = (year * 100 + plus * 3 + 270);
    entitySummary.kohfuHakkou = (year * 42 + plus * 3 + 280) + "";
    entitySummary.bikouHakkou = year + "";
    entitySummary.goukeiSenden = (year * 100 + plus * 3 + 290);
    entitySummary.kohfuSenden = (year * 42 + plus * 3 + 300) + "";
    entitySummary.bikouSenden = year + "";
    entitySummary.goukeiKaisaiPty = (year * 100 + plus * 3 + 310);
    entitySummary.kohfuKaisaiPty = (year * 42 + plus * 3 + 320) + "";
    entitySummary.bikouKaisaiPty = year + "";
    entitySummary.goukeiSonotaJigyou = (year * 100 + plus * 3 + 330);
    entitySummary.kohfuSonotaJigyou = (year * 42 + plus * 3 + 340) + "";
    entitySummary.bikouSonotaJigyou = year + "";
    entitySummary.goukeiChousaKenkyu = (year * 100 + plus * 3 + 350);
    entitySummary.kohfuChousaKenkyu = (year * 42 + plus * 3 + 360) + "";
    entitySummary.bikouChousaKenkyu = year + "";
    entitySummary.goukeiKifukin = (year * 100 + plus * 3 + 370);
    entitySummary.kohfuKifukin = (year * 42 + plus * 3 + 380) + "";
    entitySummary.bikouKifukin = year + "";
    entitySummary.goukeiSonotaKeihi = (year * 100 + plus * 3 + 390);
    entitySummary.kohfuSonotaKeihi = (year * 42 + plus * 3 + 400) + "";
    entitySummary.bikouSonotaKeihi = year + "";
    entitySummary.goukeiKatsudouhi = (year * 100 + plus * 3 + 410);
    entitySummary.kohfuKatsudouhi = (year * 42 + plus * 3 + 420);
    entitySummary.bikouKatsudouhi = year + "";
    entitySummary.goukeiZenGohkei = (year * 1000 + plus * 3);

    dto.offeringBalancesheet01And20SurfaceEntity = entitySurface;
    dto.offeringBalancesheet0702And0713SummaryEntity = entitySummary;

    return dto;
}