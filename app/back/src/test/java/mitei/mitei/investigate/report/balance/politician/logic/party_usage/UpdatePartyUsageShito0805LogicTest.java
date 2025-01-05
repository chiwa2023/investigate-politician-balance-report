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
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0805Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0805Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0805Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0805Report2023Repository;
// import追加指定位置

/**
 * UpdatePartyUsageShito0805Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsageShito0805LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsageShito0805Logic updatePartyUsageShito0805Logic;

    /** 様式8その5Repository(2022) */
    @Autowired
    private OfferingPartyUsage0805Report2022Repository offeringPartyUsage0805Report2022Repository;

    /** 様式8その5Repository(2025) */
    @Autowired
    private OfferingPartyUsage0805Report2025Repository offeringPartyUsage0805Report2025Repository;

    /** 様式8その5Repository(2024) */
    @Autowired
    private OfferingPartyUsage0805Report2024Repository offeringPartyUsage0805Report2024Repository;

    /** 様式8その5Repository(2023) */
    @Autowired
    private OfferingPartyUsage0805Report2023Repository offeringPartyUsage0805Report2023Repository;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("offering_party_usage_0805_report.sql")
    void testPractice() {

        int nendo = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingPartyUsage0805Report2022Entity entity2022Pre = offeringPartyUsage0805Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode).get(0);
        assertThat(entity2022Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingPartyUsage0805Report2025Entity entity2025Pre = offeringPartyUsage0805Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode).get(0);
        assertThat(entity2025Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePartyUsageShito0805Logic.practice(nendo, documentCode, checkPrivilegeDto);

        // 2022年のテーブルデータは変更されました
        OfferingPartyUsage0805Report2022Entity entity2022Pro = offeringPartyUsage0805Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode).get(0);

        assertThat(entity2022Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        // 2025年のテーブルデータは変更されませんでした
        OfferingPartyUsage0805Report2025Entity entity2025Pro = offeringPartyUsage0805Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode).get(0);
        assertThat(entity2025Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_party_usage_0805_report.sql")
    void testPractice2022() {

        assertEquals(1L, offeringPartyUsage0805Report2022Repository.count(), "初期入力1件");

        int houkokuNen = 2022;

        long documentCode = 3232L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        updatePartyUsageShito0805Logic.practice(houkokuNen, documentCode, CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(1L, offeringPartyUsage0805Report2022Repository.count(), "最新データの積み上げは別ロジックでしているので1件のまま");
        
        // TODO 指定文書同一識別コードのデータはすべて履歴

        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_party_usage_0805_report.sql")
    void testPractice2024() {

        assertEquals(1L, offeringPartyUsage0805Report2024Repository.count(), "初期入力1件");

        int houkokuNen = 2024;

        long documentCode = 3232L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        updatePartyUsageShito0805Logic.practice(houkokuNen, documentCode, CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(1L, offeringPartyUsage0805Report2024Repository.count(), "最新データの積み上げは別ロジックでしているので1件のまま");
        
        // TODO 指定文書同一識別コードのデータはすべて履歴

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_party_usage_0805_report.sql")
    void testPractice2023() {

        assertEquals(1L, offeringPartyUsage0805Report2023Repository.count(), "初期入力1件");

        int houkokuNen = 2023;

        long documentCode = 3232L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        updatePartyUsageShito0805Logic.practice(houkokuNen, documentCode, CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(1L, offeringPartyUsage0805Report2023Repository.count(), "最新データの積み上げは別ロジックでしているので1件のまま");
        
        // TODO 指定文書同一識別コードのデータはすべて履歴

        fail("Not yet implemented");
    }

    // 追加位置

}
