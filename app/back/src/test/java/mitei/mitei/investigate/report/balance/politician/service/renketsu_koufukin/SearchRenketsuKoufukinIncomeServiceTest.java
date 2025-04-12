package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinIncomeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageIncomeEntity;

/**
 * SearchRenketsuKoufukinIncomeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRenketsuKoufukinIncomeServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRenketsuKoufukinIncomeService searchRenketsuKoufukinIncomeService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wktbl_renketsu_koufukin.sql", "wktbl_usage_income.sql" })
    void test() {

        Integer userCode = 987;
        RenketsuKoufukinIncomeDto incomeDto = searchRenketsuKoufukinIncomeService.practice(userCode);

        List<WkTblRenketsuKoufukinEntity> listDuplicate = incomeDto.getListDuplicate();
        assertEquals(2, listDuplicate.size());
        WkTblRenketsuKoufukinEntity entityDuplicate = listDuplicate.get(0);
        assertEquals(1821L, entityDuplicate.getWkTblRenketsuKoufukinId());

        List<WkTblRenketsuKoufukinEntity> listSuccess = incomeDto.getListSuccess();

        assertEquals(3, listSuccess.size());
        WkTblRenketsuKoufukinEntity entitylistSuccess = listSuccess.get(0);
        assertEquals(1823L, entitylistSuccess.getWkTblRenketsuKoufukinId());

        List<WkTblUsageIncomeEntity> listFailure = incomeDto.getListFailure();
        assertTrue(listFailure.isEmpty());
    }

}
