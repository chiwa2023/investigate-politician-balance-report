package mitei.mitei.investigate.report.balance.politician.service.membership_fee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.FeeSummaryResultDto;

/**
 * CalcSumMembershipFeeInsuService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CalcSumMembershipFeeInsuServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CalcSumMembershipFeeInsuService calcSumMembershipFeeInsuService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "calc_offering_0702_fee_poli_party.sql", "calc_political_party_relation_person.sql" })
    void test() throws Exception {

        FeeSummaryResultDto resultDto = calcSumMembershipFeeInsuService.practice(35);
        assertEquals(2_162_215L, resultDto.getSumFee(), "党費合計が一致");
        assertEquals(175, resultDto.getSumInsu(), "員数合計が一致");

        // 政治団体を個別に指定したときは合計を算出しない
        FeeSummaryResultDto resultDtoEnmpty = calcSumMembershipFeeInsuService.practice(0);
        assertEquals(0L, resultDtoEnmpty.getSumFee(), "党費合計が空値");
        assertEquals(0, resultDtoEnmpty.getSumInsu(), "員数合計が空値");

    }

}
