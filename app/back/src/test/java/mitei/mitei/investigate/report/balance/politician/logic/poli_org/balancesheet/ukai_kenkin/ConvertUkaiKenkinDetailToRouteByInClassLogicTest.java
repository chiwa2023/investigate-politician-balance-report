package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * ConvertUkaiKenkinDetailToRouteByInClassLogic単体テスト
 */
class ConvertUkaiKenkinDetailToRouteByInClassLogicTest {

    @Test
    void test() {
        
        // Repository類使用していないのでboot起動不要
        ConvertUkaiKenkinDetailToRouteByInClassLogic convertUkaiKenkinDetailToRouteByInClassLogic = new ConvertUkaiKenkinDetailToRouteByInClassLogic();

        List<WkTblUkaiKenkinEntity> listDetail = new ArrayList<>();

        // ベースとなる明細Entity
        WkTblUkaiKenkinEntity entitySrc = new WkTblUkaiKenkinEntity();
        entitySrc.setAccrualDate("R4/5.2");
        entitySrc.setAccrualDateValue(LocalDate.of(2022, 5, 4));
        entitySrc.setInsertTimestamp(LocalDateTime.of(2022, 3, 22, 12, 34, 56));
        entitySrc.setInsertUserCode(330);
        entitySrc.setInsertUserId(336L);
        entitySrc.setInsertUserName("操作ユーザ");
        entitySrc.setItemName("寄付受け取り");
        entitySrc.setKingaku(15_001L);
        entitySrc.setPickupStage(2);
        entitySrc.setPoliticalOrgCode(490);
        entitySrc.setPoliticalOrgId(496L);
        entitySrc.setPoliticalOrgName("ちゃらんぽらん政治団体");
        entitySrc.setRenban(22);
        entitySrc.setSaishinKbn(DataHistoryStatusConstants.INSERT.value());
        entitySrc.setTradingPartnerCode(620);
        entitySrc.setTradingPartnerId(626L);
        entitySrc.setTradingPartnerName("関連団体E");
        entitySrc.setTradingPartnerAddress("宮崎県架空市");
        entitySrc.setYoushikiKbn(YoushikiKbn.DONATE);
        entitySrc.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);

        final Integer personCode = 490;
        final Long personId = 499L;
        final String personName = "迂回献金　太郎";

        // cloneして一致データをセットする(取引相手代表者)
        WkTblUkaiKenkinEntity entityDetail00 = this.cloneDetailEntity(entitySrc);
        entityDetail00.setPoliOrgDelegateId(personId);
        entityDetail00.setPoliOrgDelegateCode(personCode);
        entityDetail00.setPoliOrgDelegateName(personName);
        entityDetail00.setTradingPartnerDelegateId(personId);
        entityDetail00.setTradingPartnerDelegateCode(personCode);
        entityDetail00.setTradingPartnerDelegateName(personName);
        listDetail.add(entityDetail00);

        // cloneして一致データをセットする(取引相手会計責任者)
        WkTblUkaiKenkinEntity entityDetail01 = this.cloneDetailEntity(entitySrc);
        entityDetail01.setPoliOrgAccountManagerId(personId);
        entityDetail01.setPoliOrgAccountManagerCode(personCode);
        entityDetail01.setPoliOrgAccountManagerName(personName);
        entityDetail01.setTradingOrgAccountManagerId(personId);
        entityDetail01.setTradingOrgAccountManagerCode(personCode);
        entityDetail01.setTradingOrgAccountManagerName(personName);
        listDetail.add(entityDetail01);

        // cloneして一致データをセットする(取引相手資金管理団体責任者)
        WkTblUkaiKenkinEntity entityDetail02 = this.cloneDetailEntity(entitySrc);
        entityDetail02.setPoliOrgShikinDantaiId(personId);
        entityDetail02.setPoliOrgShikinDantaiCode(personCode);
        entityDetail02.setPoliOrgShikinDantaiName(personName);
        entityDetail02.setTradingOrgShikinDantaiId(personId);
        entityDetail02.setTradingOrgShikinDantaiCode(personCode);
        entityDetail02.setTradingOrgShikinDantaiName(personName);
        listDetail.add(entityDetail02);

        // cloneして一致データをセットする(取引相手国会議員1)
        WkTblUkaiKenkinEntity entityDetail03 = this.cloneDetailEntity(entitySrc);
        entityDetail03.setPoliOrgKokkaiGiin1Id(personId);
        entityDetail03.setPoliOrgKokkaiGiin1Code(personCode);
        entityDetail03.setPoliOrgKokkaiGiin1Name(personName);
        entityDetail03.setTradingOrgKokkaiGiin1Id(personId);
        entityDetail03.setTradingOrgKokkaiGiin1Code(personCode);
        entityDetail03.setTradingOrgKokkaiGiin1Name(personName);
        listDetail.add(entityDetail03);

        // cloneして一致データをセットする(取引相手国会議員2)
        WkTblUkaiKenkinEntity entityDetail04 = this.cloneDetailEntity(entitySrc);
        entityDetail04.setPoliOrgKokkaiGiin2Id(personId);
        entityDetail04.setPoliOrgKokkaiGiin2Code(personCode);
        entityDetail04.setPoliOrgKokkaiGiin2Name(personName);
        entityDetail04.setTradingOrgKokkaiGiin2Id(personId);
        entityDetail04.setTradingOrgKokkaiGiin2Code(personCode);
        entityDetail04.setTradingOrgKokkaiGiin2Name(personName);
        listDetail.add(entityDetail04);

        // cloneして一致データをセットする(取引相手国会議員3)
        WkTblUkaiKenkinEntity entityDetail05 = this.cloneDetailEntity(entitySrc);
        entityDetail05.setPoliOrgKokkaiGiin3Id(personId);
        entityDetail05.setPoliOrgKokkaiGiin3Code(personCode);
        entityDetail05.setPoliOrgKokkaiGiin3Name(personName);
        entityDetail05.setTradingOrgKokkaiGiin3Id(personId);
        entityDetail05.setTradingOrgKokkaiGiin3Code(personCode);
        entityDetail05.setTradingOrgKokkaiGiin3Name(personName);
        listDetail.add(entityDetail05);

        // cloneしてデータをセットする。一致なし
        WkTblUkaiKenkinEntity entityDetail06 = this.cloneDetailEntity(entitySrc);
        entityDetail06.setPoliOrgDelegateId(personId);
        entityDetail06.setPoliOrgDelegateCode(personCode);
        entityDetail06.setPoliOrgDelegateName(personName);
        entityDetail06.setTradingOrgKokkaiGiin3Id(1011L);
        entityDetail06.setTradingOrgKokkaiGiin3Code(1010);
        entityDetail06.setTradingOrgKokkaiGiin3Name("通りすがり　三郎");
        listDetail.add(entityDetail06);


        final String POLI_ID_TEXT = "明細側Idが一致";
        final String POLI_CODE_TEXT = "明細側コードが一致";
        final String POLI_NAME_TEXT = "明細側名称が一致";
        final String POLI_YAKUWARI_TEXT = "明細側役割が一致";
        final String TRADING_ID_TEXT = "比較側Idが一致";
        final String TRADING_CODE_TEXT = "比較側コードが一致";
        final String TRADING_NAME_TEXT = "比較側名称が一致";
        final String TRADING_YAKUWARI_TEXT = "比較側役割が一致";

        // 代表者
        PoliticalOrganizationPropertyEntity propertyEntity00 = this.createPropertyEntity();
        propertyEntity00.setDelegateRelationPersonId(personId);
        propertyEntity00.setDelegateRelationPersonCode(personCode);
        propertyEntity00.setDelegateKoushokuName(personName);
        
        List<WkTblUkaiKenkinPickupRouteEntity> list00 = convertUkaiKenkinDetailToRouteByInClassLogic.practice(listDetail,propertyEntity00);

        
        // 代表者
        WkTblUkaiKenkinPickupRouteEntity entity00 = list00.get(0);
        assertEquals(personCode, entity00.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity00.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity00.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity00.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity00.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity00.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity00.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity00.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

        // 会計責任者
        WkTblUkaiKenkinPickupRouteEntity entity01 = list00.get(1);
        assertEquals(personCode, entity01.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity01.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity01.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity01.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity01.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity01.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity01.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA, entity01.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

        WkTblUkaiKenkinPickupRouteEntity entity02 = list00.get(2);
        assertEquals(personCode, entity02.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity02.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity02.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity02.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity02.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity02.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity02.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA, entity02.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);
        
        
        
        WkTblUkaiKenkinPickupRouteEntity entity03 = list00.get(3);
        assertEquals(personCode, entity03.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity03.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity03.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity03.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity03.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity03.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity03.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN1, entity03.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);
        
        WkTblUkaiKenkinPickupRouteEntity entity04 = list00.get(4);
        assertEquals(personCode, entity04.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity04.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity04.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity04.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity04.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity04.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity04.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN2, entity04.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);
        
        WkTblUkaiKenkinPickupRouteEntity entity05 = list00.get(5);
        assertEquals(personCode, entity05.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity05.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity05.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity05.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity05.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity05.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity05.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN3, entity05.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);
        
        
        // 代表者
        PoliticalOrganizationPropertyEntity propertyEntity01 = this.createPropertyEntity();
        propertyEntity01.setAccountManagerRelationPersonId(personId);
        propertyEntity01.setAccountManagerRelationPersonCode(personCode);
        propertyEntity01.setAccountManagerKoushokuName(personName);
        
        List<WkTblUkaiKenkinPickupRouteEntity> list01 = convertUkaiKenkinDetailToRouteByInClassLogic.practice(listDetail,propertyEntity01);

        // 代表者
        WkTblUkaiKenkinPickupRouteEntity entity10 = list01.get(0);
        assertEquals(personCode, entity10.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity10.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity10.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA, entity10.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity10.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity10.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity10.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity10.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);
        
        
        
        
        
        fail("Not yet implemented");
    }

    
    private PoliticalOrganizationPropertyEntity createPropertyEntity() {
        
        PoliticalOrganizationPropertyEntity propertyEntity = new PoliticalOrganizationPropertyEntity();
        
        final Integer personCode = 29990;
        final Long personId = 29995L;
        final String personName = "一見無関係　太郎";
        
        propertyEntity.setDelegateRelationPersonId(personId);
        propertyEntity.setDelegateRelationPersonCode(personCode);
        propertyEntity.setDelegateKoushokuName(personName);

        propertyEntity.setAccountManagerRelationPersonId(personId);
        propertyEntity.setAccountManagerRelationPersonCode(personCode);
        propertyEntity.setAccountManagerKoushokuName(personName);

        propertyEntity.setShikinDaihyouRelationPersonId(personId);
        propertyEntity.setShikinDaihyouRelationPersonCode(personCode);
        propertyEntity.setShikinDaihyouKoushokuName(personName);

        propertyEntity.setGiin1RelationPersonId(personId);
        propertyEntity.setGiin1RelationPersonCode(personCode);
        propertyEntity.setGiin1KoushokuName(personName);

        propertyEntity.setGiin2RelationPersonId(personId);
        propertyEntity.setGiin2RelationPersonCode(personCode);
        propertyEntity.setGiin2KoushokuName(personName);

        propertyEntity.setGiin3RelationPersonId(personId);
        propertyEntity.setGiin3RelationPersonCode(personCode);
        propertyEntity.setGiin3KoushokuName(personName);
        
        return propertyEntity;
        
    }
    
    // 明細Entityの基本部分のみのCloneを生成する
    private WkTblUkaiKenkinEntity cloneDetailEntity(final WkTblUkaiKenkinEntity enitybase) {
        WkTblUkaiKenkinEntity entity = new WkTblUkaiKenkinEntity();
        BeanUtils.copyProperties(enitybase, entity);

        return entity;
    }

}
