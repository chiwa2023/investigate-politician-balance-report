package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CopyBalancesheetWkTblIncomeY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("wktbl_balancesheet_income_2022.sql")
class CopyBalancesheetWkTblIncomeY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyBalancesheetWkTblIncomeY2022Logic copyBalancesheetWkTblIncomeY2022Logic;

    /** 文書コード */
    private static final long documentCode = 7L;

    @Test
    void testSize() {

        assertEquals(33, copyBalancesheetWkTblIncomeY2022Logic.practiceSize(documentCode));
    }

    @Test
    void test() {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<WkTblBalancesheetIncomeEntity> list = copyBalancesheetWkTblIncomeY2022Logic.practice(documentCode, 0, 10,
                privilegeDto);

        assertEquals(10, list.size());
        assertEquals(2L, list.get(1).getOfferingBalancesheetIncomeId());

    }

}
