package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

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

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;

/**
 * CheckAllreadyRegistDataY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckAllreadyRegistDataPoliticalOrganizationY2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckAllreadyRegistDataPoliticalOrganizationY2025Logic checkAllreadyRegistDataPoliticalOrganizationY2025Logic;

    @Test
    @Transactional
    @Sql("check_allready_regist_data_2025.sql")
    void testPractice() {

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2025);
        documentPropertyDto.setOfferingDate(LocalDate.of(2023, 3, 21));
        documentPropertyDto.setPoliticalOrganizationId(437L);
        documentPropertyDto.setPoliticalOrganizationCode(430);

        List<Long> list = checkAllreadyRegistDataPoliticalOrganizationY2025Logic.practice(documentPropertyDto);

        // テストデータで挿入される文書同一識別コードは320,479,881,645,217,836
        // 881は提出日違いで更新対象から除外される
        // 645は政治団体Id違いで更新対象から除外される
        assertTrue(list.contains(320L), "コード320は更新対象に含まれる");
        assertTrue(list.contains(479L), "コード479は更新対象に含まれる");
        assertFalse(list.contains(881L), "コード881は更新対象に含まれない(提出日)");
        assertFalse(list.contains(645L), "コード645は更新対象に含まれない(政治団体同一識別コード)");
        assertTrue(list.contains(217L), "コード217は更新対象に含まれる");
        assertTrue(list.contains(836L), "コード836は更新対象に含まれる");
    }

}
