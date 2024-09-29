package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * RefleshYearDataAccessObjectAndLogicController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class RefleshYearDataAccessObjectAndLogicControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPractice() throws Exception {

        String path = "http://localhost:9080/reflesh-year-data-access?baseYear=2025&copyYear=2022";

        // サーバステータスがOK(200)
        assertThat(this.mockMvc.perform(get(path)).andExpect(status().isOk()).andReturn().getResponse().getStatus()) // NOPMD
                .isEqualTo(HttpStatus.OK.value());
    }

}
