package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk4Entity;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvWkTbl4Processor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchWk4Entity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchWk4Entity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchWk4Entity wkTbl4Entity = new ZenginOrgBranchWk4Entity();

        BeanUtils.copyProperties(item, wkTbl4Entity);

        return wkTbl4Entity;
    }
}
