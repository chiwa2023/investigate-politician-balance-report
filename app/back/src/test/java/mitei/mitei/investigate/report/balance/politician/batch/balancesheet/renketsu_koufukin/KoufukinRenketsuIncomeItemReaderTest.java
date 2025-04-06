package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinSelectDto;

/**
 * KoufukinRenketsuIncomeItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class KoufukinRenketsuIncomeItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private KoufukinRenketsuIncomeItemReader koufukinRenketsuIncomeItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        koufukinRenketsuIncomeItemReader.setPageSize(10);
        koufukinRenketsuIncomeItemReader.beforeStep(this.getStepExecution());

        WkTblRenketsuKoufukinSelectDto entity = koufukinRenketsuIncomeItemReader.read();
        assertEquals(1L, entity.getBalancesheetId());
    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", 12_231L).addLong("userCode", Long.valueOf(987)).addString("userName", "ユーザ")
                .addLong("documentCodeUsage", 1L).addLong("documentCodeBalance", 7L)
                .addLong("houkokunen", Long.valueOf(2022)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
