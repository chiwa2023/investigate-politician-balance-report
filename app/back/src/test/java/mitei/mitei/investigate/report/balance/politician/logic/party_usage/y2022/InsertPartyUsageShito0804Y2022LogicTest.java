package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022;

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
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShitoCoreDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0804Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0804Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0804Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0804Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0804Y2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0804Y2022LogicTest {
    // CHECKSTYLE:OFF

    /** 初期値Long */
    private static final long INIT_LONG = 0L;

    /** 初期値Integer */
    private static final int INIT_INT = 0;

    /** 初期値String */
    private static final String INIT_STRING = "";

    /** 初期値LocalDate */
    private static final LocalDate INIT_LOCALDATE = LocalDate.of(1980, 1, 1);

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0804Y2022Logic insertPartyUsageShito0804Y2022Logic;

    
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

    /** 様式8その4Repository */
    @Autowired
    private OfferingPartyUsage0804Report2022Repository offeringPartyUsage0804Report2022Repository;

    @Test
    @Transactional
    void testPractice() { // NOPMD

        int houkokuNen = 2022;
        String oathDateDtring = "R6/12/01";

        // 政治団体基礎情報
        Long documentCode = 3434L;
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

        AllShitoBook allShitoBook = new AllShitoBook();

        /* 様式8その4 */
        CreateTestDataPartyUsageShito0804Logic createTestDataPartyUsageShito0804Logic = new CreateTestDataPartyUsageShito0804Logic();
        createTestDataPartyUsageShito0804Logic.practice(allShitoBook);
        Sheet0804Dto sheet0804 = allShitoBook.getShito0804Dto().getKbn080404Dto().getList().get(0);
        // 1行データあり
        RowShitoCoreDto row0804 = sheet0804.getList().get(0);

        int size = insertPartyUsageShito0804Y2022Logic.practice(false, documentCode, documentPropertyDto,
                allShitoBook.getShito0804Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0804Report2022Entity> list0804 = offeringPartyUsage0804Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0804ReportId(documentCode);

        assertThat(list0804.size()).isEqualTo(size);// 設定数と登録数が一致

        OfferingPartyUsage0804Report2022Entity entity01 = list0804.get(0);

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

        OfferingPartyUsage0804Report2022Entity entity02 = list0804.get(1);

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
        OfferingPartyUsage0804Report2022Entity entity03 = list0804.get(2);
        OfferingPartyUsage0804Report2022Entity entity04 = list0804.get(3);

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
        OfferingPartyUsage0804Report2022Entity entity05 = list0804.get(4);
        OfferingPartyUsage0804Report2022Entity entity06 = list0804.get(5);

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
        OfferingPartyUsage0804Report2022Entity entity07 = list0804.get(6);
        OfferingPartyUsage0804Report2022Entity entity08 = list0804.get(7);

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
        OfferingPartyUsage0804Report2022Entity entity09 = list0804.get(8);
        OfferingPartyUsage0804Report2022Entity entity10 = list0804.get(9);

        assertThat(entity09.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity09.getShishutsuKbnName()).isEqualTo(KBN05_NAME);
        assertThat(entity10.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity10.getShishutsuKbnName()).isEqualTo(KBN05_NAME);

        /* 区分06 */
        OfferingPartyUsage0804Report2022Entity entity11 = list0804.get(10);
        OfferingPartyUsage0804Report2022Entity entity12 = list0804.get(11);

        assertThat(entity11.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity11.getShishutsuKbnName()).isEqualTo(KBN06_NAME);
        assertThat(entity12.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity12.getShishutsuKbnName()).isEqualTo(KBN06_NAME);

        /* 区分07 */
        OfferingPartyUsage0804Report2022Entity entity13 = list0804.get(12);
        OfferingPartyUsage0804Report2022Entity entity14 = list0804.get(13);

        assertThat(entity13.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity13.getShishutsuKbnName()).isEqualTo(KBN07_NAME);
        assertThat(entity14.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity14.getShishutsuKbnName()).isEqualTo(KBN07_NAME);

        /* 区分08 */
        OfferingPartyUsage0804Report2022Entity entity15 = list0804.get(14);
        OfferingPartyUsage0804Report2022Entity entity16 = list0804.get(15);

        assertThat(entity15.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity15.getShishutsuKbnName()).isEqualTo(KBN08_NAME);
        assertThat(entity16.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity16.getShishutsuKbnName()).isEqualTo(KBN08_NAME);

        /* 区分09 */
        OfferingPartyUsage0804Report2022Entity entity17 = list0804.get(16);
        OfferingPartyUsage0804Report2022Entity entity18 = list0804.get(17);

        assertThat(entity17.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity17.getShishutsuKbnName()).isEqualTo(KBN09_NAME);
        assertThat(entity18.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity18.getShishutsuKbnName()).isEqualTo(KBN09_NAME);

        /* 区分10 */
        OfferingPartyUsage0804Report2022Entity entity19 = list0804.get(18);
        OfferingPartyUsage0804Report2022Entity entity20 = list0804.get(19);

        assertThat(entity19.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity19.getShishutsuKbnName()).isEqualTo(KBN10_NAME);
        assertThat(entity20.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity20.getShishutsuKbnName()).isEqualTo(KBN10_NAME);

        /* 区分11 */
        OfferingPartyUsage0804Report2022Entity entity21 = list0804.get(20);
        OfferingPartyUsage0804Report2022Entity entity22 = list0804.get(21);

        assertThat(entity21.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity21.getShishutsuKbnName()).isEqualTo(KBN11_NAME);
        assertThat(entity22.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity22.getShishutsuKbnName()).isEqualTo(KBN11_NAME);

        /* 区分12 */
        OfferingPartyUsage0804Report2022Entity entity23 = list0804.get(22);
        OfferingPartyUsage0804Report2022Entity entity24 = list0804.get(23);

        assertThat(entity23.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity23.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
        assertThat(entity24.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity24.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
    }

}
