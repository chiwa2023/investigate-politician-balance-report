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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk4Entity;

/**
 * MoveFinancialTenpoWkTbl4ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveFinancialTenpoWkTbl4ItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MoveFinancialTenpoWkTbl4ItemReader moveFinancialTenpoWkTbl4ItemReader;

    @Test
    @Tag("TableTruncate")
    @Sql({"sample/zengin_org_branch_master.sql","sample/zengin_org_branch_wk4.sql"})
    void test() throws Exception {

        ZenginOrgBranchWk4Entity entity01 = moveFinancialTenpoWkTbl4ItemReader.read();
        assertEquals("農林中央金庫仙台支店", entity01.getZenginOrgBranchWk4Name(), "取得金融機関店舗名称");

        // 自動Mappingでないのですべて1回は検査
        assertEquals(85, entity01.getZenginOrgBranchWk4Id(), "");
        assertEquals(79, entity01.getZenginOrgBranchWk4Code(), "");
        assertEquals(1, entity01.getSaishinKbn(), "");
        assertEquals("3000", entity01.getOrgCode(), "");
        assertEquals("220", entity01.getBranchCode(), "");
        assertEquals("ノウリンチュウオウキンコ", entity01.getOrgNameKana(), "");
        assertEquals("農林中央金庫", entity01.getOrgName(), "");
        assertEquals("センダイシテン", entity01.getBranchNameKana(), "");
        assertEquals("仙台支店", entity01.getBranchName(), "");
        assertEquals("980-0011", entity01.getPostalCode(), "");
        assertEquals("仙台市青葉区上杉1-2-161", entity01.getBranchAddress(), "");
        assertEquals("0227117531", entity01.getPhonNumber(), "");
        assertEquals("1", entity01.getBillExchangeNumber(), "");
        assertEquals("1", entity01.getOrderCode(), "");
        assertEquals("1", entity01.getFlgNaikokuKawase(), "");
        assertEquals(339L, entity01.getInsertUserId(), "");
        assertEquals(330, entity01.getInsertUserCode(), "");
        assertEquals("ユーザA", entity01.getInsertUserName(), "");
        assertEquals(LocalDateTime.of(2024, 12, 17, 22, 8, 1), entity01.getInsertTimestamp(), "");

        ZenginOrgBranchWk4Entity entityNot = moveFinancialTenpoWkTbl4ItemReader.read();
        assertEquals(null, entityNot, "取得件数以上でnull");

    }

}
