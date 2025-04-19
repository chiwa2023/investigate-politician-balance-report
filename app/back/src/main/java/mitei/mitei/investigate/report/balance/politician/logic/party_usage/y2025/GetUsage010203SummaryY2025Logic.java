package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenUsageSurface02103SummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0801And0807ReportEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0802And0803ReportEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0802And0803Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0802And0803Report2025Repository;

/**
 * 政党交付金使途報告書表紙と集計取得Logic(2025)
 */
@Component
public class GetUsage010203SummaryY2025Logic {

    /** 政党交付金使途報告書表紙(様式区分8の1と7)Repository(2025) */
    @Autowired
    private OfferingPartyUsage0801And0807Report2025Repository offeringPartyUsage0801And0807Report2025Repository;

    /** 政党交付金使途報告書集計表(様式区分8の2と3)Repository(2025) */
    @Autowired
    private OfferingPartyUsage0802And0803Report2025Repository offeringPartyUsage0802And0803Report2025Repository;

    /**
     * 処理を行う
     *
     * @param pooliOrg 政治団体同一識別コード
     * @return 表紙と集計
     */
    public KeinenUsageSurface02103SummaryByYearDto practice(final Integer pooliOrg) {

        KeinenUsageSurface02103SummaryByYearDto summaryByYearDto = new KeinenUsageSurface02103SummaryByYearDto();
        final int myYear = 2025;
        summaryByYearDto.setHoukokuNen(myYear);

        Optional<OfferingPartyUsage0801And0807Report2025Entity> optional = offeringPartyUsage0801And0807Report2025Repository
                .findFirstByPoliticalOrganizationCodeAndSaishinKbnOrderByOfferingDateDesc(pooliOrg,
                        DataHistoryStatusConstants.INSERT.value());

        OfferingPartyUsage0801And0807ReportEntity surfaceEntity = new OfferingPartyUsage0801And0807ReportEntity();
        surfaceEntity.setNendo(myYear);
        OfferingPartyUsage0802And0803ReportEntity summaryEntity = new OfferingPartyUsage0802And0803ReportEntity();
        summaryEntity.setNendo(myYear);

        if (!optional.isEmpty()) {
            BeanUtils.copyProperties(optional.get(), surfaceEntity);
            summaryByYearDto.setSurfaceEntity(surfaceEntity);
            List<OfferingPartyUsage0802And0803Report2025Entity> list = offeringPartyUsage0802And0803Report2025Repository
                    .findByDocumentCodeOrderByPartyUsage0802And0803ReportId(
                            surfaceEntity.getPartyUsage0801And0807ReportCode());
            if (!list.isEmpty()) {
                BeanUtils.copyProperties(list.get(0), summaryEntity);
                summaryByYearDto.setSummaryEntity(summaryEntity);
            }
        }

        summaryByYearDto.setSurfaceEntity(surfaceEntity);
        summaryByYearDto.setSummaryEntity(summaryEntity);

        return summaryByYearDto;
    }

}
