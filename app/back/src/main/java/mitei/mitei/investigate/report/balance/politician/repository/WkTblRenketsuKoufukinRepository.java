package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;

/**
 * wk_tbl_renketsu_koufukin接続用Repository
 */
public interface WkTblRenketsuKoufukinRepository extends JpaRepository<WkTblRenketsuKoufukinEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    //    /**
    //     * 名称を検索対象として全文検索をする
    //     *
    //     * @param searchWords 検索語
    //     * @return 検索結果
    //     */
    //    @Query(value = "SELECT * FROM wk_tbl_renketsu_koufukin WHERE saishin_kbn= 1  AND MATCH(wk_tbl_renketsu_koufukin_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    //    List<WkTblRenketsuKoufukinEntity> findFullText(String searchWords);

    /**
     * ユーザ同一識別コード条件でデータを削除する
     *
     * @param userCode ユーザ同一識別コード
     */
    @Modifying
    @Query(value = "DELETE FROM wk_tbl_renketsu_koufukin WHERE insert_user_code = ?1", nativeQuery = true)
    void deleteByInsertUserCode(Integer userCode);

    /**
     * 重複データを取得する
     *
     * @param userCode ユーザ同一識別コード
     * @param dataKbn  データ区分
     * @return 検索結果
     */
    @Query(value = "SELECT usage_report_id FROM wk_tbl_renketsu_koufukin"
            + "    WHERE insert_user_code = ?1 AND data_kbn = ?2"
            + "    GROUP BY usage_report_id HAVING COUNT(usage_report_id) > 1", nativeQuery = true)
    List<Long> findRenketsuDuplicate(Integer userCode, Integer dataKbn);

    /**
     * 重複データの更新区分を更新する
     *
     * @param userCode     ユーザ同一識別コード
     * @param listReportid 使途報告書Idリスト
     * @param dataKbn      データ区分
     * @param dataKbnPre   変更前データ区分
     */
    @Modifying
    @Query(value = "UPDATE wk_tbl_renketsu_koufukin SET data_kbn = ?3 WHERE insert_user_code = ?1 AND usage_report_id IN ( ?2) AND data_kbn = ?4", nativeQuery = true)
    void updateDataKbnByUserCode(Integer userCode, List<Long> listReportid, Integer dataKbn, Integer dataKbnPre);

    /**
     * ユーザ同一識別コードとデータ識別コードから取得する
     *
     * @param userCode 同一識別コード
     * @param dataKbn  データ区分
     * @return 検索結果
     */
    List<WkTblRenketsuKoufukinEntity> findByInsertUserCodeAndDataKbn(Integer userCode, Integer dataKbn);

}
