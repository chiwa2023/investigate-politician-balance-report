package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class FinancialOrgCsvMasterProcessor implements ItemProcessor<FinancialOrgCsvDto, ZenginOrgBranchMasterEntity> {

    /** 自然検索用テキスト成形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchMasterEntity process(final FinancialOrgCsvDto item) throws Exception {

        ZenginOrgBranchMasterEntity masterEntity = new ZenginOrgBranchMasterEntity();

        BeanUtils.copyProperties(item, masterEntity);

        // 自然検索
        masterEntity.setZenginOrgTempoMasterName(
                formatNaturalSearchTextUtil.practice(item.getOrgName() + item.getBranchName()));

        // 更新するたび(基本毎月?)使うので数値化しておく
        Pattern pattern = Pattern.compile("^0+([0-9]+.*)");
        Matcher matcher = pattern.matcher(masterEntity.getOrgCode());
        if (matcher.matches()) {
            masterEntity.setOrgNumber(Integer.parseInt(matcher.group(1)));
        } else {

            // 1000番台以上0で始まらないとき
            // 数字でないデータの場合はPGもしくはデータに問題があるので落とす
            masterEntity.setOrgNumber(Integer.parseInt(masterEntity.getOrgCode()));
        }

        return masterEntity;
    }
}
