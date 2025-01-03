package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.TransferChangeCreateSiteTasklet;

/**
 * 異動テーブルから抽出データを作成側に送信する
 */
@Configuration
public class TransferCreateZenginChangeBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "transferCreateZenginChange";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** 追加Step名 */
    public static final String STEP_TRANSFER_NAME = FUNCTION_NAME + "Transfer" + STEP;

    /** 異動テーブル作成側転送Tasklet */
    @Autowired
    private TransferChangeCreateSiteTasklet transferChangeCreateSiteTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_TRANSFER_NAME) final Step stepTransfer) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepTransfer).end()
                .build();
    }

    /**
     * StepTransferを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_TRANSFER_NAME)
    protected Step getStepTransfer(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_TRANSFER_NAME, jobRepository)
                .tasklet(transferChangeCreateSiteTasklet, transactionManager).build();
    }

}
