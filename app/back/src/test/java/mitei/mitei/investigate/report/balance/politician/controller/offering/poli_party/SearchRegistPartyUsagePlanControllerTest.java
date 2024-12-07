package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * SearchRegistPartyUsagePlanController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRegistPartyUsagePlanControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../../../service/offering/poli_party/task_plan_party_usage_detail.sql")
    void testPractice() throws Exception {

        TemplateFrameworkCapsuleDto capsuleDto = new TemplateFrameworkCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();
        String path = "/listup-party-usage-charset/normal";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
