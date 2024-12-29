package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.FeeSummaryResultDto;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Repository;

/**
 * 合計会費と合計員数算出処理
 */
@Component
public class CalcSumMembershipFeeInsuLogic {

    /** 政治資金収支報告書様式2要約Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2022Repository offeringBalancesheet0702And0713And0717Summary2022Repository;


    /**
     * 処理を行う
     *
     * @param listRelationPersonCode 関連者同一識別コードリスト
     * @return 会費・員数の合計値
     */
    public FeeSummaryResultDto practice(final List<Integer> listRelationPersonCode) {
        
        Tuple tuple = offeringBalancesheet0702And0713And0717Summary2022Repository
                .findSumFeeAndInsu(listRelationPersonCode);
        
        FeeSummaryResultDto resultDto = new FeeSummaryResultDto();

        resultDto.setSumFee(new BigDecimal(String.valueOf(tuple.get(0))).longValue());
        resultDto.setSumInsu(new BigDecimal(String.valueOf(tuple.get(1))).intValue());
        
        return resultDto;
    }

}
