package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CopyUsageWkTblIncomeY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("wktbl_usage_income_2024.sql")
class CopyUsageWkTblIncomeY2024LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyUsageWkTblIncomeY2024Logic copyUsageWkTblIncomeY2024Logic;

    /** 文書コード */
    private static final long documentCode = 1L;

    @Test
    void testSize() {
        assertEquals(4, copyUsageWkTblIncomeY2024Logic.practiceSize(documentCode));
    }

    @Test
    void test() {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<WkTblUsageIncomeEntity> list = copyUsageWkTblIncomeY2024Logic.practice(documentCode, 0, 10, privilegeDto);

        assertEquals(4, list.size());
        assertEquals(2L, list.get(1).getPartyUsage0802Kbn02ReportId());
    }

}
