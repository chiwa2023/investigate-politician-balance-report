package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
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
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.PersonDto;
import mitei.mitei.investigate.report.balance.politician.util.GetTestResourceUtil;

/**
 * ReadCsvPersonItemReader単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadCsvPersonItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ReadCsvPersonItemReader readCsvPersonItemReader;

    private StepExecution getStepExecution() throws URISyntaxException {

        String paramPath = GetTestResourceUtil.practice("batch/trial/person.csv").toString();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFileName", paramPath).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    @Test
    void testRead() throws Exception {

        StepExecution execution = getStepExecution();
        readCsvPersonItemReader.beforeStep(execution);

        readCsvPersonItemReader.open(execution.getExecutionContext());
        readCsvPersonItemReader.read(); // 1行目をテストしてもいいけどあえて空打ち
        PersonDto personDto = readCsvPersonItemReader.read();

        assertThat(personDto.getFirstName()).isEqualTo("first");
        assertThat(personDto.getLastName()).isEqualTo("last");
        assertThat(personDto.getExamResult()).isEqualTo(48);
        assertThat(personDto.getYearOfJoining()).isEqualTo(1);
        assertThat(personDto.getTrainingAmount()).isEqualTo(100);

        readCsvPersonItemReader.close();

    }
}
