package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;


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
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKofukinDocumentCodeOptionResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.SearchDocumentCodeByOfferingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * GetBothDocumentCodeOptionService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetBothDocumentCodeOptionServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetBothDocumentCodeOptionService getBothDocumentCodeOptionService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "balance_option_2022.sql", "usage_option_2022.sql" })
    void test() {

        SearchDocumentCodeByOfferingCapsuleDto capsuleDto = new SearchDocumentCodeByOfferingCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setHoukokuNen(2022);
        capsuleDto.setPoliOrgCode(9890);

        RenketsuKofukinDocumentCodeOptionResultDto resultDto = getBothDocumentCodeOptionService.practice(capsuleDto);

        List<SelectOptionDto> listOptionBalance = resultDto.getListBalance();
        List<SelectOptionDto> listOptionUsage = resultDto.getListUsage();

        assertEquals(8, listOptionBalance.size());

        SelectOptionDto option00 = listOptionBalance.get(0);
        assertEquals("2", option00.getValue());
        assertEquals("2023-03-28提出分", option00.getText());

        SelectOptionDto option01 = listOptionBalance.get(1);
        assertEquals("9", option01.getValue());
        assertEquals("2023-12-03提出分", option01.getText());

        SelectOptionDto option02 = listOptionBalance.get(2);
        assertEquals("3", option02.getValue());
        assertEquals("2023-12-07提出分", option02.getText());

        SelectOptionDto option03 = listOptionBalance.get(3);
        assertEquals("11", option03.getValue());
        assertEquals("2023-12-11提出分", option03.getText());

        SelectOptionDto option04 = listOptionBalance.get(4);
        assertEquals("5", option04.getValue());
        assertEquals("2023-12-14提出分", option04.getText());

        SelectOptionDto option05 = listOptionBalance.get(5);
        assertEquals("10", option05.getValue());
        assertEquals("2023-12-18提出分", option05.getText());

        SelectOptionDto option06 = listOptionBalance.get(6);
        assertEquals("7", option06.getValue());
        assertEquals("2024-01-09提出分", option06.getText());

        SelectOptionDto option07 = listOptionBalance.get(7);
        assertEquals("12", option07.getValue());
        assertEquals("2024-01-26提出分", option07.getText());

        assertEquals(8, listOptionUsage.size());

        // 2023-03-28 2
        SelectOptionDto option10 = listOptionUsage.get(0);
        assertEquals("2", option10.getValue());
        assertEquals("2023-03-28提出分", option10.getText());

        // 2023-12-03 9
        SelectOptionDto option11 = listOptionUsage.get(1);
        assertEquals("9", option11.getValue());
        assertEquals("2023-12-03提出分", option11.getText());

        // 2023-12-07 3
        SelectOptionDto option12 = listOptionUsage.get(2);
        assertEquals("3", option12.getValue());
        assertEquals("2023-12-07提出分", option12.getText());

        // 2023-12-11 11
        SelectOptionDto option13 = listOptionUsage.get(3);
        assertEquals("11", option13.getValue());
        assertEquals("2023-12-11提出分", option13.getText());

        // 2023-12-14 5
        SelectOptionDto option14 = listOptionUsage.get(4);
        assertEquals("5", option14.getValue());
        assertEquals("2023-12-14提出分", option14.getText());

        // 2023-12-18 10
        SelectOptionDto option15 = listOptionUsage.get(5);
        assertEquals("10", option15.getValue());
        assertEquals("2023-12-18提出分", option15.getText());

        // 2024-01-09 7
        SelectOptionDto option16 = listOptionUsage.get(6);
        assertEquals("7", option16.getValue());
        assertEquals("2024-01-09提出分", option16.getText());

        // 2024-01-26 12
        SelectOptionDto option17 = listOptionUsage.get(7);
        assertEquals("12", option17.getValue());
        assertEquals("2024-01-26提出分", option17.getText());

    }

}
