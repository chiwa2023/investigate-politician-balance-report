package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CallTaskPlanByCodeUserLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CallTaskPlanByCodeUserLogicTest {

    /** テスト対象 */
    @Autowired
    private CallTaskPlanByCodeUserLogic callTaskPlanByCodeUserLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2024.sql")
    void test() {

        final int taskPlanCode = 250;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "不記載チェッカー";
        final int year = 2024;

        assertEquals(true,
                callTaskPlanByCodeUserLogic.practice(taskPlanCode, privilegeDto.getLoginUserCode(), taskName, year),
                "正常処理");
    }

}
