package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.wktbl;

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
 * WkTblPoliPtyUsageReportItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WkTblPoliPtyUsageReportItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private WkTblPoliPtyUsageReportItemReader wkTblPoliPtyUsageReportItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_party_usage_report.sql")
    void test() throws Exception {

        // Step組み立ての際にChnkとして与えられるので外部設定
        wkTblPoliPtyUsageReportItemReader.setPageSize(2);

        // 8件中6件を抽出
        WkTblPoliPartyUsageReportEntity entity01 = wkTblPoliPtyUsageReportItemReader.read();
        assertEquals(9999, entity01.getWkTblPoliPartyUsageReportCode(),"");

        WkTblPoliPartyUsageReportEntity entity02 = wkTblPoliPtyUsageReportItemReader.read();
        assertEquals(9999, entity02.getWkTblPoliPartyUsageReportCode(),"");

        // 件数以上取得しようとしてもnullが戻るだけ
        WkTblPoliPartyUsageReportEntity entityNot = wkTblPoliPtyUsageReportItemReader.read();
        assertEquals(null, entityNot,"");

    }

}
