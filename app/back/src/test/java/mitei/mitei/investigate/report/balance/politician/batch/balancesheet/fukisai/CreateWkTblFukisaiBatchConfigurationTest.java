package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * CreateWkTblFukisaiBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateWkTblFukisaiBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(CreateWkTblFukisaiBatchConfiguration.JOB_NAME)
    @Autowired
    private Job createWkTblFukisai;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(CreateWkTblFukisaiBatchConfiguration.JOB_NAME, createWkTblFukisai.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../task_plan/update_task_plan_2024.sql", "erase_wk_tbl_fukisai.sql",
            "convert_income_fukisai_new_2022.sql", "convert_outcome_fukisai_2022.sql" })
    void test() throws Exception {

        jobLauncherTestUtils.setJob(createWkTblFukisai);

        final int taskPlanCode = 250;
        final long userId = 1212;
        final int userCode = 970;
        final String userName = "ユーザ";
        final String taskName = "不記載チェッカー";
        final int year = 2024;
        final int poliOrgCode = 431;
        final int houkokunen = 2022;
        final boolean isSearchCode = true;

        JobParameters jobParameters = new JobParametersBuilder(
                createWkTblFukisai.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("userId", userId)
                .addLong("userCode", Long.valueOf(userCode)).addString("userName", userName)
                .addLong("taskPlanCode", Long.valueOf(taskPlanCode)).addString("taskName", taskName)
                .addLong("year", Long.valueOf(year)).addLong("poliOrgCode", Long.valueOf(poliOrgCode))
                .addLong("houkokunen", Long.valueOf(houkokunen)).addString("isSearchCode", String.valueOf(isSearchCode))
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
