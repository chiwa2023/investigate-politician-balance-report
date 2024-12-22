package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * UpdateAddAndMoveZenginMasterFromIdoAysncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateAddAndMoveZenginMasterFromIdoAysncServiceTest {

    /** テスト対象 */
    @Autowired
    private UpdateAddAndMoveZenginMasterFromIdoAysncService updateAddAndMoveZenginMasterFromIdoAysncService;

    @Test
    @Tag("TableTruncate")
    @Sql({ "../sample/zengin_org_change_branch.sql", "../sample/zengin_org_branch_master.sql" })
    void test() {

        TemplateFrameworkCapsuleDto capsuleDto = new TemplateFrameworkCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        assertDoesNotThrow(() -> updateAddAndMoveZenginMasterFromIdoAysncService.practice(capsuleDto));

    }

}
