package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

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
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchPoliPartyRelationPersonResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.political_party.SearchRlationPersonByPoliPtyLogic;

/**
 * SearchMemberShipFeeSummaryLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchMemberShipFeeSummaryLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchMemberShipFeeSummaryLogic searchMemberShipFeeSummaryLogic;

    /** 政党関連者紐づけRepository */
    @Autowired
    private SearchRlationPersonByPoliPtyLogic searchRlationPersonByPoliPtyLogic;

    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("offering_0702_fee_poli_org.sql")
    void testPoliticalOrg() throws Exception {

        // 取得可能かどうか(と月額かつ注意区分)
        SearchMembershipFeeCapsuleDto capsuleDto1 = this.createCapsule();

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto1 = searchMemberShipFeeSummaryLogic
                .practice(capsuleDto1, new SearchPoliPartyRelationPersonResultDto());

        assertEquals(1, resultDto1.getCountAll(), "1件");
        assertEquals(0, resultDto1.getPosPage(), "ページングがなく0");

        List<PoliOrgFeeInsuDto> listSummary1 = resultDto1.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto1 = listSummary1.get(0);

        assertEquals(432_430L, feeInsuDto1.getFee(), "会費が一致"); // NOPMD
        assertEquals(24, feeInsuDto1.getInsu(), "員数が一致");
        assertEquals(4343L, feeInsuDto1.getPoliticalOrgnaizationId(), "政治団体Idが一致");
        assertEquals(4340, feeInsuDto1.getPoliticalOrgnaizationCode(), "政治団体Codeが一致");
        assertEquals("政治団体", feeInsuDto1.getPoliticalOrgnaizationName(), "政治団体名称が一致");
        assertEquals(3210, feeInsuDto1.getPoliOrgDaihyoushaId(), "代表者Idが一致");
        assertEquals(3200, feeInsuDto1.getPoliOrgDaihyoushaCode(), "代表者Codeが一致");
        assertEquals("代表者名", feeInsuDto1.getPoliOrgDaihyoushaName(), "代表者名称が一致");
        assertEquals("18017.92", feeInsuDto1.getAverage(), "頭割りが一致");
        assertEquals("注意水準", feeInsuDto1.getRating(), "評価値::18017>330*12*3,18017<330*12*9");

        // 党費同値
        SearchMembershipFeeCapsuleDto capsuleDto2 = this.createCapsule();
        capsuleDto2.setFeeMonth(0);
        capsuleDto2.setFeeYear(18_018);
        capsuleDto2.setLevelAttention(9);
        capsuleDto2.setLevelWarning(200);

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto2 = searchMemberShipFeeSummaryLogic
                .practice(capsuleDto2, new SearchPoliPartyRelationPersonResultDto());
        List<PoliOrgFeeInsuDto> listSummary2 = resultDto2.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto2 = listSummary2.get(0);

        assertEquals("党費と同額", feeInsuDto2.getRating(), "評価値::18017=18017");

        // 警告
        SearchMembershipFeeCapsuleDto capsuleDto3 = this.createCapsule();
        capsuleDto3.setFeeMonth(0);
        capsuleDto3.setFeeYear(300);
        capsuleDto3.setLevelAttention(3);
        capsuleDto3.setLevelWarning(10);

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto3 = searchMemberShipFeeSummaryLogic
                .practice(capsuleDto3, new SearchPoliPartyRelationPersonResultDto());
        List<PoliOrgFeeInsuDto> listSummary3 = resultDto3.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto3 = listSummary3.get(0);

        assertEquals("警告水準", feeInsuDto3.getRating(), "評価値::18017>300*10");

        // 未満
        SearchMembershipFeeCapsuleDto capsuleDto4 = this.createCapsule();
        capsuleDto4.setFeeMonth(0);
        capsuleDto4.setFeeYear(30_000);
        capsuleDto4.setLevelAttention(3);
        capsuleDto4.setLevelWarning(10);

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto4 = searchMemberShipFeeSummaryLogic
                .practice(capsuleDto4, new SearchPoliPartyRelationPersonResultDto());
        List<PoliOrgFeeInsuDto> listSummary4 = resultDto4.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto4 = listSummary4.get(0);

        assertEquals("党費未満", feeInsuDto4.getRating(), "評価値::18017>30000");

        // より上
        SearchMembershipFeeCapsuleDto capsuleDto5 = this.createCapsule();
        capsuleDto5.setFeeMonth(0);
        capsuleDto5.setFeeYear(15_000);
        capsuleDto5.setLevelAttention(3);
        capsuleDto5.setLevelWarning(10);

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto5 = searchMemberShipFeeSummaryLogic
                .practice(capsuleDto5, new SearchPoliPartyRelationPersonResultDto());
        List<PoliOrgFeeInsuDto> listSummary5 = resultDto5.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto5 = listSummary5.get(0);

        assertEquals("党費より上", feeInsuDto5.getRating(), "評価値::18017<15000");

    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "offering_0702_fee_poli_party.sql", "political_party_relation_person.sql" })
    void testPoliticalParty() throws Exception {

        int poliPartyCode = 35;
        SearchPoliPartyRelationPersonResultDto searchResultDto = searchRlationPersonByPoliPtyLogic
                .practice(poliPartyCode, 0);

        // 取得可能かどうか
        SearchMembershipFeeCapsuleDto capsuleDto1 = this.createCapsule();
        capsuleDto1.setFeeMonth(330);
        capsuleDto1.setFeeYear(0);
        capsuleDto1.setLevelAttention(4);
        capsuleDto1.setLevelWarning(9);
        capsuleDto1.setPoliticalOrgnaizationId(0L);

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto1 = searchMemberShipFeeSummaryLogic
                .practice(capsuleDto1, searchResultDto);

        assertEquals(5, resultDto1.getCountAll(), "6件");
        assertEquals(0, resultDto1.getPosPage(), "100件超えていないので0");

        List<PoliOrgFeeInsuDto> listSummary1 = resultDto1.getListSummary();
        PoliOrgFeeInsuDto feeInsuDto1 = listSummary1.get(0);

        assertEquals(432_441L, feeInsuDto1.getFee(), "会費が一致");
        assertEquals(33, feeInsuDto1.getInsu(), "員数が一致");
        assertEquals(4343L, feeInsuDto1.getPoliticalOrgnaizationId(), "政治団体Idが一致");
        assertEquals(4340, feeInsuDto1.getPoliticalOrgnaizationCode(), "政治団体Codeが一致");
        assertEquals("政治団体", feeInsuDto1.getPoliticalOrgnaizationName(), "政治団体名称が一致");
        assertEquals(3210, feeInsuDto1.getPoliOrgDaihyoushaId(), "代表者Idが一致");
        assertEquals(144, feeInsuDto1.getPoliOrgDaihyoushaCode(), "代表者Codeが一致");
        assertEquals("代表者名", feeInsuDto1.getPoliOrgDaihyoushaName(), "代表者名称が一致");
        assertEquals("13104.27", feeInsuDto1.getAverage(), "頭割りが一致");
        assertEquals("党費より上", feeInsuDto1.getRating(), "評価値::13104>330*12*2");

        PoliOrgFeeInsuDto feeInsuDto2 = listSummary1.get(1);
        assertEquals(432_442L, feeInsuDto2.getFee(), "会費が一致");

        PoliOrgFeeInsuDto feeInsuDto3 = listSummary1.get(2);
        assertEquals(432_443L, feeInsuDto3.getFee(), "会費が一致");

    }

    private SearchMembershipFeeCapsuleDto createCapsule() {

        SearchMembershipFeeCapsuleDto capsuleDto = new SearchMembershipFeeCapsuleDto();

        capsuleDto.setFeeMonth(330);
        capsuleDto.setFeeYear(0);
        capsuleDto.setHoukokunen(2022);
        capsuleDto.setLevelAttention(3);
        capsuleDto.setLevelWarning(9);
        capsuleDto.setPoliticalOrgnaizationId(120L);
        capsuleDto.setPoliticalOrgnaizationCode(4340); // 検索条件
        capsuleDto.setPoliticalOrgnaizationName("ちゃらんぽらん政治団体");
        // 以下は不要(初期値)
        capsuleDto.setCountAll(0);
        capsuleDto.setPoliPartyCode(0);
        capsuleDto.setPosPage(0);

        return capsuleDto;
    }

}
