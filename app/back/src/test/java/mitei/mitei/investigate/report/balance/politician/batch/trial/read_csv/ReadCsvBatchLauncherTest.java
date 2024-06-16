package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskHolder;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * ReadCsvBatchLauncher単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = { BackApplication.class, // 全体起動
        ReadCsvBatchLauncher.class // テスト対象)
})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadCsvBatchLauncherTest {
    // CHECKSTYLE:OFF
    
    /** テスト対象 */
    @Autowired
    private ReadCsvBatchLauncher readCsvBatchLauncher;
    
    /** spring boot タスク保持クラス */
    @Autowired
    private ScheduledTaskHolder scheduledTaskHolder;
    // ↑取得するのはScheduledAnnotationBeanPostProcessorでも可    
    
    @Test
    void testLaunchJob()throws Exception {

        // テスト対象のクラス.メソッド名
        final String taskName = "ReadCsvBatchLauncher.launchJob";

        int checkCount = 0;
        ScheduledTask scheduledTask = null;
        for (ScheduledTask task : scheduledTaskHolder.getScheduledTasks()) {
            if (task.toString().endsWith(taskName)) {
                scheduledTask = task;
                checkCount++;
            }
        }

        // スケージュールを指定したjobLauncharは一つに絞れているはずです(念のため)。
        assertEquals(1, checkCount, "合致しているLaucherは1件であること");
        assertNotNull(scheduledTask, "確実に代入されていること");

        if (scheduledTask.getTask() instanceof CronTask) {
            CronTask cronTask = (CronTask) scheduledTask.getTask(); // NOPMD

            // テストだと現在時間でなく過去(未来)日付で基準日付はOK
            LocalDateTime dateTime = LocalDateTime.of(2022, 2, 22, 12, 34, 18);

            // SpringBoot登録側
            Instant instant = dateTime.toInstant(ZoneOffset.UTC);
            Instant instantRegist = cronTask.getTrigger() // NOPMD
                    .nextExecution(new SimpleTriggerContext(instant, instant, instant));

            // コードから力ずくで取得して予測時間を計算
            String expression = readCsvBatchLauncher.getClass().getMethod("launchJob")
                    .getAnnotation(Scheduled.class).cron();
            Instant instantExpect = CronExpression.parse(expression).next(dateTime).toInstant(ZoneOffset.UTC);

            // 記載したコードの通りbatch側スケジュールに登録されている
            assertEquals(instantExpect, instantRegist, "ソースからのスケジュール値が実行環境に違いなく登録されている");

            // 起動したい時間通りに計算されている
            OffsetDateTime offsetDateTime = instantRegist.atOffset(ZoneOffset.UTC); // NOPMD

            assertEquals(2022, offsetDateTime.getYear(), "起動時間の確認(年)");
            assertEquals(2, offsetDateTime.getMonthValue(), "起動時間の確認(月)");
            assertEquals(22, offsetDateTime.getDayOfMonth(), "起動時間の確認(日)");
        
        }
    }

}
