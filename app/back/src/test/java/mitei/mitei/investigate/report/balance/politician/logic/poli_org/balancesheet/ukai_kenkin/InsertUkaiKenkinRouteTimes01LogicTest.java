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
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;

/**
 * InsertUkaiKenkinRouteTimes01Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertUkaiKenkinRouteTimes01LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertUkaiKenkinRouteTimes01Logic insertUkaiKenkinRouteTimes01Logic;

    /** 迂回献金明細ワークテーブルRepository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wktbl_meisai_inseat_sample.sql", "wktbl_ukai_kenkin_route_insert_sample.sql" })
    void test() {
        Integer userCode = 987;
        Integer poliOrg = 100;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrg, DataHistoryStatusConstants.INSERT.value()).get();

        WkTblUkaiKenkinPickupRouteEntity entityRoute = wkTblUkaiKenkinPickupRouteRepository.findById(89L).get();

        insertUkaiKenkinRouteTimes01Logic.practice(userCode, entityRoute, propertyEntity);

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

}
