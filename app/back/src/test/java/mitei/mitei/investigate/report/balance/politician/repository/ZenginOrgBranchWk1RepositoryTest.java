package mitei.mitei.investigate.report.balance.politician.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;

/**
 * ZenginOrgBranchWk1Repository単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({"../batch/financial_org_reflesh/sample/zengin_org_branch_wk1.sql","../batch/financial_org_reflesh/sample/zengin_org_branch_master.sql"})
class ZenginOrgBranchWk1RepositoryTest {

    /** テスト対象 */
    @Autowired
    private ZenginOrgBranchWk1Repository zenginOrgBranchWk1Repository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void testAdd()throws Exception {

        List<ZenginOrgBranchWk1Entity> list = zenginOrgBranchWk1Repository.findAddBranch(null).getContent();
        assertEquals(2, list.size(),"2件取得");
        
        ZenginOrgBranchWk1Entity entity00 = list.get(0);
        assertEquals("みずほ銀行八坂支店", entity00.getZenginOrgBranchWk1Name(),"想定行を取得");

        ZenginOrgBranchWk1Entity entity01 = list.get(1);
        assertEquals("三菱ufj銀行多摩センター支店", entity01.getZenginOrgBranchWk1Name(),"想定行を取得");
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    void testMove() {

        List<ZenginOrgBranchWk1Entity> list = zenginOrgBranchWk1Repository.findMoveBranch(null).getContent();
        assertEquals(1, list.size(),"1件取得");

        ZenginOrgBranchWk1Entity entity00 = list.get(0);
        assertEquals("みずほ銀行ひばりが丘支店", entity00.getZenginOrgBranchWk1Name(),"想定行を取得");
    }

}
