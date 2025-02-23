package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

/**
 * RefleshYearPersistenceObjectInSameDirectoryTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearPersistenceObjectInSameDirectoryTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RefleshYearPersistenceObjectInSameDirectoryTasklet refleshYearPersistenceObjectInSameDirectoryTasklet;

    /** 複写元年 */
    private static final int baseYear = 2024;

    /** 複写対象年 */
    private static final int copyYear = 2023;

    /** Entityディレクトリ */
    private static final String dirEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/task_plan";

    /** Repositoryディレクトリ */
    private static final String dirRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/task_plan";

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {

        refleshYearPersistenceObjectInSameDirectoryTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, refleshYearPersistenceObjectInSameDirectoryTasklet.execute(null, null),
                "とりあえず例外なく最後まで走る");

    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addString("baseYear", String.valueOf(baseYear)).addString("copyYear", String.valueOf(copyYear))
                .addString("dirEntity", String.valueOf(dirEntity))
                .addString("dirRepository", String.valueOf(dirRepository)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
