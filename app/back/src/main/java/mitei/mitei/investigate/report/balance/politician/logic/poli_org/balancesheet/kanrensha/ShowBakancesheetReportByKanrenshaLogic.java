package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.ShowBakancesheetReportByKanrenshaY2022Logic;

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
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }

}
