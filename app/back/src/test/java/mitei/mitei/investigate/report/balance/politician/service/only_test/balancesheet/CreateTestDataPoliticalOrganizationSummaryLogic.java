package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0702SummaryTableIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0713ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0717SummaryTableOfAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070200SummaryTableIncomeExpenditureDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071300ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071700SummaryTableOfAssetsDto;

/**
 * 政治資金収支報告書集計表(その2,13,17)テストデータ作成
 */
public class CreateTestDataPoliticalOrganizationSummaryLogic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     *
     * @param allBookDto 政治資金収支報告書
     */
    public void practice(final AllBookDto allBookDto) { // NOPMD

        AllSheet0702SummaryTableIncomeDto allSheet0702SummaryTableIncomeDto = new AllSheet0702SummaryTableIncomeDto();

        // 一種類のみ
        Sheet070200SummaryTableIncomeExpenditureDto sheet02 = new Sheet070200SummaryTableIncomeExpenditureDto();
        sheet02.setShunyuGokei(340L);
        sheet02.setZennenKurikoshi(341L);
        sheet02.setHonnenShunyu(342L);
        sheet02.setShishutsuGoukei(343L);
        sheet02.setYokunenKurikoshi(345L);
        sheet02.setKojiFutanGoukei("30000");
        sheet02.setKojiFutanSuu("30000");
        sheet02.setKojinKifuGoukei(346L);
        sheet02.setKojinKifuBikou("備考");
        sheet02.setTokuteiKifuGoukei(347L);
        sheet02.setTokuteiKifuBikou("備考");
        sheet02.setHoujinKifuGoukei(348L);
        sheet02.setHoujinKifuBiko("備考");
        sheet02.setSeijiDantaiKifuGoukei(349L);
        sheet02.setSeijiDantaiKifuBikou("備考");
        sheet02.setKifuShoukeiGoukei(350L);
        sheet02.setKifuShoukeiBikou("備考");
        sheet02.setAssenGoukei(351L);
        sheet02.setAssenBikou("備考");
        sheet02.setTokumeiKifuGoukei(352L);
        sheet02.setTokumeiKifuBikou("備考");
        sheet02.setKifuSoGoukei(353L);
        sheet02.setKifuSoGoukeiBikou("備考");

        allSheet0702SummaryTableIncomeDto.setSheet070200SummaryTableIncomeExpenditureDto(sheet02);

        AllSheet0713ListOfExpenditureItemsDto allSheet0713ListOfExpenditureItemsDto = new AllSheet0713ListOfExpenditureItemsDto();

        // 一種類のみ
        Sheet071300ListOfExpenditureItemsDto sheet13 = new Sheet071300ListOfExpenditureItemsDto();
        sheet13.setGoukeiJinkenhi("3000"); // NOPMD
        sheet13.setKohfuJinkenhi("400"); // NOPMD
        sheet13.setBikouJinkenhi("備考");
        sheet13.setGoukeiKohnetsuhi("3000");
        sheet13.setKohfuKohnetsuhi("400");
        sheet13.setBikouKohnetsuhi("備考");
        sheet13.setGoukeiBihinhi("3000");
        sheet13.setKohfuBihinhi("400");
        sheet13.setBikouBihinhi("備考");
        sheet13.setGoukeiJimushohi("3000");
        sheet13.setKohfuJimushohi("400");
        sheet13.setBikouJimushohi("備考");
        sheet13.setGoukeiKeihiShoukei(3000L);
        sheet13.setKohfuKeihiShoukei(400L);
        sheet13.setBikouKeihiShoukei("備考");
        sheet13.setGoukeiSoshikiKatsudouhi(3000L);
        sheet13.setKohfuSoshikiKatsudouhi("400");
        sheet13.setBikouSoshikiKatsudouhi("備考");
        sheet13.setGoukeiSenkyoKatsudou(3000L);
        sheet13.setKohfuSenkyoKatsudou("400");
        sheet13.setBikouSenkyoKatsudou("備考");
        sheet13.setGoukeiSonota(3000L);
        sheet13.setKohfuSonota(400L);
        sheet13.setBikouSonota("備考");
        sheet13.setGoukeiHakkou(3000L);
        sheet13.setKohfuHakkou("400");
        sheet13.setBikouHakkou("備考");
        sheet13.setGoukeiSenden(3000L);
        sheet13.setKohfuSenden("400");
        sheet13.setBikouSenden("備考");
        sheet13.setGoukeiKaisaiPty(3000L);
        sheet13.setKohfuKaisaiPty("400");
        sheet13.setBikouKaisaiPty("備考");
        sheet13.setGoukeiSonotaJigyou(3000L);
        sheet13.setKohfuSonotaJigyou("400");
        sheet13.setBikouSonotaJigyou("備考");
        sheet13.setGoukeiChousaKenkyu(3000L);
        sheet13.setKohfuChousaKenkyu("400");
        sheet13.setBikouChousaKenkyu("備考");
        sheet13.setGoukeiKifukin(3000L);
        sheet13.setKohfuKifukin("400");
        sheet13.setBikouKifukin("備考");
        sheet13.setGoukeiSonotaKeihi(3000L);
        sheet13.setKohfuSonotaKeihi("400");
        sheet13.setBikouSonotaKeihi("備考");
        sheet13.setGoukeiKatsudouhi(3000L);
        sheet13.setKohfuKatsudouhi(400L);
        sheet13.setBikouKatsudouhi("備考");
        sheet13.setGoukeiZenGohkei(974L);

        allSheet0713ListOfExpenditureItemsDto.setSheet071300ListOfExpenditureItemsDto(sheet13);

        AllSheet0717SummaryTableOfAssetsDto allSheet0717SummaryTableOfAssetsDto = new AllSheet0717SummaryTableOfAssetsDto();

        // 一種類のみ
        Sheet071700SummaryTableOfAssetsDto sheet17 = new Sheet071700SummaryTableOfAssetsDto();
        sheet17.setFlgTochi(1);
        sheet17.setBikouTochi("備考");
        sheet17.setFlgTatemono(1);
        sheet17.setBikouTatemono("備考");
        sheet17.setFlgShakuchiken(1);
        sheet17.setBikouShakuchiken("備考");
        sheet17.setFlgDohsan(1);
        sheet17.setBikouDohsan("備考");
        sheet17.setFlgYokin(1);
        sheet17.setBikouYokin("備考");
        sheet17.setFlgShintaku(1);
        sheet17.setBikouShintaku("備考");
        sheet17.setFlgShouken(1);
        sheet17.setBikouShouken("備考");
        sheet17.setFlgShusshi(1);
        sheet17.setBikouShusshi("備考");
        sheet17.setFlgKashitsuke(1);
        sheet17.setBikouKashitsuke("備考");
        sheet17.setFlgShikikin(1);
        sheet17.setBikouShikikin("備考");
        sheet17.setFlgShisetsuRiyou(1);
        sheet17.setBikouShisetsuRiyou("備考");
        sheet17.setFlgKairiire(1);
        sheet17.setBikouKariire("備考");

        allSheet0717SummaryTableOfAssetsDto.setSheet071700SummaryTableOfAssetsDto(sheet17);

        allBookDto.setAllSheet0702SummaryTableIncomeDto(allSheet0702SummaryTableIncomeDto);
        allBookDto.setAllSheet0713ListOfExpenditureItemsDto(allSheet0713ListOfExpenditureItemsDto);
        allBookDto.setAllSheet0717SummaryTableOfAssetsDto(allSheet0717SummaryTableOfAssetsDto);
    }
}
