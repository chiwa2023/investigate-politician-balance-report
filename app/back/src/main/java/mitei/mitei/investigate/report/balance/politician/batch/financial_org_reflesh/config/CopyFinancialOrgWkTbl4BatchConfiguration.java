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

import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.CleanZenginOrgBranchWkTbl4Tasklet;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.FinancialOrgCsvDto;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.FinancialOrgCsvItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.FinancialOrgCsvWkTbl4Processor;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgBranchWkTbl4ItemWriter;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk4Entity;

/**
 * 全銀協金融機関・店舗ワークテーブル1にcsvデータを複写する
 */
@Configuration
public class CopyFinancialOrgWkTbl4BatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "copyFinancialOrgWkTbl4";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CLEAN_NAME = FUNCTION_NAME + "Clean" + STEP;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 500;

    /** 金融機関・支店csvリーダ */
    @Autowired
    private FinancialOrgCsvItemReader financialOrgCsvItemReader;

    /** 金融機関・支店ワークテーブル1ItemWriter */
    @Autowired
    private ZenginOrgBranchWkTbl4ItemWriter zenginOrgBranchWkTbl4ItemWriter;

    /** 金融機関・支店Csvから金融機関・支店ワークテーブル1変換プロセッサ */
    @Autowired
    private FinancialOrgCsvWkTbl4Processor financialOrgCsvWkTbl4Processor;

    /** ワークテーブルCleanTasklet */
    @Autowired
    private CleanZenginOrgBranchWkTbl4Tasklet cleanZenginOrgBranchWkTbl4Tasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAN_NAME) final Step stepClean,
            @Qualifier(STEP_INSERT_NAME) final Step stepInsert) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepInsert).end().build();
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
                .<FinancialOrgCsvDto, ZenginOrgBranchWk4Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(financialOrgCsvItemReader).processor(financialOrgCsvWkTbl4Processor)
                .writer(zenginOrgBranchWkTbl4ItemWriter).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_CLEAN_NAME)
    protected Step getStepUpdate(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN_NAME, jobRepository)
                .tasklet(cleanZenginOrgBranchWkTbl4Tasklet, transactionManager).build();
    }

}
