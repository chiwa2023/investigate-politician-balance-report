package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2024Repository;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2022Repository;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2025Repository;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2023Repository;
// import追加指定位置

/**
 * EraseWkTblPersonalTaskLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblPersonalTaskLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblPersonalTaskLogic eraseWkTblPersonalTaskLogic;

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    /** タスク計画Repository(2022) */
    @Autowired
    private TaskPlan2022Repository taskPlan2022Repository;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /** タスク計画Repository(2023) */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

    @Test
    @Tag("TableTruncate") // NOPMD
    @Transactional
    @Sql("y2024/erase_task_plan_2024.sql")
    void test2024() {

        assertEquals(4L, taskPlan2024Repository.count(), "最初は4件"); // NOPMD

        final String taskName = "不記載チェッカー"; // NOPMD
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<TaskPlan2024Entity> listPre = taskPlan2024Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPre.size(), "初期状態は1件"); // NOPMD

        assertEquals(1, eraseWkTblPersonalTaskLogic.practice(2024, Collections.singletonList(taskName), privilegeDto),
                "1件更新"); // NOPMD

        List<TaskPlan2024Entity> listPro = taskPlan2024Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(0, listPro.size(), "変更後0件"); // NOPMD

    }

    // テンプレート開始位置
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2022/erase_task_plan_2022.sql")
    void test2022() {

        assertEquals(4L, taskPlan2024Repository.count(), "最初は4件");

        final String taskName = "不記載チェッカー";
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<TaskPlan2022Entity> listPre = taskPlan2022Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPre.size(), "初期状態は1件");

        assertEquals(1, eraseWkTblPersonalTaskLogic.practice(2022, Collections.singletonList(taskName), privilegeDto),
                "1件更新");

        List<TaskPlan2022Entity> listPro = taskPlan2022Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(0, listPro.size(), "変更後0件");

    }
    // テンプレート終了位置

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2025/erase_task_plan_2025.sql")
    void test2025() {

        assertEquals(4L, taskPlan2024Repository.count(), "最初は4件");

        final String taskName = "不記載チェッカー";
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<TaskPlan2025Entity> listPre = taskPlan2025Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPre.size(), "初期状態は1件");

        assertEquals(1, eraseWkTblPersonalTaskLogic.practice(2025, Collections.singletonList(taskName), privilegeDto),
                "1件更新");

        List<TaskPlan2025Entity> listPro = taskPlan2025Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(0, listPro.size(), "変更後0件");

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2023/erase_task_plan_2023.sql")
    void test2023() {

        assertEquals(4L, taskPlan2024Repository.count(), "最初は4件");

        final String taskName = "不記載チェッカー";
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<TaskPlan2023Entity> listPre = taskPlan2023Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPre.size(), "初期状態は1件");

        assertEquals(1, eraseWkTblPersonalTaskLogic.practice(2023, Collections.singletonList(taskName), privilegeDto),
                "1件更新");

        List<TaskPlan2023Entity> listPro = taskPlan2023Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(0, listPro.size(), "変更後0件");

    }

// 追加位置
}
