package mitei.mitei.investigate.report.balance.politician.service.party_jougen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.party_jougen.SearchPartyJougenByCodeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.party_jougen.SearchPartyJougenByGenBunshoNameLogic;

/**
 * パーティ上限チェック結果を算出するService
 */
@Service
public class SearchPartyJougenService {

    /** パーティ上限検出コード基準Logic */
    @Autowired
    private SearchPartyJougenByCodeLogic searchPartyJougenByCodeLogic;

    /** パーティ上限検出原文書名基準Logic */
    @Autowired
    private SearchPartyJougenByGenBunshoNameLogic searchPartyJougenByGenBunshoNameLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto capsuleDto) {
        
        if(capsuleDto.getIsNameSearch()) {
            return searchPartyJougenByGenBunshoNameLogic.practice(capsuleDto);
        }else{
            return searchPartyJougenByCodeLogic.practice(capsuleDto);
        }
    }
}
