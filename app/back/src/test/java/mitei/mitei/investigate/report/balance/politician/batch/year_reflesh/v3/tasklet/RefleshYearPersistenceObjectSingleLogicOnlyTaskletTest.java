package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RefleshYearPersistenceObjectSingleLogicOnlyTasklet単体テスト
 */
class RefleshYearPersistenceObjectSingleLogicOnlyTaskletTest {

    /** 複写元となる年数 */
    private static final  String baseYear = "2022";
    /** 複写先となる年数 */
    private static final  String copyYear = "2023";

    /** Logicのベースディレクトリ */
    private static final  String dirLogic = "未指定";
    /** Logictestのベースディレクトリ */
    private static final  String dirLogicTest = "未指定";
    /** LogicTestSQLのベースディレクトリ */
    private static final  String dirLogicTestSql = "未指定";

    /** テスト対象 */
    @Autowired
    private RefleshYearPersistenceObjectSingleLogicOnlyTasklet refleshYearPersistenceObjectSingleLogicOnlyTasklet;

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {

        refleshYearPersistenceObjectSingleLogicOnlyTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, refleshYearPersistenceObjectSingleLogicOnlyTasklet.execute(null, null),
                "とりあえず例外なく最後まで走る");

        fail("Not yet implemented");
    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addString("baseYear", baseYear).addString("copyYear", copyYear).addString("dirLogic", dirLogic)
                .addString("dirLogicTest", dirLogicTest).addString("dirLogicTestSql", dirLogicTestSql)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
