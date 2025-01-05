package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

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

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0902Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0902Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0902Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0902Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0902Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0902Report2023Repository;
// import追加指定位置

/**
 * InsertPartyUsageShito0902Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0902LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0902Logic insertPartyUsageShito0902Logic;

    /** 様式その9その2テーブルRepository(2025) */
    @Autowired
    private OfferingPartyUsage0902Report2025Repository offeringPartyUsage0902Report2025Repository;

    /** 様式その9その2テーブルRepository(2022) */
    @Autowired
    private OfferingPartyUsage0902Report2022Repository offeringPartyUsage0902Report2022Repository;

    /** 様式その9その2テーブルRepository(2024) */
    @Autowired
    private OfferingPartyUsage0902Report2024Repository offeringPartyUsage0902Report2024Repository;

    /** 様式その9その2テーブルRepository(2023) */
    @Autowired
    private OfferingPartyUsage0902Report2023Repository offeringPartyUsage0902Report2023Repository;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    void testPractice20251() {

        Sheet0902Dto sheet1 = new Sheet0902Dto();
        sheet1.setItemName("事務所費1");
        sheet1.setDigest("適当な摘要1");

        Sheet0902Dto sheet2 = new Sheet0902Dto();
        sheet2.setItemName("事務所費2");
        sheet2.setDigest("適当な摘要2");

        Shito0902Dto shito0902Dto = new Shito0902Dto();
        shito0902Dto.getList().add(sheet1);
        shito0902Dto.getList().add(sheet2);

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

        int size = insertPartyUsageShito0902Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                shito0902Dto, CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0902Report2025Entity> listSearch = offeringPartyUsage0902Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0902ReportId(documentCode);

        // 入力した分だけリストが取れています
        assertThat(size).isEqualTo(shito0902Dto.getList().size());
        // 登録した分だけリストが取れています
        assertThat(listSearch.size()).isEqualTo(size);

        // 2件入力したので2件取れます
        OfferingPartyUsage0902Report2025Entity entity1 = listSearch.get(0);
        OfferingPartyUsage0902Report2025Entity entity2 = listSearch.get(1);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity1.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entity1.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entity1.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entity1.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity1.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entity1.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entity1.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entity1.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entity1.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity1.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity1.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity1.getItemName()).isEqualTo(sheet1.getItemName());
        assertThat(entity1.getDigest()).isEqualTo(sheet1.getDigest());

        assertThat(entity2.getItemName()).isEqualTo(sheet2.getItemName());
        assertThat(entity2.getDigest()).isEqualTo(sheet2.getDigest());

    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    void testPractice20221() {

        Sheet0902Dto sheet1 = new Sheet0902Dto();
        sheet1.setItemName("事務所費1");
        sheet1.setDigest("適当な摘要1");

        Sheet0902Dto sheet2 = new Sheet0902Dto();
        sheet2.setItemName("事務所費2");
        sheet2.setDigest("適当な摘要2");

        Shito0902Dto shito0902Dto = new Shito0902Dto();
        shito0902Dto.getList().add(sheet1);
        shito0902Dto.getList().add(sheet2);

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

        int size = insertPartyUsageShito0902Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                shito0902Dto, CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0902Report2022Entity> listSearch = offeringPartyUsage0902Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0902ReportId(documentCode);

        // 入力した分だけリストが取れています
        assertThat(size).isEqualTo(shito0902Dto.getList().size());
        // 登録した分だけリストが取れています
        assertThat(listSearch.size()).isEqualTo(size);

        // 2件入力したので2件取れます
        OfferingPartyUsage0902Report2022Entity entity1 = listSearch.get(0);
        OfferingPartyUsage0902Report2022Entity entity2 = listSearch.get(1);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity1.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entity1.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entity1.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entity1.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity1.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entity1.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entity1.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entity1.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entity1.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity1.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity1.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity1.getItemName()).isEqualTo(sheet1.getItemName());
        assertThat(entity1.getDigest()).isEqualTo(sheet1.getDigest());

        assertThat(entity2.getItemName()).isEqualTo(sheet2.getItemName());
        assertThat(entity2.getDigest()).isEqualTo(sheet2.getDigest());

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_party_usage_0902_report_2022.sql")
    void testPractice2022() {

        assertEquals(1L, offeringPartyUsage0902Report2022Repository.count(), "初期入力1件");

        int houkokuNen = 2022;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0902Logic.practice(documentCode, documentlPropertyDto, new Shito0902Dto(),
                CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0902Report2022Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_party_usage_0902_report_2024.sql")
    void testPractice2024() {

        assertEquals(1L, offeringPartyUsage0902Report2024Repository.count(), "初期入力1件");

        int houkokuNen = 2024;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0902Logic.practice(documentCode, documentlPropertyDto, new Shito0902Dto(),
                CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0902Report2024Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_party_usage_0902_report_2023.sql")
    void testPractice2023() {

        assertEquals(1L, offeringPartyUsage0902Report2023Repository.count(), "初期入力1件");

        int houkokuNen = 2023;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0902Logic.practice(documentCode, documentlPropertyDto, new Shito0902Dto(),
                CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0902Report2023Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }

    // 追加位置

}
