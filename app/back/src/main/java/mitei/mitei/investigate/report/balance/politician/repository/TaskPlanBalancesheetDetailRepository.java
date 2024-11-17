package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;

/**
 * task_plan_balancesheet_detail接続用Repository
 */
public interface TaskPlanBalancesheetDetailRepository
        extends PagingAndSortingRepository<TaskPlanBalancesheetDetailEntity, Long>,
        JpaRepository<TaskPlanBalancesheetDetailEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_balancesheet_detail WHERE saishin_kbn= 1 AND MATCH(task_plan_balancesheet_detail_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<TaskPlanBalancesheetDetailEntity> findFullText(String searchWords);

    /**
     * 最新かつ未処理かつ文字コード決定データをすべて抽出する
     *
     * @param saishinKbn 最新区分
     * @param isFinished 終了該否
     * @param pageable   ページングプロパティ
     * @return ページ
     */
    Page<TaskPlanBalancesheetDetailEntity> findBySaishinKbnAndIsFinishedAndCharsetIsNotNull(Integer saishinKbn,
            boolean isFinished, Pageable pageable);

    /**
     * 処理予定データを抽出する
     *
     * @param saishinKbn 最新区分
     * @param isFinished 処理終了該否
     * @return 検索結果
     */
    List<TaskPlanBalancesheetDetailEntity> findBySaishinKbnAndIsFinishedAndCharsetIsNotNull(Integer saishinKbn,
            boolean isFinished);

    /**
     * 未処理データを抽出する
     *
     * @param saishinKbn 最新区分
     * @param isFinished 処理終了該否
     * @return 検索結果
     */
    List<TaskPlanBalancesheetDetailEntity> findBySaishinKbnAndIsFinishedAndCharsetIsNull(Integer saishinKbn,
            boolean isFinished);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return allBookDto
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TaskPlanBalancesheetDetailEntity> findFirstByOrderByTaskPlanBalancesheetDetailCodeDesc();

}
