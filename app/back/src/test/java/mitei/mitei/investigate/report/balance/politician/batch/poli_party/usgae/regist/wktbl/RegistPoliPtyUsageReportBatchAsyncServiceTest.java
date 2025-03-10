package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.wktbl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Tag;
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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * RegistPoliPtyUsageReportBatchAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class RegistPoliPtyUsageReportBatchAsyncServiceTest {

    /** テスト対象 */
    @Autowired
    private RegistPoliPtyUsageReportBatchAsyncService registPoliPtyUsageReportBatchAsyncService;

    /** 政党交付金使途報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

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
    @Sql("wk_tbl_poli_party_usage_report_document.sql")
    void test() throws Exception {

        // 全件取得してChunkと要求されているファイルを作成してセット
        List<WkTblPoliPartyUsageReportEntity> listTask = wkTblPoliPartyUsageReportRepository.findAll();
        for (WkTblPoliPartyUsageReportEntity entity : listTask) {
            Path path = Paths.get(storageFolder, entity.getFullPath());
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, this.readBinaryFile(entity.getFileName()));
        }

        // バッチのテストと同じ条件で実施して例外さえ起こさなければ良い
        assertDoesNotThrow(() -> registPoliPtyUsageReportBatchAsyncService.practice());
    }

    // バイナリで指定されたファイルの中身を読み込む
    private byte[] readBinaryFile(final String fileName) throws IOException { // NOPMD
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/sample/usage/2022_政治家女子48党_SITO.xml");
        return Files.readAllBytes(path);
    }

}
