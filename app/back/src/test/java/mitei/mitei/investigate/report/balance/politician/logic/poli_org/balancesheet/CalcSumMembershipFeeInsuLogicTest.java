package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.FeeSummaryResultDto;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalPartyRelationPersonRepository;

/**
 * CalcSumMembershipFeeInsuLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CalcSumMembershipFeeInsuLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CalcSumMembershipFeeInsuLogic calcSumMembershipFeeInsuLogic;
    
    /** 政党関連者紐づけRepository */
    @Autowired
    private PoliticalPartyRelationPersonRepository politicalPartyRelationPersonRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "offering_0702_fee_poli_party.sql", "political_party_relation_person.sql" })
    void test()throws Exception {

        List<Integer> listRelationPersonCode = politicalPartyRelationPersonRepository
                .findByPoliticalPartyCode(35,Pageable.unpaged());
        
        FeeSummaryResultDto resultDto = calcSumMembershipFeeInsuLogic.practice(listRelationPersonCode);
        assertEquals(2_162_215L, resultDto.getSumFee(),"党費合計が一致");
        assertEquals(175, resultDto.getSumInsu(),"員数合計が一致");
    }

}
