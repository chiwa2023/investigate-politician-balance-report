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
 * RefleshYearDataAccessLogicTestTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessLogicTestTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessLogicTestTasklet refleshYearDataAccessLogicTestTasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2022;
    /** 複写先報告年 */
    private static final int copyYear = 2025;

    /** 収入(4)の部品ディレクトリ */
    private static final String subIncomeDir = "/income";
    /** 複写元使途報告書 */
    private static final String pathFunctionUsage = "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/y";
    /** 複写元収支報告書 */
    private static final String pathFunctionBalancesheet = "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y";
    /** 複写元使途報告書テストSQL */
    private static final String pathFunctionUsageSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/party_usage/y";
    /** 複写元収支報告書SQL */
    private static final String pathFunctionBalancesheetSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y";

    @Test
    void testExecute() throws Exception { // NOPMD

        final String expandText = "LogicTest.java";
        final String expandSqlText = ".sql";
        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        /*
         * 複写元に必要なファイルが含まれているか確認
         */

        /* 政党交付金使途報告書 */
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

        Path pathBaseBalancesheet10 = Paths.get(pathBaseAbsBalancesheet,
                "CheckAllreadyRegistDataPoliticalOrganizationY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet10), fileExistText);

        Path pathBaseBalancesheet11 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganization0802Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet11), fileExistText);

        Path pathBaseBalancesheet12 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganization08000Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet12), fileExistText);

        Path pathBaseBalancesheet13 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganizationEstateAllY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet13), fileExistText);

        Path pathBaseBalancesheet14 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganizationIncomeAllY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet14), fileExistText);

        Path pathBaseBalancesheet15 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganizationOutcomeAllY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet15), fileExistText);

        Path pathBaseBalancesheet16 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganizationSheet0701And0720Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet16), fileExistText);

        Path pathBaseBalancesheet17 = Paths.get(pathBaseAbsBalancesheet,
                "UpdatePoliticalOrganizationSummaryY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseBalancesheet17), fileExistText);

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

        String pathBaseAbsBalancesheetSql = GetCurrentResourcePath
                .getBackSrcPath(pathFunctionBalancesheetSql + baseYear);

        Path pathBaseBalancesheetSql10 = Paths.get(pathBaseAbsBalancesheetSql,
                "check_allready_regist_data_" + baseYear + expandSqlText); // NOPMD
        assertTrue(Files.exists(pathBaseBalancesheetSql10), fileExistText);

        Path pathBaseBalancesheetSql11 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_0701_and_0720_surface_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql11), fileExistText);

        Path pathBaseBalancesheetSql12 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_0702_and_0713_and_0717_summary_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql12), fileExistText);

        Path pathBaseBalancesheetSql13 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_0718_estate_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql13), fileExistText);

        Path pathBaseBalancesheetSql14 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_0719_real_estate_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql14), fileExistText);

        Path pathBaseBalancesheetSql15 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_difficalt_0800_recipt_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql15), fileExistText);

        Path pathBaseBalancesheetSql16 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_income_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql16), fileExistText);

        Path pathBaseBalancesheetSql17 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_outcome_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql17), fileExistText);

        Path pathBaseBalancesheetSql18 = Paths.get(pathBaseAbsBalancesheetSql,
                "offering_balancesheet_withdrawal_0802_transfer_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseBalancesheetSql18), fileExistText);

        /* 政党交付金使途報告書 */
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

        Path pathBaseUsage10 = Paths.get(pathBaseAbsUsage,
                "CheckAllreadyRegistDataPartyUsageY" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage10), fileExistText);

        Path pathBaseUsage11 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0801And0807Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage11), fileExistText);

        Path pathBaseUsage12 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0802And0803Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage12), fileExistText);

        Path pathBaseUsage13 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0802Kbn02Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage13), fileExistText);

        Path pathBaseUsage14 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0804Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage14), fileExistText);

        Path pathBaseUsage15 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0805Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage15), fileExistText);

        Path pathBaseUsage16 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0806Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage16), fileExistText);

        Path pathBaseUsage17 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0901Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage17), fileExistText);

        Path pathBaseUsage18 = Paths.get(pathBaseAbsUsage, "UpdatePartyUsageShito0902Y" + baseYear + expandText);
        assertTrue(Files.exists(pathBaseUsage18), fileExistText);

        String pathBaseAbsUsageSql = GetCurrentResourcePath.getBackSrcPath(pathFunctionUsageSql + baseYear);

        Path pathBaseUsageSql10 = Paths.get(pathBaseAbsUsageSql,
                "check_allready_regist_data_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql10), fileExistText);

        Path pathBaseUsageSql11 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0801_and_0807_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql11), fileExistText);

        Path pathBaseUsageSql12 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0802_and_0803_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql12), fileExistText);

        Path pathBaseUsageSql13 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0802_kbn_02_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql13), fileExistText);

        Path pathBaseUsageSql14 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0804_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql14), fileExistText);

        Path pathBaseUsageSql15 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0805_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql15), fileExistText);

        Path pathBaseUsageSql16 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0806_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql16), fileExistText);

        Path pathBaseUsageSql17 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0901_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql17), fileExistText);

        Path pathBaseUsageSql18 = Paths.get(pathBaseAbsUsageSql,
                "offering_party_usage_0902_report_" + baseYear + expandSqlText);
        assertTrue(Files.exists(pathBaseUsageSql18), fileExistText);

        /*
         * 生成されたファイルのチェック
         */

        StepExecution execution = this.getStepExecution();
        refleshYearDataAccessLogicTestTasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearDataAccessLogicTestTasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        /* 政治資金収支報告書 */
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

        Path pathCopyBalancesheet11 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganization0802Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet11), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet01).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet12 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganization08000Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet12), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet02).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet13 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganizationEstateAllY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet13), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet03).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet14 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganizationIncomeAllY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet14), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet04).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet15 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganizationOutcomeAllY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet15), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet05).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet16 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganizationSheet0701And0720Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet16), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheet06).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheet17 = Paths.get(pathCopyAbsBalancesheet,
                "UpdatePoliticalOrganizationSummaryY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyBalancesheet17), fileExistText);
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

        String pathCopyAbsBalancesheetSql = GetCurrentResourcePath
                .getBackSrcPath(pathFunctionBalancesheetSql + copyYear);

        Path pathCopyBalancesheetSql10 = Paths.get(pathCopyAbsBalancesheetSql,
                "check_allready_regist_data_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql10), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql10).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql11 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_0701_and_0720_surface_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql11), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql11).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql12 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_0702_and_0713_and_0717_summary_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql12), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql12).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql13 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_0718_estate_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql13), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql13).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql14 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_0719_real_estate_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql14), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql14).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql15 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_difficalt_0800_recipt_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql15), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql15).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql16 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_income_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql16), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql16).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql17 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_outcome_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql17), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql17).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyBalancesheetSql18 = Paths.get(pathCopyAbsBalancesheetSql,
                "offering_balancesheet_withdrawal_0802_transfer_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyBalancesheetSql18), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyBalancesheetSql18).toInstant().isAfter(kijunTime), updateText);

        /* 政党交付金使途報告書 */

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

        Path pathCopyUsage10 = Paths.get(pathCopyAbsUsage,
                "CheckAllreadyRegistDataPartyUsageY" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage10), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage10).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage11 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0801And0807Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage11), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage11).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage12 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0802And0803Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage12), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage12).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage13 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0802Kbn02Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage13), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage13).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage14 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0804Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage14), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage14).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage15 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0805Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage15), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage15).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage16 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0806Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage16), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage16).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage17 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0901Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage17), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage17).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsage18 = Paths.get(pathCopyAbsUsage, "UpdatePartyUsageShito0902Y" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyUsage18), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsage18).toInstant().isAfter(kijunTime), updateText);

        String pathCopyAbsUsageSql = GetCurrentResourcePath.getBackSrcPath(pathFunctionUsageSql + copyYear);

        Path pathCopyUsageSql10 = Paths.get(pathCopyAbsUsageSql,
                "check_allready_regist_data_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql10), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql10).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql11 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0801_and_0807_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql11), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql11).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql12 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0802_and_0803_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql12), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql12).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql13 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0802_kbn_02_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql13), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql13).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql14 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0804_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql14), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql14).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql15 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0805_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql15), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql15).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql16 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0806_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql16), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql16).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql17 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0901_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql17), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql17).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyUsageSql18 = Paths.get(pathCopyAbsUsageSql,
                "offering_party_usage_0902_report_" + copyYear + expandSqlText);
        assertTrue(Files.exists(pathCopyUsageSql18), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyUsageSql18).toInstant().isAfter(kijunTime), updateText);

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
