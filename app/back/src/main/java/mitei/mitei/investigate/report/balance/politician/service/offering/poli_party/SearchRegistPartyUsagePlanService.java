package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;

/**
 * 政治資金収支報告書解析予定一覧取得する
 */
@Service
public class SearchRegistPartyUsagePlanService {

    /** 政治資金収支報告書解析予定Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    /**
     * リストを取得する(未処理、最新、文字コードが未確定でない)
     *
     * @return 政治資金収支報告書解析予定リスト
     */
    public List<TaskPlanPartyUsageDetailEntity> practice() {

        return taskPlanPartyUsageDetailRepository
                .findBySaishinKbnAndIsFinishedAndCharsetIsNotNull(DataHistoryStatusConstants.INSERT.value(), false);
    }

}
