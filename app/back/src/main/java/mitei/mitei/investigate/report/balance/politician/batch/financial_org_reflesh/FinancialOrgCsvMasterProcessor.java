package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;


/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvMasterProcessor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchMasterEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchMasterEntity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchMasterEntity masterEntity = new ZenginOrgBranchMasterEntity();

        BeanUtils.copyProperties(item, masterEntity);
        
        return masterEntity;
    }
}
