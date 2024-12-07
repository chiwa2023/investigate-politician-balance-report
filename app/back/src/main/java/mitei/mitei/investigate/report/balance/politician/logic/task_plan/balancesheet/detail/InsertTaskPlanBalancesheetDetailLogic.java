package mitei.mitei.investigate.report.balance.politician.logic.task_plan.balancesheet.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 展開後のファイルを処理する予定リストを作成する
 */
@Component
public class InsertTaskPlanBalancesheetDetailLogic {

    /** 政治資金収支報告書解析予定Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    /**
     * 作成処理を行う
     *
     * @param listStorage  書証保存リスト
     * @param privilegeDto 権限確認Dto
     * @return 登録件数
     */
    public int practice(final List<SaveStorageResultDto> listStorage, final CheckPrivilegeDto privilegeDto) {

        List<TaskPlanBalancesheetDetailEntity> listEntity = new ArrayList<>();

        for (SaveStorageResultDto storageDto : listStorage) {
            listEntity.add(this.createEntity(storageDto, privilegeDto));
        }

        // 最大コードを取得
        Long code = 0L;
        Optional<TaskPlanBalancesheetDetailEntity> optional = taskPlanBalancesheetDetailRepository
                .findFirstByOrderByTaskPlanBalancesheetDetailCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getTaskPlanBalancesheetDetailCode();
        }

        for (TaskPlanBalancesheetDetailEntity detailEntity : listEntity) {
            code++;
            detailEntity.setTaskPlanBalancesheetDetailCode(code);
        }
        return taskPlanBalancesheetDetailRepository.saveAll(listEntity).size();
    }

    private TaskPlanBalancesheetDetailEntity createEntity(final SaveStorageResultDto storageDto,
            final CheckPrivilegeDto privilegeDto) {
        TaskPlanBalancesheetDetailEntity entity = new TaskPlanBalancesheetDetailEntity();

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        BeanUtils.copyProperties(storageDto, entity);

        return entity;
    }
}
