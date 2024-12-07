package mitei.mitei.investigate.report.balance.politician.controller.offering.document.regist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * ReadXmlDocumentByCompressedZipController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadXmlDocumentByCompressedZipControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({"truncate_task_plan_balancesheet_detail.sql","task_info.sql","user_web_access.sql"})
    void testPractice()throws Exception {

        ReadXmlByFileCapsuleDto capsuleDto = new ReadXmlByFileCapsuleDto();
        
        // ファイル情報を設定
        String fileName = "all.zip";
        Path pathFile = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);
        String fileContent = Base64.getEncoder().encodeToString(Files.readAllBytes(pathFile));
        capsuleDto.setFileName(fileName);
        capsuleDto.setFileContent(fileContent);

        // 共通チェックとドキュメント種類
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setDocumentKey(DocumentRecognizeKeyConstants.SOFT_BALANCESHEET);
        
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "http://localhost:9080/zip-document/expand-task";

        // サーバステータスがOK(200)
        assertThat(mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
