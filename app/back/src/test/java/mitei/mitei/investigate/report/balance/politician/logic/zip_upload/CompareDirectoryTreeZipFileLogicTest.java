package mitei.mitei.investigate.report.balance.politician.logic.zip_upload;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SaveFileOnlyUtil;

/**
 * CompareDirectoryTreeZipFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class CompareDirectoryTreeZipFileLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CompareDirectoryTreeZipFileLogic compareDirectoryTreeZipFileLogic;

    /** ファイル保存Utilty */
    @Autowired
    private SaveFileOnlyUtil saveFileOnlyUtil;

    /** zip解凍Logic */
    @Autowired
    private UnCompressZipFileLogic unCompressZipFileLogic;

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

    /** 展開フォルダ */
    private String uncompress;

    /**
     * 展開フォルダを取得する
     *
     * @return 展開フォルダ
     */
    public String getUncompress() {
        return uncompress;
    }

    /**
     * 展開フォルダを設定する
     *
     * @param uncompress 展開フォルダ
     */
    public void setUncompress(final String uncompress) {
        this.uncompress = uncompress;
    }

    @Test
    void testPractice() throws Exception {

        String fileName = "all.zip";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);

        LocalDateTime datetimeShori = LocalDateTime.of(2024, 8, 11, 0, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(datetimeShori);
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        String childDir = saveFileOnlyUtil.practice(unixTime, privilegeDto, fileName, Files.readAllBytes(path));

        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        saveStorageResultDto.setChildDir(childDir);
        saveStorageResultDto.setRegistTimeText(unixTime);
        saveStorageResultDto.setFileName(fileName);
        saveStorageResultDto.setFullPath(childDir + fileName);

        unCompressZipFileLogic.practice(saveStorageResultDto);

        final String keyWord = "収支報告書作成ソフト";
        List<SaveStorageResultDto> list = compareDirectoryTreeZipFileLogic.practice(saveStorageResultDto, keyWord);
        list.sort((dto1, dto2) -> {
            return dto1.getFileName().compareTo(dto2.getFileName());
        });

        assertThat(list.size()).isEqualTo(3); // zip内にファイル3件
        
        SaveStorageResultDto resultoDto00 = list.get(0);
        assertThat(resultoDto00.getFileName()).isEqualTo("2022_ホリエモン新党_SYUUSI.xml");
        assertThat(resultoDto00.getCharset()).isEqualTo("Shift_JIS");
        
        SaveStorageResultDto resultoDto01 = list.get(1);
        assertThat(resultoDto01.getFileName()).isEqualTo("2022_政治家女子48党_SITO.xml");
        assertThat(resultoDto01.getCharset()).isEqualTo(null); // 文字コード事態の確定はできるが政治資金収支報告書XMLではない
        
        SaveStorageResultDto resultoDto02 = list.get(2);
        assertThat(resultoDto02.getFileName()).isEqualTo("2022_政治家女子48党_SYUUSI.xml");
        assertThat(resultoDto02.getCharset()).isEqualTo("Shift_JIS");
    }

    @Test
    void testPracticeError() throws Exception {

        String fileName = "all.zip";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);

        LocalDateTime datetimeShori = LocalDateTime.of(2024, 8, 11, 0, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(datetimeShori);
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        String childDir = saveFileOnlyUtil.practice(unixTime, privilegeDto, fileName, Files.readAllBytes(path));

        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        saveStorageResultDto.setChildDir(childDir);
        saveStorageResultDto.setRegistTimeText(unixTime);
        saveStorageResultDto.setFileName(fileName);
        saveStorageResultDto.setFullPath(childDir + fileName);

        unCompressZipFileLogic.practice(saveStorageResultDto);

        // 横からファイルを追加してfalseにする
        Path zipPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress, "abc.txt");
        Files.writeString(zipPath, "abc");

        final String keyWord = "収支報告書作成ソフト";
        List<SaveStorageResultDto> list = compareDirectoryTreeZipFileLogic.practice(saveStorageResultDto, keyWord);

        assertTrue(list.isEmpty(), "不正処理の場合は空リスト");
    }
}
