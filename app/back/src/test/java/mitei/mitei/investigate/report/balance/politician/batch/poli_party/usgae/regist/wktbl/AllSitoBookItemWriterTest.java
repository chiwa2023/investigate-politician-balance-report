package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.wktbl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0801And0807Report2022Repository;

/**
 * AllSitoBookItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AllSitoBookItemWriterTest {

    /** 単体テスト */
    @Autowired
    private AllSitoBookItemWriter allSitoBookItemWriter;

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    /** 政党交付金使途報告書表紙と宣誓書レポジトリ */
    @Autowired
    private OfferingPartyUsage0801And0807Report2022Repository offeringPartyUsage0801And0807Report2022Repository;

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
    @Transactional
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

        Chunk<? extends WkTblPoliPartyUsageReportEntity> items = new Chunk<>(listTask);
        allSitoBookItemWriter.write(items);

        // 1ファイル登録の時のコードを丸コピしているので詳細の確認までは踏み込まない
        // 2022年のファイルを2件処理しているので代表データも2件
        assertEquals(2L,offeringPartyUsage0801And0807Report2022Repository.count(),"");
    }

    // TODO ファイル不在例外とカラム桁あふれテスト作成

    // バイナリで指定されたファイルの中身を読み込む
    private byte[] readBinaryFile(final String fileName) throws IOException { // NOPMD
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/sample/usage/2022_政治家女子48党_SITO.xml");
        return Files.readAllBytes(path);
    }

}
