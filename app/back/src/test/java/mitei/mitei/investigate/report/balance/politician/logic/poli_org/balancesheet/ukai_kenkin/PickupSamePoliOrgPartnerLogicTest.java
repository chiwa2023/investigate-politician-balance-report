package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
 * PickupSamePoliOrgPartnerLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupSamePoliOrgPartnerLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PickupSamePoliOrgPartnerLogic pickupSamePoliOrgPartnerLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_ukai_kenkin_pickup_person.sql")
    void test() {

        // (Taskletではロジックから算出しているがテストなので)抽出したい政治団体コードを直接している
        List<Integer> listPoliOrgCode0 = new ArrayList<>();
        listPoliOrgCode0.add(620);
        listPoliOrgCode0.add(630);
        listPoliOrgCode0.add(640);
        listPoliOrgCode0.add(650);
        listPoliOrgCode0.add(660);
        listPoliOrgCode0.add(670);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        List<WkTblUkaiKenkinPickupRouteEntity> list = pickupSamePoliOrgPartnerLogic
                .practice(privilegeDto.getLoginUserCode(), listPoliOrgCode0,null);

        assertEquals(1, list.size(), "取得サイズ");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        final String POLI_ID_TEXT = "明細側Idが一致";
        final String POLI_CODE_TEXT = "明細側コードが一致";
        final String POLI_NAME_TEXT = "明細側名称が一致";
        final String POLI_YAKUWARI_TEXT = "明細側役割が一致";
        final String TRADING_ID_TEXT = "比較側Idが一致";
        final String TRADING_CODE_TEXT = "比較側コードが一致";
        final String TRADING_NAME_TEXT = "比較側名称が一致";
        final String TRADING_YAKUWARI_TEXT = "比較側役割が一致";

        assertEquals(301L, entity00.getTablleId(), "テーブルIdが一致");
        assertEquals("R4/9/30", entity00.getAccrualDate(), "発生日が一致");
        assertEquals(LocalDate.of(2022, 9, 30), entity00.getAccrualDateValue(), "発生日実値が一致");
        assertEquals(LocalDateTime.of(2025, 2, 8, 16, 31, 39), entity00.getInsertTimestamp(), "挿入時間が一致");
        assertEquals(987, entity00.getInsertUserCode(), "挿入ユーザコードが一致");
        assertEquals(123_321L, entity00.getInsertUserId(), "挿入ユーザIdが一致");
        assertEquals("ユーザ", entity00.getInsertUserName(), "挿入ユーザ名が一致");
        assertEquals("寄付事例21", entity00.getItemName(), "項目が一致");
        assertEquals(15_003L, entity00.getKingaku(), "金額が一致");
        assertEquals(0, entity00.getPickupStage(), "抽出階層が一致");
        assertEquals(100, entity00.getPoliticalOrgCode(), "記載政治団体コードが一致");
        assertEquals(105L, entity00.getPoliticalOrgId(), "記載政治団体Idが一致");
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体名称が一致");
        assertEquals(3, entity00.getRenban(), "連番が一致");
        assertEquals(DataHistoryStatusConstants.INSERT.value(), entity00.getSaishinKbn(), "最新区分が一致");
        assertEquals(660, entity00.getTradingPartnerCode(), "取引相手コードが一致");
        assertEquals(662L, entity00.getTradingPartnerId(), "取引相手idが一致");
        assertEquals("関連団体E", entity00.getTradingPartnerName(), "取引相手名称が一致");
        assertEquals("山形県実在市", entity00.getTradingPartnerAddress(), "取引相手住所が一致");
        assertEquals(30_490, entity00.getPoliOrgRelationPersonCode(), POLI_CODE_TEXT);
        assertEquals(30_491L, entity00.getPoliOrgRelationPersonId(), POLI_ID_TEXT);
        assertEquals("国会議員 2太郎", entity00.getPoliOrgRelationPersonName(), POLI_NAME_TEXT);
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN2, entity00.getPoliOrgRelationPersonYakuari(),
                POLI_YAKUWARI_TEXT);
        assertEquals(30_490, entity00.getTradingRelationPersonCode(), TRADING_CODE_TEXT);
        assertEquals(30_491L, entity00.getTradingRelationPersonId(), TRADING_ID_TEXT);
        assertEquals("国会議員 2太郎", entity00.getTradingRelationPersonName(), TRADING_NAME_TEXT);
        // 名称検索のときは住所も識別条件だがコード検索時は不要
        assertEquals(RelationPersonYakuwariConstants.YAKUWARI_GIIN2, entity00.getTradingRelationPersonYakuari(),
                TRADING_YAKUWARI_TEXT);
    }

}
