package mitei.mitei.investigate.report.balance.politician.service.keinen_henka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.keinen_henka.KeinenHenkaSurfaceAndSummaryByYearDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet01And20SurfaceEntity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0702And0713SummaryEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * PoliOrgBalancesheetKeinenHenkaService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PoliOrgBalancesheetKeinenHenkaServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PoliOrgBalancesheetKeinenHenkaService poliOrgBalancesheetKeinenHenkaService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../logic/poli_org/balancesheet/y2022/keinen_henka_balancesheet_0213_summary_2022.sql",
            "../../logic/poli_org/balancesheet/y2023/keinen_henka_balancesheet_0213_summary_2023.sql",
            "../../logic/poli_org/balancesheet/y2024/keinen_henka_balancesheet_0213_summary_2024.sql",
            "../../logic/poli_org/balancesheet/y2025/keinen_henka_balancesheet_0213_summary_2025.sql" })
    void test() throws Exception {

        KeinenHenkaConditionCapsuleDto capsuleDto = new KeinenHenkaConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setPoliOrgCode(4340);
        capsuleDto.setHoukokuNenStart(2022);
        capsuleDto.setHoukokuNenEnd(2025);

        List<KeinenHenkaSurfaceAndSummaryByYearDto> list = poliOrgBalancesheetKeinenHenkaService.practice(capsuleDto);

        assertEquals(4, list.size(), "2022から2025の年分");

        // テストデータを自動複写している都合で報告年以外の違いはない(が別報告年テーブルにアクセスしていることを知るにはそれで十分)

        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto00 = list.get(0);
        OfferingBalancesheet01And20SurfaceEntity entitySurface00 = byYearDto00
                .getOfferingBalancesheet01And20SurfaceEntity();
        assertEquals(2022, entitySurface00.getHoukokuNen(), "報告年が一致00");
        OfferingBalancesheet0702And0713SummaryEntity entitySummary00 = byYearDto00
                .getOfferingBalancesheet0702And0713SummaryEntity();
        assertEquals(2022, entitySummary00.getHoukokuNen(), "報告年が一致00");

        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto01 = list.get(1);
        OfferingBalancesheet01And20SurfaceEntity entitySurface01 = byYearDto01
                .getOfferingBalancesheet01And20SurfaceEntity();
        assertEquals(2023, entitySurface01.getHoukokuNen(), "報告年が一致10");
        OfferingBalancesheet0702And0713SummaryEntity entitySummary01 = byYearDto01
                .getOfferingBalancesheet0702And0713SummaryEntity();
        assertEquals(2023, entitySummary01.getHoukokuNen(), "報告年が一致11");

        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto02 = list.get(2);
        OfferingBalancesheet01And20SurfaceEntity entitySurface02 = byYearDto02
                .getOfferingBalancesheet01And20SurfaceEntity();
        assertEquals(2024, entitySurface02.getHoukokuNen(), "報告年が一致20");
        OfferingBalancesheet0702And0713SummaryEntity entitySummary02 = byYearDto02
                .getOfferingBalancesheet0702And0713SummaryEntity();
        assertEquals(2024, entitySummary02.getHoukokuNen(), "報告年が一致22");

        KeinenHenkaSurfaceAndSummaryByYearDto byYearDto03 = list.get(3);
        OfferingBalancesheet01And20SurfaceEntity entitySurface03 = byYearDto03
                .getOfferingBalancesheet01And20SurfaceEntity();
        assertEquals(2025, entitySurface03.getHoukokuNen(), "報告年が一致30");
        OfferingBalancesheet0702And0713SummaryEntity entitySummary03 = byYearDto03
                .getOfferingBalancesheet0702And0713SummaryEntity();
        assertEquals(2025, entitySummary03.getHoukokuNen(), "報告年が一致33");

    }

    @Test
    void testException() throws Exception {

        KeinenHenkaConditionCapsuleDto capsuleDto = new KeinenHenkaConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setPoliOrgCode(4340);
        capsuleDto.setHoukokuNenStart(1192);
        capsuleDto.setHoukokuNenEnd(1234);
        assertThrows(IllegalArgumentException.class, () -> poliOrgBalancesheetKeinenHenkaService.practice(capsuleDto),
                "未実装の報告年は例外で停止");
    }
}
