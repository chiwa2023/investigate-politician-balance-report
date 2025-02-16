package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage05Dto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

@Component
public class InsertUkaiKenkinRouteTimes05Logic {


    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    public void practice(Integer userCode,Integer tradingPartnerCode) {
     
        List<RouteUkaiKenkinStage05Dto> list1 = wkTblUkaiKenkinRepository.findRouteStage05(userCode,tradingPartnerCode);

        for(RouteUkaiKenkinStage05Dto dto: list1) {
            System.out.println("経路"  + dto.getCode00() + "→" + dto.getCode01());
        }
    }
    
}
