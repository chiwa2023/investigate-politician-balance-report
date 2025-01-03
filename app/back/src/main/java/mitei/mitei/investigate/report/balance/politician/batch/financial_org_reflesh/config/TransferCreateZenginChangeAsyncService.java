package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

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
 * 金融機関変更テーブル作成側伝送非同期起動Service
 */
@Service
public class TransferCreateZenginChangeAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob1 */
    @Qualifier(TransferCreateZenginChangeBatchConfiguration.JOB_NAME)
    @Autowired
    private Job transferCreateZenginChange;

    /**
     * 非同期処理を行う
     *
     * @throws IOException                         ファイル関係例外
     * @throws JobExecutionAlreadyRunningException バッチ実行時例外
     * @throws JobRestartException                 バッチ実行時例外
     * @throws JobInstanceAlreadyCompleteException バッチ実行時例外
     * @throws JobParametersInvalidException       バッチ実行時例外
     */
    @Async
    public void practice() // 改行よけ
            throws IOException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {

        final String paramKeyTime = "executeTime";

        JobParameters jobParametersMaster = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now()).toJobParameters();

        jobLauncher.run(transferCreateZenginChange, jobParametersMaster);
    }

}
