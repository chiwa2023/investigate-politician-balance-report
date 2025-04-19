package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

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
 * GetUsageDocumentCodeOptionY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetUsageDocumentCodeOptionY2024LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetUsageDocumentCodeOptionY2024Logic getUsageDocumentCodeOptionY2024Logic;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("usage_option_2024.sql")
    void test() {

        final int poliOrgCode = 9890;

        List<SelectOptionDto> listOption = getUsageDocumentCodeOptionY2024Logic.practice(poliOrgCode);

        assertEquals(8, listOption.size());

        //2023-03-28  2
        SelectOptionDto option0 = listOption.get(0);
        assertEquals("2", option0.getValue());
        assertEquals("2023-03-28提出分", option0.getText());

        //2023-12-03  9
        SelectOptionDto option1 = listOption.get(1);
        assertEquals("9", option1.getValue());
        assertEquals("2023-12-03提出分", option1.getText());
        
        //2023-12-07  3
        SelectOptionDto option2 = listOption.get(2);
        assertEquals("3", option2.getValue());
        assertEquals("2023-12-07提出分", option2.getText());
        
        //2023-12-11  11
        SelectOptionDto option3 = listOption.get(3);
        assertEquals("11", option3.getValue());
        assertEquals("2023-12-11提出分", option3.getText());
        
        
        //2023-12-14  5
        SelectOptionDto option4 = listOption.get(4);
        assertEquals("5", option4.getValue());
        assertEquals("2023-12-14提出分", option4.getText());
        
        //2023-12-18  10
        SelectOptionDto option5 = listOption.get(5);
        assertEquals("10", option5.getValue());
        assertEquals("2023-12-18提出分", option5.getText());
        
        //2024-01-09  7
        SelectOptionDto option6 = listOption.get(6);
        assertEquals("7", option6.getValue());
        assertEquals("2024-01-09提出分", option6.getText());
        
        //2024-01-26  12
        SelectOptionDto option7 = listOption.get(7);
        assertEquals("12", option7.getValue());
        assertEquals("2024-01-26提出分", option7.getText());
    }

}
