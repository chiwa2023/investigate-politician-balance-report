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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CopyUsageWkTblIncomeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("../party_usage/y2022/wktbl_usage_income_2022.sql")
class CopyUsageWkTblIncomeLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyUsageWkTblIncomeLogic copyUsageWkTblIncomeLogic;

    /** 報告年 */
    private static final int houkokuNen = 2022;


    /** 文書コード */
    private static final long documentCode = 1L;

    @Test
    void testSize() {
        assertEquals(4, copyUsageWkTblIncomeLogic.practiceSize(houkokuNen,documentCode));
    }

    @Test
    void test() {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<WkTblUsageIncomeEntity> list = copyUsageWkTblIncomeLogic.practice(houkokuNen,documentCode, 0, 10, privilegeDto);

        assertEquals(4, list.size());
        assertEquals(2L, list.get(1).getPartyUsage0802Kbn02ReportId());
    }

}
