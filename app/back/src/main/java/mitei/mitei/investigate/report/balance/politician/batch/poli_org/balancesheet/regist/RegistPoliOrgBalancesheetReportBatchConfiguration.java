package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * 政治資金収支報告書ワークテーブルからデータ本登録をする
 */
@Configuration
public class RegistPoliOrgBalancesheetReportBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "registPoliOrgBalancesheetReport";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** Step名 */
    public static final String STEP_UPDATE_NAME = FUNCTION_NAME + "Update" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 10;

    /** リーダInsert */
    @Autowired
    private WkTblPoliOrgBalancesheetReportItemReader wkTblPoliOrgBalancesheetReportItemReader;

    /** ライタInsert */
    @Autowired
    private AllBookItemWriter allBookItemWriter;

    /** UpdateTasklet */
    @Autowired
    private UpdateFinishedWkTblBalancesheetTasklet updateFinishedWkTblBalancesheetTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return ジョブ
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT_NAME) final Step stepInsert,
            @Qualifier(STEP_UPDATE_NAME) final Step stepUpdate) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepInsert)
                .next(stepUpdate).end().build();
    }

    /**
     * StepInsertを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_INSERT_NAME)
    protected Step getStepInsert(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INSERT_NAME, jobRepository)
                .<WkTblPoliOrgBalancesheetReportEntity, WkTblPoliOrgBalancesheetReportEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(wkTblPoliOrgBalancesheetReportItemReader).writer(allBookItemWriter).build();
    }

    /**
     * StepUpdateを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_UPDATE_NAME)
    protected Step getStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_UPDATE_NAME, jobRepository)
                .tasklet(updateFinishedWkTblBalancesheetTasklet, transactionManager).build();
    }

}
