package mitei.mitei.investigate.report.balance.politician.controller.renketsu_koufukin;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplatePagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * SearchRenketsuOutcomeFailureController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class SearchRenketsuOutcomeFailureControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "../../service/renketsu_koufukin/wktbl_renketsu_koufukin.sql",
            "../../service/renketsu_koufukin/wktbl_usage_outcome.sql" })
    void test() throws Exception {

        TemplatePagingCapsuleDto capsuleDto = new TemplatePagingCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.getCheckPrivilegeDto().setLoginUserCode(987);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String url = "/renketsu-koufukin/outcome-failure";
        assertEquals(HttpStatus.OK.value(), mockMvc. // NOPMD
                perform(post(url) // 以下引数指定
                        .content(objectMapper.writeValueAsString(capsuleDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "レスポンスokが戻る");
    }

}
