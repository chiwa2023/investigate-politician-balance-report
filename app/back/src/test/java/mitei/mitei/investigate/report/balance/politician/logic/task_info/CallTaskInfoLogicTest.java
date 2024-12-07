package mitei.mitei.investigate.report.balance.politician.logic.task_info;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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

import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;

/**
 * CallTaskInfoLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CallTaskInfoLogicTest {

    /** テスト対象 */
    @Autowired
    private CallTaskInfoLogic callTaskInfoLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("task_info.sql")
    void testPractice() {

        final String taskInfoKey = "政治資金収支報告書一括登録準備";
        List<String> listName = new ArrayList<>();
        listName.add(taskInfoKey);

        List<TaskInfoEntity> list = callTaskInfoLogic.practice(listName);

        assertThat(list.size()).isEqualTo(1);

        TaskInfoEntity entity00 = list.get(0);
        assertThat(entity00.getTaskInfoName()).isEqualTo(taskInfoKey);

    }

}
