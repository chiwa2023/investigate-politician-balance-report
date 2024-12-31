package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;

/**
 * wk_tbl_fukisai_balancesheet接続用Repository
 */
public interface WkTblFukisaiBalancesheetRepository extends JpaRepository<WkTblFukisaiBalancesheetEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_fukisai_balancesheet WHERE saishin_kbn= 1 AND MATCH(wk_tbl_fukisai_balancesheet_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<WkTblFukisaiBalancesheetEntity> findFullText(String searchWords);

    /**
     * ユーザ同一識別コード条件でデータを削除する
     *
     * @param userCode ユーザ同一識別コード
     */
    @Modifying
    @Query(value = "DELETE FROM wk_tbl_fukisai_balancesheet WHERE insert_user_code = ?1", nativeQuery = true)
    void deleteByInsertUserCode(Integer userCode);

    /**
     * idを初期化する
     */
    @Modifying
    @Query(value = "ALTER TABLE wk_tbl_fukisai_balancesheet auto_increment = 1", nativeQuery = true)
    void initialId();

    /**
     * ユーザ同一識別コードと政治団体識別コードが一致する取引相手同一識別コードリストを作成する
     *
     * @param userCode    ユーザ同一識別コード
     * @param poliOrgCode 政治団体同一識別コード
     * @return 取引相手コードリスト
     */
    @Query(value = "SELECT DISTINCT relation_political_org_code FROM wk_tbl_fukisai_balancesheet WHERE insert_user_code =?1 AND saishin_kbn= 1 AND political_organization_code = ?2", nativeQuery = true)
    List<Integer> findUserAndRelationCode(Integer userCode, Integer poliOrgCode);

    /**
     * ユーザ同一識別コードと原文書政治団体名が一致する取引相手名称リストを作成する
     *
     * @param userCode ユーザ同一識別コード
     * @param orgName  団体名称
     * @return 取引相手名称リスト
     */
    @Query(value = "SELECT DISTINCT partner_name FROM wk_tbl_fukisai_balancesheet WHERE insert_user_code =?1 AND saishin_kbn= 1 AND dantai_name = ?2", nativeQuery = true)
    List<String> findUserAndPartnerName(Integer userCode, String orgName);

    /**
     * ユーザ同一識別コードが一致する最初の1件を取得する(政治団体名称取得用)
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    Optional<WkTblFukisaiBalancesheetEntity> findFirstByInsertUserCode(Integer userCode);

    /**
     * ページング用に検索順キーの数を算出する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索順キー数(重複しない)
     */
    @Query(value = "SELECT count(DISTINCT search_order_key) FROM wk_tbl_fukisai_balancesheet  WHERE insert_user_code = ?1", nativeQuery = true)
    Integer findCountRelationByUser(Integer userCode);

    /**
     * 抽出上限付きで実で該当検索順キーを取得する
     *
     * @param userCode ユーザ同一識別コード
     * @param pageable ページ情報
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT search_order_key FROM wk_tbl_fukisai_balancesheet  WHERE insert_user_code = ?1", nativeQuery = true)
    List<String> findSearchOrderKeyListByUser(Integer userCode, Pageable pageable);

    /**
     * 検索順キーに合致し、取引相手順に整列された取引情報をすべて取得する(あらかじめ検索キーの大きさは制御されている)
     *
     * @param userCode ユーザ同一識別コード
     * @param listKey  検索順Keyリスト
     * @return 検索結果
     */
    List<WkTblFukisaiBalancesheetEntity> findByInsertUserCodeAndSearchOrderKeyInOrderBySearchOrderKeyAscYoushikiKbnAscAccrualDateValueAsc(
            Integer userCode, List<String> listKey);

}
