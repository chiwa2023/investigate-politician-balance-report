package mitei.mitei.investigate.report.balance.politician.service.year_reflesh;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * YearRefleshPersistenceObjectByHoukokunenAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class YearRefleshPersistenceObjectByHoukokunenAsyncServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private YearRefleshPersistenceObjectByHoukokunenAsyncService yearRefleshPersistenceObjectByHoukokunenAsyncService;

    /** 複写元年(最初に作ったサンプルが2022年のため、基本2022年固定) */
    private static final int BASE_YEAR = 2022;

    @Test
    @Tag("SourceReflesh")
    void test() {

        assertDoesNotThrow(() -> yearRefleshPersistenceObjectByHoukokunenAsyncService.practice(BASE_YEAR, 2023),
                "とりあえず例外なく最後まで走る");
    }

}
