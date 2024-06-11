package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportDto;
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportLineAggregator;

/**
 * 試験結果と経験から認定グレードの結果を返すリポートを書き込むライタ
 */
@Component
public class ReadCsvReportItemWriter  extends FlatFileItemWriter<ReportDto>{
    
    /**
     * コンストラクタ
     *
     * @param lineAggregator 行解析Aggregator
     */
    public ReadCsvReportItemWriter(final ReportLineAggregator lineAggregator) {
        super();
        super.setLineAggregator(lineAggregator);
    }
    
    /**
     * 実行前にJobParameterから実行時条件(書き出しファイル)を取得する
     *
     * @param stepExecution ステップ実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution){
        Path path = Paths.get(stepExecution.getJobParameters().getString("writeFileName"));
        super.setResource(new FileSystemResource(path.toFile()));
    }
    
}
