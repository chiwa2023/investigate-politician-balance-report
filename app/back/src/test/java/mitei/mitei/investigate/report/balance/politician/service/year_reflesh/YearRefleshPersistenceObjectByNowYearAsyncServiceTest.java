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
 * YearRefleshPersistenceObjectByNowYearAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class YearRefleshPersistenceObjectByNowYearAsyncServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private YearRefleshPersistenceObjectByNowYearAsyncService yearRefleshPersistenceObjectByNowYearAsyncService;

    /** 複写元年(そのソースを運用している西暦年または1年前) */
    private static final int BASE_YEAR = 2024;

    @Test
    @Tag("SourceReflesh")
    void test() throws Exception {
        assertDoesNotThrow(() -> yearRefleshPersistenceObjectByNowYearAsyncService.practice(BASE_YEAR, 2023),
                "とりあえず例外なく最後まで走る");
    }

}
