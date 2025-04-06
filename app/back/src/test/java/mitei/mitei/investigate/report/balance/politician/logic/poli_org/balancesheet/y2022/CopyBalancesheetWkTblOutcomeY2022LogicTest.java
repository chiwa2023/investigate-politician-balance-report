package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblBalancesheetOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CopyBalancesheetWkTblOutcomeY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("wktbl_balancesheet_outcome_2022.sql")
class CopyBalancesheetWkTblOutcomeY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyBalancesheetWkTblOutcomeY2022Logic copyBalancesheetWkTblOutcomeY2022Logic;

    /** 文書コード */
    private static final long documentCode = 7L;

    @Test
    @Tag("TableTruncate")
    void testSize() {

        assertEquals(500, copyBalancesheetWkTblOutcomeY2022Logic.practiceSize(documentCode));
    }

    @Test
    @Tag("TableTruncate")
    void test() {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<WkTblBalancesheetOutcomeEntity> list = copyBalancesheetWkTblOutcomeY2022Logic.practice(documentCode, 0, 10,
                privilegeDto);

        assertEquals(10, list.size());
        assertEquals(2L, list.get(1).getOfferingBalancesheetOutcomeId());
    }

}
