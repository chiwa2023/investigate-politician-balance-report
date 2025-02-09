package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * PickupSameRelationPersonDataLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupSameRelationPersonDataLogicTest {
    
    /** テスト対象 */ 
    @Autowired
    private PickupSameRelationPersonDataLogic pickupSameRelationPersonDataLogic;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_ukai_kenkin_pickup_person.sql")
    void test() { // NOPMD
        // CHECKSTYLE:OFF
        
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        
        List<WkTblUkaiKenkinPickupRouteEntity> list = pickupSameRelationPersonDataLogic.practice(privilegeDto.getLoginUserCode());
        
        assertEquals(14, list.size(),"取得件数一致");

        // テーブルId(元データId)確認
        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals(101L, entity00.getTablleId(),"テーブルId一致00");
        
        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals(102L, entity01.getTablleId(),"テーブルId一致01");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals(103L, entity02.getTablleId(),"テーブルId一致02");
        
        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals(104L, entity03.getTablleId(),"テーブルId一致03");
        
        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals(105L, entity04.getTablleId(),"テーブルId一致04");
        
        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals(106L, entity05.getTablleId(),"テーブルId一致05");
        
        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals(107L, entity06.getTablleId(),"テーブルId一致06");
        
        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals(108L, entity07.getTablleId(),"テーブルId一致07");
        
        WkTblUkaiKenkinPickupRouteEntity entity08 = list.get(8);
        assertEquals(201L, entity08.getTablleId(),"テーブルId一致08");
        
        WkTblUkaiKenkinPickupRouteEntity entity09 = list.get(9);
        assertEquals(202L, entity09.getTablleId(),"テーブルId一致09");
        
        WkTblUkaiKenkinPickupRouteEntity entity10 = list.get(10);
        assertEquals(203L, entity10.getTablleId(),"テーブルId一致10");
        
        WkTblUkaiKenkinPickupRouteEntity entity11 = list.get(11);
        assertEquals(204L, entity11.getTablleId(),"テーブルId一致11");
        
        WkTblUkaiKenkinPickupRouteEntity entity12 = list.get(12);
        assertEquals(205L, entity12.getTablleId(),"テーブルId一致12");
        
        WkTblUkaiKenkinPickupRouteEntity entity13 = list.get(13);
        assertEquals(206L, entity13.getTablleId(),"テーブルId一致13");

        
        // 一件だけサンプルとして全変換を確認する
        final String POLI_ID_TEXT = "明細側Idが一致";
        final String POLI_CODE_TEXT = "明細側コードが一致";
        final String POLI_NAME_TEXT = "明細側名称が一致";
        final String POLI_YAKUWARI_TEXT = "明細側役割が一致";
        final String TRADING_ID_TEXT = "比較側Idが一致";
        final String TRADING_CODE_TEXT = "比較側コードが一致";
        final String TRADING_NAME_TEXT = "比較側名称が一致";
        final String TRADING_YAKUWARI_TEXT = "比較側役割が一致";

        assertEquals("R4/9/30", entity00.getAccrualDate(), "発生日が一致");
        assertEquals(LocalDate.of(2022, 9,30), entity00.getAccrualDateValue(), "発生日実値が一致");
        assertEquals(LocalDateTime.of(2025,2,8,16,31,39), entity00.getInsertTimestamp(), "挿入時間が一致");
        assertEquals(987, entity00.getInsertUserCode(), "挿入ユーザコードが一致");
        assertEquals(123_321L, entity00.getInsertUserId(), "挿入ユーザIdが一致");
        assertEquals("ユーザ", entity00.getInsertUserName(), "挿入ユーザ名が一致");
        assertEquals("寄付", entity00.getItemName(), "項目が一致");
        assertEquals(15_001L, entity00.getKingaku(), "金額が一致");
        assertEquals(0, entity00.getPickupStage(), "抽出階層が一致");
        assertEquals(100, entity00.getPoliticalOrgCode(), "記載政治団体コードが一致");
        assertEquals(105L, entity00.getPoliticalOrgId(), "記載政治団体Idが一致");
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体名称が一致");
        assertEquals(3, entity00.getRenban(), "連番が一致");
        assertEquals(DataHistoryStatusConstants.INSERT.value(), entity00.getSaishinKbn(), "最新区分が一致");
        assertEquals(360, entity00.getTradingPartnerCode(), "取引相手コードが一致");
        assertEquals(365L, entity00.getTradingPartnerId(), "取引相手idが一致");
        assertEquals("迂回　献金太郎", entity00.getTradingPartnerName(), "取引相手名称が一致");
        assertEquals("和歌山県実在市", entity00.getTradingPartnerAddress(), "取引相手住所が一致");
        assertEquals(360, entity00.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(365L, entity00.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals("迂回　献金太郎", entity00.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI, entity00.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(360, entity00.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(365L, entity00.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals("迂回　献金太郎", entity00.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA, entity00.getTradingRelationPersonYakuari(), TRADING_YAKUWARI_TEXT);

    }

}
