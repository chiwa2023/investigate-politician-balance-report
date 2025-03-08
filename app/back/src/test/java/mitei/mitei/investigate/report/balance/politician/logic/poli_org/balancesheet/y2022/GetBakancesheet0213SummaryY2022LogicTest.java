package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaSurfaceAndSummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet01And20SurfaceEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0702And0713SummaryEntity;

/**
 * GetBakancesheet0213SummaryLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetBakancesheet0213SummaryY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetBakancesheet0213SummaryY2022Logic getBakancesheet0213SummaryY2022Logic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("keinen_henka_balancesheet_0213_summary_2022.sql")
    void test() {

        final int myYear = 2022;
        // 両方取得できた場合
        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto00 = getBakancesheet0213SummaryY2022Logic.practice(4340);
        
        OfferingBalancesheet01And20SurfaceEntity entitySurface00 = byYearDto00.getOfferingBalancesheet01And20SurfaceEntity();
        OfferingBalancesheet0702And0713SummaryEntity entitySummary00 = byYearDto00.getOfferingBalancesheet0702And0713SummaryEntity();

        assertEquals(myYear, entitySurface00.getHoukokuNen(), "表紙報告年が一致0");
        assertEquals(622L, entitySurface00.getOfferingBalancesheet0701And0720SurfaceId(), "表紙Idが一致");
        assertEquals(myYear, entitySummary00.getHoukokuNen(), "集計報告年が一致0");
        assertEquals(145L, entitySummary00.getOfferingBalancesheet0702And0713And0717SummaryId(), "集計Idが一致");

        assertEquals(entitySurface00.getOfferingBalancesheet0701And0720SurfaceCode(), entitySummary00.getDocumentCode(), "文書同一識別コードが一致");

        
        // 表紙は取得できたが集計が取得できない場合(ないけど)
        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto01 = getBakancesheet0213SummaryY2022Logic.practice(8180);
        
        OfferingBalancesheet01And20SurfaceEntity entitySurface01 = byYearDto01.getOfferingBalancesheet01And20SurfaceEntity();
        OfferingBalancesheet0702And0713SummaryEntity entitySummary01 = byYearDto01.getOfferingBalancesheet0702And0713SummaryEntity();

        assertEquals(myYear, entitySurface01.getHoukokuNen(), "表紙報告年が一致0");
        assertEquals(624L, entitySurface01.getOfferingBalancesheet0701And0720SurfaceId(), "表紙Idが一致0");
        assertEquals(myYear, entitySummary01.getHoukokuNen(), "集計報告年が一致0");
        assertEquals(0L, entitySummary01.getOfferingBalancesheet0702And0713And0717SummaryId(), "集計Idは0(1)");
        
        // 取得できなかった場合
        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto02 = getBakancesheet0213SummaryY2022Logic.practice(499);
        
        OfferingBalancesheet01And20SurfaceEntity entitySurface02 = byYearDto02.getOfferingBalancesheet01And20SurfaceEntity();
        OfferingBalancesheet0702And0713SummaryEntity entitySummary02 = byYearDto02.getOfferingBalancesheet0702And0713SummaryEntity();

        assertEquals(myYear, entitySurface02.getHoukokuNen(), "表紙報告年が一致2");
        assertEquals(0L, entitySurface02.getOfferingBalancesheet0701And0720SurfaceId(), "表紙Idが2");
        assertEquals(myYear, entitySummary02.getHoukokuNen(), "集計報告年が一致2");
        assertEquals(0L, entitySummary02.getOfferingBalancesheet0702And0713And0717SummaryId(), "集計Idが0(2)");

    }

}
