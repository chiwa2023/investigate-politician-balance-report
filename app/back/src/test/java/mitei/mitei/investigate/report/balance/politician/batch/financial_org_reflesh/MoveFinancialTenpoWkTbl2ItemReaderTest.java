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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;

/**
 * MoveFinancialTenpoWkTbl2ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveFinancialTenpoWkTbl2ItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MoveFinancialTenpoWkTbl2ItemReader moveFinancialTenpoWkTbl2ItemReader;

    @Test
    @Tag("TableTruncate")
    @Sql({"sample/zengin_org_branch_master.sql","sample/zengin_org_branch_wk2.sql"})
    void test() throws Exception {

        ZenginOrgBranchWk2Entity entity01 = moveFinancialTenpoWkTbl2ItemReader.read();
        assertEquals("長野銀行丹波島支店", entity01.getZenginOrgBranchWk2Name(), "取得金融機関店舗名称");

        // 自動Mappingでないのですべて1回は検査
        assertEquals(65, entity01.getZenginOrgBranchWk2Id(), "");
        assertEquals(59, entity01.getZenginOrgBranchWk2Code(), "");
        assertEquals(1, entity01.getSaishinKbn(), "");
        assertEquals("0533", entity01.getOrgCode(), "");
        assertEquals("137", entity01.getBranchCode(), "");
        assertEquals("ナガノギンコウ", entity01.getOrgNameKana(), "");
        assertEquals("長野銀行", entity01.getOrgName(), "");
        assertEquals("タンバジマシテン", entity01.getBranchNameKana(), "");
        assertEquals("丹波島支店", entity01.getBranchName(), "");
        assertEquals("111", entity01.getPostalCode(), "");
        assertEquals("長野市青木島1丁目35番5号", entity01.getBranchAddress(), "");
        assertEquals("026-285-5520", entity01.getPhonNumber(), "");
        assertEquals("1", entity01.getBillExchangeNumber(), "");
        assertEquals("1", entity01.getOrderCode(), "");
        assertEquals("1", entity01.getFlgNaikokuKawase(), "");
        assertEquals(339L, entity01.getInsertUserId(), "");
        assertEquals(330, entity01.getInsertUserCode(), "");
        assertEquals("ユーザA", entity01.getInsertUserName(), "");
        assertEquals(LocalDateTime.of(2024, 12, 17, 22, 06, 56), entity01.getInsertTimestamp(), "");

        ZenginOrgBranchWk2Entity entityNot = moveFinancialTenpoWkTbl2ItemReader.read();
        assertEquals(null, entityNot, "取得件数以上でnull");

    }

}
