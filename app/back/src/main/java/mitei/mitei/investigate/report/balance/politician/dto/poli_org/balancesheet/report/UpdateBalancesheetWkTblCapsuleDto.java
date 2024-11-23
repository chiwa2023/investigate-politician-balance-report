package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * 政治資金収支報告書ワークテーブル更新用
 */
public class UpdateBalancesheetWkTblCapsuleDto extends AbstractCapsuleDto implements Serializable{

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    
    /** 政治資金収支報告書一括処理ワークテーブル */
    private WkTblPoliOrgBalancesheetReportEntity wkTblPoliOrgBalancesheetReportEntity;


    /**
     * 政治資金収支報告書一括処理ワークテーブルを取得する
     *
     * @return 政治資金収支報告書一括処理ワークテーブル
     */
    public WkTblPoliOrgBalancesheetReportEntity getWkTblPoliOrgBalancesheetReportEntity() {
        return wkTblPoliOrgBalancesheetReportEntity;
    }


    /**
     * 政治資金収支報告書一括処理ワークテーブルを設定する
     *
     * @param wkTblPoliOrgBalancesheetReportEntity 政治資金収支報告書一括処理ワークテーブル
     */
    public void setWkTblPoliOrgBalancesheetReportEntity(
            final WkTblPoliOrgBalancesheetReportEntity wkTblPoliOrgBalancesheetReportEntity) {
        this.wkTblPoliOrgBalancesheetReportEntity = wkTblPoliOrgBalancesheetReportEntity;
    }
    
    
}
