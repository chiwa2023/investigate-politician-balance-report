package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;


/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvWkTbl2Processor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchWk2Entity> {

    /** 自然検索用テキスト成形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;


    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchWk2Entity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchWk2Entity wkTbl2Entity = new ZenginOrgBranchWk2Entity();

        BeanUtils.copyProperties(item, wkTbl2Entity);
        
        wkTbl2Entity.setZenginOrgBranchWk2Name(
                formatNaturalSearchTextUtil.practice(item.getOrgName() + item.getBranchName()));

        return wkTbl2Entity;
    }
}
