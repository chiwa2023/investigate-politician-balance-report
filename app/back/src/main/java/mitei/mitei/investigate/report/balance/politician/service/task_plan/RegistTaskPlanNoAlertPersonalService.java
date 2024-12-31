package mitei.mitei.investigate.report.balance.politician.service.task_plan;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.logic.task_info.CallTaskInfoLogic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.EraseWkTblPersonalTaskLogic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.InsertTaskPlanSingleLogic;

/**
 * 前回タスク計画を履歴に、、未処理状態かつアラートなし個人用のタスクを追加する(主にワークテーブル用)
 */
@Service
public class RegistTaskPlanNoAlertPersonalService {

    /** タスク情報呼び出しLogic */
    @Autowired
    private CallTaskInfoLogic callTaskInfoLogic;

    /** 主にワークテーブル用過去情報削除Logic */
    @Autowired
    private EraseWkTblPersonalTaskLogic eraseWkTblPersonalTaskLogic;

    /** タスク計画1件挿入Logic */
    @Autowired
    private InsertTaskPlanSingleLogic insertTaskPlanSingleLogic;

    /**
     * 処理を行う
     *
     * @param privilegeDto 権限確認Dto
     * @param taskName     タスク名
     * @return タスク計画同一識別コード(西行が終わったときに作業計画更新用に把握する)
     */
    public int practice(final CheckPrivilegeDto privilegeDto, final String taskName,final int year) {

        // タスク名称からタスク情報を呼び出し登録する
        TaskInfoEntity taskInfoEntity = callTaskInfoLogic.practice(taskName);

        if (Objects.isNull(taskInfoEntity)) {
            return 0;
        } else {
            
            eraseWkTblPersonalTaskLogic.practice(year,Collections.singletonList(taskInfoEntity.getTaskInfoName()),
                    privilegeDto);
        }
        return insertTaskPlanSingleLogic.practice(year,taskInfoEntity, privilegeDto);
    }

}
