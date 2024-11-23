package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * RegistPoliOrgBalancesheetReportBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class RegistPoliOrgBalancesheetReportBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(RegistPoliOrgBalancesheetReportBatchConfiguration.JOB_NAME)
    @Autowired
    private Job registPoliOrgBalancesheetReport;

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

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
    void testJob() {
        assertThat(registPoliOrgBalancesheetReport.getName())
                .isEqualTo(RegistPoliOrgBalancesheetReportBatchConfiguration.JOB_NAME);
    }

    @Test
    @Tag("TableTruncate")
    @Sql("wk_tbl_poli_org_balancesheet_report_allwrite.sql")
    void test() throws Exception {

        // 全件取得してChunkと要求されているファイルを作成してセット
        List<WkTblPoliOrgBalancesheetReportEntity> listTask = wkTblPoliOrgBalancesheetReportRepository.findAll();
        for (WkTblPoliOrgBalancesheetReportEntity entity : listTask) {
            Path path = Paths.get(storageFolder, entity.getFullPath());
            Files.write(path, this.readBinaryFile(entity.getFileName()));
        }

        jobLauncherTestUtils.setJob(registPoliOrgBalancesheetReport);

        JobParameters jobParameters = new JobParametersBuilder(
                registPoliOrgBalancesheetReport.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

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
