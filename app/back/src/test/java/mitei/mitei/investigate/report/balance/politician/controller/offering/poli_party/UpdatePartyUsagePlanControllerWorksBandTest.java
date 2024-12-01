package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
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

import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.UpdatePartyUsageTaskPlanCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * UpdatePartyUsagePlanControllerWorksBand単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsagePlanControllerWorksBandTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsagePlanControllerWorksBand updatePartyUsagePlanControllerWorksBand;

    /** 政党交付金使途報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("../../../service/offering/poli_party/task_plan_party_usage_detail.sql")
    void test() {

        UpdatePartyUsageTaskPlanCapsuleDto capsuleDto = new UpdatePartyUsageTaskPlanCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        // 元データを編集した想定
        Long callId = 1245L;
        TaskPlanPartyUsageDetailEntity entitySrc = taskPlanPartyUsageDetailRepository.findById(callId).get();
        // 阻害要因
        entitySrc.setFileName(RandomStringUtils.secure().nextAlphanumeric(1000));
        capsuleDto.setTaskPlanPartyUsageDetailEntity(entitySrc);
        
        try {
            updatePartyUsagePlanControllerWorksBand.wakeBusiness(capsuleDto);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        TaskPlanPartyUsageDetailEntity entitySrc2 = taskPlanPartyUsageDetailRepository.findById(callId).get();
        
        assertEquals(1, entitySrc2.getSaishinKbn(),"ロールバックしたので変わらない");
        
    }

}
