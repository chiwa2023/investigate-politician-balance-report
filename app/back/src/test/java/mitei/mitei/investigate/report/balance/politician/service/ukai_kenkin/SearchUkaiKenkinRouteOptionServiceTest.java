package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * SearchUkaiKenkinRouteOptionService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchUkaiKenkinRouteOptionServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchUkaiKenkinRouteOptionService searchUkaiKenkinRouteOptionService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("route_select_option.sql")
    void test() throws Exception {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        List<SelectOptionDto> list = searchUkaiKenkinRouteOptionService.practice(privilegeDto.getLoginUserCode());

        assertEquals(6, list.size(), "取得件数は6件");

        SelectOptionDto dtoStage = list.get(0);
        assertEquals("0", dtoStage.getValue(), "値が一致階層0");
        assertEquals("経路なし関係者寄付", dtoStage.getText(), "表示が一致階層0");

        SelectOptionDto dto00 = list.get(1);
        assertEquals("1", dto00.getValue(), "値が一致0");
        assertEquals("経路(1)ABCD団体-->関連団体A", dto00.getText(), "表示が一致0");

        SelectOptionDto dto01 = list.get(2);
        assertEquals("2", dto01.getValue(), "値が一致1");
        assertEquals("経路(2)ABCD団体-->関連団体B", dto01.getText(), "表示が一致1");

        SelectOptionDto dto02 = list.get(3);
        assertEquals("3", dto02.getValue(), "値が一致2");
        assertEquals("経路(3)ABCD団体-->関連団体C", dto02.getText(), "表示が一致2");

        SelectOptionDto dto03 = list.get(4);
        assertEquals("4", dto03.getValue(), "値が一致3");
        assertEquals("経路(4)ABCD団体-->関連団体D", dto03.getText(), "表示が一致3");

        SelectOptionDto dto04 = list.get(5);
        assertEquals("5", dto04.getValue(), "値が一致4");
        assertEquals("経路(5)ABCD団体-->関連団体E", dto04.getText(), "表示が一致4");

    }

}
