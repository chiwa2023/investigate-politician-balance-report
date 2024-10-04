package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.BookHeadDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.KaikeiKijunKingakuDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.KaikeiShishutuKijunDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0801Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.SitoUmuFlgDto;

/**
 * 政党交付金使途報告書その1とその7(表紙と宣誓書)テストデータ生成
 */
public class CreateTestDataPartyUsageShito0801And0807Logic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final int houkokuNen,final AllShitoBook allShitoBook) {

        // ドキュメント情報
        BookHeadDto head = new BookHeadDto();
        head.setVersion("20191220");
        head.setApliName("使途等報告書作成ソフト");
        head.setFlgApli("0");
        head.setFlgHonbu("0");
        allShitoBook.setBookHeadDto(head);

        // データ有無テキスト
        SitoUmuFlgDto sitoUmuFlgDto = new SitoUmuFlgDto();
        sitoUmuFlgDto.setUmuStatusText("111100000000001100");
        allShitoBook.setSitoUmuFlgDto(sitoUmuFlgDto);

        // 会計基準
        KaikeiShishutuKijunDto kijunDto = new KaikeiShishutuKijunDto();
        KaikeiKijunKingakuDto kijunKingakuDto = new KaikeiKijunKingakuDto();
        kijunKingakuDto.setAmount(50000L); // NOPMD
        kijunDto.setKaikeiKijunKingakuDto(kijunKingakuDto);
        allShitoBook.setKaikeiShishutuKijunDto(kijunDto);

        // 様式8その1
        Shito0801Dto shito0 = new Shito0801Dto();

        shito0.getSheet0801Dto().setNendo(houkokuNen);
        shito0.getSheet0801Dto().setPartyName("政党名称");
        shito0.getSheet0801Dto().setPartyNameKana("政党名称かな");
        shito0.getSheet0801Dto().setOfficeAddress("主たる事務所住所");
        shito0.getSheet0801Dto().setDelegateName("代表者姓名");
        shito0.getSheet0801Dto().setAccountManagerName("会計責任者姓名");
        shito0.getSheet0801Dto().setWorker1Name("担当者1姓名");
        shito0.getSheet0801Dto().setWorker1Tel("012-3456-xxxx");
        shito0.getSheet0801Dto().setWorker2Name("担当者2姓名");
        shito0.getSheet0801Dto().setWorker2Tel("098-7654-xxxx");
        shito0.getSheet0801Dto().setShibuKbn(2);
        shito0.getSheet0801Dto().setKaisanKbn(1);
        shito0.getSheet0801Dto().setKaisanDate("R4/12/31");
        shito0.getSheet0801Dto().setSeiriNo("445566");
        shito0.getSheet0801Dto().setUketsukeNo("556677");
        allShitoBook.setShito0801Dto(shito0);

        // 様式8その7
        Shito0807Dto shito0807Dto = new Shito0807Dto();

        Sheet0807Dto sheet = new Sheet0807Dto();
        sheet.setCopyRecipt(0);
        sheet.setAuditOption(1);
        sheet.setAuditReport(0);
        sheet.setShibuDocument(0);
        sheet.setGoverningDocument(0);
        sheet.setFlgConfirm(1);
        sheet.setAccrualDate("R5/11/30");

        shito0807Dto.setSheet0807Dto(sheet);
        allShitoBook.setShito0807Dto(shito0807Dto);

    }

}
