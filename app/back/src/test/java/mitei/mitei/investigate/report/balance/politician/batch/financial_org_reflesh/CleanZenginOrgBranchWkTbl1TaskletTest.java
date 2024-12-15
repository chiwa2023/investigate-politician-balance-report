package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.repeat.RepeatStatus;
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
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk1Repository;

/**
 * CleanZenginOrgBranchWkTbl1Tasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CleanZenginOrgBranchWkTbl1TaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CleanZenginOrgBranchWkTbl1Tasklet cleanZenginOrgBranchWkTbl1Tasklet;

    /** 全銀協記入機関・店舗ワークテーブル1Repository */
    @Autowired
    private ZenginOrgBranchWk1Repository zenginOrgBranchWk1Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("zengin_org_branch_wk1.sql")
    void test() throws Exception {

        assertEquals(3L, zenginOrgBranchWk1Repository.count(), "削除前は3行");

        assertEquals(RepeatStatus.FINISHED, cleanZenginOrgBranchWkTbl1Tasklet.execute(null, null), "実行時には完了Status");

        assertEquals(0L, zenginOrgBranchWk1Repository.count(), "削除後は0行");

        ZenginOrgBranchWk1Entity entity01 = new ZenginOrgBranchWk1Entity();
        entity01.setZenginOrgBranchWk1Id(0); // auto increment明示
        entity01.setOrgCode("111");
        entity01.setBranchCode("112");
        entity01.setOrgNameKana("113");
        entity01.setOrgName("114");
        entity01.setBranchNameKana("115");
        entity01.setBranchName("116");
        entity01.setPostalCode("117");
        entity01.setBranchAddress("118");
        entity01.setPhonNumber("119");
        entity01.setBillExchangeNumber("120");
        entity01.setOrderCode("121");
        entity01.setFlgNaikokuKawase("122");

        ZenginOrgBranchWk1Entity entityNew = zenginOrgBranchWk1Repository.save(entity01);

        assertEquals(1, entityNew.getZenginOrgBranchWk1Id(), "新たな行Idは1");
    }

}
