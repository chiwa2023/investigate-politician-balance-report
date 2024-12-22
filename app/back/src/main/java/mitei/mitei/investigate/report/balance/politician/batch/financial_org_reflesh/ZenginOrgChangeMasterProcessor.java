package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 全銀金融機関支店異動テーブルからマスタテーブルに変換
 */
@Component
public class ZenginOrgChangeMasterProcessor
        implements ItemProcessor<ZenginOrgChangeBranchEntity, ZenginOrgBranchMasterEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgBranchMasterEntity process(final ZenginOrgChangeBranchEntity item) throws Exception {

        ZenginOrgBranchMasterEntity masterEntity = new ZenginOrgBranchMasterEntity();

        BeanUtils.copyProperties(item, masterEntity);

        return masterEntity;
    }

}
