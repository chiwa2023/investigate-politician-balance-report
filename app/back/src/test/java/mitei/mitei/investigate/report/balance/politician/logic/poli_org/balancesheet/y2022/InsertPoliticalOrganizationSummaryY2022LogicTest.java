package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0702SummaryTableIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0713ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0717SummaryTableOfAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070200SummaryTableIncomeExpenditureDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071300ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071700SummaryTableOfAssetsDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrganizationSummaryLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationSummaryY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationSummaryY2022Logic insertPoliticalOrganizationSummaryY2022Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2022Repository offeringBalancesheet0702And0713And0717Summary2022Repository;

    @Test
    @Transactional
    void testPractice() { // NOPMD

        // 文書同一識別コード
        Long documentCode = 3434L;

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2024); // 実際には表紙からコピー
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R4/12/1")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

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

        // 登録作業
        int size = insertPoliticalOrganizationSummaryY2022Logic.practice(documentCode, documentPropertyDto,
                allSheet0702SummaryTableIncomeDto, allSheet0713ListOfExpenditureItemsDto,
                allSheet0717SummaryTableOfAssetsDto, CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingBalancesheet0702And0713And0717Summary2022Entity> list = offeringBalancesheet0702And0713And0717Summary2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(documentCode);

        assertThat(size).isEqualTo(1); // 1件だけ登録
        assertThat(list.size()).isEqualTo(size);

        OfferingBalancesheet0702And0713And0717Summary2022Entity entitySummary = list.get(0);

        /* その2 */
        assertThat(entitySummary.getShunyuGokei()).isEqualTo(sheet02.getShunyuGokei());
        assertThat(entitySummary.getZennenKurikoshi()).isEqualTo(sheet02.getZennenKurikoshi());
        assertThat(entitySummary.getHonnenShunyu()).isEqualTo(sheet02.getHonnenShunyu());
        assertThat(entitySummary.getShishutsuGoukei()).isEqualTo(sheet02.getShishutsuGoukei());
        assertThat(entitySummary.getYokunenKurikoshi()).isEqualTo(sheet02.getYokunenKurikoshi());
        assertThat(entitySummary.getKojiFutanGoukei()).isEqualTo(sheet02.getKojiFutanGoukei());
        assertThat(entitySummary.getKojiFutanSuu()).isEqualTo(sheet02.getKojiFutanSuu());
        assertThat(entitySummary.getKojinKifuGoukei()).isEqualTo(sheet02.getKojinKifuGoukei());
        assertThat(entitySummary.getKojinKifuBikou()).isEqualTo(sheet02.getKojinKifuBikou());
        assertThat(entitySummary.getTokuteiKifuGoukei()).isEqualTo(sheet02.getTokuteiKifuGoukei());
        assertThat(entitySummary.getTokuteiKifuBikou()).isEqualTo(sheet02.getTokuteiKifuBikou());
        assertThat(entitySummary.getHoujinKifuGoukei()).isEqualTo(sheet02.getHoujinKifuGoukei());
        assertThat(entitySummary.getHoujinKifuBiko()).isEqualTo(sheet02.getHoujinKifuBiko());
        assertThat(entitySummary.getSeijiDantaiKifuGoukei()).isEqualTo(sheet02.getSeijiDantaiKifuGoukei());
        assertThat(entitySummary.getSeijiDantaiKifuBikou()).isEqualTo(sheet02.getSeijiDantaiKifuBikou());
        assertThat(entitySummary.getKifuShoukeiGoukei()).isEqualTo(sheet02.getKifuShoukeiGoukei());
        assertThat(entitySummary.getKifuShoukeiBikou()).isEqualTo(sheet02.getKifuShoukeiBikou());
        assertThat(entitySummary.getAssenGoukei()).isEqualTo(sheet02.getAssenGoukei());
        assertThat(entitySummary.getAssenBikou()).isEqualTo(sheet02.getAssenBikou());
        assertThat(entitySummary.getTokumeiKifuGoukei()).isEqualTo(sheet02.getTokumeiKifuGoukei());
        assertThat(entitySummary.getTokumeiKifuBikou()).isEqualTo(sheet02.getTokumeiKifuBikou());
        assertThat(entitySummary.getKifuSoGoukei()).isEqualTo(sheet02.getKifuSoGoukei());
        assertThat(entitySummary.getKifuSoGoukeiBikou()).isEqualTo(sheet02.getKifuSoGoukeiBikou());

        /* その13 */
        assertThat(entitySummary.getGoukeiJinkenhi()).isEqualTo(sheet13.getGoukeiJinkenhi());
        assertThat(entitySummary.getKohfuJinkenhi()).isEqualTo(sheet13.getKohfuJinkenhi());
        assertThat(entitySummary.getBikouJinkenhi()).isEqualTo(sheet13.getBikouJinkenhi());
        assertThat(entitySummary.getGoukeiKohnetsuhi()).isEqualTo(sheet13.getGoukeiKohnetsuhi());
        assertThat(entitySummary.getKohfuKohnetsuhi()).isEqualTo(sheet13.getKohfuKohnetsuhi());
        assertThat(entitySummary.getBikouKohnetsuhi()).isEqualTo(sheet13.getBikouKohnetsuhi());
        assertThat(entitySummary.getGoukeiBihinhi()).isEqualTo(sheet13.getGoukeiBihinhi());
        assertThat(entitySummary.getKohfuBihinhi()).isEqualTo(sheet13.getKohfuBihinhi());
        assertThat(entitySummary.getBikouBihinhi()).isEqualTo(sheet13.getBikouBihinhi());
        assertThat(entitySummary.getGoukeiJimushohi()).isEqualTo(sheet13.getGoukeiJimushohi());
        assertThat(entitySummary.getKohfuJimushohi()).isEqualTo(sheet13.getKohfuJimushohi());
        assertThat(entitySummary.getBikouJimushohi()).isEqualTo(sheet13.getBikouJimushohi());
        assertThat(entitySummary.getGoukeiKeihiShoukei()).isEqualTo(sheet13.getGoukeiKeihiShoukei());
        assertThat(entitySummary.getKohfuKeihiShoukei()).isEqualTo(sheet13.getKohfuKeihiShoukei());
        assertThat(entitySummary.getBikouKeihiShoukei()).isEqualTo(sheet13.getBikouKeihiShoukei());
        assertThat(entitySummary.getGoukeiSoshikiKatsudouhi()).isEqualTo(sheet13.getGoukeiSoshikiKatsudouhi());
        assertThat(entitySummary.getKohfuSoshikiKatsudouhi()).isEqualTo(sheet13.getKohfuSoshikiKatsudouhi());
        assertThat(entitySummary.getBikouSoshikiKatsudouhi()).isEqualTo(sheet13.getBikouSoshikiKatsudouhi());
        assertThat(entitySummary.getGoukeiSenkyoKatsudou()).isEqualTo(sheet13.getGoukeiSenkyoKatsudou());
        assertThat(entitySummary.getKohfuSenkyoKatsudou()).isEqualTo(sheet13.getKohfuSenkyoKatsudou());
        assertThat(entitySummary.getBikouSenkyoKatsudou()).isEqualTo(sheet13.getBikouSenkyoKatsudou());
        assertThat(entitySummary.getGoukeiSonota()).isEqualTo(sheet13.getGoukeiSonota());
        assertThat(entitySummary.getKohfuSonota()).isEqualTo(sheet13.getKohfuSonota());
        assertThat(entitySummary.getBikouSonota()).isEqualTo(sheet13.getBikouSonota());
        assertThat(entitySummary.getGoukeiHakkou()).isEqualTo(sheet13.getGoukeiHakkou());
        assertThat(entitySummary.getKohfuHakkou()).isEqualTo(sheet13.getKohfuHakkou());
        assertThat(entitySummary.getBikouHakkou()).isEqualTo(sheet13.getBikouHakkou());
        assertThat(entitySummary.getGoukeiSenden()).isEqualTo(sheet13.getGoukeiSenden());
        assertThat(entitySummary.getKohfuSenden()).isEqualTo(sheet13.getKohfuSenden());
        assertThat(entitySummary.getKohfuSenden()).isEqualTo(sheet13.getKohfuSenden());
        assertThat(entitySummary.getGoukeiKaisaiPty()).isEqualTo(sheet13.getGoukeiKaisaiPty());
        assertThat(entitySummary.getKohfuKaisaiPty()).isEqualTo(sheet13.getKohfuKaisaiPty());
        assertThat(entitySummary.getBikouKaisaiPty()).isEqualTo(sheet13.getBikouKaisaiPty());
        assertThat(entitySummary.getGoukeiSonotaJigyou()).isEqualTo(sheet13.getGoukeiSonotaJigyou());
        assertThat(entitySummary.getKohfuSonotaJigyou()).isEqualTo(sheet13.getKohfuSonotaJigyou());
        assertThat(entitySummary.getBikouSonotaJigyou()).isEqualTo(sheet13.getBikouSonotaJigyou());
        assertThat(entitySummary.getGoukeiChousaKenkyu()).isEqualTo(sheet13.getGoukeiChousaKenkyu());
        assertThat(entitySummary.getKohfuChousaKenkyu()).isEqualTo(sheet13.getKohfuChousaKenkyu());
        assertThat(entitySummary.getBikouChousaKenkyu()).isEqualTo(sheet13.getBikouChousaKenkyu());
        assertThat(entitySummary.getGoukeiKifukin()).isEqualTo(sheet13.getGoukeiKifukin());
        assertThat(entitySummary.getKohfuKifukin()).isEqualTo(sheet13.getKohfuKifukin());
        assertThat(entitySummary.getBikouKifukin()).isEqualTo(sheet13.getBikouKifukin());
        assertThat(entitySummary.getGoukeiSonotaKeihi()).isEqualTo(sheet13.getGoukeiSonotaKeihi());
        assertThat(entitySummary.getKohfuSonotaKeihi()).isEqualTo(sheet13.getKohfuSonotaKeihi());
        assertThat(entitySummary.getBikouSonotaKeihi()).isEqualTo(sheet13.getBikouSonotaKeihi());
        assertThat(entitySummary.getGoukeiKatsudouhi()).isEqualTo(sheet13.getGoukeiKatsudouhi());
        assertThat(entitySummary.getKohfuKatsudouhi()).isEqualTo(sheet13.getKohfuKatsudouhi());
        assertThat(entitySummary.getBikouKatsudouhi()).isEqualTo(sheet13.getBikouKatsudouhi());
        assertThat(entitySummary.getGoukeiZenGohkei()).isEqualTo(sheet13.getGoukeiZenGohkei());

        /* その17 */
        assertThat(entitySummary.getFlgTochi()).isEqualTo(sheet17.getFlgTochi());
        assertThat(entitySummary.getBikouTochi()).isEqualTo(sheet17.getBikouTochi());
        assertThat(entitySummary.getFlgTatemono()).isEqualTo(sheet17.getFlgTatemono());
        assertThat(entitySummary.getBikouTatemono()).isEqualTo(sheet17.getBikouTatemono());
        assertThat(entitySummary.getFlgShakuchiken()).isEqualTo(sheet17.getFlgShakuchiken());
        assertThat(entitySummary.getBikouShakuchiken()).isEqualTo(sheet17.getBikouShakuchiken());
        assertThat(entitySummary.getFlgDohsan()).isEqualTo(sheet17.getFlgDohsan());
        assertThat(entitySummary.getBikouDohsan()).isEqualTo(sheet17.getBikouDohsan());
        assertThat(entitySummary.getFlgYokin()).isEqualTo(sheet17.getFlgYokin());
        assertThat(entitySummary.getBikouYokin()).isEqualTo(sheet17.getBikouYokin());
        assertThat(entitySummary.getFlgShintaku()).isEqualTo(sheet17.getFlgShintaku());
        assertThat(entitySummary.getBikouShintaku()).isEqualTo(sheet17.getBikouShintaku());
        assertThat(entitySummary.getFlgShouken()).isEqualTo(sheet17.getFlgShouken());
        assertThat(entitySummary.getBikouShouken()).isEqualTo(sheet17.getBikouShouken());
        assertThat(entitySummary.getFlgShusshi()).isEqualTo(sheet17.getFlgShusshi());
        assertThat(entitySummary.getBikouShusshi()).isEqualTo(sheet17.getBikouShusshi());
        assertThat(entitySummary.getFlgKashitsuke()).isEqualTo(sheet17.getFlgKashitsuke());
        assertThat(entitySummary.getBikouKashitsuke()).isEqualTo(sheet17.getBikouKashitsuke());
        assertThat(entitySummary.getFlgShikikin()).isEqualTo(sheet17.getFlgShikikin());
        assertThat(entitySummary.getBikouShikikin()).isEqualTo(sheet17.getBikouShikikin());
        assertThat(entitySummary.getFlgShisetsuRiyou()).isEqualTo(sheet17.getFlgShisetsuRiyou());
        assertThat(entitySummary.getBikouShisetsuRiyou()).isEqualTo(sheet17.getBikouShisetsuRiyou());
        assertThat(entitySummary.getFlgKairiire()).isEqualTo(sheet17.getFlgKairiire());
        assertThat(entitySummary.getBikouKariire()).isEqualTo(sheet17.getBikouKariire());

    }

}
