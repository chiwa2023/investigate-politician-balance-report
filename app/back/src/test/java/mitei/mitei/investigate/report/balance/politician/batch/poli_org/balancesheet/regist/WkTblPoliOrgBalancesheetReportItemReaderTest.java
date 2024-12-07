package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.assertj.core.api.Assertions.assertThat;

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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * WkTblPoliOrgBalancesheetReportItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WkTblPoliOrgBalancesheetReportItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private WkTblPoliOrgBalancesheetReportItemReader wkTblPoliOrgBalancesheetReportItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_org_balancesheet_report_read.sql")
    void test() throws Exception {

        // Step組み立ての際にChnkとして与えられるので外部設定
        wkTblPoliOrgBalancesheetReportItemReader.setPageSize(2);

        // 8件中6件を抽出
        WkTblPoliOrgBalancesheetReportEntity entity01 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity01.getWkTblPoliOrgBalancesheetReportId()).isEqualTo(204L);

        WkTblPoliOrgBalancesheetReportEntity entity02 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity02.getWkTblPoliOrgBalancesheetReportId()).isEqualTo(303L);

        WkTblPoliOrgBalancesheetReportEntity entity03 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity03.getWkTblPoliOrgBalancesheetReportId()).isEqualTo(409L);

        WkTblPoliOrgBalancesheetReportEntity entity04 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity04.getWkTblPoliOrgBalancesheetReportId()).isEqualTo(611L);

        WkTblPoliOrgBalancesheetReportEntity entity05 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity05.getWkTblPoliOrgBalancesheetReportId()).isEqualTo(712L);

        WkTblPoliOrgBalancesheetReportEntity entity06 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity06.getWkTblPoliOrgBalancesheetReportId()).isEqualTo(800L);

        // 件数以上取得しようとしてもnullが戻るだけ
        WkTblPoliOrgBalancesheetReportEntity entity07 = wkTblPoliOrgBalancesheetReportItemReader.read();
        assertThat(entity07).isEqualTo(null);

    }

}
