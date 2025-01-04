package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * RefleshPersistenceObjectSingleBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshSingleLogicOnlyBatchConfigurationTest {

    /** JobParameter(複写元年数) */
    private static final int BASE_YEAR = 2022;

    /** JobParameter(複写先年数) */
    private static final int COPY_YEAR = 2023;

    /** JobParameter(Logicのベースディレクトリ) */
    private static final String DIR_LOGIC = "main/java/mitei/mitei/investigate/report/balance/politician/logic/natural_search/y";

    /** JobParameter(TestLogicのベースディレクトリ) */
    private static final String DIR_LOGIC_TEST = "test/java/mitei/mitei/investigate/report/balance/politician/logic/natural_search/y";

    /** JobParameter(TestSQLのベースディレクトリ) */
    private static final String DIR_LOGIC_TEST_SQL = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/natural_search/y";

    /** JobParameter(FactoryMethodClass) */
    private static final String DIR_FACTORY = "main/java/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/SearchIncomeAndOutcomeBySearchWordsService";

    /** JobParameter(FactoryMethodClassのテスト) */
    private static final String DIR_FACTORY_TEST = "test/java/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/SearchIncomeAndOutcomeBySearchWordsServiceTest";

    /** JobParameter(FactoryMethodClassのテストSQL) */
    private static final String DIR_SQL1 = "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/truncate_income_outcome_2022.sql";
    /** JobParameter(FactoryMethodClassのテストSQL) */
    private static final String DIR_SQL2 = "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_income_horie_2022.sql";
    /** JobParameter(FactoryMethodClassのテストSQL) */
    private static final String DIR_SQL3 = "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_income_joshi_2022.sql";
    /** JobParameter(FactoryMethodClassのテストSQL) */
    private static final String DIR_SQL4 = "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_outcome_horie_2022.sql";
    /** JobParameter(FactoryMethodClassのテストSQL) */
    private static final String DIR_SQL5 = "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_outcome_joshi_2022.sql";

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(RefleshSingleLogicOnlyBatchConfiguration.JOB_NAME)
    @Autowired
    private Job refleshSingleLogicOnly;

    @Test
    @Tag("SourceReflesh")
    void testJob() {
        assertEquals(RefleshSingleLogicOnlyBatchConfiguration.JOB_NAME, refleshSingleLogicOnly.getName(), "Job名が一致");
    }

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {

        jobLauncherTestUtils.setJob(refleshSingleLogicOnly);

        JobParameters jobParameters = new JobParametersBuilder(
                refleshSingleLogicOnly.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("baseYear", String.valueOf(BASE_YEAR))
                .addString("copyYear", String.valueOf(COPY_YEAR)).addString("dirLogic", DIR_LOGIC)
                .addString("dirLogicTest", DIR_LOGIC_TEST).addString("dirLogicTestSql", DIR_LOGIC_TEST_SQL)
                .addString("isReadFileReflesh", "false").addString("file", DIR_FACTORY + ".java")
                .addString("java", DIR_FACTORY_TEST + ".java").addString("sql1", DIR_SQL1).addString("sql2", DIR_SQL2)
                .addString("sql3", DIR_SQL3).addString("sql4", DIR_SQL4).addString("sql5", DIR_SQL5).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
        
        assertEquals(true, Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_LOGIC) + COPY_YEAR)),
                "複写年フォルダが生成されている(Logic)");

        assertEquals(true, Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_LOGIC_TEST) + COPY_YEAR)),
                "複写年フォルダが生成されている(TestLogic)");

        assertEquals(true, Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_FACTORY) + "NewDiff.java")),
                "上書きフラグオフのときは元ファイル+NewDiffファイルを生成");

        assertEquals(true,
                Files.exists(Paths.get(GetCurrentResourcePath.getBackSrcPath(DIR_FACTORY_TEST) + "NewDiff.java")),
                "上書きフラグオフのときは元テストファイル+NewDiffファイルを生成");
    }

}
