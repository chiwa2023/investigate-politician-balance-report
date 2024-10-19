package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * WriteCsvBatchConfiguration単体テスト
 */
@SpringBatchTest
@SpringJUnitConfig(WriteCsvBatchConfiguration.class)
class WriteCsvBatchConfigurationTest {

    /** テストユーティリティ */
    @MockBean
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** レポジトリのMock */
    @MockBean
    private JobRepository jobRepository;

    /** トランザクションマネージャのMock */
    @MockBean
    private PlatformTransactionManager transactionManager;

    /** ジョブランチャのMock */
    @MockBean
    private JobLauncher jobLauncher;


    @Test
    void testJob(final @Autowired Job job) {
        assertThat(job.getName()).isEqualTo(WriteCsvBatchConfiguration.JOB_NAME);
    }

    @Test
    void testStep(final @Autowired Step step) {
        assertThat(step.getName()).isEqualTo(WriteCsvBatchConfiguration.STEP_NAME);
    }

    @Test
    void testTasklet(final @Autowired Tasklet tasklet) {
        assertThat(tasklet.getClass()).isEqualTo(WriteCsvSimpleTasklet.class);
    }

}