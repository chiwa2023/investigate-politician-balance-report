package mitei.mitei.investigate.report.balance.politician.logic.task_plan.party_usage.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政党交付金使途報告書一括解析予定データを挿入する
 */
@Component
public class InsertTaskPlanPartyUsageDetailLogic {

    /** 政党交付金使途報告書一括解析予定Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    /**
     * 作成処理を行う
     *
     * @param listStorage  書証保存リスト
     * @param privilegeDto 権限確認Dto
     * @return 登録件数
     */
    public int practice(final List<SaveStorageResultDto> listStorage, final CheckPrivilegeDto privilegeDto) {

        List<TaskPlanPartyUsageDetailEntity> listEntity = new ArrayList<>();

        for (SaveStorageResultDto storageDto : listStorage) {
            listEntity.add(this.createEntity(storageDto, privilegeDto));
        }

        // 最大コードを取得
        Long code = 0L;
        Optional<TaskPlanPartyUsageDetailEntity> optional = taskPlanPartyUsageDetailRepository
                .findFirstByOrderByTaskPlanPartyUsageDetailCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getTaskPlanPartyUsageDetailCode();
        }

        for (TaskPlanPartyUsageDetailEntity detailEntity : listEntity) {
            code++;
            detailEntity.setTaskPlanPartyUsageDetailCode(code);
        }
        return taskPlanPartyUsageDetailRepository.saveAll(listEntity).size();

    }

    private TaskPlanPartyUsageDetailEntity createEntity(final SaveStorageResultDto storageDto,
            final CheckPrivilegeDto privilegeDto) {
        TaskPlanPartyUsageDetailEntity entity = new TaskPlanPartyUsageDetailEntity();

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        BeanUtils.copyProperties(storageDto, entity);

        return entity;
    }

}
