package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * 固定CSVファイルを書き出すタスクレットをもつJob定義
 */
@Configuration
public class WriteCsvBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "writeSimpleCsv";

    /** Tasklet(接尾語) */
    private static final String TASKLET = "Tasklet";
    /** Step(接尾語) */
    private static final String STEP = "Step";
    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_NAME = FUNCTION_NAME + STEP;

    /** Tasklet名 */
    public static final String TASKLET_NAME = FUNCTION_NAME + TASKLET;

    /**
     * Jobを設定する
     *
     * @param jobRepository リポジトリ
     * @param step ステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_NAME) final Step step) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(step).end().build();
    }

    /**
     * Stepを設定する
     *
     * @param jobRepository リポジトリ
     * @param transactionManager トランザクションマネージャ
     * @param tasklet タスクレット
     * @return step
     */
    @Bean(STEP_NAME)
    protected Step getStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager,
            final @Qualifier(TASKLET_NAME) Tasklet tasklet) {

        return new StepBuilder(STEP_NAME, jobRepository).tasklet(tasklet, transactionManager).build();
    }

    /**
     * タスクレットを設定する
     *
     * @return tasklet
     */
    @Bean(TASKLET_NAME)
    protected Tasklet getTasklet() {

        return new WriteCsvSimpleTasklet();
    }

}
