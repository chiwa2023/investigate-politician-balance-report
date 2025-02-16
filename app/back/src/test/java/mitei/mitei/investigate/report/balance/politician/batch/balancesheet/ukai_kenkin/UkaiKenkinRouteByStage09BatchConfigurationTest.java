package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

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
 * UkaiKenkinRouteByStage09BatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinRouteByStage09BatchConfigurationTest {


    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(UkaiKenkinRouteByStage09BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage09;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(UkaiKenkinRouteByStage09BatchConfiguration.JOB_NAME, ukaiKenkinRouteByStage09.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    // @Transactional
    @Sql({ "truncate_only_wk_tbl_ukai_kenkin.sql", "configuration_income_2022.sql",
            "configuration_poli_org_property.sql", "configuration_wktbl_meisai.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception {

        jobLauncherTestUtils.setJob(ukaiKenkinRouteByStage09);

        JobParameters jobParameters = new JobParametersBuilder(
                ukaiKenkinRouteByStage09.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("userId", 980L).addLong("userCode", 987L)
                .addString("userName", "ユーザA").addLong("houkokuNen", 2022L).addString("isSearchKoufukin", "false")
                .addLong("poliOrgCode", 100L)
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

        fail("Not yet implemented");
    }

}
