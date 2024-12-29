package mitei.mitei.investigate.report.balance.politician.service.political_party;

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

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;

/**
 * SearchPoliticalPartySelectOptionService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPoliticalPartySelectOptionServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPoliticalPartySelectOptionService searchPoliticalPartySelectOptionService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("option_political_party.sql")
    void test() throws Exception {

        List<SelectOptionDto> list1 = searchPoliticalPartySelectOptionService.practice(false);
        assertEquals(4, list1.size(), "取得件数が一致");

        final String textValue = "値が一致";
        final String textText = "表示名が一致";
        SelectOptionDto dto1 = list1.get(0);
        assertEquals("20", dto1.getValue(), textValue);
        assertEquals("のんびり政党", dto1.getText(), textText);

        SelectOptionDto dto2 = list1.get(1);
        assertEquals("30", dto2.getValue(), textValue);
        SelectOptionDto dto3 = list1.get(2);
        assertEquals("40", dto3.getValue(), textValue);
        SelectOptionDto dto4 = list1.get(3);
        assertEquals("50", dto4.getValue(), textValue);

        // 指定なしを追加した場合必ず先頭に追加されていること
        List<SelectOptionDto> list2 = searchPoliticalPartySelectOptionService.practice(true);
        assertEquals(5, list2.size(), "件数が一致");

        SelectOptionDto dtoNoSelect = list2.get(0);
        assertEquals("0", dtoNoSelect.getValue(), textValue);
        assertEquals("指定なし", dtoNoSelect.getText(), textText);

    }
}
