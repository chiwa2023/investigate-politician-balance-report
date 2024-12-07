package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.constants.blancesheet_report.OutcomeYoushikiKbnConstants;
import mitei.mitei.common.constants.blancesheet_report.RealEstateKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookHeaderDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookUmuInputDataDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070600OtherIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070900AnonymousInPoliticalPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071000SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071415OrdinaryExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071800ClassificationOfAssetsByItemDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071900CurrentStatusOfRealEstateUseDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row080000DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070200SummaryTableIncomeExpenditureDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070600OtherIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070701DonatePersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070702DonateGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070703DonatePoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070801MediationPersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070802MediationGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070803MediationPoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070900AnonymousInPoliticalPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071000SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071101ConsiderationPartyPerspnalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071102ConsiderationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071103ConsiderationPartyPoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071201ConsiderationMediationPartyPersonalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071202ConsiderationMediationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071203ConsiderationMediationPartyPoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071300ListOfExpenditureItemsDto;
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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071700SummaryTableOfAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071801LandAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071802BuildingsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071803SurfaceRightsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071804MovablesAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071805SavingsAmmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071806TrustAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071807SecuritiesAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071808InvestmentAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071809LoanAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071810DepositAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071811FacilityUsageRightsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071812BorrowingsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071900RealEstateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet072000OathDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080000DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080200WithdrawalItemsByTransferDto;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.RegistPoliticalOrgBalancesheetReportResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0718Estate2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0719RealEstate2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetDifficalt0800Recipt2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetOutcome2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetWithdrawal0802Transfer2025Entity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ReadAllBookByXmlFileLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0718Estate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0719RealEstate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetDifficalt0800Recipt2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetOutcome2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetWithdrawal0802Transfer2025Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreateTestDataPoliticalOrganization08000Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreateTestDataPoliticalOrganization0802Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreateTestDataPoliticalOrganizationEstateAllLogic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreateTestDataPoliticalOrganizationIncomeAllLogic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreateTestDataPoliticalOrganizationSheet0701And0720Logic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreateTestDataPoliticalOrganizationSummaryLogic;
import mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet.CreatetestDataPoliticalOrganizationOutcomeAllLogic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrgnaizationBalancesheetReportService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrgnaizationBalancesheetReportServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrgnaizationBalancesheetReportService insertPoliticalOrgnaizationBalancesheetReportService;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2025Repository offeringBalancesheet0701And0720Surface2025Repository;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2025Repository offeringBalancesheet0702And0713And0717Summary2025Repository;

    /** 政治資金収支報告書収入提出分 */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** 政治資金収支報告書支出提出分Repository */
    @Autowired
    private OfferingBalancesheetOutcome2025Repository offeringBalancesheetOutcome2025Repository;

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2025Repository offeringBalancesheet0718Estate2025Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2025Repository offeringBalancesheet0719RealEstate2025Repository;

    /** 様式8 領収書を徴しがたかった支出項目一覧表Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2025Repository offeringBalancesheetDifficalt0800Recipt2025Repository;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetWithdrawal0802Transfer2025Repository offeringBalancesheetWithdrawal0802Transfer2025Repository;

    /** 政治資金収支報告書XML読み取りLogic */
    @Autowired
    private ReadAllBookByXmlFileLogic readAllBookByXmlFileLogic;

    /** 政治団体Repository */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void testPractice() { // NOPMD

        Integer houkokuNen = 2025;
        String oathDateString = "R4/12/1";

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(houkokuNen); // 実際には表紙からコピー
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(oathDateString)); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        /* 登録データ作成 */

        // 政治資金収支報告書
        AllBookDto allBookDto = new AllBookDto();

        /* 表紙と宣誓書 */
        CreateTestDataPoliticalOrganizationSheet0701And0720Logic createTestDataPoliticalOrganizationSheet0701And0720Logic = new CreateTestDataPoliticalOrganizationSheet0701And0720Logic();
        createTestDataPoliticalOrganizationSheet0701And0720Logic.practice(houkokuNen, allBookDto);

        // ヘッダ
        AllBookHeaderDto allBookHeaderDto = allBookDto.getAllBookHeaderDto();

        // 有無テキスト
        AllBookUmuInputDataDto allBookUmuInputDataDto = allBookDto.getAllBookUmuInputDataDto();

        // 様式7その1
        Sheet070100CoverAndOrganizationDetailsDto sheet0 = allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto()
                .getSheet070100CoverAndOrganizationDetailsDto();

        // 様式7その20
        Sheet072000OathDto sheet20 = allBookDto.getAllSheet0720OathDto().getSheet072000OathDto();

        /* 集計表 */
        CreateTestDataPoliticalOrganizationSummaryLogic createTestDataPoliticalOrganizationSummaryLogic = new CreateTestDataPoliticalOrganizationSummaryLogic();
        createTestDataPoliticalOrganizationSummaryLogic.practice(allBookDto);

        // 一種類のみ
        Sheet070200SummaryTableIncomeExpenditureDto sheet02 = allBookDto.getAllSheet0702SummaryTableIncomeDto()
                .getSheet070200SummaryTableIncomeExpenditureDto();
        // 一種類のみ
        Sheet071300ListOfExpenditureItemsDto sheet13 = allBookDto.getAllSheet0713ListOfExpenditureItemsDto()
                .getSheet071300ListOfExpenditureItemsDto();
        // 一種類のみ
        Sheet071700SummaryTableOfAssetsDto sheet17 = allBookDto.getAllSheet0717SummaryTableOfAssetsDto()
                .getSheet071700SummaryTableOfAssetsDto();

        /* 収入 */
        CreateTestDataPoliticalOrganizationIncomeAllLogic createTestDataPoliticalOrganizationIncomeAllLogic = new CreateTestDataPoliticalOrganizationIncomeAllLogic();
        createTestDataPoliticalOrganizationIncomeAllLogic.practice(allBookDto);

        /* その3 */
        Sheet070300JournalAndOtherDto sheet03 = allBookDto.getAllSheet0703JournalAndOtherDto()
                .getSheet070300JournalAndOtherDto();
        Row070300JournalAndOtherDto row03 = sheet03.getList().get(0);

        /* その4 */
        Sheet070400BorrowedMoneyDto sheet04 = allBookDto.getAllSheet0704BorrowedMoneyDto()
                .getSheet070400BorrowedMoneyDto();
        Row070400BorrowedMoneyDto row04 = sheet04.getList().get(0);

        /* その5 */
        Sheet070500IncomeRelatedToGrantsDto sheet05 = allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .getSheet070500IncomeRelatedToGrantsDto();
        Row070500IncomeRelatedToGrantsDto row05 = sheet05.getList().get(0);

        /* その6 */
        Sheet070600OtherIncomeDto sheet06 = allBookDto.getAllSheet0706OtherIncomeDto().getSheet070600OtherIncomeDto();
        Row070600OtherIncomeDto row06 = sheet06.getList().get(0);

        /* その7 */
        Sheet070701DonatePersonDto sheet071 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .getSheet070701DonatePersonDto();
        Row070711DonateDto row071 = sheet071.getList().get(0);
        // その2
        Sheet070702DonateGroupDto sheet072 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .getSheet070702DonateGroupDto();
        Row070711DonateDto row072 = sheet072.getList().get(0);
        // その3
        Sheet070703DonatePoliticOrgDto sheet073 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .getSheet070703DonatePoliticOrgDto();
        Row070711DonateDto row073 = sheet073.getList().get(0);

        /* その8 */
        Sheet070801MediationPersonDto sheet081 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .getSheet070801MediationPersonDto();
        Row070812MediationDto row081 = sheet081.getList().get(0);
        // その2
        Sheet070802MediationGroupDto sheet082 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .getSheet070802MediationGroupDto();
        Row070812MediationDto row082 = sheet082.getList().get(0);
        // その3
        Sheet070803MediationPoliticOrgDto sheet083 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .getSheet070803MediationPoliticOrgDto();
        Row070812MediationDto row083 = sheet083.getList().get(0);

        /* その9 */
        Sheet070900AnonymousInPoliticalPartyDto sheet09 = allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto()
                .getSheet070900AnonymousInPoliticalPartyDto();
        Row070900AnonymousInPoliticalPartyDto row09 = sheet09.getList().get(0);

        /* その10 */
        Sheet071000SpecificPartyDto sheet10 = allBookDto.getAllSheet0710SpecificPartyDto()
                .getSheet071000SpecificPartyDto();
        Row071000SpecificPartyDto row10 = sheet10.getList().get(0);

        /* その11 */
        Sheet071101ConsiderationPartyPerspnalDto sheet111 = allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071101Dto().getList().get(0);
        Row070711DonateDto row111 = sheet111.getList().get(0);
        // その2
        Sheet071102ConsiderationPartyGroupDto sheet112 = allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071102Dto().getList().get(0);
        Row070711DonateDto row112 = sheet112.getList().get(0);
        // その3
        Sheet071103ConsiderationPartyPoliticOrgDto sheet113 = allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071103Dto().getList().get(0);
        Row070711DonateDto row113 = sheet113.getList().get(0);

        /* その12 */
        // その1
        Sheet071201ConsiderationMediationPartyPersonalDto sheet121 = allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071201Dto().getList().get(0);
        Row070812MediationDto row121 = sheet121.getList().get(0);
        // その2
        Sheet071202ConsiderationMediationPartyGroupDto sheet122 = allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071202Dto().getList().get(0);
        Row070812MediationDto row122 = sheet122.getList().get(0);
        // その3
        Sheet071203ConsiderationMediationPartyPoliticOrgDto sheet123 = allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071203Dto().getList().get(0);
        Row070812MediationDto row123 = sheet123.getList().get(0);

        /* 支出 */
        CreatetestDataPoliticalOrganizationOutcomeAllLogic createtestDataPoliticalOrganizationOutcomeAllLogic = new CreatetestDataPoliticalOrganizationOutcomeAllLogic();
        createtestDataPoliticalOrganizationOutcomeAllLogic.practice(allBookDto);

        // その1
        Sheet071401UtilityCostsDto sheet141 = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .getSheet071401UtilityCostsDto();
        Row071415OrdinaryExpensesDto row141 = sheet141.getList().get(0);

        // その２
        Sheet071402EquipmentCostsDto sheet142 = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .getSheet071402EquipmentCostsDto();
        Row071415OrdinaryExpensesDto row142 = sheet142.getList().get(0);

        Sheet071403OfficeExpensesDto sheet143 = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .getSheet071403OfficeExpensesDto();
        Row071415OrdinaryExpensesDto row143 = sheet143.getList().get(0);

        /* その1 */
        Sheet071501OrganizationalActivityExpensesDto sheet151 = allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071501Dto().getList().get(0);
        Row071415OrdinaryExpensesDto row151 = sheet151.getList().get(0);

        /* その2 */
        Sheet071502ElectionRelatedExpensesDto sheet152 = allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071502Dto().getList().get(0);
        Row071415OrdinaryExpensesDto row152 = sheet152.getList().get(0);

        /* その3 */
        Sheet071503MagazinePublicationExpensesDto sheet153 = allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071503Dto().getList().get(0);
        Row071415OrdinaryExpensesDto row153 = sheet153.getList().get(0);

        /* その4 */
        Sheet071504AdvertisingExpensesDto sheet154 = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto()
                .getList().get(0);
        Row071415OrdinaryExpensesDto row154 = sheet154.getList().get(0);

        /* その5 */
        Sheet071505PartyHostingFeeDto sheet155 = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto()
                .getList().get(0);
        Row071415OrdinaryExpensesDto row155 = sheet155.getList().get(0);

        /* その6 */
        Sheet071506OtherBusinessExpensesDto sheet156 = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto()
                .getList().get(0);
        Row071415OrdinaryExpensesDto row156 = sheet156.getList().get(0);
        /* その7 */
        Sheet071507ResearchExpensesDto sheet157 = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto()
                .getList().get(0);
        Row071415OrdinaryExpensesDto row157 = sheet157.getList().get(0);

        /* その8 */
        Sheet071508DonationsGrantsDto sheet158 = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto()
                .getList().get(0);
        Row071415OrdinaryExpensesDto row158 = sheet158.getList().get(0);

        /* その9 */
        Sheet071509OtherExpensesDto sheet159 = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto()
                .getList().get(0);
        Row071415OrdinaryExpensesDto row159 = sheet159.getList().get(0);

        /* 資産の詳細様式7その18 */
        CreateTestDataPoliticalOrganizationEstateAllLogic createTestDataPoliticalOrganizationEstateAllLogic = new CreateTestDataPoliticalOrganizationEstateAllLogic();
        createTestDataPoliticalOrganizationEstateAllLogic.practice(allBookDto);

        /* その1 */
        Sheet071801LandAmongAssetsDto sheet181 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071801Dto()
                .getSheet071801LandAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row181 = sheet181.getList().get(0);

        /* その2 */
        Sheet071802BuildingsAmongAssetsDto sheet182 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071802Dto()
                .getSheet071802BuildingsAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row182 = sheet182.getList().get(0);

        /* その3 */
        Sheet071803SurfaceRightsAmongAssetsDto sheet183 = allBookDto.getAllSheet0718AssetsDto()
                .getAllSheetKbn071803Dto().getSheet071803SurfaceRightsAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row183 = sheet183.getList().get(0);

        /* その4 */
        Sheet071804MovablesAmongAssetsDto sheet184 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071804Dto()
                .getSheet071804MovablesAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row184 = sheet184.getList().get(0);

        /* その5 */
        Sheet071805SavingsAmmongAssetsDto sheet185 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071805Dto()
                .getSheet071805SavingsAmmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row185 = sheet185.getList().get(0);

        /* その6 */
        Sheet071806TrustAmongAssetsDto sheet186 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071806Dto()
                .getSheet071806TrustAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row186 = sheet186.getList().get(0);

        /* その7 */
        Sheet071807SecuritiesAmongAssetsDto sheet187 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071807Dto()
                .getSheet071807SecuritiesAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row187 = sheet187.getList().get(0);

        /* その8 */
        Sheet071808InvestmentAmongAssetsDto sheet188 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071808Dto()
                .getSheet071808InvestmentAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row188 = sheet188.getList().get(0);

        /* その9 */
        Sheet071809LoanAmongAssetsDto sheet189 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071809Dto()
                .getSheet071809LoanAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row189 = sheet189.getList().get(0);

        /* その10 */
        Sheet071810DepositAmongAssetsDto sheet1810 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071810Dto()
                .getSheet071810DepositAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row1810 = sheet1810.getList().get(0);

        /* その11 */
        Sheet071811FacilityUsageRightsAmongAssetsDto sheet1811 = allBookDto.getAllSheet0718AssetsDto()
                .getAllSheetKbn071811Dto().getSheet071811FacilityUsageRightsAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row1811 = sheet1811.getList().get(0);

        /* その12 */
        Sheet071812BorrowingsAmongAssetsDto sheet1812 = allBookDto.getAllSheet0718AssetsDto().getAllSheetKbn071812Dto()
                .getSheet071812BorrowingsAmongAssetsDto();
        Row071800ClassificationOfAssetsByItemDto row1812 = sheet1812.getList().get(0);

        /* 不動産の詳細様式7その19 */

        // その1
        Sheet071900RealEstateDto sheet1813 = allBookDto.getAllSheet0719RealEstateDto().getListSheet0719().get(0);
        Row071900CurrentStatusOfRealEstateUseDto row1813 = sheet1813.getList().get(0);

        // その3
        Sheet071900RealEstateDto sheet1814 = allBookDto.getAllSheet0719RealEstateDto().getListSheet0719().get(1);
        sheet1814.setKbnRealEstitate(2);
        Row071900CurrentStatusOfRealEstateUseDto row1814 = sheet1814.getList().get(0);

        // その3
        Sheet071900RealEstateDto sheet1815 = allBookDto.getAllSheet0719RealEstateDto().getListSheet0719().get(2);
        sheet1815.setKbnRealEstitate(3);
        Row071900CurrentStatusOfRealEstateUseDto row1815 = sheet1815.getList().get(0);

        /* 様式8 */
        CreateTestDataPoliticalOrganization08000Logic createTestDataPoliticalOrganization08000Logic = new CreateTestDataPoliticalOrganization08000Logic();
        createTestDataPoliticalOrganization08000Logic.practice(allBookDto);

        Sheet080000DifficultCollectReceiptDto sheet08001 = allBookDto.getAllSheet0800DifficultCollectReceiptDto()
                .getSheet080000DifficultCollectReceiptDto();
        Row080000DifficultCollectReceiptDto row08001 = sheet08001.getList().get(0);

        /* 様式8その2 */
        CreateTestDataPoliticalOrganization0802Logic createTestDataPoliticalOrganization0802Logic = new CreateTestDataPoliticalOrganization0802Logic();
        createTestDataPoliticalOrganization0802Logic.practice(allBookDto);

        Sheet080200WithdrawalItemsByTransferDto sheet08021 = allBookDto.getAllSheet0802WithdrawalItemsByTransferDto()
                .getListSheet0802().get(0);

        /*
         * 
         * 登録作業
         * 
         */
        RegistPoliticalOrgBalancesheetReportResultDto resultDto = insertPoliticalOrgnaizationBalancesheetReportService
                .practice(documentPropertyDto, allBookDto, CreateTestPrivilegeDtoUtil.pracitce());
        Long documentCode = resultDto.getDocumentCode();

        /* 検証 */

        List<OfferingBalancesheet0701And0720Surface2025Entity> list070120 = offeringBalancesheet0701And0720Surface2025Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        documentCode);

        // 1件だけ登録している
        assertThat(list070120.size()).isEqualTo(1);

        OfferingBalancesheet0701And0720Surface2025Entity entity070120 = list070120.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity070120.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity070120.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity070120.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity070120.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity070120.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity070120.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity070120.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity070120.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity070120.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity070120.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity070120.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity070120.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity070120.getVersion()).isEqualTo(allBookHeaderDto.getVersion());
        assertThat(entity070120.getAppName()).isEqualTo(allBookHeaderDto.getAppName());
        assertThat(entity070120.getFileFormatNo()).isEqualTo(allBookHeaderDto.getFileFormatNo());
        assertThat(entity070120.getFlgKokuji()).isEqualTo(allBookHeaderDto.getFlgKokuji());
        assertThat(entity070120.getChouboAppVer()).isEqualTo(allBookHeaderDto.getChouboAppVer());

        assertThat(entity070120.getInputBitText()).isEqualTo(allBookUmuInputDataDto.getInputBitText());

        assertThat(entity070120.getHoukokuNen()).isEqualTo(sheet0.getHoukokuNen());
        assertThat(entity070120.getDateKaisai()).isEqualTo(sheet0.getDateKaisai());
        assertThat(entity070120.getDantaiName01()).isEqualTo(sheet0.getDantaiName01());
        assertThat(entity070120.getDantaiNameKana()).isEqualTo(sheet0.getDantaiNameKana());
        assertThat(entity070120.getJimushoJusho()).isEqualTo(sheet0.getJimushoJusho());
        assertThat(entity070120.getJimushoJushoTatemono()).isEqualTo(sheet0.getJimushoJushoTatemono());
        assertThat(entity070120.getDaihyoushaNameLast()).isEqualTo(sheet0.getDaihyoushaNameLast());
        assertThat(entity070120.getDaihyoushaNameFirst()).isEqualTo(sheet0.getDaihyoushaNameFirst());
        assertThat(entity070120.getKaikeiSekinnshaNameLast()).isEqualTo(sheet0.getKaikeiSekinnshaNameLast());
        assertThat(entity070120.getKaikeiSekinnshaNameFirst()).isEqualTo(sheet0.getKaikeiSekinnshaNameFirst());
        assertThat(entity070120.getJimuTantousha1NameLast()).isEqualTo(sheet0.getJimuTantousha1NameLast());
        assertThat(entity070120.getJimuTantousha1NameFirst()).isEqualTo(sheet0.getJimuTantousha1NameFirst());
        assertThat(entity070120.getJimuTantousha1Tel()).isEqualTo(sheet0.getJimuTantousha1Tel());
        assertThat(entity070120.getJimuTantousha2NameLast()).isEqualTo(sheet0.getJimuTantousha2NameLast());
        assertThat(entity070120.getJimuTantousha2NameFirst()).isEqualTo(sheet0.getJimuTantousha2NameFirst());
        assertThat(entity070120.getJimuTantousha2Tel()).isEqualTo(sheet0.getJimuTantousha2Tel());
        assertThat(entity070120.getJimuTantousha3NameLast()).isEqualTo(sheet0.getJimuTantousha3NameLast());
        assertThat(entity070120.getJimuTantousha3NameFirst()).isEqualTo(sheet0.getJimuTantousha3NameFirst());
        assertThat(entity070120.getJimuTantousha3Tel()).isEqualTo(sheet0.getJimuTantousha3Tel());
        assertThat(entity070120.getDantaiKbn()).isEqualTo(sheet0.getDantaiKbn());
        assertThat(entity070120.getKatsudouKuikiKbn()).isEqualTo(sheet0.getKatsudouKuikiKbn());
        assertThat(entity070120.getUmuShikinKanrenDantai()).isEqualTo(sheet0.getUmuShikinKanrenDantai());
        assertThat(entity070120.getKoushokuName()).isEqualTo(sheet0.getKoushokuName());
        assertThat(entity070120.getKoushokuGenKouho()).isEqualTo(sheet0.getKoushokuGenKouho());
        assertThat(entity070120.getShikinDaihyouName1()).isEqualTo(sheet0.getShikinDaihyouName1());
        assertThat(entity070120.getShikinDaihyouName2()).isEqualTo(sheet0.getShikinDaihyouName2());
        assertThat(entity070120.getKanriDantaiPeriodStart()).isEqualTo(sheet0.getKanriDantaiPeriodStart());
        assertThat(entity070120.getKanriDantaiPeriodEnd()).isEqualTo(sheet0.getKanriDantaiPeriodEnd());
        assertThat(entity070120.getKanriDantaiPeriodRest()).isEqualTo(sheet0.getKanriDantaiPeriodRest());
        assertThat(entity070120.getKokkaiGiinDantaiKbn()).isEqualTo(sheet0.getKokkaiGiinDantaiKbn());
        assertThat(entity070120.getKokkaiGiin1NameLast()).isEqualTo(sheet0.getKokkaiGiin1NameLast());
        assertThat(entity070120.getKokkaiGiin1NameFirst()).isEqualTo(sheet0.getKokkaiGiin1NameFirst());
        assertThat(entity070120.getKokkaiGiin1ShuuSan()).isEqualTo(sheet0.getKokkaiGiin1ShuuSan());
        assertThat(entity070120.getKokkaiGiin1GenKouho()).isEqualTo(sheet0.getKokkaiGiin1GenKouho());
        assertThat(entity070120.getGiinDantantaiTokureiPeriodStart())
                .isEqualTo(sheet0.getGiinDantantaiTokureiPeriodStart());
        assertThat(entity070120.getGiinDantantaiTokureiPeriodEnd())
                .isEqualTo(sheet0.getGiinDantantaiTokureiPeriodEnd());
        assertThat(entity070120.getGiinDantantaiTokureiPeriodRest())
                .isEqualTo(sheet0.getGiinDantantaiTokureiPeriodRest());
        assertThat(entity070120.getKokkaiGiin2NameLast()).isEqualTo(sheet0.getKokkaiGiin2NameLast());
        assertThat(entity070120.getKokkaiGiin2NameFirst()).isEqualTo(sheet0.getKokkaiGiin2NameFirst());
        assertThat(entity070120.getKokkaiGiin2ShuuSan()).isEqualTo(sheet0.getKokkaiGiin2ShuuSan());
        assertThat(entity070120.getKokkaiGiin2GenKouho()).isEqualTo(sheet0.getKokkaiGiin2GenKouho());
        assertThat(entity070120.getKokkaiGiin3NameLast()).isEqualTo(sheet0.getKokkaiGiin3NameLast());
        assertThat(entity070120.getKokkaiGiin3NameFirst()).isEqualTo(sheet0.getKokkaiGiin3NameFirst());
        assertThat(entity070120.getKokkaiGiin3ShuuSan()).isEqualTo(sheet0.getKokkaiGiin3ShuuSan());
        assertThat(entity070120.getKokkaiGiin3GenKouho()).isEqualTo(sheet0.getKokkaiGiin3GenKouho());

        assertThat(entity070120.getFlgReciptCopy()).isEqualTo(sheet20.getFlgReciptCopy());
        assertThat(entity070120.getFlgKansaIkensho()).isEqualTo(sheet20.getFlgKansaIkensho());
        assertThat(entity070120.getFlgSeijishikinHohkokusho()).isEqualTo(sheet20.getFlgSeijishikinHohkokusho());
        assertThat(entity070120.getDateOath()).isEqualTo(sheet20.getDateOath());
        assertThat(entity070120.getDantaiName20()).isEqualTo(sheet20.getDantaiName20());
        assertThat(entity070120.getKaikeiSekininshaNameLast()).isEqualTo(sheet20.getKaikeiSekininshaNameLast());
        assertThat(entity070120.getKaikeiSekininshaNameFirst()).isEqualTo(sheet20.getKaikeiSekininshaNameFirst());
        assertThat(entity070120.getDaihyoushaKaisanNameLast()).isEqualTo(sheet20.getDaihyoushaKaisanNameLast());
        assertThat(entity070120.getDaihyoushaKaisanNameFirst()).isEqualTo(sheet20.getDaihyoushaKaisanNameFirst());

        List<OfferingBalancesheet0702And0713And0717Summary2025Entity> listSummary = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(documentCode);

        assertThat(listSummary.size()).isEqualTo(1); // 1件だけ登録

        OfferingBalancesheet0702And0713And0717Summary2025Entity entitySummary = listSummary.get(0);

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

        List<OfferingBalancesheetIncome2025Entity> listIncome = offeringBalancesheetIncome2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetIncomeId(documentCode);

        assertThat(listIncome.size()).isEqualTo(18); // 3,4,5,6,7-1,7-2,7-3,8-1,8-2,8-3,9,10,11-1,11-2,11-3,12-1,12-2,12-3の計18

        OfferingBalancesheetIncome2025Entity entity0300 = listIncome.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity0300.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity0300.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity0300.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity0300.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity0300.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity0300.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity0300.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity0300.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity0300.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity0300.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity0300.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity0300.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* その3 */
        // 様式項目
        assertThat(entity0300.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_03);
        assertThat(entity0300.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        assertThat(entity0300.getPageTotal()).isEqualTo(sheet03.getPageTotal());
        assertThat(entity0300.getIchirenNo()).isEqualTo(row03.getIchirenNo());
        assertThat(entity0300.getItemName()).isEqualTo(row03.getJigyoNoShurui());
        assertThat(entity0300.getKingaku()).isEqualTo(row03.getKingaku());
        assertThat(entity0300.getBikou()).isEqualTo(row03.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0300.getSearchWords()).isEqualTo("機関誌発行");

        /* その4 */
        OfferingBalancesheetIncome2025Entity entity0400 = listIncome.get(1);

        // 様式項目
        assertThat(entity0400.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04);
        assertThat(entity0400.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0400.getPageTotal()).isEqualTo(sheet04.getPageTotal());
        assertThat(entity0400.getIchirenNo()).isEqualTo(row04.getIchirenNo());
        assertThat(entity0400.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04_TEXT);
        assertThat(entity0400.getKingaku()).isEqualTo(row04.getKingaku());
        assertThat(entity0400.getPartnerName()).isEqualTo(row04.getKarisaki());
        assertThat(entity0400.getBikou()).isEqualTo(row04.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0400.getSearchWords()).isEqualTo("借入金親戚のおじさん");

        /* その5 */
        OfferingBalancesheetIncome2025Entity entity0500 = listIncome.get(2);

        // 様式項目
        assertThat(entity0500.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_05);
        assertThat(entity0500.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0500.getIchirenNo()).isEqualTo(row05.getIchirenNo());
        assertThat(entity0500.getKingaku()).isEqualTo(row05.getKingaku());
        assertThat(entity0500.getAccrualDate()).isEqualTo(row05.getAccrualDate());
        assertThat(entity0500.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity0500.getAccrualDate()));
        assertThat(entity0500.getBikou()).isEqualTo(row05.getBikou());
        assertThat(entity0500.getPartnerName()).isEqualTo(row05.getHonbuShibuName());
        assertThat(entity0500.getPartnerJuusho()).isEqualTo(row05.getJimushoJuusho());

        // TODO 関連者

        // 自由検索
        assertThat(entity0500.getSearchWords()).isEqualTo("本部/支部からの交付金本部支部名称事務所住所");

        /* その6 */
        OfferingBalancesheetIncome2025Entity entity0600 = listIncome.get(3);

        // 様式項目
        assertThat(entity0600.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_06);
        assertThat(entity0600.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート記載内容
        assertThat(entity0600.getPageTotal()).isEqualTo(sheet06.getPageTotal());
        assertThat(entity0600.getMimanTotal()).isEqualTo(sheet06.getMimanTotal());
        assertThat(entity0600.getIchirenNo()).isEqualTo(row06.getIchirenNo());
        assertThat(entity0600.getItemName()).isEqualTo(row06.getTekiyou());
        assertThat(entity0600.getKingaku()).isEqualTo(row06.getKingaku());
        assertThat(entity0600.getBikou()).isEqualTo(row06.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0600.getSearchWords()).isEqualTo("摘要");

        /* その7-1 */
        OfferingBalancesheetIncome2025Entity entity0701 = listIncome.get(4);

        // 様式項目
        assertThat(entity0701.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0701.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity0701.getPageTotal()).isEqualTo(sheet071.getPageTotal());
        assertThat(entity0701.getSonotaTotal()).isEqualTo(sheet071.getSonotaTotal());

        assertThat(entity0701.getIchirenNo()).isEqualTo(row071.getIchirenNo());
        assertThat(entity0701.getPartnerName()).isEqualTo(row071.getKifusha());
        assertThat(entity0701.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0701.getKingaku()).isEqualTo(row071.getKingaku());
        assertThat(entity0701.getAccrualDate()).isEqualTo(row071.getAccrualDate());
        assertThat(entity0701.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row071.getAccrualDate()));
        assertThat(entity0701.getPartnerJuusho()).isEqualTo(row071.getJusho());
        assertThat(entity0701.getShokugyou()).isEqualTo(row071.getShokugyou());
        assertThat(entity0701.getBikou()).isEqualTo(row071.getBikou());
        assertThat(entity0701.getTohshibangou()).isEqualTo(row071.getTohshibangou());
        assertThat(entity0701.getFlgZeigakuKohjo()).isEqualTo(row071.getFlgZeigakuKohjo());
        assertThat(entity0701.getGyoukubun()).isEqualTo(row071.getGyoukubun());

        // 自由検索
        assertThat(entity0701.getSearchWords()).isEqualTo("寄付寄付者名称個人住所個人71");

        /* その7-2 */
        OfferingBalancesheetIncome2025Entity entity0702 = listIncome.get(5);

        // 様式項目
        assertThat(entity0702.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0702.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity0702.getPageTotal()).isEqualTo(sheet072.getPageTotal());
        assertThat(entity0702.getSonotaTotal()).isEqualTo(sheet072.getSonotaTotal());

        assertThat(entity0702.getIchirenNo()).isEqualTo(row072.getIchirenNo());
        assertThat(entity0702.getPartnerName()).isEqualTo(row072.getKifusha());
        assertThat(entity0702.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0702.getKingaku()).isEqualTo(row072.getKingaku());
        assertThat(entity0702.getAccrualDate()).isEqualTo(row072.getAccrualDate());
        assertThat(entity0702.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row072.getAccrualDate()));
        assertThat(entity0702.getPartnerJuusho()).isEqualTo(row072.getJusho());
        assertThat(entity0702.getShokugyou()).isEqualTo(row072.getShokugyou());
        assertThat(entity0702.getBikou()).isEqualTo(row072.getBikou());
        assertThat(entity0702.getTohshibangou()).isEqualTo(row072.getTohshibangou());
        assertThat(entity0702.getFlgZeigakuKohjo()).isEqualTo(row072.getFlgZeigakuKohjo());
        assertThat(entity0702.getGyoukubun()).isEqualTo(row072.getGyoukubun());

        // 自由検索
        assertThat(entity0702.getSearchWords()).isEqualTo("寄付寄付者名称法人住所法人72");

        /* その7-3 */
        OfferingBalancesheetIncome2025Entity entity0703 = listIncome.get(6);

        // 様式項目
        assertThat(entity0703.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0703.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity0703.getPageTotal()).isEqualTo(sheet073.getPageTotal());
        assertThat(entity0703.getSonotaTotal()).isEqualTo(sheet073.getSonotaTotal());

        assertThat(entity0703.getIchirenNo()).isEqualTo(row073.getIchirenNo());
        assertThat(entity0703.getPartnerName()).isEqualTo(row073.getKifusha());
        assertThat(entity0703.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0703.getKingaku()).isEqualTo(row073.getKingaku());
        assertThat(entity0703.getAccrualDate()).isEqualTo(row073.getAccrualDate());
        assertThat(entity0703.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row073.getAccrualDate()));
        assertThat(entity0703.getPartnerJuusho()).isEqualTo(row073.getJusho());
        assertThat(entity0703.getShokugyou()).isEqualTo(row073.getShokugyou());
        assertThat(entity0703.getBikou()).isEqualTo(row073.getBikou());
        assertThat(entity0703.getTohshibangou()).isEqualTo(row073.getTohshibangou());
        assertThat(entity0703.getFlgZeigakuKohjo()).isEqualTo(row073.getFlgZeigakuKohjo());
        assertThat(entity0703.getGyoukubun()).isEqualTo(row073.getGyoukubun());

        // 自由検索
        assertThat(entity0703.getSearchWords()).isEqualTo("寄付寄付者名称政治団体住所政治団体73");

        /* その8-1 */
        OfferingBalancesheetIncome2025Entity entity0801 = listIncome.get(7);

        // 様式項目
        assertThat(entity0801.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0801.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity0801.getPageTotal()).isEqualTo(sheet081.getPageTotal());
        assertThat(entity0801.getSonotaTotal()).isEqualTo(sheet081.getSonotaTotal());
        assertThat(entity0801.getIchirenNo()).isEqualTo(row081.getIchirenNo());
        assertThat(entity0801.getPartnerName()).isEqualTo(row081.getName());
        assertThat(entity0801.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0801.getKingaku()).isEqualTo(row081.getKingaku());
        assertThat(entity0801.getAccrualDate()).isEqualTo(row081.getAccrualDate());
        assertThat(entity0801.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row081.getAccrualDate()));
        assertThat(entity0801.getPeriodMediate()).isEqualTo(row081.getPeriodMediate());
        assertThat(entity0801.getPartnerJuusho()).isEqualTo(row081.getJuusho());
        assertThat(entity0801.getShokugyou()).isEqualTo(row081.getShokugyou());
        assertThat(entity0801.getBikou()).isEqualTo(row081.getBikou());
        assertThat(entity0801.getTohshibangou()).isEqualTo(row081.getTohshibangou());
        assertThat(entity0801.getGyoukubun()).isEqualTo(row081.getGyoukubun());

        // 自由検索
        assertThat(entity0801.getSearchWords()).isEqualTo("寄付のうちあっせん名称個人住所個人");

        /* その8-2 */
        OfferingBalancesheetIncome2025Entity entity0802 = listIncome.get(8);

        // 様式項目
        assertThat(entity0802.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0802.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity0802.getPageTotal()).isEqualTo(sheet082.getPageTotal());
        assertThat(entity0802.getSonotaTotal()).isEqualTo(sheet082.getSonotaTotal());
        assertThat(entity0802.getIchirenNo()).isEqualTo(row082.getIchirenNo());
        assertThat(entity0802.getPartnerName()).isEqualTo(row082.getName());
        assertThat(entity0802.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0802.getKingaku()).isEqualTo(row082.getKingaku());
        assertThat(entity0802.getAccrualDate()).isEqualTo(row082.getAccrualDate());
        assertThat(entity0802.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row082.getAccrualDate()));
        assertThat(entity0802.getPeriodMediate()).isEqualTo(row082.getPeriodMediate());
        assertThat(entity0802.getPartnerJuusho()).isEqualTo(row082.getJuusho());
        assertThat(entity0802.getShokugyou()).isEqualTo(row082.getShokugyou());
        assertThat(entity0802.getBikou()).isEqualTo(row082.getBikou());
        assertThat(entity0802.getTohshibangou()).isEqualTo(row082.getTohshibangou());
        assertThat(entity0802.getGyoukubun()).isEqualTo(row082.getGyoukubun());

        // 自由検索
        assertThat(entity0802.getSearchWords()).isEqualTo("寄付のうちあっせん名称法人住所法人");

        /* その8-3 */
        OfferingBalancesheetIncome2025Entity entity0803 = listIncome.get(9);

        // 様式項目
        assertThat(entity0803.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0803.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity0803.getPageTotal()).isEqualTo(sheet083.getPageTotal());
        assertThat(entity0803.getSonotaTotal()).isEqualTo(sheet083.getSonotaTotal());
        assertThat(entity0803.getIchirenNo()).isEqualTo(row083.getIchirenNo());
        assertThat(entity0803.getPartnerName()).isEqualTo(row083.getName());
        assertThat(entity0803.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0803.getKingaku()).isEqualTo(row083.getKingaku());
        assertThat(entity0803.getAccrualDate()).isEqualTo(row083.getAccrualDate());
        assertThat(entity0803.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row083.getAccrualDate()));
        assertThat(entity0803.getPeriodMediate()).isEqualTo(row083.getPeriodMediate());
        assertThat(entity0803.getPartnerJuusho()).isEqualTo(row083.getJuusho());
        assertThat(entity0803.getShokugyou()).isEqualTo(row083.getShokugyou());
        assertThat(entity0803.getBikou()).isEqualTo(row083.getBikou());
        assertThat(entity0803.getTohshibangou()).isEqualTo(row083.getTohshibangou());
        assertThat(entity0803.getGyoukubun()).isEqualTo(row083.getGyoukubun());

        // 自由検索
        assertThat(entity0803.getSearchWords()).isEqualTo("寄付のうちあっせん名称政治団体住所政治団体");

        /* その9 */
        OfferingBalancesheetIncome2025Entity entity0900 = listIncome.get(10);

        // 様式項目
        assertThat(entity0900.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09);
        assertThat(entity0900.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0900.getPageTotal()).isEqualTo(sheet09.getPageTotal());
        assertThat(entity0900.getIchirenNo()).isEqualTo(row09.getIchirenNo());
        assertThat(entity0900.getKaisaiBasho()).isEqualTo(row09.getBasho());
        assertThat(entity0900.getItemName())
                .isEqualTo(row09.getBasho() + "での" + IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09_TEXT);
        assertThat(entity0900.getKingaku()).isEqualTo(row09.getKingaku());
        assertThat(entity0900.getIchirenNo()).isEqualTo(row09.getIchirenNo());
        assertThat(entity0900.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity0900.getAccrualDate()));
        assertThat(entity0900.getBikou()).isEqualTo(row09.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0900.getSearchWords()).isEqualTo("開催場所での政党匿名寄付");

        /* その10 */
        OfferingBalancesheetIncome2025Entity entity1000 = listIncome.get(11);

        // 様式項目
        assertThat(entity1000.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_10);
        assertThat(entity1000.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity1000.getPageTotal()).isEqualTo(sheet10.getPageTotal());
        assertThat(entity1000.getIchirenNo()).isEqualTo(row10.getIchirenNo());
        assertThat(entity1000.getItemName()).isEqualTo(row10.getPartyName() + "(" + row10.getKaisaiBasho() + ")");
        assertThat(entity1000.getKingaku()).isEqualTo(row10.getKingaku());
        assertThat(entity1000.getShiharaisu()).isEqualTo(row10.getShiharaisu());
        assertThat(entity1000.getAccrualDate()).isEqualTo(row10.getAccrualDate());
        assertThat(entity1000.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row10.getAccrualDate()));
        assertThat(entity1000.getKaisaiBasho()).isEqualTo(row10.getKaisaiBasho());
        assertThat(entity1000.getBikou()).isEqualTo(row10.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity1000.getSearchWords()).isEqualTo("パーティ名称10(開催場所)");

        /* その11-1 */
        OfferingBalancesheetIncome2025Entity entity1101 = listIncome.get(12);

        // 様式項目
        assertThat(entity1101.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1101.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        assertThat(entity1101.getPageTotal()).isEqualTo(sheet111.getPageTotal());
        assertThat(entity1101.getPartyName()).isEqualTo(sheet111.getPartyName());
        assertThat(entity1101.getSortNo()).isEqualTo(sheet111.getSortNo());

        assertThat(entity1101.getIchirenNo()).isEqualTo(row111.getIchirenNo());
        assertThat(entity1101.getPartnerName()).isEqualTo(row111.getKifusha());
        assertThat(entity1101.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet111.getPartyName() + ")");
        assertThat(entity1101.getKingaku()).isEqualTo(row111.getKingaku());
        assertThat(entity1101.getAccrualDate()).isEqualTo(row111.getAccrualDate());
        assertThat(entity1101.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1101.getAccrualDate()));
        assertThat(entity1101.getPartnerJuusho()).isEqualTo(row111.getJusho());
        assertThat(entity1101.getShokugyou()).isEqualTo(row111.getShokugyou());
        assertThat(entity1101.getBikou()).isEqualTo(row111.getBikou());
        assertThat(entity1101.getTohshibangou()).isEqualTo(row111.getTohshibangou());
        assertThat(entity1101.getFlgZeigakuKohjo()).isEqualTo(row111.getFlgZeigakuKohjo());
        assertThat(entity1101.getGyoukubun()).isEqualTo(row111.getGyoukubun());

        // 自由検索
        assertThat(entity1101.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称111)寄付者名称個人住所個人");

        /* その11-2 */
        OfferingBalancesheetIncome2025Entity entity1102 = listIncome.get(13);

        // 様式項目
        assertThat(entity1102.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1102.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        assertThat(entity1102.getPageTotal()).isEqualTo(sheet112.getPageTotal());
        assertThat(entity1102.getPartyName()).isEqualTo(sheet112.getPartyName());
        assertThat(entity1102.getSortNo()).isEqualTo(sheet112.getSortNo());

        assertThat(entity1102.getIchirenNo()).isEqualTo(row112.getIchirenNo());
        assertThat(entity1102.getPartnerName()).isEqualTo(row112.getKifusha());
        assertThat(entity1102.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet112.getPartyName() + ")");
        assertThat(entity1102.getKingaku()).isEqualTo(row112.getKingaku());
        assertThat(entity1102.getAccrualDate()).isEqualTo(row112.getAccrualDate());
        assertThat(entity1102.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1102.getAccrualDate()));
        assertThat(entity1102.getPartnerJuusho()).isEqualTo(row112.getJusho());
        assertThat(entity1102.getShokugyou()).isEqualTo(row112.getShokugyou());
        assertThat(entity1102.getBikou()).isEqualTo(row112.getBikou());
        assertThat(entity1102.getTohshibangou()).isEqualTo(row112.getTohshibangou());
        assertThat(entity1102.getFlgZeigakuKohjo()).isEqualTo(row112.getFlgZeigakuKohjo());
        assertThat(entity1102.getGyoukubun()).isEqualTo(row112.getGyoukubun());

        // 自由検索
        assertThat(entity1102.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称112)寄付者名称法人住所法人");

        /* その11-3 */
        OfferingBalancesheetIncome2025Entity entity1103 = listIncome.get(14);

        // 様式項目
        assertThat(entity1103.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1103.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        assertThat(entity1103.getPageTotal()).isEqualTo(sheet113.getPageTotal());
        assertThat(entity1103.getPartyName()).isEqualTo(sheet113.getPartyName());
        assertThat(entity1103.getSortNo()).isEqualTo(sheet113.getSortNo());

        assertThat(entity1103.getIchirenNo()).isEqualTo(row113.getIchirenNo());
        assertThat(entity1103.getPartnerName()).isEqualTo(row113.getKifusha());
        assertThat(entity1103.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet113.getPartyName() + ")");
        assertThat(entity1103.getKingaku()).isEqualTo(row113.getKingaku());
        assertThat(entity1103.getAccrualDate()).isEqualTo(row113.getAccrualDate());
        assertThat(entity1103.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1103.getAccrualDate()));
        assertThat(entity1103.getPartnerJuusho()).isEqualTo(row113.getJusho());
        assertThat(entity1103.getShokugyou()).isEqualTo(row113.getShokugyou());
        assertThat(entity1103.getBikou()).isEqualTo(row113.getBikou());
        assertThat(entity1103.getTohshibangou()).isEqualTo(row113.getTohshibangou());
        assertThat(entity1103.getFlgZeigakuKohjo()).isEqualTo(row113.getFlgZeigakuKohjo());
        assertThat(entity1103.getGyoukubun()).isEqualTo(row113.getGyoukubun());

        // 自由検索
        assertThat(entity1103.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称113)寄付者名称政治団体住所政治団体");

        /* その12-1 */
        OfferingBalancesheetIncome2025Entity entity1201 = listIncome.get(15);

        // 様式項目
        assertThat(entity1201.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1201.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート記載内容
        assertThat(entity1201.getPageTotal()).isEqualTo(sheet121.getPageTotal());
        assertThat(entity1201.getPartyName()).isEqualTo(sheet121.getPartyName());
        assertThat(entity1201.getSortNo()).isEqualTo(sheet121.getSortNo());

        assertThat(entity1201.getIchirenNo()).isEqualTo(row121.getIchirenNo());
        assertThat(entity1201.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet121.getPartyName() + ")");
        assertThat(entity1201.getPartnerName()).isEqualTo(row121.getName());
        assertThat(entity1201.getKingaku()).isEqualTo(row121.getKingaku());
        assertThat(entity1201.getAccrualDate()).isEqualTo(row121.getAccrualDate());
        assertThat(entity1201.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1201.getAccrualDate()));
        assertThat(entity1201.getPeriodMediate()).isEqualTo(row121.getPeriodMediate());
        assertThat(entity1201.getPartnerJuusho()).isEqualTo(row121.getJuusho());
        assertThat(entity1201.getShokugyou()).isEqualTo(row121.getShokugyou());
        assertThat(entity1201.getBikou()).isEqualTo(row121.getBikou());
        assertThat(entity1201.getTohshibangou()).isEqualTo(row121.getTohshibangou());
        assertThat(entity1201.getGyoukubun()).isEqualTo(row121.getGyoukubun());

        // 自由検索
        assertThat(entity1201.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称)名称個人住所個人");

        /* その12-2 */
        OfferingBalancesheetIncome2025Entity entity1202 = listIncome.get(16);

        // 様式項目
        assertThat(entity1202.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1202.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート記載内容
        assertThat(entity1202.getPageTotal()).isEqualTo(sheet122.getPageTotal());
        assertThat(entity1202.getPartyName()).isEqualTo(sheet122.getPartyName());
        assertThat(entity1202.getSortNo()).isEqualTo(sheet122.getSortNo());

        assertThat(entity1202.getIchirenNo()).isEqualTo(row122.getIchirenNo());
        assertThat(entity1202.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet122.getPartyName() + ")");
        assertThat(entity1202.getPartnerName()).isEqualTo(row122.getName());
        assertThat(entity1202.getKingaku()).isEqualTo(row122.getKingaku());
        assertThat(entity1202.getAccrualDate()).isEqualTo(row122.getAccrualDate());
        assertThat(entity1202.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1202.getAccrualDate()));
        assertThat(entity1202.getPeriodMediate()).isEqualTo(row122.getPeriodMediate());
        assertThat(entity1202.getPartnerJuusho()).isEqualTo(row122.getJuusho());
        assertThat(entity1202.getShokugyou()).isEqualTo(row122.getShokugyou());
        assertThat(entity1202.getBikou()).isEqualTo(row122.getBikou());
        assertThat(entity1202.getTohshibangou()).isEqualTo(row122.getTohshibangou());
        assertThat(entity1202.getGyoukubun()).isEqualTo(row122.getGyoukubun());

        // 自由検索
        assertThat(entity1202.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称)名称法人住所法人");

        /* その12-3 */
        OfferingBalancesheetIncome2025Entity entity1203 = listIncome.get(17);

        // 様式項目
        assertThat(entity1203.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1203.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート記載内容
        assertThat(entity1203.getPageTotal()).isEqualTo(sheet123.getPageTotal());
        assertThat(entity1203.getPartyName()).isEqualTo(sheet123.getPartyName());
        assertThat(entity1203.getSortNo()).isEqualTo(sheet123.getSortNo());

        assertThat(entity1203.getIchirenNo()).isEqualTo(row123.getIchirenNo());
        assertThat(entity1203.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet123.getPartyName() + ")");
        assertThat(entity1203.getPartnerName()).isEqualTo(row123.getName());
        assertThat(entity1203.getKingaku()).isEqualTo(row123.getKingaku());
        assertThat(entity1203.getAccrualDate()).isEqualTo(row123.getAccrualDate());
        assertThat(entity1203.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1203.getAccrualDate()));
        assertThat(entity1203.getPeriodMediate()).isEqualTo(row123.getPeriodMediate());
        assertThat(entity1203.getPartnerJuusho()).isEqualTo(row123.getJuusho());
        assertThat(entity1203.getShokugyou()).isEqualTo(row123.getShokugyou());
        assertThat(entity1203.getBikou()).isEqualTo(row123.getBikou());
        assertThat(entity1203.getTohshibangou()).isEqualTo(row123.getTohshibangou());
        assertThat(entity1203.getGyoukubun()).isEqualTo(row123.getGyoukubun());

        // 自由検索
        assertThat(entity1203.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称)名称政治団体住所政治団体");

        List<OfferingBalancesheetOutcome2025Entity> listOutcome = offeringBalancesheetOutcome2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetOutcomeId(documentCode);

        assertThat(listOutcome.size()).isEqualTo(12); // 3区分+9区分

        OfferingBalancesheetOutcome2025Entity entity1401 = listOutcome.get(0);

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
        OfferingBalancesheetOutcome2025Entity entity1402 = listOutcome.get(1);
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
        OfferingBalancesheetOutcome2025Entity entity1403 = listOutcome.get(2);
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
        OfferingBalancesheetOutcome2025Entity entity1501 = listOutcome.get(3);
        // 様式区分
        assertThat(entity1501.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1501.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SOSHIKIKATSUDOUHI);

        // シート情報
        assertThat(entity1501.getPageTotal()).isEqualTo(sheet151.getPageTotal());
        assertThat(entity1501.getSonotaTotal()).isEqualTo(sheet151.getSonotaTotal());
        assertThat(entity1501.getHimoku()).isEqualTo(sheet151.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1501.getKingaku()).isEqualTo(row151.getKingaku());
        assertThat(entity1501.getAccrualDate()).isEqualTo(row151.getAccrualDate());
        assertThat(entity1501.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row151.getAccrualDate()));

        /* その15区分2 */
        OfferingBalancesheetOutcome2025Entity entity1502 = listOutcome.get(4);
        // 様式区分
        assertThat(entity1502.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1502.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENKYOKATSUDOUHI);

        // シート情報
        assertThat(entity1502.getPageTotal()).isEqualTo(sheet152.getPageTotal());
        assertThat(entity1502.getSonotaTotal()).isEqualTo(sheet152.getSonotaTotal());
        assertThat(entity1502.getHimoku()).isEqualTo(sheet152.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1502.getKingaku()).isEqualTo(row152.getKingaku());
        assertThat(entity1502.getAccrualDate()).isEqualTo(row152.getAccrualDate());
        assertThat(entity1502.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row152.getAccrualDate()));

        /* その15区分3 */
        OfferingBalancesheetOutcome2025Entity entity1503 = listOutcome.get(5);
        // 様式区分
        assertThat(entity1503.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1503.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIKANSHIHAKKOUHI);

        // シート情報
        assertThat(entity1503.getPageTotal()).isEqualTo(sheet153.getPageTotal());
        assertThat(entity1503.getSonotaTotal()).isEqualTo(sheet153.getSonotaTotal());
        assertThat(entity1503.getHimoku()).isEqualTo(sheet153.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1503.getKingaku()).isEqualTo(row153.getKingaku());
        assertThat(entity1503.getAccrualDate()).isEqualTo(row153.getAccrualDate());
        assertThat(entity1503.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row153.getAccrualDate()));

        /* その15区分4 */
        OfferingBalancesheetOutcome2025Entity entity1504 = listOutcome.get(6);
        // 様式区分
        assertThat(entity1504.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1504.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENDENKOUKOKUHI);

        // シート情報
        assertThat(entity1504.getPageTotal()).isEqualTo(sheet154.getPageTotal());
        assertThat(entity1504.getSonotaTotal()).isEqualTo(sheet154.getSonotaTotal());
        assertThat(entity1504.getHimoku()).isEqualTo(sheet154.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1504.getKingaku()).isEqualTo(row154.getKingaku());
        assertThat(entity1504.getAccrualDate()).isEqualTo(row154.getAccrualDate());
        assertThat(entity1504.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row154.getAccrualDate()));

        /* その15区分5 */
        OfferingBalancesheetOutcome2025Entity entity1505 = listOutcome.get(7);
        // 様式区分
        assertThat(entity1505.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1505.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_PARTYKAISAIHI);

        // シート情報
        assertThat(entity1505.getPageTotal()).isEqualTo(sheet155.getPageTotal());
        assertThat(entity1505.getSonotaTotal()).isEqualTo(sheet155.getSonotaTotal());
        assertThat(entity1505.getHimoku()).isEqualTo(sheet155.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1505.getKingaku()).isEqualTo(row155.getKingaku());
        assertThat(entity1505.getAccrualDate()).isEqualTo(row155.getAccrualDate());
        assertThat(entity1505.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row155.getAccrualDate()));

        /* その15区分6 */
        OfferingBalancesheetOutcome2025Entity entity1506 = listOutcome.get(8);
        // 様式区分
        assertThat(entity1506.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1506.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAJIGYOU);

        // シート情報
        assertThat(entity1506.getPageTotal()).isEqualTo(sheet156.getPageTotal());
        assertThat(entity1506.getSonotaTotal()).isEqualTo(sheet156.getSonotaTotal());
        assertThat(entity1506.getHimoku()).isEqualTo(sheet156.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1506.getKingaku()).isEqualTo(row156.getKingaku());
        assertThat(entity1506.getAccrualDate()).isEqualTo(row156.getAccrualDate());
        assertThat(entity1506.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row156.getAccrualDate()));

        /* その15区分7 */
        OfferingBalancesheetOutcome2025Entity entity1507 = listOutcome.get(9);
        // 様式区分
        assertThat(entity1507.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1507.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_CHOUSAKENKYUHI);

        // シート情報
        assertThat(entity1507.getPageTotal()).isEqualTo(sheet157.getPageTotal());
        assertThat(entity1507.getSonotaTotal()).isEqualTo(sheet157.getSonotaTotal());
        assertThat(entity1507.getHimoku()).isEqualTo(sheet157.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1507.getKingaku()).isEqualTo(row157.getKingaku());
        assertThat(entity1507.getAccrualDate()).isEqualTo(row157.getAccrualDate());
        assertThat(entity1507.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row157.getAccrualDate()));

        /* その15区分8 */
        OfferingBalancesheetOutcome2025Entity entity1508 = listOutcome.get(10);
        // 様式区分
        assertThat(entity1508.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1508.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIFUKOUFUKIN);

        // シート情報
        assertThat(entity1508.getPageTotal()).isEqualTo(sheet158.getPageTotal());
        assertThat(entity1508.getSonotaTotal()).isEqualTo(sheet158.getSonotaTotal());
        assertThat(entity1508.getHimoku()).isEqualTo(sheet158.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1508.getKingaku()).isEqualTo(row158.getKingaku());
        assertThat(entity1508.getAccrualDate()).isEqualTo(row158.getAccrualDate());
        assertThat(entity1508.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row158.getAccrualDate()));

        /* その15区分9 */
        OfferingBalancesheetOutcome2025Entity entity1509 = listOutcome.get(11);
        // 様式区分
        assertThat(entity1509.getYoushikiKbn()).isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        assertThat(entity1509.getYoushikiEdaKbn())
                .isEqualTo(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAKEIHI);

        // シート情報
        assertThat(entity1509.getPageTotal()).isEqualTo(sheet159.getPageTotal());
        assertThat(entity1509.getSonotaTotal()).isEqualTo(sheet159.getSonotaTotal());
        assertThat(entity1509.getHimoku()).isEqualTo(sheet159.getHimoku());

        // 掲載データ情報(抜粋)
        assertThat(entity1509.getKingaku()).isEqualTo(row159.getKingaku());
        assertThat(entity1509.getAccrualDate()).isEqualTo(row159.getAccrualDate());
        assertThat(entity1509.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row159.getAccrualDate()));

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

        /* 資産 */

        List<OfferingBalancesheet0718Estate2025Entity> listAsset = offeringBalancesheet0718Estate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(documentCode);
        List<OfferingBalancesheet0719RealEstate2025Entity> listRealEstate = offeringBalancesheet0719RealEstate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(documentCode);

        // 取得数の検証
        assertThat(listAsset.size()).isEqualTo(12);
        assertThat(listRealEstate.size()).isEqualTo(3);

        OfferingBalancesheet0718Estate2025Entity entityAsset01 = listAsset.get(0);
        OfferingBalancesheet0719RealEstate2025Entity entityRealEstate1 = listRealEstate.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityAsset01.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entityAsset01.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entityAsset01.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entityAsset01.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entityAsset01.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityAsset01.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entityAsset01.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entityAsset01.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entityAsset01.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entityAsset01.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityAsset01.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityAsset01.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* 区分1 */
        assertThat(entityAsset01.getIchirenNo()).isEqualTo(row181.getIchirenNo());
        assertThat(entityAsset01.getTekiyou()).isEqualTo(row181.getTekiyou());
        assertThat(entityAsset01.getKingaku()).isEqualTo(row181.getKingaku());
        assertThat(entityAsset01.getAccrualDate()).isEqualTo(row181.getAccrualDate());
        assertThat(entityAsset01.getBiko()).isEqualTo(row181.getBiko());
        OfferingBalancesheet0718Estate2025Entity entityAsset02 = listAsset.get(1);
        assertThat(entityAsset02.getKingaku()).isEqualTo(row182.getKingaku());

        /* 区分3 */
        OfferingBalancesheet0718Estate2025Entity entityAsset03 = listAsset.get(2);
        assertThat(entityAsset03.getKingaku()).isEqualTo(row183.getKingaku());

        /* 区分4 */
        OfferingBalancesheet0718Estate2025Entity entityAsset04 = listAsset.get(3);
        assertThat(entityAsset04.getKingaku()).isEqualTo(row184.getKingaku());

        /* 区分5 */
        OfferingBalancesheet0718Estate2025Entity entityAsset05 = listAsset.get(4);
        assertThat(entityAsset05.getKingaku()).isEqualTo(row185.getKingaku());

        /* 区分6 */
        OfferingBalancesheet0718Estate2025Entity entityAsset06 = listAsset.get(5);
        assertThat(entityAsset06.getKingaku()).isEqualTo(row186.getKingaku());

        /* 区分7 */
        OfferingBalancesheet0718Estate2025Entity entityAsset07 = listAsset.get(6);
        assertThat(entityAsset07.getKingaku()).isEqualTo(row187.getKingaku());

        /* 区分8 */
        OfferingBalancesheet0718Estate2025Entity entityAsset08 = listAsset.get(7);
        assertThat(entityAsset08.getKingaku()).isEqualTo(row188.getKingaku());

        /* 区分9 */
        OfferingBalancesheet0718Estate2025Entity entityAsset09 = listAsset.get(8);
        assertThat(entityAsset09.getKingaku()).isEqualTo(row189.getKingaku());

        /* 区分10 */
        OfferingBalancesheet0718Estate2025Entity entityAsset10 = listAsset.get(9);
        assertThat(entityAsset10.getKingaku()).isEqualTo(row1810.getKingaku());

        /* 区分11 */
        OfferingBalancesheet0718Estate2025Entity entityAsset11 = listAsset.get(10);
        assertThat(entityAsset11.getKingaku()).isEqualTo(row1811.getKingaku());

        /* 区分12 */
        OfferingBalancesheet0718Estate2025Entity entityAsset12 = listAsset.get(11);
        assertThat(entityAsset12.getKingaku()).isEqualTo(row1812.getKingaku());

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityRealEstate1.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entityRealEstate1.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entityRealEstate1.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entityRealEstate1.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entityRealEstate1.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityRealEstate1.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entityRealEstate1.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entityRealEstate1.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entityRealEstate1.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entityRealEstate1.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityRealEstate1.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityRealEstate1.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        // 区分
        assertThat(entityRealEstate1.getRealEstateKbn()).isEqualTo(RealEstateKbnConstants.KBN_LAND);
        assertThat(entityRealEstate1.getRealEstateKbnName()).isEqualTo(RealEstateKbnConstants.KBN_LAND_TEXT);
        assertThat(entityRealEstate1.getIchirenNo()).isEqualTo(row1813.getIchirenNo());
        assertThat(entityRealEstate1.getTekiyou()).isEqualTo(row1813.getTekiyou());
        assertThat(entityRealEstate1.getYouto()).isEqualTo(row1813.getYouto());
        assertThat(entityRealEstate1.getKankeiShiyousha()).isEqualTo(row1813.getKankeiShiyousha());
        assertThat(entityRealEstate1.getShiyouYouto()).isEqualTo(row1813.getShiyouYouto());
        assertThat(entityRealEstate1.getShiyouMenseki()).isEqualTo(row1813.getShiyouMenseki());
        assertThat(entityRealEstate1.getShiyouKakaku()).isEqualTo(row1813.getShiyouKakaku());

        /* 建物 */
        OfferingBalancesheet0719RealEstate2025Entity entityRealEstate2 = listRealEstate.get(1);
        assertThat(entityRealEstate2.getRealEstateKbn()).isEqualTo(RealEstateKbnConstants.KBN_BUILDING);
        assertThat(entityRealEstate2.getRealEstateKbnName()).isEqualTo(RealEstateKbnConstants.KBN_BUILDING_TEXT);
        assertThat(entityRealEstate2.getShiyouKakaku()).isEqualTo(row1814.getShiyouKakaku());

        /* 地上権 */
        OfferingBalancesheet0719RealEstate2025Entity entityRealEstate3 = listRealEstate.get(2);
        assertThat(entityRealEstate3.getRealEstateKbn()).isEqualTo(RealEstateKbnConstants.KBN_SURFACE_RIGHTS);
        assertThat(entityRealEstate3.getRealEstateKbnName()).isEqualTo(RealEstateKbnConstants.KBN_SURFACE_RIGHTS_TEXT);
        assertThat(entityRealEstate3.getShiyouKakaku()).isEqualTo(row1815.getShiyouKakaku());

        List<OfferingBalancesheetDifficalt0800Recipt2025Entity> list0800 = offeringBalancesheetDifficalt0800Recipt2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode);

        assertThat(list0800.size()).isEqualTo(1);

        OfferingBalancesheetDifficalt0800Recipt2025Entity entity = list0800.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
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

        assertThat(entity.getIchirenNo()).isEqualTo(row08001.getIchirenNo());
        assertThat(entity.getKoumoku()).isEqualTo(row08001.getKoumoku());
        assertThat(entity.getTekiyou()).isEqualTo(row08001.getTekiyou());
        assertThat(entity.getKingaku()).isEqualTo(row08001.getKingaku());
        assertThat(entity.getAccrualDate()).isEqualTo(row08001.getAccrualDate());
        assertThat(entity.getJijyou()).isEqualTo(row08001.getJijyou());

        List<OfferingBalancesheetWithdrawal0802Transfer2025Entity> list0802 = offeringBalancesheetWithdrawal0802Transfer2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetWithdrawal0802TransferId(documentCode);

        // 取得データは1件
        assertThat(list0802.size()).isEqualTo(1);

        OfferingBalancesheetWithdrawal0802Transfer2025Entity entity0802a = list0802.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity0802a.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity0802a.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity0802a.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity0802a.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity0802a.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity0802a.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity0802a.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity0802a.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity0802a.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity0802a.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity0802a.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity0802a.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity0802a.getShishutsuKoumoku()).isEqualTo(sheet08021.getShishutsuKoumoku());
        assertThat(entity0802a.getTekiyou()).isEqualTo(sheet08021.getTekiyou());
        assertThat(entity0802a.getDantaiName0820()).isEqualTo(sheet08021.getDantaiName0820());

    }

    @Test
    @Transactional
    void testPracticeRegistOrganization() throws Exception { // NOPMD

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setPoliticalOrganizationId(0L); // 政治団体未定
        documentPropertyDto.setIsAddOrganization(true); // 政治団体を読みとりデータで新規登録

        String path = GetCurrentResourcePath.getBackTestResourcePath() + "/sample/balancesheet/2022_ホリエモン新党_SYUUSI.xml";

        AllBookDto allBookDto = readAllBookByXmlFileLogic.practice(path, "Windows-31J");

        documentPropertyDto.setHoukokuNen(allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto()
                .getSheet070100CoverAndOrganizationDetailsDto().getHoukokuNen()); // 報告年は読みとりデータより
        /*
         * 
         * 登録作業
         * 
         */
        RegistPoliticalOrgBalancesheetReportResultDto resultDto = insertPoliticalOrgnaizationBalancesheetReportService
                .practice(documentPropertyDto, allBookDto, CreateTestPrivilegeDtoUtil.pracitce());
        assertTrue(resultDto.getIsOk() ,"登録完了");
        
        // 登録時に新たに政治団体が追加されていること
        List<PoliticalOrganizationEntity> list = politicalOrganizationRepository.findAll();
        
        PoliticalOrganizationEntity entityNew = list.stream()
                .filter(entity -> "ホリエモン新党".equals(entity.getPoliticalOrganizationName())).toList().get(0);
        assertThat(entityNew.getDaihyoshaName()).isEqualTo("立花　孝志");

    }
}
