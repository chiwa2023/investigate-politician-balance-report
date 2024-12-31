package mitei.mitei.investigate.report.balance.politician.service.task_plan;


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
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * RegistTaskPlanNoAlertPersonalService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistTaskPlanNoAlertPersonalServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistTaskPlanNoAlertPersonalService registTaskPlanNoAlertPersonalService;

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "erase_task_plan_2024.sql", "task_info.sql" })
    void test() {
        final String taskName = "不記載チェッカー";

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<TaskPlan2024Entity> listPre = taskPlan2024Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPre.size(), "初期状態は1件");

        assertEquals(260, registTaskPlanNoAlertPersonalService.practice(privilegeDto, taskName,2024), "次データの同一識別コード");

        List<TaskPlan2024Entity> listPro = taskPlan2024Repository.findByInsertUserCodeAndTaskPlanNameInAndSaishinKbn(
                privilegeDto.getLoginUserCode(), Collections.singletonList(taskName),
                DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listPro.size(), "更新後も1件");

        List<TaskPlan2024Entity> listCode = taskPlan2024Repository
                .findByTaskPlanCodeAndInsertUserCodeAndTaskPlanNameAndSaishinKbn(260, privilegeDto.getLoginUserCode(),
                        taskName, DataHistoryStatusConstants.INSERT.value());

        assertEquals(1, listCode.size(), "初期状態は1件");

        TaskPlan2024Entity entityInsert = listCode.get(0);

        assertEquals(1, entityInsert.getSaishinKbn(), "最新状態");
        assertEquals(false, entityInsert.getIsFinished(), "未終了");

    }
}
