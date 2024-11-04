package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertTaskPlanY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertTaskPlanY2024LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanY2024Logic insertTaskPlanY2024Logic;

    /** タスク計画Repository(2024年) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    @Test
    @Transactional
    @Sql("truncate_task_plan_2024.sql")
    void testPractice() {


        // タスクリスト
        TaskInfoEntity taskEntity01 = new TaskInfoEntity();
        taskEntity01.setTaskInfoName("ダミータスク1");
        taskEntity01.setMessageTemplate("【ユーザ名】さんが行った自動処理が完了したよ!。【遷移先】でまってます!");
        taskEntity01.setTransferPass("http://localhost:8080/auto-exec");
        taskEntity01.setParamQuery("?id=987&code=876");
        taskEntity01.setTaskLevelList("1,4,5");

        TaskInfoEntity taskEntity02 = new TaskInfoEntity();
        taskEntity02.setTaskInfoName("ダミータスク2");
        taskEntity02.setMessageTemplate("【ユーザ名】さんが行った操作で処理すべき新たなタスクが発生しました。【遷移先】にアクセスしてタスクの処理を行ってください");
        taskEntity02.setTransferPass("http://localhost:8080/dummy-page");
        taskEntity02.setParamQuery("?id=1&code=2");
        taskEntity02.setTaskLevelList("2,8");

        List<TaskInfoEntity> list = new ArrayList<>();
        list.add(taskEntity01);
        list.add(taskEntity02);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        int size = insertTaskPlanY2024Logic.practice(privilegeDto, list);

        assertThat(size).isEqualTo(2); // 2件登録しようとしたのでそのまま登録

        List<TaskPlan2024Entity> listTask = taskPlan2024Repository.findAll();

        listTask.sort((e1, e2) -> e1.getTaskPlanCode() - e2.getTaskPlanCode());
        TaskPlan2024Entity planEntity00 = listTask.get(0);
        
        assertFalse(planEntity00.getIsFinished(),"登録時点では作業終了ではない");
        assertThat(planEntity00.getTaskPlanName()).isEqualTo("ダミータスク1");
        assertThat(planEntity00.getTransferPass()).isEqualTo("http://localhost:8080/auto-exec?id=987&code=876");

        assertThat(planEntity00.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(planEntity00.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(planEntity00.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(planEntity00.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

        TaskPlan2024Entity planEntity01 = listTask.get(1);
        
        assertFalse(planEntity01.getIsFinished(),"登録時点では作業終了ではない");
        assertThat(planEntity01.getTaskPlanName()).isEqualTo("ダミータスク2");
        assertThat(planEntity01.getTransferPass()).isEqualTo("http://localhost:8080/dummy-page?id=1&code=2");

    }

}
