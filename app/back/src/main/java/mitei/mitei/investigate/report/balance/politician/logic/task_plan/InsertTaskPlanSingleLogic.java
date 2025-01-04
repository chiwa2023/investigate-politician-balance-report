package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.InsertTaskPlanSingleY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025.InsertTaskPlanSingleY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2023.InsertTaskPlanSingleY2023Logic;
// importを追加

/**
 * 1行だけタスク計画を登録する
 */
@Component
public class InsertTaskPlanSingleLogic {

    // フィールドテンプレート始まり
    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private InsertTaskPlanSingleY2024Logic insertTaskPlanSingleY2024Logic;

    /** 2025年 */
    private static final int YEAR_2025 = 2025;
    /** 2025年Loigc */
    @Autowired // 2025
    private InsertTaskPlanSingleY2025Logic insertTaskPlanSingleY2025Logic;

    /** 2023年 */
    private static final int YEAR_2023 = 2023;
    /** 2023年Loigc */
    @Autowired // 2023
    private InsertTaskPlanSingleY2023Logic insertTaskPlanSingleY2023Logic;

    // フィールドの追加位置

    /**
     * 処理を行う
     *
     * @param taskInfoEntity タスク情報Entity
     * @param privilegeDto   権限確認Dto
     * @return 登録タスク計画同一識別コード
     */
    public int practice(final int year, final TaskInfoEntity taskInfoEntity, final CheckPrivilegeDto privilegeDto) {

        switch (year) {
            // caseテンプレート始まり
            // 2024年
            case YEAR_2024:
                return insertTaskPlanSingleY2024Logic.practice(taskInfoEntity, privilegeDto);

            // 2025年
            case YEAR_2025:
                return insertTaskPlanSingleY2025Logic.practice(taskInfoEntity, privilegeDto);

            // 2023年
            case YEAR_2023:
                return insertTaskPlanSingleY2023Logic.practice(taskInfoEntity, privilegeDto);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }
}
