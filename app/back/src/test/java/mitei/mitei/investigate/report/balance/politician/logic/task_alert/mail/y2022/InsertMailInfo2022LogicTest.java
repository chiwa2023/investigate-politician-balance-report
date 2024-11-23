package mitei.mitei.investigate.report.balance.politician.logic.task_alert.mail.y2022;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
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
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;
import mitei.mitei.investigate.report.balance.politician.entity.mail.SendAlertMail2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.mail.SendAlertMail2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertMailInfo2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertMailInfo2022LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertMailInfo2022Logic insertMailInfo2022Logic;

    /** メール送信予定Repository(2022年) */
    @Autowired
    private SendAlertMail2022Repository sendAlertMail2022Repository;

    @Test
    @Transactional
    @Sql({ "truncate_alert_mail_2022.sql", "../../../user_web_access/user_web_access.sql" })
    void testPractice() throws Exception { // NOPMD
        // CHECKSTYLE:OFF

        // 送信ユーザ
        UserWebAccessEntity userEntiy = new UserWebAccessEntity();
        userEntiy.setUserId(2011L);
        userEntiy.setUserCode(2000);
        userEntiy.setUserName("会計責任者 正夫");
        userEntiy.setMailAddress("test123@test.net"); // NOPMD

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

        List<TaskInfoEntity> listTask = new ArrayList<>();
        listTask.add(taskEntity01);
        listTask.add(taskEntity02);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        LocalDateTime shori = LocalDateTime.of(2022, 5, 22, 11, 22, 33);

        int size = insertMailInfo2022Logic.practice(privilegeDto, shori, userEntiy, listTask);

        assertThat(size).isEqualTo(2); // Taskを2件設定したので2行

        List<SendAlertMail2022Entity> listAlert = sendAlertMail2022Repository.findAll();

        // 登録順に整列
        listAlert.stream().sorted((e1, e2) -> e1.getInsertUserCode() - e2.getInsertUserCode());

        assertThat(listAlert.size()).isEqualTo(size); // 全取得しても想定行通り

        SendAlertMail2022Entity result00 = listAlert.get(0);
        assertThat(result00.getSaishinKbn()).isEqualTo(1);
        assertThat(result00.getSendUserId()).isEqualTo(2011L);
        assertThat(result00.getSendUserCode()).isEqualTo(2000);
        assertThat(result00.getSendUserName()).isEqualTo("会計責任者 正夫");
        assertThat(result00.getSendDatetime()).isEqualTo(shori);
        assertThat(result00.getIsRepeat()).isEqualTo(false);
        assertThat(result00.getFromMail()).isEqualTo("test123@test.net");
        assertThat(result00.getToMail()).isEqualTo("test123@test.net");
        assertThat(result00.getCcMail()).isEqualTo("");
        assertThat(result00.getBccMail()).isEqualTo("test111@test.net,test444@test.net,test555@test.net");
        assertThat(result00.getReplyToMail()).isEqualTo("test123@test.net");
        assertThat(result00.getSubjectMail()).isEqualTo("ダミータスク1");
        assertThat(result00.getBodyTextMail())
                .isEqualTo("会計責任者 正夫さんが行った自動処理が完了したよ!。http://localhost:8080/auto-exec?id=987&code=876でまってます!");
        assertThat(result00.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(result00.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(result00.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

        SendAlertMail2022Entity result01 = listAlert.get(1);

        assertThat(result01.getSaishinKbn()).isEqualTo(1);
        assertThat(result01.getSendUserId()).isEqualTo(2011L);
        assertThat(result01.getSendUserCode()).isEqualTo(2000);
        assertThat(result01.getSendUserName()).isEqualTo("会計責任者 正夫");
        assertThat(result01.getSendDatetime()).isEqualTo(shori);
        assertThat(result01.getIsRepeat()).isEqualTo(false);
        assertThat(result01.getFromMail()).isEqualTo("test123@test.net");
        assertThat(result01.getToMail()).isEqualTo("test123@test.net");
        assertThat(result01.getCcMail()).isEqualTo("");
        assertThat(result01.getBccMail()).isEqualTo("test222@test.net,test888@test.net");
        assertThat(result01.getReplyToMail()).isEqualTo("test123@test.net");
        assertThat(result01.getSubjectMail()).isEqualTo("ダミータスク2");
        assertThat(result01.getBodyTextMail()).isEqualTo(
                "会計責任者 正夫さんが行った操作で処理すべき新たなタスクが発生しました。http://localhost:8080/dummy-page?id=1&code=2にアクセスしてタスクの処理を行ってください");
        assertThat(result01.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(result01.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(result01.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

    }

}
