package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDocumentEntity;

/**
 * task_plan_party_usage_document接続用Repository
 */
public interface TaskPlanPartyUsageDocumentRepository  extends JpaRepository<TaskPlanPartyUsageDocumentEntity, Long>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_party_usage_document WHERE saishin_kbn= 1 AND MATCH(task_plan_party_usage_document_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<TaskPlanPartyUsageDocumentEntity> findFullText(String searchWords);

    /**
     * 最新データをページングしながら取り出す
     *
     * @param limit 取り出し件数
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_party_usage_document WHERE saishin_kbn= 1 LIMIT ?1", nativeQuery = true)
    List<TaskPlanPartyUsageDocumentEntity> findBySaishinKbnLimit(Integer limit);

    /**
     * 最新データ件数を取得する
     *
     * @return 最新データ件数
     */
    @Query(value = "SELECT count(*) FROM task_plan_party_usage_document WHERE saishin_kbn= 1", nativeQuery = true)
    Integer findBySaishinKbnCount();

    
    
    
}
