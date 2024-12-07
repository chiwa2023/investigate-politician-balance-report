package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_org;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.UpdateBalancesheetWkTblCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * UpdateBalancesheetWkTbController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class UpdateBalancesheetWkTbControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../../../service/offering/poli_org/wk_tbl_poli_org_balancesheet_report.sql")
    void testPractice() throws Exception {

        UpdateBalancesheetWkTblCapsuleDto capsuleDto = new UpdateBalancesheetWkTblCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        Long callId = 101L;
        WkTblPoliOrgBalancesheetReportEntity entitySrc = wkTblPoliOrgBalancesheetReportRepository.findById(callId)
                .get();

        Long poliOrgId = 326L;
        Integer poliOrgCode = 320;
        String poliOrgName = "ちゃらんぽらん政治団体";

        entitySrc.setPoliticalOrganizationId(poliOrgId);
        entitySrc.setPoliticalOrganizationCode(poliOrgCode);
        entitySrc.setPoliticalOrganizationName(poliOrgName);
        capsuleDto.setWkTblPoliOrgBalancesheetReportEntity(entitySrc);

        // 共通チェックとドキュメント種類

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "http://localhost:9080/update-balancesheet-prepare/worktable";

        // サーバステータスがOK(200)
        assertThat(mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}
