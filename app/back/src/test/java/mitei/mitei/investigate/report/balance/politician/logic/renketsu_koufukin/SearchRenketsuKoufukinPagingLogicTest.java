package mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin;

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

import mitei.mitei.investigate.report.balance.politician.constants.RenketsuKoufukinConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplatePagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinWkTblDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * SearchRenketsuKoufukinPagingLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRenketsuKoufukinPagingLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRenketsuKoufukinPagingLogic searchRenketsuKoufukinPagingLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wktbl_renketsu_koufukin.sql")
    void test() {

        TemplatePagingCapsuleDto capsuleDto = new TemplatePagingCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.getCheckPrivilegeDto().setLoginUserCode(987);

        RenketsuKoufukinWkTblDto dto = searchRenketsuKoufukinPagingLogic.practice(capsuleDto,
                RenketsuKoufukinConstants.OUTCOME_DUPLICATE);

        assertEquals(50, dto.getOffset());
        assertEquals(12, dto.getCountAll());
        assertEquals(0, dto.getPageNumber());

        List<WkTblRenketsuKoufukinEntity> list = dto.getListSuccess();
        WkTblRenketsuKoufukinEntity entity0 = list.get(0);
        assertEquals(246L, entity0.getBalancesheetId());
        assertEquals(116L, entity0.getUsageReportId());

        WkTblRenketsuKoufukinEntity entity1 = list.get(1);
        assertEquals(246L, entity1.getBalancesheetId());
        assertEquals(115L, entity1.getUsageReportId());

        WkTblRenketsuKoufukinEntity entity2 = list.get(2);
        assertEquals(247L, entity2.getBalancesheetId());
        assertEquals(116L, entity2.getUsageReportId());

        WkTblRenketsuKoufukinEntity entity3 = list.get(3);
        assertEquals(247L, entity3.getBalancesheetId());
        assertEquals(115L, entity3.getUsageReportId());
    }
}
