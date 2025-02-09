package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;

/**
 * GetSamePersonPoliOrgByCodeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetSamePersonPoliOrgByCodeLogicTest {

    /** テスト対象 */
    @Autowired
    private GetSamePersonPoliOrgByCodeLogic getSamePersonPoliOrgByCodeLogic;

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    @Test
    @Sql("../ukai_kenkin/political_organization_property.sql")
    void test() throws Exception {
        // CHECKSTYLE:OFF

        // すべての関連者に何らかの値が入っていた場合
        PoliticalOrganizationPropertyEntity propertyEntity00 = politicalOrganizationPropertyRepository.findById(765L)
                .get();
        List<Integer> list00 = getSamePersonPoliOrgByCodeLogic.paractice(propertyEntity00);
        list00.sort((i1, i2) -> i1 - i2);

        assertEquals(7, list00.size(), "7件取得");
        assertEquals(100, list00.get(0), "取得データ1");
        assertEquals(1020, list00.get(1), "代表者同一1");
        assertEquals(1030, list00.get(2), "会計責任者同一1");
        assertEquals(1040, list00.get(3), "資金管理団体責任者同一1");
        assertEquals(1050, list00.get(4), "国会議員1同一1");
        assertEquals(1060, list00.get(5), "国会議員2同一1");
        assertEquals(1070, list00.get(6), "国会議員3同一1");

        // 団体代表者と会計責任者以外記載なし(代表者と会計責任者が記載されていないことは想定しなくていい)
        PoliticalOrganizationPropertyEntity propertyEntity01 = politicalOrganizationPropertyRepository.findById(884L)
                .get();
        List<Integer> list01 = getSamePersonPoliOrgByCodeLogic.paractice(propertyEntity01);
        list01.sort((i1, i2) -> i1 - i2);

        assertEquals(3, list01.size(), "3件取得");
        assertEquals(2010, list01.get(0), "代表者同一2");
        assertEquals(2020, list01.get(1), "会計責任者同一2");
        assertEquals(11_390, list01.get(2), "取得データ2");

    }

}
