package mitei.mitei.investigate.report.balance.politician.logic.natural_search;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;

/**
 * 年をまたいで検索を可能にする検索条件Mapを作成する
 */
@Component
public class CreateSearchCondeitionPeriodMapLogic {

    /**
     * 検索条件Mapを作成する
     *
     * @param searchConditionDto 検索条件
     * @return 年またぎ検索用のMap
     */
    public Map<Integer, IncomeAndOutcomeNaturalSearchConditionCapsuleDto> practice(
            final IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto) {

        Map<Integer, IncomeAndOutcomeNaturalSearchConditionCapsuleDto> map = new HashMap<>();

        // frontで1年またぎしか許可しない予定であるため、その中で前後関係も自然とチェックすることになるが
        // 複数年許可でチェックなしの場合を想定して前後関係は正しく整える
        if (searchConditionDto.getStartDate().isAfter(searchConditionDto.getEndDate())) {
            LocalDate end = searchConditionDto.getEndDate();
            LocalDate start = searchConditionDto.getStartDate();
            LocalDate tempStart = LocalDate.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
            LocalDate tempEnd = LocalDate.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth());
            searchConditionDto.setStartDate(tempEnd);
            searchConditionDto.setEndDate(tempStart);
        }

        // 開始日付の年から終了日付の年までLoop
        for (int year = searchConditionDto.getStartDate().getYear(); year <= searchConditionDto.getEndDate()
                .getYear(); year++) {
            map.put(year, this.createDto(year, searchConditionDto));
        }

        return map;
    }

    private IncomeAndOutcomeNaturalSearchConditionCapsuleDto createDto(final int year,
            final IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto) {

        IncomeAndOutcomeNaturalSearchConditionCapsuleDto dtoNew = new IncomeAndOutcomeNaturalSearchConditionCapsuleDto();
        BeanUtils.copyProperties(searchConditionDto, dtoNew);

        // 開始日付の年と処理年が一致している場合、開始日付はユーザさん指定を使用、それ以外は年初
        if (searchConditionDto.getStartDate().getYear() == year) {
            dtoNew.setStartDate(searchConditionDto.getStartDate());
        } else {
            dtoNew.setStartDate(LocalDate.of(year, 1, 1));
        }

        // 終了日付の年と処理年が一致している場合、終了日付はユーザさん指定を使用、それ以外は年末
        if (searchConditionDto.getEndDate().getYear() == year) {
            dtoNew.setEndDate(searchConditionDto.getEndDate());
        } else {
            dtoNew.setEndDate(LocalDate.of(year, 12, 31)); // SUPPRESS CHECKSTYLE MagicNumber
        }

        return dtoNew;
    }
}
