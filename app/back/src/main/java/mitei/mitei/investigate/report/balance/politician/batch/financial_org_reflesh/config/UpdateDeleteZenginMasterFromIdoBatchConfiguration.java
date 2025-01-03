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

import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.DeleteFinancialTenpoChangeItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.DeleteZenginTenpoMasterItemWriter;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.EraseChangeIdoDeleteRowTasklet;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgChangeMasterProcessor;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 異動テーブルからマスタテーブルに廃止かつ移動先決定データを複写する
 */
@Configuration
public class UpdateDeleteZenginMasterFromIdoBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "updateDeleteZenginMasterFromIdo";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** 変更テーブルStep名 */
    public static final String STEP_DELETE_NAME = FUNCTION_NAME + "Delete" + STEP;

    /** 変更テーブルStep名 */
    public static final String STEP_ERASE_NAME = FUNCTION_NAME + "Erase" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 500;

    /** 異動テーブル移転終了フラグ設定Tasklet */
    @Autowired
    private EraseChangeIdoDeleteRowTasklet eraseChangeIdoDeleteRowTasklet;

    /** 異動先指定済み変更テーブルItemReader */
    @Autowired
    private DeleteFinancialTenpoChangeItemReader deleteFinancialTenpoChangeItemReader;

    /** 変更テーブルマスタProcessor */
    @Autowired
    private ZenginOrgChangeMasterProcessor zenginOrgChangeMasterProcessor;

    /** マスタテーブル削除ItemWeriter */
    @Autowired
    private DeleteZenginTenpoMasterItemWriter deleteZenginTenpoMasterItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_DELETE_NAME) final Step stepDelete,
            @Qualifier(STEP_ERASE_NAME) final Step stepErase) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepDelete)
                .next(stepErase).end().build();
    }

    // TODO 廃止店舗決定に合わせて変更すべき項目が出てきたら
    // DeleteFinancialTenpoChangeItemReaderで呼び出して
    // 目的テーブルEntityに変換するStepを追加する

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_DELETE_NAME)
    protected Step getStepDelete(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_DELETE_NAME, jobRepository)
                .<ZenginOrgChangeBranchEntity, ZenginOrgBranchMasterEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(deleteFinancialTenpoChangeItemReader).processor(zenginOrgChangeMasterProcessor)
                .writer(deleteZenginTenpoMasterItemWriter).build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_ERASE_NAME)
    protected Step getStepErase(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ERASE_NAME, jobRepository)
                .tasklet(eraseChangeIdoDeleteRowTasklet, transactionManager).build();
    }

}
