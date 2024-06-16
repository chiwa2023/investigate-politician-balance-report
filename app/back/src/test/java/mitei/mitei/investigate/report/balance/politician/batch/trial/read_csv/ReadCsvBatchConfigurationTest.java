package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.util.GetTestResourceUtil;

/**
 * ReadCsvBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadCsvBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(ReadCsvBatchConfiguration.JOB_NAME)
    @Autowired
    private Job readCsvJob;

    @Test
    void testJob() {
        assertThat(readCsvJob.getName()).isEqualTo(ReadCsvBatchConfiguration.JOB_NAME);
    }

    @Test
    void testExecute() throws Exception {

        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replaceAll(":", "-");
        String writePath = "c:/temp/report" + time + ".csv";
        String readPath = GetTestResourceUtil.practice("batch/trial/person.csv").toString();

        JobParameters jobParameters = new JobParametersBuilder(
                readCsvJob.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addString("readFileName", readPath).addString("writeFileName", writePath).toJobParameters();

        jobLauncherTestUtils.setJob(readCsvJob);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

    }

}
