package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookHeaderDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookUmuInputDataDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0701CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0720OathDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet072000OathDto;

/**
 * 政党交付金使途報告書表紙と宣誓書(その1と20)テストデータ生成
 */
public class CreateTestDataPoliticalOrganizationSheet0701And0720Logic {
    // CHECKSTYLE:OFF
    
    
    /**
     * テストデータ生成
     *
     * @param allBookDto 政党交付金使途報告書Dto
     */
    public void practice(final int houkokuNen,final AllBookDto allBookDto) { // NOPMD
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
        sheet0.setHoukokuNen(houkokuNen);
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

    }
}
