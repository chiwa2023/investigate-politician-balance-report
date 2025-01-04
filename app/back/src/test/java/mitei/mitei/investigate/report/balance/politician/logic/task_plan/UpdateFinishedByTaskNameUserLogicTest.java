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
// 2022年
// 2025年
// 2023年
// import追加指定位置

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
    @Tag("TableTruncate") // NOPMD
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

    // テンプレート開始位置
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2022.sql")
    void testSuspended2022() {

        final int taskPlanCode = 0;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "";
        final int year = 2022;

        assertEquals(0, updateFinishedByTaskNameUserLogic.practice(taskPlanCode, privilegeDto, taskName, year),
                "更新する元データが不正"); // NOPMD
    }
    // テンプレート終了位置

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2024.sql")
    void testSuspended2024() {

        final int taskPlanCode = 0;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "";
        final int year = 2024;

        assertEquals(0, updateFinishedByTaskNameUserLogic.practice(taskPlanCode, privilegeDto, taskName, year),
                "更新する元データが不正");

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2025.sql")
    void testSuspended2025() {

        final int taskPlanCode = 0;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "";
        final int year = 2025;

        assertEquals(0, updateFinishedByTaskNameUserLogic.practice(taskPlanCode, privilegeDto, taskName, year),
                "更新する元データが不正");
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2023.sql")
    void testSuspended2023() {

        final int taskPlanCode = 0;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        final String taskName = "";
        final int year = 2023;

        assertEquals(0, updateFinishedByTaskNameUserLogic.practice(taskPlanCode, privilegeDto, taskName, year),
                "更新する元データが不正");
    }

// 追加位置

}
