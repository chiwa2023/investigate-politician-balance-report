package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * ReadAllBookByXmlFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class ReadAllBookByXmlFileLogicTest {

    /** テスト対象 */
    @Autowired
    private ReadAllBookByXmlFileLogic readAllBookByXmlFileLogic;

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
    void testPractice() throws Exception {

        // 完全パス(TestSource内)を渡しても正常に読みだし
        String charset = "Windows-31J";
        String fullPath00 = GetCurrentResourcePath.getBackTestResourcePath()
                + "/sample/balancesheet/2022_政治家女子48党_SYUUSI.xml";

        AllBookDto allBookDto01 = readAllBookByXmlFileLogic.practice(fullPath00, charset);
        // 詳細な内容のテストは別プロジェクトで詳細に行っているので、ファイルとの関係だけをチェックする
        assertThat(allBookDto01.getAllSheet0701CoverAndOrganizationDetailsDto()
                .getSheet070100CoverAndOrganizationDetailsDto().getDantaiName01()).isEqualTo("政治家女子４８党");

        // ストレージフォルダを除いた子パスを渡しても正常に読みだし
        String srcPath = GetCurrentResourcePath.getBackTestResourcePath()
                + "/sample/balancesheet/2022_ホリエモン新党_SYUUSI.xml";
        String fullPath01 = "/2022_ホリエモン新党_SYUUSI.xml";
        String copyPath = storageFolder + fullPath01;
        Files.copy(Paths.get(srcPath), Paths.get(copyPath), StandardCopyOption.REPLACE_EXISTING);

        AllBookDto allBookDto02 = readAllBookByXmlFileLogic.practice(fullPath01, charset);
        // 詳細な内容のテストは別プロジェクトで詳細に行っているので、ファイルとの関係だけをチェックする
        assertThat(allBookDto02.getAllSheet0701CoverAndOrganizationDetailsDto()
                .getSheet070100CoverAndOrganizationDetailsDto().getDantaiName01()).isEqualTo("ホリエモン新党");

        // 存在しないファイルはInvalidPathException
        String fullPath03 = fullPath00 + "q"; // 存在しないファイル
        assertThrows(InvalidPathException.class, () -> readAllBookByXmlFileLogic.practice(fullPath03, charset));

    }

}
