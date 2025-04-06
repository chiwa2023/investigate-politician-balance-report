package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.constants.RenketsuKoufukinConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * PickupRenketsuKoufukinDuplicateTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupRenketsuKoufukinDuplicateTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PickupRenketsuKoufukinDuplicateTasklet pickupRenketsuKoufukinDuplicateTasklet;

    /** 交付金連結ワークテーブル */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

    /** ユーザ同一識別コード */
    private static final int userCode = 987;

    @Test
    @Tag("TableTruncate")
    @Sql("pickup_wk_tbl_renketsu_koufukin.sql")
    @Transactional
    void test() throws Exception {

        // 処理前には重複データは存在しない
        List<WkTblRenketsuKoufukinEntity> listPreIncome = wkTblRenketsuKoufukinRepository
                .findByInsertUserCodeAndDataKbn(userCode, RenketsuKoufukinConstants.INCOME_DUPLICATE);
        assertTrue(listPreIncome.isEmpty());
        List<WkTblRenketsuKoufukinEntity> listPreOutcome = wkTblRenketsuKoufukinRepository
                .findByInsertUserCodeAndDataKbn(userCode, RenketsuKoufukinConstants.OUTCOME_DUPLICATE);
        assertTrue(listPreOutcome.isEmpty());

        pickupRenketsuKoufukinDuplicateTasklet.beforeStep(this.getStepExecution());
        assertEquals(RepeatStatus.FINISHED, pickupRenketsuKoufukinDuplicateTasklet.execute(null, null), "実行");

        // 処理後には重複データは存在しない
        List<WkTblRenketsuKoufukinEntity> listIncome = wkTblRenketsuKoufukinRepository
                .findByInsertUserCodeAndDataKbn(userCode, RenketsuKoufukinConstants.INCOME_DUPLICATE);
        assertEquals(2, listIncome.size());

        WkTblRenketsuKoufukinEntity entity00 = listIncome.get(0);
        assertEquals(298, entity00.getWkTblRenketsuKoufukinId());
        WkTblRenketsuKoufukinEntity entity01 = listIncome.get(1);
        assertEquals(299, entity01.getWkTblRenketsuKoufukinId());

        List<WkTblRenketsuKoufukinEntity> listOutcome = wkTblRenketsuKoufukinRepository
                .findByInsertUserCodeAndDataKbn(userCode, RenketsuKoufukinConstants.OUTCOME_DUPLICATE);
        assertEquals(2, listOutcome.size());

        WkTblRenketsuKoufukinEntity entity10 = listOutcome.get(0);
        assertEquals(300, entity10.getWkTblRenketsuKoufukinId());
        WkTblRenketsuKoufukinEntity entity11 = listOutcome.get(1);
        assertEquals(301, entity11.getWkTblRenketsuKoufukinId());
    }

    private StepExecution getStepExecution() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId()).addLong("userCode", Long.valueOf(userCode))
                .addString("userName", privilegeDto.getLoginUserName()) // 改行
                .addLong("houkokuNen", Long.valueOf(2022)) // 改行
                .addLong("documentCodeUsage", 1L) // 改行
                .addLong("documentCodeBalance", 7L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
