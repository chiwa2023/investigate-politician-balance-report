package mitei.mitei.investigate.report.balance.politician.logic.user_web_access;


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

import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;

/**
 * CreateTaskLevelListLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateTaskLevelListLogicTest {

    /** テスト対象 */
    @Autowired
    private CreateTaskLevelListLogic createTaskLevelListLogic;
    
    @Test
    @Tag("TruncateTable")
    @Transactional
    @Sql("user_web_access.sql")
    void testPractice() {
        // CHECKSTYLE:OFF
        
        // 空リスト
        String levelText00 = "";
        List<UserWebAccessEntity> list00 = createTaskLevelListLogic.practice(levelText00);
        assertThat(list00.size()).isEqualTo(0);
        
        // 正常
        String levelText01 = "3";
        List<UserWebAccessEntity> list01 = createTaskLevelListLogic.practice(levelText01);
        assertThat(list01.size()).isEqualTo(1);
        UserWebAccessEntity entity01 = list01.get(0);
        assertThat(entity01.getUserName()).isEqualTo("調査員　三郎");
        
        // 数値変換不可
        String levelText02 = "3,q";
        List<UserWebAccessEntity> list02 = createTaskLevelListLogic.practice(levelText02);
        assertThat(list02.size()).isEqualTo(1);
        UserWebAccessEntity entity02 = list02.get(0);
        assertThat(entity02.getUserName()).isEqualTo("調査員　三郎");

        
        // 複数取得
        String levelText03 = "1,3,5,7,9";
        List<UserWebAccessEntity> list03 = createTaskLevelListLogic.practice(levelText03);
        assertThat(list03.size()).isEqualTo(5);
        list03.sort((e1,e2) -> e1.getUserWebAccessCode()-e2.getUserWebAccessCode());
        UserWebAccessEntity entity03a = list03.get(0);
        assertThat(entity03a.getUserName()).isEqualTo("調査員　一郎");
        UserWebAccessEntity entity03b = list03.get(1);
        assertThat(entity03b.getUserName()).isEqualTo("調査員　三郎");
        UserWebAccessEntity entity03c = list03.get(2);
        assertThat(entity03c.getUserName()).isEqualTo("調査員　五郎");
        UserWebAccessEntity entity03d = list03.get(3);
        assertThat(entity03d.getUserName()).isEqualTo("調査員　七郎");
        UserWebAccessEntity entity03e = list03.get(4);
        assertThat(entity03e.getUserName()).isEqualTo("調査員　九郎");

    }

}
