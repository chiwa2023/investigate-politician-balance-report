package mitei.mitei.investigate.report.balance.politician.service.membership_fee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchMembershipFeeCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchPoliPartyRelationPersonResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.SearchMemberShipFeeSummaryLogic;
import mitei.mitei.investigate.report.balance.politician.logic.political_party.SearchRlationPersonByPoliPtyLogic;

/**
 * 政党同一識別コードから各政治団体会費・員数を取得するService
 */
@Service
public class SearchPoliPartyMembershipFeeService {

    /** 政党に紐づく関連者取得Logic */
    @Autowired
    private SearchRlationPersonByPoliPtyLogic searchRlationPersonByPoliPtyLogic;

    /** 党費・員数要約取得Logic */
    @Autowired
    private SearchMemberShipFeeSummaryLogic searchMemberShipFeeSummaryLogic;

    /**
     * 処理を実行する
     *
     * @param capsuleDto 各政治団体会費・員数検索条件Dto
     * @return 検索結果
     */
    public SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto practice(
            final SearchMembershipFeeCapsuleDto capsuleDto) {

        SearchPoliPartyRelationPersonResultDto relationPersonDto = searchRlationPersonByPoliPtyLogic
                .practice(capsuleDto.getPoliPartyCode(), capsuleDto.getPosPage());

        return searchMemberShipFeeSummaryLogic.practice(capsuleDto, relationPersonDto);
    }
}
