package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
// 2022年
// 2025年
// 2023年
// import追加指定位置

/**
 * InsertTaskPlanSingleLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertTaskPlanSingleLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanSingleLogic insertTaskPlanSingleLogic;

    // テンプレート開始位置
    @Test
    @Tag("TableTruncate")  // NOPMD
    @Transactional
    @Sql("y2022/truncate_task_plan_2022.sql")
    void test2022() {

        // タスクリスト
        TaskInfoEntity taskEntity01 = new TaskInfoEntity();
        taskEntity01.setTaskInfoName("ダミータスク1"); // NOPMD
        taskEntity01.setMessageTemplate("【ユーザ名】さんが行った自動処理が完了したよ!。【遷移先】でまってます!"); // NOPMD
        taskEntity01.setTransferPass("http://localhost:8080/auto-exec"); // NOPMD
        taskEntity01.setParamQuery("?id=987&code=876"); // NOPMD
        taskEntity01.setTaskLevelList("1,4,5"); // NOPMD

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        assertEquals(1, insertTaskPlanSingleLogic.practice(2024, taskEntity01, privilegeDto), "全消去で項目1行目"); // NOPMD
    }
    // テンプレート終了位置

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2024/truncate_task_plan_2024.sql")
    void test2024() {

        // タスクリスト
        TaskInfoEntity taskEntity01 = new TaskInfoEntity();
        taskEntity01.setTaskInfoName("ダミータスク1");
        taskEntity01.setMessageTemplate("【ユーザ名】さんが行った自動処理が完了したよ!。【遷移先】でまってます!");
        taskEntity01.setTransferPass("http://localhost:8080/auto-exec");
        taskEntity01.setParamQuery("?id=987&code=876");
        taskEntity01.setTaskLevelList("1,4,5");

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        assertEquals(1, insertTaskPlanSingleLogic.practice(2024, taskEntity01, privilegeDto), "全消去で項目1行目");
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2025/truncate_task_plan_2025.sql")
    void test2025() {

        // タスクリスト
        TaskInfoEntity taskEntity01 = new TaskInfoEntity();
        taskEntity01.setTaskInfoName("ダミータスク1");
        taskEntity01.setMessageTemplate("【ユーザ名】さんが行った自動処理が完了したよ!。【遷移先】でまってます!");
        taskEntity01.setTransferPass("http://localhost:8080/auto-exec");
        taskEntity01.setParamQuery("?id=987&code=876");
        taskEntity01.setTaskLevelList("1,4,5");

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        assertEquals(1, insertTaskPlanSingleLogic.practice(2024, taskEntity01, privilegeDto), "全消去で項目1行目");
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2023/truncate_task_plan_2023.sql")
    void test2023() {

        // タスクリスト
        TaskInfoEntity taskEntity01 = new TaskInfoEntity();
        taskEntity01.setTaskInfoName("ダミータスク1");
        taskEntity01.setMessageTemplate("【ユーザ名】さんが行った自動処理が完了したよ!。【遷移先】でまってます!");
        taskEntity01.setTransferPass("http://localhost:8080/auto-exec");
        taskEntity01.setParamQuery("?id=987&code=876");
        taskEntity01.setTaskLevelList("1,4,5");

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        assertEquals(1, insertTaskPlanSingleLogic.practice(2024, taskEntity01, privilegeDto), "全消去で項目1行目");
    }

    // 追加位置

}
