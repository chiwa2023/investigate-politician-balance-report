package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvWkTbl3Processor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchWk3Entity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchWk3Entity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchWk3Entity wkTbl3Entity = new ZenginOrgBranchWk3Entity();

        BeanUtils.copyProperties(item, wkTbl3Entity);

        return wkTbl3Entity;
    }
}
