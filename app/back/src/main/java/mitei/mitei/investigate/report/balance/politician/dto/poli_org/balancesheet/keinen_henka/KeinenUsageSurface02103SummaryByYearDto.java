package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka;

import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0801And0807ReportEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0802And0803ReportEntity;

/**
 * 政党交付金使途報告書集計表取得Dto
 */
public class KeinenUsageSurface02103SummaryByYearDto {

    /** 政党交付金使途報告書表紙Entity */
    private OfferingPartyUsage0801And0807ReportEntity surfaceEntity;

    /** 政党交付金使途報告書集計Entity */
    private OfferingPartyUsage0802And0803ReportEntity summaryEntity;

    /** 検索報告年 */
    private int houkokuNen;

    /**
     * 政党交付金使途報告書表紙Entityを取得する
     *
     * @return 政党交付金使途報告書表紙Entity
     */
    public OfferingPartyUsage0801And0807ReportEntity getSurfaceEntity() {
        return surfaceEntity;
    }

    /**
     * 政党交付金使途報告書表紙Entityを設定する
     *
     * @param surfaceEntity 政党交付金使途報告書表紙Entity
     */
    public void setSurfaceEntity(final OfferingPartyUsage0801And0807ReportEntity surfaceEntity) {
        this.surfaceEntity = surfaceEntity;
    }

    /**
     * 政党交付金使途報告書集計Entityを取得する
     *
     * @return 政党交付金使途報告書集計Entity
     */
    public OfferingPartyUsage0802And0803ReportEntity getSummaryEntity() {
        return summaryEntity;
    }

    /**
     * 政党交付金使途報告書集計Entityを設定する
     *
     * @param summaryEntity 政党交付金使途報告書集計Entity
     */
    public void setSummaryEntity(final OfferingPartyUsage0802And0803ReportEntity summaryEntity) {
        this.summaryEntity = summaryEntity;
    }

    /**
     * 検索報告年を取得する
     *
     * @return 検索報告年
     */
    public int getHoukokuNen() {
        return houkokuNen;
    }

    /**
     * 検索報告年を設定する
     *
     * @param houkokuNen 検索報告年
     */
    public void setHoukokuNen(final int houkokuNen) {
        this.houkokuNen = houkokuNen;
    }

}
