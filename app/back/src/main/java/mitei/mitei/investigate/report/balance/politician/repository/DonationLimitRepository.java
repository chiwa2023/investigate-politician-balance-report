package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.DonationLimitEntity;
import reactor.util.function.Tuple2;

/**
 * donation_limit接続用Repository
 */
public interface DonationLimitRepository extends JpaRepository<DonationLimitEntity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM donation_limit WHERE saishin_kbn= 1 AND MATCH(donation_limit_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<DonationLimitEntity> findFullText(String searchWords);

    /**
     * 企業向け寄付上限リストを取得する(資本金条件)
     *
     * @return 寄付上限リスト
     */
    @Query(value = "SELECT org_capital,limit_amount FROM donation_limit WHERE saishin_kbn= 1 ORDER BY limit_amount DESC", nativeQuery = true)
    List<Tuple2<Long, Long>> findCapitalList();

    /**
     * 組合向け寄付上限リストを取得する(組織員条件)
     *
     * @return 寄付上限リスト
     */
    @Query(value = "SELECT org_insuu,limit_amount FROM donation_limit WHERE saishin_kbn= 1 ORDER BY limit_amount DESC", nativeQuery = true)
    List<Tuple2<Long, Long>> findInsuList();

    /**
     * その他団体向け寄付上限リストを取得する(前年経費条件)
     *
     * @return 寄付上限リスト
     */
    @Query(value = "SELECT org_expens,limit_amount FROM donation_limit WHERE saishin_kbn= 1 ORDER BY limit_amount DESC", nativeQuery = true)
    List<Tuple2<Long, Long>> findExpenseList();

    /**
     * 資本金に対して設定上限額を設定する
     *
     * @param capitalAmount 資本金額
     * @return 寄付上限額
     */
    @Query(value = "SELECT limit_amount FROM donation_limit WHERE saishin_kbn= 1 AND org_capital <= ?1 ORDER BY limit_amount DESC LIMIT 1", nativeQuery = true)
    Long getLimitByCapital(Long capitalAmount);

    /**
     * 組合員に対して設定上限額を設定する
     *
     * @param insuuAmount 組合員員数
     * @return 寄付上限額
     */
    @Query(value = "SELECT limit_amount FROM donation_limit WHERE saishin_kbn= 1 AND org_insuu <= ?1 ORDER BY limit_amount DESC LIMIT 1", nativeQuery = true)
    Long getLimitByInsuu(Long insuuAmount);

    /**
     * 前年経費額に対して設定上限額を設定する
     *
     * @param expenseAmount 前年経費額
     * @return 寄付上限額
     */
    @Query(value = "SELECT limit_amount FROM donation_limit WHERE saishin_kbn= 1 AND org_expens <= ?1 ORDER BY limit_amount DESC LIMIT 1", nativeQuery = true)
    Long getLimitByExpense(Long expenseAmount);

}
