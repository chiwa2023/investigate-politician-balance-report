package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * 全銀金融機関・店舗Dtoから全銀マスタテーブルEntityに変換するProcessor
 */
@Component
public class ZenginOrgWKTbl3ChangeProcessor implements ItemProcessor<ZenginOrgBranchWk3Entity, ZenginOrgChangeBranchEntity> {

    /**
     * 変換処理を行う
     */
    @Override
    public ZenginOrgChangeBranchEntity process(final ZenginOrgBranchWk3Entity item) throws Exception {

        ZenginOrgChangeBranchEntity changeEntity = new ZenginOrgChangeBranchEntity();

        BeanUtils.copyProperties(item, changeEntity);
        changeEntity.setZenginOrgTempoMasterId(item.getZenginOrgBranchWk3Id());
        changeEntity.setZenginOrgTempoMasterCode(item.getZenginOrgBranchWk3Code());
        changeEntity.setZenginOrgTempoMasterName(item.getZenginOrgBranchWk3Name());

        return changeEntity;
    }
}
