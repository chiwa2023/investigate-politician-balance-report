package mitei.mitei.investigate.report.balance.politician.service.offering.natural_search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.transaction.Transactional;
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
//@Transactional
class SearchIncomeAndOutcomeBySearchWordsServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchIncomeAndOutcomeBySearchWordsService searchIncomeAndOutcomeBySearchWordsService;

    @Test
    @Transactional
    //@Sql({"offering_balancesheet_income_horie_2022.sql","offering_balancesheet_outcome_horie_2022.sql","offering_balancesheet_income_joshi_2022.sql","offering_balancesheet_outcome_joshi_2022.sql"})
    //@Sql({"truncate_income_outcome_2022.sql"})
    void testPractice() { // NOPMD

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords("七日市");
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2022, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2022, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService
                .practice(searchConditionDto);

        // 検索語が独自規則でフォーマットされていること
        assertThat(searchResultDto.getSearchWords()).isEqualTo("+七日市");

        // 件数など
        assertThat(searchResultDto.getCountIncome()).isEqualTo(1);
        assertThat(searchResultDto.getCountOutcome()).isEqualTo(4);
        assertThat(searchResultDto.getIsOk()).isEqualTo(true);
        assertThat(searchResultDto.getSuccessCount()).isEqualTo(5);

        // 収入
        List<IncomeAndOutcomeSearchLineDto> listIncome = searchResultDto.getListIncome();
        IncomeAndOutcomeSearchLineDto dtoIncome1 = listIncome.get(0);

        assertThat(dtoIncome1.getAccrualDate()).isEqualTo("R4/10/17");
        assertThat(dtoIncome1.getAccrualDateValue()).isEqualTo(LocalDate.of(2022, 10, 17));
        assertThat(dtoIncome1.getDaihyouName()).isEqualTo("大津綾香");
        assertThat(dtoIncome1.getDantaiName()).isEqualTo("政治家女子４８党");
        assertThat(dtoIncome1.getItemName()).isEqualTo("寄付");
        assertThat(dtoIncome1.getKingaku()).isEqualTo(1200000L); // NOPMD
        assertThat(dtoIncome1.getMokuteki()).isEqualTo("");
        assertThat(dtoIncome1.getPartnerName()).isEqualTo("大桃聴");
        assertThat(dtoIncome1.getPartnerJuusho()).isEqualTo("新潟県魚沼市七日市");
        assertThat(dtoIncome1.getYoushikiKbn()).isEqualTo(7);
        assertThat(dtoIncome1.getYoushikiEdaKbn()).isEqualTo(1);
        assertThat(dtoIncome1.getKingakuIncomeText()).isEqualTo("1,200,000");
        assertThat(dtoIncome1.getKingakuOutcomeText()).isEqualTo("");
        assertThat(dtoIncome1.getKingakuShuukei()).isEqualTo(1_200_000L);

        // 支出
        List<IncomeAndOutcomeSearchLineDto> listOutcome = searchResultDto.getListOutcome();
        // 一行目
        IncomeAndOutcomeSearchLineDto dtoOutcome1 = listOutcome.get(0);
        assertThat(dtoOutcome1.getAccrualDate()).isEqualTo("R4/4/20");
        assertThat(dtoOutcome1.getAccrualDateValue()).isEqualTo(LocalDate.of(2022, 4, 20));
        assertThat(dtoOutcome1.getDaihyouName()).isEqualTo("大津綾香");
        assertThat(dtoOutcome1.getDantaiName()).isEqualTo("政治家女子４８党");
        assertThat(dtoOutcome1.getItemName()).isEqualTo("(寄付金)寄付金");
        assertThat(dtoOutcome1.getKingaku()).isEqualTo(300_000L); // NOPMD
        assertThat(dtoOutcome1.getMokuteki()).isEqualTo("寄付金");
        assertThat(dtoOutcome1.getPartnerName()).isEqualTo("NHKから国民を守る党");
        assertThat(dtoOutcome1.getPartnerJuusho()).isEqualTo("新潟県魚沼市七日市");
        assertThat(dtoOutcome1.getYoushikiKbn()).isEqualTo(15);
        assertThat(dtoOutcome1.getYoushikiEdaKbn()).isEqualTo(8);
        assertThat(dtoOutcome1.getKingakuIncomeText()).isEqualTo("");
        assertThat(dtoOutcome1.getKingakuOutcomeText()).isEqualTo("300,000");
        assertThat(dtoOutcome1.getKingakuShuukei()).isEqualTo(-300_000L);

        // サンプルデータの特性として日付と金額が変わるだけ
        IncomeAndOutcomeSearchLineDto dtoOutcome2 = listOutcome.get(1);
        assertThat(dtoOutcome2.getAccrualDate()).isEqualTo("R4/7/20");
        assertThat(dtoOutcome2.getAccrualDateValue()).isEqualTo(LocalDate.of(2022, 7, 20));
        assertThat(dtoOutcome2.getKingaku()).isEqualTo(300000L); // NOPMD
        assertThat(dtoOutcome2.getKingakuIncomeText()).isEqualTo("");
        assertThat(dtoOutcome2.getKingakuOutcomeText()).isEqualTo("300,000");
        assertThat(dtoOutcome2.getKingakuShuukei()).isEqualTo(-300_000L);

        IncomeAndOutcomeSearchLineDto dtoOutcome3 = listOutcome.get(2);
        assertThat(dtoOutcome3.getAccrualDate()).isEqualTo("R4/10/20");
        assertThat(dtoOutcome3.getAccrualDateValue()).isEqualTo(LocalDate.of(2022, 10, 20));
        assertThat(dtoOutcome3.getKingaku()).isEqualTo(1500000L); // NOPMD
        assertThat(dtoOutcome3.getKingakuIncomeText()).isEqualTo("");
        assertThat(dtoOutcome3.getKingakuOutcomeText()).isEqualTo("1,500,000");
        assertThat(dtoOutcome3.getKingakuShuukei()).isEqualTo(-1_500_000L);

        IncomeAndOutcomeSearchLineDto dtoOutcome4 = listOutcome.get(3);
        assertThat(dtoOutcome4.getAccrualDate()).isEqualTo("R4/10/20");
        assertThat(dtoOutcome4.getAccrualDateValue()).isEqualTo(LocalDate.of(2022, 10, 20));
        assertThat(dtoOutcome4.getKingaku()).isEqualTo(300000L); // NOPMD
        assertThat(dtoOutcome4.getKingakuIncomeText()).isEqualTo("");
        assertThat(dtoOutcome4.getKingakuOutcomeText()).isEqualTo("300,000");
        assertThat(dtoOutcome4.getKingakuShuukei()).isEqualTo(-300_000L);

        
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto01 = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto01.setUserKeyWords("あああああああ");
        searchConditionDto01.setIsSearchIncome(true);
        searchConditionDto01.setIsSearchOutcome(true);
        searchConditionDto01.setOffsetIncome(0);
        searchConditionDto01.setOffsetOutcome(0);
        searchConditionDto01.setStartDate(LocalDate.of(2022, 01, 01));
        searchConditionDto01.setEndDate(LocalDate.of(2022, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto01 = searchIncomeAndOutcomeBySearchWordsService
                .practice(searchConditionDto01);

        // 検索0件
        assertTrue(searchResultDto01.getIsOk(),"0件でも検索は正常");
        assertThat(searchResultDto01.getCountIncome()).isEqualTo(0);
        assertThat(searchResultDto01.getCountOutcome()).isEqualTo(0);
        assertThat(searchResultDto01.getSuccessCount()).isEqualTo(0);
        
    }

}
