package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.assertj.core.api.Assertions.assertThat;

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
 * TaskPlanBalancesheetDetailItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TaskPlanBalancesheetDetailItemReaderTest {
    // CHECKSTYLE:OFF

    /** 単体テスト */
    @Autowired
    private TaskPlanBalancesheetDetailItemReader taskPlanBalancesheetDetailItemReader;

    @Test
    @Transactional
    @Sql("task_plan_balancesheet_detail.sql")
    void test() throws Exception {

        // Step組み立ての際にChnkとして与えられるので外部設定
        taskPlanBalancesheetDetailItemReader.setPageSize(2);
        
        // 8件登録中条件にあう5件を抽出
        // SQLログをオンにしてページサイズ2の時は3回+1回SQLが発行されているのを確認するとベター
        TaskPlanBalancesheetDetailEntity entity01 = taskPlanBalancesheetDetailItemReader.read();
        assertThat(entity01.getTaskPlanBalancesheetDetailId()).isEqualTo(101L);

        TaskPlanBalancesheetDetailEntity entity02 = taskPlanBalancesheetDetailItemReader.read();
        assertThat(entity02.getTaskPlanBalancesheetDetailId()).isEqualTo(201L);

        TaskPlanBalancesheetDetailEntity entity03 = taskPlanBalancesheetDetailItemReader.read();
        assertThat(entity03.getTaskPlanBalancesheetDetailId()).isEqualTo(401L);

        TaskPlanBalancesheetDetailEntity entity04 = taskPlanBalancesheetDetailItemReader.read();
        assertThat(entity04.getTaskPlanBalancesheetDetailId()).isEqualTo(501L);

        TaskPlanBalancesheetDetailEntity entity05 = taskPlanBalancesheetDetailItemReader.read();
        assertThat(entity05.getTaskPlanBalancesheetDetailId()).isEqualTo(801L);

        // 件数以上取得しようとしてもnullが戻るだけ
        TaskPlanBalancesheetDetailEntity entity06 = taskPlanBalancesheetDetailItemReader.read();
        assertThat(entity06).isEqualTo(null);

    }

}
