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
 * RefleshYearDataAccessLogicTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessLogicTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessLogicTasklet refleshYearDataAccessLogicTasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2022;
    /** 複写先報告年 */
    private static final int copyYear = 2024;

    /** 収入(4)の部品ディレクトリ */
    private static final String subIncomeDir = "/income";
    /** 複写元使途報告書 */
    private static final String pathFunctionUsage = "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/y";
    /** 複写元収支報告書 */
    private static final String pathFunctionBalancesheet = "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y";

    @Test
    void testExecute() throws Exception { // NOPMD

        StepExecution execution = this.getStepExecution();
        refleshYearDataAccessLogicTasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearDataAccessLogicTasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);

        final String expandText = "Logic.java";
        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        String pathBaseAbsBalancesheet = GetCurrentResourcePath.getBackSrcPath(pathFunctionBalancesheet + baseYear);

        Path pathBaseBalancesheet01 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganization0802Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet01), fileExistText);

        Path pathBaseBalancesheet02 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganization08000Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet02), fileExistText);

        Path pathBaseBalancesheet03 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganizationEstateAllY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet03), fileExistText);

        Path pathBaseBalancesheet04 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganizationIncomeAllY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet04), fileExistText);

        Path pathBaseBalancesheet05 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganizationOutcomeAllY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet05), fileExistText);

        Path pathBaseBalancesheet06 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganizationSheet0701And0720Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet06), fileExistText);

        Path pathBaseBalancesheet07 = Paths.get(pathBaseAbsBalancesheet,
                "InsertPoliticalOrganizationSummaryY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet07), fileExistText);

        String pathBaseAbsBalancesheetIncome = GetCurrentResourcePath
                .getBackSrcPath(pathFunctionBalancesheet + baseYear + subIncomeDir);

        Path pathBaseBalancesheetIncome01 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0703JournalY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome01), fileExistText);

        Path pathBaseBalancesheetIncome02 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0704BorrowedY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome02), fileExistText);

        Path pathBaseBalancesheetIncome03 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0705RelatedToGrantsY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome03), fileExistText);

        Path pathBaseBalancesheetIncome04 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0706OtherIncomeY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome04), fileExistText);

        Path pathBaseBalancesheetIncome05 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0707DonateY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome05), fileExistText);

        Path pathBaseBalancesheetIncome06 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0708MediationY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome06), fileExistText);

        Path pathBaseBalancesheetIncome07 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome07), fileExistText);

        Path pathBaseBalancesheetIncome08 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0710SpecificPartyY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome08), fileExistText);

        Path pathBaseBalancesheetIncome09 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0711ConsiderationPartyY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome09), fileExistText);

        Path pathBaseBalancesheetIncome10 = Paths.get(pathBaseAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0712PartyMediationY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheetIncome10), fileExistText);

        String pathBaseAbsUsage = GetCurrentResourcePath.getBackSrcPath(pathFunctionUsage + baseYear);

        Path pathBaseUsage01 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0801And0807Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage01), fileExistText);

        Path pathBaseUsage02 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0802And0803Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage02), fileExistText);

        Path pathBaseUsage03 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0802Kbn02Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage03), fileExistText);

        Path pathBaseUsage04 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0804Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage04), fileExistText);

        Path pathBaseUsage05 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0805Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage05), fileExistText);

        Path pathBaseUsage06 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0806Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage06), fileExistText);

        Path pathBaseUsage07 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0901Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage07), fileExistText);

        Path pathBaseUsage08 = Paths.get(pathBaseAbsUsage, "InsertPartyUsageShito0902Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage08), fileExistText);

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        String pathCopyAbsBalancesheet = GetCurrentResourcePath.getBackSrcPath(pathFunctionBalancesheet + copyYear);

        Path pathCopyBalancesheet01 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganization0802Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet01).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet02 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganization08000Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet02), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet02).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet03 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganizationEstateAllY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet03), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet03).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet04 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganizationIncomeAllY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet04), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet04).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet05 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganizationOutcomeAllY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet05), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet05).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet06 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganizationSheet0701And0720Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet06), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet06).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet07 = Paths.get(pathCopyAbsBalancesheet,
                "InsertPoliticalOrganizationSummaryY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet07), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet07).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsBalancesheetIncome = GetCurrentResourcePath
                .getBackSrcPath(pathFunctionBalancesheet + copyYear + subIncomeDir);

        Path pathCopyBalancesheetIncome01 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0703JournalY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome01).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome02 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0704BorrowedY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome02), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome02).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome03 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0705RelatedToGrantsY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome03), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome03).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome04 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0706OtherIncomeY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome04), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome04).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome05 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0707DonateY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome05), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome05).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome06 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0708MediationY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome06), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome06).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome07 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome07), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome07).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome08 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0710SpecificPartyY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome08), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome08).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome09 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0711ConsiderationPartyY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome09), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome09).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetIncome10 = Paths.get(pathCopyAbsBalancesheetIncome,
                "ConvertSheetDtoToEntity0712PartyMediationY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheetIncome10), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetIncome10).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsUsage = GetCurrentResourcePath.getBackSrcPath(pathFunctionUsage + copyYear);

        Path pathCopyUsage01 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0801And0807Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage01), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage01).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage02 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0802And0803Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage02), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage02).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage03 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0802Kbn02Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage03), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage03).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage04 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0804Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage04), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage04).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage05 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0805Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage05), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage05).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage06 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0806Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage06), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage06).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage07 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0901Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage07), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage07).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage08 = Paths.get(pathCopyAbsUsage, "InsertPartyUsageShito0902Y" + copyYear + expandText);
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
