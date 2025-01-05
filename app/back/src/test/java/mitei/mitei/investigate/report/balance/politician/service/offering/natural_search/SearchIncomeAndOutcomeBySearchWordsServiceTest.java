package mitei.mitei.investigate.report.balance.politician.service.offering.natural_search;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;
// 2022年
// importを追加

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

    // テストタグ自然検索
    private static final String TAG_SEARCH = "NaturalTextSearch"; // NOPMD

    // 年処理テスト用テキスト
    private static final String incomeText = "収入件数"; // NOPMD
    private static final String outcomeText = "支出件数"; // NOPMD
    private static final String isOkText = "出力結果"; // NOPMD
    private static final String isSumText = "合計件数"; // NOPMD

    
    @Test
    // SQLは全文検索のためテスト直前に書き込みしても動かない
    // @Sql({"truncate_income_outcome_2022.sql","offering_balancesheet_income_horie_2022.sql","offering_balancesheet_outcome_horie_2022.sql","offering_balancesheet_income_joshi_2022.sql","offering_balancesheet_outcome_joshi_2022.sql"})
    @Tag(TAG_SEARCH)
    void testPractice() { // NOPMD

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords("七日市");// NOPMD
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2022, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2022, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService
                .practice(searchConditionDto);

        // 検索語が独自規則でフォーマットされていること
        assertEquals("+七日市", searchResultDto.getSearchWords(), ""); // NOPMD

        // 件数など
        assertEquals(1, searchResultDto.getCountIncome(), "");
        assertEquals(4, searchResultDto.getCountOutcome(), "");
        assertEquals(true, searchResultDto.getIsOk(), "");
        assertEquals(5, searchResultDto.getSuccessCount(), "");

        // 収入
        List<IncomeAndOutcomeSearchLineDto> listIncome = searchResultDto.getListIncome();
        IncomeAndOutcomeSearchLineDto dtoIncome1 = listIncome.get(0);

        assertEquals("R4/10/17", dtoIncome1.getAccrualDate(), "");
        assertEquals(LocalDate.of(2022, 10, 17), dtoIncome1.getAccrualDateValue(), "");
        assertEquals("大津綾香", dtoIncome1.getDaihyouName(), "");
        assertEquals("政治家女子４８党", dtoIncome1.getDantaiName(), "");
        assertEquals("寄付", dtoIncome1.getItemName(), "");
        assertEquals(1_200_000L, dtoIncome1.getKingaku(), "");
        assertEquals("", dtoIncome1.getMokuteki(), "");
        assertEquals("大桃聴", dtoIncome1.getPartnerName(), "");
        assertEquals("新潟県魚沼市七日市", dtoIncome1.getPartnerJuusho(), "");
        assertEquals(7, dtoIncome1.getYoushikiKbn(), "");
        assertEquals(1, dtoIncome1.getYoushikiEdaKbn(), "");
        assertEquals("1,200,000", dtoIncome1.getKingakuIncomeText(), "");
        assertEquals("", dtoIncome1.getKingakuOutcomeText(), "");
        assertEquals(1_200_000L, dtoIncome1.getKingakuShuukei(), "");

        // 支出
        List<IncomeAndOutcomeSearchLineDto> listOutcome = searchResultDto.getListOutcome();
        // 一行目
        IncomeAndOutcomeSearchLineDto dtoOutcome1 = listOutcome.get(0);
        assertEquals("R4/4/20", dtoOutcome1.getAccrualDate(), "");
        assertEquals(LocalDate.of(2022, 4, 20), dtoOutcome1.getAccrualDateValue(), "");
        assertEquals("大津綾香", dtoOutcome1.getDaihyouName(), "");
        assertEquals("政治家女子４８党", dtoOutcome1.getDantaiName(), "");
        assertEquals("(寄付金)寄付金", dtoOutcome1.getItemName(), "");
        assertEquals(300_000L, dtoOutcome1.getKingaku(), "");
        assertEquals("寄付金", dtoOutcome1.getMokuteki(), "");
        assertEquals("NHKから国民を守る党", dtoOutcome1.getPartnerName(), "");
        assertEquals("新潟県魚沼市七日市", dtoOutcome1.getPartnerJuusho(), "");
        assertEquals(15, dtoOutcome1.getYoushikiKbn(), "");
        assertEquals(8, dtoOutcome1.getYoushikiEdaKbn(), "");
        assertEquals("", dtoOutcome1.getKingakuIncomeText(), "");
        assertEquals("300,000", dtoOutcome1.getKingakuOutcomeText(), "");
        assertEquals(-300_000L, dtoOutcome1.getKingakuShuukei(), "");

        // サンプルデータの特性として日付と金額が変わるだけ
        IncomeAndOutcomeSearchLineDto dtoOutcome2 = listOutcome.get(1);
        assertEquals("R4/7/20", dtoOutcome2.getAccrualDate(), "");
        assertEquals(LocalDate.of(2022, 7, 20), dtoOutcome2.getAccrualDateValue(), "");
        assertEquals(300_000L, dtoOutcome2.getKingaku(), "");
        assertEquals("", dtoOutcome2.getKingakuIncomeText(), "");
        assertEquals("300,000", dtoOutcome2.getKingakuOutcomeText(), "");
        assertEquals(-300_000L, dtoOutcome2.getKingakuShuukei(), "");

        IncomeAndOutcomeSearchLineDto dtoOutcome3 = listOutcome.get(2);
        assertEquals("R4/10/20", dtoOutcome3.getAccrualDate(), "");
        assertEquals(LocalDate.of(2022, 10, 20), dtoOutcome3.getAccrualDateValue(), "");
        assertEquals(1_500_000L, dtoOutcome3.getKingaku(), "");
        assertEquals("", dtoOutcome3.getKingakuIncomeText(), "");
        assertEquals("1,500,000", dtoOutcome3.getKingakuOutcomeText(), "");
        assertEquals(-1_500_000L, dtoOutcome3.getKingakuShuukei(), "");

        IncomeAndOutcomeSearchLineDto dtoOutcome4 = listOutcome.get(3);
        assertEquals("R4/10/20", dtoOutcome4.getAccrualDate(), "");
        assertEquals(LocalDate.of(2022, 10, 20), dtoOutcome4.getAccrualDateValue(), "");
        assertEquals(300_000L, dtoOutcome4.getKingaku(), "");
        assertEquals("", dtoOutcome4.getKingakuIncomeText(), "");
        assertEquals("300,000", dtoOutcome4.getKingakuOutcomeText(), "");
        assertEquals(-300_000L, dtoOutcome4.getKingakuShuukei(), "");

    }

    // テンプレート開始位置
    @Test
    @Tag(TAG_SEARCH)
    // @Sql({"truncate_income_outcome_2022.sql","offering_balancesheet_income_horie_2022.sql","offering_balancesheet_outcome_horie_2022.sql","offering_balancesheet_income_joshi_2022.sql","offering_balancesheet_outcome_joshi_2022.sql"})
    void testPractice2022() {
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
        assertEquals("+七日市", searchResultDto.getSearchWords(), "");
        
        // 件数など
        assertEquals(1, searchResultDto.getCountIncome(), incomeText);
        assertEquals(4, searchResultDto.getCountOutcome(), outcomeText);
        assertEquals(true, searchResultDto.getIsOk(), isOkText);
        assertEquals(5, searchResultDto.getSuccessCount(), isSumText);
    }
    // テンプレート終了位置

    @Test
    @Tag(TAG_SEARCH)
    // @Sql({"truncate_income_outcome_2024.sql","offering_balancesheet_income_horie_2024.sql","offering_balancesheet_outcome_horie_2024.sql","offering_balancesheet_income_joshi_2024.sql","offering_balancesheet_outcome_joshi_2024.sql"})
    void testPractice2024() {
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords("七日市");
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2024, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2024, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService
                .practice(searchConditionDto);

        // 検索語が独自規則でフォーマットされていること
        assertEquals("+七日市", searchResultDto.getSearchWords(), "");

        
        // 件数など
        assertEquals(1, searchResultDto.getCountIncome(), incomeText);
        assertEquals(4, searchResultDto.getCountOutcome(), outcomeText);
        assertEquals(true, searchResultDto.getIsOk(), isOkText);
        assertEquals(5, searchResultDto.getSuccessCount(), isSumText);
    }

    @Test
    @Tag(TAG_SEARCH)
    // @Sql({"truncate_income_outcome_2025.sql","offering_balancesheet_income_horie_2025.sql","offering_balancesheet_outcome_horie_2025.sql","offering_balancesheet_income_joshi_2025.sql","offering_balancesheet_outcome_joshi_2025.sql"})
    void testPractice2025() {
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords("七日市");
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2025, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2025, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService
                .practice(searchConditionDto);

        // 検索語が独自規則でフォーマットされていること
        assertEquals("+七日市", searchResultDto.getSearchWords(), "");
        
        // 件数など
        assertEquals(1, searchResultDto.getCountIncome(), incomeText);
        assertEquals(4, searchResultDto.getCountOutcome(), outcomeText);
        assertEquals(true, searchResultDto.getIsOk(), isOkText);
        assertEquals(5, searchResultDto.getSuccessCount(), isSumText);
    }

    @Test
    @Tag(TAG_SEARCH)
    // @Sql({"truncate_income_outcome_2023.sql","offering_balancesheet_income_horie_2023.sql","offering_balancesheet_outcome_horie_2023.sql","offering_balancesheet_income_joshi_2023.sql","offering_balancesheet_outcome_joshi_2023.sql"})
    void testPractice2023() {
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        searchConditionDto.setUserKeyWords("七日市");
        searchConditionDto.setIsSearchIncome(true);
        searchConditionDto.setIsSearchOutcome(true);
        searchConditionDto.setOffsetIncome(0);
        searchConditionDto.setOffsetOutcome(0);
        searchConditionDto.setStartDate(LocalDate.of(2023, 01, 01));
        searchConditionDto.setEndDate(LocalDate.of(2023, 12, 31));

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = searchIncomeAndOutcomeBySearchWordsService
                .practice(searchConditionDto);

        // 検索語が独自規則でフォーマットされていること
        assertEquals("+七日市", searchResultDto.getSearchWords(), "");
        
        // 件数など
        assertEquals(1, searchResultDto.getCountIncome(), incomeText);
        assertEquals(4, searchResultDto.getCountOutcome(), outcomeText);
        assertEquals(true, searchResultDto.getIsOk(), isOkText);
        assertEquals(5, searchResultDto.getSuccessCount(), isSumText);
    }

// 追加位置    

}
