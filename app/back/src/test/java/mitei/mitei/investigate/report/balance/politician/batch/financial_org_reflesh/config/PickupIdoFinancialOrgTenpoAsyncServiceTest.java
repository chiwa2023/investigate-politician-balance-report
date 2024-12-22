package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

import static org.assertj.core.api.Assertions.fail;
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

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * PickupIdoFinancialOrgTenpoAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "../sammple/zengin_org_branch_master.sql", "../sammple/zengin_org_branch_wk1.sql",
        "../sammple/zengin_org_branch_wk2.sql", "../sammple/zengin_org_branch_wk3.sql",
        "../sammple/zengin_org_branch_wk4.sql" })
class PickupIdoFinancialOrgTenpoAsyncServiceTest {

    /** テスト対象 */
    @Autowired
    private PickupIdoFinancialOrgTenpoAsyncService pickupIdoFinancialOrgTenpoAsyncService;

    @Test
    @Tag("TableTruncate")
    @Sql("../truncate_zengin_org_change_branch.sql")
    void test() throws Exception {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        assertDoesNotThrow(() -> pickupIdoFinancialOrgTenpoAsyncService.practice(privilegeDto), "データが整備されていれば例外なく走る");

        fail("Not yet implemented");
    }

}
