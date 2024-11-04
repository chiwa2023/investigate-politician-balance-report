package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2022.InsertTaskPlanY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.InsertTaskPlanY2024Logic;

/**
 * タスク計画データを挿入する
 */
@Component
public class InsertTaskPlanFactoryLogic {

    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private InsertTaskPlanY2024Logic insertTaskPlanY2024Logic;

    /** 2022年 */
    private static final int YEAR_2022 = 2022;
    /** 2022年Loigc */
    @Autowired // 2022
    private InsertTaskPlanY2022Logic insertTaskPlanY2022Logic;

    /** 2024年 */
    private static final int YEAR_2025 = 2025;
    /** 2025年Loigc */
    @Autowired // 2025
    private InsertTaskPlanY2024Logic insertTaskPlanY2025Logic;

    /**
     * 挿入作業を行う
     *
     * @param year 実施年
     * @param privilegeDto 権限確認Dto
     * @param listTask     タスクリスト
     * @return 挿入行数
     */
    public int practice(final int year, final CheckPrivilegeDto privilegeDto, // SUPPRESS CHECKSTYLE ReturnCountHceck
            final List<TaskInfoEntity> listTask) {

        switch (year) {
            // caseテンプレート始まり

            // 2024年
            case YEAR_2024:
                return insertTaskPlanY2024Logic.practice(privilegeDto, listTask);

            // 2022年
            case YEAR_2022:
                //return insertTaskPlanY2022Logic.practice(privilegeDto, listTask, listUser);
                return 0;
            // 2025年
            case YEAR_2025:
                //return insertTaskPlanY2025Logic.practice(privilegeDto, listTask, listUser);
                return 0;

            // caseの追加位置
            default:
                return 0;
        }

    }
}
