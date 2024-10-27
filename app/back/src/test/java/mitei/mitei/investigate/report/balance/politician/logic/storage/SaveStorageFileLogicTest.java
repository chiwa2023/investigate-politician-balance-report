package mitei.mitei.investigate.report.balance.politician.logic.storage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * SaveStorageFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class SaveStorageFileLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SaveStorageFileLogic saveStorageFileLogic;

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

    /** ファイル保存Repository(2024年) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    @Test
    @Transactional
    void testPracticeText2024() throws Exception {

        String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/balancesheet", fileName);
        String charset = "cp932";
        String fileContent = Files.readString(path, Charset.forName(charset));
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // 処理日時は2024年なのでDBは2024年分に保存
        LocalDateTime shori = LocalDateTime.of(2024, 5, 28, 13, 24, 36);

        SaveStorageResultDto resultDto = saveStorageFileLogic.practiceText(path.getFileName().toString(), fileContent,
                charset, privilegeDto, shori);

        // 保存ディレクトリが確保されていること
        Path pathFolder = Paths.get(storageFolder);
        assertTrue(Files.exists(pathFolder), "親フォルダは存在する");
        assertTrue(Files.isDirectory(pathFolder), "親フォルダはディレクトリである");

        // ファイルが内容が同じであるファイルが保存されていること
        String fullPath = resultDto.getFullPath();
        Path allPath = Paths.get(storageFolder, fullPath);
        assertTrue(Files.exists(allPath), "保存ファイルは存在する");
        assertThat(Files.readString(allPath,Charset.forName(charset))).isEqualTo(fileContent);
        assertThat(resultDto.getChildDir())
                .isEqualTo(fullPath.substring(0, fullPath.length() - (fileName.length() + 1)));
        assertThat(resultDto.getFileName()).isEqualTo(fileName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(shori);
        assertThat(resultDto.getRegistTimeText()).isEqualTo(unixTime);

        // DBに必要な属性が格納されていること
        SaveFileStorage2024Entity entity = saveFileStorage2024Repository.findById(resultDto.getShoshouId()).get();
        assertThat(entity.getSaveFileStorageCode()).isEqualTo(0L); // 新規挿入時は0固定
        assertThat(entity.getDirChild()).isEqualTo(fullPath.substring(0, fullPath.length() - (fileName.length() + 1)));
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId()); // 書証の保持者：重要
        assertThat(entity.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode()); // 書証の保持者：重要
        assertThat(entity.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName()); // 書証の保持者：重要
    }

    // @Test しばらくおやすみ TODO 実装したら再有効にする
    void testPracticeWithDecode() throws Exception {

        // ファイルコンテンツはバイナリのURLEncodeされたデータである必要がある
        String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/balancesheet", fileName);

        String charset = "cp932";
        // TODO ここがバイナリ読み取り→文字列→UrlEncode
        String fileContent = Files.readString(path, Charset.forName(charset));

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // 処理日時は2024年なのでDBは2024年分に保存
        LocalDateTime shori = LocalDateTime.of(2024, 5, 28, 13, 24, 36);

        SaveStorageResultDto resultDto = saveStorageFileLogic.practiceText(path.getFileName().toString(), fileContent,
                charset, privilegeDto, shori);

        // 保存ディレクトリが確保されていること
        Path pathFolder = Paths.get(storageFolder);
        assertTrue(Files.exists(pathFolder), "親フォルダは存在する");
        assertTrue(Files.isDirectory(pathFolder), "親フォルダはディレクトリである");

        // ファイルが内容が同じであるファイルが保存されていること
        String fullPath = resultDto.getFullPath();
        Path allPath = Paths.get(storageFolder, fullPath);
        assertTrue(Files.exists(allPath), "保存ファイルは存在する");
        assertThat(resultDto.getChildDir())
                .isEqualTo(fullPath.substring(0, fullPath.length() - (fileName.length() + 1)));
        assertThat(resultDto.getFileName()).isEqualTo(fileName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(shori);
        assertThat(resultDto.getRegistTimeText()).isEqualTo(unixTime);

        // TODO ファイルのバイナリ比較
        assertThat(Files.readString(allPath)).isEqualTo(fileContent);

        // DBに必要な属性が格納されていること
        SaveFileStorage2024Entity entity = saveFileStorage2024Repository.findById(resultDto.getShoshouId()).get();
        assertThat(entity.getSaveFileStorageCode()).isEqualTo(0L); // 新規挿入時は0固定
        assertThat(entity.getDirChild()).isEqualTo(fullPath.substring(0, fullPath.length() - (fileName.length() + 1)));
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId()); // 書証の保持者：重要
        assertThat(entity.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode()); // 書証の保持者：重要
        assertThat(entity.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName()); // 書証の保持者：重要

        fail("Not yet implemented");
    }

}
