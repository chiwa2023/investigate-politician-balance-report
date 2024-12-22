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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;

/**
 * AddFinancialTenpoWkTbl2ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AddFinancialTenpoWkTbl2ItemReaderTest {

    /** テスト対象 */
    @Autowired
    private AddFinancialTenpoWkTbl2ItemReader addFinancialTenpoWkTbl2ItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ZenginOrgBranchWk2Entity entity1 = addFinancialTenpoWkTbl2ItemReader.read();
        assertEquals("長野銀行須坂南支店", entity1.getZenginOrgBranchWk2Name(), "金融機関店舗名称が一致");

        ZenginOrgBranchWk2Entity entity2 = addFinancialTenpoWkTbl2ItemReader.read();
        assertEquals("長野銀行中野支店", entity2.getZenginOrgBranchWk2Name(), "金融機関店舗名称が一致");

        ZenginOrgBranchWk2Entity entityNot = addFinancialTenpoWkTbl2ItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得時はnull");

    }

}
