package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Repository;

/**
 * AllBookItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AllBookItemWriterTest {

    /** テスト対象 */
    @Autowired
    private AllBookItemWriter allBookItemWriter;

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2022Repository offeringBalancesheet0701And0720Surface2022Repository;

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
    @Sql("wk_tbl_poli_org_balancesheet_report_allwrite.sql")
    void testWrite() throws Exception {

        // 全件取得してChunkと要求されているファイルを作成してセット
        List<WkTblPoliOrgBalancesheetReportEntity> listTask = wkTblPoliOrgBalancesheetReportRepository.findAll();
        for (WkTblPoliOrgBalancesheetReportEntity entity : listTask) {
            Path path = Paths.get(storageFolder, entity.getFullPath());
            Files.write(path, this.readBinaryFile(entity.getFileName()));
        }

        Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items = new Chunk<>(listTask);
        allBookItemWriter.write(items);

        // 1ファイル登録の時のコードを丸コピしているので詳細の確認までは踏み込まない
        // 2022年のファイルを2件処理しているので代表データも2件
        assertThat(offeringBalancesheet0701And0720Surface2022Repository.count()).isEqualTo(2L);

        // 処理後の結果正常終了しているのであれば最新区分が更新済に
        List<WkTblPoliOrgBalancesheetReportEntity> listResult = wkTblPoliOrgBalancesheetReportRepository.findAll();
        for (WkTblPoliOrgBalancesheetReportEntity entity : listResult) {
            assertThat(entity.getSaishinKbn()).isEqualTo(0);// 更新終了
        }
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_org_balancesheet_report_allwrite.sql")
    void testIOException() throws Exception {

        // 全件取得してChunkと要求されているファイルを作成してセット
        List<WkTblPoliOrgBalancesheetReportEntity> listTask = wkTblPoliOrgBalancesheetReportRepository.findAll();
        for (WkTblPoliOrgBalancesheetReportEntity entity : listTask) {
            Path path = Paths.get(storageFolder, entity.getFullPath());
            Files.write(path, this.readBinaryFile(entity.getFileName()));
        }

        // ファイル2件目を削除してIOExceptionを発生
        Path pathDelete = Paths.get(storageFolder, listTask.get(1).getFullPath());
        Files.delete(pathDelete);

        Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items = new Chunk<>(listTask);
        allBookItemWriter.write(items);

        // 処理後の結果正常終了しているのであれば最新区分が更新済に
        List<WkTblPoliOrgBalancesheetReportEntity> listResult = wkTblPoliOrgBalancesheetReportRepository.findAll();
        // 1件目は正常終了
        assertThat(listResult.get(0).getSaishinKbn()).isEqualTo(0);
        // 2件目は異常終了
        assertThat(listResult.get(1).getSaishinKbn()).isEqualTo(1);
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_org_balancesheet_report_allwrite.sql")
    void testSQLException() throws Exception {

        // 全件取得してChunkと要求されているファイルを作成してセット
        List<WkTblPoliOrgBalancesheetReportEntity> listTask = wkTblPoliOrgBalancesheetReportRepository.findAll();
        for (WkTblPoliOrgBalancesheetReportEntity entity : listTask) {
            Path path = Paths.get(storageFolder, entity.getFullPath());
            Files.write(path, this.readBinaryFile(entity.getFileName()));
        }

        // 桁あふれを起こしてSQLエラー
        final String over300 = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        listTask.get(0).setRelationPersonNameDelegate(over300); // 現状では桁あふれするはず

        Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items = new Chunk<>(listTask);
        try {
            // TODO SQLで問題が起きた場合の処理を決定後修正する
            allBookItemWriter.write(items);
        } catch (Exception e) {
            // TODO: handle exception
        }

        fail("Not yet implemented");
    }

    // バイナリで指定されたファイルの中身を読み込む
    private byte[] readBinaryFile(final String fileName) throws IOException {
        final String horie = "2022_ホリエモン新党_SYUUSI.xml";
        if (horie.equals(fileName)) {
            Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                    "/sample/balancesheet/2022_ホリエモン新党_SYUUSI.xml");
            return Files.readAllBytes(path);
        } else {
            Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                    "/sample/balancesheet/2022_政治家女子48党_SYUUSI.xml");
            return Files.readAllBytes(path);
        }
    }

}
