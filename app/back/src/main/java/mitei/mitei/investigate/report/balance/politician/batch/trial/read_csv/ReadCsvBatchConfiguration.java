package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.PersonDto;
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportDto;


/**
 * Csv読み取りし書き込みするバッチ
 */
@Configuration
@EnableBatchProcessing
public class ReadCsvBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "readCsv";

    /** Step(接尾語) */
    private static final String STEP = "Step";
    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    private static final String STEP_NAME = FUNCTION_NAME + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 3;

    /** プロセッサ */
    @Autowired
    private ReadCsvItemProcessor readCsvItemProcessor;

    /** リーダ */
    @Autowired
    private ReadCsvPersonItemReader readCsvPersonItemReader;
    
    /** ライタ */
    @Autowired
    private ReadCsvReportItemWriter readCsvReportItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step このConfigureで設定したステップ
     * @return ジョブ
     */
    @Bean(JOB_NAME)
    protected Job job(final JobRepository jobRepository, @Qualifier(STEP_NAME) final Step step) {

        System.out.println("batch " + JOB_NAME);

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(step).end().build();
    }

    /**
     * Stepを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @param readCsvTasklet タスクレット
     * @return step
     */
    @Bean(STEP_NAME)
    protected Step step(final JobRepository jobRepository, final PlatformTransactionManager transactionManager,
            final ReadCsvTasklet readCsvTasklet) {

        System.out.println("batch " + STEP_NAME);

        // return new StepBuilder(STEP_NAME, jobRepository).tasklet(readCsvTasklet,
        // transactionManager).build();
        
        return new StepBuilder(STEP_NAME, jobRepository).<PersonDto, ReportDto>chunk(CHUNK_SIZE, transactionManager)
                .reader(readCsvPersonItemReader).processor(readCsvItemProcessor).writer(readCsvReportItemWriter).build();
    }
}
