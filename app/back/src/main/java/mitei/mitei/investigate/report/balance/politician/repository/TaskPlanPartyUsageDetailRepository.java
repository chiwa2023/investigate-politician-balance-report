package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;

/**
 * task_plan_party_usage_detail接続用Repository
 */
public interface TaskPlanPartyUsageDetailRepository  extends JpaRepository<TaskPlanPartyUsageDetailEntity, Long>{

    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_party_usage_detail WHERE saishin_kbn= 1 AND MATCH(task_plan_party_usage_detail_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<TaskPlanPartyUsageDetailEntity> findFullText(String searchWords);


    /**
     * 処理予定データを抽出する
     *
     * @param saishinKbn 最新区分
     * @param isFinished 処理終了該否
     * @return 検索結果
     */
    List<TaskPlanPartyUsageDetailEntity> findBySaishinKbnAndIsFinishedAndCharsetIsNotNull(Integer saishinKbn,
            boolean isFinished);

    /**
     * 未処理データを抽出する
     *
     * @param saishinKbn 最新区分
     * @param isFinished 処理終了該否
     * @return 検索結果
     */
    List<TaskPlanPartyUsageDetailEntity> findBySaishinKbnAndIsFinishedAndCharsetIsNull(Integer saishinKbn,
            boolean isFinished);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return allBookDto
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TaskPlanPartyUsageDetailEntity> findFirstByOrderByTaskPlanPartyUsageDetailCodeDesc();

}
