package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class ZenginOrgWKTbl2ChangeProcessor implements ItemProcessor<ZenginOrgBranchWk2Entity, ZenginOrgChangeBranchEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgChangeBranchEntity process(final ZenginOrgBranchWk2Entity item) throws Exception {

        ZenginOrgChangeBranchEntity changeEntity = new ZenginOrgChangeBranchEntity();

        BeanUtils.copyProperties(item, changeEntity);
        changeEntity.setZenginOrgTempoMasterId(item.getZenginOrgBranchWk2Id());
        changeEntity.setZenginOrgTempoMasterCode(item.getZenginOrgBranchWk2Code());
        changeEntity.setZenginOrgTempoMasterName(item.getZenginOrgBranchWk2Name());

        return changeEntity;
    }
}
