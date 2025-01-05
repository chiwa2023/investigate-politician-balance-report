package mitei.mitei.investigate.report.balance.politician.logic.party_usage; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.BookHeadDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.KaikeiKijunKingakuDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.KaikeiShishutuKijunDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0801Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.SitoUmuFlgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0801And0807Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0801And0807Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0801And0807Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0801And0807Report2023Repository;
// import追加指定位置

/**
 * InsertPartyUsageShito0801And0807Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0801And0807LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0801And0807Logic insertPartyUsageShito0801And0807Logic;

    /** 政党交付金使途報告書Repository(2025) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2025Repository offeringPartyUsage0801And0807Report2025Repository;

    /** 政党交付金使途報告書Repository(2022) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2022Repository offeringPartyUsage0801And0807Report2022Repository;

    /** 政党交付金使途報告書Repository(2024) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2024Repository offeringPartyUsage0801And0807Report2024Repository;

    /** 政党交付金使途報告書Repository(2023) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2023Repository offeringPartyUsage0801And0807Report2023Repository;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    // テスト表示テキスト
    private static final String TEST_PRE_TEXT = "初期入力1件"; // NOPMD
    private static final String TEST_PRO_TEXT = "指定したテーブルに1件追加"; // NOPMD
    
    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    void testPractice() throws Exception { // NOPMD

        int houkokuNen = 2022;
        AllShitoBook allShitoBook = new AllShitoBook();

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

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        partyUsageDocumentPoliticalPropertyDto.setNendo(shito0.getSheet0801Dto().getNendo()); // 実際には表紙からコピー
        partyUsageDocumentPoliticalPropertyDto
                .setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(sheet.getAccrualDate())); // 実際には宣誓書からコピー
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationId(433L);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationCode(431);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        partyUsageDocumentPoliticalPropertyDto.setDantaiName("ちゃらん団体");
        partyUsageDocumentPoliticalPropertyDto.setDaihyouName("代表者 世間芸名");
        partyUsageDocumentPoliticalPropertyDto.setRelationKbn(223);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonIdDelegate(9898L);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonCodeDelegate(9867);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        Long code = insertPartyUsageShito0801And0807Logic.practice(partyUsageDocumentPoliticalPropertyDto, allShitoBook,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0801And0807Report2022Entity> list = offeringPartyUsage0801And0807Report2022Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(code);

        /* codeで呼び出すと同じデータが取得できる */

        // 1件だけ取得(1件しか登録していない)
        assertThat(list.size()).isEqualTo(1);
        OfferingPartyUsage0801And0807Report2022Entity entityRecord = list.get(0);

        // コードは一致している
        assertThat(entityRecord.getPartyUsage0801And0807ReportCode()).isEqualTo(code);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityRecord.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entityRecord.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entityRecord.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entityRecord.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entityRecord.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityRecord.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entityRecord.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entityRecord.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entityRecord.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entityRecord.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityRecord.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityRecord.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());

        assertThat(entityRecord.getVersion()).isEqualTo(head.getVersion());
        assertThat(entityRecord.getApliName()).isEqualTo(head.getApliName());
        assertThat(entityRecord.getFlgApli()).isEqualTo(head.getFlgApli());
        assertThat(entityRecord.getFlgHonbu()).isEqualTo(head.getFlgHonbu());

        assertThat(entityRecord.getJohoUmuText()).isEqualTo(sitoUmuFlgDto.getUmuStatusText());

        assertThat(entityRecord.getKaikeiKijunKingaku()).isEqualTo(kijunKingakuDto.getAmount());

        assertThat(entityRecord.getCopyRecipt()).isEqualTo(sheet.getCopyRecipt());
        assertThat(entityRecord.getAuditOption()).isEqualTo(sheet.getAuditOption());
        assertThat(entityRecord.getAuditReport()).isEqualTo(sheet.getAuditReport());
        assertThat(entityRecord.getShibuDocument()).isEqualTo(sheet.getShibuDocument());
        assertThat(entityRecord.getGoverningDocument()).isEqualTo(sheet.getGoverningDocument());
        assertThat(entityRecord.getFlgConfirm()).isEqualTo(sheet.getFlgConfirm());
        assertThat(entityRecord.getAccrualDate()).isEqualTo(sheet.getAccrualDate());

        assertThat(entityRecord.getNendo()).isEqualTo(shito0.getSheet0801Dto().getNendo());
        assertThat(entityRecord.getPartyName()).isEqualTo(shito0.getSheet0801Dto().getPartyName());
        assertThat(entityRecord.getPartyNameKana()).isEqualTo(shito0.getSheet0801Dto().getPartyNameKana());
        assertThat(entityRecord.getOfficeAddress()).isEqualTo(shito0.getSheet0801Dto().getOfficeAddress());
        assertThat(entityRecord.getDelegateName()).isEqualTo(shito0.getSheet0801Dto().getDelegateName());
        assertThat(entityRecord.getAccountManagerName()).isEqualTo(shito0.getSheet0801Dto().getAccountManagerName());
        assertThat(entityRecord.getWorker1Name()).isEqualTo(shito0.getSheet0801Dto().getWorker1Name());
        assertThat(entityRecord.getWorker1Tel()).isEqualTo(shito0.getSheet0801Dto().getWorker1Tel());
        assertThat(entityRecord.getWorker2Name()).isEqualTo(shito0.getSheet0801Dto().getWorker2Name());
        assertThat(entityRecord.getWorker2Tel()).isEqualTo(shito0.getSheet0801Dto().getWorker2Tel());
        assertThat(entityRecord.getShibuKbn()).isEqualTo(shito0.getSheet0801Dto().getShibuKbn());
        assertThat(entityRecord.getKaisanKbn()).isEqualTo(shito0.getSheet0801Dto().getKaisanKbn());
        assertThat(entityRecord.getKaisanDate()).isEqualTo(shito0.getSheet0801Dto().getKaisanDate());
        assertThat(entityRecord.getSeiriNo()).isEqualTo(shito0.getSheet0801Dto().getSeiriNo());
        assertThat(entityRecord.getUketsukeNo()).isEqualTo(shito0.getSheet0801Dto().getUketsukeNo());
    }
    
    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_party_usage_0801_and_0807_report_2022.sql")
    void testPractice2022() {
        // 指定テーブルにインサートされているかだけをチェック
        
         assertEquals(1L, offeringPartyUsage0801And0807Report2022Repository.count(),TEST_PRE_TEXT);
        
         int houkokuNen = 2022;
         AllShitoBook allShitoBook = new AllShitoBook();

         // ドキュメント情報
         Shito0801Dto shito0 = new Shito0801Dto();
         shito0.getSheet0801Dto().setNendo(houkokuNen);
         allShitoBook.setShito0801Dto(shito0);
         PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
         documentlPropertyDto.setNendo(shito0.getSheet0801Dto().getNendo()); // 実際には表紙からコピー

         insertPartyUsageShito0801And0807Logic.practice(documentlPropertyDto, allShitoBook,
                 CreateTestPrivilegeDtoUtil.pracitce());

         assertEquals(2L, offeringPartyUsage0801And0807Report2022Repository.count(),TEST_PRO_TEXT);
    }
    // テンプレート終了位置
    
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2025/offering_party_usage_0801_and_0807_report_2025.sql")
    void testPractice2025() {

         assertEquals(1L, offeringPartyUsage0801And0807Report2025Repository.count(),TEST_PRE_TEXT);
        
         int houkokuNen = 2025;
         AllShitoBook allShitoBook = new AllShitoBook();

         // ドキュメント情報
         Shito0801Dto shito0 = new Shito0801Dto();
         shito0.getSheet0801Dto().setNendo(houkokuNen);
         allShitoBook.setShito0801Dto(shito0);
         
         PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
         documentlPropertyDto.setNendo(shito0.getSheet0801Dto().getNendo()); // 実際には表紙からコピー

         insertPartyUsageShito0801And0807Logic.practice(documentlPropertyDto, allShitoBook,
                 CreateTestPrivilegeDtoUtil.pracitce());

         assertEquals(2L, offeringPartyUsage0801And0807Report2025Repository.count(),TEST_PRO_TEXT);
    }
    
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_party_usage_0801_and_0807_report_2024.sql")
    void testPractice2024() {
        // 指定テーブルにインサートされているかだけをチェック
        
         assertEquals(1L, offeringPartyUsage0801And0807Report2024Repository.count(),TEST_PRE_TEXT);
        
         int houkokuNen = 2024;
         AllShitoBook allShitoBook = new AllShitoBook();

         // ドキュメント情報
         Shito0801Dto shito0 = new Shito0801Dto();
         shito0.getSheet0801Dto().setNendo(houkokuNen);
         allShitoBook.setShito0801Dto(shito0);
         PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
         documentlPropertyDto.setNendo(shito0.getSheet0801Dto().getNendo()); // 実際には表紙からコピー

         insertPartyUsageShito0801And0807Logic.practice(documentlPropertyDto, allShitoBook,
                 CreateTestPrivilegeDtoUtil.pracitce());

         assertEquals(2L, offeringPartyUsage0801And0807Report2024Repository.count(),TEST_PRO_TEXT);
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_party_usage_0801_and_0807_report_2023.sql")
    void testPractice2023() {
        // 指定テーブルにインサートされているかだけをチェック
        
         assertEquals(1L, offeringPartyUsage0801And0807Report2023Repository.count(),TEST_PRE_TEXT);
        
         int houkokuNen = 2023;
         AllShitoBook allShitoBook = new AllShitoBook();

         // ドキュメント情報
         Shito0801Dto shito0 = new Shito0801Dto();
         shito0.getSheet0801Dto().setNendo(houkokuNen);
         allShitoBook.setShito0801Dto(shito0);
         PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
         documentlPropertyDto.setNendo(shito0.getSheet0801Dto().getNendo()); // 実際には表紙からコピー

         insertPartyUsageShito0801And0807Logic.practice(documentlPropertyDto, allShitoBook,
                 CreateTestPrivilegeDtoUtil.pracitce());

         assertEquals(2L, offeringPartyUsage0801And0807Report2023Repository.count(),TEST_PRO_TEXT);
    }

    // 追加位置

}
