package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;

/**
 * 政治資金収支報告書解析予定一覧取得する
 */
@Service
public class SearchRegistBalancesheetPlanErrorService {

    /** 政治資金収支報告書解析予定Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    /**
     * リストを取得する(未処理、最新、文字コードが未確定でない)
     *
     * @return 政治資金収支報告書解析予定リスト
     */
    public List<TaskPlanBalancesheetDetailEntity> practice() {

        return taskPlanBalancesheetDetailRepository
                .findBySaishinKbnAndIsFinishedAndCharsetIsNull(DataHistoryStatusConstants.INSERT.value(), false);
    }

}
