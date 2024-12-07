package mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;

/**
 * 政治資金収支報告書ワークテーブル更新用
 */
public class UpdatePartyUsageWkTblCapsuleDto extends AbstractCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 政党交付金使途報告書一括処理ワークテーブル */
    private WkTblPoliPartyUsageReportEntity wkTblPoliPartyUsageReportEntity;

    /**
     * 政党交付金使途報告書一括処理ワークテーブルを取得する
     *
     * @return 政党交付金使途報告書一括処理ワークテーブル
     */
    public WkTblPoliPartyUsageReportEntity getWkTblPoliPartyUsageReportEntity() {
        return wkTblPoliPartyUsageReportEntity;
    }

    /**
     * 政党交付金使途報告書一括処理ワークテーブルを設定する
     *
     * @param wkTblPoliPartyUsageReportEntity 政党交付金使途報告書一括処理ワークテーブル
     */
    public void setWkTblPoliPartyUsageReportEntity(
            final WkTblPoliPartyUsageReportEntity wkTblPoliPartyUsageReportEntity) {
        this.wkTblPoliPartyUsageReportEntity = wkTblPoliPartyUsageReportEntity;
    }

}
