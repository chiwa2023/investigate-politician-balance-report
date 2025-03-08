package mitei.mitei.investigate.report.balance.politician.service.keinen_henka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaSurfaceAndSummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.GetBakancesheet0213SummaryY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.GetBakancesheet0213SummaryY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.GetBakancesheet0213SummaryY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.GetBakancesheet0213SummaryY2025Logic;

/**
 * 政治団体経年変化データを取得する
 */
@Service
public class PoliOrgBalancesheetKeinenHenkaService {

    // フィールドテンプレート始まり

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private GetBakancesheet0213SummaryY2022Logic getBakancesheet0213SummaryY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired
    private GetBakancesheet0213SummaryY2023Logic getBakancesheet0213SummaryY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private GetBakancesheet0213SummaryY2024Logic getBakancesheet0213SummaryY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private GetBakancesheet0213SummaryY2025Logic getBakancesheet0213SummaryY2025Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 経年変化検索条件格納Dto
     * @return 収支報告書集計収入集計(02)支出集計(07)Entity
     */
    public List<KeinenHenkaSurfaceAndSummaryByYearDto> practice(final KeinenHenkaConditionCapsuleDto capsuleDto) {

        List<KeinenHenkaSurfaceAndSummaryByYearDto> list = new ArrayList<>();

        Integer poliOrgCode = capsuleDto.getPoliOrgCode();

        // 検索開始報告年から検索終了報告年までループ
        for (int houkokuNen = capsuleDto.getHoukokuNenStart(); houkokuNen <= capsuleDto
                .getHoukokuNenEnd(); houkokuNen++) {
            switch (houkokuNen) {

                // 2022年
                case YEAR_2022:
                    list.add(getBakancesheet0213SummaryY2022Logic.practice(poliOrgCode));
                    break;

                // 2023年
                case YEAR_2023:
                    list.add(getBakancesheet0213SummaryY2023Logic.practice(poliOrgCode));
                    break;

                // 2024年
                case YEAR_2024:
                    list.add(getBakancesheet0213SummaryY2024Logic.practice(poliOrgCode));
                    break;

                // 2025年
                case YEAR_2025:
                    list.add(getBakancesheet0213SummaryY2025Logic.practice(poliOrgCode));
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
            }

        }

        return list;
    }

}
