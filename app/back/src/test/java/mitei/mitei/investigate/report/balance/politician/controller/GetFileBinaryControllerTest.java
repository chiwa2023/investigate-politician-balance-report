package mitei.mitei.investigate.report.balance.politician.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * GetFileBinaryController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetFileBinaryControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPractice() throws Exception {

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        // 現時点ではここでの指定と関係ない特定のファイルしか呼ばない
        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/src.md");
        saveStorageResultDto.setShoshouId(path.toString());

        assertThat(this.mockMvc // NOPMD
                .perform(post("/get-file-binary").content(objectMapper.writeValueAsString(saveStorageResultDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn() // NOPMD
                .getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
