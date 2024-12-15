package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;


/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvWkTbl2Processor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchWk2Entity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchWk2Entity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchWk2Entity wkTbl2Entity = new ZenginOrgBranchWk2Entity();

        BeanUtils.copyProperties(item, wkTbl2Entity);
        
        return wkTbl2Entity;
    }
}
