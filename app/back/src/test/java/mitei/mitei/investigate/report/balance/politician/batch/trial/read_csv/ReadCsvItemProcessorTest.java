package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.PersonDto;
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportDto;

/**
 * ReadCsvItemProcessor単体テスト
 */
class ReadCsvItemProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    void testProcess()throws Exception {

        //プロセッサのテストはBoot機能と一切関係なし!
        ReadCsvItemProcessor readCsvItemProcessor = new ReadCsvItemProcessor();
        
        PersonDto personDto = new PersonDto();
        
        personDto.setFirstName("first");
        personDto.setLastName("last");
        personDto.setExamResult(80);
        personDto.setYearOfJoining(3);
        personDto.setExamResult(100);
        
        ReportDto reportDto = readCsvItemProcessor.process(personDto);
        
        //本当は分岐の数だけテストが必要ですがサンプルなので…
        assertThat(reportDto.getFirstName()).isEqualTo(personDto.getFirstName());
        assertThat(reportDto.getLastName()).isEqualTo(personDto.getLastName());
        assertThat(reportDto.getGradeName()).isEqualTo("グレードA");
    }

}
