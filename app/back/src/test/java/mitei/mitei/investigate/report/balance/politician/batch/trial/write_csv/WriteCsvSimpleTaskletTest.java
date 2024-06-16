package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.util.GetTestResourceUtil;

/**
 * WriteCsvSimpleTasklet単体テスト
 */
@SpringBatchTest
@SpringJUnitConfig(WriteCsvSimpleTasklet.class)
class WriteCsvSimpleTaskletTest {

    /** テスト対象 */
    @Autowired
    private WriteCsvSimpleTasklet writeCsvSimpleTasklet;

    @Test
    void test() throws Exception {

        StepExecution execution = MetaDataInstanceFactory.createStepExecution();
        StepContribution contribution = execution.createStepContribution();
        ChunkContext chunk = new ChunkContext(new StepContext(execution));

        RepeatStatus status = writeCsvSimpleTasklet.execute(contribution, chunk);

        assertThat(status.name()).isEqualTo("FINISHED");

        // 想定されるファイルの中身が同一
        Path pathExpect = GetTestResourceUtil.practice("/batch/trial/tasklet_test.csv");

        String actual = Files.readString(Paths.get(WriteCsvSimpleTasklet.PATH_WRTITE));
        String expect = Files.readString(pathExpect);
        assertThat(actual).isEqualTo(expect);
    }

}
