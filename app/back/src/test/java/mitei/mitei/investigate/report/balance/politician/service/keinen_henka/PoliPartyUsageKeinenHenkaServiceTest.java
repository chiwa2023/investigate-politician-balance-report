package mitei.mitei.investigate.report.balance.politician.service.keinen_henka;


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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenUsageSurface02103SummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0801And0807ReportEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0802And0803ReportEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * PoliPartyUsageKeinenHenkaService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PoliPartyUsageKeinenHenkaServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PoliPartyUsageKeinenHenkaService poliPartyUsageKeinenHenkaService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../logic/party_usage/y2022/usage_0107_surface_2022.sql",
            "../../logic/party_usage/y2022/usage_0203_summary_2022.sql",
            "../../logic/party_usage/y2023/usage_0107_surface_2023.sql",
            "../../logic/party_usage/y2023/usage_0203_summary_2023.sql",
            "../../logic/party_usage/y2024/usage_0107_surface_2024.sql",
            "../../logic/party_usage/y2024/usage_0203_summary_2024.sql",
            "../../logic/party_usage/y2025/usage_0107_surface_2025.sql",
            "../../logic/party_usage/y2025/usage_0203_summary_2025.sql" })
    void test() {

        KeinenHenkaConditionCapsuleDto capsuleDto = new KeinenHenkaConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setPoliOrgCode(132);
        capsuleDto.setHoukokuNenStart(2022);
        capsuleDto.setHoukokuNenEnd(2025);

        List<KeinenUsageSurface02103SummaryByYearDto> list = poliPartyUsageKeinenHenkaService.practice(capsuleDto);

        assertEquals(4, list.size(), "2022から2025の年分");

        // テストデータを自動複写している都合で報告年以外の違いはない(が別報告年テーブルにアクセスしていることを知るにはそれで十分)
        KeinenUsageSurface02103SummaryByYearDto byYearDto00 = list.get(0);
        OfferingPartyUsage0801And0807ReportEntity entitySurface00 = byYearDto00.getSurfaceEntity();
        assertEquals(2022, entitySurface00.getNendo(), "報告年が一致00");
        OfferingPartyUsage0802And0803ReportEntity entitySummary00 = byYearDto00.getSummaryEntity();
        assertEquals(2022, entitySummary00.getNendo(), "報告年が一致00");

        KeinenUsageSurface02103SummaryByYearDto byYearDto01 = list.get(1);
        OfferingPartyUsage0801And0807ReportEntity entitySurface01 = byYearDto01.getSurfaceEntity();
        assertEquals(2023, entitySurface01.getNendo(), "報告年が一致00");
        OfferingPartyUsage0802And0803ReportEntity entitySummary01 = byYearDto01.getSummaryEntity();
        assertEquals(2023, entitySummary01.getNendo(), "報告年が一致00");

        KeinenUsageSurface02103SummaryByYearDto byYearDto02 = list.get(2);
        OfferingPartyUsage0801And0807ReportEntity entitySurface02 = byYearDto02.getSurfaceEntity();
        assertEquals(2024, entitySurface02.getNendo(), "報告年が一致00");
        OfferingPartyUsage0802And0803ReportEntity entitySummary02 = byYearDto02.getSummaryEntity();
        assertEquals(2024, entitySummary02.getNendo(), "報告年が一致00");

        KeinenUsageSurface02103SummaryByYearDto byYearDto03 = list.get(3);
        OfferingPartyUsage0801And0807ReportEntity entitySurface03 = byYearDto03.getSurfaceEntity();
        assertEquals(2025, entitySurface03.getNendo(), "報告年が一致00");
        OfferingPartyUsage0802And0803ReportEntity entitySummary03 = byYearDto03.getSummaryEntity();
        assertEquals(2025, entitySummary03.getNendo(), "報告年が一致00");

    }

}
