package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
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
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void testCode() { // NOPMD
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

        // cloneして一致データをセットする(記載団体代表者と取引相手代表者)
        WkTblUkaiKenkinEntity entityDetail00 = this.cloneDetailEntity(entitySrc);
        entityDetail00.setPoliOrgDelegateId(personId);
        entityDetail00.setPoliOrgDelegateCode(personCode);
        entityDetail00.setPoliOrgDelegateName(personName);
        entityDetail00.setTradingPartnerDelegateId(personId);
        entityDetail00.setTradingPartnerDelegateCode(personCode);
        entityDetail00.setTradingPartnerDelegateName(personName);
        listDetail.add(entityDetail00);

        // cloneして一致データをセットする(記載団体会計責任者と取引相手会計責任者)
        WkTblUkaiKenkinEntity entityDetail01 = this.cloneDetailEntity(entitySrc);
        entityDetail01.setPoliOrgAccountManagerId(personId);
        entityDetail01.setPoliOrgAccountManagerCode(personCode);
        entityDetail01.setPoliOrgAccountManagerName(personName);
        entityDetail01.setTradingOrgAccountManagerId(personId);
        entityDetail01.setTradingOrgAccountManagerCode(personCode);
        entityDetail01.setTradingOrgAccountManagerName(personName);
        listDetail.add(entityDetail01);

        // cloneして一致データをセットする(記載団体資金管理団体責任者と取引相手資金管理団体責任者)
        WkTblUkaiKenkinEntity entityDetail02 = this.cloneDetailEntity(entitySrc);
        entityDetail02.setPoliOrgShikinDantaiId(personId);
        entityDetail02.setPoliOrgShikinDantaiCode(personCode);
        entityDetail02.setPoliOrgShikinDantaiName(personName);
        entityDetail02.setTradingOrgShikinDantaiId(personId);
        entityDetail02.setTradingOrgShikinDantaiCode(personCode);
        entityDetail02.setTradingOrgShikinDantaiName(personName);
        listDetail.add(entityDetail02);

        // cloneして一致データをセットする(記載団体国会議員1と取引相手国会議員1)
        WkTblUkaiKenkinEntity entityDetail03 = this.cloneDetailEntity(entitySrc);
        entityDetail03.setPoliOrgKokkaiGiin1Id(personId);
        entityDetail03.setPoliOrgKokkaiGiin1Code(personCode);
        entityDetail03.setPoliOrgKokkaiGiin1Name(personName);
        entityDetail03.setTradingOrgKokkaiGiin1Id(personId);
        entityDetail03.setTradingOrgKokkaiGiin1Code(personCode);
        entityDetail03.setTradingOrgKokkaiGiin1Name(personName);
        listDetail.add(entityDetail03);

        // cloneして一致データをセットする(記載団体国会議員2と取引相手国会議員2)
        WkTblUkaiKenkinEntity entityDetail04 = this.cloneDetailEntity(entitySrc);
        entityDetail04.setPoliOrgKokkaiGiin2Id(personId);
        entityDetail04.setPoliOrgKokkaiGiin2Code(personCode);
        entityDetail04.setPoliOrgKokkaiGiin2Name(personName);
        entityDetail04.setTradingOrgKokkaiGiin2Id(personId);
        entityDetail04.setTradingOrgKokkaiGiin2Code(personCode);
        entityDetail04.setTradingOrgKokkaiGiin2Name(personName);
        listDetail.add(entityDetail04);

        // cloneして一致データをセットする(記載団体国会議員3と取引相手国会議員3)
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

        // cloneしてデータをセットする。記載団体代表者と取引相手国会議員3の場合それぞれのデータで一致が出る
        WkTblUkaiKenkinEntity entityDetail07 = this.cloneDetailEntity(entitySrc);
        entityDetail07.setPoliOrgDelegateId(personId);
        entityDetail07.setPoliOrgDelegateCode(personCode);
        entityDetail07.setPoliOrgDelegateName(personName);
        entityDetail07.setTradingOrgKokkaiGiin3Id(personId);
        entityDetail07.setTradingOrgKokkaiGiin3Code(personCode);
        entityDetail07.setTradingOrgKokkaiGiin3Name(personName);
        listDetail.add(entityDetail07);

        final String POLI_ID_TEXT = "明細側Idが一致";
        final String POLI_CODE_TEXT = "明細側コードが一致";
        final String POLI_NAME_TEXT = "明細側名称が一致";
        final String POLI_YAKUWARI_TEXT = "明細側役割が一致";
        final String TRADING_ID_TEXT = "比較側Idが一致";
        final String TRADING_CODE_TEXT = "比較側コードが一致";
        final String TRADING_NAME_TEXT = "比較側名称が一致";
        final String TRADING_YAKUWARI_TEXT = "比較側役割が一致";

        PoliticalOrganizationPropertyEntity propertyEntity = new PoliticalOrganizationPropertyEntity();
        propertyEntity.setDelegateRelationPersonId(personId);
        propertyEntity.setDelegateRelationPersonCode(personCode);
        propertyEntity.setDelegateKoushokuName(personName);

        List<WkTblUkaiKenkinPickupRouteEntity> list = convertUkaiKenkinDetailToRouteByInClassLogic.practice(listDetail,
                propertyEntity);

        // 代表者一致
        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);

        // 共通ロジック
        assertEquals(entitySrc.getAccrualDate(), entity00.getAccrualDate(), "発生日が一致");
        assertEquals(entitySrc.getAccrualDateValue(), entity00.getAccrualDateValue(), "発生日実値が一致");
        assertEquals(entitySrc.getInsertTimestamp(), entity00.getInsertTimestamp(), "挿入時間が一致");
        assertEquals(entitySrc.getInsertUserCode(), entity00.getInsertUserCode(), "挿入ユーザコードが一致");
        assertEquals(entitySrc.getInsertUserId(), entity00.getInsertUserId(), "挿入ユーザIdが一致");
        assertEquals(entitySrc.getInsertUserName(), entity00.getInsertUserName(), "挿入ユーザ名が一致");
        assertEquals(entitySrc.getItemName(), entity00.getItemName(), "項目が一致");
        assertEquals(entitySrc.getKingaku(), entity00.getKingaku(), "金額が一致");
        assertEquals(entitySrc.getPickupStage(), entity00.getPickupStage(), "抽出階層が一致");
        assertEquals(entitySrc.getPoliticalOrgCode(), entity00.getPoliticalOrgCode(), "記載政治団体コードが一致");
        assertEquals(entitySrc.getPoliticalOrgId(), entity00.getPoliticalOrgId(), "記載政治団体Idが一致");
        assertEquals(entitySrc.getPoliticalOrgName(), entity00.getPoliticalOrgName(), "記載政治団体名称が一致");
        assertEquals(entitySrc.getRenban(), entity00.getRenban(), "連番が一致");
        assertEquals(entitySrc.getSaishinKbn(), entity00.getSaishinKbn(), "最新区分が一致");
        assertEquals(entitySrc.getTradingPartnerCode(), entity00.getTradingPartnerCode(), "取引相手コードが一致");
        assertEquals(entitySrc.getTradingPartnerId(), entity00.getTradingPartnerId(), "取引相手idが一致");
        assertEquals(entitySrc.getTradingPartnerName(), entity00.getTradingPartnerName(), "取引相手名称が一致");
        assertEquals(entitySrc.getTradingPartnerAddress(), entity00.getTradingPartnerAddress(), "取引相手住所が一致");

        // 代表者一致
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

        // 会計責任者一致
        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals(personCode, entity01.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity01.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity01.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity01.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity01.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity01.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity01.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA,
                entity01.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 資金管理団体責任者一致
        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals(personCode, entity02.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity02.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity02.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity02.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity02.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity02.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity02.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA,
                entity02.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 国会議員1一致
        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
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

        // 国会議員2一致
        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
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

        // 国会議員3一致
        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
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

        // 一致データが存在しない
        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        final Long INIT_LONG = 0L;
        final Integer INIT_INTEGER = 0;
        final String INIT_STRING = "";

        // 一致しないので初期値がそのまま出る
        assertEquals(INIT_INTEGER, entity06.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(INIT_LONG, entity06.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(INIT_STRING, entity06.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(INIT_STRING, entity06.getPoliOrgRelationPersonYakuari(), POLI_YAKUWARI_TEXT);
        assertEquals(INIT_INTEGER, entity06.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(INIT_LONG, entity06.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(INIT_STRING, entity06.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(INIT_STRING, entity06.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        List<WkTblUkaiKenkinEntity> listDetail2 = new ArrayList<>();
        listDetail2.add(entityDetail00);

        // 参照元が会計責任者
        PoliticalOrganizationPropertyEntity propertyEntityAccount = new PoliticalOrganizationPropertyEntity();
        propertyEntityAccount.setAccountManagerRelationPersonId(personId);
        propertyEntityAccount.setAccountManagerRelationPersonCode(personCode);
        propertyEntityAccount.setAccountManagerKoushokuName(personName);

        List<WkTblUkaiKenkinPickupRouteEntity> listAccount = convertUkaiKenkinDetailToRouteByInClassLogic
                .practice(listDetail2, propertyEntityAccount);

        WkTblUkaiKenkinPickupRouteEntity entity11 = listAccount.get(0);
        assertEquals(personCode, entity11.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity11.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity11.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA,
                entity11.getPoliOrgRelationPersonYakuari(), POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity11.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity11.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity11.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity11.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

        // 参照元が資金管理団体責任者
        PoliticalOrganizationPropertyEntity propertyEntityShikinDaihyosha = new PoliticalOrganizationPropertyEntity();
        propertyEntityShikinDaihyosha.setShikinDaihyouRelationPersonId(personId);
        propertyEntityShikinDaihyosha.setShikinDaihyouRelationPersonCode(personCode);
        propertyEntityShikinDaihyosha.setShikinDaihyouKoushokuName(personName);

        List<WkTblUkaiKenkinPickupRouteEntity> listShikin = convertUkaiKenkinDetailToRouteByInClassLogic
                .practice(listDetail2, propertyEntityShikinDaihyosha);
        WkTblUkaiKenkinPickupRouteEntity entity21 = listShikin.get(0);
        assertEquals(personCode, entity21.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity21.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity21.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA,
                entity21.getPoliOrgRelationPersonYakuari(), POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity21.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity21.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity21.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity21.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

        // 参照元が国会議員1
        PoliticalOrganizationPropertyEntity propertyEntityGin1 = new PoliticalOrganizationPropertyEntity();
        propertyEntityGin1.setGiin1RelationPersonId(personId);
        propertyEntityGin1.setGiin1RelationPersonCode(personCode);
        propertyEntityGin1.setGiin1KoushokuName(personName);
        List<WkTblUkaiKenkinPickupRouteEntity> listGin1 = convertUkaiKenkinDetailToRouteByInClassLogic
                .practice(listDetail2, propertyEntityGin1);
        WkTblUkaiKenkinPickupRouteEntity entity31 = listGin1.get(0);
        assertEquals(personCode, entity31.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity31.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity31.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN1, entity31.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity31.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity31.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity31.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity31.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

        // 参照元が国会議員2
        PoliticalOrganizationPropertyEntity propertyEntityGin2 = new PoliticalOrganizationPropertyEntity();
        propertyEntityGin2.setGiin2RelationPersonId(personId);
        propertyEntityGin2.setGiin2RelationPersonCode(personCode);
        propertyEntityGin2.setGiin2KoushokuName(personName);
        List<WkTblUkaiKenkinPickupRouteEntity> listGin2 = convertUkaiKenkinDetailToRouteByInClassLogic
                .practice(listDetail2, propertyEntityGin2);
        WkTblUkaiKenkinPickupRouteEntity entity32 = listGin2.get(0);
        assertEquals(personCode, entity32.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity32.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity32.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN2, entity32.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity32.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity32.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity32.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity32.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

        // 参照元が国会議員3
        PoliticalOrganizationPropertyEntity propertyEntityGin3 = new PoliticalOrganizationPropertyEntity();
        propertyEntityGin3.setGiin3RelationPersonId(personId);
        propertyEntityGin3.setGiin3RelationPersonCode(personCode);
        propertyEntityGin3.setGiin3KoushokuName(personName);
        List<WkTblUkaiKenkinPickupRouteEntity> listGin3 = convertUkaiKenkinDetailToRouteByInClassLogic
                .practice(listDetail2, propertyEntityGin3);
        WkTblUkaiKenkinPickupRouteEntity entity33 = listGin3.get(0);
        assertEquals(personCode, entity33.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personId, entity33.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personName, entity33.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN3, entity33.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personCode, entity33.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personId, entity33.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personName, entity33.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity33.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);

    }

    @Test
    void testName() {
        fail("Not yet implemented");
    }

    // 明細Entityの基本部分のみのCloneを生成する
    private WkTblUkaiKenkinEntity cloneDetailEntity(final WkTblUkaiKenkinEntity enitybase) {
        WkTblUkaiKenkinEntity entity = new WkTblUkaiKenkinEntity();
        BeanUtils.copyProperties(enitybase, entity);

        return entity;
    }

}
