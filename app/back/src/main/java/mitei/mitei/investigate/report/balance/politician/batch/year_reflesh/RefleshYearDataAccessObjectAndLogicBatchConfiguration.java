package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh;

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

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.RefleshYearDataAccessDdlTaskletV1;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.RefleshYearDataAccessEntityTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.RefleshYearDataAccessLogicTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.RefleshYearDataAccessLogicTestTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.RefleshYearDataAccessRepositoryTasklet;

/**
 * データアクセス用のオブジェクトとロジックを複写するBatchConfiguration
 */
@Configuration
public class RefleshYearDataAccessObjectAndLogicBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "refleshYearDataAccessObjectAndLogic";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_NAME = FUNCTION_NAME + STEP;

    /** Entity複写タスクレット */
    @Autowired
    private RefleshYearDataAccessEntityTasklet refleshYearDataAccessEntityTasklet;

    /** Repository複写タスクレット */
    @Autowired
    private RefleshYearDataAccessRepositoryTasklet refleshYearDataAccessRepositoryTasklet;

    /** Logic複写タスクレット */
    @Autowired
    private RefleshYearDataAccessLogicTasklet refleshYearDataAccessLogicTasklet;

    /** LogicTest複写タスクレット */
    @Autowired
    private RefleshYearDataAccessLogicTestTasklet refleshYearDataAccessLogicTestTasklet;

    /** DDL複写タスクレット */
    @Autowired
    private RefleshYearDataAccessDdlTaskletV1 refleshYearDataAccessDdlTaskletV1;


    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブリポジトリ
     * @param step          このConfigureで設定したステップ
     * @return ジョブ
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_NAME) final Step step) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(step).end().build();
    }

    /**
     * Stepを返却する
     *
     * @param jobRepository      ジョブリポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_NAME)
    protected Step getStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        // 各データアクセスオブジェクトを複写する
        // TODO 生成したオブジェクトをファクトリメソッドで使用できるようにし、さらに自動化を進める

        return new StepBuilder(STEP_NAME, jobRepository)
                .tasklet(refleshYearDataAccessEntityTasklet, transactionManager) // Entity
                .tasklet(refleshYearDataAccessRepositoryTasklet, transactionManager) // Repositoy
                .tasklet(refleshYearDataAccessLogicTasklet, transactionManager) // Logic
                .tasklet(refleshYearDataAccessLogicTestTasklet, transactionManager) // Logicのテスト
                .tasklet(refleshYearDataAccessDdlTaskletV1, transactionManager) // DDL
                // .tasklet(null, transactionManager) ファクトリメソッドLogicに生成したLogicを導入
                .build();
    }
}
