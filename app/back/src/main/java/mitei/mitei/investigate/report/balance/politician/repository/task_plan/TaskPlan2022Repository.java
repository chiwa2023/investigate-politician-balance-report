package mitei.mitei.investigate.report.balance.politician.repository.task_plan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.task_plan.TaskPlan2022Entity;

/**
 * task_plan_2022接続用Repository
 */
public interface TaskPlan2022Repository extends JpaRepository<TaskPlan2022Entity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_2022 WHERE saishin_kbn= 1 AND MATCH(task_plan_2022_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<TaskPlan2022Entity> findFullText(String searchWords);

    /**
     * 未処理タスクリストを検索する
     *
     * @param saishinKbn      最新区分
     * @param listPoliOrgCode 政治団体同一識別コードリスト
     * @return 検索結果
     */
    List<TaskPlan2022Entity> findBySaishinKbnAndPoliticalOrganizationCodeIn(Integer saishinKbn,
            List<Integer> listPoliOrgCode);

    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TaskPlan2022Entity> findFirstByOrderByTaskPlanCodeDesc();

    /**
     * 未処理タスクリストを検索する
     *
     * @param saishinKbn 最新区分
     * @param taskName   タスク名称
     * @return 検索結果
     */
    List<TaskPlan2022Entity> findBySaishinKbnAndTaskPlanName(Integer saishinKbn, String taskName);

}
