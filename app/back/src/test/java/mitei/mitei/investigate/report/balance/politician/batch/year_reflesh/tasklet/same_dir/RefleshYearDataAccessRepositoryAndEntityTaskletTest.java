package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.same_dir;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * RefleshYearDataAccessRepositoryAndEntityTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessRepositoryAndEntityTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessRepositoryAndEntityTasklet refleshYearDataAccessRepositoryAndEntityTasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2024;
    /** 複写先報告年 */
    private static final int copyYear = 2025;

    /** タスクメール配信予定Repositoryフォルダ */
    private static final String pathFunctionMailRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/mail/";
    /** タスク予定Repositoryフォルダ */
    private static final String pathFunctionTaskRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/task_plan/";
    /** 書証保存Repositoryフォルダ */
    private static final String pathFunctionStorageRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/storage/";

    /** タスクメール配信予定Entityフォルダ */
    private static final String pathFunctionMailEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/mail/";
    /** タスク予定Entityフォルダ */
    private static final String pathFunctionTaskEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/task_plan/";
    /** 書証保存Entityフォルダ */
    private static final String pathFunctionStorageEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/storage/";

    @Test
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        refleshYearDataAccessRepositoryAndEntityTasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearDataAccessRepositoryAndEntityTasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        /* ファイルの存在だけ確認 */

        final String expandTextEntity = "Entity.java";
        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        /* Entity */
        String pathCopyAbsTaskMailEntity = GetCurrentResourcePath.getBackSrcPath(pathFunctionMailEntity);
        Path pathCopyTaskMailEntity = Paths.get(pathCopyAbsTaskMailEntity,
                "SendAlertMail" + copyYear + expandTextEntity);

        assertTrue(Files.exists(pathCopyTaskMailEntity), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskMailEntity).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsTaskPlanEntity = GetCurrentResourcePath.getBackSrcPath(pathFunctionTaskEntity);
        Path pathCopyTaskPlanEntity = Paths.get(pathCopyAbsTaskPlanEntity, "TaskPlan" + copyYear + expandTextEntity);
        assertTrue(Files.exists(pathCopyTaskPlanEntity), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskPlanEntity).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsSaveStorageEntity = GetCurrentResourcePath.getBackSrcPath(pathFunctionStorageEntity);
        Path pathCopySaveSorageEntity = Paths.get(pathCopyAbsSaveStorageEntity,
                "SaveFileStorage" + copyYear + expandTextEntity);
        assertTrue(Files.exists(pathCopySaveSorageEntity), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySaveSorageEntity).toInstant().isAfter(kijunTime), updateText);

        /* Repository */
        final String expandTextRepository = "Repository.java";

        String pathCopyAbsTaskMailRepository = GetCurrentResourcePath.getBackSrcPath(pathFunctionMailRepository);
        Path pathCopyTaskMailRepository = Paths.get(pathCopyAbsTaskMailRepository,
                "SendAlertMail" + copyYear + expandTextRepository);
        assertTrue(Files.exists(pathCopyTaskMailRepository), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskMailRepository).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsTaskPlanRepository = GetCurrentResourcePath.getBackSrcPath(pathFunctionTaskRepository);
        Path pathCopyTaskPlanRepository = Paths.get(pathCopyAbsTaskPlanRepository,
                "TaskPlan" + copyYear + expandTextRepository);
        assertTrue(Files.exists(pathCopyTaskPlanRepository), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskPlanRepository).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsSaveStorageRepository = GetCurrentResourcePath.getBackSrcPath(pathFunctionStorageRepository);
        Path pathCopySaveSorageRepository = Paths.get(pathCopyAbsSaveStorageRepository,
                "SaveFileStorage" + copyYear + expandTextRepository);
        assertTrue(Files.exists(pathCopySaveSorageRepository), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySaveSorageRepository).toInstant().isAfter(kijunTime), updateText);

    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addString("baseYear", String.valueOf(baseYear))
                .addString("copyYear", String.valueOf(copyYear)).addLocalDateTime("now", LocalDateTime.now()) // 実作業では必要としないがパラメータ一致よけ
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
