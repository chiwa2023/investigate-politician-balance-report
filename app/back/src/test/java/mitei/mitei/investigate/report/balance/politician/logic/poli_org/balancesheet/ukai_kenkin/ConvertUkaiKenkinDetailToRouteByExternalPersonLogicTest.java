package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * ConvertUkaiKenkinDetailToRouteLogic単体テスト
 */
class ConvertUkaiKenkinDetailToRouteByExternalPersonLogicTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void testByCode() { // NOPMD
        // Repository類を使用していないので、bootを起動しなくても動作
        ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic = new ConvertUkaiKenkinDetailToRouteByExternalPersonLogic();

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

        // 迂回判定の基準関連者
        RelationPersonWithYakuwariDto personDto = new RelationPersonWithYakuwariDto();
        personDto.setId(1048L);
        personDto.setCode(1040);
        personDto.setName("迂回献金　太郎");
        personDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        // cloneして一致データをセットする(代表者)
        WkTblUkaiKenkinEntity entityDetail00 = this.cloneDetailEntity(entitySrc);
        entityDetail00.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail00.setTradingPartnerDelegateId(personDto.getId());
        entityDetail00.setTradingPartnerDelegateCode(personDto.getCode());
        entityDetail00.setTradingPartnerDelegateName(personDto.getName());
        listDetail.add(entityDetail00);

        // cloneして一致データをセットする(会計責任者)
        WkTblUkaiKenkinEntity entityDetail01 = this.cloneDetailEntity(entitySrc);
        entityDetail01.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail01.setTradingOrgAccountManagerId(personDto.getId());
        entityDetail01.setTradingOrgAccountManagerCode(personDto.getCode());
        entityDetail01.setTradingOrgAccountManagerName(personDto.getName());
        listDetail.add(entityDetail01);

        // cloneして一致データをセットする(資金管理団体責任者)
        WkTblUkaiKenkinEntity entityDetail02 = this.cloneDetailEntity(entitySrc);
        entityDetail02.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail02.setTradingOrgShikinDantaiId(personDto.getId());
        entityDetail02.setTradingOrgShikinDantaiCode(personDto.getCode());
        entityDetail02.setTradingOrgShikinDantaiName(personDto.getName());
        listDetail.add(entityDetail02);

        // cloneして一致データをセットする(国会議員1)
        WkTblUkaiKenkinEntity entityDetail03 = this.cloneDetailEntity(entitySrc);
        entityDetail03.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail03.setTradingOrgKokkaiGiin1Id(personDto.getId());
        entityDetail03.setTradingOrgKokkaiGiin1Code(personDto.getCode());
        entityDetail03.setTradingOrgKokkaiGiin1Name(personDto.getName());
        listDetail.add(entityDetail03);

        // cloneして一致データをセットする(国会議員2)
        WkTblUkaiKenkinEntity entityDetail04 = this.cloneDetailEntity(entitySrc);
        entityDetail04.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail04.setTradingOrgKokkaiGiin2Id(personDto.getId());
        entityDetail04.setTradingOrgKokkaiGiin2Code(personDto.getCode());
        entityDetail04.setTradingOrgKokkaiGiin2Name(personDto.getName());
        listDetail.add(entityDetail04);

        // cloneして一致データをセットする(国会議員3)
        WkTblUkaiKenkinEntity entityDetail05 = this.cloneDetailEntity(entitySrc);
        entityDetail05.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail05.setTradingOrgKokkaiGiin3Id(personDto.getId());
        entityDetail05.setTradingOrgKokkaiGiin3Code(personDto.getCode());
        entityDetail05.setTradingOrgKokkaiGiin3Name(personDto.getName());
        listDetail.add(entityDetail05);

        // cloneして一致データをセットする(取引相手・ただし個人取引データでない場合は無視)
        WkTblUkaiKenkinEntity entityDetail06 = this.cloneDetailEntity(entitySrc);
        entityDetail06.setYoushikiEdaKbn(YoushikiEdaKbn.SEIJI_DANTAI);
        entityDetail06.setTradingPartnerId(1048L); // なぜだか取引相手の政治団Idまで一致
        entityDetail06.setTradingPartnerCode(1040); // たまたま取引相手の政治団体同一識別コードが一致
        entityDetail06.setTradingPartnerName("強欲政治団体");
        listDetail.add(entityDetail06);

        // cloneして一致データをセットする(取引相手・個人)
        WkTblUkaiKenkinEntity entityDetail07 = this.cloneDetailEntity(entitySrc);
        entityDetail07.setYoushikiEdaKbn(YoushikiEdaKbn.KOJIN);
        entityDetail07.setTradingPartnerId(personDto.getId());
        entityDetail07.setTradingPartnerCode(personDto.getCode());
        entityDetail07.setTradingPartnerName(personDto.getName());
        listDetail.add(entityDetail07);

        List<WkTblUkaiKenkinPickupRouteEntity> list = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                .practice(listDetail, personDto);

        // 責任者一致
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

        final String POLI_ID_TEXT = "明細側Idが一致";
        final String POLI_CODE_TEXT = "明細側コードが一致";
        final String POLI_NAME_TEXT = "明細側名称が一致";
        final String POLI_YAKUWARI_TEXT = "明細側役割が一致";
        final String TRADING_ID_TEXT = "比較側Idが一致";
        final String TRADING_CODE_TEXT = "比較側コードが一致";
        final String TRADING_NAME_TEXT = "比較側名称が一致";
        final String TRADING_YAKUWARI_TEXT = "比較側役割が一致";

        // 代表者一致
        assertEquals(personDto.getCode(), entity00.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity00.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity00.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity00.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity00.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity00.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity00.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity00.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 会計責任者一致
        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        // 一致として出されたロジック
        assertEquals(personDto.getCode(), entity01.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity01.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity01.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA,
                entity01.getPoliOrgRelationPersonYakuari(), POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity01.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity01.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity01.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity01.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 資金管理団体責任者一致
        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        // 一致として出されたロジック
        assertEquals(personDto.getCode(), entity02.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity02.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity02.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA,
                entity02.getPoliOrgRelationPersonYakuari(), POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity02.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity02.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity02.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity02.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 国会議員1一致
        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        // 一致として出されたロジック
        assertEquals(personDto.getCode(), entity03.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity03.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity03.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN1, entity03.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity03.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity03.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity03.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity03.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 国会議員2一致
        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        // 一致として出されたロジック
        assertEquals(personDto.getCode(), entity04.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity04.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity04.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN2, entity04.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity04.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity04.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity04.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity04.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 国会議員3一致
        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        // 一致として出されたロジック
        assertEquals(personDto.getCode(), entity05.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity05.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity05.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN3, entity05.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity05.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity05.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity05.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity05.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

        // 個人寄付データかつ取引相手
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

        // 個人寄付データかつ取引相手
        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        // 一致として出されたロジック
        assertEquals(personDto.getCode(), entity07.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(personDto.getId(), entity07.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals(personDto.getName(), entity07.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI, entity07.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(personDto.getCode(), entity07.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(personDto.getId(), entity07.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals(personDto.getName(), entity07.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(personDto.getYakuwari(), entity07.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

    }

    @Test
    void testByNameAndAddress() {

        fail("Not yet implemented");
    }

    // 明細Entityの基本部分のみのCloneを生成する
    private WkTblUkaiKenkinEntity cloneDetailEntity(final WkTblUkaiKenkinEntity enitybase) {
        WkTblUkaiKenkinEntity entity = new WkTblUkaiKenkinEntity();
        BeanUtils.copyProperties(enitybase, entity);

        return entity;
    }
}
