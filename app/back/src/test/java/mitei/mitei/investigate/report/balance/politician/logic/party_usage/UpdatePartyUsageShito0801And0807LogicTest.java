package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0801And0807Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0801And0807Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0801And0807Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0801And0807Report2023Repository;
// import追加指定位置

/**
 * UpdatePartyUsageShito0801And0807Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsageShito0801And0807LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsageShito0801And0807Logic updatePartyUsageShito0801And0807Logic;

    /** 政党交付金使途報告書表紙と宣誓書Repository(2022) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2022Repository offeringPartyUsage0801And0807Report2022Repository;

    /** 政党交付金使途報告書表紙と宣誓書Repository(2025) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2025Repository offeringPartyUsage0801And0807Report2025Repository;

    /** 政党交付金使途報告書表紙と宣誓書Repository(2024) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2024Repository offeringPartyUsage0801And0807Report2024Repository;

    /** 政党交付金使途報告書Repository(2023) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2023Repository offeringPartyUsage0801And0807Report2023Repository;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD
    
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("offering_party_usage_0801_and_0807_report.sql")
    void testPractice() {

        int nendo = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingPartyUsage0801And0807Report2022Entity entity2022Pre = offeringPartyUsage0801And0807Report2022Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(documentCode).get(0);
        assertThat(entity2022Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingPartyUsage0801And0807Report2025Entity entity2025Pre = offeringPartyUsage0801And0807Report2025Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(documentCode).get(0);
        assertThat(entity2025Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePartyUsageShito0801And0807Logic.practice(nendo, documentCode, checkPrivilegeDto);

        // 2022年のテーブルデータは変更されました
        OfferingPartyUsage0801And0807Report2022Entity entity2022Pro = offeringPartyUsage0801And0807Report2022Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(documentCode).get(0);

        assertThat(entity2022Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        // 2025年のテーブルデータは変更されませんでした
        OfferingPartyUsage0801And0807Report2025Entity entity2025Pro = offeringPartyUsage0801And0807Report2025Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(documentCode).get(0);
        assertThat(entity2025Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_party_usage_0801_and_0807_report.sql")
    void testPractice2022() {

        assertEquals(1L, offeringPartyUsage0801And0807Report2022Repository.count(), "初期入力1件");

        int houkokuNen = 2022;

        long documentCode = 3232L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        updatePartyUsageShito0801And0807Logic.practice(houkokuNen, documentCode, CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(1L, offeringPartyUsage0801And0807Report2022Repository.count(), "最新データの積み上げは別ロジックでしているので1件のまま");
        
        // TODO 指定文書同一識別コードのデータはすべて履歴
        
        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_party_usage_0801_and_0807_report.sql")
    void testPractice2024() {

        assertEquals(1L, offeringPartyUsage0801And0807Report2024Repository.count(), "初期入力1件");

        int houkokuNen = 2024;

        long documentCode = 3232L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        updatePartyUsageShito0801And0807Logic.practice(houkokuNen, documentCode, CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(1L, offeringPartyUsage0801And0807Report2024Repository.count(), "最新データの積み上げは別ロジックでしているので1件のまま");
        
        // TODO 指定文書同一識別コードのデータはすべて履歴
        
        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_party_usage_0801_and_0807_report.sql")
    void testPractice2023() {

        assertEquals(1L, offeringPartyUsage0801And0807Report2023Repository.count(), "初期入力1件");

        int houkokuNen = 2023;

        long documentCode = 3232L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        updatePartyUsageShito0801And0807Logic.practice(houkokuNen, documentCode, CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(1L, offeringPartyUsage0801And0807Report2023Repository.count(), "最新データの積み上げは別ロジックでしているので1件のまま");
        
        // TODO 指定文書同一識別コードのデータはすべて履歴
        
        fail("Not yet implemented");
    }

    // 追加位置

}
