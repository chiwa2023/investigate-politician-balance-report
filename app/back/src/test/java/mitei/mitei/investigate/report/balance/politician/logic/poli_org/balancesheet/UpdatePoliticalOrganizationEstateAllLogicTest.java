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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0718Estate2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0719RealEstate2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0718Estate2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0719RealEstate2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0718Estate2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0719RealEstate2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0718Estate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0719RealEstate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheet0718Estate2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheet0719RealEstate2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheet0718Estate2023Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheet0719RealEstate2023Repository;
// import追加指定位置

/**
 * UpdatePoliticalOrganizationEstateAllLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganizationEstateAllLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllLogic updatePoliticalOrganizationEstateAllLogic;

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2022Repository offeringBalancesheet0718Estate2022Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2022Repository offeringBalancesheet0719RealEstate2022Repository;

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2025Repository offeringBalancesheet0718Estate2025Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2025Repository offeringBalancesheet0719RealEstate2025Repository;

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2024Repository offeringBalancesheet0718Estate2024Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2024Repository offeringBalancesheet0719RealEstate2024Repository;

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2023Repository offeringBalancesheet0718Estate2023Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2023Repository offeringBalancesheet0719RealEstate2023Repository;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    // テスト初期状態説明
    private static final String TEST_INIT_COUNT = "初期は1件"; // NOPMD

    @Test
    @Tag(TEST_TAG)
    @Transactional
    @Sql("offering_balancesheet_0718_estate.sql")
    void testPractice() {

        int houkokuNen = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingBalancesheet0718Estate2022Entity entity202218Pre = offeringBalancesheet0718Estate2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(documentCode).get(0);
        assertThat(entity202218Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingBalancesheet0718Estate2025Entity entity202518Pre = offeringBalancesheet0718Estate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(documentCode).get(0);
        assertThat(entity202518Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 2022年、2025年とも最新状態です
        OfferingBalancesheet0719RealEstate2022Entity entity202219Pre = offeringBalancesheet0719RealEstate2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(documentCode).get(0);
        assertThat(entity202219Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingBalancesheet0719RealEstate2025Entity entity202519Pre = offeringBalancesheet0719RealEstate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(documentCode).get(0);
        assertThat(entity202519Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePoliticalOrganizationEstateAllLogic.practice(houkokuNen, documentCode, checkPrivilegeDto);

        OfferingBalancesheet0718Estate2022Entity entity202218Pro = offeringBalancesheet0718Estate2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(documentCode).get(0);
        assertThat(entity202218Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        OfferingBalancesheet0718Estate2025Entity entity202518Pro = offeringBalancesheet0718Estate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(documentCode).get(0);
        assertThat(entity202518Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 2022年、2025年とも最新状態です
        OfferingBalancesheet0719RealEstate2022Entity entity202219Pro = offeringBalancesheet0719RealEstate2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(documentCode).get(0);
        assertThat(entity202219Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        OfferingBalancesheet0719RealEstate2025Entity entity202519Pro = offeringBalancesheet0719RealEstate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(documentCode).get(0);
        assertThat(entity202519Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql({"y2022/offering_balancesheet_0718_estate_2022.sql","offering_balancesheet_0719_real_estate_2022.sql"})
    void testPractice2022() {
        
        assertEquals(1L , offeringBalancesheet0718Estate2022Repository.count(),TEST_INIT_COUNT);
        assertEquals(1L , offeringBalancesheet0719RealEstate2022Repository.count(),TEST_INIT_COUNT);
        
        fail("Not yet implemented");
    }
    // テンプレート終了位置


    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql({"y2024/offering_balancesheet_0718_estate_2024.sql","offering_balancesheet_0719_real_estate_2024.sql"})
    void testPractice2024() {
        
        assertEquals(1L , offeringBalancesheet0718Estate2024Repository.count(),TEST_INIT_COUNT);
        assertEquals(1L , offeringBalancesheet0719RealEstate2024Repository.count(),TEST_INIT_COUNT);

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql({"y2023/offering_balancesheet_0718_estate_2023.sql","offering_balancesheet_0719_real_estate_2023.sql"})
    void testPractice2023() {
        
        assertEquals(1L , offeringBalancesheet0718Estate2023Repository.count(),TEST_INIT_COUNT);
        assertEquals(1L , offeringBalancesheet0719RealEstate2023Repository.count(),TEST_INIT_COUNT);
        
        fail("Not yet implemented");
    }

    // 追加位置

}
