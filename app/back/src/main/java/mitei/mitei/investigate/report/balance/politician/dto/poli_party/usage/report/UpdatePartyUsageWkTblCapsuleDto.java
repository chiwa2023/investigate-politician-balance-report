package mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgPartyUsageReportEntity;

/**
 * 政治資金収支報告書ワークテーブル更新用
 */
public class UpdatePartyUsageWkTblCapsuleDto extends AbstractCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 政党交付金使途報告書一括処理ワークテーブル */
    private WkTblPoliOrgPartyUsageReportEntity wkTblPoliOrgPartyUsageReportEntity;

    /**
     * 政党交付金使途報告書一括処理ワークテーブルを取得する
     *
     * @return 政党交付金使途報告書一括処理ワークテーブル
     */
    public WkTblPoliOrgPartyUsageReportEntity getWkTblPoliOrgPartyUsageReportEntity() {
        return wkTblPoliOrgPartyUsageReportEntity;
    }

    /**
     * 政党交付金使途報告書一括処理ワークテーブルを設定する
     *
     * @param wkTblPoliOrgPartyUsageReportEntity 政党交付金使途報告書一括処理ワークテーブル
     */
    public void setWkTblPoliOrgPartyUsageReportEntity(
            final WkTblPoliOrgPartyUsageReportEntity wkTblPoliOrgPartyUsageReportEntity) {
        this.wkTblPoliOrgPartyUsageReportEntity = wkTblPoliOrgPartyUsageReportEntity;
    }

}
