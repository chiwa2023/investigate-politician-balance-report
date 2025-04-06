package mitei.mitei.investigate.report.balance.politician.logic.political_party;

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
 * CopyUsageWkTblOutcomeLogicTest単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("../party_usage/y2022/wktbl_usage_outcome_2022.sql")
class CopyUsageWkTblOutcomeLogicTest {
    // CHECKSTYLE:OFF

    /** 単体テスト */
    @Autowired
    private CopyUsageWkTblOutcomeLogic copyUsageWkTblOutcomeLogic;

    /** 報告年 */
    private static final int houkokuNen = 2022;

    /** 文書コード */
    private static final long documentCode = 1L;

    @Test
    void testSize() {
        assertEquals(203, copyUsageWkTblOutcomeLogic.practiceSize(houkokuNen, documentCode));
    }

    @Test
    void test() {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<WkTblUsageOutcomeEntity> list = copyUsageWkTblOutcomeLogic.practice(houkokuNen, documentCode, 0, 10,
                privilegeDto);

        assertEquals(10, list.size());
        assertEquals(2L, list.get(1).getPartyUsage0804ReportId());
    }

}
