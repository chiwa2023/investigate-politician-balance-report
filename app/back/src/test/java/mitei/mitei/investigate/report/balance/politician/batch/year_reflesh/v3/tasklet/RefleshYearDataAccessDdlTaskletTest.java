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
 * RefleshYearDataAccessDdlTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessDdlTaskletTest {

    /** 複写元となる年数 */
    private static final String baseYear = "2022";
    /** 複写先となる年数 */
    private static final String copyYear = "2025";

    /** Entityの存在するディレクトリ */
    private static final String dirEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_org/balancesheet/y";

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessDdlTasklet refleshYearDataAccessDdlTasklet;
    
    @Test
    void test()throws Exception {
        
        refleshYearDataAccessDdlTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, refleshYearDataAccessDdlTasklet.execute(null, null),
                "とりあえず例外なく最後まで走る");

        fail("Not yet implemented");
    }
    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addString("baseYear", String.valueOf(baseYear)).addString("copyYear", String.valueOf(copyYear))
                .addString("dirEntity", dirEntity)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
