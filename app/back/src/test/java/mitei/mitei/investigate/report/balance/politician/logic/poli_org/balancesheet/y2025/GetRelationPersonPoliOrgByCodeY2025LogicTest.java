package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;

/**
 * GetSameRelationPersonPoliOrgByCodeY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetRelationPersonPoliOrgByCodeY2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetRelationPersonPoliOrgByCodeY2025Logic getRelationPersonPoliOrgByCodeY2025Logic;

    @Test
    @Tag("TableTruncate")
    @Sql({ "offering_balancesheet_0701_and_0720_surface_ukai_2025.sql",
            "../ukai_kenkin/political_organization_property.sql" })
    void test() throws Exception {

        assertThrows(EmptyResultDataAccessException.class,
                () -> getRelationPersonPoliOrgByCodeY2025Logic.practice(9999), "表紙からidが取得できない");

        assertThrows(EmptyResultDataAccessException.class,
                () -> getRelationPersonPoliOrgByCodeY2025Logic.practice(430), "団体属性から関連者が取得できない");

        PoliticalOrganizationPropertyEntity entity = getRelationPersonPoliOrgByCodeY2025Logic.practice(100);

        assertEquals(105L, entity.getPoliticalOrganizationId(),"政治団体Id");
        assertEquals(100, entity.getPoliticalOrganizationCode(),"政治団体Code");
        assertEquals("ABCD団体", entity.getPoliticalOrganizationName(),"政治団体名称");

        assertEquals(105L, entity.getPoliticalOrganizationId(),"政治団体属性代表者Id");
        assertEquals(100, entity.getPoliticalOrganizationCode(),"政治団体属性代表者Code");
        assertEquals("ABCD団体", entity.getPoliticalOrganizationName(),"政治団体属性代表者名称");


        // 収支報告書記載団体会計責任者
        assertEquals(59L, entity.getAccountManagerRelationPersonId(), "政治団体属性団体会計責任者Idデータ1");
        assertEquals(50, entity.getAccountManagerRelationPersonCode(), "政治団体属性体会計責任者同一識別コードデータ1");
        assertEquals("ABCD会計責任者 良子", entity.getAccountManagerKoushokuName(), "政治団体属性会計責任者名称データ1");

        // 収支報告書資金管理団体登録者
        assertEquals(625L, entity.getShikinDaihyouRelationPersonId(), "政治団体属性資金管理団体登録者Idデータ1");
        assertEquals(620, entity.getShikinDaihyouRelationPersonCode(), "政治団体属性資金管理団体登録者同一識別コードデータ1");
        assertEquals("資金管理団体責任者　三郎", entity.getShikinDaihyouKoushokuName(), "政治団体属性資金管理団体登録者名称データ1");

        // 収支報告書国会議員
        assertEquals(30_225L, entity.getGiin1RelationPersonId(), "政治団体属性国会議員1関連者データ1");
        assertEquals(30_220, entity.getGiin1RelationPersonCode(), "政治団体属性国会議員1関連者同一識別コードデータ1");
        assertEquals("国会議員 1太郎", entity.getGiin1KoushokuName(), "政治団体属性国会議員1関連者名称データ1");
        assertEquals(30_491L, entity.getGiin2RelationPersonId(), "政治団体属性国会議員2関連者Idデータ1");
        assertEquals(30_490, entity.getGiin2RelationPersonCode(), "政治団体属性国会議員2関連者同一識別コードデータ1");
        assertEquals("国会議員 2太郎", entity.getGiin2KoushokuName(), "政治団体属性国会議員2関連者名称データ1");
        assertEquals(30_588L, entity.getGiin3RelationPersonId(), "政治団体属性国会議員3関連者Idデータ1");
        assertEquals(30_580, entity.getGiin3RelationPersonCode(), "政治団体属性国会議員3関連者同一識別コードデータ1");
        assertEquals("国会議員 3太郎", entity.getGiin3KoushokuName(), "政治団体属性国会議員3関連者名称データ1");

    }

}
