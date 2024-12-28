package mitei.mitei.investigate.report.balance.politician.logic.political_party;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

/**
 * RegistPoliticalPartyKanrenshaLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistPoliticalPartyRelationPersonLogicTest {

    /** 政党関連者紐づけテーブル */
    @Autowired
    private RegistPoliticalPartyRelationPersonLogic registPoliticalPaRelationPersonLogic;
    
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("")
    void test()throws Exception {
        
        assertEquals(1, registPoliticalPaRelationPersonLogic.practice(),"1行追加");
        
        fail("Not yet implemented");
    }

}
