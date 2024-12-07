package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePartyUsagePlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsagePlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsagePlanService updatePartyUsagePlanService;

    /** 政党交付金使途報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    /** 政治資金収支報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("task_plan_party_usage_detail.sql")
    void testPractice() throws Exception {

        // 元データを編集した想定
        Long callId = 1245L;
        String charset = "UTF-8";
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        TaskPlanPartyUsageDetailEntity entitySrc = taskPlanPartyUsageDetailRepository.findById(callId).get();

        // 編集
        Long newId = updatePartyUsagePlanService.practice(entitySrc, privilegeDto);

        // 元のデータを呼びなおし
        entitySrc = taskPlanPartyUsageDetailRepository.findById(callId).get();

        // 編集後データを呼ぶ
        TaskPlanPartyUsageDetailEntity entityCopy = taskPlanPartyUsageDetailRepository.findById(newId).get();

        // 最新区分が変更されている
        assertEquals(DataHistoryStatusConstants.UPDATE.value(), entitySrc.getSaishinKbn(),"");

        assertEquals(DataHistoryStatusConstants.INSERT.value(), entityCopy.getSaishinKbn(),"");

        // 文字コードが変更されている
        assertEquals(charset, entityCopy.getCharset(),"");

    }

    @Test
    @Tag("TableTruncate")
    @Sql("task_plan_party_usage_detail.sql")
    void testPracticeToBalancesheet() throws Exception {

        // 元データを編集した想定
        Long callId = 904L;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        TaskPlanPartyUsageDetailEntity entitySrc = taskPlanPartyUsageDetailRepository.findById(callId).get();
        String charset = "Shift_JIS";
        String documentKey = DocumentRecognizeKeyConstants.SOFT_BALANCESHEET;
        entitySrc.setCharset(charset);
        entitySrc.setDocumentKey(documentKey);

        // 編集
        Long newId = updatePartyUsagePlanService.practice(entitySrc, privilegeDto);

        // 元のデータを呼びなおし
        entitySrc = taskPlanPartyUsageDetailRepository.findById(callId).get();

        // 編集後データを呼ぶ(政治資金収支報告書Entity)
        TaskPlanBalancesheetDetailEntity entityCopy = taskPlanBalancesheetDetailRepository.findById(newId).get();

        // 最新区分が変更されている
        assertEquals(DataHistoryStatusConstants.UPDATE.value(), entitySrc.getSaishinKbn(),"");
        assertEquals(DataHistoryStatusConstants.INSERT.value(), entityCopy.getSaishinKbn(),"");

        // 文字コードが変更されている
        assertEquals(null, entitySrc.getCharset(),"");
        assertEquals(charset, entityCopy.getCharset(),"");

        // 政党交付金使途報告書テーブルのデータはサンプルデータから増えていない
        assertEquals(8, taskPlanPartyUsageDetailRepository.count(),"");
    }

    // TODO 負荷テスト

}
