package mitei.mitei.investigate.report.balance.politician.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinSelectDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageIncomeEntity;

/**
 * wk_tbl_usage_income接続用Repository
 */
public interface WkTblUsageIncomeRepository extends JpaRepository<WkTblUsageIncomeEntity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    // /**
    // * 名称を検索対象として全文検索をする
    // *
    // * @param searchWords 検索語
    // * @return 検索結果
    // */
    // @Query(value = "SELECT * FROM wk_tbl_usage_income WHERE saishin_kbn= 1 AND
    // MATCH(wk_tbl_usage_income_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)",
    // nativeQuery = true)
    // List<WkTblUsageIncomeEntity> findFullText(String searchWords);

    /**
     * 収支報告書と使途報告書を連結して連結結果Entityとして出力する
     *
     * @param userCode            ユーザ同一識別コード
     * @param documentCodeUsage   文書同一識別コード使途報告書
     * @param documentCodeBalance 文書同一識別コード収支報告書
     * @param pageable            ページング
     * @return 検索結果
     */
    @Query(value = "SELECT"
            + " p_balance.offering_balancesheet_income_id AS wk_tbl_renketsu_koufukin_id, ?4 AS data_kbn,"
            + " 1 AS saishin_kbn, p_balance.houkoku_nen AS houkoku_nen,"
            + " p_balance.offering_balancesheet_income_id AS balancesheet_id,p_usage.party_usage_0802_kbn_02_report_id AS usage_report_id,"
            + " p_usage.dantai_name AS daihyou_name,p_usage.daihyou_name AS dantai_name,"
            + " p_usage.offering_date AS balancesheet_offering_date,p_usage.offering_date AS usage_offering_date,"
            + " p_balance.accrual_date_value,p_balance.item_name AS balance_himoku, '' AS balance_mokuteki,"
            + " p_usage.amount AS amount_koufukin,0 AS amount_my_funds,p_balance.kingaku as amount_all,"
            + " '' AS usage_himoku,'' AS usage_shishutsu_name,'' AS usage_usage_item,"
            + " p_usage.item_name AS payee_name,'' AS payee_address,"
            + " p_usage.insert_user_id AS insert_user_id,p_usage.insert_user_code AS insert_user_code,"
            + " p_usage.insert_user_name AS insert_user_name,p_usage.insert_timestamp AS insert_timestamp,"
            + " 0 AS update_user_id,0 AS update_user_code,"
            + " '' AS update_user_name,'1948-07-29 00:00:00' AS update_timestamp"
            + "   FROM wk_tbl_usage_income AS p_usage" + "   INNER JOIN wk_tbl_balancesheet_income AS p_balance"
            + "      ON p_usage.amount = p_balance.kingaku AND p_usage.accrual_date_value = p_balance.accrual_date_value"
            + "      WHERE p_usage.insert_user_code = ?1  AND p_usage.document_code = ?2 AND p_balance.document_code = ?3", nativeQuery = true)
    Page<WkTblRenketsuKoufukinSelectDto> findRenketsuEntity(Integer userCode, Long documentCodeUsage,
            Long documentCodeBalance, Integer dataKbn, Pageable pageable);

    /**
     * ユーザ同一識別コード条件でデータを削除する
     *
     * @param userCode ユーザ同一識別コード
     */
    @Modifying
    @Query(value = "DELETE FROM wk_tbl_usage_income WHERE insert_user_code = ?1", nativeQuery = true)
    void deleteByInsertUserCode(Integer userCode);

}
