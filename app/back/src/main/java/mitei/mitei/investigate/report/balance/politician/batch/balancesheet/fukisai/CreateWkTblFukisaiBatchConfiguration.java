package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;

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

/**
 * 不記載検出ワークテーブル作成BachConfig
 */
@Configuration
public class CreateWkTblFukisaiBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "createWkTblFukisai";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CLEAN_NAME = FUNCTION_NAME + "Clean" + STEP;

    /** Step名 */
    public static final String STEP_INCOME_NAME = FUNCTION_NAME + "Income" + STEP;

    /** Step名 */
    public static final String STEP_OUTCOME_NAME = FUNCTION_NAME + "Outcome" + STEP;

    /** Step名 */
    public static final String STEP_TASK_PLAN_NAME = FUNCTION_NAME + "TaskPlan" + STEP;

    /** 不記載検出ワークテーブル消去Tasklet */
    @Autowired
    private EraseWkTblMyDataTasklet eraseWkTblMyDataTasklet;

    /** 収入ワークテーブル書き込みTasklet */
    @Autowired
    private WriteIncomeDataFukisaiTasklet writeIncomeDataFukisaiTasklet;

    /** 支出ワークテーブル書き込みTasklet */
    @Autowired
    private WriteOutcomeDataFukisaiTasklet writeOutcomeDataFukisaiTasklet;

    /** タスク計画終了フラグTasklet */
    @Autowired
    private FinishTaskPlanTasklet finishTaskPlanTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAN_NAME) final Step stepClean,
            @Qualifier(STEP_INCOME_NAME) final Step stepIncome, @Qualifier(STEP_OUTCOME_NAME) final Step stepOutcome,
            @Qualifier(STEP_TASK_PLAN_NAME) final Step stepTaskPlan) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepIncome).next(stepOutcome).next(stepTaskPlan).end().build();
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

        return new StepBuilder(STEP_CLEAN_NAME, jobRepository).tasklet(eraseWkTblMyDataTasklet, transactionManager)
                .build();
    }

    /**
     * StepIncomeを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_INCOME_NAME)
    protected Step getStepIncome(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INCOME_NAME, jobRepository)
                .tasklet(writeIncomeDataFukisaiTasklet, transactionManager).build();
    }

    /**
     * StepIncomeを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_OUTCOME_NAME)
    protected Step getStepOutcome(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_OUTCOME_NAME, jobRepository)
                .tasklet(writeOutcomeDataFukisaiTasklet, transactionManager).build();
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
