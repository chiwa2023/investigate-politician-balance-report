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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;

/**
 * DeleteFinancialTenpoWkTbl1ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteFinancialTenpoWkTbl1ItemReaderTest {

    /** テスト対象 */
    @Autowired
    private DeleteFinancialTenpoWkTbl1ItemReader deleteFinancialTenpoWkTbl1ItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        ZenginOrgBranchMasterEntity entity1 = deleteFinancialTenpoWkTbl1ItemReader.read();
        assertEquals("みずほ銀行東久留米支店", entity1.getZenginOrgTempoMasterName(), "1件目");

        ZenginOrgBranchMasterEntity entity2 = deleteFinancialTenpoWkTbl1ItemReader.read();
        assertEquals("三菱ufj銀行相模大野支店", entity2.getZenginOrgTempoMasterName(), "2件目");

        ZenginOrgBranchMasterEntity entityNot = deleteFinancialTenpoWkTbl1ItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得しようとするとnull");

    }

}
