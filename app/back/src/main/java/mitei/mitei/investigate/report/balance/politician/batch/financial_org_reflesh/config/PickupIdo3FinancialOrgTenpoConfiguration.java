package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

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

import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.AddFinancialTenpoWkTbl3ItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.DeleteFinancialTenpoWkTbl3ItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.MoveFinancialTenpoWkTbl3ItemReader;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginChangeAddProcessor;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginChangeDeleteProcessor;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginChangeMoveProcessor;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgChangeBranchItemWriter;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgMasterChangeProcessor;
import mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.ZenginOrgWKTbl3ChangeProcessor;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 全銀協金融機関・店舗ワークテーブル1にcsvデータを複写する
 */
@Configuration
public class PickupIdo3FinancialOrgTenpoConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "pickupIdo3FinancialOrgTenpo";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_ADD_NAME = FUNCTION_NAME + "Add" + STEP;

    /** Step名 */
    public static final String STEP_DELETE_NAME = FUNCTION_NAME + "Delete" + STEP;

    /** Step名 */
    public static final String STEP_MOVE_NAME = FUNCTION_NAME + "Move" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 500;

    /** 追加用ItemReader */
    @Autowired
    private AddFinancialTenpoWkTbl3ItemReader addFinancialTenpoWkTbl3ItemReader;

    /** 移転用ItemReader */
    @Autowired
    private MoveFinancialTenpoWkTbl3ItemReader moveFinancialTenpoWkTbl3ItemReader;

    /** 閉店用ItemReader */
    @Autowired
    private DeleteFinancialTenpoWkTbl3ItemReader deleteFinancialTenpoWkTbl3ItemReader;

    /** 金融機関・支店ワークテーブル1異動テーブル変換プロセッサ */
    @Autowired
    private ZenginOrgWKTbl3ChangeProcessor zenginOrgWKTbl3ChangeProcessor;

    /** 金融機関・支店マスタテーブル異動テーブル変換プロセッサ */
    @Autowired
    private ZenginOrgMasterChangeProcessor zenginOrgMasterChangeProcessor;

    /** 異動テーブル追加プロセッサ */
    @Autowired
    private ZenginChangeAddProcessor zenginChangeAddProcessor;

    /** 異動テーブル廃止プロセッサ */
    @Autowired
    private ZenginChangeDeleteProcessor zenginChangeDeleteProcessor;

    /** 異動テーブル移転プロセッサ */
    @Autowired
    private ZenginChangeMoveProcessor zenginChangeMoveProcessor;

    /** 異動テーブルitemWriter */
    @Autowired
    private ZenginOrgChangeBranchItemWriter zenginOrgChangeBranchItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_ADD_NAME) final Step stepAdd,
            @Qualifier(STEP_MOVE_NAME) final Step stepMove, @Qualifier(STEP_DELETE_NAME) final Step stepDelete) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepAdd).next(stepMove)
                .next(stepDelete).end().build();
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

        // プロセッサをまとめる
        List<ItemProcessor<?, ?>> delegates = new ArrayList<>();
        delegates.add(zenginOrgWKTbl3ChangeProcessor);
        delegates.add(zenginChangeAddProcessor);

        CompositeItemProcessor<ZenginOrgBranchWk3Entity, ZenginOrgChangeBranchEntity> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(delegates);

        return new StepBuilder(STEP_ADD_NAME, jobRepository)
                .<ZenginOrgBranchWk3Entity, ZenginOrgChangeBranchEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(addFinancialTenpoWkTbl3ItemReader).processor(compositeItemProcessor)
                .writer(zenginOrgChangeBranchItemWriter).build();
    }

    /**
     * StepMoveを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_MOVE_NAME)
    protected Step getStepMove(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        // プロセッサをまとめる
        List<ItemProcessor<?, ?>> delegates = new ArrayList<>();
        delegates.add(zenginOrgWKTbl3ChangeProcessor);
        delegates.add(zenginChangeMoveProcessor);

        CompositeItemProcessor<ZenginOrgBranchWk3Entity, ZenginOrgChangeBranchEntity> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(delegates);

        return new StepBuilder(STEP_MOVE_NAME, jobRepository)
                .<ZenginOrgBranchWk3Entity, ZenginOrgChangeBranchEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(moveFinancialTenpoWkTbl3ItemReader).processor(compositeItemProcessor)
                .writer(zenginOrgChangeBranchItemWriter).build();
    }

    /**
     * StepDeleteを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_DELETE_NAME)
    protected Step getStepDelete(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        // プロセッサをまとめる
        List<ItemProcessor<?, ?>> delegates = new ArrayList<>();
        delegates.add(zenginOrgMasterChangeProcessor);
        delegates.add(zenginChangeDeleteProcessor);

        CompositeItemProcessor<ZenginOrgBranchMasterEntity, ZenginOrgChangeBranchEntity> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(delegates);

        return new StepBuilder(STEP_DELETE_NAME, jobRepository)
                .<ZenginOrgBranchMasterEntity, ZenginOrgChangeBranchEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(deleteFinancialTenpoWkTbl3ItemReader).processor(compositeItemProcessor)
                .writer(zenginOrgChangeBranchItemWriter).build();
    }

}
