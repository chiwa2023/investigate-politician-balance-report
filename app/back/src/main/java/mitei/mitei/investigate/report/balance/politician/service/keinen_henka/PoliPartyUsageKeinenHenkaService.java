package mitei.mitei.investigate.report.balance.politician.service.keinen_henka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenUsageSurface02103SummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.GetUsage010203SummaryY2022Logic;

/**
 * 政党交付金使途報告書経年変化集計表を取得する
 */
@Service
public class PoliPartyUsageKeinenHenkaService {

    
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private GetUsage010203SummaryY2022Logic getUsage010203SummaryY2022Logic;
    
    
    /**
     * 処理を行う
     *
     * @param capsuleDto 経年変化検索条件格納Dto
     * @return 収支報告書集計収入集計(02)支出集計(07)Entity
     */
    public List<KeinenUsageSurface02103SummaryByYearDto> practice(final KeinenHenkaConditionCapsuleDto capsuleDto) {
        
        
        List<KeinenUsageSurface02103SummaryByYearDto> list = new ArrayList<>();
        
        Integer poliOrgCode = capsuleDto.getPoliOrgCode();

        // 検索開始報告年から検索終了報告年までループ
        for (int houkokuNen = capsuleDto.getHoukokuNenStart(); houkokuNen <= capsuleDto
                .getHoukokuNenEnd(); houkokuNen++) {
            switch (houkokuNen) {

                // 2022年
                case YEAR_2022:
                    list.add(getUsage010203SummaryY2022Logic.practice(poliOrgCode));
                    break;

//                // 2023年
//                case YEAR_2023:
//                    list.add(getBakancesheet0213SummaryY2023Logic.practice(poliOrgCode));
//                    break;
//
//                // 2024年
//                case YEAR_2024:
//                    list.add(getBakancesheet0213SummaryY2024Logic.practice(poliOrgCode));
//                    break;
//
//                // 2025年
//                case YEAR_2025:
//                    list.add(getBakancesheet0213SummaryY2025Logic.practice(poliOrgCode));
//                    break;
                    
                default:
                    throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
            }

        }

        
        
        
        
        return list;
    }

}
