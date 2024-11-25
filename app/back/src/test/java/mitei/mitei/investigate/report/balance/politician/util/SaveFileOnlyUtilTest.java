package mitei.mitei.investigate.report.balance.politician.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;

/**
 * SaveFileOnlyUtil単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class SaveFileOnlyUtilTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SaveFileOnlyUtil saveFileOnlyUtil;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
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
    void testPracticetext() throws Exception {

        LocalDateTime shori = LocalDateTime.of(2024, 5, 28, 13, 24, 36);
        String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/balancesheet", fileName);
        String charset = "cp932";
        String fileContent = Files.readString(path, Charset.forName(charset));
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(shori);

        String pathChild = saveFileOnlyUtil.practice(unixTime, privilegeDto, fileName, fileContent, charset);

        Path pathAll = Paths.get(storageFolder, pathChild, fileName);

        assertTrue(Files.exists(pathAll), "指定したフォルダにファイルが生成されている(ランダム・タイムスタンプがフォルダになっているので上書きされる可能性はない)");

        // 渡した内容と同じ内容が書き込まれている
        assertThat(Files.readString(pathAll, Charset.forName(charset))).isEqualTo(fileContent);
    }

    @Test
    void testPracticeBinary() throws Exception {

        LocalDateTime shori = LocalDateTime.of(2024, 5, 28, 13, 24, 36);
        String fileName = "2022_SITO.zip";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);

        byte[] srcBinary = Files.readAllBytes(path);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(shori);

        String pathChild = saveFileOnlyUtil.practice(unixTime, privilegeDto, fileName, Files.readAllBytes(path));

        Path pathAll = Paths.get(storageFolder, pathChild, fileName);
        byte[] copyBinary = Files.readAllBytes(pathAll);

        assertTrue(Files.exists(pathAll), "指定したフォルダにファイルが生成されている(ランダム・タイムスタンプがフォルダになっているので上書きされる可能性はない)");
        assertThat(copyBinary.length).isEqualTo(srcBinary.length);

        for (int index = 0; index < srcBinary.length; index++) {
            assertThat(copyBinary[index]).isEqualTo(srcBinary[index]);
        }

    }
}
