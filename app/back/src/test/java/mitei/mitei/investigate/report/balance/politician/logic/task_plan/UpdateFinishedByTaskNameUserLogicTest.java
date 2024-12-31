package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdateFinishedByTaskNameUserLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateFinishedByTaskNameUserLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdateFinishedByTaskNameUserLogic updateFinishedByTaskNameUserLogic;

    /** タスク計画Repository */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2024.sql")
    void test() {

        assertEquals(4, taskPlan2024Repository.count(), "処理前4件");

        final int taskPlanCode = 250;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "不記載チェッカー";
        final int year = 2024;

        assertEquals(1, updateFinishedByTaskNameUserLogic.practice(taskPlanCode, privilegeDto, taskName, year), "正常処理");

        assertEquals(5, taskPlan2024Repository.count(), "処理前5件");

        List<TaskPlan2024Entity> list = taskPlan2024Repository
                .findByTaskPlanCodeAndInsertUserCodeAndTaskPlanNameAndSaishinKbn(taskPlanCode,
                        privilegeDto.getLoginUserCode(), taskName, DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, list.size(), "条件合致1件");

        TaskPlan2024Entity entity = list.get(0);

        assertNotEquals(255L, entity.getTaskPlanId(), "取得できたEntityは前のものと異なるid");
        assertEquals(true, entity.getIsFinished(), "終了フラグ");

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2024.sql")
    void testSuspended() {

        final int taskPlanCode = 0;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "";
        final int year = 2024;

        assertEquals(0, updateFinishedByTaskNameUserLogic.practice(taskPlanCode, privilegeDto, taskName, year),
                "更新する元データが不正");

    }

}
