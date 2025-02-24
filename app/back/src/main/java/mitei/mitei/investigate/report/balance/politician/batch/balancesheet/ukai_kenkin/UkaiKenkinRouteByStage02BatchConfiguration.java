package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

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

import mitei.mitei.investigate.report.balance.politician.batch.task_plan.FinishTaskPlanTasklet;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;

/**
 * 迂回献金5階層抽出処理Batch
 */
@Configuration
public class UkaiKenkinRouteByStage02BatchConfiguration {

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 50;

    /** 機能名 */
    private static final String FUNCTION_NAME = "ukaiKenkinRouteByStage02";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** クリーンStep名 */
    public static final String STEP_CLEAN_NAME = FUNCTION_NAME + "Clean" + STEP;

    /** 抽出階層0Step名 */
    public static final String STEP_STAGE_0 = FUNCTION_NAME + "Stage00" + STEP;

    /** 抽出階層1Step名 */
    public static final String STEP_STAGE_1 = FUNCTION_NAME + "Stage01" + STEP;

    /** 抽出階層2Step名 */
    public static final String STEP_STAGE_2 = FUNCTION_NAME + "Stage02" + STEP;

    /** 個人・企業経路抽出Step名 */
    public static final String STEP_ROUTE_PERSON_CORP = FUNCTION_NAME + "RoutePersonCorp" + STEP;

    /** 政治団体経路抽出Step名 */
    public static final String STEP_ROUTE_POLI_ORG = FUNCTION_NAME + "RoutePoliOrg" + STEP;

    /** Step名 */
    public static final String STEP_TASK_PLAN_NAME = FUNCTION_NAME + "TaskPlan" + STEP;

    /** ワークテーブル消去Tasklet */
    @Autowired
    private EraseWkTblUkaiKenkinDataTasklet eraseWkTblUkaiKenkinDataTasklet;

    /** 階層0Tasklet */
    @Autowired
    private CreateUkaiKenkinDataStageZeroTasklet createUkaiKenkinDataStageZeroTasklet;

    /** 階層共通収支報告書収入から迂回献金明細変換Processor */
    @Autowired
    private UkaiKenkinBalancesheetIncomeMeisaiItemProcessor ukaiKenkinBalancesheetIncomeMeisaiItemProcessor;

    /** 階層共通収支報告書収入から迂回献金明細変換Processor */
    @Autowired
    private UkaiKenkinMeisaiItemWriter ukaiKenkinMeisaiItemWriter;

    /** 階層1ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage01TimesItemReader ukaiKenkinIncomeStage01TimesItemReader;

    /** 階層2ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage02TimesItemReader ukaiKenkinIncomeStage02TimesItemReader;

    /** 経路抽出個人・企業Tasklet */
    @Autowired
    private PickupUkaiKenkinPersonAndCorpTasklet pickupUkaiKenkinPersonAndCorpTasklet;

    /** 経路抽出政治団体Tasklet */
    @Autowired
    private PickupUkaiKenkinPoliOrgTasklet pickupUkaiKenkinPoliOrgTasklet;

    /** タスク計画終了フラグTasklet */
    @Autowired
    private FinishTaskPlanTasklet finishTaskPlanTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CLEAN_NAME) final Step stepClean,
            @Qualifier(STEP_STAGE_0) final Step stepStage0, @Qualifier(STEP_STAGE_1) final Step stepStage1,
            @Qualifier(STEP_STAGE_2) final Step stepStage2,
            @Qualifier(STEP_ROUTE_PERSON_CORP) final Step stepPickupPerson,
            @Qualifier(STEP_ROUTE_POLI_ORG) final Step stepPickupOrg,
            @Qualifier(STEP_TASK_PLAN_NAME) final Step stepTaskPlan) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepStage0).next(stepStage1).next(stepStage2) // ここまで階層
                .next(stepPickupPerson).next(stepPickupOrg).next(stepTaskPlan).end().build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_CLEAN_NAME)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN_NAME, jobRepository)
                .tasklet(eraseWkTblUkaiKenkinDataTasklet, transactionManager).build();
    }

    /**
     * 取得階層0 このステップのみ政治団体だけでなく個人・企業データも抽出する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_0)
    protected Step getPickupStage00(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_0, jobRepository)
                .tasklet(createUkaiKenkinDataStageZeroTasklet, transactionManager).build();
    }

    /**
     * 取得階層1
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_1)
    protected Step getPickupStage01(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_1, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage01TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 取得階層2
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_2)
    protected Step getPickupStage02(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_2, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage02TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 経路抽出個人・企業
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_ROUTE_PERSON_CORP)
    protected Step getRoutePersonCorp(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ROUTE_PERSON_CORP, jobRepository)
                .tasklet(pickupUkaiKenkinPersonAndCorpTasklet, transactionManager).build();
    }

    /**
     * 経路抽出個人・企業
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_ROUTE_POLI_ORG)
    protected Step getRoutePoliOrg(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ROUTE_POLI_ORG, jobRepository)
                .tasklet(pickupUkaiKenkinPoliOrgTasklet, transactionManager).build();
    }

    /**
     * StepIncomeを返却する
     *
     * @param jobRepository      ジョブレポジトリ
     * @param transactionManager トランザクションマネージャ
     * @return step
     */
    @Bean(STEP_TASK_PLAN_NAME)
    protected Step getStepTaskPlan(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_TASK_PLAN_NAME, jobRepository).tasklet(finishTaskPlanTasklet, transactionManager)
                .build();
    }


}
