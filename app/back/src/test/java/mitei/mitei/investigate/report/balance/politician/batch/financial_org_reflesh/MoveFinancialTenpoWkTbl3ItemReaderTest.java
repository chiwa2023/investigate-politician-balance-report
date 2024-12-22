package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import java.time.LocalDateTime;

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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;

/**
 * MoveFinancialTenpoWkTbl3ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveFinancialTenpoWkTbl3ItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MoveFinancialTenpoWkTbl3ItemReader moveFinancialTenpoWkTbl3ItemReader;
    
    @Test
    @Tag("TableTruncate")
    @Sql({"sample/zengin_org_branch_master.sql","sample/zengin_org_branch_wk3.sql"})
    void test() throws Exception {

        ZenginOrgBranchWk3Entity entity01 = moveFinancialTenpoWkTbl3ItemReader.read();
        assertEquals("東京シティ信用金庫新柴又支店", entity01.getZenginOrgBranchWk3Name(), "取得金融機関店舗名称");

        // 自動Mappingでないのですべて1回は検査
        assertEquals(78, entity01.getZenginOrgBranchWk3Id(), "");
        assertEquals(72, entity01.getZenginOrgBranchWk3Code(), "");
        assertEquals(1, entity01.getSaishinKbn(), "");
        assertEquals("1311", entity01.getOrgCode(), "");
        assertEquals("066", entity01.getBranchCode(), "");
        assertEquals("トウキョウシティシンヨウキンコ", entity01.getOrgNameKana(), "");
        assertEquals("東京シティ信用金庫", entity01.getOrgName(), "");
        assertEquals("シンシバマタシテン", entity01.getBranchNameKana(), "");
        assertEquals("新柴又支店", entity01.getBranchName(), "");
        assertEquals("333-098x", entity01.getPostalCode(), "");
        assertEquals("葛飾区鎌倉3-28-24", entity01.getBranchAddress(), "");
        assertEquals("0336578701", entity01.getPhonNumber(), "");
        assertEquals("1", entity01.getBillExchangeNumber(), "");
        assertEquals("1", entity01.getOrderCode(), "");
        assertEquals("1", entity01.getFlgNaikokuKawase(), "");
        assertEquals(339L, entity01.getInsertUserId(), "");
        assertEquals(330, entity01.getInsertUserCode(), "");
        assertEquals("ユーザA", entity01.getInsertUserName(), "");
        assertEquals(LocalDateTime.of(2024, 12, 17, 22, 07, 28), entity01.getInsertTimestamp(), "");

        ZenginOrgBranchWk3Entity entityNot = moveFinancialTenpoWkTbl3ItemReader.read();
        assertEquals(null, entityNot, "取得件数以上でnull");

    }

}
