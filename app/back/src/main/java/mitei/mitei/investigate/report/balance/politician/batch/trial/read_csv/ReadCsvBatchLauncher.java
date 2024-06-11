package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//TODO 本番起動時には下記@EnableSchedulingを有効にする。そのままにするとapplication.propertyで
//batchを有効化しているかどうかに関係なく起動してくる(スケジュール機能ってそういう物らしい)
/**
 * バッチをタイムスケージュールで自動起動するランチャ
 */
@EnableScheduling
@Component
public class ReadCsvBatchLauncher {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob */
    @Qualifier(ReadCsvBatchConfiguration.JOB_NAME)
    @Autowired
    private Job readCsvJob;

    /** JobParameter */
    private JobParameters jobParameters = new JobParameters();

    /**
     * 時間間隔と必要な起動引数を設定し、所定時刻で起動する
     *
     * @throws JobExecutionException ジョブ設定に誤りがある場合の例外
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void launchJob() throws JobExecutionException {

        jobParameters = new JobParametersBuilder(readCsvJob.getJobParametersIncrementer().getNext(this.jobParameters)) // NOPMD
                .addString("readFileName", "c:/temp/person.csv").addString("writeFileName", "c:/temp/report2.csv")
                .toJobParameters();

        jobLauncher.run(readCsvJob, jobParameters);
    }

}
