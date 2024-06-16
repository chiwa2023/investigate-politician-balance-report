package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
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

/**
 * WriteCsvBatchConfiguration単体テスト(実行が成功する)
 */
@SpringJUnitConfig(WriteCsvBatchConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class)//全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WriteCsvBatchConfigurationSuccessExecuteTest {
    
    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    
    @Test
    void test(final @Qualifier(WriteCsvBatchConfiguration.JOB_NAME) @Autowired Job job)throws Exception {
        //JOB名だけを検証していた場合は他のクラスを起動していなかったので、Job名を指定する必要がなかったが、
        //今回全体起動したため、readCsvがおなじくBoot管理下におかれているため、@QualifierでJob名を厳密に指定する必要が出た
        
        jobLauncherTestUtils.setJob(job);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

}
