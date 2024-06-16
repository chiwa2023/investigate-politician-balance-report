package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

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
import org.springframework.stereotype.Service;

/**
 * 固定csvを書き出すJobを起動するService
 */
@Service
public class WriteCsvService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob */
    @Qualifier(WriteCsvBatchConfiguration.JOB_NAME)
    @Autowired
    private Job readCsvJob;

    /** JobParameter */
    private JobParameters jobParameters = new JobParameters();

    /**
     * 固定CSV書き出しバッチを起動する
     *
     * @return バッチの終了コード
     * @throws JobParametersInvalidException       バッチ実行条件重複
     * @throws JobInstanceAlreadyCompleteException 実行完了インスタンスが存在
     * @throws JobRestartException                 再スタート例外
     * @throws JobExecutionAlreadyRunningException 実行中ジョブが存在する
     */
    public String practice() throws JobParametersInvalidException, JobInstanceAlreadyCompleteException,
            JobRestartException, JobExecutionAlreadyRunningException {

        jobParameters = new JobParametersBuilder(readCsvJob.getJobParametersIncrementer().getNext(this.jobParameters)) // NOPMD
                .toJobParameters();

        return jobLauncher.run(readCsvJob, jobParameters).getExitStatus().getExitCode();

    }

}
