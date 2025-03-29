package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;

/**
 * ShowBakancesheetReportByKanrenshaLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ShowBakancesheetReportByKanrenshaLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ShowBakancesheetReportByKanrenshaLogic showBakancesheetReportByKanrenshaLogic;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "../y2022/search_kanrensha_income_2022.sql", "../y2022/search_kanrensha_outcome_2022.sql" })
    void test() throws Exception { // NOPMD

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto = new KanrenshaBalancesheetConditionCapsuleDto();
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

        IncomeAndOutcomeNaturalSearchResultDto resultDto = showBakancesheetReportByKanrenshaLogic.practice(capsuleDto);
        assertTrue(resultDto.getIsOk(), "基本的に成功");

        assertEquals(18, resultDto.getCountIncome(), "18件取得");

        List<IncomeAndOutcomeSearchLineDto> listIncome = resultDto.getListIncome();
        assertEquals(18, listIncome.size(), "各1項目ずつ取得(収入)");

        IncomeAndOutcomeSearchLineDto dto000 = listIncome.get(0);
        assertEquals(3, dto000.getYoushikiKbn(), "収入様式区分0");
        assertEquals(0, dto000.getYoushikiEdaKbn(), "収入枝区分項目0");

        IncomeAndOutcomeSearchLineDto dto001 = listIncome.get(1);
        assertEquals(4, dto001.getYoushikiKbn(), "収入様式区分1");
        assertEquals(0, dto001.getYoushikiEdaKbn(), "収入枝区分項目1");

        IncomeAndOutcomeSearchLineDto dto002 = listIncome.get(2);
        assertEquals(5, dto002.getYoushikiKbn(), "収入様式区分2");
        assertEquals(0, dto002.getYoushikiEdaKbn(), "収入枝区分項目2");

        IncomeAndOutcomeSearchLineDto dto003 = listIncome.get(3);
        assertEquals(6, dto003.getYoushikiKbn(), "収入様式区分3");
        assertEquals(0, dto003.getYoushikiEdaKbn(), "収入枝区分項目3");

        IncomeAndOutcomeSearchLineDto dto004 = listIncome.get(4);
        assertEquals(7, dto004.getYoushikiKbn(), "収入様式区分4");
        assertEquals(1, dto004.getYoushikiEdaKbn(), "収入枝区分項目4");

        IncomeAndOutcomeSearchLineDto dto005 = listIncome.get(5);
        assertEquals(7, dto005.getYoushikiKbn(), "収入様式区分5");
        assertEquals(2, dto005.getYoushikiEdaKbn(), "収入枝区分項目5");

        IncomeAndOutcomeSearchLineDto dto006 = listIncome.get(6);
        assertEquals(7, dto006.getYoushikiKbn(), "収入様式区分6");
        assertEquals(3, dto006.getYoushikiEdaKbn(), "収入枝区分項目6");

        IncomeAndOutcomeSearchLineDto dto007 = listIncome.get(7);
        assertEquals(8, dto007.getYoushikiKbn(), "収入様式区分7");
        assertEquals(1, dto007.getYoushikiEdaKbn(), "収入枝区分項目7");

        IncomeAndOutcomeSearchLineDto dto008 = listIncome.get(8);
        assertEquals(8, dto008.getYoushikiKbn(), "収入様式区分8");
        assertEquals(2, dto008.getYoushikiEdaKbn(), "収入枝区分項目8");

        IncomeAndOutcomeSearchLineDto dto009 = listIncome.get(9);
        assertEquals(8, dto009.getYoushikiKbn(), "収入様式区分9");
        assertEquals(3, dto009.getYoushikiEdaKbn(), "収入枝区分項目9");

        IncomeAndOutcomeSearchLineDto dto010 = listIncome.get(10);
        assertEquals(9, dto010.getYoushikiKbn(), "収入様式区分10");
        assertEquals(0, dto010.getYoushikiEdaKbn(), "収入枝区分項目10");

        IncomeAndOutcomeSearchLineDto dto011 = listIncome.get(11);
        assertEquals(10, dto011.getYoushikiKbn(), "収入様式区分11");
        assertEquals(0, dto011.getYoushikiEdaKbn(), "収入枝区分項目11");

        IncomeAndOutcomeSearchLineDto dto012 = listIncome.get(12);
        assertEquals(11, dto012.getYoushikiKbn(), "収入様式区分12");
        assertEquals(1, dto012.getYoushikiEdaKbn(), "収入枝区分項目12");

        IncomeAndOutcomeSearchLineDto dto013 = listIncome.get(13);
        assertEquals(11, dto013.getYoushikiKbn(), "収入様式区分13");
        assertEquals(2, dto013.getYoushikiEdaKbn(), "収入枝区分項目13");

        IncomeAndOutcomeSearchLineDto dto014 = listIncome.get(14);
        assertEquals(11, dto014.getYoushikiKbn(), "収入様式区分14");
        assertEquals(3, dto014.getYoushikiEdaKbn(), "収入枝区分項目14");

        IncomeAndOutcomeSearchLineDto dto015 = listIncome.get(15);
        assertEquals(12, dto015.getYoushikiKbn(), "収入様式区分15");
        assertEquals(1, dto015.getYoushikiEdaKbn(), "収入枝区分項目15");

        IncomeAndOutcomeSearchLineDto dto016 = listIncome.get(16);
        assertEquals(12, dto016.getYoushikiKbn(), "収入様式区分16");
        assertEquals(2, dto016.getYoushikiEdaKbn(), "収入枝区分項目16");

        IncomeAndOutcomeSearchLineDto dto017 = listIncome.get(17);
        assertEquals(12, dto017.getYoushikiKbn(), "収入様式区分17");
        assertEquals(3, dto017.getYoushikiEdaKbn(), "収入枝区分項目17");

        assertEquals(12, resultDto.getCountOutcome(), "12件取得");
        List<IncomeAndOutcomeSearchLineDto> listOutcome = resultDto.getListOutcome();

        IncomeAndOutcomeSearchLineDto dto101 = listOutcome.get(0);
        assertEquals(14, dto101.getYoushikiKbn(), "支出様式区分1");
        assertEquals(2, dto101.getYoushikiEdaKbn(), "支出枝区分項目1");

        IncomeAndOutcomeSearchLineDto dto102 = listOutcome.get(1);
        assertEquals(14, dto102.getYoushikiKbn(), "支出様式区分2");
        assertEquals(3, dto102.getYoushikiEdaKbn(), "支出枝区分項目2");

        IncomeAndOutcomeSearchLineDto dto103 = listOutcome.get(2);
        assertEquals(14, dto103.getYoushikiKbn(), "支出様式区分3");
        assertEquals(4, dto103.getYoushikiEdaKbn(), "支出枝区分項目3");

        IncomeAndOutcomeSearchLineDto dto104 = listOutcome.get(3);
        assertEquals(15, dto104.getYoushikiKbn(), "支出様式区分4");
        assertEquals(1, dto104.getYoushikiEdaKbn(), "支出枝区分項目4");

        IncomeAndOutcomeSearchLineDto dto105 = listOutcome.get(4);
        assertEquals(15, dto105.getYoushikiKbn(), "支出様式区分5");
        assertEquals(2, dto105.getYoushikiEdaKbn(), "支出枝区分項目5");

        IncomeAndOutcomeSearchLineDto dto106 = listOutcome.get(5);
        assertEquals(15, dto106.getYoushikiKbn(), "支出様式区分6");
        assertEquals(3, dto106.getYoushikiEdaKbn(), "支出枝区分項目6");

        IncomeAndOutcomeSearchLineDto dto107 = listOutcome.get(6);
        assertEquals(15, dto107.getYoushikiKbn(), "支出様式区分7");
        assertEquals(4, dto107.getYoushikiEdaKbn(), "支出枝区分項目7");

        IncomeAndOutcomeSearchLineDto dto108 = listOutcome.get(7);
        assertEquals(15, dto108.getYoushikiKbn(), "支出様式区分8");
        assertEquals(5, dto108.getYoushikiEdaKbn(), "支出枝区分項目8");

        IncomeAndOutcomeSearchLineDto dto109 = listOutcome.get(8);
        assertEquals(15, dto109.getYoushikiKbn(), "支出様式区分9");
        assertEquals(6, dto109.getYoushikiEdaKbn(), "支出枝区分項目9");

        IncomeAndOutcomeSearchLineDto dto110 = listOutcome.get(9);
        assertEquals(15, dto110.getYoushikiKbn(), "支出様式区分10");
        assertEquals(7, dto110.getYoushikiEdaKbn(), "支出枝区分項目10");

        IncomeAndOutcomeSearchLineDto dto111 = listOutcome.get(10);
        assertEquals(15, dto111.getYoushikiKbn(), "支出様式区分11");
        assertEquals(8, dto111.getYoushikiEdaKbn(), "支出枝区分項目11");

        IncomeAndOutcomeSearchLineDto dto112 = listOutcome.get(11);
        assertEquals(15, dto112.getYoushikiKbn(), "支出様式区分12");
        assertEquals(9, dto112.getYoushikiEdaKbn(), "支出枝区分項目12");

    }

}
