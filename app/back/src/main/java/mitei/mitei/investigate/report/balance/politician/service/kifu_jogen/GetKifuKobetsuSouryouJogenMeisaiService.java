package mitei.mitei.investigate.report.balance.politician.service.kifu_jogen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.GetKifuKobetsuJogenByNameMeisaiLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.GetKifuKobetsuJogenByRelationCodeMeisaiLogic;

/**
 * 寄付上限確認用明細取得Service
 */
@Service
public class GetKifuKobetsuSouryouJogenMeisaiService {

    /** 寄付上限用明細取得原文書名称用Logic */
    @Autowired
    private GetKifuKobetsuJogenByNameMeisaiLogic getKifuKobetsuJogenByNameMeisaiLogic;

    /** 寄付上限用明細取得同一識別コード用Logic */
    @Autowired
    private GetKifuKobetsuJogenByRelationCodeMeisaiLogic getKifuKobetsuJogenByRelationCodeMeisaiLogic;

    /**
     * 処理を行う
     *
     * @param conditionDto 検索条件Dto
     * @return 検索結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto conditionDto) {

        if (conditionDto.getIsNameSearch()) {
            return getKifuKobetsuJogenByNameMeisaiLogic.practice(conditionDto);
        } else {
            return getKifuKobetsuJogenByRelationCodeMeisaiLogic.practice(conditionDto);
        }
    }

}
