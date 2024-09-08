package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * RefleshYearDataAccessDdlasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessDdlaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessDdlasklet refleshYearDataAccessDdlasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2025;
    /** 複写先報告年 */
    private static final int copyYear = 2022;

    /** 複写先取得用相対ディレクトリ */
    private static final String copyPath = "main/resources/SQL/DDL";

    @Test
    void testExecute() throws Exception { // NOPMD

        StepExecution execution = this.getStepExecution();
        refleshYearDataAccessDdlasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearDataAccessDdlasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);

        /* ファイルの存在だけ確認 */
        String pathAbs = GetCurrentResourcePath.getBackSrcPath(copyPath);

        final String expandText = ".sql";
        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        Path pathBaseBalancesheet01 = Paths.get(pathAbs,
                "offering_balancesheet_0701_and_0720_surface_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet01), fileExistText);

        Path pathBaseBalancesheet02 = Paths.get(pathAbs,
                "offering_balancesheet_0702_and_0713_and_0717_summary_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet02), fileExistText);

        Path pathBaseBalancesheet03 = Paths.get(pathAbs, "offering_balancesheet_0718_estate_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet03), fileExistText);

        Path pathBaseBalancesheet04 = Paths.get(pathAbs,
                "offering_balancesheet_0719_real_estate_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet04), fileExistText);

        Path pathBaseBalancesheet05 = Paths.get(pathAbs,
                "offering_balancesheet_difficalt_0800_recipt_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet05), fileExistText);

        Path pathBaseBalancesheet06 = Paths.get(pathAbs, "offering_balancesheet_income_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet06), fileExistText);

        Path pathBaseBalancesheet07 = Paths.get(pathAbs, "offering_balancesheet_outcome_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet07), fileExistText);

        Path pathBaseBalancesheet08 = Paths.get(pathAbs,
                "offering_balancesheet_withdrawal_0802_transfer_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet08), fileExistText);

        Path pathBaseUsage01 = Paths.get(pathAbs, "offering_party_usage_0801_and_0807_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage01), fileExistText);

        Path pathBaseUsage02 = Paths.get(pathAbs, "offering_party_usage_0802_and_0803_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage02), fileExistText);

        Path pathBaseUsage03 = Paths.get(pathAbs, "offering_party_usage_0802_kbn_02_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage03), fileExistText);

        Path pathBaseUsage04 = Paths.get(pathAbs, "offering_party_usage_0804_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage04), fileExistText);

        Path pathBaseUsage05 = Paths.get(pathAbs, "offering_party_usage_0805_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage05), fileExistText);

        Path pathBaseUsage06 = Paths.get(pathAbs, "offering_party_usage_0806_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage06), fileExistText);

        Path pathBaseUsage07 = Paths.get(pathAbs, "offering_party_usage_0901_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage07), fileExistText);

        Path pathBaseUsage08 = Paths.get(pathAbs, "offering_party_usage_0902_report_" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage08), fileExistText);

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        Path pathCopyBalancesheet01 = Paths.get(pathAbs,
                "offering_balancesheet_0701_and_0720_surface_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet01).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet02 = Paths.get(pathAbs,
                "offering_balancesheet_0702_and_0713_and_0717_summary_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet02), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet02).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet03 = Paths.get(pathAbs, "offering_balancesheet_0718_estate_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet03), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet03).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet04 = Paths.get(pathAbs,
                "offering_balancesheet_0719_real_estate_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet04), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet04).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet05 = Paths.get(pathAbs,
                "offering_balancesheet_difficalt_0800_recipt_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet05), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet05).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet06 = Paths.get(pathAbs, "offering_balancesheet_income_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet06), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet06).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet07 = Paths.get(pathAbs, "offering_balancesheet_outcome_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet07), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet07).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet08 = Paths.get(pathAbs,
                "offering_balancesheet_withdrawal_0802_transfer_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet08), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet08).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage01 = Paths.get(pathAbs, "offering_party_usage_0801_and_0807_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage01).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage02 = Paths.get(pathAbs, "offering_party_usage_0802_and_0803_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage02), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage02).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage03 = Paths.get(pathAbs, "offering_party_usage_0802_kbn_02_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage03), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage03).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage04 = Paths.get(pathAbs, "offering_party_usage_0804_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage04), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage04).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage05 = Paths.get(pathAbs, "offering_party_usage_0805_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage05), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage05).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage06 = Paths.get(pathAbs, "offering_party_usage_0806_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage06), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage06).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage07 = Paths.get(pathAbs, "offering_party_usage_0901_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage07), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage07).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage08 = Paths.get(pathAbs, "offering_party_usage_0902_report_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage08), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage08).toInstant().isAfter(kijunTime), updateText);

    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addString("baseYear", String.valueOf(baseYear))
                .addString("copyYear", String.valueOf(copyYear)).addLocalDateTime("now", LocalDateTime.now()) // 実作業では必要としないがパラメータ一致よけ
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
