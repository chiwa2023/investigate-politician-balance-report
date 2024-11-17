package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import static org.assertj.core.api.Assertions.assertThat;

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

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;

/**
 * SearchRegistBalancesheetPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRegistBalancesheetPlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRegistBalancesheetPlanService searchRegistBalancesheetPlanService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("task_plan_balancesheet_detail.sql")
    void testPractice() {

        List<TaskPlanBalancesheetDetailEntity> list = searchRegistBalancesheetPlanService.practice();
        list.sort((e1, e2) -> e1.getInsertUserCode() - e2.getInsertUserCode());

        assertThat(list.size()).isEqualTo(2); // 正常登録は2件

        TaskPlanBalancesheetDetailEntity entity00 = list.get(0);
        assertThat(entity00.getTaskPlanBalancesheetDetailId()).isEqualTo(1245L);

        TaskPlanBalancesheetDetailEntity entity01 = list.get(1);
        assertThat(entity01.getTaskPlanBalancesheetDetailId()).isEqualTo(1246L);

    }

}
