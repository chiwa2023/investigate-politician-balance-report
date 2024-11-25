package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.same_dir;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Tag;
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
 * RefleshYearDataAccessLogicAndLogicTestTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessLogicAndLogicTestTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessLogicAndLogicTestTasklet refleshYearDataAccessLogicAndLogicTestTasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2024;
    /** 複写先報告年 */
    private static final int copyYear = 2022;

    /** メール通知Logic */
    private static final String pathFunctionMail = "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_alert/mail/y";
    /** メール計画Logic */
    private static final String pathFunctionTask = "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y";
    /** 書証保存Logic */
    private static final String pathFunctionStorage = "main/java/mitei/mitei/investigate/report/balance/politician/logic/storage/y";

    /** メール通知LogicTest */
    private static final String pathFunctionMailTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_alert/mail/y";
    /** メール計画LogicTest */
    private static final String pathFunctionTaskTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y";
    /** 書証保存LogicTest */
    private static final String pathFunctionStorageTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/storage/y";

    /** メール通知LogicTest用SQL */
    private static final String pathFunctionMailTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/task_alert/mail/y";
    /** メール計画LogicTest用SQL */
    private static final String pathFunctionTaskTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y";
    /** 書証保存LogicTest用SQL */
    private static final String pathFunctionStorageTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/storage/y";

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        refleshYearDataAccessLogicAndLogicTestTasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearDataAccessLogicAndLogicTestTasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        /* Logic */
        final String expandTextLogic = "Logic.java";

        String pathCopyAbsAlertMail = GetCurrentResourcePath.getBackSrcPath(pathFunctionMail + copyYear);
        Path pathCopyAlertMail = Paths.get(pathCopyAbsAlertMail, "InsertMailInfo" + copyYear + expandTextLogic);
        assertTrue(Files.exists(pathCopyAlertMail), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyAlertMail).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsTaskPlan = GetCurrentResourcePath.getBackSrcPath(pathFunctionTask + copyYear);
        Path pathCopyTaskPlan = Paths.get(pathCopyAbsTaskPlan, "InsertTaskPlanY" + copyYear + expandTextLogic);
        assertTrue(Files.exists(pathCopyTaskPlan), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskPlan).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsSaveStorage = GetCurrentResourcePath.getBackSrcPath(pathFunctionStorage + copyYear);
        Path pathCopySaveStorage = Paths.get(pathCopyAbsSaveStorage, "SaveStorageFileY" + copyYear + expandTextLogic);
        assertTrue(Files.exists(pathCopySaveStorage), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySaveStorage).toInstant().isAfter(kijunTime), updateText);

        /* Logic Test */
        final String expandTextLogicTest = "LogicTest.java";

        String pathCopyAbsAlertMailTest = GetCurrentResourcePath.getBackSrcPath(pathFunctionMailTest + copyYear);
        Path pathCopyAlertMailTest = Paths.get(pathCopyAbsAlertMailTest,
                "InsertMailInfo" + copyYear + expandTextLogicTest);
        assertTrue(Files.exists(pathCopyAlertMailTest), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyAlertMailTest).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsTaskPlanTest = GetCurrentResourcePath.getBackSrcPath(pathFunctionTaskTest + copyYear);
        Path pathCopyTaskPlanTest = Paths.get(pathCopyAbsTaskPlanTest,
                "InsertTaskPlanY" + copyYear + expandTextLogicTest);
        assertTrue(Files.exists(pathCopyTaskPlanTest), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskPlanTest).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsSaveStorageTest = GetCurrentResourcePath.getBackSrcPath(pathFunctionStorageTest + copyYear);
        Path pathCopySaveStorageTest = Paths.get(pathCopyAbsSaveStorageTest,
                "SaveStorageFileY" + copyYear + expandTextLogicTest);
        assertTrue(Files.exists(pathCopySaveStorageTest), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySaveStorageTest).toInstant().isAfter(kijunTime), updateText);

        /* Logic Test SQL */
        final String expandTextLogicTestSql = ".sql";

        String pathCopyAbsAlertMailTestSql = GetCurrentResourcePath.getBackSrcPath(pathFunctionMailTestSql + copyYear);
        Path pathCopyAlertMailTestSql = Paths.get(pathCopyAbsAlertMailTestSql,
                "truncate_alert_mail_" + copyYear + expandTextLogicTestSql);
        assertTrue(Files.exists(pathCopyAlertMailTestSql), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyAlertMailTestSql).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsTaskPlanTestSql = GetCurrentResourcePath.getBackSrcPath(pathFunctionTaskTestSql + copyYear);
        Path pathCopyTaskPlanTestSql = Paths.get(pathCopyAbsTaskPlanTestSql,
                "truncate_task_plan_" + copyYear + expandTextLogicTestSql);
        assertTrue(Files.exists(pathCopyTaskPlanTestSql), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskPlanTestSql).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsSaveStorageTestSql = GetCurrentResourcePath
                .getBackSrcPath(pathFunctionStorageTestSql + copyYear);
        Path pathCopySaveStorageTestSql = Paths.get(pathCopyAbsSaveStorageTestSql,
                "truncate_save_file_storage_" + copyYear + expandTextLogicTestSql);
        assertTrue(Files.exists(pathCopySaveStorageTestSql), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySaveStorageTestSql).toInstant().isAfter(kijunTime), updateText);

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
