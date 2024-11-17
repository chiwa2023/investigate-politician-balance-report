package mitei.mitei.investigate.report.balance.politician.service.task_plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import mitei.mitei.investigate.report.balance.politician.dto.task_plan.RegistTaskPlanResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.mail.SendAlertMail2024Entity;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2024Entity;
import mitei.mitei.investigate.report.balance.politician.logic.task_info.CallTaskInfoLogic;
import mitei.mitei.investigate.report.balance.politician.repository.mail.SendAlertMail2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * RegistTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistTaskPlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistTaskPlanService registTaskPlanService;

    /** タスク呼び出しLogic */
    @Autowired
    private CallTaskInfoLogic callTaskInfoLogic;

    /** メール送信予定Repository(2024年) */
    @Autowired
    private SendAlertMail2024Repository sendAlertMail2024Repository;

    /** タスク計画Repository(2024年) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "task_info.sql", "user_web_access.sql" })
    void testPractice() throws Exception { // NOPMD

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        LocalDateTime datetimeShori = LocalDateTime.of(2024, 7, 21, 12, 34, 12);

        // TODO リスト入手
        // 該当のタスク情報を呼び出す
        List<String> listName = new ArrayList<>();
        listName.add("政治資金収支報告書一括登録準備");
        listName.add("政治資金収支報告書一括登録処理結果");
        List<TaskInfoEntity> listTask = callTaskInfoLogic.practice(listName);

        RegistTaskPlanResultDto resultDto = registTaskPlanService.practice(privilegeDto, datetimeShori, listTask);

        assertTrue(resultDto.getIsOk(), "登録成功");

        // 通知予定詳細
        List<SendAlertMail2024Entity> listMail = sendAlertMail2024Repository.findAll();
        assertThat(listMail.size()).isEqualTo(2); // BCCを使うので登録タスクと同じ数

        SendAlertMail2024Entity mailEntity00 = listMail.get(0);

        assertThat(mailEntity00.getBccMail()).isEqualTo("200mail@1234.com,500mail@1235.com,400mail@1235.com");
        assertThat(mailEntity00.getBodyTextMail())
                .isEqualTo("事務担当者　花子さんが行った自動処理が完了したよ!。http://localhost:8080/auto-exec?id=987&code=876でまってます!");
        assertThat(mailEntity00.getCcMail()).isEqualTo("");
        assertThat(mailEntity00.getFromMail()).isEqualTo("500mail@1235.com");// NOPMD
        assertThat(mailEntity00.getIsRepeat()).isEqualTo(false);
        assertThat(mailEntity00.getReplyToMail()).isEqualTo("500mail@1235.com");
        assertThat(mailEntity00.getSubjectMail()).isEqualTo("政治資金収支報告書一括登録準備");
        assertThat(mailEntity00.getToMail()).isEqualTo("500mail@1235.com"); // NOPMD
        assertThat(mailEntity00.getSendUserId()).isEqualTo(123_321L);
        assertThat(mailEntity00.getSendUserCode()).isEqualTo(987);
        assertThat(mailEntity00.getSendUserName()).isEqualTo("事務担当者　花子");
        // assertThat(mailEntity00.getSendDatetime()).isEqualTo("2024-07-21T12:34:12");

        assertThat(mailEntity00.getSaishinKbn()).isEqualTo(1);
        assertThat(mailEntity00.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(mailEntity00.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(mailEntity00.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

        SendAlertMail2024Entity mailEntity01 = listMail.get(1);

        assertThat(mailEntity01.getBccMail()).isEqualTo("300mail@1234.com,500mail@1235.com");
        assertThat(mailEntity01.getBodyTextMail())
                .isEqualTo("事務担当者　花子さんが行った自動処理が完了したよ!。http://localhost:8080/auto-exec-result?id=987&code=876でまってます!");
        assertThat(mailEntity01.getCcMail()).isEqualTo("");
        assertThat(mailEntity01.getFromMail()).isEqualTo("500mail@1235.com");
        assertThat(mailEntity01.getIsRepeat()).isEqualTo(false);
        assertThat(mailEntity01.getReplyToMail()).isEqualTo("500mail@1235.com");
        assertThat(mailEntity01.getSubjectMail()).isEqualTo("政治資金収支報告書一括登録処理結果");
        assertThat(mailEntity01.getToMail()).isEqualTo("500mail@1235.com");

        assertThat(mailEntity01.getSendUserId()).isEqualTo(123_321L);
        assertThat(mailEntity01.getSendUserCode()).isEqualTo(987);
        assertThat(mailEntity01.getSendUserName()).isEqualTo("事務担当者　花子");
        // assertThat(mailEntity01.getSendDatetime()).isEqualTo("2024-07-21T12:34:12");

        // 計画登録詳細
        List<TaskPlan2024Entity> listPlan = taskPlan2024Repository.findAll();
        listPlan.sort((e1, e2) -> e1.getTaskPlanName().compareTo(e2.getTaskPlanName()));
        assertThat(listPlan.size()).isEqualTo(2);

        TaskPlan2024Entity planEntity00 = listPlan.get(0);

        assertThat(planEntity00.getIsFinished()).isEqualTo(false);
        assertThat(planEntity00.getPoliticalOrganizationId()).isEqualTo(0);
        assertThat(planEntity00.getPoliticalOrganizationCode()).isEqualTo(0);
        assertThat(planEntity00.getPoliticalOrganizationName()).isEqualTo("");
        assertThat(planEntity00.getTaskPlanName()).isEqualTo("政治資金収支報告書一括登録処理結果");
        assertThat(planEntity00.getTaskLevelList()).isEqualTo("3,4");
        assertThat(planEntity00.getTransferPass()).isEqualTo("http://localhost:8080/auto-exec-result?id=987&code=876");

        assertThat(planEntity00.getSaishinKbn()).isEqualTo(1);
        assertThat(planEntity00.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(planEntity00.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(planEntity00.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

        TaskPlan2024Entity planEntity01 = listPlan.get(1);
        assertThat(planEntity01.getIsFinished()).isEqualTo(false);
        assertThat(planEntity01.getPoliticalOrganizationId()).isEqualTo(0);
        assertThat(planEntity01.getPoliticalOrganizationCode()).isEqualTo(0);
        assertThat(planEntity01.getPoliticalOrganizationName()).isEqualTo("");
        assertThat(planEntity01.getTaskPlanName()).isEqualTo("政治資金収支報告書一括登録準備");
        assertThat(planEntity01.getTaskLevelList()).isEqualTo("1,4,5");
        assertThat(planEntity01.getTransferPass()).isEqualTo("http://localhost:8080/auto-exec?id=987&code=876");

    }

}
