package mitei.mitei.investigate.report.balance.politician.service.year_reflesh;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.RefleshSingleLogicOnlyBatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.RefleshYearBalancesheetAndUsageWithPersistenceObjectBatchConfiguration;

/**
 * 政治資金収支報告書・政党交付金使途報告書など報告年基準で処理を行うロジックの年更新処理を行う
 */
@Service
public class YearRefleshPersistenceObjectByHoukokunenAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob1 */
    @Qualifier(RefleshYearBalancesheetAndUsageWithPersistenceObjectBatchConfiguration.JOB_NAME)
    @Autowired
    private Job refleshYearBalancesheetAndUsageWithPersistenceObject;

    /** 起動をするJob1 */
    @Qualifier(RefleshSingleLogicOnlyBatchConfiguration.JOB_NAME)
    @Autowired
    private Job refleshSingleLogicOnly;

    /**
     * 非同期処理を行う
     *
     * @param baseYear 複写元年
     * @param copyYear 複写先年
     * @throws IOException                         ファイル関係例外
     * @throws JobExecutionAlreadyRunningException バッチ実行時例外
     * @throws JobRestartException                 バッチ実行時例外
     * @throws JobInstanceAlreadyCompleteException バッチ実行時例外
     * @throws JobParametersInvalidException       バッチ実行時例外
     */
    @Async
    public void practice(final int baseYear, final int copyYear) // 改行よけ NOPMD
            throws JobExecutionAlreadyRunningException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        final String baseYearText = "baseYear";
        final String copyYearText = "copyYear";
        final String dirEntity = "dirEntity";
        final String dirRepository = "dirRepository";
        final String dirLogic = "dirLogic";
        final String dirLogicIncome = "dirLogicIncome";
        final String dirLogicTest = "dirLogicTest";
        final String dirLogicTestIncome = "dirLogicTestIncome";
        final String dirLogicTestSql = "dirLogicTestSql";
        final String isReadFileReflesh = "isReadFileReflesh"; // NOPMD
        final String fileA = "fileA";
        final String fileB = "fileB";
        final String fileC = "fileC";
        final String file01 = "file01";
        final String file02 = "file02";
        final String file03 = "file03";
        final String file04 = "file04";
        final String file05 = "file05";
        final String file06 = "file06";
        final String file07 = "file07";
        final String file08 = "file08";
        final String file11 = "file11";
        final String file12 = "file12";
        final String file13 = "file13";
        final String file14 = "file14";
        final String file15 = "file15";
        final String file16 = "file16";
        final String file17 = "file17";
        final String file18 = "file18";

        final String javaA = "javaA";
        final String javaB = "javaB";
        final String javaC = "javaC";
        final String java01 = "java01";
        final String java02 = "java02";
        final String java03 = "java03";
        final String java04 = "java04";
        final String java05 = "java05";
        final String java06 = "java06";
        final String java07 = "java07";
        final String java08 = "java08";
        final String java11 = "java11";
        final String java12 = "java12";
        final String java13 = "java13";
        final String java14 = "java14";
        final String java15 = "java15";
        final String java16 = "java16";
        final String java17 = "java17";
        final String java18 = "java18";

        final String file = "file";
        final String java = "java";
        final String sql1 = "sql1";
        final String sql2 = "sql2";
        final String sql3 = "sql3";
        final String sql4 = "sql4";
        final String sql5 = "sql5";            
            
        // 政治資金収支報告書
        JobParameters jobParametersBalancesheet = new JobParametersBuilder(refleshYearBalancesheetAndUsageWithPersistenceObject.getJobParametersIncrementer().getNext(new JobParameters())) //NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString(baseYearText, String.valueOf(baseYear))
                .addString(copyYearText, String.valueOf(copyYear))
                .addString(dirEntity, "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_org/balancesheet/y")
                .addString(dirRepository, "main/java/mitei/mitei/investigate/report/balance/politician/repository/poli_org/balancesheet/y")
                .addString(dirLogic, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y")
                .addString(dirLogicIncome, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y" + baseYear + "/income")
                .addString(dirLogicTest, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y")
                .addString(dirLogicTestIncome, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/income/y" + baseYear + "/income")
                .addString(dirLogicTestSql, "test/resources/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y")
                .addString(isReadFileReflesh, "true")
                .addString(fileA, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/CheckAllreadyRegistDataPoliticalOrganizationLogic.java")
                .addString(fileB, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/ConvertFukisaiIncomeLogic.java")
                .addString(fileC, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/ConvertFukisaiOutcomeLogic.java")
                // .addString("fileD", "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/SearchMemberShipFeeSummaryLogic.java") TOD 各ロジックで取得に修正したのち追加
                .addString(file01, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganization08000Logic.java")
                .addString(file02, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganization0802Logic.java")
                .addString(file03, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationEstateAllLogic.java")
                .addString(file04, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationIncomeAllLogic.java")
                .addString(file05, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationOutcomeAllLogic.java")
                .addString(file06, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationSheet0701And0720Logic.java")
                .addString(file07, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationSummaryLogic.java")
                .addString(file11, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganization08000Logic.java")
                .addString(file12, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganization0802Logic.java")
                .addString(file13, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationEstateAllLogic.java")
                .addString(file14, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationIncomeAllLogic.java")
                .addString(file15, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationOutcomeAllLogic.java")
                .addString(file16, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationSheet0701And0720Logic.java")
                .addString(file17, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationSummaryLogic.java")
                .addString(javaA, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/CheckAllreadyRegistDataPoliticalOrganizationLogicTest.java")
                .addString(javaB, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/ConvertFukisaiIncomeLogicTest.java")
                .addString(javaC, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/ConvertFukisaiOutcomeLogicTest.java")
                // .addString("javaD", "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/SearchMemberShipFeeSummaryLogicTest.java") TOD 各ロジックで取得に修正したのち追加
                .addString(java01, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganization08000LogicTest.java")
                .addString(java02, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganization0802LogicTest.java")
                .addString(java03, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationEstateAllLogicTest.java")
                .addString(java04, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationIncomeAllLogicTest.java")
                .addString(java05, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationOutcomeAllLogicTest.java")
                .addString(java06, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationSheet0701And0720LogicTest.java")
                .addString(java07, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/InsertPoliticalOrganizationSummaryLogicTest.java")
                .addString(java11, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganization08000LogicTest.java")
                .addString(java12, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganization0802LogicTest.java")
                .addString(java13, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationEstateAllLogicTest.java")
                .addString(java14, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationIncomeAllLogicTest.java")
                .addString(java15, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationOutcomeAllLogicTest.java")
                .addString(java16, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationSheet0701And0720LogicTest.java")
                .addString(java17, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/UpdatePoliticalOrganizationSummaryLogicTest.java")
                .toJobParameters();

        jobLauncher.run(refleshYearBalancesheetAndUsageWithPersistenceObject, jobParametersBalancesheet);

        // 全文検索
        JobParameters jobParametersNaturalSearch = new JobParametersBuilder(refleshSingleLogicOnly.getJobParametersIncrementer().getNext(new JobParameters())) //NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString(baseYearText, String.valueOf(baseYear))
                .addString(copyYearText, String.valueOf(copyYear))
                .addString(dirLogic, "main/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y")
                .addString(dirLogicTest, "test/java/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y")
                .addString(dirLogicTestSql, "test/resources/mitei/mitei/investigate/report/balance/politician/logic/poli_org/balancesheet/y")
                .addString(isReadFileReflesh, "true")
                .addString(file, "main/java/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/SearchIncomeAndOutcomeBySearchWordsService.java")
                .addString(java, "test/java/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/SearchIncomeAndOutcomeBySearchWordsServiceTest.java")
                .addString(sql1, "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/truncate_income_outcome_2022.sql")
                .addString(sql2, "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_income_horie_2022.sql")
                .addString(sql3, "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_income_joshi_2022.sql")
                .addString(sql4, "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_outcome_horie_2022.sql")
                .addString(sql5, "test/resources/mitei/mitei/investigate/report/balance/politician/service/offering/natural_search/offering_balancesheet_outcome_joshi_2022.sql")
                .toJobParameters();

        jobLauncher.run(refleshSingleLogicOnly, jobParametersNaturalSearch);
        
        
        // 政党交付金使途報告書
        JobParameters jobParametersPartyUsage = new JobParametersBuilder(refleshYearBalancesheetAndUsageWithPersistenceObject.getJobParametersIncrementer().getNext(new JobParameters())) //NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString(baseYearText, String.valueOf(baseYear))
                .addString(copyYearText, String.valueOf(copyYear))
                .addString(dirEntity, "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_party/usage/y")
                .addString(dirRepository, "main/java/mitei/mitei/investigate/report/balance/politician/repository/poli_party/usage/y")
                .addString(dirLogic, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/y")
                .addString(dirLogicTest, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/y")
                .addString(dirLogicTestSql, "test/resources/mitei/mitei/investigate/report/balance/politician/logic/party_usage/y")
                .addString(isReadFileReflesh, "true")
                .addString(file, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/CheckAllreadyRegistDataPartyUsageLogic.java")
                .addString(file01, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0801And0807Logic.java")
                .addString(file02, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0802And0803Logic.java")
                .addString(file03, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0802Kbn02Logic.java")
                .addString(file04, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0804Logic.java")
                .addString(file05, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0805Logic.java")
                .addString(file06, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0806Logic.java")
                .addString(file07, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0901Logic.java")
                .addString(file08, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0902Logic.java")
                .addString(file11, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0801And0807Logic.java")
                .addString(file12, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0802And0803Logic.java")
                .addString(file13, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0802Kbn02Logic.java")
                .addString(file14, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0804Logic.java")
                .addString(file15, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0805Logic.java")
                .addString(file16, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0806Logic.java")
                .addString(file17, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0901Logic.java")
                .addString(file18, "main/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatetPartyUsageShito0902Logic.java")
                .addString(java, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/CheckAllreadyRegistDataPartyUsageLogicTest.java")
                .addString(java01, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0801And0807LogicTest.java")
                .addString(java02, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0802And0803LogicTest.java")
                .addString(java03, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0802Kbn02LogicTest.java")
                .addString(java04, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0804LogicTest.java")
                .addString(java05, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0805LogicTest.java")
                .addString(java06, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0806LogicTest.java")
                .addString(java07, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0901LogicTest.java")
                .addString(java08, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/InsertPartyUsageShito0902LogicTest.java")
                .addString(java11, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0801And0807LogicTest.java")
                .addString(java12, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0802And0803LogicTest.java")
                .addString(java13, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0802Kbn02LogicTest.java")
                .addString(java14, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0804LogicTest.java")
                .addString(java15, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0805LogicTest.java")
                .addString(java16, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0806LogicTest.java")
                .addString(java17, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatePartyUsageShito0901LogicTest.java")
                .addString(java18, "test/java/mitei/mitei/investigate/report/balance/politician/logic/party_usage/UpdatetPartyUsageShito0902LogicTest.java")
                .toJobParameters();

        jobLauncher.run(refleshYearBalancesheetAndUsageWithPersistenceObject, jobParametersPartyUsage);

    }
}
