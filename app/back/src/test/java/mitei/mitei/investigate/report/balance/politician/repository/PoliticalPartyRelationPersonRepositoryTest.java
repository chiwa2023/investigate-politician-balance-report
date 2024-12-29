package mitei.mitei.investigate.report.balance.politician.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.transaction.Transactional;

/**
 * PoliticalPartyRelationPersonRepository単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PoliticalPartyRelationPersonRepositoryTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PoliticalPartyRelationPersonRepository politicalPartyRelationPersonRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("political_party_relation_person.sql")
    void test() {

        final String text1 = "3件取得";
        final String text2 = "codeが一致";

        Pageable page1 = Pageable.ofSize(3).withPage(0);

        List<Integer> list1 = politicalPartyRelationPersonRepository.findByPoliticalPartyCode(35, page1);

        assertEquals(3, list1.size(), text1);
        assertEquals(144, list1.get(0), text2);
        assertEquals(154, list1.get(1), text2);
        assertEquals(164, list1.get(2), text2);

        Pageable page2 = Pageable.ofSize(3).withPage(2);
        List<Integer> list2 = politicalPartyRelationPersonRepository.findByPoliticalPartyCode(35, page2);

        assertEquals(3, list2.size(), text1);
        assertEquals(204, list2.get(0), text2);
        assertEquals(214, list2.get(1), text2);
        assertEquals(224, list2.get(2), text2);

    }

}
