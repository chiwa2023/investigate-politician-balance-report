package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;

/**
 * SearchRegistPartyUsagePlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRegistPartyUsagePlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRegistPartyUsagePlanService searchRegistPartyUsagePlanService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("task_plan_party_usage_detail.sql")
    void testPractice() {

        List<TaskPlanPartyUsageDetailEntity> list = searchRegistPartyUsagePlanService.practice();
        list.sort((e1, e2) -> e1.getInsertUserCode() - e2.getInsertUserCode());

        assertEquals(2,list.size(),"正常登録は2件");

        TaskPlanPartyUsageDetailEntity entity00 = list.get(0);
        assertEquals(1245L, entity00.getTaskPlanPartyUsageDetailId(),"取得Idが一致");

        TaskPlanPartyUsageDetailEntity entity01 = list.get(1);
        assertEquals(1246L, entity01.getTaskPlanPartyUsageDetailId(),"取得Idが一致");
    }

}
