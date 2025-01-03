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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;

/**
 * AddFinancialTenpoWkTbl1ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AddFinancialTenpoWkTbl1ItemReaderTest {

    /** テスト対象 */
    @Autowired
    private AddFinancialTenpoWkTbl1ItemReader addFinancialTenpoWkTbl1ItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({"sample/zengin_org_branch_wk1.sql","sample/zengin_org_branch_master.sql"})
    void test() throws Exception {

        ZenginOrgBranchWk1Entity entity1 = addFinancialTenpoWkTbl1ItemReader.read();
        assertEquals("みずほ銀行八坂支店", entity1.getZenginOrgBranchWk1Name(), "金融機関店舗名称が一致");

        ZenginOrgBranchWk1Entity entity2 = addFinancialTenpoWkTbl1ItemReader.read();
        assertEquals("三菱ufj銀行多摩センター支店", entity2.getZenginOrgBranchWk1Name(), "金融機関店舗名称が一致");

        ZenginOrgBranchWk1Entity entityNot = addFinancialTenpoWkTbl1ItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得時はnull");

    }

}
