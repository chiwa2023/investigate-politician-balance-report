package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * WriteCsvBatchConfiguration実行テスト(失敗する)
 */
@SpringBatchTest
@SpringJUnitConfig(WriteCsvBatchConfiguration.class)
class WriteCsvBatchConfigurationExecuteTestFailure {

    /** テストユーティリティ */
    @Autowired
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
    void test(final @Autowired Job job) throws Exception {

        jobRepository.createJobExecution(WriteCsvBatchConfiguration.JOB_NAME, new JobParameters());
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);

        jobLauncherTestUtils.setJob(job);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // 実行できなくてnullの結果しか返らなくてテストが落ちる。普通に考えるとConfigureで
        // 引数に取っているPlatformTransactionManagerなどがMockだから…
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

}
