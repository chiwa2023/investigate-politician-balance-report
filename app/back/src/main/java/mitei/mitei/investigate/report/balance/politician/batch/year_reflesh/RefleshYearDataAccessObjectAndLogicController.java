package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * データアクセス用のオブジェクトとロジックを複写するController
 */
@Controller
public class RefleshYearDataAccessObjectAndLogicController {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob */
    @Qualifier(RefleshYearDataAccessObjectAndLogicBatchConfiguration.JOB_NAME)
    @Autowired
    private Job refleshYearDataAccessObjectAndLogicBatchConfiguration;

    /**
     * データアクセスオブジェクトを複写する
     *
     * @param baseYear 複写元となる年
     * @param copyYear 複写先となる年
     * @return レスポンス(仮でStringメッセージ)
     */
    @GetMapping("/reflesh-year-data-access") // SUPPRESS CHECKSTYLE
    public ResponseEntity<String> practice(final @RequestParam(name = "baseYear") int baseYear, final @RequestParam(name = "copyYear") int copyYear) {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("baseYear", String.valueOf(baseYear))
                .addString("copyYear", String.valueOf(copyYear))
                .addLocalDateTime("now", LocalDateTime.now()) // 実作業では必要としないがパラメータ一致よけ
                .toJobParameters();
        try {
            // Batch動作させる
            jobLauncher.run(refleshYearDataAccessObjectAndLogicBatchConfiguration, jobParameters);

            return ResponseEntity.ok("成功!");
        } catch (Exception e) { // NOPMD
            return ResponseEntity.internalServerError().build();
        }
    }

}
