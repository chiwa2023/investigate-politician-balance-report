package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinSelectDto;

/**
 * 交付金連結Processor
 */
@Component
public class RenketsuThroughProcessor
        implements ItemProcessor<WkTblRenketsuKoufukinSelectDto, WkTblRenketsuKoufukinEntity> {

    /**
     * 変換作業を行う
     */
    @Override
    public WkTblRenketsuKoufukinEntity process(final WkTblRenketsuKoufukinSelectDto item) throws Exception {

        WkTblRenketsuKoufukinEntity entity = new WkTblRenketsuKoufukinEntity();
        BeanUtils.copyProperties(item, entity);
        entity.setWkTblRenketsuKoufukinId(0L); // 明示しないと動かない!
        
        return entity;
    }

}
