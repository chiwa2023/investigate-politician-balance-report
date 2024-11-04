package mitei.mitei.investigate.report.balance.politician.batch.time_schedule;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

import mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist.RegistPreaparePoliOrgBalancesheetBatchConfiguration;

/**
 * 政治資金収支報告書一括登録準備Batch時間間隔起動
 */
//@EnableScheduling
//@Component
public class RegistPreparePoliticalBalancesheetBatchLauncher {

    // タスク

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob */
    @Qualifier(RegistPreaparePoliOrgBalancesheetBatchConfiguration.JOB_NAME)
    @Autowired
    private Job registPreaparePoliOrgBalancesheetJob;

    /**
     * 時間間隔と必要な起動引数を設定し、所定時刻で起動する
     *
     * @throws JobExecutionException ジョブ設定に誤りがある場合の例外
     */
    @Scheduled(cron = "0 1 * * * *")
    public void launchJob() throws JobExecutionException, URISyntaxException {

        JobParameters jobParameters = new JobParametersBuilder(
                registPreaparePoliOrgBalancesheetJob.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).toJobParameters();

        jobLauncher.run(registPreaparePoliOrgBalancesheetJob, jobParameters);

    }

}
