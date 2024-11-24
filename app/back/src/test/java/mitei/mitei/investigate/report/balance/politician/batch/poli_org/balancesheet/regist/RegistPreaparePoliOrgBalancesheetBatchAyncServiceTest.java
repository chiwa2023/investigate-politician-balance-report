package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * RegistPreaparePoliOrgBalancesheetBatchAyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class RegistPreaparePoliOrgBalancesheetBatchAyncServiceTest {

    /** テスト対象 */
    @Autowired
    private RegistPreaparePoliOrgBalancesheetBatchAyncService registPreaparePoliOrgBalancesheetBatchAyncService;

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
    @Sql("configuration_test.sql")
    void testPractice() throws Exception {

        // このコピーと同じ内容を準備テーブルに入れている

        // テンプレートファイルの呼び出し
        String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/balancesheet", fileName);
        String charset = "cp932";
        String fileContent = Files.readString(path, Charset.forName(charset));

        String childDir = "test_user/expand";

        // ファイルの複写
        Path pathCopy = Paths.get(storageFolder, childDir, fileName);
        Files.createDirectories(pathCopy.getParent());
        Files.writeString(pathCopy, fileContent, Charset.forName(charset));

        // すでにテスト済のBatchを非同期にしているだけなので、同じ条件でテストをしたらとりあえず例外だけは起こさなければよい
        assertDoesNotThrow(() -> registPreaparePoliOrgBalancesheetBatchAyncService.practice());

    }

}
