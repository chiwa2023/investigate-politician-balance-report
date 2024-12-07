package mitei.mitei.investigate.report.balance.politician.logic.task_plan.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.task_plan.TaskPlan2025Repository;
import mitei.mitei.investigate.report.balance.politician.dto.task_plan.TaskPlanSelectOptionDto;

/**
 * 未処理タスク予定検索Logic(2025)
 */
@Component
public class SearchTaskPlanByPoliticalOrgY2025Logic {

    /** 未処理タスク予定Repository */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /**
     * 検索処理をおこなう
     *
     * @param listPoriOrg ユーザ政治団体同一識別コードリスト
     * @param userCode    ログインユーザ同一識別コード
     * @return 未処理タスクリスト
     */
    public List<TaskPlanSelectOptionDto> practice(final List<Integer> listPoriOrg, final Integer userCode) {

        List<TaskPlan2025Entity> listEntity = taskPlan2025Repository
                .findBySaishinKbnAndPoliticalOrganizationCodeIn(DataHistoryStatusConstants.INSERT.value(), listPoriOrg);

        List<TaskPlanSelectOptionDto> listDto = new ArrayList<>();
        List<String> listCode;
        for (TaskPlan2025Entity entity : listEntity) {

            // リストに含まれるユーザのタスクのみタスクリストに追加する
            listCode = this.getListUserFilter(entity.getTaskLevelList());
            if (listCode.contains(String.valueOf(userCode))) {
                listDto.add(this.createDto(entity));
            }

        }

        return listDto;
    }

    private TaskPlanSelectOptionDto createDto(final TaskPlan2025Entity entity) {
        TaskPlanSelectOptionDto dto = new TaskPlanSelectOptionDto();

        BeanUtils.copyProperties(entity, dto);
        
        dto.setValue(String.valueOf(dto.getTaskPlanId()));
        dto.setText(dto.getTaskPlanName());

        return dto;
    }

    private List<String> getListUserFilter(final String data) {
        return Arrays.asList(data.split("-"));
    }

}
