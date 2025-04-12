package mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.SearchDocumentCodeByOfferingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * GetBalanceDocumentCodeOptionLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetBalanceDocumentCodeOptionLogicTest {

    /** テスト対象 */
    @Autowired
    private GetBalanceDocumentCodeOptionLogic getBalanceDocumentCodeOptionLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../poli_org/balancesheet/y2022/balance_option_2022.sql")
    void test() {
        // CHECKSTYLE:OFF

        SearchDocumentCodeByOfferingCapsuleDto capsuleDto = new SearchDocumentCodeByOfferingCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setHoukokuNen(2022);
        capsuleDto.setPoliOrgCode(9890);

        List<SelectOptionDto> listOption = getBalanceDocumentCodeOptionLogic.practice(capsuleDto);

        assertEquals(8, listOption.size());

        SelectOptionDto option0 = listOption.get(0);
        assertEquals("2", option0.getValue());
        assertEquals("2023-03-28提出分", option0.getText());

        SelectOptionDto option1 = listOption.get(1);
        assertEquals("9", option1.getValue());
        assertEquals("2023-12-03提出分", option1.getText());

        SelectOptionDto option2 = listOption.get(2);
        assertEquals("3", option2.getValue());
        assertEquals("2023-12-07提出分", option2.getText());

        SelectOptionDto option3 = listOption.get(3);
        assertEquals("11", option3.getValue());
        assertEquals("2023-12-11提出分", option3.getText());

        SelectOptionDto option4 = listOption.get(4);
        assertEquals("5", option4.getValue());
        assertEquals("2023-12-14提出分", option4.getText());

        SelectOptionDto option5 = listOption.get(5);
        assertEquals("10", option5.getValue());
        assertEquals("2023-12-18提出分", option5.getText());

        SelectOptionDto option6 = listOption.get(6);
        assertEquals("7", option6.getValue());
        assertEquals("2024-01-09提出分", option6.getText());

        SelectOptionDto option7 = listOption.get(7);
        assertEquals("12", option7.getValue());
        assertEquals("2024-01-26提出分", option7.getText());
    }

}
