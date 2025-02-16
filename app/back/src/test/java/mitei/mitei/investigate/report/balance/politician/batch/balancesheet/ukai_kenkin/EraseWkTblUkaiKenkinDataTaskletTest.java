package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * EraseWkTblUkaiKenkinDataTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblUkaiKenkinDataTaskletTest {
    // CHECKSTYLE:OFF

    /** 迂回献金のワークテーブルを自身のデータを削除する */
    @Autowired
    private EraseWkTblUkaiKenkinDataTasklet eraseWkTblUkaiKenkinDataTasklet;

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "erase_wk_tbl_ukai_kenki.sql", "erase_ukai_kenkin_route.sql" })
    void test() throws Exception {

        eraseWkTblUkaiKenkinDataTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, eraseWkTblUkaiKenkinDataTasklet.execute(null, null), "自分のデータのみ消去正常終了");

        assertEquals(10, wkTblUkaiKenkinRepository.count(), "削除後の明細テーブルの件数が一致");
        assertEquals(4, wkTblUkaiKenkinPickupRouteRepository.count(), "削除後の経路テーブルの件数が一致");

    }

    // 起動条件をセット
    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", 123_321L).addLong("userCode", 987L).addString("userName", "ユーザ")
                .addLocalDateTime("executeTime", LocalDateTime.now()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
