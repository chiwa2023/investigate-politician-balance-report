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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheet0702And0713And0717Summary2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheet0702And0713And0717Summary2023Repository;
// import追加指定位置

/**
 * UpdatePoliticalOrganizationSummaryLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganizationSummaryLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganizationSummaryLogic updatePoliticalOrganizationSummaryLogic;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2022Repository offeringBalancesheet0702And0713And0717Summary2022Repository;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2025Repository offeringBalancesheet0702And0713And0717Summary2025Repository;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2024Repository offeringBalancesheet0702And0713And0717Summary2024Repository;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2023Repository offeringBalancesheet0702And0713And0717Summary2023Repository;
    
    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    // テスト初期状態説明
    private static final String TEST_INIT_COUNT = "初期は1件"; // NOPMD

    @Test
    @Tag(TEST_TAG)
    @Transactional
    @Sql("offering_balancesheet_0702_and_0713_and_0717_summary.sql")
    void testPractice() {

        int houkokuNen = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingBalancesheet0702And0713And0717Summary2022Entity entity2022Pre = offeringBalancesheet0702And0713And0717Summary2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(documentCode).get(0);
        assertThat(entity2022Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingBalancesheet0702And0713And0717Summary2025Entity entity2025Pre = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(documentCode).get(0);
        assertThat(entity2025Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePoliticalOrganizationSummaryLogic.practice(houkokuNen, documentCode, checkPrivilegeDto);

        // 2022年のテーブルデータは変更されました
        OfferingBalancesheet0702And0713And0717Summary2022Entity entity2022Pro = offeringBalancesheet0702And0713And0717Summary2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(documentCode).get(0);

        assertThat(entity2022Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        // 2025年のテーブルデータは変更されませんでした
        OfferingBalancesheet0702And0713And0717Summary2025Entity entity2025Pro = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(documentCode).get(0);
        assertThat(entity2025Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
    }

    
    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/offering_balancesheet_0702_and_0713_and_0717_summary_2022.sql")
    void testPractice2022() {
        
        
        assertEquals(1L , offeringBalancesheet0702And0713And0717Summary2022Repository.count(),TEST_INIT_COUNT);

        fail("Not yet implemented");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/offering_balancesheet_0702_and_0713_and_0717_summary_2024.sql")
    void testPractice2024() {
        
        assertEquals(1L , offeringBalancesheet0702And0713And0717Summary2024Repository.count(),TEST_INIT_COUNT);

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2023/offering_balancesheet_0702_and_0713_and_0717_summary_2023.sql")
    void testPractice2023() {
        
        
        assertEquals(1L , offeringBalancesheet0702And0713And0717Summary2023Repository.count(),TEST_INIT_COUNT);

        fail("Not yet implemented");
    }

    // 追加位置

}
