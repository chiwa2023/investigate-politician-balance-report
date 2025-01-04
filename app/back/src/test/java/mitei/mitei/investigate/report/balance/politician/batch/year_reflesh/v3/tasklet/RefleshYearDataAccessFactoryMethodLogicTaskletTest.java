package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
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
 * RefleshYearDataAccessFactoryMethodLogicTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessFactoryMethodLogicTaskletTest {
    // CHECKSTYLE:OFF

    /** 複写対象年 */
    private static final int copyYear = 2023;

    /** ファクトリメソッドクラス */
    private static final String filePath = "main/java/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/SearchIncomeAndOutcomeBySearchWordsService";

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessFactoryMethodLogicTasklet refleshYearDataAccessFactoryMethodLogicTasklet;

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {

        refleshYearDataAccessFactoryMethodLogicTasklet.beforeStep(this.getStepExecution());

        assertEquals(RepeatStatus.FINISHED, refleshYearDataAccessFactoryMethodLogicTasklet.execute(null, null),
                "とりあえず例外なく最後まで動作する");

        assertEquals(true, Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(filePath) + "NewDiff.java")),
                "上書きフラグオフのときは元ファイル+NewDiffファイルを生成");
    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addString("copyYear", String.valueOf(copyYear)).addString("isReadFileReflesh", "false")
                .addString("file", filePath + ".java").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
