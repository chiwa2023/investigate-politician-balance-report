package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.ShowBakancesheetReportByKanrenshaY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.ShowBakancesheetReportByKanrenshaY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.ShowBakancesheetReportByKanrenshaY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.ShowBakancesheetReportByKanrenshaY2025Logic;

/**
 * 関連者から収入・支出データを取得する
 */
@Component
public class ShowBakancesheetReportByKanrenshaLogic {

    // フィールドテンプレート始まり

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private ShowBakancesheetReportByKanrenshaY2022Logic showBakancesheetReportByKanrenshaY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private ShowBakancesheetReportByKanrenshaY2023Logic showBakancesheetReportByKanrenshaY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private ShowBakancesheetReportByKanrenshaY2024Logic showBakancesheetReportByKanrenshaY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private ShowBakancesheetReportByKanrenshaY2025Logic showBakancesheetReportByKanrenshaY2025Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public IncomeAndOutcomeNaturalSearchResultDto practice(final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {

        int houkokuNen = capsuleDto.getHoukokuNen();

        switch (houkokuNen) {
            case YEAR_2022:
                return showBakancesheetReportByKanrenshaY2022Logic.practice(capsuleDto);

            case YEAR_2023:
                return showBakancesheetReportByKanrenshaY2023Logic.practice(capsuleDto);

            case YEAR_2024:
                return showBakancesheetReportByKanrenshaY2024Logic.practice(capsuleDto);

            case YEAR_2025:
                return showBakancesheetReportByKanrenshaY2025Logic.practice(capsuleDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }

}
