package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalPartyRelationPersonEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Repository;

/**
 * 会費要約検索Logic
 */
public class SearchMemberShipFeeSummaryLogic {

    
    /** 政治資金収支報告書様式2,13,17Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2022Repository balancesheet0702And0713And0717Summary2022Repository;
    
    public void practice(List<PoliticalPartyRelationPersonEntity> listKanrensha) {
        
        List<Integer> listDaihyoushaCode = new ArrayList<>();
        for(PoliticalPartyRelationPersonEntity entity : listKanrensha) {
            listDaihyoushaCode.add(entity.getRelationPersonCode());
        }
        
        List<OfferingBalancesheet0702And0713And0717Summary2022Entity> listEntity =
        balancesheet0702And0713And0717Summary2022Repository.findByRelationPersonCodeDelegateInAndSaishinKbn(listDaihyoushaCode,DataHistoryStatusConstants.INSERT.value());

        for(OfferingBalancesheet0702And0713And0717Summary2022Entity entity : listEntity) {
            
        }
        
        
        
    }
    
}
