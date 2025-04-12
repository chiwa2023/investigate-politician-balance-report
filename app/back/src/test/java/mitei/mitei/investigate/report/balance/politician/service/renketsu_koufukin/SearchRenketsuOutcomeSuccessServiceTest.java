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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplatePagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinWkTblDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * SearchRenketsuOutcomeSuccessService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRenketsuOutcomeSuccessServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRenketsuOutcomeSuccessService searchRenketsuOutcomeSuccessService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wktbl_renketsu_koufukin.sql")
    void test() {
        TemplatePagingCapsuleDto capsuleDto = new TemplatePagingCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.getCheckPrivilegeDto().setLoginUserCode(987);
        capsuleDto.setPageNumber(1);

        RenketsuKoufukinWkTblDto dto = searchRenketsuOutcomeSuccessService.practice(capsuleDto);

        assertEquals(50, dto.getOffset());
        assertEquals(171, dto.getCountAll());
        assertEquals(1, dto.getPageNumber());

        List<WkTblRenketsuKoufukinEntity> list = dto.getListSuccess();

        WkTblRenketsuKoufukinEntity entity0 = list.get(0);
        assertEquals(123L, entity0.getBalancesheetId());
        assertEquals(65L, entity0.getUsageReportId());

        WkTblRenketsuKoufukinEntity entity1 = list.get(1);
        assertEquals(124L, entity1.getBalancesheetId());
        assertEquals(66L, entity1.getUsageReportId());

        WkTblRenketsuKoufukinEntity entity2 = list.get(2);
        assertEquals(125L, entity2.getBalancesheetId());
        assertEquals(67L, entity2.getUsageReportId());

        WkTblRenketsuKoufukinEntity entity3 = list.get(3);
        assertEquals(127L, entity3.getBalancesheetId());
        assertEquals(68L, entity3.getUsageReportId());
    }

}
