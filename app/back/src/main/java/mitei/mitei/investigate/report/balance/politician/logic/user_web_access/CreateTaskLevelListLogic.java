package mitei.mitei.investigate.report.balance.politician.logic.user_web_access;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;
import mitei.mitei.investigate.report.balance.politician.repository.UserWebAccessRepository;

/**
 * タスク情報からWebアクセス用対象ユーザリストを作成する
 */
@Component
public class CreateTaskLevelListLogic {

    /** ユーザWebアクセスRepository */
    @Autowired
    private UserWebAccessRepository userWebAccessRepository;

    /**
     * ユーザリスト作成処理を行う
     *
     * @param taskLevelText (カンマ区切りされた)タスク水準文字列
     * @return ユーザ情報リスト
     */
    public List<UserWebAccessEntity> practice(final String taskLevelText) {

        return userWebAccessRepository.findBySaishinKbnAndTaskLevelIn(DataHistoryStatusConstants.INSERT.value(),
                this.convertTaskLevelList(taskLevelText));
    }

    /* タスク情報からタスク水準リストを作成する */
    private List<Integer> convertTaskLevelList(final String text) {

        List<Integer> list = new ArrayList<>();

        for (String data : text.split(",")) {
            try {
                list.add(Integer.parseInt(data));
            }catch(NumberFormatException exception) {
                // スルーする  TODO　処理が必要になったら修正する
            }
        }

        return list;
    }

}
