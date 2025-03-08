package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet01And20SurfaceEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0702And0713SummaryEntity;

/**
 * 年単位の政治資金収支報告書の表紙Entityと集計Entity格納Dto
 */
public class KeinenHenkaSurfaceAndSummaryByYearDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 収支報告書表紙Entity */
    private OfferingBalancesheet01And20SurfaceEntity offeringBalancesheet01And20SurfaceEntity;

    /** 収支報告書集計Entity */
    private OfferingBalancesheet0702And0713SummaryEntity offeringBalancesheet0702And0713SummaryEntity;

    /**
     * 収支報告書表紙Entityを取得する
     *
     * @return 収支報告書表紙Entity
     */
    public OfferingBalancesheet01And20SurfaceEntity getOfferingBalancesheet01And20SurfaceEntity() {
        return offeringBalancesheet01And20SurfaceEntity;
    }

    /**
     * 収支報告書表紙Entityを設定する
     *
     * @param offeringBalancesheet01And20SurfaceEntity 収支報告書表紙Entity
     */
    public void setOfferingBalancesheet01And20SurfaceEntity(
            final OfferingBalancesheet01And20SurfaceEntity offeringBalancesheet01And20SurfaceEntity) {
        this.offeringBalancesheet01And20SurfaceEntity = offeringBalancesheet01And20SurfaceEntity;
    }

    /**
     * 収支報告書集計Entityを取得する
     *
     * @return 収支報告書集計Entity
     */
    public OfferingBalancesheet0702And0713SummaryEntity getOfferingBalancesheet0702And0713SummaryEntity() {
        return offeringBalancesheet0702And0713SummaryEntity;
    }

    /**
     * 収支報告書集計Entityを設定する
     *
     * @param offeringBalancesheet0702And0713SummaryEntity 収支報告書集計Entity
     */
    public void setOfferingBalancesheet0702And0713SummaryEntity(
            final OfferingBalancesheet0702And0713SummaryEntity offeringBalancesheet0702And0713SummaryEntity) {
        this.offeringBalancesheet0702And0713SummaryEntity = offeringBalancesheet0702And0713SummaryEntity;
    }

}
