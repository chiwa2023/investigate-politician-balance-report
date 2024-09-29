package mitei.mitei.investigate.report.balance.politician.service.offering.natural_search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;

/**
 * SearchIncomeAndOutcomeBySearchWordsService単体テスト2
 * 年またぎが検索できる簡易検索閲覧用としてConsole出力を残しておく
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//@Transactional
class SearchIncomeAndOutcomeBySearchWordsServiceTest2 {
    // CHECKSTYLE:OFF
    
    /** テスト対象 */
    @Autowired
    private SearchIncomeAndOutcomeBySearchWordsService searchIncomeAndOutcomeBySearchWordsService;
    
    @Test
    //@Sql({"truncate_income_outcome_2022.sql","offering_balancesheet_income_horie_2022.sql","offering_balancesheet_outcome_horie_2022.sql","offering_balancesheet_income_joshi_2022.sql","offering_balancesheet_outcome_joshi_2022.sql"})
    void testPractice() {
        
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords(">利息 >東京");
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2022, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2022, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService.practice(searchConditionDto);
        
        System.out.println("=======検索"); // NOPMD
        System.out.println(searchResultDto.getSearchWords()); // NOPMD

        System.out.println("=======収入"); // NOPMD
        System.out.println("------件数"+searchResultDto.getCountIncome()); // NOPMD
        for (IncomeAndOutcomeSearchLineDto dto : searchResultDto.getListIncome()) {
            System.out.println(dto.getAccrualDateValue() + "--" + dto.getItemName() + "--" + dto.getKingaku() + "--" + dto.getPartnerName()); // NOPMD
        }

        System.out.println("=======支出"); // NOPMD
        System.out.println("------件数"+searchResultDto.getCountOutcome()); // NOPMD
        for (IncomeAndOutcomeSearchLineDto dto : searchResultDto.getListOutcome()) {
            System.out.println(dto.getAccrualDateValue() + "--" + dto.getMokuteki() + "--" + dto.getKingaku() + "--" + dto.getPartnerName()); // NOPMD
        }
        
        assertTrue(searchResultDto.getIsOk(),"正常検索されていること");
    }

}
