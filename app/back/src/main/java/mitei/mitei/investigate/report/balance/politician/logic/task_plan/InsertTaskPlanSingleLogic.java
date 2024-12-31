package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.InsertTaskPlanSingleY2024Logic;

/**
 * 1行だけタスク計画を登録する
 */
@Component
public class InsertTaskPlanSingleLogic {
    
    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private InsertTaskPlanSingleY2024Logic insertTaskPlanSingleY2024Logic;
    
    /**
     * 処理を行う
     *
     * @param taskInfoEntity タスク情報Entity
     * @param privilegeDto   権限確認Dto
     * @return 登録タスク計画同一識別コード
     */
    public int practice(final int year,final TaskInfoEntity taskInfoEntity, final CheckPrivilegeDto privilegeDto) {

        switch (year) {
            case YEAR_2024:

                return insertTaskPlanSingleY2024Logic.practice(taskInfoEntity, privilegeDto);
            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }
        
        
    }
}
