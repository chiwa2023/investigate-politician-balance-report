package mitei.mitei.investigate.report.balance.politician.service;


import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;

/**
 * SearchIncomeAndOutcomeBySearchWordsService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
//@Sql("truncate_income_outcome_2022.sql")
class SearchIncomeAndOutcomeBySearchWordsServiceTest {
    // CHECKSTYLE:OFF
    
    /** テスト対象 */
    @Autowired
    private SearchIncomeAndOutcomeBySearchWordsService searchIncomeAndOutcomeBySearchWordsService;
    
    @Test
    //@Sql({"offering_balancesheet_income_horie_2022.sql","offering_balancesheet_outcome_horie_2022.sql","offering_balancesheet_income_joshi_2022.sql","offering_balancesheet_outcome_joshi_2022.sql"})
    void testPractice() {
        
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords("利息");
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2022, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2022, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService.practice(searchConditionDto);

        System.out.println("=======検索");
        System.out.println(searchConditionDto.getSearchWords());

        System.out.println("=======収入");
        System.out.println("------件数"+searchResultDto.getCountIncome());
        for (IncomeAndOutcomeSearchLineDto dto : searchResultDto.getListIncome()) {
            System.out.println(dto.getAccrualDateValue() + "--" + dto.getItemName() + "--" + dto.getKingaku() + "--" + dto.getPartnerName());
        }

        System.out.println("=======支出");
        System.out.println("------件数"+searchResultDto.getCountOutcome());
        for (IncomeAndOutcomeSearchLineDto dto : searchResultDto.getListOutcome()) {
            System.out.println(dto.getAccrualDateValue() + "--" + dto.getMokuteki() + "--" + dto.getKingaku() + "--" + dto.getPartnerName());
        }
        
        fail("Not yet implemented");
    }

}
