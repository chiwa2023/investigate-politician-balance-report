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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;

/**
 * 迂回献金5階層抽出処理Batch
 */
@Configuration
public class UkaiKenkinRouteByStage08BatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "ukaiKenkinRouteByStage08";

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

    /** 抽出階層3Step名 */
    public static final String STEP_STAGE_3 = FUNCTION_NAME + "Stage03" + STEP;

    /** 抽出階層4Step名 */
    public static final String STEP_STAGE_4 = FUNCTION_NAME + "Stage04" + STEP;

    /** 抽出階層5Step名 */
    public static final String STEP_STAGE_5 = FUNCTION_NAME + "Stage05" + STEP;

    /** 抽出階層6Step名 */
    public static final String STEP_STAGE_6 = FUNCTION_NAME + "Stage06" + STEP;

    /** 抽出階層7Step名 */
    public static final String STEP_STAGE_7 = FUNCTION_NAME + "Stage07" + STEP;

    /** 抽出階層8Step名 */
    public static final String STEP_STAGE_8 = FUNCTION_NAME + "Stage08" + STEP;

    /** 個人・企業経路抽出Step名 */
    public static final String STEP_ROUTE_PERSON_CORP = FUNCTION_NAME + "RoutePersonCorp" + STEP;

    /** 政治団体経路抽出Step名 */
    public static final String STEP_ROUTE_POLI_ORG = FUNCTION_NAME + "RoutePoliOrg" + STEP;

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 50;

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

    /** 階層3ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage03TimesItemReader ukaiKenkinIncomeStage03TimesItemReader;

    /** 階層4ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage04TimesItemReader ukaiKenkinIncomeStage04TimesItemReader;

    /** 階層5ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage05TimesItemReader ukaiKenkinIncomeStage05TimesItemReader;

    /** 階層6ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage06TimesItemReader ukaiKenkinIncomeStage06TimesItemReader;

    /** 階層7ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage07TimesItemReader ukaiKenkinIncomeStage07TimesItemReader;

    /** 階層8ItemReader */
    @Autowired
    private UkaiKenkinIncomeStage08TimesItemReader ukaiKenkinIncomeStage08TimesItemReader;

    /** 経路抽出個人・企業Tasklet */
    @Autowired
    private PickupUkaiKenkinPersonAndCorpTasklet pickupUkaiKenkinPersonAndCorpTasklet;

    /** 経路抽出政治団体Tasklet */
    @Autowired
    private PickupUkaiKenkinPoliOrgTasklet pickupUkaiKenkinPoliOrgTasklet;

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
            @Qualifier(STEP_STAGE_2) final Step stepStage2, @Qualifier(STEP_STAGE_3) final Step stepStage3,
            @Qualifier(STEP_STAGE_4) final Step stepStage4, @Qualifier(STEP_STAGE_5) final Step stepStage5,
            @Qualifier(STEP_STAGE_6) final Step stepStage6, @Qualifier(STEP_STAGE_7) final Step stepStage7,
            @Qualifier(STEP_STAGE_8) final Step stepStage8, // ここまで階層
            @Qualifier(STEP_ROUTE_PERSON_CORP) final Step stepPickupPerson,
            @Qualifier(STEP_ROUTE_POLI_ORG) final Step stepPickupOrg) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepClean)
                .next(stepStage0).next(stepStage1).next(stepStage2).next(stepStage3).next(stepStage4).next(stepStage5)
                .next(stepStage6).next(stepStage7).next(stepStage8) // ここまで階層
                .next(stepPickupPerson).next(stepPickupOrg).end().build();
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
     * 取得階層3
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_3)
    protected Step getPickupStage03(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_3, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage03TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 取得階層4
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_4)
    protected Step getPickupStage04(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_4, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage04TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 取得階層5
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_5)
    protected Step getPickupStage05(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_5, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage05TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 取得階層6
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_6)
    protected Step getPickupStage06(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_6, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage06TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 取得階層7
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_7)
    protected Step getPickupStage07(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_7, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage07TimesItemReader)
                .processor(ukaiKenkinBalancesheetIncomeMeisaiItemProcessor).writer(ukaiKenkinMeisaiItemWriter).build();
    }

    /**
     * 取得階層8
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_STAGE_8)
    protected Step getPickupStage08(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_STAGE_8, jobRepository)
                .<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(ukaiKenkinIncomeStage08TimesItemReader)
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

}
