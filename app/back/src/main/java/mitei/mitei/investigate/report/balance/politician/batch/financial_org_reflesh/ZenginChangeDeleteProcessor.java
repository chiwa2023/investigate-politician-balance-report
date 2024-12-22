package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.ZenginOrgChangeKbnConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 金融機関支店変更テーブルの変更区分に追加区分を追加する
 */
@Component
public class ZenginChangeDeleteProcessor  implements ItemProcessor<ZenginOrgChangeBranchEntity, ZenginOrgChangeBranchEntity>{

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgChangeBranchEntity process(final ZenginOrgChangeBranchEntity item) throws Exception {
        final int kbn = ZenginOrgChangeKbnConstants.DELETE;
        
        // 削除
        item.setChangeKbn(kbn);
        item.setChangeKbnText(ZenginOrgChangeKbnConstants.getText(kbn));
        
        return item;
    }
}
