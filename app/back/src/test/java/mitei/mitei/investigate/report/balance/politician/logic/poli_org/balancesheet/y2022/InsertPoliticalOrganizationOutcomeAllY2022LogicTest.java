package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.constants.blancesheet_report.OutcomeYoushikiKbnConstants;
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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrganizationOutcomeAllLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationOutcomeAllY2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationOutcomeAllY2022Logic insertPoliticalOrganizationOutcomeAllY2022Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 政治資金収支報告書支出提出分Repository */
    @Autowired
    private OfferingBalancesheetOutcome2022Repository offeringBalancesheetOutcome2022Repository;

    @Test
    @Tag("TableTruncate")
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
        AllBookDto allBookDto = new AllBookDto();
        allBookDto.setAllSheet0714ConstsDto(allSheet0714ConstsDto);
        allBookDto.setAllSheet0715ExpenseDto(allSheet0715ExpenseDto);

        int size = insertPoliticalOrganizationOutcomeAllY2022Logic.practice(documentCode, documentPropertyDto, allBookDto,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingBalancesheetOutcome2022Entity> list = offeringBalancesheetOutcome2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetOutcomeId(documentCode);

        assertThat(size).isEqualTo(12); // 3区分+9区分
        assertThat(list.size()).isEqualTo(size); // 想定登録数と取得数が同じ

        OfferingBalancesheetOutcome2022Entity entity1401 = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1401.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity1401.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity1401.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity1401.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity1401.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity1401.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity1401.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity1401.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity1401.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity1401.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity1401.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity1401.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        final String HIMOKU_BLANK = "";

        /* その14区分1 */
        // 様式区分
        assertThat(entity1401.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14);
        assertThat(entity1401.getYoushikiEdaKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_KOUNETSUHI);

        // シート情報
        assertThat(entity1401.getPageTotal()).isEqualTo(sheet141.getPageTotal());
        assertThat(entity1401.getSonotaTotal()).isEqualTo(sheet141.getSonotaTotal());
        assertThat(entity1401.getHimoku()).isEqualTo(HIMOKU_BLANK);

        assertThat(entity1401.getIchirenNo()).isEqualTo(row141.getIchirenNo());
        assertThat(entity1401.getMokuteki()).isEqualTo(row141.getMokuteki());
        assertThat(entity1401.getKingaku()).isEqualTo(row141.getKingaku());
        assertThat(entity1401.getAccrualDate()).isEqualTo(row141.getAccrualDate());
        assertThat(entity1401.getPartnerName()).isEqualTo(row141.getName());
        assertThat(entity1401.getPartnerJuusho()).isEqualTo(row141.getJusho());
        assertThat(entity1401.getBikou()).isEqualTo(row141.getBikou());
        assertThat(entity1401.getFlgRyoushuusho()).isEqualTo(row141.getFlgRyoushuusho());
        assertThat(entity1401.getFlgKouufukin()).isEqualTo(row141.getFlgKouufukin());

        /* その14区分2 */
        OfferingBalancesheetOutcome2022Entity entity1402 = list.get(1);
        // 様式区分
        assertThat(entity1402.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14);
        assertThat(entity1402.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_SHOUMOUHINHI);

        // シート情報
        assertThat(entity1402.getPageTotal()).isEqualTo(sheet142.getPageTotal());
        assertThat(entity1402.getSonotaTotal()).isEqualTo(sheet142.getSonotaTotal());
        assertThat(entity1402.getHimoku()).isEqualTo(HIMOKU_BLANK);

        // 掲載データ情報(抜粋)
        assertThat(entity1402.getKingaku()).isEqualTo(row142.getKingaku());
        assertThat(entity1402.getAccrualDate()).isEqualTo(row142.getAccrualDate());
        assertThat(entity1402.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row142.getAccrualDate()));

        /* その14区分3 */
        OfferingBalancesheetOutcome2022Entity entity1403 = list.get(2);
        // 様式区分
        assertThat(entity1403.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14);
        assertThat(entity1403.getYoushikiEdaKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_JIMUSHOHI);

        // シート情報
        assertThat(entity1403.getPageTotal()).isEqualTo(sheet143.getPageTotal());
        assertThat(entity1403.getSonotaTotal()).isEqualTo(sheet143.getSonotaTotal());
        assertThat(entity1403.getHimoku()).isEqualTo(HIMOKU_BLANK);

        // 掲載データ情報(抜粋)
        assertThat(entity1403.getKingaku()).isEqualTo(row143.getKingaku());
        assertThat(entity1403.getAccrualDate()).isEqualTo(row143.getAccrualDate());
        assertThat(entity1403.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row143.getAccrualDate()));

        /* その15区分1 */
        OfferingBalancesheetOutcome2022Entity entity1501 = list.get(3);
        // 様式区分
        assertThat(entity1501.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1501.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SOSHIKIKATSUDOUHI);

        // シート情報
        assertThat(entity1501.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity1501.getSonotaTotal()).isEqualTo(sheet1.getSonotaTotal());
        assertThat(entity1501.getHimoku()).isEqualTo(sheet1.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1501.getKingaku()).isEqualTo(row1.getKingaku());
        assertThat(entity1501.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity1501.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));

        /* その15区分2 */
        OfferingBalancesheetOutcome2022Entity entity1502 = list.get(4);
        // 様式区分
        assertThat(entity1502.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1502.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENKYOKATSUDOUHI);

        // シート情報
        assertThat(entity1502.getPageTotal()).isEqualTo(sheet2.getPageTotal());
        assertThat(entity1502.getSonotaTotal()).isEqualTo(sheet2.getSonotaTotal());
        assertThat(entity1502.getHimoku()).isEqualTo(sheet2.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1502.getKingaku()).isEqualTo(row2.getKingaku());
        assertThat(entity1502.getAccrualDate()).isEqualTo(row2.getAccrualDate());
        assertThat(entity1502.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row2.getAccrualDate()));

        /* その15区分3 */
        OfferingBalancesheetOutcome2022Entity entity1503 = list.get(5);
        // 様式区分
        assertThat(entity1503.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1503.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIKANSHIHAKKOUHI);

        // シート情報
        assertThat(entity1503.getPageTotal()).isEqualTo(sheet3.getPageTotal());
        assertThat(entity1503.getSonotaTotal()).isEqualTo(sheet3.getSonotaTotal());
        assertThat(entity1503.getHimoku()).isEqualTo(sheet3.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1503.getKingaku()).isEqualTo(row3.getKingaku());
        assertThat(entity1503.getAccrualDate()).isEqualTo(row3.getAccrualDate());
        assertThat(entity1503.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row3.getAccrualDate()));

        /* その15区分4 */
        OfferingBalancesheetOutcome2022Entity entity1504 = list.get(6);
        // 様式区分
        assertThat(entity1504.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1504.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENDENKOUKOKUHI);

        // シート情報
        assertThat(entity1504.getPageTotal()).isEqualTo(sheet4.getPageTotal());
        assertThat(entity1504.getSonotaTotal()).isEqualTo(sheet4.getSonotaTotal());
        assertThat(entity1504.getHimoku()).isEqualTo(sheet4.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1504.getKingaku()).isEqualTo(row4.getKingaku());
        assertThat(entity1504.getAccrualDate()).isEqualTo(row4.getAccrualDate());
        assertThat(entity1504.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row4.getAccrualDate()));

        /* その15区分5 */
        OfferingBalancesheetOutcome2022Entity entity1505 = list.get(7);
        // 様式区分
        assertThat(entity1505.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1505.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_PARTYKAISAIHI);

        // シート情報
        assertThat(entity1505.getPageTotal()).isEqualTo(sheet5.getPageTotal());
        assertThat(entity1505.getSonotaTotal()).isEqualTo(sheet5.getSonotaTotal());
        assertThat(entity1505.getHimoku()).isEqualTo(sheet5.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1505.getKingaku()).isEqualTo(row5.getKingaku());
        assertThat(entity1505.getAccrualDate()).isEqualTo(row5.getAccrualDate());
        assertThat(entity1505.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row5.getAccrualDate()));

        /* その15区分6 */
        OfferingBalancesheetOutcome2022Entity entity1506 = list.get(8);
        // 様式区分
        assertThat(entity1506.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1506.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAJIGYOU);

        // シート情報
        assertThat(entity1506.getPageTotal()).isEqualTo(sheet6.getPageTotal());
        assertThat(entity1506.getSonotaTotal()).isEqualTo(sheet6.getSonotaTotal());
        assertThat(entity1506.getHimoku()).isEqualTo(sheet6.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1506.getKingaku()).isEqualTo(row6.getKingaku());
        assertThat(entity1506.getAccrualDate()).isEqualTo(row6.getAccrualDate());
        assertThat(entity1506.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row6.getAccrualDate()));

        /* その15区分7 */
        OfferingBalancesheetOutcome2022Entity entity1507 = list.get(9);
        // 様式区分
        assertThat(entity1507.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1507.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_CHOUSAKENKYUHI);

        // シート情報
        assertThat(entity1507.getPageTotal()).isEqualTo(sheet7.getPageTotal());
        assertThat(entity1507.getSonotaTotal()).isEqualTo(sheet7.getSonotaTotal());
        assertThat(entity1507.getHimoku()).isEqualTo(sheet7.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1507.getKingaku()).isEqualTo(row7.getKingaku());
        assertThat(entity1507.getAccrualDate()).isEqualTo(row7.getAccrualDate());
        assertThat(entity1507.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row7.getAccrualDate()));

        /* その15区分8 */
        OfferingBalancesheetOutcome2022Entity entity1508 = list.get(10);
        // 様式区分
        assertThat(entity1508.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1508.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIFUKOUFUKIN);

        // シート情報
        assertThat(entity1508.getPageTotal()).isEqualTo(sheet8.getPageTotal());
        assertThat(entity1508.getSonotaTotal()).isEqualTo(sheet8.getSonotaTotal());
        assertThat(entity1508.getHimoku()).isEqualTo(sheet8.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1508.getKingaku()).isEqualTo(row8.getKingaku());
        assertThat(entity1508.getAccrualDate()).isEqualTo(row8.getAccrualDate());
        assertThat(entity1508.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row8.getAccrualDate()));

        /* その15区分9 */
        OfferingBalancesheetOutcome2022Entity entity1509 = list.get(11);
        // 様式区分
        assertThat(entity1509.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1509.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAKEIHI);

        // シート情報
        assertThat(entity1509.getPageTotal()).isEqualTo(sheet9.getPageTotal());
        assertThat(entity1509.getSonotaTotal()).isEqualTo(sheet9.getSonotaTotal());
        assertThat(entity1509.getHimoku()).isEqualTo(sheet9.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1509.getKingaku()).isEqualTo(row9.getKingaku());
        assertThat(entity1509.getAccrualDate()).isEqualTo(row9.getAccrualDate());
        assertThat(entity1509.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row9.getAccrualDate()));

        // TODO 関連者

        // 自由検索用
        assertThat(entity1401.getSearchWords()).isEqualTo("目的1支出の相手先名称41東京都千代田区141");
        assertThat(entity1402.getSearchWords()).isEqualTo("目的2支出の相手先名称142東京都千代田区142");
        assertThat(entity1403.getSearchWords()).isEqualTo("目的3支出の相手先名称143東京都千代田区143");
        assertThat(entity1501.getSearchWords()).isEqualTo("費目1目的1支出の相手先名称東京都千代田区");
        assertThat(entity1502.getSearchWords()).isEqualTo("費目2目的2支出の相手先名称東京都千代田区");
        assertThat(entity1503.getSearchWords()).isEqualTo("費目3目的3支出の相手先名称東京都千代田区");
        assertThat(entity1504.getSearchWords()).isEqualTo("費目4目的4支出の相手先名称東京都千代田区");
        assertThat(entity1505.getSearchWords()).isEqualTo("費目5目的5支出の相手先名称東京都千代田区");
        assertThat(entity1506.getSearchWords()).isEqualTo("費目6目的6支出の相手先名称東京都千代田区");
        assertThat(entity1507.getSearchWords()).isEqualTo("費目7目的7支出の相手先名称東京都千代田区");
        assertThat(entity1508.getSearchWords()).isEqualTo("費目8目的8支出の相手先名称東京都千代田区");
        assertThat(entity1509.getSearchWords()).isEqualTo("費目9目的9支出の相手先名称東京都千代田区");
    }

}
