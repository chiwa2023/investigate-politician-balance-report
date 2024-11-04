package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * ReadXmlDtoからワークテーブルEntityに変換するProcessor
 */
@Component
public class ReadXmlDtoToWrokTableEntityProcessor
        implements ItemProcessor<ReadXmlBalancesheetResultDto, WkTblPoliOrgBalancesheetReportEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public WkTblPoliOrgBalancesheetReportEntity process(final ReadXmlBalancesheetResultDto item) throws Exception {

        WkTblPoliOrgBalancesheetReportEntity entity = new WkTblPoliOrgBalancesheetReportEntity();

        BeanUtils.copyProperties(item.getSaveStorageResultDto(), entity);
        BeanUtils.copyProperties(item.getCoverDto(), entity);
        BeanUtils.copyProperties(item.getDocumentPropertyDto(), entity);

        entity.setTaskPlanBalancesheetDetailCode(item.getTaskPlanBalancesheetDetailCode());
        entity.setTaskPlanBalancesheetDetailId(item.getTaskPlanBalancesheetDetailId());
        
        return entity;
    }
}
