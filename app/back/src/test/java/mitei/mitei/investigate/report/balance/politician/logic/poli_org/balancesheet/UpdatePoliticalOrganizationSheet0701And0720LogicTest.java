package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import static org.assertj.core.api.Assertions.assertThat;

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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePoliticalOrganizationSheet0701And0720Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganizationSheet0701And0720LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganizationSheet0701And0720Logic updatePoliticalOrganizationSheet0701And0720Logic;

    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2022Repository offeringBalancesheet0701And0720Surface2022Repository;

    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2025Repository offeringBalancesheet0701And0720Surface2025Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("offering_balancesheet_0701_and_0720_surface.sql")
    void testPractice() {

        int houkokuNen = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingBalancesheet0701And0720Surface2022Entity entity2022Pre = offeringBalancesheet0701And0720Surface2022Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        documentCode)
                .get(0);
        assertThat(entity2022Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingBalancesheet0701And0720Surface2025Entity entity2025Pre = offeringBalancesheet0701And0720Surface2025Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        documentCode)
                .get(0);
        assertThat(entity2025Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePoliticalOrganizationSheet0701And0720Logic.practice(houkokuNen, documentCode, checkPrivilegeDto);

        // 2022年のテーブルデータは変更されました
        OfferingBalancesheet0701And0720Surface2022Entity entity2022Pro = offeringBalancesheet0701And0720Surface2022Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        documentCode)
                .get(0);

        assertThat(entity2022Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        // 2025年のテーブルデータは変更されませんでした
        OfferingBalancesheet0701And0720Surface2025Entity entity2025Pro = offeringBalancesheet0701And0720Surface2025Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        documentCode)
                .get(0);
        assertThat(entity2025Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

    }

}
