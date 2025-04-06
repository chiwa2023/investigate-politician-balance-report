package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblBalancesheetOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetOutcomeRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CopyBalancesheetWkTblTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CopyBalancesheetWkTblTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyBalancesheetWkTblTasklet copyBalancesheetWkTblTasklet;

    /** ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetIncomeRepository wkTblBalancesheetIncomeRepository;

    /** ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetOutcomeRepository wkTblBalancesheetOutcomeRepository;
    
    @Test
    @Tag("TableTruncate")
    @Sql({ "truncate_wk_tbl_balancesheet_income.sql", "truncate_wk_tbl_balancesheet_outcome.sql" })
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        copyBalancesheetWkTblTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, copyBalancesheetWkTblTasklet.execute(null, null), "実行");

        List<WkTblBalancesheetIncomeEntity> listIncome = wkTblBalancesheetIncomeRepository.findAll();
        assertEquals(33, listIncome.size(),"収入取得サイズ");
        assertEquals(2L, listIncome.get(1).getOfferingBalancesheetIncomeId(),"収入取得Id");
        
        List<WkTblBalancesheetOutcomeEntity> listOutcome = wkTblBalancesheetOutcomeRepository.findAll();
        assertEquals(500, listOutcome.size(),"支出取得サイズ");
        assertEquals(2L, listOutcome.get(1).getOfferingBalancesheetOutcomeId(),"支出取得Id");
    }

    private StepExecution getStepExecution() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(privilegeDto.getLoginUserCode()))
                .addString("userName", privilegeDto.getLoginUserName()) // 改行
                .addLong("houkokuNen", Long.valueOf(2022)) // 改行
                .addLong("documentCodeBalance", 7L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
