package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * 迂回献金明細データを経路データに、クラス内部関連者データと比較して一致点を探しながら変換する
 */
@Component
public class ConvertUkaiKenkinDetailToRouteByInClassLogic {

//    /**
//     * 処理を行う
//     *
//     * @param listData 迂回献金明細リスト
//     * @return 迂回献金経路リスト
//     */
//    public List<WkTblUkaiKenkinPickupRouteEntity> practice(final List<WkTblUkaiKenkinEntity> listData) {
//
//        List<WkTblUkaiKenkinPickupRouteEntity> list = new ArrayList<>();
//        for (WkTblUkaiKenkinEntity entity : listData) {
//            list.add(this.createRouteEntiy(entity));
//        }
//
//        return list;
//    }

    /**
     * 処理を行う
     *
     * @param listData 迂回献金明細リスト
     * @return 迂回献金経路リスト
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> practice(final List<WkTblUkaiKenkinEntity> listData,final PoliticalOrganizationPropertyEntity propertyEntity) {

        List<WkTblUkaiKenkinPickupRouteEntity> list = new ArrayList<>();
        for (WkTblUkaiKenkinEntity entity : listData) {
            list.add(this.createRouteEntiy(entity,propertyEntity));
        }

        return list;
    }

    // 迂回系明細から経路Entityに変換
    private WkTblUkaiKenkinPickupRouteEntity createRouteEntiy(final WkTblUkaiKenkinEntity entity,final PoliticalOrganizationPropertyEntity propertyEntity) {

        WkTblUkaiKenkinPickupRouteEntity routeEntity = new WkTblUkaiKenkinPickupRouteEntity();
        BeanUtils.copyProperties(entity, routeEntity);
        
        // 資金の流し先に影響を与えられそうな順に判定
        RelationPersonWithYakuwariDto judageDto = this.checkSameRelationPerson(propertyEntity.getDelegateRelationPersonCode(), entity);
        if (!Objects.isNull(judageDto)) {
            routeEntity.setPoliOrgRelationPersonId(propertyEntity.getDelegateRelationPersonId());
            routeEntity.setPoliOrgRelationPersonCode(propertyEntity.getDelegateRelationPersonCode());
            routeEntity.setPoliOrgRelationPersonName(propertyEntity.getDelegateKoushokuName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);
            this.setTradingData(routeEntity, judageDto);
            return routeEntity;
        }

        // 会計責任者
        judageDto = this.checkSameRelationPerson(propertyEntity.getAccountManagerRelationPersonCode(), entity);
        if (!Objects.isNull(judageDto)) {
            routeEntity.setPoliOrgRelationPersonId(propertyEntity.getAccountManagerRelationPersonId());
            routeEntity.setPoliOrgRelationPersonCode(propertyEntity.getAccountManagerRelationPersonCode());
            routeEntity.setPoliOrgRelationPersonName(propertyEntity.getAccountManagerKoushokuName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA);
            this.setTradingData(routeEntity, judageDto);
            return routeEntity;
        }

        // 資金管理団体責任者
        judageDto = this.checkSameRelationPerson(propertyEntity.getShikinDaihyouRelationPersonCode(), entity);
        if (!Objects.isNull(judageDto)) {
            routeEntity.setPoliOrgRelationPersonId(propertyEntity.getShikinDaihyouRelationPersonId());
            routeEntity.setPoliOrgRelationPersonCode(propertyEntity.getShikinDaihyouRelationPersonCode());
            routeEntity.setPoliOrgRelationPersonName(propertyEntity.getShikinDaihyouKoushokuName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA);
            this.setTradingData(routeEntity, judageDto);
            return routeEntity;
        }

        // 議員1
        judageDto = this.checkSameRelationPerson(propertyEntity.getDelegateRelationPersonCode(), entity);
        if (!Objects.isNull(judageDto)) {
            routeEntity.setPoliOrgRelationPersonId(propertyEntity.getGiin1RelationPersonId());
            routeEntity.setPoliOrgRelationPersonCode(propertyEntity.getGiin1RelationPersonCode());
            routeEntity.setPoliOrgRelationPersonName(propertyEntity.getGiin1KoushokuName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN1);
            this.setTradingData(routeEntity, judageDto);
            return routeEntity;
        }

        // 議員2
        judageDto = this.checkSameRelationPerson(entity.getPoliOrgKokkaiGiin2Code(), entity);
        if (!Objects.isNull(judageDto)) {
            routeEntity.setPoliOrgRelationPersonId(propertyEntity.getGiin2RelationPersonId());
            routeEntity.setPoliOrgRelationPersonCode(propertyEntity.getGiin2RelationPersonCode());
            routeEntity.setPoliOrgRelationPersonName(propertyEntity.getGiin2KoushokuName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN2);
            this.setTradingData(routeEntity, judageDto);
            return routeEntity;
        }

        // 議員3
        judageDto = this.checkSameRelationPerson(entity.getPoliOrgKokkaiGiin3Code(), entity);
        if (!Objects.isNull(judageDto)) {
            routeEntity.setPoliOrgRelationPersonId(propertyEntity.getGiin3RelationPersonId());
            routeEntity.setPoliOrgRelationPersonCode(propertyEntity.getGiin3RelationPersonCode());
            routeEntity.setPoliOrgRelationPersonName(propertyEntity.getGiin3KoushokuName());
            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN3);
            this.setTradingData(routeEntity, judageDto);
            return routeEntity;
        }
        
        return routeEntity;
    }
    
    
    
//    // 迂回系明細から経路Entityに変換
//    private WkTblUkaiKenkinPickupRouteEntity createRouteEntiy(final WkTblUkaiKenkinEntity entity) {
//
//        WkTblUkaiKenkinPickupRouteEntity routeEntity = new WkTblUkaiKenkinPickupRouteEntity();
//        BeanUtils.copyProperties(entity, routeEntity);
//
//        // 資金の流し先に影響を与えられそうな順に判定
//        // 団体代表者
//        RelationPersonWithYakuwariDto judageDto = this.checkSameRelationPerson(entity.getPoliOrgDelegateCode(), entity);
//        if (!Objects.isNull(judageDto)) {
//            routeEntity.setPoliOrgRelationPersonId(entity.getPoliOrgDelegateId());
//            routeEntity.setPoliOrgRelationPersonCode(entity.getPoliOrgDelegateCode());
//            routeEntity.setPoliOrgRelationPersonName(entity.getPoliOrgDelegateName());
//            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);
//            this.setTradingData(routeEntity, judageDto);
//            return routeEntity;
//        }
//
//        // 会計責任者
//        judageDto = this.checkSameRelationPerson(entity.getPoliOrgAccountManagerCode(), entity);
//        if (!Objects.isNull(judageDto)) {
//            routeEntity.setPoliOrgRelationPersonId(entity.getPoliOrgAccountManagerId());
//            routeEntity.setPoliOrgRelationPersonCode(entity.getPoliOrgAccountManagerCode());
//            routeEntity.setPoliOrgRelationPersonName(entity.getPoliOrgAccountManagerName());
//            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA);
//            this.setTradingData(routeEntity, judageDto);
//            return routeEntity;
//        }
//
//        // 資金管理団体責任者
//        judageDto = this.checkSameRelationPerson(entity.getPoliOrgShikinDantaiCode(), entity);
//        if (!Objects.isNull(judageDto)) {
//            routeEntity.setPoliOrgRelationPersonId(entity.getPoliOrgShikinDantaiId());
//            routeEntity.setPoliOrgRelationPersonCode(entity.getPoliOrgShikinDantaiCode());
//            routeEntity.setPoliOrgRelationPersonName(entity.getPoliOrgShikinDantaiName());
//            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA);
//            this.setTradingData(routeEntity, judageDto);
//            return routeEntity;
//        }
//
//        // 議員1
//        judageDto = this.checkSameRelationPerson(entity.getPoliOrgKokkaiGiin1Code(), entity);
//        if (!Objects.isNull(judageDto)) {
//            routeEntity.setPoliOrgRelationPersonId(entity.getPoliOrgKokkaiGiin1Id());
//            routeEntity.setPoliOrgRelationPersonCode(entity.getPoliOrgKokkaiGiin1Code());
//            routeEntity.setPoliOrgRelationPersonName(entity.getPoliOrgKokkaiGiin1Name());
//            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN1);
//            this.setTradingData(routeEntity, judageDto);
//            return routeEntity;
//        }
//
//        // 議員2
//        judageDto = this.checkSameRelationPerson(entity.getPoliOrgKokkaiGiin2Code(), entity);
//        if (!Objects.isNull(judageDto)) {
//            routeEntity.setPoliOrgRelationPersonId(entity.getPoliOrgKokkaiGiin2Id());
//            routeEntity.setPoliOrgRelationPersonCode(entity.getPoliOrgKokkaiGiin2Code());
//            routeEntity.setPoliOrgRelationPersonName(entity.getPoliOrgKokkaiGiin2Name());
//            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN2);
//            this.setTradingData(routeEntity, judageDto);
//            return routeEntity;
//        }
//
//        // 議員3
//        judageDto = this.checkSameRelationPerson(entity.getPoliOrgKokkaiGiin3Code(), entity);
//        if (!Objects.isNull(judageDto)) {
//            routeEntity.setPoliOrgRelationPersonId(entity.getPoliOrgKokkaiGiin3Id());
//            routeEntity.setPoliOrgRelationPersonCode(entity.getPoliOrgKokkaiGiin3Code());
//            routeEntity.setPoliOrgRelationPersonName(entity.getPoliOrgKokkaiGiin3Name());
//            routeEntity.setPoliOrgRelationPersonYakuari(RelationPersonYakuwariConstants.YAKUWARI_GIIN3);
//            this.setTradingData(routeEntity, judageDto);
//            return routeEntity;
//        }
//
//        return routeEntity;
//    }

    // 関連者一致判定を行う
    private RelationPersonWithYakuwariDto checkSameRelationPerson(final Integer code,
            final WkTblUkaiKenkinEntity entity) {

        // 初期値ならそもそも判定の必要なし
        if (code == 0) {
            return null;
        }

        RelationPersonWithYakuwariDto person = new RelationPersonWithYakuwariDto();

        // 団体に影響力を持つ順番に判定(複数該当の可能性もある)
        // 代表者
        if (code.equals(entity.getTradingPartnerDelegateCode())) {
            person.setId(entity.getTradingPartnerDelegateId());
            person.setCode(entity.getTradingPartnerDelegateCode());
            person.setName(entity.getTradingPartnerDelegateName());
            person.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);
            return person;
        }

        // 会計責任者
        if (code.equals(entity.getTradingOrgAccountManagerCode())) {
            person.setId(entity.getTradingOrgAccountManagerId());
            person.setCode(entity.getTradingOrgAccountManagerCode());
            person.setName(entity.getTradingOrgAccountManagerName());
            person.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA);
            return person;
        }

        // 資金管理団体責任者
        if (code.equals(entity.getTradingOrgShikinDantaiCode())) {
            person.setId(entity.getTradingOrgShikinDantaiId());
            person.setCode(entity.getTradingOrgShikinDantaiCode());
            person.setName(entity.getTradingOrgShikinDantaiName());
            person.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA);
            return person;
        }

        // 議員1
        if (code.equals(entity.getTradingOrgKokkaiGiin1Code())) {
            person.setId(entity.getTradingOrgKokkaiGiin1Id());
            person.setCode(entity.getTradingOrgKokkaiGiin1Code());
            person.setName(entity.getTradingOrgKokkaiGiin1Name());
            person.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_GIIN1);
            return person;
        }

        // 議員2
        if (code.equals(entity.getTradingOrgKokkaiGiin2Code())) {
            person.setId(entity.getTradingOrgKokkaiGiin2Id());
            person.setCode(entity.getTradingOrgKokkaiGiin2Code());
            person.setName(entity.getTradingOrgKokkaiGiin2Name());
            person.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_GIIN2);
            return person;
        }

        // 議員3
        if (code.equals(entity.getTradingOrgKokkaiGiin3Code())) {
            person.setId(entity.getTradingOrgKokkaiGiin3Id());
            person.setCode(entity.getTradingOrgKokkaiGiin3Code());
            person.setName(entity.getTradingOrgKokkaiGiin3Name());
            person.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_GIIN3);
            return person;
        }

        // どの項目も一致しない場合は一致なし
        return null;
    }

    // 取り引き相手データを設定する
    private void setTradingData(final WkTblUkaiKenkinPickupRouteEntity routeEntity,final RelationPersonWithYakuwariDto judgeDto) {
        
        routeEntity.setTradingRelationPersonId(judgeDto.getId());
        routeEntity.setTradingRelationPersonCode(judgeDto.getCode());
        routeEntity.setTradingRelationPersonName(judgeDto.getName());
        routeEntity.setTradingRelationPersonYakuari(judgeDto.getYakuwari());

    }
}
