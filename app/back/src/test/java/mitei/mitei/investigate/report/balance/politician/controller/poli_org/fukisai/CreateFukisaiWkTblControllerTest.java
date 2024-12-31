package mitei.mitei.investigate.report.balance.politician.controller.poli_org.fukisai;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchBalancesheetFukisaiCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * CreateFukisaiWkTblController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class CreateFukisaiWkTblControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "update_task_plan_2024.sql", "erase_wk_tbl_fukisai.sql", "convert_income_fukisai_2022.sql",
            "convert_outcome_fukisai_2022.sql", "erase_task_plan_2024.sql", "task_info.sql" })
    void test()throws Exception {

        SearchBalancesheetFukisaiCapsuleDto capsuleDto = new SearchBalancesheetFukisaiCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        FukisaiSearchConditionDto fukisaiSearchConditionDto = new FukisaiSearchConditionDto();
        fukisaiSearchConditionDto.setHoukokuNen(2022);
        fukisaiSearchConditionDto.setIsSearchCode(true);
        fukisaiSearchConditionDto.setPoliOrgCode(431);
        capsuleDto.setFukisaiSearchConditionDto(fukisaiSearchConditionDto);
        
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String url = "/check-fukisai/create-wktbl";
        assertEquals(HttpStatus.OK.value(), mockMvc. // NOPMD
                perform(post(url) // 以下引数指定
                        .content(objectMapper.writeValueAsString(capsuleDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "レスポンスokが戻る");
    }

}
