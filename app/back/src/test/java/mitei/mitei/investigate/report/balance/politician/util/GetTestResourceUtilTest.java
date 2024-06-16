package mitei.mitei.investigate.report.balance.politician.util;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

/**
 * GetTestResourceUtil単体テスト
 */
class GetTestResourceUtilTest {

    @Test
    void test() throws Exception {

        //テストリソース内で存在するファイルのパスを指定
        Path path = GetTestResourceUtil.practice("/batch/trial/tasklet_test.csv");
        
        assertTrue(Files.exists(path),"指定したパスのファイルが存在");
    }

}
