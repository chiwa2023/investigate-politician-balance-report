package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

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

/**
 * 使途報告書一括準備処理を非同期で実行するためのWrapService
 */
@Service
public class RegistPreaparePoliPtyUsageBatchAyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob */
    @Qualifier(RegistPreaparePoliPtyUsageBatchConfiguration.JOB_NAME)
    @Autowired
    private Job registJob;

    
    /**
     * 非同期処理を行う
     *
     * @throws IOException ファイル関係例外
     * @throws JobExecutionAlreadyRunningException バッチ実行時例外
     * @throws JobRestartException バッチ実行時例外
     * @throws JobInstanceAlreadyCompleteException バッチ実行時例外
     * @throws JobParametersInvalidException バッチ実行時例外
     */
    @Async
    public void practice() throws IOException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {

        // 毎回書き込み側に日時をはさむので実行条件の重複は基本的にない
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("execute_time", LocalDateTime.now()).toJobParameters();

        jobLauncher.run(registJob, jobParameters);
    }

    
    
}
