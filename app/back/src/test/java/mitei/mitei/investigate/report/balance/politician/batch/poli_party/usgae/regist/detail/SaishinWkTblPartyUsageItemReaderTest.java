package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;

/**
 * SaishinWkTblPartyUsageItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SaishinWkTblPartyUsageItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SaishinWkTblPartyUsageItemReader saishinWkTblPartyUsageItemReader;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("../../../../../service/offering/poli_party/wk_tbl_poli_party_usage_report.sql")
    void test() throws Exception {

        // Step組み立ての際にChnkとして与えられるので外部設定
        saishinWkTblPartyUsageItemReader.setPageSize(2);

        WkTblPoliPartyUsageReportEntity entity01 = saishinWkTblPartyUsageItemReader.read();
        assertEquals(2239L, entity01.getWkTblPoliPartyUsageReportId(), "1件目");

        WkTblPoliPartyUsageReportEntity entity02 = saishinWkTblPartyUsageItemReader.read();
        assertEquals(2240L, entity02.getWkTblPoliPartyUsageReportId(), "2件目");

        WkTblPoliPartyUsageReportEntity entity03 = saishinWkTblPartyUsageItemReader.read();
        assertEquals(3653L, entity03.getWkTblPoliPartyUsageReportId(), "3件目");

        WkTblPoliPartyUsageReportEntity entity04 = saishinWkTblPartyUsageItemReader.read();
        assertEquals(3654L, entity04.getWkTblPoliPartyUsageReportId(), "3件目");

        WkTblPoliPartyUsageReportEntity entityNot = saishinWkTblPartyUsageItemReader.read();
        assertEquals(null, entityNot, "該当件数を超えた場合はnull");

    }

}
