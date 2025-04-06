package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

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

import mitei.mitei.investigate.report.balance.politician.batch.task_plan.FinishTaskPlanTasklet;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinSelectDto;

/**
 * 交付金連結作業BatchConfiguration
 */
@Configuration
public class CreateKoufukinRenketsuBathConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "createKoufukinRenketsu";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CLEAN_NAME = FUNCTION_NAME + "CleanTkTbl" + STEP;

    /** Step名 */
    public static final String STEP_COPY_BALANCE_NAME = FUNCTION_NAME + "CopyBalance" + STEP;

    /** Step名 */
    public static final String STEP_COPY_USGAE_NAME = FUNCTION_NAME + "CopyUsage" + STEP;

    /** Step名 */
    public static final String STEP_INCOME_ADD_NAME = FUNCTION_NAME + "RenketsuIncome" + STEP;

    /** Step名 */
    public static final String STEP_OUTCOME_ADD_NAME = FUNCTION_NAME + "RenketsuOutcome" + STEP;

    /** Step名 */
    public static final String STEP_DUPLICATE_NAME = FUNCTION_NAME + "PickupDuplicate" + STEP;

    /** Step名 */
    public static final String STEP_TASK_PLAN_NAME = FUNCTION_NAME + "TaskPlan" + STEP;

    /** タスク計画終了フラグTasklet */
    @Autowired
    private FinishTaskPlanTasklet finishTaskPlanTasklet;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 10;

    /** ワークテーブルクリーンTasklet */
    @Autowired
    private DeleteKoufukinRenketsuWkTblTasklet deleteKoufukinRenketsuWkTblTasklet;

    /** 収支報告書複写Tasklet */
    @Autowired
    private CopyBalancesheetWkTblTasklet copyBalancesheetWkTblTasklet;

    /** 使途報告書複写Tasklet */
    @Autowired
    private CopyUsageWkTblTasklet copyUsageWkTblTasklet;

    /** 収入用ItemReader */
    @Autowired
    private KoufukinRenketsuIncomeItemReader koufukinRenketsuIncomeItemReader;

    /** 支出用ItemReader */
    @Autowired
    private KoufukinRenketsuOutcomeItemReader koufukinRenketsuOutcomeItemReader;

    /** 支出用ItemReader */
    @Autowired
    private RenketsuThroughProcessor renketsuThroughProcessor;

    /** 連結テーブルitemWriter */
    @Autowired
    private KoufukinRenketsuItemWriter koufukinRenketsuItemWriter;

    /** 重複抽出Tasklet */
    @Autowired
    private PickupRenketsuKoufukinDuplicateTasklet pickupRenketsuKoufukinDuplicateTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAN_NAME) final Step stepClean,
            @Qualifier(STEP_COPY_BALANCE_NAME) final Step stepBalance,
            @Qualifier(STEP_COPY_USGAE_NAME) final Step stepusage,
            @Qualifier(STEP_INCOME_ADD_NAME) final Step stepIncomeAdd,
            @Qualifier(STEP_OUTCOME_ADD_NAME) final Step stepOutcomeAdd,
            @Qualifier(STEP_DUPLICATE_NAME) final Step stepPickup,
            @Qualifier(STEP_TASK_PLAN_NAME) final Step stepTaskPlan) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepBalance).next(stepusage).next(stepIncomeAdd).next(stepOutcomeAdd).next(stepPickup)
                .next(stepTaskPlan).end().build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_CLEAN_NAME)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_CLEAN_NAME, jobRepository)
                .tasklet(deleteKoufukinRenketsuWkTblTasklet, transactionManager).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_COPY_BALANCE_NAME)
    protected Step getStepBalance(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_COPY_BALANCE_NAME, jobRepository)
                .tasklet(copyBalancesheetWkTblTasklet, transactionManager).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_COPY_USGAE_NAME)
    protected Step getStepUsage(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_COPY_USGAE_NAME, jobRepository).tasklet(copyUsageWkTblTasklet, transactionManager)
                .build();
    }

    /**
     * StepIncomeを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_INCOME_ADD_NAME)
    protected Step getStepIncomeAdd(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INCOME_ADD_NAME, jobRepository)
                .<WkTblRenketsuKoufukinSelectDto, WkTblRenketsuKoufukinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(koufukinRenketsuIncomeItemReader).processor(renketsuThroughProcessor)
                .writer(koufukinRenketsuItemWriter).build();
    }

    /**
     * StepOutcomeを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_OUTCOME_ADD_NAME)
    protected Step getStepOutcomeAdd(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_OUTCOME_ADD_NAME, jobRepository)
                .<WkTblRenketsuKoufukinSelectDto, WkTblRenketsuKoufukinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(koufukinRenketsuOutcomeItemReader).processor(renketsuThroughProcessor)
                .writer(koufukinRenketsuItemWriter).build();
    }

    /**
     * StepPickupを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_DUPLICATE_NAME)
    protected Step getStepPickup(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_DUPLICATE_NAME, jobRepository)
                .tasklet(pickupRenketsuKoufukinDuplicateTasklet, transactionManager).build();
    }

    /**
     * StepIncomeを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_TASK_PLAN_NAME)
    protected Step getStepTaskPlan(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_TASK_PLAN_NAME, jobRepository).tasklet(finishTaskPlanTasklet, transactionManager)
                .build();
    }

}
