package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

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

import jakarta.transaction.Transactional;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookHeaderDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookUmuInputDataDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0701CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0720OathDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet072000OathDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPoliticalOrganizationSheet0701And0720Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationSheet0701And0720Y2025LogicTest {
    // CHECKSTYLE:OFF MagicNumbr
    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationSheet0701And0720Y2025Logic insertPoliticalOrganizationSheet0701And0720Y2025Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2025Repository offeringBalancesheet0701And0720Surface2025Repository;

    @Test
    @Transactional
    void testPractice() { // NOPMD

        AllBookDto allBookDto = new AllBookDto();

        // ヘッダ
        AllBookHeaderDto allBookHeaderDto = new AllBookHeaderDto();
        allBookHeaderDto.setVersion("20191220");
        allBookHeaderDto.setAppName("収支報告書作成ソフト〔収支報告書作成ソフト〕");
        allBookHeaderDto.setFileFormatNo("1");
        allBookHeaderDto.setFlgKokuji("0");
        allBookHeaderDto.setChouboAppVer("20150701");
        allBookDto.setAllBookHeaderDto(allBookHeaderDto);

        // 有無テキスト
        AllBookUmuInputDataDto allBookUmuInputDataDto = new AllBookUmuInputDataDto();
        allBookUmuInputDataDto.setInputBitText("110010000000000000001111111111111111111111111111100");
        allBookDto.setAllBookUmuInputDataDto(allBookUmuInputDataDto);

        // 様式7その1
        AllSheet0701CoverAndOrganizationDetailsDto allSheet0701CoverAndOrganizationDetailsDto = new AllSheet0701CoverAndOrganizationDetailsDto();

        // 一種類のみ
        Sheet070100CoverAndOrganizationDetailsDto sheet0 = new Sheet070100CoverAndOrganizationDetailsDto();
        sheet0.setHoukokuNen(2022);
        sheet0.setDateKaisai("R4/12/1");
        sheet0.setDantaiName01("サンプル政治団体");
        sheet0.setDantaiNameKana("さんぷるせいじだんたい");
        sheet0.setJimushoJusho("東京都千代田区");
        sheet0.setJimushoJushoTatemono("建物方書");
        sheet0.setDaihyoushaNameLast("代表者の姓");
        sheet0.setDaihyoushaNameFirst("代表者の名");
        sheet0.setKaikeiSekinnshaNameLast("会計責任者の姓");
        sheet0.setKaikeiSekinnshaNameFirst("会計責任者の名");
        sheet0.setJimuTantousha1NameLast("事務担当者1の姓");
        sheet0.setJimuTantousha1NameFirst("事務担当者1の名");
        sheet0.setJimuTantousha1Tel("000-0000-0000");
        sheet0.setJimuTantousha2NameLast("事務担当者2の姓");
        sheet0.setJimuTantousha2NameFirst("事務担当者1の名");
        sheet0.setJimuTantousha2Tel("111-1111-1111");
        sheet0.setJimuTantousha3NameLast("事務担当者3の姓");
        sheet0.setJimuTantousha3NameFirst("事務担当者3の名");
        sheet0.setJimuTantousha3Tel("222-2222-2222");
        sheet0.setDantaiKbn("05");
        sheet0.setKatsudouKuikiKbn(1);
        sheet0.setUmuShikinKanrenDantai(1);
        sheet0.setKoushokuName("国会議員");
        sheet0.setKoushokuGenKouho("1");
        sheet0.setShikinDaihyouName1("資金管理団体設立者の姓");
        sheet0.setShikinDaihyouName2("資金管理団体設立者の名");
        sheet0.setKanriDantaiPeriodStart("R4/1/1");
        sheet0.setKanriDantaiPeriodEnd("R4/12/1");
        sheet0.setKanriDantaiPeriodRest("R4/1/1-R4/2/1、R4/3/1-R4/4/1");
        sheet0.setKokkaiGiinDantaiKbn(1);
        sheet0.setKokkaiGiin1NameLast("公職者の姓");
        sheet0.setKokkaiGiin1NameFirst("公職者の名");
        sheet0.setKokkaiGiin1ShuuSan("衆議院");
        sheet0.setKokkaiGiin1GenKouho("1");
        sheet0.setGiinDantantaiTokureiPeriodStart("R4/1/1");
        sheet0.setGiinDantantaiTokureiPeriodEnd("R4/1/1");
        sheet0.setGiinDantantaiTokureiPeriodRest("R4/1/1-R4/2/1、R4/3/1-R4/4/1");
        sheet0.setKokkaiGiin2NameLast("国会議員1の姓");
        sheet0.setKokkaiGiin2NameFirst("国会議員1の名");
        sheet0.setKokkaiGiin2ShuuSan("衆議院");
        sheet0.setKokkaiGiin2GenKouho("1");
        sheet0.setKokkaiGiin3NameLast("国会議員2の姓");
        sheet0.setKokkaiGiin3NameFirst("国会議員2の名");
        sheet0.setKokkaiGiin3ShuuSan("衆議院");
        sheet0.setKokkaiGiin3GenKouho("1");
        allSheet0701CoverAndOrganizationDetailsDto.setSheet070100CoverAndOrganizationDetailsDto(sheet0);

        allBookDto.setAllSheet0701CoverAndOrganizationDetailsDto(allSheet0701CoverAndOrganizationDetailsDto);

        AllSheet0720OathDto allSheet0720OathDto = new AllSheet0720OathDto();
        Sheet072000OathDto sheet20 = new Sheet072000OathDto();
        sheet20.setFlgReciptCopy(0);
        sheet20.setFlgKansaIkensho(0);
        sheet20.setFlgSeijishikinHohkokusho(1);
        sheet20.setDateOath("R4/12/1");
        sheet20.setDantaiName20("団体名称");
        sheet20.setKaikeiSekininshaNameLast("会計責任者の姓");
        sheet20.setKaikeiSekininshaNameFirst("会計責任者の名");
        sheet20.setDaihyoushaKaisanNameLast("解散時代表者の姓");
        sheet20.setDaihyoushaKaisanNameFirst("解散時代表者の名");

        allSheet0720OathDto.setSheet072000OathDto(sheet20);
        allBookDto.setAllSheet0720OathDto(allSheet0720OathDto);

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(sheet0.getHoukokuNen()); // 実際には表紙からコピー
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(sheet20.getDateOath())); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        Long documentCode = insertPoliticalOrganizationSheet0701And0720Y2025Logic.practice(documentPropertyDto, allBookDto,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingBalancesheet0701And0720Surface2025Entity> list = offeringBalancesheet0701And0720Surface2025Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        documentCode);

        // 1件だけ登録している
        assertThat(list.size()).isEqualTo(1);

        OfferingBalancesheet0701And0720Surface2025Entity entity = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
        assertThat(entity.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity.getPoliticalOrganizationCode()).isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity.getPoliticalOrganizationName()).isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity.getRelationPersonIdDelegate()).isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity.getVersion()).isEqualTo(allBookHeaderDto.getVersion());
        assertThat(entity.getAppName()).isEqualTo(allBookHeaderDto.getAppName());
        assertThat(entity.getFileFormatNo()).isEqualTo(allBookHeaderDto.getFileFormatNo());
        assertThat(entity.getFlgKokuji()).isEqualTo(allBookHeaderDto.getFlgKokuji());
        assertThat(entity.getChouboAppVer()).isEqualTo(allBookHeaderDto.getChouboAppVer());

        assertThat(entity.getInputBitText()).isEqualTo(allBookUmuInputDataDto.getInputBitText());

        assertThat(entity.getHoukokuNen()).isEqualTo(sheet0.getHoukokuNen());
        assertThat(entity.getDateKaisai()).isEqualTo(sheet0.getDateKaisai());
        assertThat(entity.getDantaiName01()).isEqualTo(sheet0.getDantaiName01());
        assertThat(entity.getDantaiNameKana()).isEqualTo(sheet0.getDantaiNameKana());
        assertThat(entity.getJimushoJusho()).isEqualTo(sheet0.getJimushoJusho());
        assertThat(entity.getJimushoJushoTatemono()).isEqualTo(sheet0.getJimushoJushoTatemono());
        assertThat(entity.getDaihyoushaNameLast()).isEqualTo(sheet0.getDaihyoushaNameLast());
        assertThat(entity.getDaihyoushaNameFirst()).isEqualTo(sheet0.getDaihyoushaNameFirst());
        assertThat(entity.getKaikeiSekinnshaNameLast()).isEqualTo(sheet0.getKaikeiSekinnshaNameLast());
        assertThat(entity.getKaikeiSekinnshaNameFirst()).isEqualTo(sheet0.getKaikeiSekinnshaNameFirst());
        assertThat(entity.getJimuTantousha1NameLast()).isEqualTo(sheet0.getJimuTantousha1NameLast());
        assertThat(entity.getJimuTantousha1NameFirst()).isEqualTo(sheet0.getJimuTantousha1NameFirst());
        assertThat(entity.getJimuTantousha1Tel()).isEqualTo(sheet0.getJimuTantousha1Tel());
        assertThat(entity.getJimuTantousha2NameLast()).isEqualTo(sheet0.getJimuTantousha2NameLast());
        assertThat(entity.getJimuTantousha2NameFirst()).isEqualTo(sheet0.getJimuTantousha2NameFirst());
        assertThat(entity.getJimuTantousha2Tel()).isEqualTo(sheet0.getJimuTantousha2Tel());
        assertThat(entity.getJimuTantousha3NameLast()).isEqualTo(sheet0.getJimuTantousha3NameLast());
        assertThat(entity.getJimuTantousha3NameFirst()).isEqualTo(sheet0.getJimuTantousha3NameFirst());
        assertThat(entity.getJimuTantousha3Tel()).isEqualTo(sheet0.getJimuTantousha3Tel());
        assertThat(entity.getDantaiKbn()).isEqualTo(sheet0.getDantaiKbn());
        assertThat(entity.getKatsudouKuikiKbn()).isEqualTo(sheet0.getKatsudouKuikiKbn());
        assertThat(entity.getUmuShikinKanrenDantai()).isEqualTo(sheet0.getUmuShikinKanrenDantai());
        assertThat(entity.getKoushokuName()).isEqualTo(sheet0.getKoushokuName());
        assertThat(entity.getKoushokuGenKouho()).isEqualTo(sheet0.getKoushokuGenKouho());
        assertThat(entity.getShikinDaihyouName1()).isEqualTo(sheet0.getShikinDaihyouName1());
        assertThat(entity.getShikinDaihyouName2()).isEqualTo(sheet0.getShikinDaihyouName2());
        assertThat(entity.getKanriDantaiPeriodStart()).isEqualTo(sheet0.getKanriDantaiPeriodStart());
        assertThat(entity.getKanriDantaiPeriodEnd()).isEqualTo(sheet0.getKanriDantaiPeriodEnd());
        assertThat(entity.getKanriDantaiPeriodRest()).isEqualTo(sheet0.getKanriDantaiPeriodRest());
        assertThat(entity.getKokkaiGiinDantaiKbn()).isEqualTo(sheet0.getKokkaiGiinDantaiKbn());
        assertThat(entity.getKokkaiGiin1NameLast()).isEqualTo(sheet0.getKokkaiGiin1NameLast());
        assertThat(entity.getKokkaiGiin1NameFirst()).isEqualTo(sheet0.getKokkaiGiin1NameFirst());
        assertThat(entity.getKokkaiGiin1ShuuSan()).isEqualTo(sheet0.getKokkaiGiin1ShuuSan());
        assertThat(entity.getKokkaiGiin1GenKouho()).isEqualTo(sheet0.getKokkaiGiin1GenKouho());
        assertThat(entity.getGiinDantantaiTokureiPeriodStart()).isEqualTo(sheet0.getGiinDantantaiTokureiPeriodStart());
        assertThat(entity.getGiinDantantaiTokureiPeriodEnd()).isEqualTo(sheet0.getGiinDantantaiTokureiPeriodEnd());
        assertThat(entity.getGiinDantantaiTokureiPeriodRest()).isEqualTo(sheet0.getGiinDantantaiTokureiPeriodRest());
        assertThat(entity.getKokkaiGiin2NameLast()).isEqualTo(sheet0.getKokkaiGiin2NameLast());
        assertThat(entity.getKokkaiGiin2NameFirst()).isEqualTo(sheet0.getKokkaiGiin2NameFirst());
        assertThat(entity.getKokkaiGiin2ShuuSan()).isEqualTo(sheet0.getKokkaiGiin2ShuuSan());
        assertThat(entity.getKokkaiGiin2GenKouho()).isEqualTo(sheet0.getKokkaiGiin2GenKouho());
        assertThat(entity.getKokkaiGiin3NameLast()).isEqualTo(sheet0.getKokkaiGiin3NameLast());
        assertThat(entity.getKokkaiGiin3NameFirst()).isEqualTo(sheet0.getKokkaiGiin3NameFirst());
        assertThat(entity.getKokkaiGiin3ShuuSan()).isEqualTo(sheet0.getKokkaiGiin3ShuuSan());
        assertThat(entity.getKokkaiGiin3GenKouho()).isEqualTo(sheet0.getKokkaiGiin3GenKouho());

        assertThat(entity.getFlgReciptCopy()).isEqualTo(sheet20.getFlgReciptCopy());
        assertThat(entity.getFlgKansaIkensho()).isEqualTo(sheet20.getFlgKansaIkensho());
        assertThat(entity.getFlgSeijishikinHohkokusho()).isEqualTo(sheet20.getFlgSeijishikinHohkokusho());
        assertThat(entity.getDateOath()).isEqualTo(sheet20.getDateOath());
        assertThat(entity.getDantaiName20()).isEqualTo(sheet20.getDantaiName20());
        assertThat(entity.getKaikeiSekininshaNameLast()).isEqualTo(sheet20.getKaikeiSekininshaNameLast());
        assertThat(entity.getKaikeiSekininshaNameFirst()).isEqualTo(sheet20.getKaikeiSekininshaNameFirst());
        assertThat(entity.getDaihyoushaKaisanNameLast()).isEqualTo(sheet20.getDaihyoushaKaisanNameLast());
        assertThat(entity.getDaihyoushaKaisanNameFirst()).isEqualTo(sheet20.getDaihyoushaKaisanNameFirst());
    }

}
