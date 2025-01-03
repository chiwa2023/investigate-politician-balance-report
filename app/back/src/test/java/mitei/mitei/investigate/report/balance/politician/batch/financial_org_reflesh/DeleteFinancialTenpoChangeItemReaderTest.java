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
 * DeleteFinancialTenpoChangeItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteFinancialTenpoChangeItemReaderTest {

    /** テスト対象 */
    @Autowired
    private DeleteFinancialTenpoChangeItemReader deleteFinancialTenpoChangeItemReader;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("zengin_org_change_branch_set_ido.sql")
    void test() throws Exception {

        ZenginOrgChangeBranchEntity entity1 = deleteFinancialTenpoChangeItemReader.read();
        assertEquals("農林中央金庫前橋支店", entity1.getZenginOrgTempoMasterName(), "金融機関店舗名称が一致");

        ZenginOrgChangeBranchEntity entityNot = deleteFinancialTenpoChangeItemReader.read();
        assertEquals(null, entityNot, "件数以上に取得時はnull");
    }

}
