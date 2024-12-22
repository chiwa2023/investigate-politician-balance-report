package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvWkTbl1Processor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchWk1Entity> {

    /** 自然検索用テキスト成形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchWk1Entity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchWk1Entity wkTbl1Entity = new ZenginOrgBranchWk1Entity();

        BeanUtils.copyProperties(item, wkTbl1Entity);

        wkTbl1Entity.setZenginOrgBranchWk1Name(
                formatNaturalSearchTextUtil.practice(item.getOrgName() + item.getBranchName()));

        return wkTbl1Entity;
    }
}
