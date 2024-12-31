package mitei.mitei.investigate.report.balance.politician.controller.poli_org.fukisai;

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
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchBalancesheetFukisaiCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * CreateFukisaiWkTblAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateFukisaiWkTblAsyncServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CreateFukisaiWkTblAsyncService createFukisaiWkTblAsyncService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "update_task_plan_2024.sql", "erase_wk_tbl_fukisai.sql", "convert_income_fukisai_2022.sql",
            "convert_outcome_fukisai_2022.sql" })
    void test() {

        SearchBalancesheetFukisaiCapsuleDto capsuleDto = new SearchBalancesheetFukisaiCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        FukisaiSearchConditionDto fukisaiSearchConditionDto = new FukisaiSearchConditionDto();
        fukisaiSearchConditionDto.setHoukokuNen(2022);
        fukisaiSearchConditionDto.setIsSearchCode(true);
        fukisaiSearchConditionDto.setPoliOrgCode(431);
        capsuleDto.setFukisaiSearchConditionDto(fukisaiSearchConditionDto);
        
        assertDoesNotThrow(() -> createFukisaiWkTblAsyncService.practice(capsuleDto, "不記載チェッカー", 250, 2024),
                "非同期で例外なければOK");
    }

}
