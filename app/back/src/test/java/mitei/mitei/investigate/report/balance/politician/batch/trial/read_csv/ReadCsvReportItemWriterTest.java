package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportDto;
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportLineAggregator;

/**
 * ReadCsvReportItemWriter単体テスト
 */
@SpringBatchTest
@SpringJUnitConfig
class ReadCsvReportItemWriterTest {
    // CHECKSTYLE:OFF

    /** Jobパラメータ */
    private static final String paramPath = "c:/temp/report_test.csv";

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("writeFileName", paramPath).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    @Test
    void testBeforeStep() throws Exception {

        /* ジョブパラメータで設定したファイルが事前に存在するなら、テストのために消すという行為を行っているので要注意!、 */
        Path path = Paths.get(paramPath);
        if (Files.exists(path)) {
            Files.delete(path);
        }

        ReadCsvReportItemWriter readCsvReportItemWriter = new ReadCsvReportItemWriter(new ReportLineAggregator());

        assertFalse(Files.exists(path),"これから作ろうとするファイルは存在しません");

        StepExecution execution = this.getStepExecution();

        readCsvReportItemWriter.beforeStep(execution);

        ReportDto reportDto = new ReportDto();
        reportDto.setFirstName("first");
        reportDto.setLastName("last");
        reportDto.setGradeName("グレードA");
        Chunk<ReportDto> chunk = new Chunk<>(reportDto);

        readCsvReportItemWriter.open(execution.getExecutionContext());
        readCsvReportItemWriter.write(chunk);
        readCsvReportItemWriter.close();

        // write終了後、ファイルが生成されました=writerにジョブパラメータが渡ったことを意味する。・・・・かなり論理展開が苦しい
        assertTrue(Files.exists(path),"書き込み終了後指定ファイルが存在するようになった");

    }

    @Test
    void testDoWrite() throws Exception {

        ReadCsvReportItemWriter readCsvReportItemWriter = new ReadCsvReportItemWriter(new ReportLineAggregator());

        // 投入するデータを作成
        ReportDto reportDto = new ReportDto();
        reportDto.setFirstName("first");
        reportDto.setLastName("last");
        reportDto.setGradeName("グレードA");
        Chunk<ReportDto> chunk = new Chunk<>(reportDto);

        String actual = readCsvReportItemWriter.doWrite(chunk);

        assertThat(actual).isEqualTo("\"first\",\"last\",\"グレードA\"" + ReadCsvReportItemWriter.DEFAULT_LINE_SEPARATOR);
    }

}
