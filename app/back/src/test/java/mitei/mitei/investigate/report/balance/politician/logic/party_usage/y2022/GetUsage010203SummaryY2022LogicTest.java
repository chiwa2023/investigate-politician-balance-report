package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenUsageSurface02103SummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0801And0807ReportEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0802And0803ReportEntity;

/**
 * GetUsage010203SummaryY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetUsage010203SummaryY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetUsage010203SummaryY2022Logic getUsage010203SummaryY2022Logic;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({"usage_0107_surface_2022.sql","usage_0203_summary_2022.sql"})
    void test() {

        // データが表紙・集計とも存在しない
        Integer poliOrgCode0 = 999;
        final int year = 2022;
        KeinenUsageSurface02103SummaryByYearDto dto0 = getUsage010203SummaryY2022Logic.practice(poliOrgCode0);
        assertNotNull(dto0);
        assertNotNull(dto0.getSurfaceEntity());
        assertNotNull(dto0.getSummaryEntity());
        assertEquals(year, dto0.getHoukokuNen());
        OfferingPartyUsage0801And0807ReportEntity surfaceEntity0 = dto0.getSurfaceEntity(); // NOPMD
        OfferingPartyUsage0802And0803ReportEntity summaryEntity0 = dto0.getSummaryEntity(); // NOPMD
        assertEquals(year, surfaceEntity0.getNendo());
        assertEquals(year, summaryEntity0.getNendo());
        assertEquals(0L, surfaceEntity0.getPartyUsage0801And0807ReportId());
        assertEquals(0L, summaryEntity0.getPartyUsage0802And0803ReportId());

        // データが表紙のみが存在しないが双方存在しないときと結果は一緒
        Integer poliOrgCode1 = 414;
        KeinenUsageSurface02103SummaryByYearDto dto1 = getUsage010203SummaryY2022Logic.practice(poliOrgCode1);
        assertNotNull(dto1);
        assertNotNull(dto1.getSurfaceEntity());
        assertNotNull(dto1.getSummaryEntity());
        assertEquals(year, dto1.getHoukokuNen());
        OfferingPartyUsage0801And0807ReportEntity surfaceEntity1 = dto1.getSurfaceEntity(); // NOPMD
        OfferingPartyUsage0802And0803ReportEntity summaryEntity1 = dto1.getSummaryEntity(); // NOPMD
        assertEquals(year, surfaceEntity1.getNendo());
        assertEquals(year, summaryEntity1.getNendo());
        assertEquals(0L, surfaceEntity1.getPartyUsage0801And0807ReportId());
        assertEquals(0L, summaryEntity1.getPartyUsage0802And0803ReportId());

        // データが集計のみ存在しない
        Integer poliOrgCode2 = 133;
        KeinenUsageSurface02103SummaryByYearDto dto2 = getUsage010203SummaryY2022Logic.practice(poliOrgCode2);
        assertNotNull(dto2);
        assertNotNull(dto2.getSurfaceEntity());
        assertNotNull(dto2.getSummaryEntity());
        assertEquals(year, dto2.getHoukokuNen());
        OfferingPartyUsage0801And0807ReportEntity surfaceEntity2 = dto2.getSurfaceEntity(); // NOPMD
        OfferingPartyUsage0802And0803ReportEntity summaryEntity2 = dto2.getSummaryEntity(); // NOPMD
        assertEquals(year, surfaceEntity2.getNendo());
        assertEquals(year, summaryEntity2.getNendo());
        assertEquals(233L, surfaceEntity2.getPartyUsage0801And0807ReportId());
        assertEquals(0L, summaryEntity2.getPartyUsage0802And0803ReportId());

        // 双方存在する
        Integer poliOrgCode3 = 132;
        KeinenUsageSurface02103SummaryByYearDto dto3 = getUsage010203SummaryY2022Logic.practice(poliOrgCode3);
        assertNotNull(dto3);
        assertNotNull(dto3.getSurfaceEntity());
        assertNotNull(dto3.getSummaryEntity());
        assertEquals(year, dto3.getHoukokuNen());
        OfferingPartyUsage0801And0807ReportEntity surfaceEntity3 = dto3.getSurfaceEntity(); // NOPMD
        OfferingPartyUsage0802And0803ReportEntity summaryEntity3 = dto3.getSummaryEntity(); // NOPMD
        assertEquals(year, surfaceEntity3.getNendo());
        assertEquals(year, summaryEntity3.getNendo());
        assertEquals(232L, surfaceEntity3.getPartyUsage0801And0807ReportId());
        assertEquals(357L, summaryEntity3.getPartyUsage0802And0803ReportId());

    }

}
