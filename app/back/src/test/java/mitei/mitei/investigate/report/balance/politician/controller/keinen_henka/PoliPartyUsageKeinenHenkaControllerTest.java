package mitei.mitei.investigate.report.balance.politician.controller.keinen_henka;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * PoliPartyUsageKeinenHenkaController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PoliPartyUsageKeinenHenkaControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../logic/party_usage/y2022/usage_0107_surface_2022.sql",
            "../../logic/party_usage/y2022/usage_0203_summary_2022.sql",
            "../../logic/party_usage/y2023/usage_0107_surface_2023.sql",
            "../../logic/party_usage/y2023/usage_0203_summary_2023.sql",
            "../../logic/party_usage/y2024/usage_0107_surface_2024.sql",
            "../../logic/party_usage/y2024/usage_0203_summary_2024.sql",
            "../../logic/party_usage/y2025/usage_0107_surface_2025.sql",
            "../../logic/party_usage/y2025/usage_0203_summary_2025.sql" })
    void test() throws Exception {

        KeinenHenkaConditionCapsuleDto capsuleDto = new KeinenHenkaConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setPoliOrgCode(132);
        capsuleDto.setHoukokuNenStart(2022);
        capsuleDto.setHoukokuNenEnd(2025);
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/keinen-usage/search";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "ステータス200が返却");
    }

}
