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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;

/**
 * AddFinancialTenpoWkTbl3ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AddFinancialTenpoWkTbl3ItemReaderTest {

    /** テスト対象 */
    @Autowired
    private AddFinancialTenpoWkTbl3ItemReader addFinancialTenpoWkTbl3ItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ZenginOrgBranchWk3Entity entity1 = addFinancialTenpoWkTbl3ItemReader.read();
        assertEquals("東京シティ信用金庫日本橋支店", entity1.getZenginOrgBranchWk3Name(), "金融機関店舗名称が一致");

        ZenginOrgBranchWk3Entity entity2 = addFinancialTenpoWkTbl3ItemReader.read();
        assertEquals("東京シティ信用金庫新小岩支店", entity2.getZenginOrgBranchWk3Name(), "金融機関店舗名称が一致");

        ZenginOrgBranchWk3Entity entityNot = addFinancialTenpoWkTbl3ItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得時はnull");

    }

}
