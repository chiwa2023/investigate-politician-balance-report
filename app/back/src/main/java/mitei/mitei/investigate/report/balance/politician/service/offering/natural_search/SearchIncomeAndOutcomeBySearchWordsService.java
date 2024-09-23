package mitei.mitei.investigate.report.balance.politician.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.natural_search.CreateSearchCondeitionPeriodMapLogic;
import mitei.mitei.investigate.report.balance.politician.logic.natural_search.CreateSerachWordsBooleanModeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.natural_search.y2022.NaturalAllSentenceSearchY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.natural_search.y2024.NaturalAllSentenceSearchY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.natural_search.y2025.NaturalAllSentenceSearchY2025Logic;

/**
 * 自然検索による項目抽出
 */
@Service
public class SearchIncomeAndOutcomeBySearchWordsService {

    /** BooleanMode全文検索用検索語生成Logic */
    @Autowired
    private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /** 年またぎ検索用検索条件Map生成Logic */
    @Autowired
    private CreateSearchCondeitionPeriodMapLogic createSearchCondeitionPeriodMapLogic;

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 検索実行Logic(2022年) */
    @Autowired
    private NaturalAllSentenceSearchY2022Logic naturalAllSentenceSearchY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 検索実行Logic(2024年) */
    @Autowired
    private NaturalAllSentenceSearchY2024Logic naturalAllSentenceSearchY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 検索実行Logic(2025年) */
    @Autowired
    private NaturalAllSentenceSearchY2025Logic naturalAllSentenceSearchY2025Logic;

    /**
     * 検索を行う
     *
     * @param searchConditionDto 検索条件Dto
     */
    public IncomeAndOutcomeNaturalSearchResultDto practice(
            final IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto) {

        // 先にシステム用検索語を設定
        searchConditionDto
                .setSearchWords(createSerachWordsBooleanModeLogic.practice(searchConditionDto.getUserKeyWords()));

        Map<Integer, IncomeAndOutcomeNaturalSearchConditionCapsuleDto> map = createSearchCondeitionPeriodMapLogic
                .practice(searchConditionDto);

        IncomeAndOutcomeNaturalSearchResultDto dtoTemp;
        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = new IncomeAndOutcomeNaturalSearchResultDto();

        for (Integer houkokuNen : map.keySet()) {
            switch (houkokuNen) {
                // 2022年
                case YEAR_2022:
                    dtoTemp = naturalAllSentenceSearchY2022Logic.practice(map.get(houkokuNen));
                    this.plusResult(searchResultDto, dtoTemp);
                    break;

                // 2024年
                case YEAR_2024:
                    dtoTemp = naturalAllSentenceSearchY2024Logic.practice(map.get(houkokuNen));
                    this.plusResult(searchResultDto, dtoTemp);
                    break;

                // 2022年
                case YEAR_2025:
                    dtoTemp = naturalAllSentenceSearchY2025Logic.practice(map.get(houkokuNen));
                    this.plusResult(searchResultDto, dtoTemp);
                    break;

                default:
                    break;
            }

        }

        return searchResultDto;
    }

    /**
     * 最終的に送信する結果Dtoに年ごとの取得データを詰める
     *
     * @param searchResultDto 最終結果Dto
     * @param dtoTemp 年ごとの結果Dto
     */
    private void plusResult(final IncomeAndOutcomeNaturalSearchResultDto searchResultDto,
            final IncomeAndOutcomeNaturalSearchResultDto dtoTemp) {
        searchResultDto.getListIncome().addAll(dtoTemp.getListIncome());
        searchResultDto.getListOutcome().addAll(dtoTemp.getListOutcome());
        searchResultDto.setCountIncome(searchResultDto.getCountIncome() + dtoTemp.getCountIncome());
        searchResultDto.setCountOutcome(searchResultDto.getCountOutcome() + dtoTemp.getCountOutcome());
    }
}
