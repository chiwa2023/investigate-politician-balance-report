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

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearDataAccessFactoryMethodLogicTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearDataAccessFactoryMethodLogicTestTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet.RefleshYearPersistenceObjectSingleLogicOnlyTasklet;

/**
 * 年更新ロジックを複写するBatchConfiguration
 */
@Configuration
public class RefleshSingleLogicOnlyBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "refleshPersistenceObjectSingle";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_LOGIC_NAME = FUNCTION_NAME + "Logic" + STEP;

    /** Step名 */
    public static final String STEP_FACTORY_NAME = FUNCTION_NAME + "Factory" + STEP;

    /** Step名 */
    public static final String STEP_FACTORY_TEST_NAME = FUNCTION_NAME + "FactoryTest" + STEP;

    /** Logic複写タスクレット */
    @Autowired
    private RefleshYearPersistenceObjectSingleLogicOnlyTasklet refleshYearPersistenceObjectSingleLogicOnlyTasklet;

    /** 年Logicファクトリメソッド複写タスクレット */
    @Autowired
    private RefleshYearDataAccessFactoryMethodLogicTasklet refleshYearDataAccessFactoryMethodLogicTasklet;

    /** 年Logicファクトリメソッドテスト複写タスクレット */
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
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_LOGIC_NAME) final Step stepLogic,
            @Qualifier(STEP_FACTORY_NAME) final Step stepFactory,
            @Qualifier(STEP_FACTORY_TEST_NAME) final Step stepFactoryTest) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepLogic)
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
                .tasklet(refleshYearPersistenceObjectSingleLogicOnlyTasklet, transactionManager).build();
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
