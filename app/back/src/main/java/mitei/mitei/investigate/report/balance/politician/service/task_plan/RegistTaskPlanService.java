package mitei.mitei.investigate.report.balance.politician.service.task_plan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.task_plan.RegistTaskPlanResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;
import mitei.mitei.investigate.report.balance.politician.logic.task_alert.mail.InsertMailInfoFactoryLogic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.InsertTaskPlanFactoryLogic;
import mitei.mitei.investigate.report.balance.politician.repository.UserWebAccessRepository;

/**
 * タスクの新規登録を行う
 */
@Service
public class RegistTaskPlanService {

    /** メール送信Logic */
    @Autowired
    private InsertMailInfoFactoryLogic insertMailInfoFactoryLogic;

    /** タスク計画挿入Logic */
    @Autowired
    private InsertTaskPlanFactoryLogic insertTaskPlanFactoryLogic;

    /** ユーザーWeb連絡先Repository */
    @Autowired
    private UserWebAccessRepository userWebAccessRepository;

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(RegistTaskPlanService.class);

    /**
     * タスク計画、タスク通知予定情報を登録する
     *
     * @param privilegeDto 権限確認Dto
     * @param listTask     タスクリスト
     */
    public RegistTaskPlanResultDto practice(final CheckPrivilegeDto privilegeDto, final LocalDateTime datetimeShori,
            final List<TaskInfoEntity> listTask) {

        if (Objects.isNull(privilegeDto)) {
            throw new IllegalArgumentException("引数CheckPrivilegeDtoがnullです");
        }

        if (Objects.isNull(listTask)) {
            throw new IllegalArgumentException("引数List<TaskInfoEntity>がnullです");
        }
        
        // 操作者自身のアクセス情報
        UserWebAccessEntity userEntity = userWebAccessRepository
                .findBySaishinKbnAndUserCode(DataHistoryStatusConstants.INSERT.value(), privilegeDto.getLoginUserCode())
                .get();

        // タスクを作成
        int sizeTask = insertTaskPlanFactoryLogic.practice(datetimeShori.getYear(), privilegeDto, listTask);

        // メール通知予定を作成する
        int sizeMail = insertMailInfoFactoryLogic.practice(privilegeDto, datetimeShori, userEntity, listTask);

        RegistTaskPlanResultDto resultDto = new RegistTaskPlanResultDto();

        // タスクの数とタスク計画に登録した数が一致していれば成功
        if (sizeTask == listTask.size()) {
            resultDto.setIsOk(true);
            resultDto.setSuccessCount(sizeTask);
            resultDto.setFailureCount(0);
        } else {
            resultDto.setIsOk(false);
            resultDto.setSuccessCount(listTask.size() - sizeTask);
            resultDto.setFailureCount(sizeTask);
        }

        // 調査したくして入った人(or仕事)ばかりであることを想定しているので、SNSを併用した厳重な通知を必要としてない
        // 操作者が自分あてのメールと該当者のBccなのでTaskの数と一致する
        if (sizeMail != sizeTask) {
            log.warn("メール通知計画が必要数登録されていません");
        }

        return resultDto;
    }

}
