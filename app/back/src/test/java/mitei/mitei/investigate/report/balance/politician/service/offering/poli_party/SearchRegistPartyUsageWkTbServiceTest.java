package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;

/**
 * SearchRegistPartyUsageWkTbService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchRegistPartyUsageWkTbServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchRegistPartyUsageWkTbService searchRegistPartyUsageWkTbService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_party_usage_report.sql")
    void testPractice() {

        List<WkTblPoliPartyUsageReportEntity> list = searchRegistPartyUsageWkTbService.practice();

        list.sort((e1, e2) -> e1.getInsertUserCode() - e2.getInsertUserCode());

        assertEquals(2, list.size(), "正常登録は2件");

        WkTblPoliPartyUsageReportEntity entity00 = list.get(0);
        assertEquals(3653L, entity00.getWkTblPoliPartyUsageReportId(), "取得Idが一致");

        WkTblPoliPartyUsageReportEntity entity01 = list.get(1);
        assertEquals(3654L, entity01.getWkTblPoliPartyUsageReportId(), "取得Idが一致");

    }

}
