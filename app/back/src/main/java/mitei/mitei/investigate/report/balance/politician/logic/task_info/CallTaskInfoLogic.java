package mitei.mitei.investigate.report.balance.politician.logic.task_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskInfoRepository;

/**
 * タスク情報を呼び出すLogic
 */
@Component
public class CallTaskInfoLogic {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;
    
    /**
     * 処理を実行する
     *
     * @param listName タスク情報名称リスト
     * @return 検索結果
     */
    public List<TaskInfoEntity> practice(final List<String> listName){
        
        return taskInfoRepository.findByTaskInfoNameInAndSaishinKbn(listName,DataHistoryStatusConstants.INSERT.value());
    }
}
