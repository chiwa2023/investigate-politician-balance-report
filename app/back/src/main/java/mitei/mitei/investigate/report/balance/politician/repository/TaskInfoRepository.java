package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;

/**
 * task_info接続用Repository
 */
public interface TaskInfoRepository  extends JpaRepository<TaskInfoEntity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_info WHERE saishin_kbn= 1 AND MATCH(task_info_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<TaskInfoEntity> findFullText(String searchWords);
}
