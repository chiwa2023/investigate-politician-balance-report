package mitei.mitei.investigate.report.balance.politician.logic.natural_search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;

/**
 * CreateSearchCondeitionPeriodMapLogic単体テスト
 */
class CreateSearchCondeitionPeriodMapLogicTest {
    // CHECKSTYLE:OFF

    @Test
    void testPractice() { // NOPMD

        CreateSearchCondeitionPeriodMapLogic createSearchCondeitionPeriodMapLogic = new CreateSearchCondeitionPeriodMapLogic();

        final String assertMessage = "指定された年がmapのkeyになっている";

        // 年またぎのない場合
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto01 = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        LocalDate start01 = LocalDate.of(2022, 1, 1);
        LocalDate end01 = LocalDate.of(2022, 12, 31);
        dto01.setStartDate(start01);
        dto01.setEndDate(end01);
        Map<Integer, IncomeAndOutcomeNaturalSearchConditionCapsuleDto> map01 = createSearchCondeitionPeriodMapLogic
                .practice(dto01);
        assertThat(map01.size()).isEqualTo(1); // 1件
        assertNotNull(map01.get(2022), assertMessage);
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto011 = map01.get(2022);
        assertThat(dto011.getStartDate()).isEqualTo(start01);
        assertThat(dto011.getEndDate()).isEqualTo(end01);

        // (年またぎがないが)前後関係が誤りの場合は補正
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto02 = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        LocalDate end02 = LocalDate.of(2022, 1, 2);
        LocalDate start02 = LocalDate.of(2022, 12, 30);
        dto02.setStartDate(start02);
        dto02.setEndDate(end02);
        Map<Integer, IncomeAndOutcomeNaturalSearchConditionCapsuleDto> map02 = createSearchCondeitionPeriodMapLogic
                .practice(dto02);
        assertThat(map02.size()).isEqualTo(1); // 1件
        assertNotNull(map02.get(2022), assertMessage);
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto021 = map02.get(2022);
        assertThat(dto021.getStartDate()).isEqualTo(end02);
        assertThat(dto021.getEndDate()).isEqualTo(start02);

        // 年またぎの場合
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto03 = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        LocalDate start03 = LocalDate.of(2020, 1, 3);
        LocalDate end03 = LocalDate.of(2027, 12, 29);
        dto03.setStartDate(start03);
        dto03.setEndDate(end03);
        Map<Integer, IncomeAndOutcomeNaturalSearchConditionCapsuleDto> map03 = createSearchCondeitionPeriodMapLogic
                .practice(dto03);
        assertThat(map03.size()).isEqualTo(8); // 8件
        assertNotNull(map03.get(2020), assertMessage);
        assertNotNull(map03.get(2021), assertMessage);
        assertNotNull(map03.get(2022), assertMessage);
        assertNotNull(map03.get(2023), assertMessage);
        assertNotNull(map03.get(2024), assertMessage);
        assertNotNull(map03.get(2025), assertMessage);
        assertNotNull(map03.get(2026), assertMessage);
        assertNotNull(map03.get(2027), assertMessage);

        // 実際に入っている値のチェック
        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto031 = map03.get(2020);
        assertThat(dto031.getStartDate()).isEqualTo(start03);
        assertThat(dto031.getEndDate()).isEqualTo(LocalDate.of(2020, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto032 = map03.get(2021);
        assertThat(dto032.getStartDate()).isEqualTo(LocalDate.of(2021, 1, 1));
        assertThat(dto032.getEndDate()).isEqualTo(LocalDate.of(2021, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto033 = map03.get(2022);
        assertThat(dto033.getStartDate()).isEqualTo(LocalDate.of(2022, 1, 1));
        assertThat(dto033.getEndDate()).isEqualTo(LocalDate.of(2022, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto034 = map03.get(2023);
        assertThat(dto034.getStartDate()).isEqualTo(LocalDate.of(2023, 1, 1));
        assertThat(dto034.getEndDate()).isEqualTo(LocalDate.of(2023, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto035 = map03.get(2024);
        assertThat(dto035.getStartDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(dto035.getEndDate()).isEqualTo(LocalDate.of(2024, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto036 = map03.get(2025);
        assertThat(dto036.getStartDate()).isEqualTo(LocalDate.of(2025, 1, 1));
        assertThat(dto036.getEndDate()).isEqualTo(LocalDate.of(2025, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto037 = map03.get(2026);
        assertThat(dto037.getStartDate()).isEqualTo(LocalDate.of(2026, 1, 1));
        assertThat(dto037.getEndDate()).isEqualTo(LocalDate.of(2026, 12, 31));

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dto038 = map03.get(2027);
        assertThat(dto038.getStartDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(dto038.getEndDate()).isEqualTo(end03);

    }

}
