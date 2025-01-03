package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

import static org.assertj.core.api.Assertions.fail;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * TransferCreateZenginChangeAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TransferCreateZenginChangeAsyncServiceTest {

    /** テスト対象 */
    @Autowired
    private TransferCreateZenginChangeAsyncService transferCreateZenginChangeAsyncService;

    @Test
    @Tag("WebClient8080")
    @Sql("../sample/zengin_org_change_branch.sql")
    void test() {
        assertDoesNotThrow(() -> transferCreateZenginChangeAsyncService.practice(),"とりあえず例外が戻らない");

        fail("Not yet implemented");
    }

}
