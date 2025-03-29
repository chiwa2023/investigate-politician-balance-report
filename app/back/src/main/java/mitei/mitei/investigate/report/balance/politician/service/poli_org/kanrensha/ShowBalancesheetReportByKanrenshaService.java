package mitei.mitei.investigate.report.balance.politician.service.poli_org.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha.ShowBakancesheetReportByKanrenshaLogic;

/**
 * 関連者から収入・支出データを取得するService
 */
@Service
public class ShowBalancesheetReportByKanrenshaService {

    /** 関連者から収入・支出データを取得するLogic */
    @Autowired
    private ShowBakancesheetReportByKanrenshaLogic showBakancesheetReportByKanrenshaLogic;
    
    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public IncomeAndOutcomeNaturalSearchResultDto practice(final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {
        
        return showBakancesheetReportByKanrenshaLogic.practice(capsuleDto);
    }

}
