package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;

/**
 * ReadXmlDtoPartyUsageToWrokTableEntityProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class ReadXmlDtoPartyUsageToWrokTableEntityProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ReadXmlDtoPartyUsageToWrokTableEntityProcessor readXmlDtoPartyUsageToWrokTableEntityProcessor;

    /** このプロセッサの前段階 */
    @Autowired
    private UnCompressedFileToReadXmlDtoShitoProcessor unCompressedFileToReadXmlDtoProcessor;

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

    @Test
    void test() throws Exception {

        // テンプレートファイルの呼び出し
        String fileName = "2022_政治家女子48党_SITO.xml";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/usage", fileName);
        String charset = "UTF-8";
        String fileContent = Files.readString(path, Charset.forName(charset));

        String childDir = "test_user/expand";

        // ファイルの複写
        Path pathCopy = Paths.get(storageFolder, childDir, fileName);
        Files.createDirectories(pathCopy.getParent());
        Files.writeString(pathCopy, fileContent, Charset.forName(charset));

        TaskPlanPartyUsageDetailEntity detailEntity = new TaskPlanPartyUsageDetailEntity();
        detailEntity.setCharset(charset);
        detailEntity.setChildDir(childDir);
        detailEntity.setFileName(fileName);
        detailEntity.setFullPath(childDir + "/" + fileName);
        detailEntity.setShoshouId(108L);
        detailEntity.setShoshouCode(100L);
        detailEntity.setShoshouKbn(0);
        detailEntity.setTaskPlanPartyUsageDetailId(756L);
        detailEntity.setTaskPlanPartyUsageDetailCode(750L);

        ReadXmlPartyUsageResultDto resultDto = unCompressedFileToReadXmlDtoProcessor.process(detailEntity);

        WkTblPoliPartyUsageReportEntity entity = readXmlDtoPartyUsageToWrokTableEntityProcessor.process(resultDto);

        // WkTblPoliPartyUsageReportEntityからReadXmlPartyUsageResultDtoがBeanUtilsを用いて正しくコピーされるのは検証済

        /* 書証保存Dtoから抜粋 */
        assertEquals(charset, entity.getCharset(), "文字コード一致");
        assertEquals(childDir, entity.getChildDir(), "子フォルダ一致");
        assertEquals(fileName, entity.getFileName(), "ファイル名一致");

        /* 表紙Dtoから抜粋 */
        assertEquals(2022, entity.getNendo(), "年度一致");
        assertEquals("大津　綾香", entity.getDaihyouName(), "代表者名一致");

        /* 書類情報Dtoから抜粋 */
        assertEquals(2022, entity.getNendo(), "年度一致");
        assertEquals("政治家女子４８党", entity.getDantaiName(), "団体名一致");

        assertEquals(756L, entity.getTaskPlanPartyUsageDetailId(), "Id一致");
        assertEquals(750L, entity.getTaskPlanPartyUsageDetailCode(), "同一識別コード一致");

    }

}
