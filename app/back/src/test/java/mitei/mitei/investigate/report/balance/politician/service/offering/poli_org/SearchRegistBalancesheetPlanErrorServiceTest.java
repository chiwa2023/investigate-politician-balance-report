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
 * SearchRegistBalancesheetPlanErrorService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRegistBalancesheetPlanErrorServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRegistBalancesheetPlanErrorService searchRegistBalancesheetPlanErrorService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("task_plan_balancesheet_detail.sql")
    void testPractice() {

        List<TaskPlanBalancesheetDetailEntity> list = searchRegistBalancesheetPlanErrorService.practice();

        assertThat(list.size()).isEqualTo(1); // 以上登録は1件

        TaskPlanBalancesheetDetailEntity entity00 = list.get(0);
        assertThat(entity00.getTaskPlanBalancesheetDetailId()).isEqualTo(1247L);

    }

}
