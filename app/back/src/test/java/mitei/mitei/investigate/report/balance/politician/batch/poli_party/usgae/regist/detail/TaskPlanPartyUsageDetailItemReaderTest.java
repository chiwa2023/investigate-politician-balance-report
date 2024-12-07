package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

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

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;

/**
 * TaskPlanPartyUsageDetailItemReader
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TaskPlanPartyUsageDetailItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private TaskPlanPartyUsageDetailItemReader taskPlanPartyUsageDetailItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../../../../../service/offering/poli_party/task_plan_party_usage_detail.sql")
    void test() throws Exception {

        // Step組み立ての際にChnkとして与えられるので外部設定
        taskPlanPartyUsageDetailItemReader.setPageSize(2);

        // SQLログをオンにしてページサイズ2の時は3回+1回SQLが発行されているのを確認するとベター
        TaskPlanPartyUsageDetailEntity entity01 = taskPlanPartyUsageDetailItemReader.read();
        assertEquals(1245L, entity01.getTaskPlanPartyUsageDetailId(), "1件目");

        TaskPlanPartyUsageDetailEntity entity02 = taskPlanPartyUsageDetailItemReader.read();
        assertEquals(1246L, entity02.getTaskPlanPartyUsageDetailId(), "2件目");

        // 件数以上取得しようとしてもnullが戻るだけ
        TaskPlanPartyUsageDetailEntity entityNot = taskPlanPartyUsageDetailItemReader.read();
        assertEquals(null, entityNot, "取得可能件数以上に要求");
    }

}
