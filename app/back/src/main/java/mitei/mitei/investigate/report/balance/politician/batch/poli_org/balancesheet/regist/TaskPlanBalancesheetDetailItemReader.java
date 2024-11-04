package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;

/**
 * 政治資金収支報告書タスク詳細テーブルItemReader
 * SQLを直書きしないでRepositoryのメソッドを使用する
 * cf. https://medium.com/@shashibheemanapally/using-a-crudrepository-to-read-records-in-spring-batch-repositoryitemreader-51eca4361e9b
 */
@Component
public class TaskPlanBalancesheetDetailItemReader extends RepositoryItemReader<TaskPlanBalancesheetDetailEntity> {

    /** ページサイズ */
    private static final int PAGE_SIZE = 2;

    /**
     * コンストラクタ
     *
     * @param taskPlanBalancesheetDetailRepository 政治資金収支報告書準備詳細テーブルRepository
     */
    public TaskPlanBalancesheetDetailItemReader(
            final @Autowired TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository) {

        super();
        super.setPageSize(PAGE_SIZE);
        super.setRepository(taskPlanBalancesheetDetailRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findBySaishinKbnAndIsFinished");
        List<Object> listArgs = new ArrayList<>();
        listArgs.add(DataHistoryStatusConstants.INSERT.value()); // saishinKbn = 1:最新
        listArgs.add(false); // isFinished:false
        super.setArguments(listArgs);
    }

}
