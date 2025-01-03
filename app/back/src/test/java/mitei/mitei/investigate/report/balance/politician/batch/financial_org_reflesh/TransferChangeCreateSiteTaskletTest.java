package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * TransferChangeCreateSiteTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TransferChangeCreateSiteTaskletTest {

    /** テスト対象 */
    @Autowired
    private TransferChangeCreateSiteTasklet transferChangeCreateSiteTasklet;

    @Test
    @Tag("WebClient8080")
    @Transactional
    @Sql("sample/zengin_org_change_branch.sql")
    void test() throws Exception {

        assertEquals(RepeatStatus.FINISHED, transferChangeCreateSiteTasklet.execute(null, null), "とりあえずFinishしてればOK");
    }

}
