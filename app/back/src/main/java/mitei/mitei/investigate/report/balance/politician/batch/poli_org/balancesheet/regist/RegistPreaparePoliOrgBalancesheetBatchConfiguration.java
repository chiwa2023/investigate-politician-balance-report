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
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** Step名 */
    public static final String STEP_UPDATE_NAME = FUNCTION_NAME + "Update" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 10;

    /** 解凍済ファイルからAllBookに変換するプロセッサ */
    @Autowired
    private UnCompressedFileToReadXmlDtoProcessor unCompressedFileToReadXmlDtoProcessor;

    /** ReadXmlDtoからワークテーブルに変換するプロセッサ */
    @Autowired
    private ReadXmlDtoToWrokTableEntityProcessor readXmlDtoToWrokTableEntityProcessor;

    /** リーダInsert */
    @Autowired
    private TaskPlanBalancesheetDetailItemReader taskPlanBalancesheetDetailItemReader;

    /** ライタInsert */
    @Autowired
    private WkTblPoliOrgBalancesheetReportItemWriter wkTblPoliOrgBalancesheetReportItemWriter;

    /** リーダUpdate */
    @Autowired
    private SaishinWkTblBalancesheetItemReader saishinWkTblBalancesheetItemReader;

    /** ライタUpdate */
    @Autowired
    private UpdateFinishedTaskPlanBalancesheetDetailItemWriter updateFinishedTaskPlanBalancesheetDetailItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return ジョブ
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT_NAME) final Step stepInsert,
            @Qualifier(STEP_UPDATE_NAME) final Step stepUpdate) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepInsert)
                .next(stepUpdate).end().build();
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

        // プロセッサをまとめる
        List<ItemProcessor<?, ?>> delegates = new ArrayList<>();
        delegates.add(unCompressedFileToReadXmlDtoProcessor);
        delegates.add(readXmlDtoToWrokTableEntityProcessor);

        CompositeItemProcessor<TaskPlanBalancesheetDetailEntity, WkTblPoliOrgBalancesheetReportEntity> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(delegates);

        return new StepBuilder(STEP_INSERT_NAME, jobRepository)
                .<TaskPlanBalancesheetDetailEntity, WkTblPoliOrgBalancesheetReportEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(taskPlanBalancesheetDetailItemReader).processor(compositeItemProcessor)
                .writer(wkTblPoliOrgBalancesheetReportItemWriter).build();
    }

    /**
     * StepUpdateを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_UPDATE_NAME)
    protected Step getStepUpdate(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_UPDATE_NAME, jobRepository)
                .<WkTblPoliOrgBalancesheetReportEntity, WkTblPoliOrgBalancesheetReportEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(saishinWkTblBalancesheetItemReader).writer(updateFinishedTaskPlanBalancesheetDetailItemWriter)
                .build();
    }

}
