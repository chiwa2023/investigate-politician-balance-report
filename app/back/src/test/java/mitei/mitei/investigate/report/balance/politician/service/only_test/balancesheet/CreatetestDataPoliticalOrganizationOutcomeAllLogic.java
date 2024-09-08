package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet; // NOPMD

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0714ConstsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0715ExpenseDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071401Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071402Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071403Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071501Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071502Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071503Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071504Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071505Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071506Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071507Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071508Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071509Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071415OrdinaryExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071401UtilityCostsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071402EquipmentCostsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071403OfficeExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071501OrganizationalActivityExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071502ElectionRelatedExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071503MagazinePublicationExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071504AdvertisingExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071505PartyHostingFeeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071506OtherBusinessExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071507ResearchExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071508DonationsGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071509OtherExpensesDto;

/**
 * 政治資金収支報告書支出(その14,15)テストデータ生成
 */
public class CreatetestDataPoliticalOrganizationOutcomeAllLogic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     * @param allBookDto 政治資金収支報告書Dto
     */
    public void practice(final AllBookDto allBookDto) { // NOPMD

        AllSheet0714ConstsDto allSheet0714ConstsDto = new AllSheet0714ConstsDto();

        // その1
        Sheet071401UtilityCostsDto sheet141 = new Sheet071401UtilityCostsDto();
        sheet141.setPageTotal(301L);
        sheet141.setSonotaTotal("301");

        Row071415OrdinaryExpensesDto row141 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row141.setIchirenNo(1);
        /// ** 支出の目的 */
        row141.setMokuteki("目的1");
        /// ** 金額 */
        row141.setKingaku(401L);
        /// ** 発生日 */
        row141.setAccrualDate("R4/11/1");
        /// ** 支出の相手先名称 */
        row141.setName("支出の相手先名称41");
        /// ** 支出の相手先住所 */
        row141.setJusho("東京都千代田区141");
        /// ** 備考 */
        row141.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row141.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row141.setFlgKouufukin(1);

        sheet141.getList().add(row141);

        // その２
        Sheet071402EquipmentCostsDto sheet142 = new Sheet071402EquipmentCostsDto();
        sheet142.setPageTotal(302L);
        sheet142.setSonotaTotal("302");

        Row071415OrdinaryExpensesDto row142 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row142.setIchirenNo(1);
        /// ** 支出の目的 */
        row142.setMokuteki("目的2");
        /// ** 金額 */
        row142.setKingaku(402L);
        /// ** 発生日 */
        row142.setAccrualDate("R4/11/2");
        /// ** 支出の相手先名称 */
        row142.setName("支出の相手先名称142");
        /// ** 支出の相手先住所 */
        row142.setJusho("東京都千代田区142");
        /// ** 備考 */
        row142.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row142.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row142.setFlgKouufukin(1);

        sheet142.getList().add(row142);

        Sheet071403OfficeExpensesDto sheet143 = new Sheet071403OfficeExpensesDto();
        sheet143.setPageTotal(303L);
        sheet143.setSonotaTotal("303");

        Row071415OrdinaryExpensesDto row143 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row143.setIchirenNo(1);
        /// ** 支出の目的 */
        row143.setMokuteki("目的3");
        /// ** 金額 */
        row143.setKingaku(403L);
        /// ** 発生日 */
        row143.setAccrualDate("R4/11/3");
        /// ** 支出の相手先名称 */
        row143.setName("支出の相手先名称143");
        /// ** 支出の相手先住所 */
        row143.setJusho("東京都千代田区143");
        /// ** 備考 */
        row143.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row143.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row143.setFlgKouufukin(1);

        sheet143.getList().add(row143);

        // 区分ごとにまとめる
        AllSheetKbn071401Dto allSheetKbn071401Dto = new AllSheetKbn071401Dto();
        allSheetKbn071401Dto.setSheet071401UtilityCostsDto(sheet141);
        AllSheetKbn071402Dto allSheetKbn071402Dto = new AllSheetKbn071402Dto();
        allSheetKbn071402Dto.setSheet071402EquipmentCostsDto(sheet142);
        AllSheetKbn071403Dto allSheetKbn071403Dto = new AllSheetKbn071403Dto();
        allSheetKbn071403Dto.setSheet071403OfficeExpensesDto(sheet143);

        // 区分データを挿入
        allSheet0714ConstsDto.setAllSheetKbn071401Dto(allSheetKbn071401Dto);
        allSheet0714ConstsDto.setAllSheetKbn071402Dto(allSheetKbn071402Dto);
        allSheet0714ConstsDto.setAllSheetKbn071403Dto(allSheetKbn071403Dto);

        AllSheet0715ExpenseDto allSheet0715ExpenseDto = new AllSheet0715ExpenseDto();

        /**
         * その1
         */
        AllSheetKbn071501Dto allSheetKbn071501Dto = new AllSheetKbn071501Dto();

        Sheet071501OrganizationalActivityExpensesDto sheet1 = new Sheet071501OrganizationalActivityExpensesDto();
        sheet1.setPageTotal(501L);
        sheet1.setSonotaTotal("501");
        sheet1.setHimoku("費目1");

        Row071415OrdinaryExpensesDto row1 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row1.setIchirenNo(1);
        /// ** 支出の目的 */
        row1.setMokuteki("目的1");
        /// ** 金額 */
        row1.setKingaku(601L);
        /// ** 発生日 */
        row1.setAccrualDate("R4/12/1"); // NOPMD
        /// ** 支出の相手先名称 */
        row1.setName("支出の相手先名称"); // NOPMD
        /// ** 支出の相手先住所 */
        row1.setJusho("東京都千代田区"); // NOPMD
        /// ** 備考 */
        row1.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row1.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row1.setFlgKouufukin(1);

        sheet1.getList().add(row1);

        allSheetKbn071501Dto.getList().add(sheet1);

        /**
         * その2
         */
        AllSheetKbn071502Dto allSheetKbn071502Dto = new AllSheetKbn071502Dto();

        Sheet071502ElectionRelatedExpensesDto sheet2 = new Sheet071502ElectionRelatedExpensesDto();
        sheet2.setPageTotal(502L);
        sheet2.setSonotaTotal("502");
        sheet2.setHimoku("費目2");

        Row071415OrdinaryExpensesDto row2 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row2.setIchirenNo(1);
        /// ** 支出の目的 */
        row2.setMokuteki("目的2");
        /// ** 金額 */
        row2.setKingaku(602L);
        /// ** 発生日 */
        row2.setAccrualDate("R4/12/2");
        /// ** 支出の相手先名称 */
        row2.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row2.setJusho("東京都千代田区");
        /// ** 備考 */
        row2.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row2.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row2.setFlgKouufukin(1);

        sheet2.getList().add(row2);

        allSheetKbn071502Dto.getList().add(sheet2);

        /**
         * その3
         */
        AllSheetKbn071503Dto allSheetKbn071503Dto = new AllSheetKbn071503Dto();

        Sheet071503MagazinePublicationExpensesDto sheet3 = new Sheet071503MagazinePublicationExpensesDto();
        sheet3.setPageTotal(503L);
        sheet3.setSonotaTotal("503");
        sheet3.setHimoku("費目3");

        Row071415OrdinaryExpensesDto row3 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row3.setIchirenNo(1);
        /// ** 支出の目的 */
        row3.setMokuteki("目的3");
        /// ** 金額 */
        row3.setKingaku(603L);
        /// ** 発生日 */
        row3.setAccrualDate("R4/12/3");
        /// ** 支出の相手先名称 */
        row3.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row3.setJusho("東京都千代田区");
        /// ** 備考 */
        row3.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row3.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row3.setFlgKouufukin(1);

        sheet3.getList().add(row3);

        allSheetKbn071503Dto.getList().add(sheet3);

        /*
         * その4
         */
        AllSheetKbn071504Dto allSheetKbn071504Dto = new AllSheetKbn071504Dto();

        Sheet071504AdvertisingExpensesDto sheet4 = new Sheet071504AdvertisingExpensesDto();
        sheet4.setPageTotal(504L);
        sheet4.setSonotaTotal("504");
        sheet4.setHimoku("費目4");

        Row071415OrdinaryExpensesDto row4 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row4.setIchirenNo(1);
        /// ** 支出の目的 */
        row4.setMokuteki("目的4");
        /// ** 金額 */
        row4.setKingaku(604L);
        /// ** 発生日 */
        row4.setAccrualDate("R4/12/4");
        /// ** 支出の相手先名称 */
        row4.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row4.setJusho("東京都千代田区");
        /// ** 備考 */
        row4.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row4.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row4.setFlgKouufukin(1);

        sheet4.getList().add(row4);

        allSheetKbn071504Dto.getList().add(sheet4);

        /**
         * その5
         */
        AllSheetKbn071505Dto allSheetKbn071505Dto = new AllSheetKbn071505Dto();

        Sheet071505PartyHostingFeeDto sheet5 = new Sheet071505PartyHostingFeeDto();
        sheet5.setPageTotal(505L);
        sheet5.setSonotaTotal("505");
        sheet5.setHimoku("費目5");

        Row071415OrdinaryExpensesDto row5 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row5.setIchirenNo(1);
        /// ** 支出の目的 */
        row5.setMokuteki("目的5");
        /// ** 金額 */
        row5.setKingaku(605L);
        /// ** 発生日 */
        row5.setAccrualDate("R4/12/5");
        /// ** 支出の相手先名称 */
        row5.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row5.setJusho("東京都千代田区");
        /// ** 備考 */
        row5.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row5.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row5.setFlgKouufukin(1);

        sheet5.getList().add(row5);

        allSheetKbn071505Dto.getList().add(sheet5);

        /**
         * その6
         */
        AllSheetKbn071506Dto allSheetKbn071506Dto = new AllSheetKbn071506Dto();

        Sheet071506OtherBusinessExpensesDto sheet6 = new Sheet071506OtherBusinessExpensesDto();
        sheet6.setPageTotal(506L);
        sheet6.setSonotaTotal("506");
        sheet6.setHimoku("費目6");

        Row071415OrdinaryExpensesDto row6 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row6.setIchirenNo(1);
        /// ** 支出の目的 */
        row6.setMokuteki("目的6");
        /// ** 金額 */
        row6.setKingaku(606L);
        /// ** 発生日 */
        row6.setAccrualDate("R4/12/6");
        /// ** 支出の相手先名称 */
        row6.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row6.setJusho("東京都千代田区");
        /// ** 備考 */
        row6.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row6.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row6.setFlgKouufukin(1);

        sheet6.getList().add(row6);

        allSheetKbn071506Dto.getList().add(sheet6);

        /**
         * その7
         */
        AllSheetKbn071507Dto allSheetKbn071507Dto = new AllSheetKbn071507Dto();

        Sheet071507ResearchExpensesDto sheet7 = new Sheet071507ResearchExpensesDto();
        sheet7.setPageTotal(507L);
        sheet7.setSonotaTotal("507");
        sheet7.setHimoku("費目7");

        Row071415OrdinaryExpensesDto row7 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row7.setIchirenNo(1);
        /// ** 支出の目的 */
        row7.setMokuteki("目的7");
        /// ** 金額 */
        row7.setKingaku(607L);
        /// ** 発生日 */
        row7.setAccrualDate("R4/12/7");
        /// ** 支出の相手先名称 */
        row7.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row7.setJusho("東京都千代田区");
        /// ** 備考 */
        row7.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row7.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row7.setFlgKouufukin(1);

        sheet7.getList().add(row7);

        allSheetKbn071507Dto.getList().add(sheet7);

        /**
         * その8
         */
        AllSheetKbn071508Dto allSheetKbn071508Dto = new AllSheetKbn071508Dto();

        Sheet071508DonationsGrantsDto sheet8 = new Sheet071508DonationsGrantsDto();
        sheet8.setPageTotal(508L);
        sheet8.setSonotaTotal("508");
        sheet8.setHimoku("費目8");

        Row071415OrdinaryExpensesDto row8 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row8.setIchirenNo(1);
        /// ** 支出の目的 */
        row8.setMokuteki("目的8");
        /// ** 金額 */
        row8.setKingaku(608L);
        /// ** 発生日 */
        row8.setAccrualDate("R4/12/8");
        /// ** 支出の相手先名称 */
        row8.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row8.setJusho("東京都千代田区");
        /// ** 備考 */
        row8.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row8.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row8.setFlgKouufukin(1);

        sheet8.getList().add(row8);

        allSheetKbn071508Dto.getList().add(sheet8);

        /**
         * その9
         */
        AllSheetKbn071509Dto allSheetKbn071509Dto = new AllSheetKbn071509Dto();

        Sheet071509OtherExpensesDto sheet9 = new Sheet071509OtherExpensesDto();
        sheet9.setPageTotal(509L);
        sheet9.setSonotaTotal("509");
        sheet9.setHimoku("費目9");

        Row071415OrdinaryExpensesDto row9 = new Row071415OrdinaryExpensesDto();
        /// ** 連番 */
        row9.setIchirenNo(1);
        /// ** 支出の目的 */
        row9.setMokuteki("目的9");
        /// ** 金額 */
        row9.setKingaku(609L);
        /// ** 発生日 */
        row9.setAccrualDate("R4/12/9");
        /// ** 支出の相手先名称 */
        row9.setName("支出の相手先名称");
        /// ** 支出の相手先住所 */
        row9.setJusho("東京都千代田区");
        /// ** 備考 */
        row9.setBikou("備考");
        /// ** 領収書を徴しがたかったフラグ */
        row9.setFlgRyoushuusho(0);
        /// ** 交付金に係る支出フラグ */
        row9.setFlgKouufukin(1);

        sheet9.getList().add(row9);

        allSheetKbn071509Dto.getList().add(sheet9);

        allSheet0715ExpenseDto.setAllSheetKbn071501Dto(allSheetKbn071501Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071502Dto(allSheetKbn071502Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071503Dto(allSheetKbn071503Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071504Dto(allSheetKbn071504Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071505Dto(allSheetKbn071505Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071506Dto(allSheetKbn071506Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071507Dto(allSheetKbn071507Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071508Dto(allSheetKbn071508Dto);
        allSheet0715ExpenseDto.setAllSheetKbn071509Dto(allSheetKbn071509Dto);

        // 全体に挿入
        allBookDto.setAllSheet0714ConstsDto(allSheet0714ConstsDto);
        allBookDto.setAllSheet0715ExpenseDto(allSheet0715ExpenseDto);

    }
}
