package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * EraseWkTblUkaiKenkinDataTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblUkaiKenkinDataTaskletTest {

    /** 迂回献金のワークテーブルを自身のデータを削除する */
    @Autowired
    private EraseWkTblUkaiKenkinDataTasklet eraseWkTblUkaiKenkinDataTasklet;
    
    @Test
    void test()throws Exception {
        
        assertEquals(RepeatStatus.FINISHED,eraseWkTblUkaiKenkinDataTasklet.execute(null, null),"自分のデータのみ消去");
        
        fail("Not yet implemented");
    }

}
