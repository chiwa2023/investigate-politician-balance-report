package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetOutcomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageOutcomeRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * DeleteKoufukinRenketsuWkTblTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteKoufukinRenketsuWkTblTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private DeleteKoufukinRenketsuWkTblTasklet deleteKoufukinRenketsuWkTblTasklet;

    /** 収支報告書収入ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetIncomeRepository wkTblBalancesheetIncomeRepository;

    /** 収支報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetOutcomeRepository wkTblBalancesheetOutcomeRepository;

    /** 使途報告書収入ワークテーブルRepository */
    @Autowired
    private WkTblUsageIncomeRepository wkTblUsageIncomeRepository;

    /** 使途報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblUsageOutcomeRepository wkTblUsageOutcomeRepository;

    /** 交付金連結ワークテーブルRepository */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "init_wk_tbl_balancesheet_income.sql", "init_wk_tbl_balancesheet_outcome.sql",
            "init_wk_tbl_usage_income.sql", "init_wk_tbl_usage_outcome.sql", "init_wk_tbl_renketsu_koufukin.sql" })
    void test() throws Exception {

        deleteKoufukinRenketsuWkTblTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, deleteKoufukinRenketsuWkTblTasklet.execute(null, null), "実行");

        final String mess = "テーブル件数が一致";
        assertEquals(1, wkTblBalancesheetIncomeRepository.count(), mess);
        assertEquals(2, wkTblBalancesheetOutcomeRepository.count(), mess);
        assertEquals(3, wkTblUsageIncomeRepository.count(), mess);
        assertEquals(4, wkTblUsageOutcomeRepository.count(), mess);
        assertEquals(5, wkTblRenketsuKoufukinRepository.count(), mess);

    }

    private StepExecution getStepExecution() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId()).addLong("userCode", 422L)
                .addString("userName", privilegeDto.getLoginUserName()) // 改行
                .addLong("houkokuNen", Long.valueOf(2022)) // 改行
                .addLong("documentCodeUsage", 1L) // 改行
                .addLong("documentCodeBalance", 7L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
