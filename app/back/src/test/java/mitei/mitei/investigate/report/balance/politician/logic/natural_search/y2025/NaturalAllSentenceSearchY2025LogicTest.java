package mitei.mitei.investigate.report.balance.politician.logic.natural_search.y2025;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;

/**
 * NaturalAllSentenceSearchY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class NaturalAllSentenceSearchY2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private NaturalAllSentenceSearchY2025Logic naturalAllSentenceSearchY2025Logic;

    @Test
    // @Transactional
    @Tag("NaturalTextSearch")
    @Sql("natural_search_test_2025.sql")
    void testPractice() {

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setSearchWords("+テストデータ検索2025"); // 検索語フォーマットはこのLogicの前段階で編集しているので、検索語そのものを入れる
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2025, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2025, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = naturalAllSentenceSearchY2025Logic
                .practice(searchConditionDto);

        // 件数など
        assertThat(searchResultDto.getCountIncome()).isEqualTo(1);
        assertThat(searchResultDto.getCountOutcome()).isEqualTo(1);
        // assertThat(searchResultDto.getIsOk()).isEqualTo(true); 集計用はの値まだセットされていない
        // assertThat(searchResultDto.getSuccessCount()).isEqualTo(5); 集計用の値はまだセットされていない

        // 収入
        List<IncomeAndOutcomeSearchLineDto> listIncome = searchResultDto.getListIncome();
        IncomeAndOutcomeSearchLineDto dtoIncome1 = listIncome.get(0);

        assertThat(dtoIncome1.getItemId()).isEqualTo("2025-883-7-1-12");
        assertThat(dtoIncome1.getAccrualDate()).isEqualTo("R4/10/17");
        assertThat(dtoIncome1.getAccrualDateValue()).isEqualTo(LocalDate.of(2025, 10, 17));
        assertThat(dtoIncome1.getDaihyouName()).isEqualTo("大津綾香");
        assertThat(dtoIncome1.getDantaiName()).isEqualTo("政治家女子４８党");
        assertThat(dtoIncome1.getItemName()).isEqualTo("寄付");
        assertThat(dtoIncome1.getKingaku()).isEqualTo(1_200_000L);
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

        IncomeAndOutcomeSearchLineDto dtoOutcome1 = listOutcome.get(0);

        assertThat(dtoOutcome1.getItemId()).isEqualTo("2025-883-15-8-11");
        assertThat(dtoOutcome1.getAccrualDate()).isEqualTo("R4/4/20");
        assertThat(dtoOutcome1.getAccrualDateValue()).isEqualTo(LocalDate.of(2025, 4, 20));
        assertThat(dtoOutcome1.getDaihyouName()).isEqualTo("大津綾香");
        assertThat(dtoOutcome1.getDantaiName()).isEqualTo("政治家女子４８党");
        assertThat(dtoOutcome1.getItemName()).isEqualTo("(寄付金)寄付金");
        assertThat(dtoOutcome1.getKingaku()).isEqualTo(300_000L);
        assertThat(dtoOutcome1.getMokuteki()).isEqualTo("寄付金");
        assertThat(dtoOutcome1.getPartnerName()).isEqualTo("NHKから国民を守る党");
        assertThat(dtoOutcome1.getPartnerJuusho()).isEqualTo("新潟県魚沼市七日市");
        assertThat(dtoOutcome1.getYoushikiKbn()).isEqualTo(15);
        assertThat(dtoOutcome1.getYoushikiEdaKbn()).isEqualTo(8);
        assertThat(dtoOutcome1.getKingakuIncomeText()).isEqualTo("");
        assertThat(dtoOutcome1.getKingakuOutcomeText()).isEqualTo("300,000");
        assertThat(dtoOutcome1.getKingakuShuukei()).isEqualTo(-300_000L);

    }

}
