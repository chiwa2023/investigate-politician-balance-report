package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ReadXmlDtoからワークテーブルEntityに変換するProcessor
 */
@Component
public class ReadXmlDtoPartyUsageToWrokTableEntityProcessor
        implements ItemProcessor<ReadXmlPartyUsageResultDto, WkTblPoliPartyUsageReportEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public WkTblPoliPartyUsageReportEntity process(final ReadXmlPartyUsageResultDto item) throws Exception {

        WkTblPoliPartyUsageReportEntity entity = new WkTblPoliPartyUsageReportEntity();

        SetTableDataHistoryUtil.practice(item.getCheckPrivilegeDto(), entity, DataHistoryStatusConstants.INSERT);
        
        BeanUtils.copyProperties(item.getSaveStorageResultDto(), entity);
        BeanUtils.copyProperties(item.getCoverDto(), entity);
        BeanUtils.copyProperties(item.getDocumentPropertyDto(), entity);

        entity.setTaskPlanPartyUsageCode(item.getTaskPlanPartyUsageDetailCode());
        entity.setTaskPlanPartyUsageDetailId(item.getTaskPlanPartyUsageDetailId());
        
        return entity;
    }
}
