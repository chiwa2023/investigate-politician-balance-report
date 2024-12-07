package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.same_dir;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Tag;
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
 * RefleshYearDataAccessDdlFileTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearDataAccessDdlFileTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearDataAccessDdlFileTasklet refleshYearDataAccessDdlFileTasklet;

    /** 複写元報告年 */
    private static final int baseYear = 2024;
    /** 複写先報告年 */
    private static final int copyYear = 2022;

    /** 複写先取得用相対ディレクトリ */
    private static final String copyPath = "main/resources/SQL/DDL";

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        refleshYearDataAccessDdlFileTasklet.beforeStep(execution);

        // 最後まで到達すればとりあえずOK(内容は各個別のTestを走らせるので)
        assertThat(refleshYearDataAccessDdlFileTasklet.execute(new StepContribution(execution), null))
                .isEqualTo(RepeatStatus.FINISHED);
        
        
        /* ファイルの存在だけ確認 */
        String pathAbs = GetCurrentResourcePath.getBackSrcPath(copyPath);

        final String expandText = ".sql";
        final String fileExistText = "ファイルが存在する";
        final String updateText = "更新時間が1分前より後";

        // FileTimeがUTCで返るのでそれに合わせる
        Instant kijunTime = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        kijunTime = kijunTime.minusSeconds(60); // SUPPRESS CHECKSTYLE MagicNumber

        Path pathCopyTaskMail = Paths.get(pathAbs,
                "send_alert_mail_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyTaskMail), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskMail).toInstant().isAfter(kijunTime), updateText);

        Path pathCopyTaskPlan = Paths.get(pathAbs,
                "task_plan_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopyTaskPlan), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopyTaskPlan).toInstant().isAfter(kijunTime), updateText);

        Path pathCopySaveStorage = Paths.get(pathAbs,
                "save_file_storage_" + copyYear + expandText);
        assertTrue(Files.exists(pathCopySaveStorage), fileExistText);
        assertTrue(Files.getLastModifiedTime(pathCopySaveStorage).toInstant().isAfter(kijunTime), updateText);
        
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
