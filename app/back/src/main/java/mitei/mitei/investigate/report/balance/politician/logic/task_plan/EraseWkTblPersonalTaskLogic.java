package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2024.EraseWkTblPersonalTaskY2024Logic;

/**
 * 指定したタスクを履歴にする
 */
@Component
public class EraseWkTblPersonalTaskLogic {

    /** 2024年 */
    private static final int YEAR_2024 = 2024;
    /** 2024年Loigc */
    @Autowired // 2024
    private EraseWkTblPersonalTaskY2024Logic eraseWkTblPersonalTaskY2024Logic;

    /**
     * 処理を行う
     *
     * @param listTaskName タスク名
     * @param privilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final int year, final List<String> listTaskName, final CheckPrivilegeDto privilegeDto) {

        switch (year) {
            case YEAR_2024:
                return eraseWkTblPersonalTaskY2024Logic.practice(listTaskName, privilegeDto);
            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
