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

import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.AddFinancialTenpoChangeItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.AddZenginTenpoMasterItemWriter;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.EraseChangeIdoAddRowTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.EraseChangeIdoMoveRowTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.MoveFinancialTenpoChangeItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.MoveZenginTenpoMasterItemWriter;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgChangeMasterProcessor;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 異動テーブルからマスタテーブルにデータを複写する
 */
@Configuration
public class UpdateAddAndMoveZenginMasterFromIdoBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "updateAddAndMoveZenginMasterFromIdo";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** 追加Step名 */
    public static final String STEP_ADD_NAME = FUNCTION_NAME + "Add" + STEP;

    /** 移転Step名 */
    public static final String STEP_MOVE_NAME = FUNCTION_NAME + "Move" + STEP;

    /** 異動テーブル処理フラグ消去Step名 */
    public static final String STEP_ERASE_ADD_NAME = FUNCTION_NAME + "EraseAdd" + STEP;

    /** 異動テーブル処理フラグ消去Step名 */
    public static final String STEP_ERASE_MOVE_NAME = FUNCTION_NAME + "EraseMove" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 50;

    /** 異動テーブル追加ItemReader */
    @Autowired
    private AddFinancialTenpoChangeItemReader addFinancialTenpoChangeItemReader;

    /** 異動テーブル移転ItemReader */
    @Autowired
    private MoveFinancialTenpoChangeItemReader moveFinancialTenpoChangeItemReader;

    /** 金融機関・支店マスタ追加ItemWriter */
    @Autowired
    private AddZenginTenpoMasterItemWriter addZenginTenpoMasterItemWriter;

    /** 金融機関・支店マスタ移転ItemWriter */
    @Autowired
    private MoveZenginTenpoMasterItemWriter moveZenginTenpoMasterItemWriter;

    /** 異動テーブルマスタ変換プロセッサ */
    @Autowired
    private ZenginOrgChangeMasterProcessor zenginOrgChangeMasterProcessor;

    /** 異動テーブル追加終了フラグ設定Tasklet */
    @Autowired
    private EraseChangeIdoAddRowTasklet eraseChangeIdoAddRowTasklet;

    /** 異動テーブル移転終了フラグ設定Tasklet */
    @Autowired
    private EraseChangeIdoMoveRowTasklet eraseChangeIdoMoveRowTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_ADD_NAME) final Step stepAdd,
            @Qualifier(STEP_MOVE_NAME) final Step stepMove, @Qualifier(STEP_ERASE_ADD_NAME) final Step stepEraseAdd,
            @Qualifier(STEP_ERASE_MOVE_NAME) final Step stepEraseMove) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepAdd).next(stepMove)
                .next(stepEraseAdd).next(stepEraseMove).end().build();
    }

    /**
     * StepAddを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_ADD_NAME)
    protected Step getStepAdd(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ADD_NAME, jobRepository)
                .<ZenginOrgChangeBranchEntity, ZenginOrgBranchMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(addFinancialTenpoChangeItemReader).processor(zenginOrgChangeMasterProcessor)
                .writer(addZenginTenpoMasterItemWriter).build();
    }

    /**
     * StepAddを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_MOVE_NAME)
    protected Step getStepMove(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_MOVE_NAME, jobRepository)
                .<ZenginOrgChangeBranchEntity, ZenginOrgBranchMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(moveFinancialTenpoChangeItemReader).processor(zenginOrgChangeMasterProcessor)
                .writer(moveZenginTenpoMasterItemWriter).build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_ERASE_MOVE_NAME)
    protected Step getStepEraseMove(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ERASE_MOVE_NAME, jobRepository)
                .tasklet(eraseChangeIdoMoveRowTasklet, transactionManager).build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_ERASE_ADD_NAME)
    protected Step getStepEraseAdd(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ERASE_ADD_NAME, jobRepository)
                .tasklet(eraseChangeIdoAddRowTasklet, transactionManager).build();
    }

}
