package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class ZenginOrgWKTbl1ChangeProcessor implements ItemProcessor<ZenginOrgBranchWk1Entity, ZenginOrgChangeBranchEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgChangeBranchEntity process(final ZenginOrgBranchWk1Entity item) throws Exception {

        ZenginOrgChangeBranchEntity changeEntity = new ZenginOrgChangeBranchEntity();

        BeanUtils.copyProperties(item, changeEntity);
        changeEntity.setZenginOrgTempoMasterId(item.getZenginOrgBranchWk1Id());
        changeEntity.setZenginOrgTempoMasterCode(item.getZenginOrgBranchWk1Code());
        changeEntity.setZenginOrgTempoMasterName(item.getZenginOrgBranchWk1Name());
        
        return changeEntity;
    }
}
