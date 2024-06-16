package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * person形式のcsvを読み取り、report形式のcsvに書き出すバッチコントローラ
 */
@Controller
public class ReadCsvController {

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
     * webページからバッチを動作させるサンプル
     *
     * @param readPathText  読み取りファイル
     * @param writePathText 書き出しファイル
     * @return レスポンス結果
     * @throws JobParametersInvalidException       バッチ実行条件重複
     * @throws JobInstanceAlreadyCompleteException 実行完了インスタンスが存在
     * @throws JobRestartException                 再スタート例外
     * @throws JobExecutionAlreadyRunningException 実行中ジョブが存在する
     */
    @GetMapping("/trial-batch") // CHECKSTYLE:OFF
    public ResponseEntity<String> practice(final @RequestParam(name = "readPath") String readPathText,
            final @RequestParam(name = "writePath") String writePathText) throws JobParametersInvalidException,
            JobInstanceAlreadyCompleteException, JobRestartException, JobExecutionAlreadyRunningException {

        // http://localhost:8080/trial-batch?readPath=c:/temp/person.csv&writePath=c:/temp/report.csv

        // 書き出しファイルパスの生成
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replaceAll(":", "-");
        String writePath;
        final String key = ".csv";
        if (writePathText.endsWith(key)) {
            writePath = writePathText.substring(0, writePathText.length() - key.length()) + time + key;
        } else {
            writePath = writePathText + time + ".csv";
        }

        // 読み取りファイルが存在しないときは何もしていないサーバステータスを返却する
        if (!Files.exists(Paths.get(readPathText))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("読み取りファイルが見つかりません");
        }

        // 毎回書き込み側に日時をはさむので実行条件の重複は基本的にない
        jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFileName", readPathText).addString("writeFileName", writePath).toJobParameters();

        try {

            jobLauncher.run(readCsvJob, jobParameters);

            return ResponseEntity.ok("成功!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
