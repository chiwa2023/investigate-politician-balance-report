package mitei.mitei.investigate.report.balance.politician.controller.membership_fee;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchMembershipFeeCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * SearchPoliPartyMembershipFeeController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPoliPartyMembershipFeeControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "../../service/membership_fee/offering_0702_fee_poli_party.sql",
            "../../service/membership_fee/political_party_relation_person.sql" })
    void test() throws Exception {

        // 取得可能かどうか確認
        SearchMembershipFeeCapsuleDto capsuleDto = new SearchMembershipFeeCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        capsuleDto.setPoliticalOrgnaizationId(0L); // 代表者から検索
        capsuleDto.setPoliPartyCode(35); // 検索条件
        capsuleDto.setFeeMonth(330);
        capsuleDto.setFeeYear(0);
        capsuleDto.setLevelAttention(4);
        capsuleDto.setLevelWarning(9);
        capsuleDto.setHoukokunen(2022);

        // 以下は不要(初期値)
        capsuleDto.setPoliticalOrgnaizationCode(0);
        capsuleDto.setPoliticalOrgnaizationName("");
        capsuleDto.setCountAll(0);
        capsuleDto.setPosPage(0);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/invest-membership-fee/search-summary";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "ステータス200が返却");
    }

}
