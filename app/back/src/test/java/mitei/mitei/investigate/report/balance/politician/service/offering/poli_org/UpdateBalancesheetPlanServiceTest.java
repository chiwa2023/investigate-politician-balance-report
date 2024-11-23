package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdateBalancesheetPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateBalancesheetPlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdateBalancesheetPlanService updateBalancesheetPlanService;

    /** 詳細タスク計画Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    /** 政党交付金使途報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("task_plan_balancesheet_detail.sql")
    void testPractice() throws Exception {

        // 元データを編集した想定
        Long callId = 1245L;
        String charset = "Shift_JIS";
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        TaskPlanBalancesheetDetailEntity entitySrc = taskPlanBalancesheetDetailRepository.findById(callId).get();
        //entitySrc.setCharset(charset);

        // 編集
        Long newId = updateBalancesheetPlanService.practice(entitySrc, privilegeDto);

        // 元のデータを呼びなおし
        entitySrc = taskPlanBalancesheetDetailRepository.findById(callId).get();

        // 編集後データを呼ぶ
        TaskPlanBalancesheetDetailEntity entityCopy = taskPlanBalancesheetDetailRepository.findById(newId).get();

        // 最新区分が変更されている
        assertThat(entitySrc.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());
        assertThat(entityCopy.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 文字コードが変更されている
        //assertThat(entitySrc.getCharset()).isEqualTo(null);
        assertThat(entityCopy.getCharset()).isEqualTo(charset);

    }

    @Test
    @Tag("TableTruncate")
    @Sql("task_plan_balancesheet_detail.sql")
    void testPracticeToPartyUsage() throws Exception {

        // 元データを編集した想定
        Long callId = 1247L;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        TaskPlanBalancesheetDetailEntity entitySrc = taskPlanBalancesheetDetailRepository.findById(callId).get();
        String charset = "UTF-8";
        String documentKey = DocumentRecognizeKeyConstants.SOFT_PARTY_USAGE;
        entitySrc.setCharset(charset);
        entitySrc.setDocumentKey(documentKey);

        // 編集
        Long newId = updateBalancesheetPlanService.practice(entitySrc, privilegeDto);

        // 元のデータを呼びなおし
        entitySrc = taskPlanBalancesheetDetailRepository.findById(callId).get();

        // 編集後データを呼ぶ(政党交付金使途報告書Entity)
        TaskPlanPartyUsageDetailEntity entityCopy = taskPlanPartyUsageDetailRepository.findById(newId).get();

        // 最新区分が変更されている
        assertThat(entitySrc.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());
        assertThat(entityCopy.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 文字コードが変更されている
        assertThat(entitySrc.getCharset()).isEqualTo(null);
        assertThat(entityCopy.getCharset()).isEqualTo(charset);

        // 政治資金収支報告書テーブルのデータはサンプルデータから増えていない
        assertThat(taskPlanBalancesheetDetailRepository.count()).isEqualTo(3); // 元の全件は3件

    }

    @Test
    @Tag("TableTruncate")
    @Sql("task_plan_balancesheet_detail.sql")
    void testPracticeException() throws Exception {

        //  誤った文書と文字コードを指定したときは登録不可で処理
        Long callId = 1245L;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        TaskPlanBalancesheetDetailEntity entitySrc = taskPlanBalancesheetDetailRepository.findById(callId).get();

        String documentKey = DocumentRecognizeKeyConstants.SOFT_PARTY_USAGE;
        entitySrc.setCharset("US-ASCII");
        entitySrc.setDocumentKey(documentKey);

        assertThat(updateBalancesheetPlanService.practice(entitySrc, privilegeDto)).isEqualTo(0L);
        
        // 事故で呼び出せないデータを編集対象にした場合は例外で処理中断
        TaskPlanBalancesheetDetailEntity entityNoInsert = new TaskPlanBalancesheetDetailEntity();
        entityNoInsert.setTaskPlanBalancesheetDetailId(33L);

        assertThrows(EmptyResultDataAccessException.class,
                () -> updateBalancesheetPlanService.practice(entityNoInsert, privilegeDto));

    }

}
