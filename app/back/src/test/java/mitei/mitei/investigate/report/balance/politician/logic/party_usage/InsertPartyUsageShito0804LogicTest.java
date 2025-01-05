package mitei.mitei.investigate.report.balance.politician.logic.party_usage; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
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

import mitei.mitei.common.constants.party.usage.ConstantsKbn0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShitoCoreDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0804Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0804Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0804Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0804Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0804Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0804Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0804Report2023Repository;
// import追加指定位置

/**
 * InsertPartyUsageShito0804Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0804LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0804Logic insertPartyUsageShito0804Logic;

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
    
    /** 様式8その4Repository(2025) */
    @Autowired
    private OfferingPartyUsage0804Report2025Repository offeringPartyUsage0804Report2025Repository;

    /** 様式8その4Repository(2022) */
    @Autowired
    private OfferingPartyUsage0804Report2022Repository offeringPartyUsage0804Report2022Repository;

    /** 様式8その4Repository(2024) */
    @Autowired
    private OfferingPartyUsage0804Report2024Repository offeringPartyUsage0804Report2024Repository;

    /** 様式8その4Repository(2023) */
    @Autowired
    private OfferingPartyUsage0804Report2023Repository offeringPartyUsage0804Report2023Repository;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 初期値Long */
    private static final long INIT_LONG =0L;
    
    /** 初期値Integer */
    private static final int INIT_INT =0;
    
    /** 初期値String */
    private static final String INIT_STRING ="";
    
    /** 初期値LocalDate */
    private static final LocalDate INIT_LOCALDATE = LocalDate.of(1948, 7, 29);
    
    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    void testPractice20251() { // NOPMD
        
        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        partyUsageDocumentPoliticalPropertyDto.setNendo(2025); // 実際には表紙からコピー
        partyUsageDocumentPoliticalPropertyDto.setOfferingDate(LocalDate.of(2022, 12, 1)); // 実際には宣誓書からコピー
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationId(433L);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationCode(431);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        partyUsageDocumentPoliticalPropertyDto.setDantaiName("ちゃらん団体");
        partyUsageDocumentPoliticalPropertyDto.setDaihyouName("代表者 世間芸名");
        partyUsageDocumentPoliticalPropertyDto.setRelationKbn(223);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonIdDelegate(9898L);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonCodeDelegate(9867);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");
        
        // 1行データあり
        RowShitoCoreDto row1 = new RowShitoCoreDto();
        row1.setRowNo(1);
        row1.setUsageItem("取引項目");
        row1.setAmountAll(10000L); // NOPMD
        row1.setAmountKoufukin(4000L);
        row1.setAmountMyFunds(6000L);
        row1.setAccrualDate("R4/12/21");
        row1.setPayeeName("取引先名称");
        row1.setAddress("取引先住所");
        row1.setBikou("備考");
        row1.setFlgCollectRecipt(1);

        Sheet0804Dto sheet1 = new Sheet0804Dto();

        sheet1.setHimoku("費目");
        sheet1.setSonotaAmount(500L);
        sheet1.setSonotaKoufukin("300");
        sheet1.setSonotaMyFunds("200");
        sheet1.setAmountAll(5000L);
        sheet1.setAmountAllKoufukin(3000L);
        sheet1.setAmountAllMyFunds(2000L);

        sheet1.getList().add(row1);

        Shito0804Dto shito1 = new Shito0804Dto();
        shito1.getKbn080401Dto().getList().add(sheet1);
        shito1.getKbn080401Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080402Dto().getList().add(sheet1);
        shito1.getKbn080402Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080403Dto().getList().add(sheet1);
        shito1.getKbn080403Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080404Dto().getList().add(sheet1);
        shito1.getKbn080404Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080405Dto().getList().add(sheet1);
        shito1.getKbn080405Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080406Dto().getList().add(sheet1);
        shito1.getKbn080406Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080407Dto().getList().add(sheet1);
        shito1.getKbn080407Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080408Dto().getList().add(sheet1);
        shito1.getKbn080408Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080409Dto().getList().add(sheet1);
        shito1.getKbn080409Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080410Dto().getList().add(sheet1);
        shito1.getKbn080410Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080411Dto().getList().add(sheet1);
        shito1.getKbn080411Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080412Dto().getList().add(sheet1);
        shito1.getKbn080412Dto().getList().add(new Sheet0804Dto());
        
        // 支払者の関連者紐づけを行った場合のテストはクラスを分けて行う(現段階では紐づけしない)
        int size = insertPartyUsageShito0804Logic.practice(false,documentCode, partyUsageDocumentPoliticalPropertyDto, shito1, CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingPartyUsage0804Report2025Entity> list = offeringPartyUsage0804Report2025Repository.findByDocumentCodeOrderByPartyUsage0804ReportId(documentCode);
                
        //assertThat(size).isEqualTo(24);//1行明細ありシートと空シートが各12区分=24行
        assertThat(list.size()).isEqualTo(size);//設定数と登録数が一致
        
        OfferingPartyUsage0804Report2025Entity entity01 = list.get(0);
        
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity01.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity01.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entity01.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entity01.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entity01.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity01.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entity01.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entity01.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entity01.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entity01.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity01.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity01.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());
        
        /* シート情報 */
        assertThat(entity01.getShishutsuKbn()).isEqualTo(KBN01);
        assertThat(entity01.getShishutsuKbnName()).isEqualTo(KBN01_NAME);
        
        assertThat(entity01.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity01.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity01.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity01.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity01.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity01.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity01.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity01.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity01.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity01.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity01.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity01.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity01.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity01.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity01.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity01.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity01.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        assertThat(entity01.getSearchWords()).isEqualTo("取引項目取引先名称取引先住所"); // NOPMD
        /* 追加情報 */
        assertThat(entity01.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        
        
        OfferingPartyUsage0804Report2025Entity entity02 = list.get(1);
        
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
        assertThat(entity02.getSearchWords()).isEqualTo(INIT_STRING);
        
        

        /* 区分02 */
        OfferingPartyUsage0804Report2025Entity entity03 = list.get(2);
        OfferingPartyUsage0804Report2025Entity entity04 = list.get(3);
        
        /* シート情報 */
        assertThat(entity03.getShishutsuKbn()).isEqualTo(KBN02);
        assertThat(entity03.getShishutsuKbnName()).isEqualTo(KBN02_NAME);
        
        assertThat(entity03.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity03.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity03.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity03.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity03.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity03.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity03.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity03.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity03.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity03.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity03.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity03.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity03.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity03.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity03.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity03.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity03.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        assertThat(entity03.getSearchWords()).isEqualTo("取引項目取引先名称取引先住所");
        /* 追加情報 */
        assertThat(entity03.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        assertThat(entity04.getSearchWords()).isEqualTo(INIT_STRING);
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
        OfferingPartyUsage0804Report2025Entity entity05 = list.get(4);
        OfferingPartyUsage0804Report2025Entity entity06 = list.get(5);
        
        /* シート情報 */
        assertThat(entity05.getShishutsuKbn()).isEqualTo(KBN03);
        assertThat(entity05.getShishutsuKbnName()).isEqualTo(KBN03_NAME);
        
        assertThat(entity05.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity05.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity05.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity05.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity05.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity05.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity05.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity05.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity05.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity05.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity05.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity05.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity05.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity05.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity05.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity05.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity05.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity05.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        OfferingPartyUsage0804Report2025Entity entity07 = list.get(6);
        OfferingPartyUsage0804Report2025Entity entity08 = list.get(7);
        
        /* シート情報 */
        assertThat(entity07.getShishutsuKbn()).isEqualTo(KBN04);
        assertThat(entity07.getShishutsuKbnName()).isEqualTo(KBN04_NAME);
        
        assertThat(entity07.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity07.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity07.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity07.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity07.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity07.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity07.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity07.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity07.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity07.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity07.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity07.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity07.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity07.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity07.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity07.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity07.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity07.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        OfferingPartyUsage0804Report2025Entity entity09 = list.get(8);
        OfferingPartyUsage0804Report2025Entity entity10 = list.get(9);
        
        assertThat(entity09.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity09.getShishutsuKbnName()).isEqualTo(KBN05_NAME);
        assertThat(entity10.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity10.getShishutsuKbnName()).isEqualTo(KBN05_NAME);

        

        /* 区分06 */
        OfferingPartyUsage0804Report2025Entity entity11 = list.get(10);
        OfferingPartyUsage0804Report2025Entity entity12 = list.get(11);
        
        assertThat(entity11.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity11.getShishutsuKbnName()).isEqualTo(KBN06_NAME);
        assertThat(entity12.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity12.getShishutsuKbnName()).isEqualTo(KBN06_NAME);

        
        /* 区分07 */
        OfferingPartyUsage0804Report2025Entity entity13 = list.get(12);
        OfferingPartyUsage0804Report2025Entity entity14 = list.get(13);
        
        assertThat(entity13.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity13.getShishutsuKbnName()).isEqualTo(KBN07_NAME);
        assertThat(entity14.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity14.getShishutsuKbnName()).isEqualTo(KBN07_NAME);
        

        /* 区分08 */
        OfferingPartyUsage0804Report2025Entity entity15 = list.get(14);
        OfferingPartyUsage0804Report2025Entity entity16 = list.get(15);
        
        assertThat(entity15.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity15.getShishutsuKbnName()).isEqualTo(KBN08_NAME);
        assertThat(entity16.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity16.getShishutsuKbnName()).isEqualTo(KBN08_NAME);

        /* 区分09 */
        OfferingPartyUsage0804Report2025Entity entity17 = list.get(16);
        OfferingPartyUsage0804Report2025Entity entity18 = list.get(17);
        
        assertThat(entity17.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity17.getShishutsuKbnName()).isEqualTo(KBN09_NAME);
        assertThat(entity18.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity18.getShishutsuKbnName()).isEqualTo(KBN09_NAME);
        
        
        /* 区分10 */
        OfferingPartyUsage0804Report2025Entity entity19 = list.get(18);
        OfferingPartyUsage0804Report2025Entity entity20 = list.get(19);
        
        assertThat(entity19.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity19.getShishutsuKbnName()).isEqualTo(KBN10_NAME);
        assertThat(entity20.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity20.getShishutsuKbnName()).isEqualTo(KBN10_NAME);
        

        
        /* 区分11 */
        OfferingPartyUsage0804Report2025Entity entity21 = list.get(20);
        OfferingPartyUsage0804Report2025Entity entity22 = list.get(21);
        
        assertThat(entity21.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity21.getShishutsuKbnName()).isEqualTo(KBN11_NAME);
        assertThat(entity22.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity22.getShishutsuKbnName()).isEqualTo(KBN11_NAME);
        
        
        /* 区分12 */
        OfferingPartyUsage0804Report2025Entity entity23 = list.get(22);
        OfferingPartyUsage0804Report2025Entity entity24 = list.get(23);
        
        assertThat(entity23.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity23.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
        assertThat(entity24.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity24.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
        
    }
    
    @Test
    @Tag(TEST_TAG)
    @Transactional
    void testPractice20221() { // NOPMD
        
        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        partyUsageDocumentPoliticalPropertyDto.setNendo(2022); // 実際には表紙からコピー
        partyUsageDocumentPoliticalPropertyDto.setOfferingDate(LocalDate.of(2022, 12, 1)); // 実際には宣誓書からコピー
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationId(433L);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationCode(431);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        partyUsageDocumentPoliticalPropertyDto.setDantaiName("ちゃらん団体");
        partyUsageDocumentPoliticalPropertyDto.setDaihyouName("代表者 世間芸名");
        partyUsageDocumentPoliticalPropertyDto.setRelationKbn(223);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonIdDelegate(9898L);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonCodeDelegate(9867);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");
        
        // 1行データあり
        RowShitoCoreDto row1 = new RowShitoCoreDto();
        row1.setRowNo(1);
        row1.setUsageItem("取引項目");
        row1.setAmountAll(10000L); // NOPMD
        row1.setAmountKoufukin(4000L);
        row1.setAmountMyFunds(6000L);
        row1.setAccrualDate("R4/12/21");
        row1.setPayeeName("取引先名称");
        row1.setAddress("取引先住所");
        row1.setBikou("備考");
        row1.setFlgCollectRecipt(1);

        Sheet0804Dto sheet1 = new Sheet0804Dto();

        sheet1.setHimoku("費目");
        sheet1.setSonotaAmount(500L);
        sheet1.setSonotaKoufukin("300");
        sheet1.setSonotaMyFunds("200");
        sheet1.setAmountAll(5000L);
        sheet1.setAmountAllKoufukin(3000L);
        sheet1.setAmountAllMyFunds(2000L);

        sheet1.getList().add(row1);

        Shito0804Dto shito1 = new Shito0804Dto();
        shito1.getKbn080401Dto().getList().add(sheet1);
        shito1.getKbn080401Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080402Dto().getList().add(sheet1);
        shito1.getKbn080402Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080403Dto().getList().add(sheet1);
        shito1.getKbn080403Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080404Dto().getList().add(sheet1);
        shito1.getKbn080404Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080405Dto().getList().add(sheet1);
        shito1.getKbn080405Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080406Dto().getList().add(sheet1);
        shito1.getKbn080406Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080407Dto().getList().add(sheet1);
        shito1.getKbn080407Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080408Dto().getList().add(sheet1);
        shito1.getKbn080408Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080409Dto().getList().add(sheet1);
        shito1.getKbn080409Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080410Dto().getList().add(sheet1);
        shito1.getKbn080410Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080411Dto().getList().add(sheet1);
        shito1.getKbn080411Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080412Dto().getList().add(sheet1);
        shito1.getKbn080412Dto().getList().add(new Sheet0804Dto());
        
        // 支払者の関連者紐づけを行った場合のテストはクラスを分けて行う(現段階では紐づけしない)
        int size = insertPartyUsageShito0804Logic.practice(false,documentCode, partyUsageDocumentPoliticalPropertyDto, shito1, CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingPartyUsage0804Report2022Entity> list = offeringPartyUsage0804Report2022Repository.findByDocumentCodeOrderByPartyUsage0804ReportId(documentCode);
                
        //assertThat(size).isEqualTo(24);//1行明細ありシートと空シートが各12区分=24行
        assertThat(list.size()).isEqualTo(size);//設定数と登録数が一致
        
        OfferingPartyUsage0804Report2022Entity entity01 = list.get(0);
        
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity01.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity01.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entity01.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entity01.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entity01.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity01.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entity01.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entity01.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entity01.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entity01.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity01.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity01.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());
        
        /* シート情報 */
        assertThat(entity01.getShishutsuKbn()).isEqualTo(KBN01);
        assertThat(entity01.getShishutsuKbnName()).isEqualTo(KBN01_NAME);
        
        assertThat(entity01.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity01.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity01.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity01.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity01.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity01.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity01.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity01.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity01.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity01.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity01.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity01.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity01.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity01.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity01.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity01.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity01.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        assertThat(entity01.getSearchWords()).isEqualTo("取引項目取引先名称取引先住所");
        /* 追加情報 */
        assertThat(entity01.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        
        
        OfferingPartyUsage0804Report2022Entity entity02 = list.get(1);
        
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
        assertThat(entity02.getSearchWords()).isEqualTo(INIT_STRING);
        
        

        /* 区分02 */
        OfferingPartyUsage0804Report2022Entity entity03 = list.get(2);
        OfferingPartyUsage0804Report2022Entity entity04 = list.get(3);
        
        /* シート情報 */
        assertThat(entity03.getShishutsuKbn()).isEqualTo(KBN02);
        assertThat(entity03.getShishutsuKbnName()).isEqualTo(KBN02_NAME);
        
        assertThat(entity03.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity03.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity03.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity03.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity03.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity03.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity03.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity03.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity03.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity03.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity03.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity03.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity03.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity03.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity03.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity03.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity03.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        assertThat(entity03.getSearchWords()).isEqualTo("取引項目取引先名称取引先住所");
        /* 追加情報 */
        assertThat(entity03.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        assertThat(entity04.getSearchWords()).isEqualTo(INIT_STRING);
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
        OfferingPartyUsage0804Report2022Entity entity05 = list.get(4);
        OfferingPartyUsage0804Report2022Entity entity06 = list.get(5);
        
        /* シート情報 */
        assertThat(entity05.getShishutsuKbn()).isEqualTo(KBN03);
        assertThat(entity05.getShishutsuKbnName()).isEqualTo(KBN03_NAME);
        
        assertThat(entity05.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity05.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity05.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity05.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity05.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity05.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity05.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity05.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity05.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity05.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity05.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity05.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity05.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity05.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity05.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity05.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity05.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity05.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        OfferingPartyUsage0804Report2022Entity entity07 = list.get(6);
        OfferingPartyUsage0804Report2022Entity entity08 = list.get(7);
        
        /* シート情報 */
        assertThat(entity07.getShishutsuKbn()).isEqualTo(KBN04);
        assertThat(entity07.getShishutsuKbnName()).isEqualTo(KBN04_NAME);
        
        assertThat(entity07.getSheetHimoku()).isEqualTo(sheet1.getHimoku());
        assertThat(entity07.getSheetAmountAll()).isEqualTo(sheet1.getAmountAll());
        assertThat(entity07.getSheetAmountAllKoufukin()).isEqualTo(sheet1.getAmountAllKoufukin());
        assertThat(entity07.getSheetAmountAllMyFunds()).isEqualTo(sheet1.getAmountAllMyFunds());
        assertThat(entity07.getSheetAmountSonota()).isEqualTo(sheet1.getSonotaAmount());
        assertThat(entity07.getSheetAmountSonotaKoufukin()).isEqualTo(sheet1.getSonotaKoufukin());
        assertThat(entity07.getSheetAmountSonotaMyFunds()).isEqualTo(sheet1.getSonotaMyFunds());

        /* 行情報 */
        assertThat(entity07.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity07.getUsageItem()).isEqualTo(row1.getUsageItem());
        assertThat(entity07.getAmountAll()).isEqualTo(row1.getAmountAll());
        assertThat(entity07.getAmountKoufukin()).isEqualTo(row1.getAmountKoufukin());
        assertThat(entity07.getAmountMyFunds()).isEqualTo(row1.getAmountMyFunds());
        assertThat(entity07.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity07.getPayeeName()).isEqualTo(row1.getPayeeName());
        assertThat(entity07.getAddress()).isEqualTo(row1.getAddress());
        assertThat(entity07.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity07.getFlgCollectRecipt()).isEqualTo(row1.getFlgCollectRecipt());
        /* 追加情報 */
        assertThat(entity07.getAccrualDateValue()).isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
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
        OfferingPartyUsage0804Report2022Entity entity09 = list.get(8);
        OfferingPartyUsage0804Report2022Entity entity10 = list.get(9);
        
        assertThat(entity09.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity09.getShishutsuKbnName()).isEqualTo(KBN05_NAME);
        assertThat(entity10.getShishutsuKbn()).isEqualTo(KBN05);
        assertThat(entity10.getShishutsuKbnName()).isEqualTo(KBN05_NAME);

        

        /* 区分06 */
        OfferingPartyUsage0804Report2022Entity entity11 = list.get(10);
        OfferingPartyUsage0804Report2022Entity entity12 = list.get(11);
        
        assertThat(entity11.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity11.getShishutsuKbnName()).isEqualTo(KBN06_NAME);
        assertThat(entity12.getShishutsuKbn()).isEqualTo(KBN06);
        assertThat(entity12.getShishutsuKbnName()).isEqualTo(KBN06_NAME);

        
        /* 区分07 */
        OfferingPartyUsage0804Report2022Entity entity13 = list.get(12);
        OfferingPartyUsage0804Report2022Entity entity14 = list.get(13);
        
        assertThat(entity13.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity13.getShishutsuKbnName()).isEqualTo(KBN07_NAME);
        assertThat(entity14.getShishutsuKbn()).isEqualTo(KBN07);
        assertThat(entity14.getShishutsuKbnName()).isEqualTo(KBN07_NAME);
        

        /* 区分08 */
        OfferingPartyUsage0804Report2022Entity entity15 = list.get(14);
        OfferingPartyUsage0804Report2022Entity entity16 = list.get(15);
        
        assertThat(entity15.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity15.getShishutsuKbnName()).isEqualTo(KBN08_NAME);
        assertThat(entity16.getShishutsuKbn()).isEqualTo(KBN08);
        assertThat(entity16.getShishutsuKbnName()).isEqualTo(KBN08_NAME);

        /* 区分09 */
        OfferingPartyUsage0804Report2022Entity entity17 = list.get(16);
        OfferingPartyUsage0804Report2022Entity entity18 = list.get(17);
        
        assertThat(entity17.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity17.getShishutsuKbnName()).isEqualTo(KBN09_NAME);
        assertThat(entity18.getShishutsuKbn()).isEqualTo(KBN09);
        assertThat(entity18.getShishutsuKbnName()).isEqualTo(KBN09_NAME);
        
        
        /* 区分10 */
        OfferingPartyUsage0804Report2022Entity entity19 = list.get(18);
        OfferingPartyUsage0804Report2022Entity entity20 = list.get(19);
        
        assertThat(entity19.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity19.getShishutsuKbnName()).isEqualTo(KBN10_NAME);
        assertThat(entity20.getShishutsuKbn()).isEqualTo(KBN10);
        assertThat(entity20.getShishutsuKbnName()).isEqualTo(KBN10_NAME);
        

        
        /* 区分11 */
        OfferingPartyUsage0804Report2022Entity entity21 = list.get(20);
        OfferingPartyUsage0804Report2022Entity entity22 = list.get(21);
        
        assertThat(entity21.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity21.getShishutsuKbnName()).isEqualTo(KBN11_NAME);
        assertThat(entity22.getShishutsuKbn()).isEqualTo(KBN11);
        assertThat(entity22.getShishutsuKbnName()).isEqualTo(KBN11_NAME);
        
        
        /* 区分12 */
        OfferingPartyUsage0804Report2022Entity entity23 = list.get(22);
        OfferingPartyUsage0804Report2022Entity entity24 = list.get(23);
        
        assertThat(entity23.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity23.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
        assertThat(entity24.getShishutsuKbn()).isEqualTo(KBN12);
        assertThat(entity24.getShishutsuKbnName()).isEqualTo(KBN12_NAME);
        
    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_party_usage_0804_report_2022.sql")
    void testPractice2022() {

        assertEquals(1L, offeringPartyUsage0804Report2022Repository.count(), "初期入力1件");

        int houkokuNen = 2022;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0804Logic.practice(false,documentCode, documentlPropertyDto, new Shito0804Dto(),
                 CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0804Report2022Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("y2024/offering_party_usage_0804_report_2024.sql")
    void testPractice2024() {

        assertEquals(1L, offeringPartyUsage0804Report2024Repository.count(), "初期入力1件");

        int houkokuNen = 2024;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0804Logic.practice(false,documentCode, documentlPropertyDto, new Shito0804Dto(),
                 CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0804Report2024Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("y2023/offering_party_usage_0804_report_2023.sql")
    void testPractice2023() {

        assertEquals(1L, offeringPartyUsage0804Report2023Repository.count(), "初期入力1件");

        int houkokuNen = 2023;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0804Logic.practice(false,documentCode, documentlPropertyDto, new Shito0804Dto(),
                 CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0804Report2023Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }

    // 追加位置

}
