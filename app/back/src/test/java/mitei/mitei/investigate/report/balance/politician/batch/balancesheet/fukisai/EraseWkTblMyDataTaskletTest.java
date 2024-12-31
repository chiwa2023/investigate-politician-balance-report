package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;

/**
 * EraseWkTblMyDataTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblMyDataTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblMyDataTasklet eraseWkTblMyDataTasklet;

    /** 不記載ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("erase_wk_tbl_fukisai.sql")
    void testNotIniial() throws Exception {

        assertEquals(6L, wkTblFukisaiBalancesheetRepository.count(), "処理前には6件");

        StepExecution execution = this.getStepExecution();
        eraseWkTblMyDataTasklet.beforeStep(execution);

        assertEquals(RepeatStatus.FINISHED, eraseWkTblMyDataTasklet.execute(null, null), "終了ステータスが戻る");

        assertEquals(2L, wkTblFukisaiBalancesheetRepository.count(), "処理後には2件");

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("erase_init_wk_tbl_fukisai.sql")
    void testIniial() throws Exception {

        assertEquals(4L, wkTblFukisaiBalancesheetRepository.count(), "処理前には4件");

        StepExecution execution = this.getStepExecution();
        eraseWkTblMyDataTasklet.beforeStep(execution);

        assertEquals(RepeatStatus.FINISHED, eraseWkTblMyDataTasklet.execute(null, null), "終了ステータスが戻る");

        assertEquals(0L, wkTblFukisaiBalancesheetRepository.count(), "処理後には0件");

        // 空行を挿入
        WkTblFukisaiBalancesheetEntity entity = new WkTblFukisaiBalancesheetEntity();
        wkTblFukisaiBalancesheetRepository.save(entity);

        List<WkTblFukisaiBalancesheetEntity> list = wkTblFukisaiBalancesheetRepository.findAll();
        assertEquals(1, list.size(), "1件追加");

        WkTblFukisaiBalancesheetEntity entitySaved = list.get(0);
        assertEquals(1L, entitySaved.getWkTblFukisaiBalancesheetId(), "auto_incrementを初期化したのでidは1スタート");

    }

    private StepExecution getStepExecution() {
        final int userCode = 970;

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userCode", Long.valueOf(userCode)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
