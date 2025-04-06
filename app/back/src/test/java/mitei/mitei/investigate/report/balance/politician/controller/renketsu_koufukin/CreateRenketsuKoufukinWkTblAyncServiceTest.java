package mitei.mitei.investigate.report.balance.politician.controller.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.CreateRenketsuKoufukinCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * CreateRenketsuKoufukinWkTblAyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateRenketsuKoufukinWkTblAyncServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CreateRenketsuKoufukinWkTblAyncService createRenketsuKoufukinWkTblAyncService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "truncate_wk_tbl_balancesheet_income.sql", "truncate_wk_tbl_balancesheet_outcome.sql",
            "truncate_wk_tbl_usage_income.sql", "truncate_wk_tbl_usage_outcome.sql",
            "truncate_wk_tbl_renketsu_koufukin.sql", "task_info.sql" })
    void test() {

        CreateRenketsuKoufukinCapsuleDto capsuleDto = new CreateRenketsuKoufukinCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setHoukokuNen(2022);
        capsuleDto.setDocumentCodeUsage(1L);
        capsuleDto.setDocumentCodeBalance(7L);

        assertDoesNotThrow(() -> createRenketsuKoufukinWkTblAyncService.practice(capsuleDto, "交付金連結", 250, 2024),
                "非同期で例外なければOK");
    }

}
