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

import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.FinancialOrgCsvDto;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.FinancialOrgCsvItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.FinancialOrgCsvMasterProcessor;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgBranchMasterItemWriter;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;

/**
 * 全銀協金融機関・店舗ワークテーブル1にcsvデータを複写する
 */
@Configuration
public class CopyFinancialOrgZenginMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "copyFinancialOrgZenginMaster";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 500;

    /** 金融機関・支店csvリーダ */
    @Autowired
    private FinancialOrgCsvItemReader financialOrgCsvItemReader;

    /** 金融機関・支店ワークテーブル1ItemWriter */
    @Autowired
    private ZenginOrgBranchMasterItemWriter zenginOrgBranchMasterItemWriter;

    /** 金融機関・支店Csvから金融機関・支店ワークテーブル1変換プロセッサ */
    @Autowired
    private FinancialOrgCsvMasterProcessor financialOrgCsvMasterProcessor;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, 
            @Qualifier(STEP_INSERT_NAME) final Step stepInsert) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepInsert).end().build();
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
                .<FinancialOrgCsvDto, ZenginOrgBranchMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(financialOrgCsvItemReader).processor(financialOrgCsvMasterProcessor)
                .writer(zenginOrgBranchMasterItemWriter).build();
    }

}
