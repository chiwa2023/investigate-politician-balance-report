package mitei.mitei.investigate.report.balance.politician.service.membership_fee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.PoliOrgFeeInsuDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchMembershipFeeCapsuleDto;

/**
 * SearchPoliPartyMembershipFeeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPoliPartyMembershipFeeServiceTest {

    /** テスト対象 */
    @Autowired
    private SearchPoliPartyMembershipFeeService searchPoliPartyMembershipFeeService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "offering_0702_fee_poli_party.sql", "political_party_relation_person.sql" })
    void test() throws Exception {
        // CHECKSTYLE:OFF

        // 取得可能かどうか確認
        SearchMembershipFeeCapsuleDto capsuleDto = new SearchMembershipFeeCapsuleDto();
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

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto1 = searchPoliPartyMembershipFeeService
                .practice(capsuleDto);

        assertEquals(5, resultDto1.getCountAll(), "6件");
        assertEquals(0, resultDto1.getPosPage(), "100件超えていないので0");

        final String textKaihi = "会費が一致";
        List<PoliOrgFeeInsuDto> listSummary1 = resultDto1.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto1 = listSummary1.get(0);
        assertEquals(432_441L, feeInsuDto1.getFee(), textKaihi);

        PoliOrgFeeInsuDto feeInsuDto2 = listSummary1.get(1);
        assertEquals(432_442L, feeInsuDto2.getFee(), textKaihi);

        PoliOrgFeeInsuDto feeInsuDto3 = listSummary1.get(2);
        assertEquals(432_443L, feeInsuDto3.getFee(), textKaihi);

    }

}
