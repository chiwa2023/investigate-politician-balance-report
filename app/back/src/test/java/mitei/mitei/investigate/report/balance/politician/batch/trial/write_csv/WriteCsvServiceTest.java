package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;

/**
 * WriteCsvService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class)//全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WriteCsvServiceTest {

    /** テスト対象 */
    @Autowired
    private WriteCsvService writeCsvService;
    
    @Test
    void testPractice()throws Exception {
        //テスト時はできるまでループをしないので、バッチ管理テーブルにデータが入っているときはすでに実行済例外が返る
        assertThrows(JobInstanceAlreadyCompleteException.class, () -> writeCsvService.practice());

        //TODO バッチ管理テーブル初期化時は上記例外チェックを無効にし、実行完了チェックをする
        //assertThat(writeCsvService.practice()).isEqualTo("COMPLETED");
    }

}
