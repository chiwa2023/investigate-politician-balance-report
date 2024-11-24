package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;


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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * SaishinWkTblBalancesheetItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SaishinWkTblBalancesheetItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SaishinWkTblBalancesheetItemReader saishinWkTblBalancesheetItemReader;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_org_balancesheet_report_read.sql")
    void test()throws Exception {
        
        // Step組み立ての際にChnkとして与えられるので外部設定
        saishinWkTblBalancesheetItemReader.setPageSize(2);

        WkTblPoliOrgBalancesheetReportEntity entity01 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(204L, entity01.getWkTblPoliOrgBalancesheetReportId(),"1件目");

        WkTblPoliOrgBalancesheetReportEntity entity02 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(303L, entity02.getWkTblPoliOrgBalancesheetReportId(),"2件目");

        WkTblPoliOrgBalancesheetReportEntity entity03 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(409L, entity03.getWkTblPoliOrgBalancesheetReportId(),"3件目");

        WkTblPoliOrgBalancesheetReportEntity entity04 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(510L, entity04.getWkTblPoliOrgBalancesheetReportId(),"4件目");

        WkTblPoliOrgBalancesheetReportEntity entity05 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(611L, entity05.getWkTblPoliOrgBalancesheetReportId(),"5件目");

        WkTblPoliOrgBalancesheetReportEntity entity06 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(712L, entity06.getWkTblPoliOrgBalancesheetReportId(),"6件目");

        WkTblPoliOrgBalancesheetReportEntity entity07 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(800L, entity07.getWkTblPoliOrgBalancesheetReportId(),"7件目");

        WkTblPoliOrgBalancesheetReportEntity entity08 = saishinWkTblBalancesheetItemReader.read();
        assertEquals(null, entity08,"該当件数を超えた場合はnull");
        
    }

}
