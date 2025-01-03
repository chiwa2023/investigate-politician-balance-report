package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;

/**
 * DeleteFinancialTenpoWkTbl2ItemReader単体テスト
 */

@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteFinancialTenpoWkTbl2ItemReaderTest {

    /** テスト対象 */
    @Autowired
    private DeleteFinancialTenpoWkTbl2ItemReader deleteFinancialTenpoWkTbl2ItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({"sample/zengin_org_branch_wk2.sql","sample/zengin_org_branch_master.sql"})
    void test() throws Exception {

        ZenginOrgBranchMasterEntity entity1 = deleteFinancialTenpoWkTbl2ItemReader.read();
        assertEquals("長野銀行若槻支店", entity1.getZenginOrgTempoMasterName(), "1件目");

        ZenginOrgBranchMasterEntity entityNot = deleteFinancialTenpoWkTbl2ItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得しようとするとnull");

    }

}
