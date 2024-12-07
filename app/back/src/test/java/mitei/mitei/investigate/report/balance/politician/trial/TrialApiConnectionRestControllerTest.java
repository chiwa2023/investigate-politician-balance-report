package mitei.mitei.investigate.report.balance.politician.trial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;

/**
 * TrialApiConnectionRestController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TrialApiConnectionRestControllerTest {

    /** MockMvc. */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    void testTrialApiConnection() throws Exception {

        String responsContent = this.mockMvc.perform(get("/trial-api-rest")).andExpect(status().isOk()).andReturn() // NOPMD
                .getResponse().getContentAsString(StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();

        MakeTrialTestDataLogic makeTrialTestDataLogic = new MakeTrialTestDataLogic();
        List<SelectOptionDto> list = makeTrialTestDataLogic.practice();

        assertEquals(responsContent, objectMapper.writeValueAsString(list), "JSONが一致");
    }

}
