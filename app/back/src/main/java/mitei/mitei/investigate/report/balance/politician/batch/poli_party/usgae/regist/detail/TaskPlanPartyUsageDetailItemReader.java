package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;

/**
 * 政党交付金使途報告書タスク詳細テーブルItemReader
 * SQLを直書きしないでRepositoryのメソッドを使用する
 */
@Component
public class TaskPlanPartyUsageDetailItemReader extends RepositoryItemReader<TaskPlanPartyUsageDetailEntity> {

    /**
     * コンストラクタ
     *
     * @param taskPlanPartyUsageDetailRepository 政党交付金使途報告書準備詳細テーブルRepository
     */
    public TaskPlanPartyUsageDetailItemReader(
            final @Autowired TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository) {

        super();
        super.setRepository(taskPlanPartyUsageDetailRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findBySaishinKbnAndIsFinishedAndCharsetIsNotNull");
        List<Object> listArgs = new ArrayList<>();
        listArgs.add(DataHistoryStatusConstants.INSERT.value()); // saishinKbn = 1:最新
        listArgs.add(false); // isFinished:false
        super.setArguments(listArgs);
    }

}
