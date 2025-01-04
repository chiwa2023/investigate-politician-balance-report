package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025;

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
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * EraseWkTblPersonalTaskY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblPersonalTaskY2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblPersonalTaskY2025Logic eraseWkTblPersonalTaskY2025Logic;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("erase_task_plan_2025.sql")
    void test() {

        assertEquals(4L, taskPlan2025Repository.count(), "最初は4件");

        final String taskName = "不記載チェッカー";
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<TaskPlan2025Entity> listPre = taskPlan2025Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPre.size(), "初期状態は1件");

        assertEquals(1, eraseWkTblPersonalTaskY2025Logic.practice(Collections.singletonList(taskName), privilegeDto),
                "1件更新");

        List<TaskPlan2025Entity> listPro = taskPlan2025Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(0, listPro.size(), "変更後0件");

    }

}
