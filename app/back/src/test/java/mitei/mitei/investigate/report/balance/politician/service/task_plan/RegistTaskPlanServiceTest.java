package mitei.mitei.investigate.report.balance.politician.service.task_plan;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.task_plan.RegistTaskPlanResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * RegistTaskPlanService単体テスト
 */
class RegistTaskPlanServiceTest {

    /** テスト対象 */
    @Autowired
    private RegistTaskPlanService registTaskPlanService;

    @Test
    void testPractice() throws Exception {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        LocalDateTime datetimeShori = LocalDateTime.of(2024, 7,21,12,34,12);
        
        List<TaskInfoEntity> listTask = new ArrayList<>();
        
        // TODO リスト入手
        
        
        RegistTaskPlanResultDto resultDto = registTaskPlanService.practice(privilegeDto, datetimeShori, listTask);

        assertTrue(resultDto.getIsOk(),"登録成功");
        
        
        // TODO 具体的な登録の検証
        
        fail("Not yet implemented");
    }

}
