package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * FinancialOrgCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class FinancialOrgCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private FinancialOrgCsvItemReader financialOrgCsvItemReader;

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

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        String childDir = "zengin";
        String fileName = "nkyk3.csv";

        // ファイル準備(読み取り)
        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/bank/orgin", fileName);
        String srcData = Files.readString(pathSrc);

        // 確実にテストを行うため都度書き出し
        Path pathCopy = Paths.get(storageFolder, childDir, fileName);
        Files.createDirectories(pathCopy.getParent());
        Files.writeString(pathCopy, srcData);

        // 実際の処理
        String paramPath = Paths.get(childDir, fileName).toString();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFileName", paramPath).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        financialOrgCsvItemReader.beforeStep(execution);
        financialOrgCsvItemReader.open(execution.getExecutionContext());

        FinancialOrgCsvDto csvDto00 = financialOrgCsvItemReader.read();
        assertEquals("店舗コード", csvDto00.getBranchCode(), "店舗コード"); // NOPMD
        FinancialOrgCsvDto csvDto01 = financialOrgCsvItemReader.read();
        assertEquals("011", csvDto01.getBranchCode(), "店舗コード");
        FinancialOrgCsvDto csvDto02 = financialOrgCsvItemReader.read();
        assertEquals("035", csvDto02.getBranchCode(), "店舗コード");
        FinancialOrgCsvDto csvDto03 = financialOrgCsvItemReader.read();
        assertEquals("053", csvDto03.getBranchCode(), "店舗コード");

        // LineMapperのテストを流用
        FinancialOrgCsvDto csvDto04 = financialOrgCsvItemReader.read();
        assertEquals("1311", csvDto04.getOrgCode(), "金融機関コード");
        assertEquals("035", csvDto04.getBranchCode(), "店舗コード");
        assertEquals("トウキョウシティシンヨウキンコ", csvDto04.getOrgNameKana(), "金融機関名(カナ)");
        assertEquals("東京シティ信用金庫", csvDto04.getOrgName(), "金融機関名(漢字)");
        assertEquals("ニンギョウマチシュッチョウショ", csvDto04.getBranchNameKana(), "店舗名(カナ)");
        assertEquals("日本橋支店 人形町出張所", csvDto04.getBranchName(), "店舗名(漢字)");
        assertEquals("post", csvDto04.getPostalCode(), "郵便番号");
        assertEquals("中央区日本橋人形町3-7-9", csvDto04.getBranchAddress(), "店舗所在地(漢字)");
        assertEquals("0356145160", csvDto04.getPhonNumber(), "電話番号");
        assertEquals("1", csvDto04.getBillExchangeNumber(), "手形交換所番号");
        assertEquals("1", csvDto04.getOrderCode(), "並びコード");
        assertEquals("1", csvDto04.getFlgNaikokuKawase(), "内国為替制度加盟");

        FinancialOrgCsvDto csvDto05 = financialOrgCsvItemReader.read();
        assertEquals("016", csvDto05.getBranchCode(), "店舗コード");

        FinancialOrgCsvDto csvDto06 = financialOrgCsvItemReader.read();
        assertEquals("018", csvDto06.getBranchCode(), "店舗コード");

        FinancialOrgCsvDto csvDto07 = financialOrgCsvItemReader.read();
        assertEquals("029", csvDto07.getBranchCode(), "店舗コード");

        FinancialOrgCsvDto csvDto08 = financialOrgCsvItemReader.read();
        assertEquals("066", csvDto08.getBranchCode(), "店舗コード");

        FinancialOrgCsvDto csvDtoNot = financialOrgCsvItemReader.read();
        assertEquals(null, csvDtoNot, "最終行以上に取得しようとするとnull");

    }

}
