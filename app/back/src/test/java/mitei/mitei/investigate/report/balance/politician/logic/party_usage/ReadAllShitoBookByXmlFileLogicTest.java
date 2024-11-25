package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * ReadAllShitoBookByXmlFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class ReadAllShitoBookByXmlFileLogicTest {

    /** テスト対象 */ 
    @Autowired
    private ReadAllShitoBookByXmlFileLogic readAllShitoBookByXmlFileLogic;


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
    void testPractice()throws Exception {
        
        // 完全パス(TestSource内)を渡しても正常に読みだし
        String charset = "Windows-31J";
        String fullPath00 = GetCurrentResourcePath.getBackTestResourcePath()
                + "/sample/usage/2022_政治家女子48党_SITO.xml";

        AllShitoBook allBookDto01 = readAllShitoBookByXmlFileLogic.practice(fullPath00, charset);
        // 詳細な内容のテストは別プロジェクトで詳細に行っているので、ファイルとの関係だけをチェックする
        assertThat(allBookDto01.getShito0801Dto().getSheet0801Dto().getPartyName()).isEqualTo("政治家女子４８党");

        // ストレージフォルダを除いた子パスを渡しても正常に読みだし
        String srcPath = GetCurrentResourcePath.getBackTestResourcePath()
                + "/sample/usage/2022_政治家女子48党_SITO.xml";
        String fullPath01 = "/2022_政治家女子48党_SITO.xml";
        String copyPath = storageFolder + fullPath01;
        Files.copy(Paths.get(srcPath), Paths.get(copyPath), StandardCopyOption.REPLACE_EXISTING);

        AllShitoBook allBookDto02 = readAllShitoBookByXmlFileLogic.practice(fullPath01, charset);
        // 詳細な内容のテストは別プロジェクトで詳細に行っているので、ファイルとの関係だけをチェックする
        assertThat(allBookDto02.getShito0801Dto().getSheet0801Dto().getPartyName()).isEqualTo("政治家女子４８党");

        // 存在しないファイルはInvalidPathException
        String fullPath03 = fullPath00 + "q"; // 存在しないファイル
        assertThrows(InvalidPathException.class, () -> readAllShitoBookByXmlFileLogic.practice(fullPath03, charset));
        
    }

}
