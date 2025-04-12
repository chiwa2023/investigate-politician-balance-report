package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinFailureDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * SearchRenketsuOutcomeFailureService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRenketsuOutcomeFailureServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRenketsuOutcomeFailureService searchRenketsuOutcomeFailureService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wktbl_renketsu_koufukin.sql", "wktbl_usage_outcome.sql" })
    void test() {

        TemplatePagingCapsuleDto capsuleDto = new TemplatePagingCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.getCheckPrivilegeDto().setLoginUserCode(987);
        capsuleDto.setPageNumber(0);

        RenketsuKoufukinFailureDto dto = searchRenketsuOutcomeFailureService.practice(capsuleDto);

        assertEquals(50, dto.getOffset());
        assertEquals(17, dto.getCountAll());
        assertEquals(0, dto.getPageNumber());

        List<WkTblUsageOutcomeEntity> list = dto.getListFailure();

        WkTblUsageOutcomeEntity entity0 = list.get(0);
        assertEquals(5L, entity0.getPartyUsage0804ReportId());

        WkTblUsageOutcomeEntity entity1 = list.get(1);
        assertEquals(11L, entity1.getPartyUsage0804ReportId());

        WkTblUsageOutcomeEntity entity2 = list.get(2);
        assertEquals(14L, entity2.getPartyUsage0804ReportId());

        WkTblUsageOutcomeEntity entity3 = list.get(3);
        assertEquals(16L, entity3.getPartyUsage0804ReportId());

        fail("Not yet implemented");
    }

}
