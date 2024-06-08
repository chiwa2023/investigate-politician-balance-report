package mitei.mitei.investigate.report.balance.politician.trial;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

/**
 * BalancesheetXmlController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class BalancesheetXmlControllerTest {

    /** MockMvc. */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test()throws Exception {
        String responsContent = this.mockMvc.perform(get("/convert-balancesheet-xml")).andExpect(status().isOk()).andReturn() // NOPMD
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        
        System.out.println(responsContent);
    }

}