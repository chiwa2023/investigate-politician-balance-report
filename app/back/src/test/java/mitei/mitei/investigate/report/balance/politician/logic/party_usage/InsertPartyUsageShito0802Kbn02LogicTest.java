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

import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080202Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0802Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0802Kbn02Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0802Kbn02Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0802Kbn02Report2023Repository;
// import追加指定位置

/**
 * InsertPartyUsageShito0802Kbn02Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0802Kbn02LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0802Kbn02Logic insertPartyUsageShito0802Kbn02Logic;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2025Repository offeringPartyUsage0802Kbn02Report2025Repository;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2022Repository offeringPartyUsage0802Kbn02Report2022Repository;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2024Repository offeringPartyUsage0802Kbn02Report2024Repository;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2023Repository offeringPartyUsage0802Kbn02Report2023Repository;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    void testPractice() {

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

        Kbn080202Dto kbn1 = new Kbn080202Dto();
        kbn1.setTotalAmount(200000L); // NOPMD

        RowShito0802Dto row0 = new RowShito0802Dto();
        row0.setRowNo(3);
        row0.setItemName("項目名称");
        row0.setAccrualDate("R4/12/1");
        row0.setAmount(65432L); // NOPMD
        kbn1.getList().add(row0);

        int size = insertPartyUsageShito0802Kbn02Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                kbn1, CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0802Kbn02Report2025Entity> list = offeringPartyUsage0802Kbn02Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(documentCode);

        assertThat(size).isEqualTo(1);// 1行しか登録していません
        assertThat(list.size()).isEqualTo(size);// 設定と登録が一致

        OfferingPartyUsage0802Kbn02Report2025Entity entity1 = list.get(0);

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

        // kbn1.setTotalAmount(200000L);
        assertThat(entity1.getTotalAmount()).isEqualTo(kbn1.getTotalAmount());
        // row0.setRowNo(3);
        assertThat(entity1.getRowNo()).isEqualTo(row0.getRowNo());
        // row0.setItemName("項目名称");
        assertThat(entity1.getItemName()).isEqualTo(row0.getItemName());
        // row0.setAccrualDate("R4/12/1");
        assertThat(entity1.getAccrualDate()).isEqualTo(row0.getAccrualDate());
        // row0.setAmount(65432L);
        assertThat(entity1.getAmount()).isEqualTo(row0.getAmount());

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_party_usage_0802_kbn_02_report_2022.sql")
    void testPractice2022() {

        assertEquals(1L, offeringPartyUsage0802Kbn02Report2022Repository.count(), "初期入力1件");

        int houkokuNen = 2022;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0802Kbn02Logic.practice(documentCode, documentlPropertyDto,
                new Shito0802Dto().getSheet0802Dto().getKbn080202Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0802Kbn02Report2022Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_party_usage_0802_kbn_02_report_2024.sql")
    void testPractice2024() {

        assertEquals(1L, offeringPartyUsage0802Kbn02Report2024Repository.count(), "初期入力1件");

        int houkokuNen = 2024;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0802Kbn02Logic.practice(documentCode, documentlPropertyDto,
                new Shito0802Dto().getSheet0802Dto().getKbn080202Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0802Kbn02Report2024Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_party_usage_0802_kbn_02_report_2023.sql")
    void testPractice2023() {

        assertEquals(1L, offeringPartyUsage0802Kbn02Report2023Repository.count(), "初期入力1件");

        int houkokuNen = 2023;

        long documentCode = 100L;
        PartyUsageDocumentPoliticalPropertyDto documentlPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentlPropertyDto.setNendo(houkokuNen); // 実際には表紙からコピー

        insertPartyUsageShito0802Kbn02Logic.practice(documentCode, documentlPropertyDto,
                new Shito0802Dto().getSheet0802Dto().getKbn080202Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        assertEquals(2L, offeringPartyUsage0802Kbn02Report2023Repository.count(), "追加で1件");

        fail("Not yet implemented");
    }

    // 追加位置

}
