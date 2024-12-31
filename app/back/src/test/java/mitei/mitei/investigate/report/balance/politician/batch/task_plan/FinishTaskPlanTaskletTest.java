package mitei.mitei.investigate.report.balance.politician.batch.task_plan;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * FinishTaskPlanTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class FinishTaskPlanTaskletTest {

    /** テスト対象 */
    @Autowired
    private FinishTaskPlanTasklet finishTaskPlanTasklet;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("update_task_plan_2024.sql")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        finishTaskPlanTasklet.beforeStep(execution);

        assertEquals(RepeatStatus.FINISHED, finishTaskPlanTasklet.execute(null, null), "終了ステータスが戻る");
    }

    private StepExecution getStepExecution() {

        final int taskPlanCode = 250;
        final long userId = 1212;
        final int userCode = 970;
        final String userName = "ユーザ";
        final String taskName = "不記載チェッカー";
        final int year = 2024;

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", userId).addLong("userCode", Long.valueOf(userCode)).addString("userName", userName)
                .addLong("taskPlanCode", Long.valueOf(taskPlanCode)).addString("taskName", taskName)
                .addLong("year", Long.valueOf(year)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
