package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

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
 * CheckAllreadyRegistDataLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckAllreadyRegistDataLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckAllreadyRegistDataLogic checkAllreadyRegistDataLogic;

    @Test
    @Transactional
    @Sql("check_allready_regist_data.sql")
    void testPractice() {

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto1 = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto1.setHoukokuNen(2022);
        documentPropertyDto1.setOfferingDate(LocalDate.of(2023, 3, 21));
        documentPropertyDto1.setPoliticalOrganizationId(437L);
        documentPropertyDto1.setPoliticalOrganizationCode(430);

        List<Long> list1 = checkAllreadyRegistDataLogic.practice(documentPropertyDto1);

        // 2022年のデータが抽出される
        assertTrue(list1.contains(320L), "コード320は更新対象に含まれる");
        assertTrue(list1.contains(479L), "コード479は更新対象に含まれる");
        assertFalse(list1.contains(881L), "コード881は更新対象に含まれない(提出日)");
        assertFalse(list1.contains(645L), "コード645は更新対象に含まれない(政治団体同一識別コード)");
        assertTrue(list1.contains(217L), "コード217は更新対象に含まれる");
        assertTrue(list1.contains(836L), "コード836は更新対象に含まれる");

        // その他の報告年、2025年テーブルからは一切抽出されない
        assertFalse(list1.contains(1020L), "コード1020は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1021L), "コード1021は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1022L), "コード1022は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1023L), "コード1023は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1024L), "コード1024は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1025L), "コード1025は更新対象に含まれない(テーブル違い)");

        /* 文書属性Dtoの報告年によって登録テーブルが切り替わること */

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto2 = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto2.setHoukokuNen(2025);
        documentPropertyDto2.setOfferingDate(LocalDate.of(2023, 3, 21));
        documentPropertyDto2.setPoliticalOrganizationId(437L);
        documentPropertyDto2.setPoliticalOrganizationCode(430);

        List<Long> list2 = checkAllreadyRegistDataLogic.practice(documentPropertyDto2);

        // 2025年のデータが抽出される
        assertTrue(list2.contains(1020L), "コード1020は更新対象に含まれる");
        assertTrue(list2.contains(1021L), "コード1021は更新対象に含まれる");
        assertTrue(list2.contains(1022L), "コード1022は更新対象に含まれない(提出日)");
        assertTrue(list2.contains(1023L), "コード1023は更新対象に含まれない(政治団体同一識別コード)");
        assertTrue(list2.contains(1024L), "コード1024は更新対象に含まれる");
        assertTrue(list2.contains(1025L), "コード1025は更新対象に含まれる");

        // その他の報告年、2022年テーブルからは一切抽出されない
        assertFalse(list2.contains(320L), "コード320は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(479L), "コード479は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(881L), "コード881は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(645L), "コード645は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(217L), "コード217は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(836L), "コード836は更新対象に含まれない(テーブル違い)");

    }

}
