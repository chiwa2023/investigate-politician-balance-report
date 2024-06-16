package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.util.GetTestResourceUtil;

/**
 * ReadCsvController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class ReadCsvControllerTest {

    /** MockMvc. */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOk() throws Exception {

        // 正常起動
        String readPath = GetTestResourceUtil.practice("batch/trial/person.csv").toString();

        String path = "http://localhost:8080/trial-batch?readPath=" + readPath + "&writePath=c:/temp/report.csv";

        String responsContent = this.mockMvc.perform(get(path)).andExpect(status().isOk()).andReturn() // NOPMD
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertThat(responsContent).isEqualTo("成功!");

    }

    @Test
    void testNoContent() throws Exception {

        // 読み取りファイルなしケース
        String readPath = GetTestResourceUtil.practice("batch/trial/person.csv").toString() + "xyz";

        String path = "http://localhost:8080/trial-batch?readPath=" + readPath + "&writePath=c:/temp/report.csv";

        String responsContent = this.mockMvc.perform(get(path)).andExpect(status().isNoContent()).andReturn() // NOPMD
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertThat(responsContent).isEqualTo("読み取りファイルが見つかりません");

    }
}
