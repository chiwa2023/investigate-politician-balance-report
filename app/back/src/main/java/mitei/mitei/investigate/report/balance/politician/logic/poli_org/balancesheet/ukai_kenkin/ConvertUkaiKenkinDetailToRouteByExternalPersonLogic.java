package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * 迂回献金明細データを経路データに、外部関連者データと比較して一致点を探しながら変換する
 */
@Component
public class ConvertUkaiKenkinDetailToRouteByExternalPersonLogic {

    /**
     * 処理を行う
     *
     * @param listData  迂回献金明細リスト
     * @param personDto 比較対象関連者(個人)データ
     * @return 迂回献金経路リスト
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> practice(final List<WkTblUkaiKenkinEntity> listData,
            final RelationPersonWithYakuwariDto personDto) {

        List<WkTblUkaiKenkinPickupRouteEntity> list = new ArrayList<>();
        final String INIT_DATA = "";
        
        for (WkTblUkaiKenkinEntity entity : listData) {

            WkTblUkaiKenkinPickupRouteEntity routeEntity = this.createRouteEntiy(entity, personDto);

            // 一致データが入っている場合は取引相手側にも挿入
            if (!INIT_DATA.equals(routeEntity.getPoliOrgRelationPersonYakuari())) {
                routeEntity.setTradingRelationPersonId(personDto.getId());
                routeEntity.setTradingRelationPersonCode(personDto.getCode());
                routeEntity.setTradingRelationPersonName(personDto.getName());
                routeEntity.setTradingRelationPersonYakuari(personDto.getYakuwari());
            }
            list.add(routeEntity);
        }

        return list;
    }

    // 迂回系明細から経路Entityに変換
    private WkTblUkaiKenkinPickupRouteEntity createRouteEntiy(final WkTblUkaiKenkinEntity entity,
            final RelationPersonWithYakuwariDto personDto) {

        WkTblUkaiKenkinPickupRouteEntity routeEntity = new WkTblUkaiKenkinPickupRouteEntity();
        BeanUtils.copyProperties(entity, routeEntity);

        Integer code = personDto.getCode();

        // 代表者
        if (code.equals(entity.getTradingPartnerDelegateCode())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingPartnerDelegateId());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingPartnerDelegateCode());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingPartnerDelegateName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);
            return routeEntity;
        }

        // 会計責任者
        if (code.equals(entity.getTradingOrgAccountManagerCode())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingOrgAccountManagerId());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingOrgAccountManagerCode());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingOrgAccountManagerName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA);
            return routeEntity;
        }

        // 資金管理団体責任者
        if (code.equals(entity.getTradingOrgShikinDantaiCode())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingOrgShikinDantaiId());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingOrgShikinDantaiCode());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingOrgShikinDantaiName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA);
            return routeEntity;
        }

        // 議員1
        if (code.equals(entity.getTradingOrgKokkaiGiin1Code())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingOrgKokkaiGiin1Id());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingOrgKokkaiGiin1Code());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingOrgKokkaiGiin1Name());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN1);
            return routeEntity;
        }

        // 議員2
        if (code.equals(entity.getTradingOrgKokkaiGiin2Code())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingOrgKokkaiGiin2Id());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingOrgKokkaiGiin2Code());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingOrgKokkaiGiin2Name());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN2);
            return routeEntity;
        }

        // 議員3
        if (code.equals(entity.getTradingOrgKokkaiGiin3Code())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingOrgKokkaiGiin3Id());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingOrgKokkaiGiin3Code());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingOrgKokkaiGiin3Name());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN3);
            return routeEntity;
        }

        // 個人寄付データに限り、取引相手そのものとも判定
        if (routeEntity.getYoushikiEdaKbn().equals(YoushikiEdaKbn.KOJIN)
                && code.equals(entity.getTradingPartnerCode())) {
            routeEntity.setPoliOrgRelationPersonId(entity.getTradingPartnerId());
            routeEntity.setPoliOrgRelationPersonCode(entity.getTradingPartnerCode());
            routeEntity.setPoliOrgRelationPersonName(entity.getTradingPartnerName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI);
            return routeEntity;
        }

        return routeEntity;
    }

}
