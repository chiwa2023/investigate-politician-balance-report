package mitei.mitei.investigate.report.balance.politician.service; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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

import mitei.mitei.common.constants.party.usage.ConstantsKbn0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.BookHeadDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.KaikeiKijunKingakuDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080201Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080202Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080601Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080602Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080603Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0901Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShitoCoreDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0902Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0801Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0803Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.SitoUmuFlgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0802And0803Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0804Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0806Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0901Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0802And0803Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0804Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0806Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0901Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0801And0807Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0802And0803Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0802Kbn02Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0804Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0805Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0806Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0901Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0902Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageReportService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageReportServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageReportService insertPartyUsageReportService;

    /** 初期値Long */
    private static final long INIT_LONG = 0L;

    /** 初期値Integer */
    private static final int INIT_INT = 0;

    /** 初期値String */
    private static final String INIT_STRING = "";

    /** 初期値LocalDate */
    private static final LocalDate INIT_LOCALDATE = LocalDate.of(1980, 1, 1);

    /** 様式8その4区分01 */
    private static final int KBN01 = ConstantsKbn0804Dto.KBN01;
    /** 様式8その4区分02 */
    private static final int KBN02 = ConstantsKbn0804Dto.KBN02;
    /** 様式8その4区分03 */
    private static final int KBN03 = ConstantsKbn0804Dto.KBN03;
    /** 様式8その4区分04 */
    private static final int KBN04 = ConstantsKbn0804Dto.KBN04;
    /** 様式8その4区分05 */
    private static final int KBN05 = ConstantsKbn0804Dto.KBN05;
    /** 様式8その4区分06 */
    private static final int KBN06 = ConstantsKbn0804Dto.KBN06;
    /** 様式8その4区分07 */
    private static final int KBN07 = ConstantsKbn0804Dto.KBN07;
    /** 様式8その4区分08 */
    private static final int KBN08 = ConstantsKbn0804Dto.KBN08;
    /** 様式8その4区分09 */
    private static final int KBN09 = ConstantsKbn0804Dto.KBN09;
    /** 様式8その4区分10 */
    private static final int KBN10 = ConstantsKbn0804Dto.KBN10;
    /** 様式8その4区分11 */
    private static final int KBN11 = ConstantsKbn0804Dto.KBN11;
    /** 様式8その4区分12 */
    private static final int KBN12 = ConstantsKbn0804Dto.KBN12;

    /** 様式8その4区分01名称 */
    private static final String KBN01_NAME = ConstantsKbn0804Dto.KBN01_TEXT;
    /** 様式8その4区分02名称 */
    private static final String KBN02_NAME = ConstantsKbn0804Dto.KBN02_TEXT;
    /** 様式8その4区分03名称 */
    private static final String KBN03_NAME = ConstantsKbn0804Dto.KBN03_TEXT;
    /** 様式8その4区分04名称 */
    private static final String KBN04_NAME = ConstantsKbn0804Dto.KBN04_TEXT;
    /** 様式8その4区分05名称 */
    private static final String KBN05_NAME = ConstantsKbn0804Dto.KBN05_TEXT;
    /** 様式8その4区分06名称 */
    private static final String KBN06_NAME = ConstantsKbn0804Dto.KBN06_TEXT;
    /** 様式8その4区分07名称 */
    private static final String KBN07_NAME = ConstantsKbn0804Dto.KBN07_TEXT;
    /** 様式8その4区分08名称 */
    private static final String KBN08_NAME = ConstantsKbn0804Dto.KBN08_TEXT;
    /** 様式8その4区分09名称 */
    private static final String KBN09_NAME = ConstantsKbn0804Dto.KBN09_TEXT;
    /** 様式8その4区分10名称 */
    private static final String KBN10_NAME = ConstantsKbn0804Dto.KBN10_TEXT;
    /** 様式8その4区分11名称 */
    private static final String KBN11_NAME = ConstantsKbn0804Dto.KBN11_TEXT;
    /** 様式8その4区分12名称 */
    private static final String KBN12_NAME = ConstantsKbn0804Dto.KBN12_TEXT;
   
    
    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 政党交付金使途報告書Repository */
    @Autowired
    private OfferingPartyUsage0801And0807Report2025Repository offeringPartyUsage0801And0807Report2025Repository;

    /** 様式8その2区分1と3Repository */
    @Autowired
    private OfferingPartyUsage0802And0803Report2025Repository offeringPartyUsage0802And0803Report2025Repository;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2025Repository offeringPartyUsage0802Kbn02Report2025Repository;

    /** 様式8その4Repository */
    @Autowired
    private OfferingPartyUsage0804Report2025Repository offeringPartyUsage0804Report2025Repository;

    /** 様式8その5Repository */
    @Autowired
    private OfferingPartyUsage0805Report2025Repository offeringPartyUsage0805Report2025Repository;

    /** 様式8その6Repository */
    @Autowired
    private OfferingPartyUsage0806Report2025Repository offeringPartyUsage0806Report2025Repository;

    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2025Repository offeringPartyUsage0901Report2025Repository;

    /** 様式その9その2テーブルRepository */
    @Autowired
    private OfferingPartyUsage0902Report2025Repository offeringPartyUsage0902Report2025Repository;

    @Test
    @Transactional
    void test() throws Exception { // NOPMD

        /* 登録データ作成 */
        AllShitoBook allShitoBook = new AllShitoBook();

        int houkokuNen = 2025;
        String oathDateDtring = "R6/12/01";

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(houkokuNen);
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(oathDateDtring));
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        /* 様式8表紙と宣誓書 */
        CreateTestDataPartyUsageShito0801And0807Logic createTestDataPartyUsageShito0801And0807Logic = new CreateTestDataPartyUsageShito0801And0807Logic();
        createTestDataPartyUsageShito0801And0807Logic.practice(2025, allShitoBook);
        // ドキュメント情報
        BookHeadDto head = allShitoBook.getBookHeadDto();
        // データ有無テキスト
        SitoUmuFlgDto sitoUmuFlgDto = allShitoBook.getSitoUmuFlgDto();
        // 会計基準
        KaikeiKijunKingakuDto kijunKingakuDto = allShitoBook.getKaikeiShishutuKijunDto().getKaikeiKijunKingakuDto();
        // 様式8その1
        Shito0801Dto shito0801 = allShitoBook.getShito0801Dto();
        // 様式8その7
        Shito0807Dto shito0807Dto = allShitoBook.getShito0807Dto();
        Sheet0807Dto sheet0807 = shito0807Dto.getSheet0807Dto();

        /* 様式8集計表その2と3 */
        CreateTestDataPartyUsageShito0802And0803Logic createTestDataPartyUsageShito0802And0803Logic = new CreateTestDataPartyUsageShito0802And0803Logic();
        createTestDataPartyUsageShito0802And0803Logic.practice(allShitoBook);
        Shito0803Dto shito3 = allShitoBook.getShito0803Dto();

        /// * 様式8その2区分1 */
        Kbn080201Dto Kbn080201Dto = allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080201Dto();

        /* 様式8その2区分2 */
        CreateTestDataPartyUsageShito0802Kbn02Logic createTestDataPartyUsageShito0802Kbn02Logic = new CreateTestDataPartyUsageShito0802Kbn02Logic();
        createTestDataPartyUsageShito0802Kbn02Logic.practice(allShitoBook);
        Kbn080202Dto kbn1 = allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080202Dto();
        RowShito0802Dto row080202 = kbn1.getList().get(0);

        /* 様式8その4 */
        CreateTestDataPartyUsageShito0804Logic createTestDataPartyUsageShito0804Logic = new CreateTestDataPartyUsageShito0804Logic();
        createTestDataPartyUsageShito0804Logic.practice(allShitoBook);
        Sheet0804Dto sheet0804 = allShitoBook.getShito0804Dto().getKbn080404Dto().getList().get(0);
        // 1行データあり
        RowShitoCoreDto row0804 = sheet0804.getList().get(0);

        /* 様式8その5 */
        CreateTestDataPartyUsageShito0805Logic createTestDataPartyUsageShito0805Logic = new CreateTestDataPartyUsageShito0805Logic();
        createTestDataPartyUsageShito0805Logic.practice(allShitoBook);
        Sheet0805Dto sheet0805 = allShitoBook.getShito0805Dto().getSheet0805Dto();
        RowShito0805Dto row0805 = sheet0805.getList().get(0);

        /* 様式8その6 */
        CreateTestDataPartyUsageShito0806Logic createTestDataPartyUsageShito0806Logic = new CreateTestDataPartyUsageShito0806Logic();
        createTestDataPartyUsageShito0806Logic.practice(allShitoBook);
        Sheet0806Dto sheet0806 = allShitoBook.getShito0806Dto().getList().get(0);
        RowShito0806Dto row0806 = sheet0806.getKbn080601Dto().getList().get(0);
        Kbn080601Dto kbn080601 = sheet0806.getKbn080601Dto();
        Kbn080602Dto kbn080602 = sheet0806.getKbn080602Dto();
        Kbn080603Dto kbn080603 = sheet0806.getKbn080603Dto();

        /* 様式9 */
        CreateTestDataPartyUsageShito0901Logic createTestDataPartyUsageShito0901Logic = new CreateTestDataPartyUsageShito0901Logic();
        createTestDataPartyUsageShito0901Logic.practice(allShitoBook);
        RowShito0901Dto row090101 = allShitoBook.getShito0901Dto().getSheet0901Dto().getList().get(0);
        RowShito0901Dto row090102 = allShitoBook.getShito0901Dto().getSheet0901Dto().getList().get(1);

        /* 様式9その2 */
        CreateTestDataPartyUsageShito0902Logic createTestDataPartyUsageShito0902Logic = new CreateTestDataPartyUsageShito0902Logic();
        createTestDataPartyUsageShito0902Logic.practice(allShitoBook);
        Sheet0902Dto sheet090201 = allShitoBook.getShito0902Dto().getList().get(0);
        Sheet0902Dto sheet090202 = allShitoBook.getShito0902Dto().getList().get(1);

        /*
         * 
         * 登録作業
         * 
         */
        RegistPoliticalPartyUsageReportResultDto resultDto = insertPartyUsageReportService.practice(documentPropertyDto,
                CreateTestPrivilegeDtoUtil.pracitce(), allShitoBook, false);
        Long documentCode = resultDto.getDocumentCode();

        /*
         * 検証
         */
        List<OfferingPartyUsage0801And0807Report2025Entity> listSurface = offeringPartyUsage0801And0807Report2025Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(documentCode);

        // 1件だけ取得(1件しか登録していない)
        assertThat(listSurface.size()).isEqualTo(1);
        OfferingPartyUsage0801And0807Report2025Entity entityRecord = listSurface.get(0);

        // コードは一致している
        assertThat(entityRecord.getPartyUsage0801And0807ReportCode()).isEqualTo(documentCode);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityRecord.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entityRecord.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entityRecord.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entityRecord.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entityRecord.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityRecord.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entityRecord.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entityRecord.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entityRecord.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entityRecord.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityRecord.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityRecord.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entityRecord.getVersion()).isEqualTo(head.getVersion());
        assertThat(entityRecord.getApliName()).isEqualTo(head.getApliName());
        assertThat(entityRecord.getFlgApli()).isEqualTo(head.getFlgApli());
        assertThat(entityRecord.getFlgHonbu()).isEqualTo(head.getFlgHonbu());

        assertThat(entityRecord.getJohoUmuText()).isEqualTo(sitoUmuFlgDto.getUmuStatusText());

        assertThat(entityRecord.getKaikeiKijunKingaku()).isEqualTo(kijunKingakuDto.getAmount());

        assertThat(entityRecord.getCopyRecipt()).isEqualTo(sheet0807.getCopyRecipt());
        assertThat(entityRecord.getAuditOption()).isEqualTo(sheet0807.getAuditOption());
        assertThat(entityRecord.getAuditReport()).isEqualTo(sheet0807.getAuditReport());
        assertThat(entityRecord.getShibuDocument()).isEqualTo(sheet0807.getShibuDocument());
        assertThat(entityRecord.getGoverningDocument()).isEqualTo(sheet0807.getGoverningDocument());
        assertThat(entityRecord.getFlgConfirm()).isEqualTo(sheet0807.getFlgConfirm());
        assertThat(entityRecord.getAccrualDate()).isEqualTo(sheet0807.getAccrualDate());

        assertThat(entityRecord.getNendo()).isEqualTo(shito0801.getSheet0801Dto().getNendo());
        assertThat(entityRecord.getPartyName()).isEqualTo(shito0801.getSheet0801Dto().getPartyName());
        assertThat(entityRecord.getPartyNameKana()).isEqualTo(shito0801.getSheet0801Dto().getPartyNameKana());
        assertThat(entityRecord.getOfficeAddress()).isEqualTo(shito0801.getSheet0801Dto().getOfficeAddress());
        assertThat(entityRecord.getDelegateName()).isEqualTo(shito0801.getSheet0801Dto().getDelegateName());
        assertThat(entityRecord.getAccountManagerName()).isEqualTo(shito0801.getSheet0801Dto().getAccountManagerName());
        assertThat(entityRecord.getWorker1Name()).isEqualTo(shito0801.getSheet0801Dto().getWorker1Name());
        assertThat(entityRecord.getWorker1Tel()).isEqualTo(shito0801.getSheet0801Dto().getWorker1Tel());
        assertThat(entityRecord.getWorker2Name()).isEqualTo(shito0801.getSheet0801Dto().getWorker2Name());
        assertThat(entityRecord.getWorker2Tel()).isEqualTo(shito0801.getSheet0801Dto().getWorker2Tel());
        assertThat(entityRecord.getShibuKbn()).isEqualTo(shito0801.getSheet0801Dto().getShibuKbn());
        assertThat(entityRecord.getKaisanKbn()).isEqualTo(shito0801.getSheet0801Dto().getKaisanKbn());
        assertThat(entityRecord.getKaisanDate()).isEqualTo(shito0801.getSheet0801Dto().getKaisanDate());
        assertThat(entityRecord.getSeiriNo()).isEqualTo(shito0801.getSheet0801Dto().getSeiriNo());
        assertThat(entityRecord.getUketsukeNo()).isEqualTo(shito0801.getSheet0801Dto().getUketsukeNo());

        List<OfferingPartyUsage0802And0803Report2025Entity> listSummary = offeringPartyUsage0802And0803Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0802And0803ReportId(documentCode);

        assertThat(listSummary.size()).isEqualTo(1); // 1件だけ登録

        OfferingPartyUsage0802And0803Report2025Entity entity08021 = listSummary.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity08021.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity08021.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity08021.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity08021.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity08021.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity08021.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity08021.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity08021.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity08021.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity08021.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity08021.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity08021.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity08021.getTotalShibuKoufuAll()).isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuAll());
        assertThat(entity08021.getTotalShibuKoufuJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuJutoKoufukin());
        assertThat(entity08021.getTotalShibuKoufuJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuJutoMyFunds());
        assertThat(entity08021.getTotalShibuKoufuBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuBikou());
        assertThat(entity08021.getTotalJinkenhiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiAll());
        assertThat(entity08021.getTotalJinkenhiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiJutoKoufukin());
        assertThat(entity08021.getTotalJinkenhiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiJutoMyFunds());
        assertThat(entity08021.getTotalJinkenhiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiBikou());
        assertThat(entity08021.getTotalKounetsuhiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiAll());
        assertThat(entity08021.getTotalKounetsuhiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiJutoKoufukin());
        assertThat(entity08021.getTotalKounetsuhiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiJutoMyFunds());
        assertThat(entity08021.getTotalKounetsuhiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiBikou());
        assertThat(entity08021.getTotalBihinAll()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinAll());
        assertThat(entity08021.getTotalBihinJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalBihinJutoKoufukin());
        assertThat(entity08021.getTotalBihinJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalBihinJutoMyFunds());
        assertThat(entity08021.getTotalBihinBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinBikou());
        assertThat(entity08021.getTotalJimushoAll()).isEqualTo(shito3.getSheet0803Dto().getTotalJimushoAll());
        assertThat(entity08021.getTotalJimushoJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJimushoJutoKoufukin());
        assertThat(entity08021.getTotalJimushoJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJimushoJutoMyFunds());
        assertThat(entity08021.getTotalJimushoBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalJimushoBikou());
        assertThat(entity08021.getTotalKeihiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiAll());
        assertThat(entity08021.getTotalKeihiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKeihiJutoKoufukin());
        assertThat(entity08021.getTotalKeihiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKeihiJutoMyFunds());
        assertThat(entity08021.getTotalKeihiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiBikou());
        assertThat(entity08021.getTotalSoshikiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiAll());
        assertThat(entity08021.getTotalSoshikiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiJutoKoufukin());
        assertThat(entity08021.getTotalSoshikiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiJutoMyFunds());
        assertThat(entity08021.getTotalSoshikiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiBikou());
        assertThat(entity08021.getTotalSenkyoAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoAll());
        assertThat(entity08021.getTotalSenkyoJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoJutoKoufukin());
        assertThat(entity08021.getTotalSenkyoJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoJutoMyFunds());
        assertThat(entity08021.getTotalSenkyoBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoBikou());
        assertThat(entity08021.getTotalAllJigyouAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouAll());
        assertThat(entity08021.getTotalAllJigyouJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouJutoKoufukin());
        assertThat(entity08021.getTotalAllJigyouJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouJutoMyFunds());
        assertThat(entity08021.getTotalAllJigyouBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouBikou());
        assertThat(entity08021.getTotalKikanshiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiAll());
        assertThat(entity08021.getTotalKikanshiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiJutoKoufukin());
        assertThat(entity08021.getTotalKikanshiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiJutoMyFunds());
        assertThat(entity08021.getTotalKikanshiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiBikou());
        assertThat(entity08021.getTotalSendenAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenAll());
        assertThat(entity08021.getTotalSendenJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSendenJutoKoufukin());
        assertThat(entity08021.getTotalSendenJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSendenJutoMyFunds());
        assertThat(entity08021.getTotalSendenBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenBikou());
        assertThat(entity08021.getTotalPartyAll()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyAll());
        assertThat(entity08021.getTotalPartyJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalPartyJutoKoufukin());
        assertThat(entity08021.getTotalPartyJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalPartyJutoMyFunds());
        assertThat(entity08021.getTotalPartyBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyBikou());
        assertThat(entity08021.getTotalSonotaJigyouAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouAll());
        assertThat(entity08021.getTotalSonotaJigyouJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouJutoKoufukin());
        assertThat(entity08021.getTotalSonotaJigyouJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouJutoMyFunds());
        assertThat(entity08021.getTotalSonotaJigyouBikou())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouBikou());
        assertThat(entity08021.getTotalChousaAll()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaAll());
        assertThat(entity08021.getTotalChousaJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalChousaJutoKoufukin());
        assertThat(entity08021.getTotalChousaJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalChousaJutoMyFunds());
        assertThat(entity08021.getTotalChousaBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaBikou());
        assertThat(entity08021.getTotalKifuAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuAll());
        assertThat(entity08021.getTotalKifuJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKifuJutoKoufukin());
        assertThat(entity08021.getTotalKifuJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuJutoMyFunds());
        assertThat(entity08021.getTotalKifuBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuBikou());
        assertThat(entity08021.getTotalSonotaKeihiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiAll());
        assertThat(entity08021.getTotalSonotaKeihiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiJutoKoufukin());
        assertThat(entity08021.getTotalSonotaKeihiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiJutoMyFunds());
        assertThat(entity08021.getTotalSonotaKeihiBikou())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiBikou());
        assertThat(entity08021.getTotalAllActionAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllActionAll());
        assertThat(entity08021.getTotalAllActionJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllActionJutoKoufukin());
        assertThat(entity08021.getTotalAllActionJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllActionJutoMyFunds());
        assertThat(entity08021.getTotalAllActionBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllActionBikou());
        assertThat(entity08021.getTotalAllAmountAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountAll());
        assertThat(entity08021.getTotalAllAmountJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountJutoKoufukin());
        assertThat(entity08021.getTotalAllAmountJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountJutoMyFunds());
        assertThat(entity08021.getTotalAllAmountBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountBikou());

        // 政党交付金（支部政党交付金）の総額 --------------------------------------------①
        // list.get(0).getAmount(); party_all_koufukin
        assertThat(entity08021.getPartyAllKoufukin()).isEqualTo(Kbn080201Dto.getList().get(0).getAmount());

        // 前年末政党基金（支部基金）残高 ------------------------------------------------②
        // list.get(1).getAmount(); last_year_rest
        assertThat(entity08021.getLastYearRest()).isEqualTo(Kbn080201Dto.getList().get(1).getAmount());

        // 政党交付金（支部政党交付金）による支出総額（④＋⑤） --------------------------③
        // list.get(2).getAmount(); outcome_koufukin_all
        assertThat(entity08021.getOutcomeKoufukinAll()).isEqualTo(Kbn080201Dto.getList().get(2).getAmount());

        // 政党交付金（支部政党交付金）支出充当額総額 ----------------------------------④
        // list.get(3).getAmount(); outcome_koufukin
        assertThat(entity08021.getOutcomeKoufukin()).isEqualTo(Kbn080201Dto.getList().get(3).getAmount());

        // 政党基金（支部基金）支出充当額総額 ------------------------------------------⑤
        // list.get(4).getAmount(); outcome_shibu_kikin
        assertThat(entity08021.getOutcomeShibuKikin()).isEqualTo(Kbn080201Dto.getList().get(4).getAmount());

        // 政党基金（支部基金）の積立に充てるために取り崩した政党基金（支部基金）の額 ----⑥
        // list.get(5).getAmount(); //withdrawal_funds
        assertThat(entity08021.getWithdrawalFunds()).isEqualTo(Kbn080201Dto.getList().get(5).getAmount());

        // 政党基金（支部基金）積立総額（果実を含む。） ----------------------------------⑦
        // list.get(6).getAmount(); accumulate_funds
        assertThat(entity08021.getAccumulateFunds()).isEqualTo(Kbn080201Dto.getList().get(6).getAmount());

        // 政党基金（支部基金）の運用により収受した果実の総額
        // list.get(7).getAmount(); funds_sum_gain
        assertThat(entity08021.getFundsSumGain()).isEqualTo(Kbn080201Dto.getList().get(7).getAmount());

        // 本年末等政党基金（支部基金）残高（②－⑤－⑥＋⑦） ----------------------------⑧
        // list.get(8).getAmount(); this_year_rest
        assertThat(entity08021.getThisYearRest()).isEqualTo(Kbn080201Dto.getList().get(8).getAmount());

        // （備 考） ①－③＋②－⑧
        // list.get(9).getAmount(); bikou_differ
        assertThat(entity08021.getBikouDiffer()).isEqualTo(Kbn080201Dto.getList().get(9).getAmount());

        List<OfferingPartyUsage0802Kbn02Report2025Entity> list080202 = offeringPartyUsage0802Kbn02Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(documentCode);

        assertThat(list080202.size()).isEqualTo(1);// 1行しか登録していません

        OfferingPartyUsage0802Kbn02Report2025Entity entity080202 = list080202.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity080202.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity080202.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity080202.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity080202.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity080202.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity080202.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity080202.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity080202.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity080202.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity080202.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity080202.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity080202.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        // kbn1.setTotalAmount(200000L);
        assertThat(entity080202.getTotalAmount()).isEqualTo(kbn1.getTotalAmount());
        // row0.setRowNo(3);
        assertThat(entity080202.getRowNo()).isEqualTo(row080202.getRowNo());
        // row0.setItemName("項目名称");
        assertThat(entity080202.getItemName()).isEqualTo(row080202.getItemName());
        // row0.setAccrualDate("R4/12/1");
        assertThat(entity080202.getAccrualDate()).isEqualTo(row080202.getAccrualDate());
        // row0.setAmount(65432L);
        assertThat(entity080202.getAmount()).isEqualTo(row080202.getAmount());

        List<OfferingPartyUsage0804Report2025Entity> list0804 = offeringPartyUsage0804Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0804ReportId(documentCode);

        assertThat(list0804.size()).isEqualTo(24);// 設定数と登録数が一致

        OfferingPartyUsage0804Report2025Entity entity01 = list0804.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity01.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity01.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity01.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity01.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity01.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity01.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity01.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity01.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity01.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity01.getRelationPersonIdDelegate()).isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity01.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity01.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* シート情報 */
        assertThat(entity01.getShishutsuKbn()).isEqualTo(KBN01);
        assertThat(entity01.getShishutsuKbnName()).isEqualTo(KBN01_NAME);

        assertThat(entity01.getSheetHimoku()).isEqualTo(sheet0804.getHimoku());
        assertThat(entity01.getSheetAmountAll()).isEqualTo(sheet0804.getAmountAll());
        assertThat(entity01.getSheetAmountAllKoufukin()).isEqualTo(sheet0804.getAmountAllKoufukin());
        assertThat(entity01.getSheetAmountAllMyFunds()).isEqualTo(sheet0804.getAmountAllMyFunds());
        assertThat(entity01.getSheetAmountSonota()).isEqualTo(sheet0804.getSonotaAmount());
        assertThat(entity01.getSheetAmountSonotaKoufukin()).isEqualTo(sheet0804.getSonotaKoufukin());
        assertThat(entity01.getSheetAmountSonotaMyFunds()).isEqualTo(sheet0804.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity01.getRowNo()).isEqualTo(row0804.getRowNo());
        assertThat(entity01.getUsageItem()).isEqualTo(row0804.getUsageItem());
        assertThat(entity01.getAmountAll()).isEqualTo(row0804.getAmountAll());
        assertThat(entity01.getAmountKoufukin()).isEqualTo(row0804.getAmountKoufukin());
        assertThat(entity01.getAmountMyFunds()).isEqualTo(row0804.getAmountMyFunds());
        assertThat(entity01.getAccrualDate()).isEqualTo(row0804.getAccrualDate());
        assertThat(entity01.getPayeeName()).isEqualTo(row0804.getPayeeName());
        assertThat(entity01.getAddress()).isEqualTo(row0804.getAddress());
        assertThat(entity01.getBikou()).isEqualTo(row0804.getBikou());
        assertThat(entity01.getFlgCollectRecipt()).isEqualTo(row0804.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity01.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row0804.getAccrualDate()));
        assertThat(entity01.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity01.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity01.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity01.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity01.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity01.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity01.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity01.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity01.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity01.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        OfferingPartyUsage0804Report2025Entity entity02 = list0804.get(1);

        /* シート情報 */
        assertThat(entity02.getShishutsuKbn()).isEqualTo(KBN01);
        assertThat(entity02.getShishutsuKbnName()).isEqualTo(KBN01_NAME);

        assertThat(entity02.getSheetHimoku()).isEqualTo(INIT_STRING);
        assertThat(entity02.getSheetAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity02.getSheetAmountAllKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity02.getSheetAmountAllMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity02.getSheetAmountSonota()).isEqualTo(INIT_LONG);
        assertThat(entity02.getSheetAmountSonotaKoufukin()).isEqualTo(null);
        assertThat(entity02.getSheetAmountSonotaMyFunds()).isEqualTo(null);
        /* 行情報 */
        assertThat(entity02.getRowNo()).isEqualTo(INIT_INT);
        assertThat(entity02.getUsageItem()).isEqualTo(INIT_STRING);
        assertThat(entity02.getAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity02.getAmountKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity02.getAmountMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity02.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity02.getPayeeName()).isEqualTo(INIT_STRING);
        assertThat(entity02.getAddress()).isEqualTo(INIT_STRING);
        assertThat(entity02.getBikou()).isEqualTo(INIT_STRING);
        assertThat(entity02.getFlgCollectRecipt()).isEqualTo(INIT_INT);
        /* 追加情報 */
        assertThat(entity02.getAccrualDateValue()).isEqualTo(INIT_LOCALDATE);
        assertThat(entity02.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity02.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity02.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity02.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity02.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity02.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity02.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity02.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity02.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity02.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* 区分02 */
        OfferingPartyUsage0804Report2025Entity entity03 = list0804.get(2);
        OfferingPartyUsage0804Report2025Entity entity04 = list0804.get(3);

        /* シート情報 */
        assertThat(entity03.getShishutsuKbn()).isEqualTo(KBN02);
        assertThat(entity03.getShishutsuKbnName()).isEqualTo(KBN02_NAME);

        assertThat(entity03.getSheetHimoku()).isEqualTo(sheet0804.getHimoku());
        assertThat(entity03.getSheetAmountAll()).isEqualTo(sheet0804.getAmountAll());
        assertThat(entity03.getSheetAmountAllKoufukin()).isEqualTo(sheet0804.getAmountAllKoufukin());
        assertThat(entity03.getSheetAmountAllMyFunds()).isEqualTo(sheet0804.getAmountAllMyFunds());
        assertThat(entity03.getSheetAmountSonota()).isEqualTo(sheet0804.getSonotaAmount());
        assertThat(entity03.getSheetAmountSonotaKoufukin()).isEqualTo(sheet0804.getSonotaKoufukin());
        assertThat(entity03.getSheetAmountSonotaMyFunds()).isEqualTo(sheet0804.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity03.getRowNo()).isEqualTo(row0804.getRowNo());
        assertThat(entity03.getUsageItem()).isEqualTo(row0804.getUsageItem());
        assertThat(entity03.getAmountAll()).isEqualTo(row0804.getAmountAll());
        assertThat(entity03.getAmountKoufukin()).isEqualTo(row0804.getAmountKoufukin());
        assertThat(entity03.getAmountMyFunds()).isEqualTo(row0804.getAmountMyFunds());
        assertThat(entity03.getAccrualDate()).isEqualTo(row0804.getAccrualDate());
        assertThat(entity03.getPayeeName()).isEqualTo(row0804.getPayeeName());
        assertThat(entity03.getAddress()).isEqualTo(row0804.getAddress());
        assertThat(entity03.getBikou()).isEqualTo(row0804.getBikou());
        assertThat(entity03.getFlgCollectRecipt()).isEqualTo(row0804.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity03.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row0804.getAccrualDate()));
        assertThat(entity03.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity03.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity03.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity03.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity03.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity03.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity03.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity03.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity03.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity03.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* シート情報 */
        assertThat(entity04.getShishutsuKbn()).isEqualTo(KBN02);
        assertThat(entity04.getShishutsuKbnName()).isEqualTo(KBN02_NAME);

        assertThat(entity04.getSheetHimoku()).isEqualTo(INIT_STRING);
        assertThat(entity04.getSheetAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity04.getSheetAmountAllKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity04.getSheetAmountAllMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity04.getSheetAmountSonota()).isEqualTo(INIT_LONG);
        assertThat(entity04.getSheetAmountSonotaKoufukin()).isEqualTo(null);
        assertThat(entity04.getSheetAmountSonotaMyFunds()).isEqualTo(null);
        /* 行情報 */
        assertThat(entity04.getRowNo()).isEqualTo(INIT_INT);
        assertThat(entity04.getUsageItem()).isEqualTo(INIT_STRING);
        assertThat(entity04.getAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity04.getAmountKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity04.getAmountMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity04.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity04.getPayeeName()).isEqualTo(INIT_STRING);
        assertThat(entity04.getAddress()).isEqualTo(INIT_STRING);
        assertThat(entity04.getBikou()).isEqualTo(INIT_STRING);
        assertThat(entity04.getFlgCollectRecipt()).isEqualTo(INIT_INT);
        /* 追加情報 */
        assertThat(entity04.getAccrualDateValue()).isEqualTo(INIT_LOCALDATE);
        assertThat(entity04.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity04.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity04.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity04.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity04.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity04.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity04.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity04.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity04.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity04.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* 区分03 */
        OfferingPartyUsage0804Report2025Entity entity05 = list0804.get(4);
        OfferingPartyUsage0804Report2025Entity entity06 = list0804.get(5);

        /* シート情報 */
        assertThat(entity05.getShishutsuKbn()).isEqualTo(KBN03);
        assertThat(entity05.getShishutsuKbnName()).isEqualTo(KBN03_NAME);

        assertThat(entity05.getSheetHimoku()).isEqualTo(sheet0804.getHimoku());
        assertThat(entity05.getSheetAmountAll()).isEqualTo(sheet0804.getAmountAll());
        assertThat(entity05.getSheetAmountAllKoufukin()).isEqualTo(sheet0804.getAmountAllKoufukin());
        assertThat(entity05.getSheetAmountAllMyFunds()).isEqualTo(sheet0804.getAmountAllMyFunds());
        assertThat(entity05.getSheetAmountSonota()).isEqualTo(sheet0804.getSonotaAmount());
        assertThat(entity05.getSheetAmountSonotaKoufukin()).isEqualTo(sheet0804.getSonotaKoufukin());
        assertThat(entity05.getSheetAmountSonotaMyFunds()).isEqualTo(sheet0804.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity05.getRowNo()).isEqualTo(row0804.getRowNo());
        assertThat(entity05.getUsageItem()).isEqualTo(row0804.getUsageItem());
        assertThat(entity05.getAmountAll()).isEqualTo(row0804.getAmountAll());
        assertThat(entity05.getAmountKoufukin()).isEqualTo(row0804.getAmountKoufukin());
        assertThat(entity05.getAmountMyFunds()).isEqualTo(row0804.getAmountMyFunds());
        assertThat(entity05.getAccrualDate()).isEqualTo(row0804.getAccrualDate());
        assertThat(entity05.getPayeeName()).isEqualTo(row0804.getPayeeName());
        assertThat(entity05.getAddress()).isEqualTo(row0804.getAddress());
        assertThat(entity05.getBikou()).isEqualTo(row0804.getBikou());
        assertThat(entity05.getFlgCollectRecipt()).isEqualTo(row0804.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity05.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row0804.getAccrualDate()));
        assertThat(entity05.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity05.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity05.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity05.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity05.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity05.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity05.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity05.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity05.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity05.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* シート情報 */
        assertThat(entity06.getShishutsuKbn()).isEqualTo(KBN03);
        assertThat(entity06.getShishutsuKbnName()).isEqualTo(KBN03_NAME);

        assertThat(entity06.getSheetHimoku()).isEqualTo(INIT_STRING);
        assertThat(entity06.getSheetAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity06.getSheetAmountAllKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity06.getSheetAmountAllMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity06.getSheetAmountSonota()).isEqualTo(INIT_LONG);
        assertThat(entity06.getSheetAmountSonotaKoufukin()).isEqualTo(null);
        assertThat(entity06.getSheetAmountSonotaMyFunds()).isEqualTo(null);
        /* 行情報 */
        assertThat(entity06.getRowNo()).isEqualTo(INIT_INT);
        assertThat(entity06.getUsageItem()).isEqualTo(INIT_STRING);
        assertThat(entity06.getAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity06.getAmountKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity06.getAmountMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity06.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity06.getPayeeName()).isEqualTo(INIT_STRING);
        assertThat(entity06.getAddress()).isEqualTo(INIT_STRING);
        assertThat(entity06.getBikou()).isEqualTo(INIT_STRING);
        assertThat(entity06.getFlgCollectRecipt()).isEqualTo(INIT_INT);
        /* 追加情報 */
        assertThat(entity06.getAccrualDateValue()).isEqualTo(INIT_LOCALDATE);
        assertThat(entity06.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity06.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity06.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity06.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity06.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity06.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity06.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity06.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity06.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity06.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* 区分04 */
        OfferingPartyUsage0804Report2025Entity entity07 = list0804.get(6);
        OfferingPartyUsage0804Report2025Entity entity08 = list0804.get(7);

        /* シート情報 */
        assertThat(entity07.getShishutsuKbn()).isEqualTo(KBN04);
        assertThat(entity07.getShishutsuKbnName()).isEqualTo(KBN04_NAME);

        assertThat(entity07.getSheetHimoku()).isEqualTo(sheet0804.getHimoku());
        assertThat(entity07.getSheetAmountAll()).isEqualTo(sheet0804.getAmountAll());
        assertThat(entity07.getSheetAmountAllKoufukin()).isEqualTo(sheet0804.getAmountAllKoufukin());
        assertThat(entity07.getSheetAmountAllMyFunds()).isEqualTo(sheet0804.getAmountAllMyFunds());
        assertThat(entity07.getSheetAmountSonota()).isEqualTo(sheet0804.getSonotaAmount());
        assertThat(entity07.getSheetAmountSonotaKoufukin()).isEqualTo(sheet0804.getSonotaKoufukin());
        assertThat(entity07.getSheetAmountSonotaMyFunds()).isEqualTo(sheet0804.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity07.getRowNo()).isEqualTo(row0804.getRowNo());
        assertThat(entity07.getUsageItem()).isEqualTo(row0804.getUsageItem());
        assertThat(entity07.getAmountAll()).isEqualTo(row0804.getAmountAll());
        assertThat(entity07.getAmountKoufukin()).isEqualTo(row0804.getAmountKoufukin());
        assertThat(entity07.getAmountMyFunds()).isEqualTo(row0804.getAmountMyFunds());
        assertThat(entity07.getAccrualDate()).isEqualTo(row0804.getAccrualDate());
        assertThat(entity07.getPayeeName()).isEqualTo(row0804.getPayeeName());
        assertThat(entity07.getAddress()).isEqualTo(row0804.getAddress());
        assertThat(entity07.getBikou()).isEqualTo(row0804.getBikou());
        assertThat(entity07.getFlgCollectRecipt()).isEqualTo(row0804.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity07.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row0804.getAccrualDate()));
        assertThat(entity07.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity07.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity07.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity07.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity07.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity07.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity07.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity07.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity07.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity07.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* シート情報 */
        assertThat(entity08.getShishutsuKbn()).isEqualTo(KBN04);
        assertThat(entity08.getShishutsuKbnName()).isEqualTo(KBN04_NAME);

        assertThat(entity08.getSheetHimoku()).isEqualTo(INIT_STRING);
        assertThat(entity08.getSheetAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity08.getSheetAmountAllKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity08.getSheetAmountAllMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity08.getSheetAmountSonota()).isEqualTo(INIT_LONG);
        assertThat(entity08.getSheetAmountSonotaKoufukin()).isEqualTo(null);
        assertThat(entity08.getSheetAmountSonotaMyFunds()).isEqualTo(null);
        /* 行情報 */
        assertThat(entity08.getRowNo()).isEqualTo(INIT_INT);
        assertThat(entity08.getUsageItem()).isEqualTo(INIT_STRING);
        assertThat(entity08.getAmountAll()).isEqualTo(INIT_LONG);
        assertThat(entity08.getAmountKoufukin()).isEqualTo(INIT_LONG);
        assertThat(entity08.getAmountMyFunds()).isEqualTo(INIT_LONG);
        assertThat(entity08.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity08.getPayeeName()).isEqualTo(INIT_STRING);
        assertThat(entity08.getAddress()).isEqualTo(INIT_STRING);
        assertThat(entity08.getBikou()).isEqualTo(INIT_STRING);
        assertThat(entity08.getFlgCollectRecipt()).isEqualTo(INIT_INT);
        /* 追加情報 */
        assertThat(entity08.getAccrualDateValue()).isEqualTo(INIT_LOCALDATE);
        assertThat(entity08.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity08.getRelationPersonIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity08.getRelationPersonCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity08.getRelationPersonNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity08.getRelationCorpIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity08.getRelationCorpCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity08.getRelationCorpNamePayee()).isEqualTo(INIT_STRING);
        assertThat(entity08.getRelationPoliticalOrgIdPayee()).isEqualTo(INIT_LONG);
        assertThat(entity08.getRelationPoliticalOrgCodePayee()).isEqualTo(INIT_INT);
        assertThat(entity08.getRelationPoliticalOrgNamePayee()).isEqualTo(INIT_STRING);

        /* 区分05 */
        OfferingPartyUsage0804Report2025Entity entity09 = list0804.get(8);
        OfferingPartyUsage0804Report2025Entity entity10 = list0804.get(9);

        assertThat(entity09.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity09.getShishutsuKbnName()).isEqualTo(KBN05_NAME);
        assertThat(entity10.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity10.getShishutsuKbnName()).isEqualTo(KBN05_NAME);

        /* 区分06 */
        OfferingPartyUsage0804Report2025Entity entity11 = list0804.get(10);
        OfferingPartyUsage0804Report2025Entity entity12 = list0804.get(11);

        assertThat(entity11.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity11.getShishutsuKbnName()).isEqualTo(KBN06_NAME);
        assertThat(entity12.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity12.getShishutsuKbnName()).isEqualTo(KBN06_NAME);

        /* 区分07 */
        OfferingPartyUsage0804Report2025Entity entity13 = list0804.get(12);
        OfferingPartyUsage0804Report2025Entity entity14 = list0804.get(13);

        assertThat(entity13.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity13.getShishutsuKbnName()).isEqualTo(KBN07_NAME);
        assertThat(entity14.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity14.getShishutsuKbnName()).isEqualTo(KBN07_NAME);

        /* 区分08 */
        OfferingPartyUsage0804Report2025Entity entity15 = list0804.get(14);
        OfferingPartyUsage0804Report2025Entity entity16 = list0804.get(15);

        assertThat(entity15.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity15.getShishutsuKbnName()).isEqualTo(KBN08_NAME);
        assertThat(entity16.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity16.getShishutsuKbnName()).isEqualTo(KBN08_NAME);

        /* 区分09 */
        OfferingPartyUsage0804Report2025Entity entity17 = list0804.get(16);
        OfferingPartyUsage0804Report2025Entity entity18 = list0804.get(17);

        assertThat(entity17.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity17.getShishutsuKbnName()).isEqualTo(KBN09_NAME);
        assertThat(entity18.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity18.getShishutsuKbnName()).isEqualTo(KBN09_NAME);

        /* 区分10 */
        OfferingPartyUsage0804Report2025Entity entity19 = list0804.get(18);
        OfferingPartyUsage0804Report2025Entity entity20 = list0804.get(19);

        assertThat(entity19.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity19.getShishutsuKbnName()).isEqualTo(KBN10_NAME);
        assertThat(entity20.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity20.getShishutsuKbnName()).isEqualTo(KBN10_NAME);

        /* 区分11 */
        OfferingPartyUsage0804Report2025Entity entity21 = list0804.get(20);
        OfferingPartyUsage0804Report2025Entity entity22 = list0804.get(21);

        assertThat(entity21.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity21.getShishutsuKbnName()).isEqualTo(KBN11_NAME);
        assertThat(entity22.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity22.getShishutsuKbnName()).isEqualTo(KBN11_NAME);

        /* 区分12 */
        OfferingPartyUsage0804Report2025Entity entity23 = list0804.get(22);
        OfferingPartyUsage0804Report2025Entity entity24 = list0804.get(23);

        assertThat(entity23.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity23.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
        assertThat(entity24.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity24.getShishutsuKbnName()).isEqualTo(KBN12_NAME);

        List<OfferingPartyUsage0805Report2025Entity> list0805 = offeringPartyUsage0805Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode);

        assertThat(list0805.size()).isEqualTo(1);// 取得と設定が同一

        OfferingPartyUsage0805Report2025Entity entity0805 = list0805.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity0805.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity0805.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity0805.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity0805.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity0805.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity0805.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity0805.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity0805.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity0805.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity0805.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity0805.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity0805.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity0805.getSumAmount()).isEqualTo(sheet0805.getSumAmount());
        assertThat(entity0805.getRowNo()).isEqualTo(row0805.getRowNo());
        assertThat(entity0805.getSibuName()).isEqualTo(row0805.getSibuName());
        assertThat(entity0805.getAmount()).isEqualTo(row0805.getAmount());
        assertThat(entity0805.getAccrualDate()).isEqualTo(row0805.getAccrualDate());
        assertThat(entity0805.getPurpose()).isEqualTo(row0805.getPurpose());
        assertThat(entity0805.getBikou()).isEqualTo(row0805.getBikou());
        assertThat(entity0805.getRowKbn()).isEqualTo(row0805.getRowKbn());

        List<OfferingPartyUsage0806Report2025Entity> list0806 = offeringPartyUsage0806Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode);

        assertThat(list0806.size()).isEqualTo(4);// 区分データ3件と集計データの計4件

        OfferingPartyUsage0806Report2025Entity entity080601 = list0806.get(0);
        OfferingPartyUsage0806Report2025Entity entity080602 = list0806.get(1);
        OfferingPartyUsage0806Report2025Entity entity080603 = list0806.get(2);
        OfferingPartyUsage0806Report2025Entity entity080604 = list0806.get(3);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity080601.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity080601.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity080601.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity080601.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity080601.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity080601.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity080601.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity080601.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity080601.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity080601.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity080601.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity080601.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* 初期値を繰り返し使うので最初に設定 */
        final String INIT_STRING = "";
        final Integer INIT_INTEGER = 0;
        final Long INIT_LONG = 0L;

        /* 集計データ */
        assertThat(entity080601.getFundsName()).isEqualTo(sheet0806.getFundsName());
        assertThat(entity080601.getTotalLastYear()).isEqualTo(sheet0806.getTotalLastYear());
        assertThat(entity080601.getFundsPurpose()).isEqualTo(sheet0806.getFundsPurpose());
        assertThat(entity080601.getTotal()).isEqualTo(sheet0806.getTotal());
        assertThat(entity080601.getTotalBikou()).isEqualTo(sheet0806.getTotalBikou());
        assertThat(entity080601.getTotalThisYear()).isEqualTo(sheet0806.getTotalThisYear());
        assertThat(entity080601.getTotalThisYearBikou()).isEqualTo(sheet0806.getTotalThisYearBikou());
        assertThat(entity080601.getTotalIncrease()).isEqualTo(sheet0806.getTotalIncrease());
        assertThat(entity080601.getTotalIncreaseBikou()).isEqualTo(sheet0806.getTotalIncreaseBikou());
        /* 入力なしの初期値 */
        assertThat(entity080601.getSubTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080601.getSubTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080601.getRowNo()).isEqualTo(INIT_INTEGER);
        assertThat(entity080601.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity080601.getAmount()).isEqualTo(INIT_LONG);
        assertThat(entity080601.getBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分1) */
        assertThat(entity080602.getSubTotal()).isEqualTo(kbn080601.getSubTotal());
        assertThat(entity080602.getSubTotalBikou()).isEqualTo(kbn080601.getSubTotalBikou());
        assertThat(entity080602.getRowNo()).isEqualTo(row0806.getRowNo());
        assertThat(entity080602.getAccrualDate()).isEqualTo(row0806.getAccrualDate());
        assertThat(entity080602.getAmount()).isEqualTo(row0806.getAmount());
        assertThat(entity080602.getBikou()).isEqualTo(row0806.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity080602.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分2) */
        assertThat(entity080603.getSubTotal()).isEqualTo(kbn080602.getSubTotal());
        assertThat(entity080603.getSubTotalBikou()).isEqualTo(kbn080602.getSubTotalBikou());
        assertThat(entity080603.getRowNo()).isEqualTo(row0806.getRowNo());
        assertThat(entity080603.getAccrualDate()).isEqualTo(row0806.getAccrualDate());
        assertThat(entity080603.getAmount()).isEqualTo(row0806.getAmount());
        assertThat(entity080603.getBikou()).isEqualTo(row0806.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity080603.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分3) */
        assertThat(entity080604.getSubTotal()).isEqualTo(kbn080603.getSubTotal());
        assertThat(entity080604.getSubTotalBikou()).isEqualTo(kbn080603.getSubTotalBikou());
        assertThat(entity080604.getRowNo()).isEqualTo(row0806.getRowNo());
        assertThat(entity080604.getAccrualDate()).isEqualTo(row0806.getAccrualDate());
        assertThat(entity080604.getAmount()).isEqualTo(row0806.getAmount());
        assertThat(entity080604.getBikou()).isEqualTo(row0806.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity080604.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        List<OfferingPartyUsage0901Report2025Entity> list0901 = offeringPartyUsage0901Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0901ReportId(documentCode);

        assertThat(list0901.size()).isEqualTo(2);// 2行追加

        OfferingPartyUsage0901Report2025Entity entity090101 = list0901.get(0);
        OfferingPartyUsage0901Report2025Entity entity090102 = list0901.get(1);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity090101.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity090101.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity090101.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity090101.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity090101.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity090101.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity090101.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity090101.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity090101.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity090101.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity090101.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity090101.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity090101.getRowNo()).isEqualTo(row090101.getRowNo());
        assertThat(entity090101.getItemName()).isEqualTo(row090101.getItemName());
        assertThat(entity090101.getDigest()).isEqualTo(row090101.getDigest());
        assertThat(entity090101.getAmount()).isEqualTo(row090101.getAmount());
        assertThat(entity090101.getAccrualDate()).isEqualTo(row090101.getAccrualDate());
        assertThat(entity090101.getExplainText()).isEqualTo(row090101.getExplainText());

        assertThat(entity090102.getRowNo()).isEqualTo(row090102.getRowNo());
        assertThat(entity090102.getItemName()).isEqualTo(row090102.getItemName());
        assertThat(entity090102.getDigest()).isEqualTo(row090102.getDigest());
        assertThat(entity090102.getAmount()).isEqualTo(row090102.getAmount());
        assertThat(entity090102.getAccrualDate()).isEqualTo(row090102.getAccrualDate());
        assertThat(entity090102.getExplainText()).isEqualTo(row090102.getExplainText());

        List<OfferingPartyUsage0902Report2025Entity> listSearch = offeringPartyUsage0902Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0902ReportId(documentCode);

        // 登録した分だけリストが取れています
        assertThat(listSearch.size()).isEqualTo(2);

        // 2件入力したので2件取れます
        OfferingPartyUsage0902Report2025Entity entity090201 = listSearch.get(0);
        OfferingPartyUsage0902Report2025Entity entity090202 = listSearch.get(1);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity090201.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity090201.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity090201.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity090201.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity090201.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity090201.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity090201.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity090201.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity090201.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity090201.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity090201.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity090201.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity090201.getItemName()).isEqualTo(sheet090201.getItemName());
        assertThat(entity090201.getDigest()).isEqualTo(sheet090201.getDigest());

        assertThat(entity090202.getItemName()).isEqualTo(sheet090202.getItemName());
        assertThat(entity090202.getDigest()).isEqualTo(sheet090202.getDigest());
    }

}
