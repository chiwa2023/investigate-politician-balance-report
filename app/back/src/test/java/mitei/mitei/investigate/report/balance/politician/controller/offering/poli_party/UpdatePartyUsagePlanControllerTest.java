package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.UpdatePartyUsageTaskPlanCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * UpdatePartyUsagePlanController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class UpdatePartyUsagePlanControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 政党交付金使途報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("../../../service/offering/poli_party/task_plan_party_usage_detail.sql")
    void testPractice() throws Exception {

        UpdatePartyUsageTaskPlanCapsuleDto capsuleDto = new UpdatePartyUsageTaskPlanCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        // 元データを編集した想定
        Long callId = 1245L;
        TaskPlanPartyUsageDetailEntity entitySrc = taskPlanPartyUsageDetailRepository.findById(callId).get();
        capsuleDto.setTaskPlanPartyUsageDetailEntity(entitySrc);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/update-party-usage-prepare/task-plan";

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "正常コード200");

    }

}
