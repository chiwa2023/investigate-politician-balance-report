package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * 全銀協csvファイルを読み取りするItemReader
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class FinancialOrgCsvItemReader extends FlatFileItemReader<FinancialOrgCsvDto> {

    /** 保存親フォルダ */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /**
     * コンストラクタ
     *
     * @param lineMapper 行解析Mapper
     */
    public FinancialOrgCsvItemReader(final @Autowired FinancialOrgCsvLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
    }
    
    /**
     * 実行条件(読み取りファイル)を取得する
     *
     * @param stepExecution Step実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        
        String filePath = stepExecution.getJobParameters().getString("readFileName");
        Path path = Paths.get(storageFolder, filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }

}
