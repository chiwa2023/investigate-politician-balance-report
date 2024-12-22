package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvWkTbl3Processor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchWk3Entity> {

    /** 自然検索用テキスト成形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;


    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchWk3Entity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchWk3Entity wkTbl3Entity = new ZenginOrgBranchWk3Entity();

        BeanUtils.copyProperties(item, wkTbl3Entity);

        wkTbl3Entity.setZenginOrgBranchWk3Name(
                formatNaturalSearchTextUtil.practice(item.getOrgName() + item.getBranchName()));

        return wkTbl3Entity;
    }
}
