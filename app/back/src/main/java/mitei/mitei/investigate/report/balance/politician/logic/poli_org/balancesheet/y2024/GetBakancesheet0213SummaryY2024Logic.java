package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaSurfaceAndSummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet01And20SurfaceEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0702And0713SummaryEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheet0701And0720Surface2024Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheet0702And0713And0717Summary2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheet0701And0720Surface2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheet0702And0713And0717Summary2024Repository;

/**
 * 政治資金収支報告書要約(様式02収入要約・13支出要約)を取得する
 */
@Component
public class GetBakancesheet0213SummaryY2024Logic {

    /** 政治資金収支報告書要約Repository(2024) */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2024Repository offeringBalancesheet0701And0720Surface2024Repository;

    /** 政治資金収支報告書要約Repository(2024) */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2024Repository offeringBalancesheet0702And0713And0717Summary2024Repository;

    /**
     * 処理を行う
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 政治資金収支報告書要約Entity
     */
    public KeinenHenkaSurfaceAndSummaryByYearDto practice(final Integer poliOrgCode) {

        // 最新の提出日の最新データを取得する
        Optional<OfferingBalancesheet0701And0720Surface2024Entity> optional = offeringBalancesheet0701And0720Surface2024Repository
                .findFirstByPoliticalOrganizationCodeAndSaishinKbnOrderByOfferingDateDesc(poliOrgCode,
                        DataHistoryStatusConstants.INSERT.value());

        final int myYear = 2024;
        OfferingBalancesheet01And20SurfaceEntity entitySurface = new OfferingBalancesheet01And20SurfaceEntity();
        OfferingBalancesheet0702And0713SummaryEntity entitySummary = new OfferingBalancesheet0702And0713SummaryEntity();
        if (optional.isEmpty()) {
            // 登録がない場合は表紙も集計も空Entity
            entitySurface.setHoukokuNen(myYear);
            entitySummary.setHoukokuNen(myYear);

        } else {
            // 表紙が取得できたので設定
            OfferingBalancesheet0701And0720Surface2024Entity entityDocument = optional.get();
            BeanUtils.copyProperties(entityDocument, entitySurface);

            // 表紙と同じ文書同一識別コードを持っている最新データを取得する
            List<OfferingBalancesheet0702And0713And0717Summary2024Entity> listYear = offeringBalancesheet0702And0713And0717Summary2024Repository
                    .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(
                            entitySurface.getOfferingBalancesheet0701And0720SurfaceCode());
            if (listYear.isEmpty()) {
                entitySummary.setHoukokuNen(myYear);
            } else {
                BeanUtils.copyProperties(listYear.get(0), entitySummary);
            }

        }

        // 表紙と集計をDtoにセット
        KeinenHenkaSurfaceAndSummaryByYearDto keinenHenkaByYearDto = new KeinenHenkaSurfaceAndSummaryByYearDto();
        keinenHenkaByYearDto.setOfferingBalancesheet01And20SurfaceEntity(entitySurface);
        keinenHenkaByYearDto.setOfferingBalancesheet0702And0713SummaryEntity(entitySummary);

        return keinenHenkaByYearDto;
    }

}
