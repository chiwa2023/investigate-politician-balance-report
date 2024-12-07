package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;

/**
 * UnCompressedFileToReadXmlDtoShitoProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class UnCompressedFileToReadXmlDtoShitoProcessorTest {

    /** テスト対象 */
    @Autowired
    private UnCompressedFileToReadXmlDtoShitoProcessor unCompressedFileToReadXmlDtoShitoProcessor;

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
    @Tag("TableTruncate")
    void test() throws Exception {
        // CHECKSTYLE:OFF

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

        ReadXmlPartyUsageResultDto resultDto = unCompressedFileToReadXmlDtoShitoProcessor.process(detailEntity);

        Sheet0801Dto sheet0801 = resultDto.getCoverDto(); // NOPMD

        assertEquals(2022, sheet0801.getNendo(),"");
        assertEquals("大津　綾香", sheet0801.getDelegateName(),"");
        assertEquals("政治家女子４８党", sheet0801.getPartyName(),"");

        assertEquals(2022, resultDto.getDocumentPropertyDto().getNendo()); // NOPMD
        assertEquals("政治家女子４８党", resultDto.getDocumentPropertyDto().getDantaiName()); // NOPMD

        assertEquals(charset, resultDto.getSaveStorageResultDto().getCharset(),"");
        assertEquals(childDir, resultDto.getSaveStorageResultDto().getChildDir(),"");
        assertEquals(fileName, resultDto.getSaveStorageResultDto().getFileName(),"");

        assertEquals(756L, resultDto.getTaskPlanPartyUsageDetailId(),"");
        assertEquals(750L, resultDto.getTaskPlanPartyUsageDetailCode(),"");

    }

}
