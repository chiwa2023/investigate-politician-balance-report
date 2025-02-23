package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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

/**
 * RefleshYearPersistenceObjectBalancesheetAndUsageTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearPersistenceObjectBalancesheetAndUsageTaskletTest {

    /** 複写元となる年数 */
    private static final String baseYear = "2022";
    /** 複写先となる年数 */
    private static final String copyYear = "2025";

    /** Entityのベースディレクトリ */
    private static final String dirEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_org/balancesheet/y";
    /** Repositoryのベースディレクトリ */
    private static final String dirRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/poli_org/balancesheet/y";
    /** Logicのベースディレクトリ */
    private static final String dirLogic = "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y";
    /** LogicTestのベースディレクトリ */
    private static final String dirLogicTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y";
    /** TestSQLのベースディレクトリ */
    private static final String dirLogicTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y";

    /** テスト対象 */
    @Autowired
    private RefleshYearPersistenceObjectBalancesheetAndUsageTasklet refleshYearPersistenceObjectBalancesheetAndUsageTasklet;

    @Test
    void test() throws Exception {

        // 実行処理
        refleshYearPersistenceObjectBalancesheetAndUsageTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, refleshYearPersistenceObjectBalancesheetAndUsageTasklet.execute(null, null),
                "とりあえず例外なく最後まで動作する");

        fail("Not yet implemented");
    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addString("baseYear", baseYear).addString("copyYear", copyYear).addString("dirEntity", dirEntity)
                .addString("dirRepository", dirRepository).addString("dirLogic", dirLogic)
                // .addString("dirLogicIncome",dirLogicIncome)
                .addString("dirLogicTest", dirLogicTest)
                // .addString("dirLogicTestIncome",dirLogicTestIncome)
                .addString("dirLogicTestSql", dirLogicTestSql).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
