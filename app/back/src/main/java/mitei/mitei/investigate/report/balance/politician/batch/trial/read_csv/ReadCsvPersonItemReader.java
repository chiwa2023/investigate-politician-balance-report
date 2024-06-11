package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.PersonDto;
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.PersonLineMapper;


/**
 * 個人の勤続年数、テスト結果が記載されたcsvを読み取りするリーダ
 */
@Component
public class ReadCsvPersonItemReader  extends FlatFileItemReader<PersonDto>{

    /**
     * コンストラクタ
     *
     * @param lineMapper 行解析Mapper
     */
    public ReadCsvPersonItemReader(final PersonLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
    }

    /**
     * 実行条件(読み取りファイル)を取得する
     *
     * @param stepExecution Step実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution){
        Path path = Paths.get(stepExecution.getJobParameters().getString("readFileName"));
        super.setResource(new FileSystemResource(path.toFile()));
    }
    
}
