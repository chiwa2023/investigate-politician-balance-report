package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_org;

import static org.assertj.core.api.Assertions.assertThat;
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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.UpdateBalancesheetTaskPlanCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * UpdateBalancesheetPlanController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateBalancesheetPlanControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 詳細タスク計画Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../../../service/offering/poli_org/task_plan_balancesheet_detail.sql")
    void testPractice() throws Exception {

        UpdateBalancesheetTaskPlanCapsuleDto capsuleDto = new UpdateBalancesheetTaskPlanCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        // 元データを編集した想定
        Long callId = 1247L;
        String charset = "Shift_JIS";
        TaskPlanBalancesheetDetailEntity entitySrc = taskPlanBalancesheetDetailRepository.findById(callId).get();
        entitySrc.setCharset(charset);

        capsuleDto.setTaskPlanBalancesheetDetailEntity(entitySrc);

        // 共通チェックとドキュメント種類

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "http://localhost:9080//update-balancesheet-prepare/task-plan";

        // サーバステータスがOK(200)
        assertThat(mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}
