package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3;

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

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearDataAccessDdlTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearDataAccessFactoryMethodLogicTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearDataAccessFactoryMethodLogicTestTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearPersistenceObjectBalancesheetAndUsageTasklet;

/**
 * 政治資金収支報告書と政党交付金使途報告書用年更新データアクセス用のオブジェクトとロジックを複写するBatchConfiguration
 */
@Configuration
public class RefleshYearBalancesheetAndUsageWithPersistenceObjectBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "refleshYearBalancesheetAndUsageWithPersistenceObject";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_LOGIC_NAME = FUNCTION_NAME + "Logic" + STEP;

    /** Step名 */
    public static final String STEP_DDL_NAME = FUNCTION_NAME + "Ddl" + STEP;

    /** Step名 */
    public static final String STEP_FACTORY_NAME = FUNCTION_NAME + "Factory" + STEP;

    /** Step名 */
    public static final String STEP_FACTORY_TEST_NAME = FUNCTION_NAME + "FactoryTest" + STEP;

    /** Logic複写タスクレット */
    @Autowired
    private RefleshYearPersistenceObjectBalancesheetAndUsageTasklet refleshYearPersistenceObjectBalancesheetAndUsageTasklet;

    /** DDL複写タスクレット */
    @Autowired
    private RefleshYearDataAccessDdlTasklet refleshYearDataAccessDdlTasklet;

    /** ファクトリメソッド複写タスクレット */
    @Autowired
    private RefleshYearDataAccessFactoryMethodLogicTasklet refleshYearDataAccessFactoryMethodLogicTasklet;

    /** ファクトリメソッドテスト複写タスクレット */
    @Autowired
    private RefleshYearDataAccessFactoryMethodLogicTestTasklet refleshYearDataAccessFactoryMethodLogicTestTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブリポジトリ
     * @param step          このConfigureで設定したステップ
     * @return ジョブ
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_DDL_NAME) final Step stepDdl,
            @Qualifier(STEP_LOGIC_NAME) final Step stepLogic, @Qualifier(STEP_FACTORY_NAME) final Step stepFactory,
            @Qualifier(STEP_FACTORY_TEST_NAME) final Step stepFactoryTest) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepDdl).next(stepLogic)
                .next(stepFactory).next(stepFactoryTest).end().build();
    }

    /**
     * Stepを返却する
     *
     * @param jobRepository      ジョブリポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_LOGIC_NAME)
    protected Step getStepLogic(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_LOGIC_NAME, jobRepository)
                .tasklet(refleshYearPersistenceObjectBalancesheetAndUsageTasklet, transactionManager).build();
    }

    /**
     * Stepを返却する
     *
     * @param jobRepository      ジョブリポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_DDL_NAME)
    protected Step getStepDdl(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_DDL_NAME, jobRepository)
                .tasklet(refleshYearDataAccessDdlTasklet, transactionManager).build();
    }

    /**
     * Stepを返却する
     *
     * @param jobRepository      ジョブリポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_FACTORY_NAME)
    protected Step getStepFactory(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_FACTORY_NAME, jobRepository)
                .tasklet(refleshYearDataAccessFactoryMethodLogicTasklet, transactionManager).build();
    }

    /**
     * Stepを返却する
     *
     * @param jobRepository      ジョブリポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_FACTORY_TEST_NAME)
    protected Step getStepFactoryTest(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_FACTORY_TEST_NAME, jobRepository)
                .tasklet(refleshYearDataAccessFactoryMethodLogicTestTasklet, transactionManager).build();
    }

}
