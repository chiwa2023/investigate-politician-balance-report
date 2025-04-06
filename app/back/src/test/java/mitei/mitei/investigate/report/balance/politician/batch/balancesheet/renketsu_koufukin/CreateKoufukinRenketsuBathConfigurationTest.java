package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * CreateKoufukinRenketsuBathConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class CreateKoufukinRenketsuBathConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(CreateKoufukinRenketsuBathConfiguration.JOB_NAME)
    @Autowired
    private Job createKoufukinRenketsu;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(CreateKoufukinRenketsuBathConfiguration.JOB_NAME, createKoufukinRenketsu.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "truncate_wk_tbl_balancesheet_income.sql", "truncate_wk_tbl_balancesheet_outcome.sql",
            "truncate_wk_tbl_usage_income.sql", "truncate_wk_tbl_usage_outcome.sql",
            "init_wk_tbl_balancesheet_income.sql", "init_wk_tbl_balancesheet_outcome.sql",
            "wktbl_usage_income_2022.sql", "wktbl_usage_outcome_2022.sql", "truncate_wk_tbl_renketsu_koufukin.sql" })

    void test() throws Exception {

        // 実際の処理
        jobLauncherTestUtils.setJob(createKoufukinRenketsu);

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", 12_231L).addLong("userCode", Long.valueOf(987)).addString("userName", "ユーザ")
                .addLong("documentCodeUsage", 1L).addLong("documentCodeBalance", 7L)
                .addLong("houkokuNen", Long.valueOf(2022))
                .addLong("year", Long.valueOf(2024))
                .addLong("taskPlanCode", Long.valueOf(198))
                .addString("taskName", "交付金連結")
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
