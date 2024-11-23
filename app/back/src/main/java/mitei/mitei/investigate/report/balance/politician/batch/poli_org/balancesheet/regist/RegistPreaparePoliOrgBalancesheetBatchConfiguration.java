package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * 政治資金収支報告書一括登録準備バッチConfiguration
 */
@Configuration
public class RegistPreaparePoliOrgBalancesheetBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "registPreaparePoliOrgBalancesheet";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_NAME = FUNCTION_NAME + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 3;

    /** 解凍済ファイルからAllBookに変換するプロセッサ */
    @Autowired
    private UnCompressedFileToReadXmlDtoProcessor unCompressedFileToReadXmlDtoProcessor;

    /** ReadXmlDtoからワークテーブルに変換するプロセッサ */
    @Autowired
    private ReadXmlDtoToWrokTableEntityProcessor readXmlDtoToWrokTableEntityProcessor;

    /** リーダ */
    @Autowired
    private TaskPlanBalancesheetDetailItemReader taskPlanBalancesheetDetailItemReader;

    /** ライタ */
    @Autowired
    private WkTblPoliOrgBalancesheetReportItemWriter wkTblPoliOrgBalancesheetReportItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
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
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_NAME)
    protected Step getStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        // プロセッサをまとめる
        List<ItemProcessor<?, ?>> delegates = new ArrayList<>();
        delegates.add(unCompressedFileToReadXmlDtoProcessor);
        delegates.add(readXmlDtoToWrokTableEntityProcessor);

        CompositeItemProcessor<TaskPlanBalancesheetDetailEntity, WkTblPoliOrgBalancesheetReportEntity> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(delegates);

        return new StepBuilder(STEP_NAME, jobRepository)
                .<TaskPlanBalancesheetDetailEntity, WkTblPoliOrgBalancesheetReportEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(taskPlanBalancesheetDetailItemReader).processor(compositeItemProcessor)
                .writer(wkTblPoliOrgBalancesheetReportItemWriter).build();
    }

}
