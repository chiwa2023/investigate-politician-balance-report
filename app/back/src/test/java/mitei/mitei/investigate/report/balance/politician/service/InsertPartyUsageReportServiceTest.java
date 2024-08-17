package mitei.mitei.investigate.report.balance.politician.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * InsertPartyUsageReportService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageReportServiceTest {

    /** テスト対象 */
    private InsertPartyUsageReportService insertPartyUsageReportService;
    
    @Test
    void test()throws Exception {
        
        
        insertPartyUsageReportService.practice(null,null,null);
        
        fail("Not yet implemented");
    }

}
