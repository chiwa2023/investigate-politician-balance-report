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

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.RefleshYearSingleLogicWithPersistenceObjectBatchConfiguration;

/**
 * タスク計画、書証保存など現在時間基準で処理を行うロジックの年更新処理を行う
 */
@Service
public class YearRefleshPersistenceObjectByNowYearAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob1 */
    @Qualifier(RefleshYearSingleLogicWithPersistenceObjectBatchConfiguration.JOB_NAME)
    @Autowired
    private Job refleshYearSingleLogicWithPersistenceObject;

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
    public void practice(final int baseYear,final int copyYear) // 改行よけ
            throws JobExecutionAlreadyRunningException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        
        // TaskPlan
        JobParameters jobParametersTaskPlan = new JobParametersBuilder()
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString("baseYear", String.valueOf(baseYear))
                .addString("copyYear", String.valueOf(copyYear))
                .addString("dirEntity", "main/java/mitei/mitei/investigate/report/balance/politician/entity/task_plan")
                .addString("dirRepository", "main/java/mitei/mitei/investigate/report/balance/politician/repository/task_plan")
                .addString("dirLogic", "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y")
                .addString("dirLogicTest", "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y")
                .addString("dirLogicTestSql", "test/resources/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y")
                .addString("isReadFileReflesh", "true")
                .addString("file1", "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/CallTaskPlanByCodeUserLogic.java")
                .addString("file2", "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/EraseWkTblPersonalTaskLogic.java")
                .addString("file3", "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/InsertTaskPlanFactoryLogic.java")
                .addString("file4", "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/InsertTaskPlanSingleLogic.java")
                .addString("file5", "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/UpdateFinishedByTaskNameUserLogic.java")
                .addString("java1", "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/CallTaskPlanByCodeUserLogicTest.java")
                .addString("java2", "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/EraseWkTblPersonalTaskLogicTest.java")
                .addString("java4", "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/InsertTaskPlanSingleLogicTest.java")
                .addString("java5", "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/UpdateFinishedByTaskNameUserLogicTest.java")
                .toJobParameters();

        jobLauncher.run(refleshYearSingleLogicWithPersistenceObject, jobParametersTaskPlan);
        
        // TaskPlan
        JobParameters jobParametersSaveStorage = new JobParametersBuilder()
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString("baseYear", String.valueOf(baseYear))
                .addString("copyYear", String.valueOf(copyYear))
                .addString("dirEntity", "main/java/mitei/mitei/investigate/report/balance/politician/entity/storage")
                .addString("dirRepository", "main/java/mitei/mitei/investigate/report/balance/politician/repository/storage")
                .addString("dirLogic", "main/java/mitei/mitei/investigate/report/balance/politician/logic/storage/y")
                //.addString("dirLogicTest", "test/java/mitei/mitei/investigate/report/balance/politician/logic/storage/y")
                //.addString("dirLogicTestSql", "test/resources/mitei/mitei/investigate/report/balance/politician/logic/storage/y")
                .addString("isReadFileReflesh", "true")
                .addString("file1", "main/java/mitei/mitei/investigate/report/balance/politician/logic/storage/SaveStorageFileLogic.java")
                //.addString("java1", "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/CallTaskPlanByCodeUserLogicTest.java")
                .toJobParameters();

        jobLauncher.run(refleshYearSingleLogicWithPersistenceObject, jobParametersSaveStorage);
        
    }
    
}
