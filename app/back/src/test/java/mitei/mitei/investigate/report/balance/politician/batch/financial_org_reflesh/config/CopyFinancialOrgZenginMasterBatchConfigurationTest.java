package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * CopyFinancialOrgZenginMasterBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class CopyFinancialOrgZenginMasterBatchConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(CopyFinancialOrgZenginMasterBatchConfiguration.JOB_NAME)
    @Autowired
    private Job copyFinancialOrgZenginMaster;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(CopyFinancialOrgZenginMasterBatchConfiguration.JOB_NAME, copyFinancialOrgZenginMaster.getName(),
                "Job名が一致");
    }

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
    void testExecute() throws Exception {

        String childDir = "zengin";
        String fileName = "nkyk4.csv";

        // ファイル準備(読み取り)
        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/bank/pre", fileName);
        String srcData = Files.readString(pathSrc);

        // 確実にテストを行うため都度書き出し
        Path pathCopy = Paths.get(storageFolder, childDir, fileName);
        Files.createDirectories(pathCopy.getParent());
        Files.writeString(pathCopy, srcData);

        // 実際の処理
        String paramPath = Paths.get(childDir, fileName).toString();

        jobLauncherTestUtils.setJob(copyFinancialOrgZenginMaster);

        JobParameters jobParameters = new JobParametersBuilder(
                copyFinancialOrgZenginMaster.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFileName", paramPath)
                .addLong("loginUserId", 339L).addString("loginUserCode", "330").addString("loginUserName", "ユーザA")
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
