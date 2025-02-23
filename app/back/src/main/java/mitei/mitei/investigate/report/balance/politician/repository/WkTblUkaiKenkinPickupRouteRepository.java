package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * wk_tbl_ukai_kenkin_pickup_route接続用Repository
 */
public interface WkTblUkaiKenkinPickupRouteRepository extends JpaRepository<WkTblUkaiKenkinPickupRouteEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin_pickup_route WHERE saishin_kbn= 1 AND MATCH(wk_tbl_ukai_kenkin_pickup_route_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<WkTblUkaiKenkinPickupRouteEntity> findFullText(String searchWords);

    /**
     * ユーザ同一識別コード条件でデータを削除する
     *
     * @param userCode ユーザ同一識別コード
     */
    @Modifying
    @Query(value = "DELETE FROM wk_tbl_ukai_kenkin_pickup_route WHERE insert_user_code = ?1", nativeQuery = true)
    void deleteByInsertUserCode(Integer userCode);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return allBookDto
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WkTblUkaiKenkinPickupRouteEntity> findFirstByOrderByWkTblUkaiKenkinPickupRouteCodeDesc();

    /**
     * 同一ルートのデータを抽出する
     *
     * @param routeCode 迂回献金経路ワークテーブル同一識別コード
     * @return 検索結果
     */
    List<WkTblUkaiKenkinPickupRouteEntity> findByWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(Integer routeCode);

    /**
     * 作成したユーザに紐づく経路データを抽出する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 検索結果
     */
    List<WkTblUkaiKenkinPickupRouteEntity> findByInsertUserCodeOrderByWkTblUkaiKenkinPickupRouteCodeAscPickupStageAsc(
            Integer userCode);

    /**
     * 操作ユーザと経路番号から明細を抽出する
     *
     * @param userCode  操作ユーザ同一識別コード
     * @param wktblCode 経路識別コード
     * @return 検索結果
     */
    List<WkTblUkaiKenkinPickupRouteEntity> findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(
            Integer userCode, Integer wktblCode);

}
