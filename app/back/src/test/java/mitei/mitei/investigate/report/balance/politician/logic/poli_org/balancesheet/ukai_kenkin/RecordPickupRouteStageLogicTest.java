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
 * RecordPickupRouteStageLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql({ "wktbl_meisai_inseat_sample.sql", "wktbl_ukai_kenkin_route_insert_sample.sql" })
class RecordPickupRouteStageLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RecordPickupRouteStageLogic recordPickupRouteStageLogic;

    /** 迂回献金明細ワークテーブルRepository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testProperty1() {
        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(89L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(2, list.size(), "2件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体11", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体11", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("関連団体A", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");
    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari1() {
        Integer userCode = 987;

        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(89L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(2, list.size(), "2件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体11", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体11", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("関連団体A", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");
    }

    @Test
    @Tag("TableTruncate")
    void testProperty2() {
        // CHECKSTYLE:OFF

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(90L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(3, list.size(), "3件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体12", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体12", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("迂回団体12", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("迂回団体12", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("関連団体B", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari2() {
        // CHECKSTYLE:OFF

        Integer userCode = 987;

        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(90L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(3, list.size(), "3件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体12", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体12", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("迂回団体12", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("迂回団体12", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("関連団体B", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

    }

    @Test
    @Tag("TableTruncate")
    void testProperty3() {

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(91L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(4, list.size(), "4件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体13", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体13", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体23", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体23", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("迂回団体23", entity02.getTradingPartnerName(), "取り引き相手が一致2"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("迂回団体23", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("関連団体C", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari3() {

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(91L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(4, list.size(), "4件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体13", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体13", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体23", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体23", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("迂回団体23", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("迂回団体23", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("関連団体C", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

    }

    @Test
    @Tag("TableTruncate")
    void testProperty4() {

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(92L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(5, list.size(), "5件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体14", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体14", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体24", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体24", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体34", entity02.getTradingPartnerName(), "取り引き相手が一致2"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体34", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("迂回団体34", entity03.getTradingPartnerName(), "取り引き相手が一致3"); // NOPMD
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("迂回団体34", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("関連団体D", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari4() {

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(92L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(5, list.size(), "5件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体14", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体14", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体24", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体24", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体34", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体34", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("迂回団体34", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("迂回団体34", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("関連団体D", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

    }

    @Test
    @Tag("TableTruncate")
    void testProperty5() {

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(93L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(7, list.size(), "7件取得(最終の迂回先・余分な分岐含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体15", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体15", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体25", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体25", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体35", entity02.getTradingPartnerName(), "取り引き相手が一致22"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体35", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体45", entity03.getTradingPartnerName(), "取り引き相手が一致3"); // NOPMD
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体45", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("迂回団体45", entity04.getTradingPartnerName(), "取り引き相手が一致4"); // NOPMD
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(6);
        assertEquals("迂回団体45", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("関連団体E", entity05.getTradingPartnerName(), "取り引き相手が一致5");
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");
    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari5() {

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(93L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(7, list.size(), "7件取得(最終の迂回先・余分な分岐含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体15", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体15", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体25", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体25", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体35", entity02.getTradingPartnerName(), "取り引き相手が一致22");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体35", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体45", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体45", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("迂回団体45", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(6);
        assertEquals("迂回団体45", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("関連団体E", entity05.getTradingPartnerName(), "取り引き相手が一致5");
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");
    }

    @Test
    @Tag("TableTruncate")
    void testProperty6() {
        // CHECKSTYLE:OFF

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(94L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(7, list.size(), "7件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体16", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体16", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体26", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体26", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体36", entity02.getTradingPartnerName(), "取り引き相手が一致2"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体36", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体46", entity03.getTradingPartnerName(), "取り引き相手が一致3"); // NOPMD
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体46", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体56", entity04.getTradingPartnerName(), "取り引き相手が一致4"); // NOPMD
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体56", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("迂回団体56", entity05.getTradingPartnerName(), "取り引き相手が一致5"); // NOPMD
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("迂回団体56", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("関連団体F", entity06.getTradingPartnerName(), "取り引き相手が一致6");
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari6() {
        // CHECKSTYLE:OFF

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(94L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(7, list.size(), "7件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体16", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体16", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体26", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体26", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体36", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体36", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体46", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体46", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体56", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体56", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("迂回団体56", entity05.getTradingPartnerName(), "取り引き相手が一致5");
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("迂回団体56", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("関連団体F", entity06.getTradingPartnerName(), "取り引き相手が一致6");
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

    }

    @Test
    @Tag("TableTruncate")
    void testProperty7() {
        // CHECKSTYLE:OFF

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(95L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(8, list.size(), "8件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体17", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体17", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体27", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体27", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体37", entity02.getTradingPartnerName(), "取り引き相手が一致2"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体37", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体47", entity03.getTradingPartnerName(), "取り引き相手が一致3"); // NOPMD
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体47", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体57", entity04.getTradingPartnerName(), "取り引き相手が一致4"); // NOPMD
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体57", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("中間団体67", entity05.getTradingPartnerName(), "取り引き相手が一致5"); // NOPMD
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("中間団体67", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("迂回団体67", entity06.getTradingPartnerName(), "取り引き相手が一致6"); // NOPMD
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals("迂回団体67", entity07.getPoliticalOrgName(), "記載政治団体が一致7");
        assertEquals("関連団体G", entity07.getTradingPartnerName(), "取り引き相手が一致7");
        assertEquals(7, entity07.getPickupStage(), "階層が一致7");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari7() {
        // CHECKSTYLE:OFF

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(95L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(8, list.size(), "8件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体17", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体17", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体27", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体27", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体37", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体37", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体47", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体47", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体57", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体57", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("中間団体67", entity05.getTradingPartnerName(), "取り引き相手が一致5");
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("中間団体67", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("迂回団体67", entity06.getTradingPartnerName(), "取り引き相手が一致6");
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals("迂回団体67", entity07.getPoliticalOrgName(), "記載政治団体が一致7");
        assertEquals("関連団体G", entity07.getTradingPartnerName(), "取り引き相手が一致7");
        assertEquals(7, entity07.getPickupStage(), "階層が一致7");

    }

    @Test
    @Tag("TableTruncate")
    void testProperty8() {

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(96L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

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

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

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

    @Test
    @Tag("TableTruncate")
    void testProperty9() {

        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(97L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, propertyEntity);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(10, list.size(), "10件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体19", entity00.getTradingPartnerName(), "取り引き相手が一致0"); // NOPMD
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体19", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体29", entity01.getTradingPartnerName(), "取り引き相手が一致1"); // NOPMD
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体29", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体39", entity02.getTradingPartnerName(), "取り引き相手が一致2"); // NOPMD
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体39", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体49", entity03.getTradingPartnerName(), "取り引き相手が一致3"); // NOPMD
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体49", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体59", entity04.getTradingPartnerName(), "取り引き相手が一致4"); // NOPMD
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体59", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("中間団体69", entity05.getTradingPartnerName(), "取り引き相手が一致5"); // NOPMD
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("中間団体69", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("中間団体79", entity06.getTradingPartnerName(), "取り引き相手が一致6"); // NOPMD
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals("中間団体79", entity07.getPoliticalOrgName(), "記載政治団体が一致7");
        assertEquals("中間団体89", entity07.getTradingPartnerName(), "取り引き相手が一致7"); // NOPMD
        assertEquals(7, entity07.getPickupStage(), "階層が一致7");

        WkTblUkaiKenkinPickupRouteEntity entity08 = list.get(8);
        assertEquals("中間団体89", entity08.getPoliticalOrgName(), "記載政治団体が一致8");
        assertEquals("迂回団体89", entity08.getTradingPartnerName(), "取り引き相手が一致8"); // NOPMD
        assertEquals(8, entity08.getPickupStage(), "階層が一致8");

        WkTblUkaiKenkinPickupRouteEntity entity09 = list.get(9);
        assertEquals("迂回団体89", entity09.getPoliticalOrgName(), "記載政治団体が一致9");
        assertEquals("関連団体I", entity09.getTradingPartnerName(), "取り引き相手が一致9");
        assertEquals(9, entity09.getPickupStage(), "階層が一致9");

    }

    @Test
    @Tag("TableTruncate")
    void testYakuwari9() {

        Integer userCode = 987;
        // 45,40,ABCD代表者 太郎
        RelationPersonWithYakuwariDto personWithYakuwariDto = new RelationPersonWithYakuwariDto();
        personWithYakuwariDto.setId(45L);
        personWithYakuwariDto.setCode(40);
        personWithYakuwariDto.setName("ABCD太郎");
        personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(97L).get();

        recordPickupRouteStageLogic.practice(userCode, entityRoute, personWithYakuwariDto);

        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
                        entityRoute.getWkTblUkaiKenkinPickupRouteCode());

        assertEquals(10, list.size(), "10件取得(最終の迂回先含む)");

        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "記載政治団体が一致0");
        assertEquals("中間団体19", entity00.getTradingPartnerName(), "取り引き相手が一致0");
        assertEquals(0, entity00.getPickupStage(), "階層が一致0");

        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals("中間団体19", entity01.getPoliticalOrgName(), "記載政治団体が一致1");
        assertEquals("中間団体29", entity01.getTradingPartnerName(), "取り引き相手が一致1");
        assertEquals(1, entity01.getPickupStage(), "階層が一致1");

        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals("中間団体29", entity02.getPoliticalOrgName(), "記載政治団体が一致2");
        assertEquals("中間団体39", entity02.getTradingPartnerName(), "取り引き相手が一致2");
        assertEquals(2, entity02.getPickupStage(), "階層が一致2");

        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals("中間団体39", entity03.getPoliticalOrgName(), "記載政治団体が一致3");
        assertEquals("中間団体49", entity03.getTradingPartnerName(), "取り引き相手が一致3");
        assertEquals(3, entity03.getPickupStage(), "階層が一致3");

        WkTblUkaiKenkinPickupRouteEntity entity04 = list.get(4);
        assertEquals("中間団体49", entity04.getPoliticalOrgName(), "記載政治団体が一致4");
        assertEquals("中間団体59", entity04.getTradingPartnerName(), "取り引き相手が一致4");
        assertEquals(4, entity04.getPickupStage(), "階層が一致4");

        WkTblUkaiKenkinPickupRouteEntity entity05 = list.get(5);
        assertEquals("中間団体59", entity05.getPoliticalOrgName(), "記載政治団体が一致5");
        assertEquals("中間団体69", entity05.getTradingPartnerName(), "取り引き相手が一致5");
        assertEquals(5, entity05.getPickupStage(), "階層が一致5");

        WkTblUkaiKenkinPickupRouteEntity entity06 = list.get(6);
        assertEquals("中間団体69", entity06.getPoliticalOrgName(), "記載政治団体が一致6");
        assertEquals("中間団体79", entity06.getTradingPartnerName(), "取り引き相手が一致6");
        assertEquals(6, entity06.getPickupStage(), "階層が一致6");

        WkTblUkaiKenkinPickupRouteEntity entity07 = list.get(7);
        assertEquals("中間団体79", entity07.getPoliticalOrgName(), "記載政治団体が一致7");
        assertEquals("中間団体89", entity07.getTradingPartnerName(), "取り引き相手が一致7");
        assertEquals(7, entity07.getPickupStage(), "階層が一致7");

        WkTblUkaiKenkinPickupRouteEntity entity08 = list.get(8);
        assertEquals("中間団体89", entity08.getPoliticalOrgName(), "記載政治団体が一致8");
        assertEquals("迂回団体89", entity08.getTradingPartnerName(), "取り引き相手が一致8");
        assertEquals(8, entity08.getPickupStage(), "階層が一致8");

        WkTblUkaiKenkinPickupRouteEntity entity09 = list.get(9);
        assertEquals("迂回団体89", entity09.getPoliticalOrgName(), "記載政治団体が一致9");
        assertEquals("関連団体I", entity09.getTradingPartnerName(), "取り引き相手が一致9");
        assertEquals(9, entity09.getPickupStage(), "階層が一致9");

    }

}
