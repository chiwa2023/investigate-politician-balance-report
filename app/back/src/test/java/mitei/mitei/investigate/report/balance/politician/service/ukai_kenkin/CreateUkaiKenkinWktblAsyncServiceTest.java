package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.service.task_plan.RegistTaskPlanNoAlertPersonalService;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CreateUkaiKenkinWktblService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateUkaiKenkinWktblAsyncServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CreateUkaiKenkinWktblAsyncService createUkaiKenkinWktblAsyncService;

    /** タスク計画登録Service */
    @Autowired
    private RegistTaskPlanNoAlertPersonalService registTaskPlanNoAlertPersonalService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../batch/balancesheet/ukai_kenkin/configuration_income_2022.sql",
            "../../batch/balancesheet/ukai_kenkin/configuration_poli_org_property.sql",
            "../../batch/balancesheet/ukai_kenkin/configuration_wktbl_meisai_clean.sql",
            "../../batch/balancesheet/ukai_kenkin/configuration_wktbl_route.sql" })
    void test() throws Exception {

        UkaiKenkinConditionCapsuleDto capsuleDto = new UkaiKenkinConditionCapsuleDto();
        capsuleDto.setCheckPrivilegeDto(CreateTestPrivilegeDtoUtil.pracitce());
        capsuleDto.setHoukokuNen(2022);
        capsuleDto.setIsKoufukin(false);
        capsuleDto.setIsNameSearch(false);
        capsuleDto.setPickupTimes(1); // 1階層
        capsuleDto.setPoliOrgId(105L);
        capsuleDto.setPoliOrgCode(100);
        capsuleDto.setPoliOrgName("ABCD団体");

        int year = 2024;
        
        final String taskName = "迂回献金キャッチャー";
        Integer code = registTaskPlanNoAlertPersonalService.practice(capsuleDto.getCheckPrivilegeDto(), taskName, year);

        assertDoesNotThrow(() -> createUkaiKenkinWktblAsyncService.practice(capsuleDto, code, taskName, year),
                "非同期側で例外が出なければよい");
    }

}
