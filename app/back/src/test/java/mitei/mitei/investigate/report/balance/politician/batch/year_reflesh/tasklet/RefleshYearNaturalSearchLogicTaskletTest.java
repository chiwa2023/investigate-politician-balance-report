package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet;

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
 * RefleshYearNaturalSearchLogicTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearNaturalSearchLogicTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearNaturalSearchLogicTasklet refleshYearNaturalSearchLogicTasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2022;
    /** 複写先報告年 */
    private static final int copyYear = 2025;

    /** ベースフォルダLogic */
    private static final String pathFunctionLogic = "main/java/mitei/mitei/investigate/report/balance/politician/logic/natural_search/y";

    /** ベースフォルダLogicTest */
    private static final String pathFunctionLogicTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/natural_search/y";

    /** ベースフォルダLogicTest用SQL */
    private static final String pathFunctionTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/natural_search/y";

    @Test
    @Tag("SourceReflesh")
    void testExecute() throws Exception {

        StepExecution execution = this.getStepExecution();
        refleshYearNaturalSearchLogicTasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearNaturalSearchLogicTasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);

        /* ファイルの存在だけ確認 */

        final String expandText = "Logic.java";
        final String expandTestText = "LogicTest.java";
        final String expandSqlText = ".sql";
        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        /*
         * 元ファイル存在確認
         */
        String pathBaseAbsLogic = GetCurrentResourcePath.getBackSrcPath(pathFunctionLogic + baseYear);
        String pathBaseAbsLogicTest = GetCurrentResourcePath.getBackSrcPath(pathFunctionLogicTest + baseYear);
        String pathBaseAbsLogicSql = GetCurrentResourcePath.getBackSrcPath(pathFunctionTestSql + baseYear);

        final String fileNameJava = "NaturalAllSentenceSearchY";
        final String fileNameSQL = "natural_search_test_";

        Path pathLogic01 = Paths.get(pathBaseAbsLogic, fileNameJava + baseYear + expandText);
        assertTrue(Files.exists(pathLogic01), fileExistText);

        Path pathLogicTest01 = Paths.get(pathBaseAbsLogicTest, fileNameJava + baseYear + expandTestText);
        assertTrue(Files.exists(pathLogicTest01), fileExistText);

        Path pathSql01 = Paths.get(pathBaseAbsLogicSql, fileNameSQL + baseYear + expandSqlText);
        assertTrue(Files.exists(pathSql01), fileExistText);

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        /*
         * 生成確認
         */
        String pathCopyAbsLogic = GetCurrentResourcePath.getBackSrcPath(pathFunctionLogic + copyYear);
        Path pathCopyLogic01 = Paths.get(pathCopyAbsLogic, fileNameJava + copyYear + expandText);
        assertTrue(Files.exists(pathCopyLogic01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyLogic01).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsLogicTest = GetCurrentResourcePath.getBackSrcPath(pathFunctionLogicTest + copyYear);
        Path pathCopyLogicTest01 = Paths.get(pathCopyAbsLogicTest, fileNameJava + copyYear + expandTestText);
        assertTrue(Files.exists(pathCopyLogicTest01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyLogicTest01).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsLogicSql = GetCurrentResourcePath.getBackSrcPath(pathFunctionTestSql + copyYear);
        Path pathCopySql01 = Paths.get(pathCopyAbsLogicSql, fileNameSQL + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopySql01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySql01).toInstant().isAfter(kijunTime), updateText);
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
