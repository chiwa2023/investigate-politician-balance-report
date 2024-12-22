package mitei.mitei.investigate.report.balance.politician.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * zengin_org_change_branch接続用Repository
 */
public interface ZenginOrgChangeBranchRepository extends JpaRepository<ZenginOrgChangeBranchEntity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_change_branch WHERE saishin_kbn= 1 AND MATCH(zengin_org_change_branch_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<ZenginOrgChangeBranchEntity> findFullText(String searchWords);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return optional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ZenginOrgChangeBranchEntity> findFirstByOrderByZenginOrgChangeBranchCodeDesc();

    /**
     * 未処理かつ変更区分条件で異動支店を抽出する
     *
     * @param changeKbn   変更区分
     * @param isFisinshed 処理終了フラグ
     * @param saishinKbn  最新区分
     * @param pageable    ページングDto
     * @return 検索結果
     */
    Page<ZenginOrgChangeBranchEntity> findByChangeKbnAndIsFinishedAndSaishinKbn(Integer changeKbn, boolean isFisinshed,
            Integer saishinKbn, Pageable pageable);

    /**
     * 変更区分が条件通りの異動データを取得する(ただし使い方として取得確認済は除く)
     *
     * @param changeKbn 変更区分
     * @param checkedId 除外Id
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_change_branch WHERE is_finished = 0 AND saishin_kbn = 1 AND change_kbn =?1 AND zengin_org_change_branch_id NOT IN (?2) Limit 30", nativeQuery = true)
    List<ZenginOrgChangeBranchEntity> findByYetFinished(Integer changeKbn, List<Integer> checkedId);

    /**
     * 検索条件に基づいて検索する(金融機関コードなし)
     *
     * @param listChangeKbn 変更区分リスト
     * @param saishinKbn    最新区分
     * @param startTime     開始日時
     * @param endTime       終了日時
     * @return 検索結果
     */
    List<ZenginOrgChangeBranchEntity> findByChangeKbnInAndSaishinKbnAndInsertTimestampBetween(
            List<Integer> listChangeKbn, int saishinKbn, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 検索条件に基づいて検索する(金融機関コードあり)
     *
     * @param listChangeKbn 変更区分リスト
     * @param saishinKbn    最新区分
     * @param startTime     開始日時
     * @param endTime       終了日時
     * @param financialCode 金融機関コード
     * @return 検索結果
     */
    List<ZenginOrgChangeBranchEntity> findByChangeKbnInAndSaishinKbnAndInsertTimestampBetweenAndOrgCode(
            List<Integer> listChangeKbn, Integer saishinKbn, LocalDateTime startTime, LocalDateTime endTime,
            String financialCode);

    /**
     * 未処理データを抽出する
     *
     * @param isFisinshed 終了フラグ
     * @param saishinKbn  最新区分
     * @return 検索結果
     */
    List<ZenginOrgChangeBranchEntity> findByIsFinishedAndSaishinKbn(boolean isFisinshed, Integer saishinKbn);

}
