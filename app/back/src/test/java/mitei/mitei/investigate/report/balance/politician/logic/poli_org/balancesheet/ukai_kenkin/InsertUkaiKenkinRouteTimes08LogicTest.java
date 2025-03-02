package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;

/**
 * InsertUkaiKenkinRouteTimes08Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql({ "wktbl_meisai_inseat_sample.sql", "wktbl_ukai_kenkin_route_insert_sample.sql" })
class InsertUkaiKenkinRouteTimes08LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertUkaiKenkinRouteTimes08Logic insertUkaiKenkinRouteTimes08Logic;

    /** 迂回献金明細ワークテーブルRepository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testProperty8() {

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(96L).get();

        insertUkaiKenkinRouteTimes08Logic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(9, list.size(), "9件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体18", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体18", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体28", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体28", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体38", entity02.getTradingPartnerName(), "取り引き相手が一致2"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体38", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体48", entity03.getTradingPartnerName(), "取り引き相手が一致3"); // NOPMD
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体48", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体58", entity04.getTradingPartnerName(), "取り引き相手が一致4"); // NOPMD
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体58", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("中間団体68", entity05.getTradingPartnerName(), "取り引き相手が一致5"); // NOPMD
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("中間団体68", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("中間団体78", entity06.getTradingPartnerName(), "取り引き相手が一致6"); // NOPMD
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals("中間団体78", entity07.getPoliticalOrgName(), "記載政治団体が一致7");
        assertEquals("迂回団体78", entity07.getTradingPartnerName(), "取り引き相手が一致7"); // NOPMD
        assertEquals(7, entity07.getPickupStage(), "階層が一致7");

        WkTblUkaiKenkinPickupRouteEntity entity08 = list.get(8);
        assertEquals("迂回団体78", entity08.getPoliticalOrgName(), "記載政治団体が一致8");
        assertEquals("関連団体H", entity08.getTradingPartnerName(), "取り引き相手が一致8");
        assertEquals(8, entity08.getPickupStage(), "階層が一致8");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari8() {

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(96L).get();

        insertUkaiKenkinRouteTimes08Logic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(9, list.size(), "9件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体18", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体18", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体28", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体28", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体38", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体38", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体48", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体48", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体58", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体58", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("中間団体68", entity05.getTradingPartnerName(), "取り引き相手が一致5");
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("中間団体68", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("中間団体78", entity06.getTradingPartnerName(), "取り引き相手が一致6");
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals("中間団体78", entity07.getPoliticalOrgName(), "記載政治団体が一致7");
        assertEquals("迂回団体78", entity07.getTradingPartnerName(), "取り引き相手が一致7");
        assertEquals(7, entity07.getPickupStage(), "階層が一致7");

        WkTblUkaiKenkinPickupRouteEntity entity08 = list.get(8);
        assertEquals("迂回団体78", entity08.getPoliticalOrgName(), "記載政治団体が一致8");
        assertEquals("関連団体H", entity08.getTradingPartnerName(), "取り引き相手が一致8");
        assertEquals(8, entity08.getPickupStage(), "階層が一致8");

    }

}
