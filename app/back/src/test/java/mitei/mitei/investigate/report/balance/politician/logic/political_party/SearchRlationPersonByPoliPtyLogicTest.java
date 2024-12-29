package mitei.mitei.investigate.report.balance.politician.logic.political_party;

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

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchPoliPartyRelationPersonResultDto;

/**
 * SearchRlationPersonByPoliPtyLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRlationPersonByPoliPtyLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRlationPersonByPoliPtyLogic searchRlationPersonByPoliPtyLogic;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("political_party_relation_person.sql")
    void test() {

        int poliPartyCode = 35;
        SearchPoliPartyRelationPersonResultDto resultDto1 = searchRlationPersonByPoliPtyLogic.practice(poliPartyCode,
                0);
        assertEquals(12, resultDto1.getCountAll(), "全件12件");
        assertEquals(0, resultDto1.getPosPage(), "指定ページと一致");

        List<Integer> list1 = resultDto1.getListPerson();

        final String text1 = "3件取得";
        final String text2 = "codeが一致";

        assertEquals(12, list1.size(), text1);
        assertEquals(144, list1.get(0), text2);
        assertEquals(154, list1.get(1), text2);
        assertEquals(164, list1.get(2), text2);
    }

}
