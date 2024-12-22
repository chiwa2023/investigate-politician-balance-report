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

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * MoveFinancialTenpoChangeItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveFinancialTenpoChangeItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MoveFinancialTenpoChangeItemReader moveFinancialTenpoChangeItemReader;
    
    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("zengin_org_change_branch.sql")
    void test()throws  Exception{
        
        ZenginOrgChangeBranchEntity entity1 = moveFinancialTenpoChangeItemReader.read();
        assertEquals("農林中央金庫仙台支店", entity1.getZenginOrgTempoMasterName(), "金融機関店舗名称が一致");
        assertEquals(85, entity1.getZenginOrgTempoMasterId(), "重要なので元Idチェック");
        assertEquals(79, entity1.getZenginOrgTempoMasterCode(), "重要なので元Idチェック");

        ZenginOrgChangeBranchEntity entityNot = moveFinancialTenpoChangeItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得時はnull");

    }

}
