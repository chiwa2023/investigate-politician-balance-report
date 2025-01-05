package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetDifficalt0800Recipt2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetDifficalt0800Recipt2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetDifficalt0800Recipt2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetDifficalt0800Recipt2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetDifficalt0800Recipt2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheetDifficalt0800Recipt2023Repository;
// import追加指定位置

/**
 * UpdatePoliticalOrganization08000Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganization08000LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganization08000Logic updatePoliticalOrganization08000Logic;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2022Repository offeringBalancesheetDifficalt0800Recipt2022Repository;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2025Repository offeringBalancesheetDifficalt0800Recipt2025Repository;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2024Repository offeringBalancesheetDifficalt0800Recipt2024Repository;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2023Repository offeringBalancesheetDifficalt0800Recipt2023Repository;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    // テスト初期状態説明
    private static final String TEST_INIT_COUNT = "初期は1件"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("offering_balancesheet_difficalt_0800_recipt.sql")
    void testPractice() {

        int houkokuNen = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingBalancesheetDifficalt0800Recipt2022Entity entity2022Pre = offeringBalancesheetDifficalt0800Recipt2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode).get(0);
        assertThat(entity2022Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingBalancesheetDifficalt0800Recipt2025Entity entity2025Pre = offeringBalancesheetDifficalt0800Recipt2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode).get(0);
        assertThat(entity2025Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePoliticalOrganization08000Logic.practice(houkokuNen, documentCode, checkPrivilegeDto);

        // 2022年のテーブルデータは変更されました
        OfferingBalancesheetDifficalt0800Recipt2022Entity entity2022Pro = offeringBalancesheetDifficalt0800Recipt2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode).get(0);

        assertThat(entity2022Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        // 2025年のテーブルデータは変更されませんでした
        OfferingBalancesheetDifficalt0800Recipt2025Entity entity2025Pro = offeringBalancesheetDifficalt0800Recipt2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode).get(0);
        assertThat(entity2025Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_balancesheet_difficalt_0800_recipt_2022.sql")
    void testPractice2022() {

        assertEquals(1L, offeringBalancesheetDifficalt0800Recipt2022Repository.count(), TEST_INIT_COUNT);

        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_balancesheet_difficalt_0800_recipt_2024.sql")
    void testPractice2024() {

        assertEquals(1L, offeringBalancesheetDifficalt0800Recipt2024Repository.count(), TEST_INIT_COUNT);

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_balancesheet_difficalt_0800_recipt_2023.sql")
    void testPractice2023() {

        assertEquals(1L, offeringBalancesheetDifficalt0800Recipt2023Repository.count(), TEST_INIT_COUNT);

        fail("Not yet implemented");
    }

    // 追加位置

}
