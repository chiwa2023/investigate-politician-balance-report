package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;

/**
 * offering_balancesheet_income_2025接続用Repository
 */
public interface OfferingBalancesheetIncome2025Repository
        extends JpaRepository<OfferingBalancesheetIncome2025Entity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 全文検索をする
     *
     * @param searchWords 検索語
     * @param offset      検索表示位置
     * @param startDate   開始日付
     * @param endDate     終了日付
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_income_2025 WHERE saishin_kbn= 1 AND accrual_date_value BETWEEN ?3 AND ?4 AND MATCH(search_words) AGAINST (?1 IN BOOLEAN MODE) LIMIT 100 OFFSET ?2", nativeQuery = true)
    List<OfferingBalancesheetIncome2025Entity> findFullText(String searchWords, int offset, LocalDate startDate,
            LocalDate endDate);

    /**
     * 検索対象行数を取得する
     *
     * @param searchWords 検索語
     * @param startDate   開始日付
     * @param endDate     終了日付
     * @return 件数
     */
    @Query(value = "SELECT count(*) AS count FROM offering_balancesheet_income_2025 WHERE saishin_kbn= 1 AND accrual_date_value BETWEEN ?2 AND ?3 AND MATCH(search_words) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    Integer findFullTextCount(String searchWords, LocalDate startDate, LocalDate endDate);

    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheetIncome2025Entity> findFirstByOrderByOfferingBalancesheetIncomeCodeDesc();

    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 文書同一識別コード
     * @return データリスト
     */
    List<OfferingBalancesheetIncome2025Entity> findByDocumentCodeOrderByOfferingBalancesheetIncomeId(Long documentCode);

    /**
     * 政治団体とと取引相手の同一識別コードを条件に収入を取得する
     *
     * @param relationCode 取引相手同一識別コード
     * @param saishinKbn   最新区分
     * @param poliOrgCode  政治団体同一識別コード
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2025Entity> findByRelationPoliticalOrgCodeIncomeNotAndSaishinKbnAndPoliticalOrganizationCode(
            Integer relationCode, Integer saishinKbn, Integer poliOrgCode);

    /**
     * 原文書団体名と取引相手名を条件に収入を取得する
     *
     * @param partnerName 取引相手名
     * @param saishinKbn  最新区分
     * @param dantaiName  団体名
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2025Entity> findByPartnerNameIsNotAndSaishinKbnAndDantaiName(String partnerName,
            Integer saishinKbn, String dantaiName);

    /**
     * 団体名称取得用に該当の最初のデータを取得する
     *
     * @param poliOrgCode 政治団体
     * @param saishinKbn  最新区分
     * @return 収入最初の1件
     */
    Optional<OfferingBalancesheetIncome2025Entity> findFirstByPoliticalOrganizationCodeAndSaishinKbn(
            Integer poliOrgCode, Integer saishinKbn);
}
