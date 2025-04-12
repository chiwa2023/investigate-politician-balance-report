package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinSelectDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageOutcomeEntity;

/**
 * wk_tbl_usage_outcome接続用Repository
 */
public interface WkTblUsageOutcomeRepository extends JpaRepository<WkTblUsageOutcomeEntity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    // /**
    // * 名称を検索対象として全文検索をする
    // *
    // * @param searchWords 検索語
    // * @return 検索結果
    // */
    // @Query(value = "SELECT * FROM wk_tbl_usage_outcome WHERE saishin_kbn= 1 AND
    // MATCH(wk_tbl_usage_outcome_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)",
    // nativeQuery = true)
    // List<WkTblUsageOutcomeEntity> findFullText(String searchWords);

    /**
     * 収支報告書と使途報告書を連結して連結結果Entityとして出力する
     *
     * @param userCode            ユーザ同一識別コード
     * @param documentCodeUsage   文書同一識別コード使途報告書
     * @param documentCodeBalance 文書同一識別コード収支報告書
     * @param pageable            ページング
     * @return 検索結果
     */
    @Query(value = "SELECT 0 AS wk_tbl_renketsu_koufukin_id, ?4 AS data_kbn,"
            + " 1 AS saishin_kbn, p_balance.houkoku_nen AS houkoku_nen,"
            + " p_balance.offering_balancesheet_outcome_id AS balancesheet_id,p_usage.party_usage_0804_report_id AS usage_report_id,"
            + " p_usage.dantai_name AS daihyou_name,p_usage.daihyou_name AS dantai_name,"
            + " p_usage.offering_date AS balancesheet_offering_date,p_usage.offering_date AS usage_offering_date,"
            + " p_balance.accrual_date_value,p_balance.himoku AS balance_himoku,p_balance.mokuteki AS balance_mokuteki ,"
            + " p_usage.amount_koufukin,p_usage.amount_my_funds,p_usage.amount_all,"
            + " p_usage.sheet_himoku AS usage_himoku,p_usage.shishutsu_kbn_name AS usage_shishutsu_name,p_usage.usage_item AS usage_usage_item,"
            + " p_usage.payee_name AS payee_name,p_usage.address AS payee_address,"
            + " p_usage.insert_user_id AS insert_user_id,p_usage.insert_user_code AS insert_user_code,"
            + " p_usage.insert_user_name AS insert_user_name,p_usage.insert_timestamp AS insert_timestamp,"
            + " 0 AS update_user_id,0 AS update_user_code,"
            + " '' AS update_user_name,'1948-07-29 00:00:00' AS update_timestamp"
            + "  FROM wk_tbl_usage_outcome AS p_usage" + "  INNER JOIN wk_tbl_balancesheet_outcome AS p_balance"
            + "      ON p_usage.amount_all = p_balance.kingaku AND p_usage.accrual_date_value = p_balance.accrual_date_value"
            + "      AND p_usage.usage_item = p_balance.mokuteki"
            + "      AND p_usage.payee_name = p_balance.partner_name  AND p_usage.address = p_balance.partner_jusho"
            + "      WHERE p_usage.insert_user_code = ?1  AND p_usage.document_code = ?2 AND p_balance.document_code = ?3", nativeQuery = true)
    Page<WkTblRenketsuKoufukinSelectDto> findRenketsuEntity(Integer userCode, Long documentCodeUsage,
            Long documentCodeBalance, Integer dataKbn, Pageable pageable);

    /**
     * ユーザ同一識別コード条件でデータを削除する
     *
     * @param userCode ユーザ同一識別コード
     */
    @Modifying
    @Query(value = "DELETE FROM wk_tbl_usage_outcome WHERE insert_user_code = ?1", nativeQuery = true)
    void deleteByInsertUserCode(Integer userCode);

    /**
     * 交付金連結テーブルに存在しないデータ件数を取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT count(*) FROM test_politician_balance_report_investigate.wk_tbl_usage_outcome"
            + " WHERE insert_user_code = ?1 AND sheet_amount_all_koufukin > 0"
            + "   AND party_usage_0804_report_id NOT IN ("
            + "      SELECT usage_report_id FROM wk_tbl_renketsu_koufukin" + "   )", nativeQuery = true)
    Integer findCountFailureRenketsuByUserCode(Integer userCode);

    /**
     * 交付金連結テーブルに存在しないデータをページングで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @param pageable ページング条件
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM test_politician_balance_report_investigate.wk_tbl_usage_outcome"
            + " WHERE insert_user_code = ?1 AND sheet_amount_all_koufukin > 0"
            + "   AND party_usage_0804_report_id NOT IN ("
            + "      SELECT usage_report_id FROM wk_tbl_renketsu_koufukin" + "   )", nativeQuery = true)
    List<WkTblUsageOutcomeEntity> findFailureRenketsuByUserCode(Integer userCode, Pageable pageable);

}
