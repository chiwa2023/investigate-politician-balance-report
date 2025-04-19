package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CopyUsageWkTbOutncomeY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("wktbl_usage_outcome_2025.sql")
class CopyUsageWkTbOutncomeY2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyUsageWkTbOutncomeY2025Logic copyUsageWkTbOutncomeY2025Logic;

    /** 文書コード */
    private static final long documentCode = 1L;

    @Test
    void testSize() {
        assertEquals(203, copyUsageWkTbOutncomeY2025Logic.practiceSize(documentCode));
    }

    @Test
    void test() {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<WkTblUsageOutcomeEntity> list = copyUsageWkTbOutncomeY2025Logic.practice(documentCode, 0, 10,
                privilegeDto);

        assertEquals(10, list.size());
        assertEquals(2L, list.get(1).getPartyUsage0804ReportId());
    }

}
