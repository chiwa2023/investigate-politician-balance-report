package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
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

import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;

/**
 * UkaiKenkinIncomeStage02TimesItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinIncomeStage02TimesItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UkaiKenkinIncomeStage02TimesItemReader ukaiKenkinIncomeStage02TimesItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_ukai_kenkin_times02.sql", "tasklet_stage_income_2022.sql" })
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        ukaiKenkinIncomeStage02TimesItemReader.beforeStep(execution);

        OfferingBalancesheetIncomeEntity entity00 = ukaiKenkinIncomeStage02TimesItemReader.read();
        assertEquals(1001L, entity00.getOfferingBalancesheetIncomeId(), "idが一致0");
        OfferingBalancesheetIncomeEntity entity01 = ukaiKenkinIncomeStage02TimesItemReader.read();
        assertEquals(1002L, entity01.getOfferingBalancesheetIncomeId(), "idが一致1");

        OfferingBalancesheetIncomeEntity entity02 = ukaiKenkinIncomeStage02TimesItemReader.read();
        assertEquals(1003L, entity02.getOfferingBalancesheetIncomeId(), "idが一致2");
        OfferingBalancesheetIncomeEntity entity03 = ukaiKenkinIncomeStage02TimesItemReader.read();
        assertEquals(1004L, entity03.getOfferingBalancesheetIncomeId(), "idが一致3");

        OfferingBalancesheetIncomeEntity entity04 = ukaiKenkinIncomeStage02TimesItemReader.read();
        assertNull(entity04, "最大4件");
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", 123_321L).addLong("userCode", 987L).addString("userName", "ユーザ")
                .addLong("houkokuNen", 2022L).addString("isSearchKoufukin", "true")
                .addLocalDateTime("executeTime", LocalDateTime.now()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
