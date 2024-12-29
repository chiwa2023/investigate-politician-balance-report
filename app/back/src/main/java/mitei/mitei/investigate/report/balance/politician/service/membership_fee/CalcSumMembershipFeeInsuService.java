package mitei.mitei.investigate.report.balance.politician.service.membership_fee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.FeeSummaryResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CalcSumMembershipFeeInsuLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalPartyRelationPersonRepository;

/**
 * 会費の合計と人数合計を算出するService
 */
@Service
public class CalcSumMembershipFeeInsuService {

    /** 政党関連者紐づけRepository */
    @Autowired
    private PoliticalPartyRelationPersonRepository politicalPartyRelationPersonRepository;

    /** 関連者リストから会費人数を算出するLogic */
    @Autowired
    private CalcSumMembershipFeeInsuLogic calcSumMembershipFeeInsuLogic;
    
    /**
     * 処理を行う
     *
     * @param poliPartyCode 政党同一識別コード
     * @return 合計結果Dto
     */
    public FeeSummaryResultDto practice(final Integer poliPartyCode) {

        // 政治団体を個別に指定しているときは、政治団体の合計概念が存在しないので空値を返す
        if(poliPartyCode.equals(0)) {
            return new FeeSummaryResultDto();
        }
        
        List<Integer> listRelationPersonCode = politicalPartyRelationPersonRepository
                .findByPoliticalPartyCode(poliPartyCode,Pageable.unpaged());
        
        return calcSumMembershipFeeInsuLogic.practice(listRelationPersonCode);
    }
    
}
