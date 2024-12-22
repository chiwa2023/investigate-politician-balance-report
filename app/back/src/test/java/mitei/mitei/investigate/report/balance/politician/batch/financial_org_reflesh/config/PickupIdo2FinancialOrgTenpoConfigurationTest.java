package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

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

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * PickupIdo2FinancialOrgTenpoConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../truncate_zengin_org_change_branch.sql")
class PickupIdo2FinancialOrgTenpoConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(PickupIdo2FinancialOrgTenpoConfiguration.JOB_NAME)
    @Autowired
    private Job pickupIdo2FinancialOrgTenpo;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(PickupIdo2FinancialOrgTenpoConfiguration.JOB_NAME, pickupIdo2FinancialOrgTenpo.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        jobLauncherTestUtils.setJob(pickupIdo2FinancialOrgTenpo);

        JobParameters jobParameters = new JobParametersBuilder(
                pickupIdo2FinancialOrgTenpo.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("loginUserId", 339L)
                .addString("loginUserCode", "330").addString("loginUserName", "ユーザA").toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
