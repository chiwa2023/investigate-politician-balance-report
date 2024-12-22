package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;

/**
 * MoveFinancialTenpoWkTbl1ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveFinancialTenpoWkTbl1ItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MoveFinancialTenpoWkTbl1ItemReader moveFinancialTenpoWkTbl1ItemReader;

    @Test
    @Tag("TableTruncate")
    @Sql({"sample/zengin_org_branch_master.sql","sample/zengin_org_branch_wk1.sql"})
    void test() throws Exception {

        ZenginOrgBranchWk1Entity entity01 = moveFinancialTenpoWkTbl1ItemReader.read();
        assertEquals("みずほ銀行ひばりが丘支店", entity01.getZenginOrgBranchWk1Name(), "取得金融機関店舗名称");

        // 自動Mappingでないのですべて1回は検査
        assertEquals(35, entity01.getZenginOrgBranchWk1Id(), "");
        assertEquals(29, entity01.getZenginOrgBranchWk1Code(), "");
        assertEquals(1, entity01.getSaishinKbn(), "");
        assertEquals("0001", entity01.getOrgCode(), "");
        assertEquals("262", entity01.getBranchCode(), "");
        assertEquals("ミズホギンコウ", entity01.getOrgNameKana(), "");
        assertEquals("みずほ銀行", entity01.getOrgName(), "");
        assertEquals("ヒバリガオカシテン", entity01.getBranchNameKana(), "");
        assertEquals("ひばりが丘支店", entity01.getBranchName(), "");
        assertEquals("202-0002", entity01.getPostalCode(), "");
        assertEquals("東京都西東京市ひばりが丘北4-3-27", entity01.getBranchAddress(), "");
        assertEquals("042-aaa-bbb", entity01.getPhonNumber(), "");
        assertEquals("1", entity01.getBillExchangeNumber(), "");
        assertEquals("1", entity01.getOrderCode(), "");
        assertEquals("1", entity01.getFlgNaikokuKawase(), "");
        assertEquals(339L, entity01.getInsertUserId(), "");
        assertEquals(330, entity01.getInsertUserCode(), "");
        assertEquals("ユーザA", entity01.getInsertUserName(), "");
        assertEquals(LocalDateTime.of(2024, 12, 17, 20, 18, 41), entity01.getInsertTimestamp(), "");

        ZenginOrgBranchWk1Entity entityNot = moveFinancialTenpoWkTbl1ItemReader.read();
        assertEquals(null, entityNot, "取得件数以上でnull");

    }

}
