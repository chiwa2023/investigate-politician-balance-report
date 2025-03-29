package mitei.mitei.investigate.report.balance.politician.controller.poli_org.kanrensha;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * ShowBalancesheetReportByKanrenshaController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class ShowBalancesheetReportByKanrenshaControllerTest {
    // CHECKSTYLE:OFF

    /** mockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../../service/poli_org/kanrensha/search_kanrensha_income_2022.sql",
            "../../../service/poli_org/kanrensha/search_kanrensha_outcome_2022.sql" })
    void test() throws Exception {

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto = new KanrenshaBalancesheetConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setHoukokuNen(2022);
        capsuleDto.setOffset(25);
        // 取引相手
        capsuleDto.setRelationKbn(1);
        capsuleDto.setRelationCode(360);

        // 収入全検出
        capsuleDto.setIsSearchJournal(true);
        capsuleDto.setIsSearchBorrowed(true);
        capsuleDto.setIsSearchRelatedlgrants(true);
        capsuleDto.setIsSearchOtherIncome(true);
        capsuleDto.setIsSearchDonatePerson(true);
        capsuleDto.setIsSearchDonateCorp(true);
        capsuleDto.setIsSearchDonatePoliOrg(true);
        capsuleDto.setIsSearchDonateMediate(true);
        capsuleDto.setIsSearchAnonymus(true);
        capsuleDto.setIsSearchSpecificParty(true);
        capsuleDto.setIsSearchPartyPerson(true);
        capsuleDto.setIsSearchPartyCorp(true);
        capsuleDto.setIsSearchPartyPoliOrg(true);
        capsuleDto.setIsSearchPartyMediate(true);

        // 支出全検出
        capsuleDto.setIsSearchKounetsuhi(true);
        capsuleDto.setIsSearchShoumouhin(true);
        capsuleDto.setIsSearchJimusho(true);
        capsuleDto.setIsSearchActivation(true);
        capsuleDto.setIsSearchElection(true);
        capsuleDto.setIsSearchPaper(true);
        capsuleDto.setIsSearchComercial(true);
        capsuleDto.setIsSearchPartyOutcome(true);
        capsuleDto.setIsSearchBuissiness(true);
        capsuleDto.setIsSearchResearch(true);
        capsuleDto.setIsSearchDonation(true);
        capsuleDto.setIsSearchOtherOutcome(true);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/show-balancesheet/kanrensha";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "ステータス200が返却");

    }

}
